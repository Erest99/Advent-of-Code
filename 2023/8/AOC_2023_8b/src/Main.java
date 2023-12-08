import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String,Row> rows = new HashMap<>();
        List<String> lines =  Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_8\\src\\input.txt"), StandardCharsets.UTF_8);
        String direction = lines.get(0);
        for(int i = 2; i<lines.size();i++){
            String line = lines.get(i);
            line = line.replaceAll("[^a-zA-Z0-9 ]","");
            line = line.replaceAll(" +"," ");
            Row temp = new Row();
            String[] parts = line.split(" ");
            temp.id = parts[0];
            temp.left = parts[1];
            temp.right = parts[2];
            rows.put(parts[0],temp);

        }
        List current = getStarting(rows);
        int counter = 0;
        int steps = 0;
        while(testEnd(current)){
            int swapIndex = (direction.charAt(counter) == 'L') ? 0 : 1;
            current = swapTo(swapIndex, current, rows);
            counter++;
            steps++;
            if(steps%100000000==0) System.out.println(steps);
            if(counter > direction.length()-1)counter = 0;

        }
        System.out.println(steps);
    }

    private static List<Row> swapTo(int i, List<Row> current, HashMap<String,Row> rows) {
        List<Row> temp = new ArrayList<>();
        for (Row r : current) {
            if(i == 0) r = rows.get(r.left);
            else r = rows.get(r.right);
            temp.add(r);
        }
        return temp;
    }

    private static boolean testEnd(List<Row> current) {
        for (Row row : current) {
            if(row.id.charAt(2)!='Z')return true;
        }
        return false;
    }

    private static List getStarting(HashMap<String,Row> rows){
        return rows.entrySet()
                .stream()
                .filter(entry -> entry.getKey().length() >= 3 && entry.getKey().charAt(2) == 'A')
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}