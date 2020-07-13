package xmlopportunity.jdom2.createXmlFile;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;

public class CreatorXmlFile
{
    public static void main(String[] args) throws IOException
    {
        Document document = new Document();
        document.setRootElement(new Element("cats"));

        document.getRootElement().addContent(createXmlCat(1, "Masha", (byte) 15));
        document.getRootElement().addContent(createXmlCat(2, "Kuzya", (byte) 5));

        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, new FileWriter("D:\\Repository\\My projects (programing)\\MyProjects.git\\IdeaProjects\\Diverse projects by java\\XmlProject\\src\\main\\resources\\jdom2\\creator\\cats.xml"));
    }

    private static Element createXmlCat(int id, String name, byte age)
    {
        Element cat = new Element("cat");
        cat.setAttribute("id", String.valueOf(id));
        cat.addContent(new Element("name").setText(name));
        cat.addContent(new Element("age").setText(String.valueOf(age)));
        return cat;
    }
}
