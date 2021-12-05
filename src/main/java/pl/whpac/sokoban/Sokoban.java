package pl.whpac.sokoban;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.board.BoardBuilder;
import pl.whpac.sokoban.board.MockBoardBuilder;
import pl.whpac.sokoban.display.BoardDisplay;

public class Sokoban extends Application {
    @Override
    public void start(Stage stage) {
        BoardBuilder board_builder = new MockBoardBuilder();
        Board board = board_builder.createBoard();

        BoardDisplay display = new BoardDisplay(stage);
        display.setBoard(board);
        display.start();
    }

    public static void main(String[] args) {
        launch();
    }
}