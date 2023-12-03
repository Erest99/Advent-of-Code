import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static char[][] game_board;
    private static final String DIGITS = "0123456789";
    private static final int ROW_COUNT = 140;
    private static ArrayList<Character> temp = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        int line = 1;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_3\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(line == 1){
                    game_board = new char[ROW_COUNT+4][row.length()+4];
                    for(int y = 0; y < game_board.length; y++){
                        for(int x = 0; x < game_board[0].length; x++ ){
                            game_board[y][x] = '.';
                        }
                    }
                }
                addToBoard(row,line);
                line++;
            }
            int score = countScore();
            scanner.close();
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int countScore() {
        int score = 0;
        for(int y = 2; y < game_board.length-2; y++){
            for(int x = 2; x < game_board[0].length-2; x++ ){
                if(!(DIGITS+".").contains(String.valueOf(game_board[y][x]))){
                    score += calcSurrounding(y,x);
                }
            }
        }
        return score;
    }

    private static int calcSurrounding(int y, int x) {
        int score = 0;
        for(int j = y-1; j <= y+1; j++){
            for (int i = x-1; i <= x+1; i++){
                if(DIGITS.contains(String.valueOf(game_board[j][i]))){
                    temp.add(game_board[j][i]);
                    game_board[j][i] = '.';
                    checkAdjected(j,i,-1);
                    checkAdjected(j,i,+1);
                    score += retrieveData();
                    temp = new ArrayList<>();
                }
            }
        }
        return score;
    }

    private static int retrieveData() {
        char[] result = new char[temp.size()];
        for(int i = 0;i < temp.size(); i++){
            result[i] = temp.get(i);
        }
        return Integer.parseInt(String.valueOf(result));
    }

    private static void checkAdjected(int j, int i, int direction) {
        if(DIGITS.contains(String.valueOf(game_board[j][i+direction]))){
            if(direction < 0){
                temp.addFirst(game_board[j][i+direction]);
            }else{
                temp.addLast(game_board[j][i+direction]);
            }
            game_board[j][i+direction] = '.';
            checkAdjected(j,i+direction,direction);
        }
    }

    private static void addToBoard(String row, int line) {
        for(int i = 1;i<row.length()+1;i++) {
            game_board[line][i] = row.charAt(i-1);
        }
    }

}
