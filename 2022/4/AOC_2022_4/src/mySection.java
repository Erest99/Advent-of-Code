public class mySection {
    private int lowerNum;
    private int higherNum;

    public int getLowerNum() {
        return lowerNum;
    }

    public void setLowerNum(int lowerNum) {
        this.lowerNum = lowerNum;
    }

    public int getHigherNum() {
        return higherNum;
    }

    public void setHigherNum(int higherNum) {
        this.higherNum = higherNum;
    }

    public mySection(int lowerNum, int higherNum) {
        this.lowerNum = lowerNum;
        this.higherNum = higherNum;
    }

    public mySection() {
        this.lowerNum = 0;
        this.higherNum = 0;
    }
}
