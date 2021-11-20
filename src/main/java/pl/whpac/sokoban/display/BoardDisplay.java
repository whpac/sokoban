package pl.whpac.sokoban.display;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.board.Entity;
import pl.whpac.sokoban.board.Field;

public class BoardDisplay {
    private final GraphicsContext gc;

    public BoardDisplay(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(400, 200);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        stage.setTitle("Sokoban");
        stage.show();
    }

    public void display(Board board) {
        final int size = 50;
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
    }
}
