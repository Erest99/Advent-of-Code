import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

        private static List<List<Long>> sequence = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            long score = 0;
            long back_score = 0;
            int counter = 0;
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_9\\src\\input.txt"), StandardCharsets.UTF_8);
            List<Long> temp = new ArrayList<>();
            for (String line : lines) {
                sequence.add(Arrays.stream(line.split("\\s+"))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
                while(!areAllZeros(sequence.getLast())){ //checking if the difference of last list is only zeros
                    temp.add(0L);
                    for(int i = 0;i < sequence.getLast().size()-1; i++){
                        temp.add(sequence.getLast().get(i)*(-1));
                    }
                    temp = sumLists(temp);
                    sequence.add(temp);
                    temp = new ArrayList<>();
                }
                score += calcNewNum();
                back_score += calcNewNumBack();
                counter++;
                System.out.println(counter);
                sequence = new ArrayList<>();
            }
            System.out.println(score);
            System.out.println(back_score);
        }

    private static long calcNewNumBack() {
        long a;
        long b;
        for(int i = sequence.size()-2; i >= 0; i-- ){
            a = sequence.get(i).getFirst();
            b = sequence.get(i+1).getFirst();
            sequence.get(i).addFirst(a-b);
        }
        return sequence.getFirst().getFirst();
    }

    private static long calcNewNum() {
            long a;
            long b;
            for(int i = sequence.size()-2; i >= 0; i-- ){
                a = sequence.get(i).getLast();
                b = sequence.get(i+1).getLast();
                sequence.get(i).add(a+b);
            }
        return sequence.getFirst().getLast();
    }

    private static List<Long> sumLists(List<Long> temp) {
        List<Long> finalTemp = temp;
        temp = IntStream.range(0, sequence.getLast().size())
                .mapToObj(i -> sequence.getLast().get(i) + finalTemp.get(i))
                .collect(Collectors.toList());
        temp.removeFirst();
        return temp;
    }

    public static boolean areAllZeros(List<Long> list) {
        return list.stream().allMatch(element -> element == 0);
    }
}