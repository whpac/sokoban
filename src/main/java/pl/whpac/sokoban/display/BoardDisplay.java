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

import java.util.HashSet;

public class BoardDisplay {
    private final int canvasWidth;
    private final int canvasHeight;

    private final GraphicsContext gc;
    private final HashSet<String> currentlyActiveKeys;
    private final AnimationTimer timer;
    private Board board;

    public BoardDisplay(Stage stage) {
        canvasWidth = 400;
        canvasHeight = 200;

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        stage.setTitle("Sokoban");
        stage.show();

        currentlyActiveKeys = new HashSet<>();
        scene.setOnKeyPressed(event -> processKeyDown(event.getCode().toString()));
        scene.setOnKeyReleased(event -> processKeyUp(event.getCode().toString()));

        timer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                tick();
            }
        };
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void start(){
        timer.start();
    }

    private void tick(){
        // processUserInput()
        // hasBoardChanged()
        render();
    }

    private void render() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        int size = 50;

        Painter p;
        for(int x = 0; x < board.width; x++){
            for(int y = 0; y < board.height; y++){
                Field f = board.getFieldAt(x, y);
                Entity e = board.getEntityAt(x, y);

                p = PainterFactory.getFieldPainter(f);
                if(p != null) p.paint(gc, size*x, size*y, size);

                p = PainterFactory.getEntityPainter(e);
                if(p != null) p.paint(gc, size*x, size*y, size);
            }
        }

        String remaining = Integer.toString(board.getRemainingBoxes());
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", 20));
        gc.fillText(remaining, 250, 100);
    }

    private void processKeyDown(String keycode){
        currentlyActiveKeys.add(keycode);
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyDown);
        board.dispatchEvent(e);
    }

    private void processKeyUp(String keycode){
        currentlyActiveKeys.remove(keycode);
        Event e = new KeyboardEvent(keycode, KeyboardEvent.KeyboardEventType.keyUp);
        board.dispatchEvent(e);
    }
}
