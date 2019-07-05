package logics;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class Ini4jExecutor
{
    private String fileName;
    private Wini ini;
    private static final String CONFIG_FOLDER = "config";

    public Ini4jExecutor(String fileName)
    {
        this.fileName = fileName;
        init();
    }

    private void init()
    {
        try {
            ini = new Wini(new File("./" + CONFIG_FOLDER + "/" + fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getProperty(String section, String property, Class typeClass)
    {
        return ini.get(section, property, typeClass);
    }

    public void putProperty(String section, String property, Object value)
    {
        ini.put(section, property, value);
    }
}
