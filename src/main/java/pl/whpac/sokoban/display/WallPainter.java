package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallPainter implements Painter {

    public void paint(GraphicsContext gc, int x, int y, int size) {
        gc.setFill(Color.ORANGE);
        gc.fillRect(x, y, size, size);
    }
}