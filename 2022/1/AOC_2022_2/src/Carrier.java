import java.util.ArrayList;
import java.util.List;

public class Carrier {
    private List<Integer> goodies;

    public Carrier() {
        this.goodies = new ArrayList<>();
        for(int i = 0;i<3;i++) {
            goodies.add(0);
        }
    }

    public List<Integer> getGoodies() {
        return goodies;
    }

    public void setGoodies(List<Integer> goodies) {
        this.goodies = goodies;
    }
}
