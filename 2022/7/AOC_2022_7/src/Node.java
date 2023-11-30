import java.util.ArrayList;

public class Node {

    private int id;
    private String name;
    private String path;
    private int size;
    private boolean isFile;
    private Node parent;
    private ArrayList<Node> children;

    public Node(int id, String name, String path, int size, boolean isFile, Node parent, ArrayList<Node> children) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.size = size;
        this.isFile = isFile;
        this.parent = parent;
        this.children = children;
    }

    public Node() {
        this.name = null;
        this.path = null;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Node(Node another){
        this.id = another.getId();
        this.name = another.getname();
        this.path = another.getPath();
        this.size = another.getSize();
        this.isFile = another.getIsFile();
        this.parent = another.getParent();
        this.children = another.getChildren();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getIsFile() {
        return isFile;
    }

    public void setIsFile(boolean file) {
        this.isFile = file;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public ArrayList<Integer> getChildrenId(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Node n: children) {
            ids.add(n.getId());
        }
        return ids;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
}

