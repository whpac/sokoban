package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

public class Wall implements Field {

    public boolean canHaveEntity(){
        return false;
    }

    public void handleEvent(Event event) {

    }
}
