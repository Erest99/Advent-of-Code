import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Carrier max = new Carrier();
        int current = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\Desktop\\advent of code\\Advent-of-Code\\2022\\1\\AOC_2022_1\\src\\input.txt"));

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(isNumeric(row)) {
                    current += Integer.parseInt(row);
                }
                else{
                    max = compareWithMax(current,max);
                    current = 0;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(max.getGoodies());
        int mysum = 0;
        for (int i:max.getGoodies()) {
            mysum += i;
        }
        System.out.println(mysum);
    }

    private static Carrier compareWithMax(int current, Carrier max) {
        max.getGoodies().set(0,Math.max(current,max.getGoodies().get(0)));
        Collections.sort(max.getGoodies());
        return max;
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