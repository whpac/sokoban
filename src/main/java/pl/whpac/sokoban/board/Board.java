package pl.whpac.sokoban.board;

import pl.whpac.sokoban.input.Event;

import java.util.ArrayList;

public class Board {
    public final int width;
    public final int height;

    // These hold the fields (i.e. wall, empty, X etc.)
    // and entities (i.e. player and boxes)
    private final Field[] fields;
    private final Entity[] entities;

    public Board(int width, int height){
        this.width = width;
        this.height = height;

        // Initialize arrays for board contents
        fields = new Field[width * height];
        entities = new Entity[width * height];

        // Initialize board with empty fields
        for(int i = 0; i < width * height; i++) fields[i] = new EmptyField();

        entities[0] = new Player(this, 0, 0);
        entities[1] = new Box(this, 1, 0);
        fields[2] = new TargetField();
        fields[6] = new Wall();
    }

    // Converts the (x, y) coordinates to offset used in arrays
    private int convertXYtoOffset(int x, int y) {
        return y * height + x;
    }

    // Checks whether the specified coordinates are outside board
    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    // Returns the field at the specified coordinates
    public Field getFieldAt(int x, int y) {
        if(outOfBounds(x, y)) return null;
        int offset = convertXYtoOffset(x, y);
        return fields[offset];
    }

    // Returns the entity at the specified coordinates
    public Entity getEntityAt(int x, int y) {
        if(outOfBounds(x, y)) return null;
        int offset = convertXYtoOffset(x, y);
        return entities[offset];
    }

    // Sets an entity at the given position
    public void setEntityAt(Entity e, int x, int y){
        if(outOfBounds(x, y)) return;
        int offset = convertXYtoOffset(x, y);
        entities[offset] = e;
    }

    // Moves the specified entity to the target position
    // Returns true on success
    public boolean moveEntity(Entity e, int target_x, int target_y){
        if(outOfBounds(target_x, target_y)) return false;

        Field target_field = getFieldAt(target_x, target_y);
        if(!target_field.canHaveEntity()) return false;

        int src_x = e.getPosX();
        int src_y = e.getPosY();

        Entity entity_at_target = getEntityAt(target_x, target_y);
        if(entity_at_target != null){
            int dx = target_x - src_x;
            int dy = target_y - src_y;
            if(!entity_at_target.tryPush(target_x + dx, target_y + dy)) return false;
        }

        if(getEntityAt(src_x, src_y) != e) return false;

        setEntityAt(null, src_x, src_y);
        setEntityAt(e, target_x, target_y);
        e.setPosition(target_x, target_y);
        return true;
    }

    public int getRemainingBoxes(){
        int remaining_boxes = 0;

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Field f = getFieldAt(x, y);
                if(f instanceof TargetField) continue;
                Entity e = getEntityAt(x, y);
                if(e == null) continue;
                if(e instanceof Box) remaining_boxes++;
            }
        }

        return remaining_boxes;
    }

    // Dispatches event to all the fields and entities
    public void dispatchEvent(Event event){
        ArrayList<Field> field_list = new ArrayList<>();
        ArrayList<Entity> entity_list = new ArrayList<>();

        for(int i = 0; i < width * height; i++){
            field_list.add(fields[i]);
            if(entities[i] == null) continue;
            entity_list.add(entities[i]);
        }

        for(Field f : field_list){
            f.handleEvent(event);
        }

        for(Entity e : entity_list){
            e.handleEvent(event);
        }
    }
}
