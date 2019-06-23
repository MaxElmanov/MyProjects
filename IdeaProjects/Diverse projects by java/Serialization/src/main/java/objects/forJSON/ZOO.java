package objects.forJSON;

import java.io.Serializable;
import java.util.Arrays;

public class ZOO implements Serializable {

    Tiger[] tigers;

    public ZOO(Tiger... tigers) {
        this.tigers = tigers;
    }

    public Tiger[] getTigers() {
        return tigers;
    }

    public void setTigers(Tiger[] tigers) {
        this.tigers = tigers;
    }

    @Override
    public String toString() {
        return "ZOO{" +
                "tigers=" + Arrays.toString(tigers) +
                '}';
    }
}
