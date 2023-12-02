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

    public static void main(String[] args) throws FileNotFoundException {
        int score = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_2\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().substring(7);
                String[] game = row.split(";");
                score += playable(game);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static int playable(String[] parts) {
        int red_min = 0;
        int blue_min = 0;
        int green_min = 0;

        for (String p: parts) {
            String[] colors = p.split(",");
            for (String c : colors) {
                if (c.contains("blue") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > blue_min) {
                    blue_min = Integer.valueOf(c.replaceAll("[^0-9]", ""));
                } else if (c.contains("red") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > red_min) {
                    red_min = Integer.valueOf(c.replaceAll("[^0-9]", ""));
                } else if (c.contains("green") && Integer.valueOf(c.replaceAll("[^0-9]", "")) > green_min) {
                    green_min = Integer.valueOf(c.replaceAll("[^0-9]", ""));
                }
            }
        }
        return red_min*blue_min*green_min;
    }
}
