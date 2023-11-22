import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int max = 0;
        int current = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\Desktop\\advent of code\\Advent-of-Code\\2022\\1\\input.txt"));

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(isNumeric(row)) {
                    current += Integer.parseInt(row);
                }
                else{
                    max = Math.max(current, max);
                    current = 0;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(max);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}