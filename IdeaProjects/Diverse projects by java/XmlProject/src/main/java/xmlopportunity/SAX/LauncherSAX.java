package xmlopportunity.SAX;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class LauncherSAX
{
    public static void main(String[] args)
    {
        try
        {
            XMLReader readerFactory = XMLReaderFactory.createXMLReader();
            SimpleXmlHandler simpleXmlHandler = new SimpleXmlHandler();
            readerFactory.setContentHandler(simpleXmlHandler);
            readerFactory.parse(LauncherSAX.class.getClassLoader().getResource("common/diplomMatrixAdjancecy_33BP.xml").getFile());
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
