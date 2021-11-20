package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

public class EmptyField implements Field {

    public boolean canHaveEntity(){
        return true;
    }

    public void handleEvent(Event event) {

    }
}
