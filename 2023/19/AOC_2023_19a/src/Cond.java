public class Cond {
    private String category;
    private boolean isLarger;
    private int value;
    private String target;

    public Cond(String category, boolean isLarger, int value, String target) {
        this.category = category;
        this.isLarger = isLarger;
        this.value = value;
        this.target = target;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isLarger() {
        return isLarger;
    }

    public void setLarger(boolean larger) {
        isLarger = larger;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getWhenTru() {
        return target;
    }

    public void setWhenTru(String target) {
        this.target = target;
    }
}
