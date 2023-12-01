import java.awt.*;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int score = 0;
        ArrayList<String> decimals = new ArrayList<>();
        decimals.add("one");
        decimals.add("two");
        decimals.add("three");
        decimals.add("four");
        decimals.add("five");
        decimals.add("six");
        decimals.add("seven");
        decimals.add("eight");
        decimals.add("nine");
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_1\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                for (String dec : decimals) {
                    row = row.replaceAll(dec, dec + (decimals.indexOf(dec) + 1) + dec); //bcs of overlapping
                }
                String num = row.replaceAll("[^0-9]", "");
                String calibration = "" + num.charAt(0) + num.charAt(num.length() - 1);
                score += Integer.parseInt(calibration);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }
}
