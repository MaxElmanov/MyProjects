package objects.forPLainSerialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DataObject extends NotSerializable implements Serializable {

    private static final long serialVersionUID = 1L;

    private int number = 5;
    private String str = "aaa";
    private String[] massStr = {"h", "e", "l", "l", "o"};

    private transient CustomObject co;

    @Override
    public String toString() {
        return "DataObject{" +
                "number=" + number +
                ", str='" + str + '\'' +
                ", massStr=" + Arrays.toString(massStr) +
                ", co=" + co +
                ", data=" + data +
                '}';
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(number);
        out.writeObject(str);
        out.writeObject(data);
        out.writeObject(massStr);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        number = in.readInt();
        str = (String)in.readObject();
        data = (String)in.readObject();
        massStr = (String[]) in.readObject();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
