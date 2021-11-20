package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

public interface Entity {

    // Handles an event
    void handleEvent(Event event);
}
