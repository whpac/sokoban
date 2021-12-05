package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class BoxPainter implements Painter {
    private Image img;
    private static BoxPainter painter;

    private BoxPainter() {}

    public static BoxPainter getPainter() throws IOException {
        if(painter == null){
            painter = new BoxPainter();
            painter.loadSprite();
        }
        return painter;
    }

    public void loadSprite() throws IOException{
        URL url = getClass().getResource("box.png");
        if(url == null) throw new IOException("Unable to load box sprite");
        img = new Image(url.toExternalForm());
    }

    public void paint(GraphicsContext gc, int x, int y, int size) {
        gc.drawImage(img, x, y);
    }
}