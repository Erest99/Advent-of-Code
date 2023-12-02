import java.awt.*;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final int blue_limit = 14;
    private static final int red_limit = 12;
    private static final int green_limit = 13;

    public static void main(String[] args) throws FileNotFoundException {
        int id = 1;
        int score = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_2\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().substring(7);
                String[] game = row.split(";");
                if(playable(game))score+=id;
                id++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static boolean playable(String[] parts) {
        for (String p: parts) {
            String[] colors = p.split(",");
            for (String c : colors) {
                if (c.contains("blue") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > blue_limit) {
                    return false;
                } else if (c.contains("red") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > red_limit) {
                    return false;
                } else if (c.contains("green") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > green_limit) {
                    return false;
                }
            }
        }
        return true;
    }
}
