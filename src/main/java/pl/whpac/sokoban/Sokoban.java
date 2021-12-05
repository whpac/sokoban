package pl.whpac.sokoban;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.board.BoardBuilder;
import pl.whpac.sokoban.board.FileBoardBuilder;
import pl.whpac.sokoban.display.BoardDisplay;

import java.io.IOException;
import java.io.InputStream;

public class Sokoban extends Application {
    @Override
    public void start(Stage stage) {
        Board board;
        try{
            InputStream input_stream = getClass().getResourceAsStream("board/lvl1.txt");
            BoardBuilder board_builder = new FileBoardBuilder(input_stream);
            board = board_builder.createBoard();
        }catch(IOException e){
            System.out.println("Cannot load board.");
            return;
        }

        BoardDisplay display = new BoardDisplay(stage);
        display.setBoard(board);
        display.start();
    }

    public static void main(String[] args) {
        launch();
    }
}