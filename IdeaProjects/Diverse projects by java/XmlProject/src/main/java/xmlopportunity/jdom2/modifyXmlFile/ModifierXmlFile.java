package xmlopportunity.jdom2.modifyXmlFile;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class works with file "jdom2/start.xml".
 * It performs convert fields (id, name, country) in a @person block into it's attributes
 * and save new built file as "jdom2/end.xml"
 */

public class ModifierXmlFile
{
    public static void main(String[] args)
    {
//        String filePath = ModifierXmlFile.class.getClassLoader().getResource(File.separator).getPath() + "jdom2/end.xml";
        String filePath = "D:\\Repository\\My projects (programing)\\MyProjects.git\\IdeaProjects\\Diverse projects by java\\XmlProject\\target\\classes\\jdom2\\end.xml";

        try
        {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(ModifierXmlFile.class.getClassLoader().getResource("jdom2/modifier/start.xml"));
            Element rootElement = document.getRootElement();

            for (Element pE : rootElement.getChildren("person")){
                //id
                pE.setAttribute(pE.getChild("id").getName(), pE.getChildText("id"));
                pE.removeChild("id");
                //name
                pE.setAttribute(pE.getChild("name").getName(), pE.getChildText("name"));
                pE.removeChild("name");
                //country
                pE.setAttribute(pE.getChild("country").getName(), pE.getChildText("country"));
                pE.removeChild("country");
                //new field
                pE.addContent(new Element("status").setText("updated"));
            }

            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            File file = new File(filePath);
            //create endFile
            Path path = Paths.get(file.getAbsolutePath());
            Files.createFile(path);
            //output
            outputter.output(document, new BufferedOutputStream(new FileOutputStream(file)));
        }
        catch (JDOMException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
