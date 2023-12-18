import java.awt.*;
import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;
import java.util.stream.Collectors;


public class Main {
    private static char[][] inputArray;
    private record coord (int x,int y){}
    private static List<coord> points;
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("input.txt"), StandardCharsets.UTF_8);
        points = new ArrayList<>();
        int max_x = 0;
        int max_y = 0;
        int min_x = 0;
        int min_y = 0;
        int x = 0;
        int y = 0;
        // Replace this with your input data (2D char array)
        for (String line : lines) {
            String[] parts = line.split(" ");
            if(parts[0].contains("R"))x += Integer.parseInt(parts[1]);
            if(parts[0].contains("L"))x -= Integer.parseInt(parts[1]);
            if(parts[0].contains("U"))y -= Integer.parseInt(parts[1]);
            if(parts[0].contains("D"))y += Integer.parseInt(parts[1]);
            if(x>max_x)max_x = x;
            if(x<min_x)min_x = x;
            if(y>max_y)max_y = y;
            if(y<min_y)min_y = y;
        }

        int rows = Math.abs(max_y)+Math.abs(min_y)+1;
        int cols =Math.abs(max_x) + Math.abs(min_x)+1;
        inputArray = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                inputArray[i][j] = '.';
            }
        }
        x = Math.abs(min_x);
        y = Math.abs(min_y);
        inputArray[y][x] = 'W';
        points.add(new coord(x,y));

        for (String line : lines) {
            String[] parts = line.split(" ");
            if(parts[0].contains("R")) x = move(x,y,Integer.parseInt(parts[1]),0);
            if(parts[0].contains("L")) x = move(x,y,Integer.parseInt(parts[1]),2);
            if(parts[0].contains("U")) y = move(x,y,Integer.parseInt(parts[1]),3);
            if(parts[0].contains("D")) y = move(x,y,Integer.parseInt(parts[1]),1);
        }
        List<coord> shallowCopy = points.subList(0, points.size());
        Collections.reverse(shallowCopy);
        int area = calcArea(shallowCopy);
        // Perform watershed segmentation and print the result
        // Memory issue with watershed
        // int[] regionSizes = performWatershedSegmentation(inputArray);
        //printResult(inputArray, new int[1]);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(inputArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(area);
    }

    private static int calcArea(List<coord> coords) {
//        int area = 0;
//        int local = 0;
//        boolean lock = false;
//        boolean in = false;
//        boolean cooldown = false;
//        for(int y = 0; y < inputArray.length; y++) {
//            for (int x = 1; x < inputArray[0].length; x++) {
//                if (inputArray[y][x] == 'W'){
//                    area++;
//                    if(!in && !cooldown){
//                        in = true;
//                        lock = true;
//                    }
//                    if(!lock){
//                        area += local;
//                        local = 0;
//                        in = false;
//                        cooldown = true;
//                    }
//                }
//                else {
//                    lock = false;
//                    cooldown = false;
//                    if(in)local++;
//                }
//
//            }
//            System.out.println(area);
//            local = 0;
//            lock = false;
//            in = false;
//            cooldown = false;
//        }
//            return area;
        List<Integer> col1 = new ArrayList<>();
        List<Integer> col2 = new ArrayList<>();
        for(int i = 1;i<coords.size();i++){
            col1.add(coords.get(i-1).x * coords.get(i).y);
            col2.add(coords.get(i-1).y * coords.get(i).x);
        }
        int res1 = col1.stream().mapToInt(Integer::intValue).sum();
        int res2 = col2.stream().mapToInt(Integer::intValue).sum();

        return Math.abs(res1 - res2)/2;
    }

    private static int move(int x, int y, int step, int dir){
        int result = 0;
        try{
            if(dir == 0){
                for(int i = 1; i <= step; i++){
                    inputArray[y][x+i] = 'W';
                }
                points.add(new coord(x+step,y));
                return x+step;
            }
            if(dir == 1){
                for(int i = 1; i <= step; i++){
                    inputArray[y+i][x] = 'W';
                }
                points.add(new coord(x,y+step));
                return y+step;
            }
            if(dir == 2){
                for(int i = 1; i <= step; i++){
                    inputArray[y][x-i] = 'W';
                }
                points.add(new coord(x-step,y));
                return x-step;
            }
            if(dir == 3){
                for(int i = 1; i <= step; i++){
                    inputArray[y-i][x] = 'W';
                }
                points.add(new coord(x,y-step));
                return y-step;
            }
            return 0;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("" + x + " " + y + " " + step + " " + " " +dir);
            return 0;
        }
    }

    private static int[] performWatershedSegmentation(char[][] inputArray) {
        int rows = inputArray.length;
        int cols = inputArray[0].length;

        // Create a marker array to store segmented regions
        char[][] markers = new char[rows][cols];

        // Initialize markers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                markers[i][j] = ' ';
            }
        }

        char currentMarker = 'A';
        int[] regionSizes = new int[26]; // Assuming a maximum of 26 regions (A-Z)

        // Iterate through the input array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the current cell is unmarked and represents an object
                if (inputArray[i][j] == 'B' && markers[i][j] == ' ') {
                    regionSizes[currentMarker - 'A'] = floodFill(inputArray, markers, i, j, currentMarker);
                    currentMarker++;
                }
            }
        }

        return regionSizes;
    }

        private static int floodFill(char[][] inputArray, char[][] markers, int x, int y, char marker) {
            // Check boundaries and conditions for flood fill
            if (x < 0 || y < 0 || x >= inputArray.length || y >= inputArray[0].length
                    || inputArray[x][y] != 'B' || markers[x][y] != ' ' || markers[x][y] == marker) {
                return 0;
            }

            // Mark the current cell with the given marker
            markers[x][y] = marker;

            // Recursively perform flood fill in all four directions
            return 1 + floodFill(inputArray, markers, x + 1, y, marker)
                    + floodFill(inputArray, markers, x - 1, y, marker)
                    + floodFill(inputArray, markers, x, y + 1, marker)
                    + floodFill(inputArray, markers, x, y - 1, marker);
        }


    private static void printResult(char[][] array, int[] regionSizes) {
        int rows = array.length;
        int cols = array[0].length;



        System.out.println("\nRegion Sizes:");
        char currentMarker = 'A';

        for (int i = 0; i < regionSizes.length; i++) {
            if (regionSizes[i] > 0) {
                System.out.println(currentMarker + ": " + regionSizes[i]);
            }
            currentMarker++;
        }
    }
}