package pl.whpac.sokoban.display;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.board.Entity;
import pl.whpac.sokoban.board.Field;
import pl.whpac.sokoban.input.Event;
import pl.whpac.sokoban.input.KeyboardEvent;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class BoardDisplay {
    private final int canvasWidth;
    private final int canvasHeight;
    private final Stage stage;

    private final GraphicsContext gc;
    private final AnimationTimer timer;
    private Board board;
    private boolean isRunning = false;

    public BoardDisplay(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        canvasWidth = 600;
        canvasHeight = 450;

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        stage.setTitle("Sokoban");
        stage.show();

        scene.setOnKeyPressed(event -> processKeyDown(event.getCode().toString()));
        scene.setOnKeyReleased(event -> processKeyUp(event.getCode().toString()));

        timer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                render();
            }
        };
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void start(){
        isRunning = true;
        timer.start();
    }

    public void stop() {
        isRunning = false;
        timer.stop();
    }

    private void render() {
        int size = 64;
        // Board X and Y coordinates relative to the window
        // so that the board is centered
        int bx = (canvasWidth - board.width * size) / 2;
        int by = (canvasHeight - board.height * size) / 2;

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        try {
            Painter p;
            int margin_x = (int)Math.ceil(bx / (double)size);
            int margin_y = (int)Math.ceil(by / (double)size);
            for (int x = -margin_x; x < board.width + margin_x; x++) {
                for (int y = -margin_y; y < board.height + margin_y; y++) {
                    Field f = board.getFieldAt(x, y);
                    Entity e = board.getEntityAt(x, y);

                    p = PainterFactory.getFieldPainter(f);
                    if (p != null) p.paint(gc, bx + size * x, by + size * y, size);

                    if(e == null) continue;
                    p = PainterFactory.getEntityPainter(e);
                    if (p != null) p.paint(gc, bx + size * x, by + size * y, size);
                }
            }
        }catch(IOException e){
            drawStatus(e.getMessage());
            this.stop();
        }

        int boxes_left = board.getRemainingBoxes();
        String status;
        if(boxes_left == 0){
            status = "You won!";
            stop();
        }else{
            status = "Remaining boxes: " + boxes_left;
        }
        drawStatus(status);
    }

    private void drawStatus(String text){
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 20));
        gc.fillText(text, 12, 32);
    }

    private void processKeyDown(String keycode){
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyDown);
        if(Objects.equals(keycode, "ESCAPE")){
            stop();
            stage.close();
        }
        if(isRunning) board.dispatchEvent(e);
    }

    private void processKeyUp(String keycode){
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyUp);
        if(isRunning) board.dispatchEvent(e);
    }
}
