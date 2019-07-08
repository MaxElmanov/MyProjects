package launcher;

import binaryTree.BinaryTree;

public class Launcher
{
    public static void main(String[] args)
    {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.add(7, "a");
        binaryTree.add(3, "a");
        binaryTree.add(2, "a");
        binaryTree.add(5, "a");
        binaryTree.add(4, "a");
        binaryTree.add(6, "a");

        System.out.println("search one\n");
        binaryTree.print(binaryTree.search(7));

        System.out.println("removing 5\n");
        binaryTree.remove(7);

        System.out.println("search two\n");
        binaryTree.print(binaryTree.search(3));
    }

}
