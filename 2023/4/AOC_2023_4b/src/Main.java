import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> cards = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        long score = 0;

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_4\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().substring(9).trim().replaceAll(" +", " ");
                cards.add(row);
            }
            scanner.close();
            score += cards.size();
            for (int i = 0;i<cards.size();i++) {
                score += calcScoringCards(i);
            }
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static int calcScoringCards(int index) {
        int local = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        String[] parts = cards.get(index).split("\\|", 2);
        List<String> winning = Arrays.asList(parts[0].trim().split(" "));
        List<String> playing = Arrays.asList(parts[1].trim().split(" "));
        for (String card: playing) {
            if(winning.contains(card)){
                nums.add(Integer.valueOf(card));
            }
        }
        local = nums.size();
        for(int i = index+1; i <= index + nums.size(); i++){
            local += calcScoringCards(i);
        }
        return local;
    }
}