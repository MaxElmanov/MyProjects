package xmlopportunity.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class SimpleXmlHandler extends DefaultHandler
{
    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("parsing is started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        if(Objects.nonNull(uri) && !uri.isEmpty())
            System.out.print("uri = " + uri);
        if(Objects.nonNull(localName) && !localName.isEmpty())
            System.out.print("localName = " + localName);
        if(Objects.nonNull(qName) && !qName.isEmpty())
            System.out.print("qName = " + qName);

        System.out.println("\nAttributes:");
        for (int i = 0; i < attributes.getLength(); i++)
        {
            System.out.println(attributes.getLocalName(i) + " = " + attributes.getValue(i));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String str = new String(ch);
        if(ch.length != 0 && !str.contains("<") && !str.contains(">"))
            System.out.print("content = " + new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println(localName);
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("parsing is finished");
    }
}
