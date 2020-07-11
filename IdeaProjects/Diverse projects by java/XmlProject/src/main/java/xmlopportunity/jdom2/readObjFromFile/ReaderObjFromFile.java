package xmlopportunity.jdom2.readObjFromFile;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import xmlopportunity.SAXB.objects.FootballPlayer;
import xmlopportunity.SAXB.objects.Team;

import java.io.IOException;
import java.util.Objects;

public class Launcher
{
    public static void main(String[] args)
    {
        try
        {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(Launcher.class.getClassLoader().getResource("saxb/marshulledTeam.xml"));
            Element rootElement = doc.getRootElement();

            Team team = new Team();

            if (Objects.nonNull(rootElement))
            {
                //team
                if (rootElement.hasAttributes())
                {
                    team.setName(rootElement.getAttributeValue("NAME"));
                }

                //footballPlayer
                for (Element playerElement : rootElement.getChildren("footballPlayer"))
                {
                    FootballPlayer player = new FootballPlayer();
                    FootballPlayer.Country country = new FootballPlayer.Country();

                    if (playerElement.hasAttributes())
                    {
                        player.setNumber(Byte.parseByte(playerElement.getAttributeValue("number")));
                    }

                    player.setName(playerElement.getChildText("name"));

                    country.setName(playerElement.getChild("country").getChildText("name"));

                    player.setCountry(country);
                    team.add(player);
                }
            }

            //OUTPUTTING
            System.out.println(team);
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
