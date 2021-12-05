package pl.whpac.sokoban.board;

public class MockBoardBuilder implements BoardBuilder {

    @Override
    public Board createBoard() {
        Board b = new Board(4, 4);
        b.setEntityAt(new Player(b), 0, 0);
        b.setEntityAt(new Box(b), 1, 0);
        b.setFieldAt(new TargetField(), 2, 0);
        b.setFieldAt(new Wall(), 2, 1);

        return b;
    }
}
