package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

public abstract class Entity {
    protected final Board board;
    private int posX;
    private int posY;

    public Entity(Board board){
        this.board = board;
    }

    public final int getPosX(){
        return posX;
    }

    public final int getPosY(){
        return posY;
    }

    public final void setPosition(int x, int y){
        posX = x;
        posY = y;
    }

    // Tries to push an entity
    // Returns true if push was successful
    public boolean tryPush(int target_x, int target_y){
        return false;
    }

    // Handles an event
    void handleEvent(Event event) {

    }
}
