package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

public interface Field {

    // Checks whether the field can have an entity on it
    boolean canHaveEntity();

    // Handles an event
    void handleEvent(Event event);
}
