package pl.whpac.sokoban.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileBoardBuilder implements BoardBuilder {
    private final String[] boardData;

    public FileBoardBuilder(InputStream input_stream) throws IOException{
        boardData = readFromInputStream(input_stream);
    }

    @Override
    public Board createBoard() {
        int width = 0;
        int height = boardData.length;
        for(String s : boardData){
            width = Math.max(width, s.length());
        }

        Board b = new Board(width, height);
        for(int y = 0; y < boardData.length; y++){
            String line = boardData[y];
            for(int x = 0; x < line.length(); x++){
                Entity e = null;
                Field f = null;
                char c = line.charAt(x);

                switch (c) {
                    case '#' -> f = new Wall();
                    case 'o' -> e = new Box(b);
                    case 'x' -> f = new TargetField();
                    case '*' -> {
                        f = new TargetField();
                        e = new Box(b);
                    }
                    case '@' -> e = new Player(b);
                }

                if(f != null) b.setFieldAt(f, x, y);
                if(e != null) b.setEntityAt(e, x, y);
            }
        }

        return b;
    }

    private static String[] readFromInputStream(InputStream input_stream) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input_stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result.toArray(new String[0]);
    }
}
