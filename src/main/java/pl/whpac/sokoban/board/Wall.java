package pl.whpac.sokoban.board;

public class Wall implements Field {

    public boolean canHaveEntity(){
        return false;
    }
}
