import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long score = 0;
        int counter = -1;
        long min = Long.MAX_VALUE;
        List<Long> seeds = new ArrayList<>();
        ArrayList<ArrayList<Row>> maps = new ArrayList<ArrayList<Row>>(7);

        for(int i = 0;i < 7;i++){
            maps.add(new ArrayList<>());
        }

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_5\\src\\input.txt"));
            String line = scanner.nextLine().substring(6).trim().replaceAll(" +", " ");
            for (String s: line.split(" ")) {
                seeds.add(Long.valueOf(s));
            }
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.matches("([a-z]+)-to-([a-z]+) map:"))counter++;
                else if(!line.isEmpty()) {
                    Row row = new Row();
                    row.destination = Long.parseLong(line.split(" ")[0]);
                    row.source = Long.parseLong(line.split(" ")[1]);
                    row.range = Long.parseLong(line.split(" ")[2]);
                    maps.get(counter).add(row);
                }
            }
            scanner.close();
            for (Long seed : seeds) {
                for(int i = 0; i < maps.size(); i++){
                    seed = calcPart(seed,maps.get(i));
                }
                if(seed<min)min = seed;
            }
            System.out.println(min);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Long calcPart(Long seed, ArrayList<Row> rows) {
        long newseed = -1;
        for (Row r : rows) {
            if(seed >= r.source && seed < r.source + r.range){
                newseed = r.destination + seed - r.source;
            }
        }
        if(newseed<0)return seed;
        else return newseed;
    }
}