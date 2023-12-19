import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Workflow> work_system = new HashMap<String, Workflow>();

    public static void main(String[] args) {

        boolean button = false;
        int score = 0;

        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.isEmpty()){
                    button = true;
                    line = scanner.nextLine();
                }
                if(!button){
                    processLine(line);
                }else{
                    score += processPart(line);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(score);
    }

    private static int processPart(String line) {
        //TODO get data from line
        String[] parts = line.split(",");
        int x = Integer.parseInt(parts[0].replaceAll("[^0-9]",""));
        int m = Integer.parseInt(parts[1].replaceAll("[^0-9]",""));
        int a = Integer.parseInt(parts[2].replaceAll("[^0-9]",""));
        int s = Integer.parseInt(parts[3].replaceAll("[^0-9]",""));
        String id = "in";
        int score = 0;
        //TODO while loop over conds untill you get A or R
        while(!(id.equals("A") || id.equals("R"))){
            Workflow w = work_system.get(id);
            boolean redirected = false;
            for (Cond c: w.getCondList()) {
                if(evaluateCond(c,x,m,a,s)){
                    id = c.getWhenTru();
                    redirected = true;
                    break;
                }
            }
            if(!redirected)id = w.getWhenFal();
            //TODO if you get A return sum of all attributes else return 0
            if(id.equals("A"))score = x+m+a+s;
        }

        return score;
    }

    private static boolean evaluateCond(Cond c,int x, int m, int a,int s) {
        if(c.getCategory().equals("x")){
            if(c.isLarger()) return x > c.getValue();
            else return x < c.getValue();
        }
        if(c.getCategory().equals("m")){
            if(c.isLarger()) return m > c.getValue();
            else return m < c.getValue();
        }
        if(c.getCategory().equals("a")){
            if(c.isLarger()) return a > c.getValue();
            else return a < c.getValue();
        }
        if(c.getCategory().equals("s")){
            if(c.isLarger()) return s > c.getValue();
            else return s < c.getValue();
        }
        return false;
    }

    private static void processLine(String line) {
        String[] parts = line.split("\\{");
        String[] conds = parts[1].split(",");
        String id = parts[0];
        List<Cond> condList = new ArrayList<>();
        for (int i = 0; i < conds.length-1; i++) {
            String cond = conds[i];
            String attribute = String.valueOf(cond.charAt(0));
            boolean isLarger = cond.charAt(1) == '>';
            int val = Integer.parseInt(cond.replaceAll("[^0-9]", ""));
            String target = cond.split(":")[1];
            condList.add(new Cond(attribute,isLarger,val,target));
        }
        work_system.put(id,new Workflow(condList,conds[conds.length-1].replaceAll("}","")));
    }
}