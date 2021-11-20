package pl.whpac.sokoban.display;

import pl.whpac.sokoban.board.*;

public class PainterFactory {

    public static Painter getEntityPainter(Entity e){
        if(e instanceof Player) return new PlayerPainter();
        if(e instanceof Box) return new BoxPainter();
        return null;
    }

    public static Painter getFieldPainter(Field f){
        if(f instanceof Wall) return new WallPainter();
        if(f instanceof EmptyField) return new EmptyFieldPainter();
        if(f instanceof TargetField) return new TargetFieldPainter();
        return null;
    }
}
