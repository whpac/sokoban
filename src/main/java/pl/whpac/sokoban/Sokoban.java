package pl.whpac.sokoban;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.whpac.sokoban.board.Board;
import pl.whpac.sokoban.board.BoardBuilder;
import pl.whpac.sokoban.board.FileBoardBuilder;
import pl.whpac.sokoban.display.BoardDisplay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Sokoban extends Application {
    private static String levelPath;

    @Override
    public void start(Stage stage) {
        Board board;
        try{
            InputStream input_stream = getLevelStream();
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
        Scanner s = new Scanner(System.in);
        System.out.print("Type the level name: ");
        levelPath = s.nextLine();
        launch();
    }

    private InputStream getLevelStream() throws FileNotFoundException {
        if(levelPath.startsWith("@")){
            return getClass().getResourceAsStream("board/" + levelPath.substring(1) + ".txt");
        }else{
            return new FileInputStream(levelPath);
        }
    }
}