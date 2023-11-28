import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        boolean scannedPlan = false;
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_5\\src\\input.txt"));
            String firstRow = scanner.nextLine();
            LinkedList<Character>[] gamePlan = new LinkedList[(firstRow.length()+1)/4];
            gamePlan = scanPlan(gamePlan,firstRow);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if(!scannedPlan){
                    if(row.contains("1")) {
                        scannedPlan = true;
                    }else{
                        gamePlan = scanPlan(gamePlan,row);
                    }
                }else{
                    if(!row.isBlank()){
                        Move move = readMove(row);
                        gamePlan = makeTheMove(move,gamePlan);
                    }
                }
            }
            scanner.close();
            for (LinkedList<Character> l : gamePlan) {
                System.out.println(l.getFirst());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static LinkedList[] makeTheMove(Move move, LinkedList[] gamePlan) {
        for(int i = 0;i<move.getQuantity();i++){
            Character payload = (Character) gamePlan[move.getSource()].getFirst();
            gamePlan[move.getSource()].removeFirst();
            gamePlan[move.getTarget()].addFirst(payload);
        }
        return gamePlan;
    }

    private static Move readMove(String row) {
        row = row.replaceAll("[^0-9]", " ");
        while (row.contains("  ")) {
            row = row.replace("  ", " ");
        }
        String[] values = row.split(" ");
        Move move = new Move(Integer.valueOf(values[1]),Integer.valueOf(values[2])-1,Integer.valueOf(values[3])-1);
        return move;
    }

    private static LinkedList[] scanPlan(LinkedList[] gamePlan, String row) {
        int x = 0;
        for(int i = 1;i < row.length()+1;i=i+4){
            if(gamePlan[x]==null)gamePlan[x]=new LinkedList<Character>();
            if(row.charAt(i)!=' ')gamePlan[x].add(row.charAt(i));
            x++;
        }
        return  gamePlan;
    }

}