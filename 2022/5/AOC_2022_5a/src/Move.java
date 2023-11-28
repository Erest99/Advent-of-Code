public class Move {
    private int quantity;
    private int source;
    private int target;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public Move(int quantity, int source, int target) {
        this.quantity = quantity;
        this.source = source;
        this.target = target;
    }
}
