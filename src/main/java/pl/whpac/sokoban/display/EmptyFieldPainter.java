package pl.whpac.sokoban.display;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class EmptyFieldPainter implements Painter {
    private Image img;
    private static EmptyFieldPainter painter;

    private EmptyFieldPainter(){}

    public static EmptyFieldPainter getPainter() throws IOException {
        if(painter == null){
            painter = new EmptyFieldPainter();
            painter.loadSprite();
        }
        return painter;
    }

    public void loadSprite() throws IOException{
        URL url = getClass().getResource("ground.png");
        if(url == null) throw new IOException("Unable to load ground sprite");
        img = new Image(url.toExternalForm());
    }

    public void paint(GraphicsContext gc, int x, int y, int size){
        if(img == null) return;
        gc.drawImage(img, x, y);
    }
}