import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        int score = 0;
        String[] parts;
        int mid;
        int line = 1;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_3a\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(row.length()%2==0){
                    mid = row.length() / 2; //get the middle of the String
                    parts = new String[]{row.substring(0, mid), row.substring(mid)};
                    System.out.println("line: "+line);
                    line++;
                    score += checkMatch(parts[0],parts[1]);
                }else{

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static int checkMatch(String part1, String part2) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int localscore = 0;
        for (int i = 0; i < part1.length();i++) {
            if(part2.contains(String.valueOf(part1.charAt(i)))){
                if(alphabet.contains(String.valueOf(part1.charAt(i)))){
                    localscore += alphabet.indexOf(String.valueOf(part1.charAt(i)))+1;
                    System.out.println("Matched "+ part1.charAt(i));
                    alphabet = alphabet.replace(String.valueOf(part1.charAt(i)),".");
                }
            }
        }
        return localscore;
    }
}