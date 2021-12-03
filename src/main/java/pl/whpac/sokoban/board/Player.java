package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;
import pl.whpac.sokoban.input.KeyboardEvent;

public class Player extends Entity {

    public Player(Board board, int pos_x, int pos_y) {
        super(board, pos_x, pos_y);
    }

    public void handleEvent(Event event) {
        if (event instanceof KeyboardEvent){
            processKeyboardEvent((KeyboardEvent)event);
        }
    }

    protected void processKeyboardEvent(KeyboardEvent event){
        if(event.getType() != KeyboardEvent.KeyboardEventType.keyDown) return;

        switch (event.getKeycode()) {
            case "UP" -> board.moveEntity(this, getPosX(), getPosY() - 1);
            case "DOWN" -> board.moveEntity(this, getPosX(), getPosY() + 1);
            case "LEFT" -> board.moveEntity(this, getPosX() - 1, getPosY());
            case "RIGHT" -> board.moveEntity(this, getPosX() + 1, getPosY());
        }
    }
}
