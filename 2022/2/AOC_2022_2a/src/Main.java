import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        int score = 0;
        HashMap<String, Integer> scoreTable = new HashMap<String, Integer>();
        scoreTable.put("X",1);
        scoreTable.put("Y",2);
        scoreTable.put("Z",3);
        int symbolscore;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\Desktop\\advent of code\\Advent-of-Code\\2022\\2\\AOC_2022_2a\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] parts = row.split(" ");
                symbolscore = scoreTable.get(parts[1]);
                score += symbolscore;
                score += checkWinner(parts[0],parts[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static int checkWinner(String op, String me) {
        if (me.contains("X")) {
            if (op.contains("C")) return 6;
            if (op.contains("A")) return 3;
            if (op.contains("B")) return 0;
        } else if (me.contains("Y")) {
            if (op.contains("A")) return 6;
            if (op.contains("B")) return 3;
            if (op.contains("C")) return 0;
        } else if (me.contains("Z")) {
            if (op.contains("B")) return 6;
            if (op.contains("C")) return 3;
            if (op.contains("A")) return 0;
        }
        return 0;
    }
}