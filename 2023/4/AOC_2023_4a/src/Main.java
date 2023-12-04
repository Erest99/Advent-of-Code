import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long score = 0;
        List<String> cards = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_4\\src\\input.txt"));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().substring(9).trim().replaceAll(" +", " ");
                cards.add(row);
            }
            scanner.close();
            for (int i = 0;i<cards.size();i++) {
                score += calcScore(cards.get(i));
//                int n = calcScoringCards(cards.get(i));
//                for(int j = i; j <= i+n;j++){
//
//                    System.out.println(score);
//                }
            }
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static long calcScore(String row) {
        int local = 0;
        String[] parts = row.split("\\|", 2);
        List<String> winning = Arrays.asList(parts[0].trim().split(" "));
        List<String> playing = Arrays.asList(parts[1].trim().split(" "));
        for (String card: playing) {
            if(winning.contains(card)){
                if(local == 0)local++;
                else local *= 2;
            }
        }
        return local;
    }

    private static int calcScoringCards(String row) {
        int local = 0;
        String[] parts = row.split("\\|", 2);
        List<String> winning = Arrays.asList(parts[0].trim().split(" "));
        List<String> playing = Arrays.asList(parts[1].trim().split(" "));
        for (String card: playing) {
            if(winning.contains(card)){
                local++;
            }
        }
        return local;
    }
}