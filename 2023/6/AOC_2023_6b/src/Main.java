import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class Main {

    public static void main(String[] args) throws IOException {
        int score = 1;
        List<String> lines =  Files.readAllLines(Paths.get("C:\\Users\\Horky\\IdeaProjects\\AOC_2023_6\\src\\input.txt"), StandardCharsets.UTF_8);

        String time = lines.get(0).substring(5).trim().replaceAll(" ", "");
        String distance = lines.get(1).substring(9).trim().replaceAll(" ", "");
        score *= calcRaces(time,distance);
        System.out.println(score);
    }

    private static void testCalc() {
        System.out.println("test: " + String.valueOf(calcRaces("5","8")));
    }

    private static int calcRaces(String time, String distance) {
        double hold = ceil(Float.valueOf(time)/2);
        double run = Float.valueOf(time)-hold;
        double limit = Float.valueOf(distance);
//        System.out.println("hold: "+String.valueOf(hold));
//        System.out.println("run: " + String.valueOf(run));
        int score = 0;
        while(true){
            if(hold * run > limit){
                score++;
                hold = hold - 1f;
                run = run + 1f;
//                System.out.println("hold: "+String.valueOf(hold));
//                System.out.println("run: " + String.valueOf(run));
            }else break;
        }
        if(score == 0) return 0;
        if(Integer.valueOf(time)%2 == 0) return 2*score-1;
        else return 2*score;
    }
}