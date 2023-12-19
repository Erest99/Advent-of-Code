import java.awt.*;
import java.util.List;

public class Workflow {
    private List<Cond> condList;
    private String whenFal;
    public Workflow(List<Cond> condList, String whenFal) {
        this.condList = condList;
        this.whenFal = whenFal;
    }
    public List<Cond> getCondList() {
        return condList;
    }

    public void setCondList(List<Cond> condList) {
        this.condList = condList;
    }

    public String getWhenFal() {
        return whenFal;
    }

    public void setWhenFal(String whenFal) {
        this.whenFal = whenFal;
    }
}
