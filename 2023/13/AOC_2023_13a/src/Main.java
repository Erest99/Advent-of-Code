import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> all_lines = Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_13\\src\\input"), StandardCharsets.UTF_8);
        List<String> lines = new ArrayList<>();
        int res = 0;
        for(int i = 0; i < all_lines.size();i++){
            String line = all_lines.get(i);
            if(!line.isEmpty()){
                lines.add(line);
            }else{
                res += calculateResult(lines);
                lines = new ArrayList<>();
            }
        }
        res += calculateResult(lines);
        System.out.println(res);
    }

    private static int calculateResult(List<String> lines) {
        int temp = 0;
        temp += calcHorizontal(lines,100);
        temp += calcHorizontal(rotateLines(lines),1);
        return temp;
    }

    private static List<String> rotateLines(List<String> lines) {
        List<String> rotated = new ArrayList<>();
        for(int i = 0;i<lines.get(0).length();i++){
            StringBuilder t = new StringBuilder();
            for (String l: lines.reversed()) {
                t.append(l.charAt(i));
            }
            rotated.add(t.toString());
        }
        return rotated;
    }

    private static int calcHorizontal(List<String> lines,int coef) {
        int temp = 0;
        for(int i = 1;i<lines.size();i++){
            if(lines.get(i).equals(lines.get(i-1))){
                int d = 1;
                boolean r = true;
                while(true){
                    if(i+d >= lines.size() || i-1-d < 0)break;
                    r = lines.get(i+d).equals(lines.get(i-1-d));
                    if(!r)break;
                    d++;
                }
                if(r)temp += i*coef;
            }
        }
        return temp;
    }

}