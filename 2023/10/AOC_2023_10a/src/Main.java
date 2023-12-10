import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static String[] dir_set = {"|F 7","J-7 "," L|J","L F-"};
    private static char[][] field;

    public static void main(String[] args) throws IOException {
        long steps = 0;
        int start_x = 0;
        int start_y = 0;
        Move path1, path2;
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_10\\src\\input.txt"), StandardCharsets.UTF_8);
        field = new char[lines.size()+2][lines.getFirst().length()+2];
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.get(0).length(); j++){
                field[i+1][j+1] = lines.get(i).charAt(j);
                if(lines.get(i).charAt(j) == 'S'){
                    start_x = j+1;
                    start_y = i+1;
                }
            }
        }
        ArrayList<Move> paths = checkStart(start_x,start_y);
        path1 = paths.get(0);
        path2 = paths.get(1);
//        steps++;
        while(!(path1.previous.x() == path2.previous.x()
              && path1.previous.y() == path2.previous.y() && steps > 1)){
            path1 = makeAMove(path1);
            path2 = makeAMove(path2);
            steps++;
        }
        System.out.println(steps);
    }

    private static Move makeAMove(Move path) {
        Move temp = new Move();
        temp.previous = path.current;
        if(path.direction == 0){
            temp.current = new Move.Coord(path.current.x(),path.current.y()-1);
            temp.direction = dir_set[0].indexOf(field[path.current.y()-1][path.current.x()]);
        } else if(path.direction == 1) {
            temp.current = new Move.Coord(path.current.x()+1,path.current.y());
            temp.direction = dir_set[1].indexOf(field[path.current.y()][path.current.x()+1]);
        } else if(path.direction == 2) {
            temp.current = new Move.Coord(path.current.x(),path.current.y()+1);
            temp.direction = dir_set[2].indexOf(field[path.current.y()+1][path.current.x()]);
        } else {
            temp.current = new Move.Coord(path.current.x()-1,path.current.y());
            temp.direction = dir_set[3].indexOf(field[path.current.y()][path.current.x()-1]);
        }
        return temp;
    }

    private static ArrayList<Move> checkStart(int startX, int startY) {
        ArrayList<Move> starts = new ArrayList<>();
        Move temp = new Move();
        if(dir_set[0].contains(String.valueOf(field[startY-1][startX]))){
            temp.previous = new Move.Coord(startX,startY);
            temp.current = new Move.Coord(startX,startY-1);
            temp.direction = dir_set[0].indexOf(field[startY-1][startX]);
            starts.add(temp);
            temp = new Move();
        }
        if(dir_set[1].contains(String.valueOf(field[startY][startX+1]))){
            temp.previous = new Move.Coord(startX,startY);
            temp.current = new Move.Coord(startX+1,startY);
            temp.direction = dir_set[1].indexOf(field[startY][startX+1]);
            starts.add(temp);
            temp = new Move();
        }
        if(dir_set[2].contains(String.valueOf(field[startY+1][startX]))){
            temp.previous = new Move.Coord(startX,startY);
            temp.current = new Move.Coord(startX,startY+1);
            temp.direction = dir_set[2].indexOf(field[startY+1][startX]);
            starts.add(temp);
            temp = new Move();
        }
        if(dir_set[3].contains(String.valueOf(field[startY][startX-1]))){
            temp.previous = new Move.Coord(startX,startY);
            temp.current = new Move.Coord(startX-1,startY);
            temp.direction = dir_set[3].indexOf(field[startY][startX-1]);
            starts.add(temp);
            temp = new Move();
        }
        return starts;
    }
}