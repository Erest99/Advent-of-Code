import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_11\\src\\input.txt"), StandardCharsets.UTF_8);
        ArrayList<ArrayList<Character>> universe = new ArrayList<>();
        for (int y = 0; y < lines.size();y++) {
            ArrayList<Character> chars = new ArrayList<>();
            for (int x = 0; x < lines.get(y).length(); x++){
                char ch = lines.get(y).charAt(x);
                chars.add(ch);
            }
            universe.add(chars);
        }
        universe = expandUniverse(universe);
        System.out.println("expand1");
        universe = rotateClockWise(universe);
        System.out.println("rotated");
        universe = expandUniverse(universe);
        System.out.println("expand2");
        List<Galaxy> galaxies = mapUniverse(universe);
        int result = calculateResult(galaxies);
        System.out.println(result/2);
    }

    private static List<Galaxy> mapUniverse(ArrayList<ArrayList<Character>> universe) {
        List<Galaxy> galaxies = new ArrayList<>();
        for (int y = 0; y < universe.size();y++) {
            for (int x = 0; x < universe.get(y).size(); x++) {
                if (universe.get(y).get(x) == '#') galaxies.add(new Galaxy(x, y));
            }
        }
        return galaxies;
    }

    private static int calculateResult(List<Galaxy> galaxies) {
        int res = 0;
        for(int i = 0; i < galaxies.size();i++){
            for(int j = 0; j < galaxies.size();j++){
                res += Math.abs(galaxies.get(i).getX() - galaxies.get(j).getX()) + Math.abs(galaxies.get(i).getY() - galaxies.get(j).getY());
            }
        }

        return res;
    }

    private static ArrayList<ArrayList<Character>> expandUniverse(ArrayList<ArrayList<Character>> universe){
        ArrayList<ArrayList<Character>> temp = new ArrayList<>();
        for (ArrayList<Character> l: universe) {
            if(!l.contains('#')){
                for(int i = 0; i < 2;i++)temp.add(l);
            }else{
                temp.add(l);
            }
        }
        return temp;
    }

    private static ArrayList<ArrayList<Character>> rotateClockWise(ArrayList<ArrayList<Character>> universe) {
        ArrayList<ArrayList<Character>> ret = new ArrayList<>();
        for (int i = 0; i < universe.get(0).size(); ++i) {
            ret.add(new ArrayList<>());
            for (int j = 0; j < universe.size(); ++j){
                ret.get(i).add(universe.get(universe.size() - j - 1).get(i));
            }
        }
        return ret;
    }
}