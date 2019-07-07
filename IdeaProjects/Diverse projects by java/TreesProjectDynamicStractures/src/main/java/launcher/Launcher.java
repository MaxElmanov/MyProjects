package launcher;

import binaryTree.BinaryTree;
import binaryTree.Node;

public class Launcher
{
    public static void main(String[] args)
    {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.add(3, "a");

        System.out.println("search 3\n");
        binaryTree.print(binaryTree.search(3));

        System.out.println("removing 3\n");
        binaryTree.remove(3);

        System.out.println("search 3\n");
        binaryTree.print(binaryTree.search(2));
    }

}
