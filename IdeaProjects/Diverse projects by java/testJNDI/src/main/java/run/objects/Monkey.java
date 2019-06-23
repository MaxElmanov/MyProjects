package run.objects;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class Monkey implements Referenceable {

    public static final String NAME = "name";
    public static final String FAVORITE_FRUIT = "favorite fruit";
    public static final String LIKES_BANANAS = "Does it like bananas?";

    private String name;
    private String favoriteFruit;
    private boolean likesBananas;

    public Monkey() {
    }

    public Monkey(String name, String favoriteFruit, boolean likesBananas) {
        this.name = name;
        this.favoriteFruit = favoriteFruit;
        this.likesBananas = likesBananas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public boolean isLikesBananas() {
        return likesBananas;
    }

    public void setLikesBananas(boolean likesBananas) {
        this.likesBananas = likesBananas;
    }

    @Override
    public Reference getReference() throws NamingException {
        Reference reference = new Reference(Monkey.class.getName(), MonkeyFactory.class.getName(), null);

        reference.add(new StringRefAddr(NAME, this.name));
        reference.add(new StringRefAddr(FAVORITE_FRUIT, this.favoriteFruit));
        reference.add(new StringRefAddr(LIKES_BANANAS, Boolean.toString(this.likesBananas)));

        return reference;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", favoriteFruit='" + favoriteFruit + '\'' +
                ", likesBananas=" + likesBananas +
                '}';
    }
}
