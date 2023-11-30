import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Node currentFolder = new Node();
    private static Node parentFolder = new Node();
    private static ArrayList<Node> tree = new ArrayList<>();
    private static int id = 1;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Horky\\IdeaProjects\\AOC_2022_7\\src\\input.txt"));
            String row = scanner.nextLine();
            openDir(row);
            while (scanner.hasNextLine()) {
                row = scanner.nextLine();
                if(row.contains("$ cd .."))leaveDir();
                else if(row.contains("$ cd"))openDir(row);
                else if(row.contains("$ ls"))showDir(row);
                else if(row.contains("dir"));
                else currentFolder.setSize(currentFolder.getSize()+Integer.valueOf(row.replaceAll("[^0-9]", "")));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //TODO iterate over tree and sumarize size of all parent folders
        //TODO filter those directories that are smaller than 100000
        System.out.println();
    }

    private static void showDir(String row) {
    }

    private static void leaveDir() {
        Node temp = currentFolder.getParent();
        parentFolder = temp.getParent();
        currentFolder = temp;
        
    }

    private static void openDir(String row) {
        //TODO check if node is in tree, if yes -> copy it from tree, if no -> use this code to create new node
        //TODO upravit name tak, aby se podle něj dalo vyhledávat -> save absolute path
        parentFolder = new Node(currentFolder);
        currentFolder.setId(id);
        currentFolder.setname(row.replace("$ cd ",""));
        currentFolder.setParent(parentFolder);
        currentFolder.setIsFile(false);
        if(!parentFolder.getChildrenId().contains(currentFolder.getId())){
            parentFolder.getChildren().add(currentFolder);
            //TODO přidat do tree
        }
        //TODO add saving nodes into tree
//        if(!tree.stream().anyMatch(i -> i.getId() == id)){
//            tree.add(tree.stream().filter(node -> node.getId() == id).findFirst().orElse(null));
//        }
        id++;
    }
}