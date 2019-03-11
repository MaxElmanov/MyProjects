package run.objects;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Enumeration;
import java.util.Hashtable;

public class MonkeyFactory implements ObjectFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        if(!(obj instanceof Reference)) {
            return null;
        }

        Reference reference = (Reference) obj;
        if(!(Monkey.class.getName().equals(reference.getClassName()))) {
            return null;
        }

        Monkey monkey = new Monkey();
        Enumeration<RefAddr> addresses = reference.getAll();

        while(addresses.hasMoreElements()){
            RefAddr address = addresses.nextElement();

            switch(address.getType()){
                case Monkey.NAME:
                    monkey.setName((String) address.getContent());
                    break;
                case Monkey.FAVORITE_FRUIT:
                    monkey.setFavoriteFruit((String) address.getContent());
                    break;
                case Monkey.LIKES_BANANAS:
                    monkey.setLikesBananas(Boolean.valueOf((String) address.getContent()));
                    break;
            }
        }

        return monkey;
    }
}
