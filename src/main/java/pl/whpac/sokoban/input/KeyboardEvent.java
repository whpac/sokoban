package pl.whpac.sokoban.input;

public class KeyboardEvent implements Event{
    protected String keycode;
    protected KeyboardEventType type;

    public KeyboardEvent(String keycode, KeyboardEventType type){
        this.keycode = keycode;
        this.type = type;
    }

    public String getKeycode(){
        return this.keycode;
    }

    public KeyboardEventType getType(){
        return this.type;
    }

    public enum KeyboardEventType {
        keyDown,
        keyUp
    }
}
