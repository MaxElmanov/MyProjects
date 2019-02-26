package launcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.forJSON.Tiger;
import objects.forJSON.ZOO;
import objects.forPLainSerialization.DataObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.Arrays;

public class run {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Tiger t1 = new Tiger();
        t1.setHasTail(true);
        t1.setLegs(4);
        t1.setNames(Arrays.asList("Tom", "Humster", "Misha"));

        Tiger t2 = new Tiger();
        t2.setHasTail(false);
        t2.setLegs(3);
        t2.setNames(Arrays.asList("Cordinal"));

        ZOO zoo = new ZOO();
        zoo.setTigers(new Tiger[]{t2, t1});

//        JSON_write(zoo); //1

//        SERIALIZATION_write(zoo); //2

        MAPPERMETHOD_read_write(); //3

    }

    private static void MAPPERMETHOD_read_write() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("serialization.max");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ZOO zoo = (ZOO) ois.readObject();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("myGson.json"), zoo);

        ois.close();
    }

    private static void SERIALIZATION_write(ZOO zoo) throws IOException {

        FileOutputStream fos = new FileOutputStream("serialization.max");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(zoo);
        oos.flush();
        oos.close();

    }

    private static void JSON_write(ZOO zoo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String obj = gson.toJson(zoo);

        FileWriter writer = new FileWriter("myGson.json");
        writer.write(obj);
        writer.close();

        ZOO zoo2 = gson.fromJson(obj, ZOO.class);
        System.out.println(zoo2);
    }



    private static void writeObjectInSerialization(Object obj, String nameFile){

        File file = new File(nameFile);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);

            oos.flush();
            oos.close();
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object readObjectInSerialization(String nameFile){

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        DataObject dataObj = null;

        try {
            fis = new FileInputStream(nameFile);
            ois = new ObjectInputStream(fis);

            dataObj = (DataObject) ois.readObject();

            ois.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataObj;
    }

}
