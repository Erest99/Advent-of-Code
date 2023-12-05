import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Long> seeds = new ArrayList<>();
    private static ArrayList<ArrayList<Row>> maps = new ArrayList<>(7);

    public static void main(String[] args) throws FileNotFoundException {
        int counter = -1;
        long min = Long.MAX_VALUE;

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
            for(int i = 0; i < seeds.size(); i += 2){
                System.out.println("Calculation: "+ ((i/2)+1) + "/10");
                long seed = seeds.get(i);
                long range = seeds.get(i+1);
                for(long l = seed; l < seed + range; l++){
                    long val = l;
                    for(int j = 0; j < maps.size(); j++){
                        val = calcPart(val,j);
                    }
                    if(val<min)min = val;
                }
            }
            System.out.println(min);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Long calcPart(Long seed, int i) {
        long newseed = -1;
        for (Row r : maps.get(i)) {
            if(seed >= r.source && seed < r.source + r.range){
                newseed = r.destination + seed - r.source;
            }
        }
        if(newseed<0)return seed;
        else return newseed;
    }
}