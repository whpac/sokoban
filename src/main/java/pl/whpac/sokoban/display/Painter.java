package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

public interface Painter {

    void loadSprite() throws IOException;
    void paint(GraphicsContext gc, int x, int y, int size);
}
