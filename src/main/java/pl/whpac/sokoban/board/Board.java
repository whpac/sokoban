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

        this.fields = new Field[width * height];
        this.entities = new Entity[width * height];
    }
}
