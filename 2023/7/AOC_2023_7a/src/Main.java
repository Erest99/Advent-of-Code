import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int score = 0;
        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();
        List<String> lines =  Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_7\\src\\input.txt"), StandardCharsets.UTF_8);

        for (String l : lines) {
            Hand hand = new Hand();
            hand.cards = l.split(" ")[0];
            hand.bet = Integer.valueOf(l.split(" ")[1]);
            hand.power = getPower(hand.cards);
            hands.add(hand);
        }
        hands.sort(new Hand.HandComparator());
        for(int i = 0; i < hands.size(); i++) {
            score += hands.get(i).bet * (i+1);
        }
        System.out.println(score);
    }

    private static int getPower(String cards) {
        HashMap<Character, Integer> card_map = new HashMap<>();
        for(int i = 0; i < cards.length(); i++){
            if(card_map.containsKey(cards.charAt(i)))card_map.put(cards.charAt(i),card_map.get(cards.charAt(i))+1);
            else card_map.put(cards.charAt(i),1);
        }
        if(card_map.containsValue(5))return 6;
        else if (card_map.containsValue(4))return 5;
        else if (card_map.containsValue(3) && card_map.size() <= 2)return 4;
        else if (card_map.containsValue(3) && card_map.size() > 2) return 3;
        else if (card_map.containsValue(2) && card_map.size() <= 3) return 2;
        else if (card_map.containsValue(2) && card_map.size() > 3) return 1;
        else return 0;

    }

}