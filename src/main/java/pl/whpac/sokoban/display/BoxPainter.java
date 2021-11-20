package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoxPainter implements Painter {

    public void paint(GraphicsContext gc, int x, int y, int size) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, size, size);
    }
}