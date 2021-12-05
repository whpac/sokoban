package pl.whpac.sokoban.display;

import pl.whpac.sokoban.board.*;

import java.io.IOException;
import java.io.InvalidObjectException;

public class PainterFactory {

    public static Painter getEntityPainter(Entity e) throws IOException {
        if(e instanceof Player) return PlayerPainter.getPainter();
        if(e instanceof Box) return BoxPainter.getPainter();

        throw new InvalidObjectException("Cannot resolve a painter for the given entity");
    }

    public static Painter getFieldPainter(Field f) throws IOException{
        if(f instanceof Wall) return WallPainter.getPainter();
        if(f instanceof EmptyField) return EmptyFieldPainter.getPainter();
        if(f instanceof TargetField) return TargetFieldPainter.getPainter();

        throw new InvalidObjectException("Cannot resolve a painter for the given entity");
    }
}
