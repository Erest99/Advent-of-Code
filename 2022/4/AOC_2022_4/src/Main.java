import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        int score = 0;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_4a\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                //if(fullyContains(row))score++;
                if(partlyContains(row) || fullyContains(row))score++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static boolean fullyContains(String row) {
        String[] parts = row.split(",", 2);
        mySection section1 = new mySection(Integer.parseInt(parts[0].split("-",2)[0]),Integer.parseInt(parts[0].split("-",2)[1]));
        mySection section2 = new mySection(Integer.parseInt(parts[1].split("-",2)[0]),Integer.parseInt(parts[1].split("-",2)[1]));
        if(
                (section1.getLowerNum() <= section2.getLowerNum() && section1.getHigherNum() >= section2.getHigherNum())
                ||
                (section2.getLowerNum() <= section1.getLowerNum() && section2.getHigherNum() >= section1.getHigherNum())
        ){
            return true;
        }else return false;
    }


    private static boolean partlyContains(String row) {
        String[] parts = row.split(",", 2);
        mySection section1 = new mySection(Integer.parseInt(parts[0].split("-",2)[0]),Integer.parseInt(parts[0].split("-",2)[1]));
        mySection section2 = new mySection(Integer.parseInt(parts[1].split("-",2)[0]),Integer.parseInt(parts[1].split("-",2)[1]));
        if(
                (section1.getLowerNum() <= section2.getLowerNum() && section1.getHigherNum() >= section2.getLowerNum())
                ||
                (section2.getLowerNum() <= section1.getLowerNum() && section2.getHigherNum() >= section1.getLowerNum())
        ){
            return true;
        }else return false;
    }
}