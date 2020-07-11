package xmlopportunity.SAXB.objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UnMarshallazer
{
    public static void main(String[] args)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(Team.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader reader = new FileReader("D:\\Repository\\My projects (programing)\\MyProjects.git\\IdeaProjects\\Diverse projects by java\\XmlProject\\src\\main\\resources\\saxb\\marshulledTeam.xml");
            Team team = (Team) unmarshaller.unmarshal(reader);

            System.out.println(team);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
