import java.util.Comparator;

public class Hand {
    public String cards;
    public int bet;
    public int power;

    static class HandComparator implements Comparator {
        String vals = "AKQJT98765432";
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Hand) || !(o2 instanceof Hand))
                throw new ClassCastException();

            Hand e1 = (Hand) o1;
            Hand e2 = (Hand) o2;

            int result = (e1.power - e2.power);
            if(result == 0){
                for(int i = 0; i < e1.cards.length(); i++){
                    if(vals.indexOf(e1.cards.charAt(i))<vals.indexOf(e2.cards.charAt(i)))return 1;
                    if(vals.indexOf(e1.cards.charAt(i))>vals.indexOf(e2.cards.charAt(i)))return -1;
                }
            }
            return result;
        }
    }
}
