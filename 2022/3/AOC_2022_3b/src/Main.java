import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        int score = 0;
        String[] parts = new String[3];
        int line = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_3a\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                parts[line % 3] = row;
                if (line % 3 == 2) score += checkMatch(parts[0], parts[1], parts[2]);
                line++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static int checkMatch(String part1, String part2, String part3) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char c: part1.toCharArray()) {
            if(part2.contains(String.valueOf(c)) && part3.contains(String.valueOf(c))) return alphabet.indexOf(String.valueOf(c)) + 1;
        }
        return 0;
    }
}