import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Row> rows = new ArrayList<>();
        List<String> lines =  Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_8\\src\\input.txt"), StandardCharsets.UTF_8);
        String direction = lines.get(0);
        for(int i = 2; i<lines.size();i++){
            String line = lines.get(i);
            line = line.replaceAll("[^a-zA-Z0-9 ]","");
            line = line.replaceAll(" +"," ");
            Row temp = new Row();
            temp.id = line.split(" ")[0];
            temp.left = line.split(" ")[1];
            temp.right = line.split(" ")[2];
            rows.add(temp);

        }
        Row current = findById(rows,"AAA");
        int counter = 0;
        int steps = 0;
        while(!Objects.equals(current.id, "ZZZ")){
            if(direction.charAt(counter) == 'L')current = findById(rows,current.left);
            else current = findById(rows,current.right);
            counter++;
            steps++;
            if(counter > direction.length()-1)counter = 0;

        }
        System.out.println(steps);
    }

    public static Row findById(Collection<Row> rows, String id) {
        return rows.stream().filter(row -> id.equals(row.id)).findFirst().orElse(null);
    }
}