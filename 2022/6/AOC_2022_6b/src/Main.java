import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        String dupl = "";
        int index = 14;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_6\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                text.append(row);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 13;i< text.length();i++){
            String key = text.substring(i-13,i+1);
            if(!checkDuplicates(key))break;
            else index++;
        }
        System.out.println(index);
    }

    private static boolean checkDuplicates(String key) {
        String help = "";
        for(int i = 0; i < key.length();i++){
            char c = key.charAt(i);
            if(help.contains(String.valueOf(c)))return true;
            help += String.valueOf(c);
        }
        return false;
    }


}