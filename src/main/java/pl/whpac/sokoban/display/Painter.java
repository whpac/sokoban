package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;

public interface Painter {

    void paint(GraphicsContext gc, int x, int y, int size);
}
