package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class WallPainter implements Painter {
    private Image img;
    private static WallPainter painter;
    private static EmptyFieldPainter groundPainter;

    private WallPainter(){}

    public static WallPainter getPainter() throws IOException {
        if(painter == null){
            painter = new WallPainter();
            painter.loadSprite();
        }
        if(groundPainter == null){
            groundPainter = EmptyFieldPainter.getPainter();
        }
        return painter;
    }

    public void loadSprite() throws IOException{
        URL url = getClass().getResource("block.png");
        if(url == null) throw new IOException("Unable to load block sprite");
        img = new Image(url.toExternalForm());
    }

    public void paint(GraphicsContext gc, int x, int y, int size){
        if(img == null) return;
        groundPainter.paint(gc, x, y, size);
        gc.drawImage(img, x, y);
    }
}