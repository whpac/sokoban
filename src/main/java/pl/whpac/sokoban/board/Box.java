package pl.whpac.sokoban.board;

public class Box extends Entity{

    public Box(Board board, int pos_x, int pos_y) {
        super(board, pos_x, pos_y);
    }

    @Override
    public boolean tryPush(int target_x, int target_y) {
        Entity entity_at_target = board.getEntityAt(target_x, target_y);
        if(entity_at_target != null) return false;

        return board.moveEntity(this, target_x, target_y);
    }
}
