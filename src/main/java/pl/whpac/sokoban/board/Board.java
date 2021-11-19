package pl.whpac.sokoban.board;

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

        entities[0] = new Player();
        entities[9] = new Player();
    }

    // Converts the (x, y) coordinates to offset used in arrays
    private int convertXYtoOffset(int x, int y) {
        return y * height + x;
    }

    // Returns the field at the specified coordinates
    public Field getFieldAt(int x, int y) {
        int offset = convertXYtoOffset(x, y);
        return fields[offset];
    }

    // Returns the entity at the specified coordinates
    public Entity getEntityAt(int x, int y) {
        int offset = convertXYtoOffset(x, y);
        return entities[offset];
    }
}