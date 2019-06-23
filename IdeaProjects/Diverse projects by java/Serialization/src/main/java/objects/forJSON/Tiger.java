package objects.forJSON;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Tiger implements Serializable {

    private List<String> names;
    private int legs = 4;
    private boolean hasTail;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public boolean isHasTail() {
        return hasTail;
    }

    public void setHasTail(boolean hasTail) {
        this.hasTail = hasTail;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "names=" + names +
                ", legs=" + legs +
                ", hasTail=" + hasTail +
                '}';
    }
}
