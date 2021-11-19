package pl.whpac.sokoban;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.display.BoardDisplay;

public class Sokoban extends Application {
    @Override
    public void start(Stage stage) {
        Board board = new Board(4, 4);

        BoardDisplay display = new BoardDisplay(stage);
        display.display(board);
    }

    public static void main(String[] args) {
        launch();
    }
}