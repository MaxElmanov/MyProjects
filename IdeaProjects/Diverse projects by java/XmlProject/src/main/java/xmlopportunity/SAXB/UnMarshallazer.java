package xmlopportunity.SAXB;

import xmlopportunity.SAXB.objects.Team;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class UnMarshallazer
{
    public static void main(String[] args)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(Team.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
//            FileReader reader = new FileReader("D:\\Repository\\My projects (programing)\\MyProjects.git\\IdeaProjects\\Diverse projects by java\\XmlProject\\src\\main\\resources\\saxb\\marshulledTeam.xml");
            FileInputStream fis = new FileInputStream(new File(new UnMarshallazer().getClass().getClassLoader().getResource("saxb/marshulledTeam.xml").toURI()));
            Team team = (Team) unmarshaller.unmarshal(fis);

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
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
