package binaryTree;

public class Node implements Comparable<Integer>
{
    private int key;
    private String value;
    private Node left;
    private Node right;

    public Node(int key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public int compareTo(Integer key)
    {
        return this.key == key ? 0 : this.key > key ? 1 : -1;
    }
}
