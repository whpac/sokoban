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

public class BoardDisplay {
    private final int canvasWidth;
    private final int canvasHeight;

    private final GraphicsContext gc;
    private final AnimationTimer timer;
    private Board board;

    public BoardDisplay(Stage stage) {
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
        timer.start();
    }

    private void render() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        int size = 64;
        // Board X and Y coordinates relative to the window
        // so that the board is centered
        int bx = (canvasWidth - board.width * size) / 2;
        int by = (canvasHeight - board.height * size) / 2;

        try {
            Painter p;
            for (int x = 0; x < board.width; x++) {
                for (int y = 0; y < board.height; y++) {
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
            System.out.println("Error " + e);
        }

        String remaining = Integer.toString(board.getRemainingBoxes());
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", 20));
        gc.fillText(remaining, bx + 250, by + 100);
    }

    private void processKeyDown(String keycode){
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyDown);
        board.dispatchEvent(e);
    }

    private void processKeyUp(String keycode){
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyUp);
        board.dispatchEvent(e);
    }
}
