package binaryTree;

public class BinaryTree
{
    Node root;

    public BinaryTree()
    {
    }

    public Node search(int key)
    {
        Node current = root;

        while (current != null) {
            int cmp = current.compareTo(key);
            if (cmp == 0) {
                return current;
            }
            else {
                if (cmp > 0) {
                    current = current.getLeft();
                }
                else {
                    current = current.getRight();
                }
            }
        }

        return null;
    }

//    private boolean hasRoot()
//    {
//        return root == null ? true : false;
//    }

    public void add(int key, String value)
    {
        Node newNode = new Node(key, value);
        add(newNode);
    }

    public void add(Node newNode)
    {
        if (root == null) {
            root = newNode;
        }
        else {
            Node current = root;
            Node previousCurrent = null;
            while (current != null) {
                int cmp = current.compareTo(newNode.getKey());

                if (cmp == 0) {
                    current.setValue(newNode.getValue());
                    return;
                }
                else {
                    previousCurrent = current;
                    if (cmp > 0) {
                        current = current.getLeft();
                    }
                    else {
                        current = current.getRight();
                    }
                }
            }

            if (previousCurrent.compareTo(newNode.getKey()) > 0) {
                previousCurrent.setLeft(newNode);
            }
            else {
                previousCurrent.setRight(newNode);
            }
        }
    }

    public void print(Node startNode)
    {
        if (startNode != null) {
            print(startNode.getLeft());
            System.out.println(startNode);
            print(startNode.getRight());
        }
    }

    //todo i've developed removing only in case root deleting
    //todo i must do removing for nodes with 1, 2 children, without children, leaves
    public void remove(int key)
    {
        Node node = search(key);
        if (node == null) {
            return;
        }

        //node == root
        if (node == root){
            removeRoot(node);
            return;
        }

        //node has one right child


//        Node current = root;
//        Node previousCurrent = null;
//
//        while (current != null) {
//            int cmp = current.compareTo(key);
//            if (cmp == 0) {
//                current = replace(current);
//                return;
//            }
//            else {
//                previousCurrent = current;
//
//                if (cmp > 0) {
//                    current = current.getLeft();
//                }
//                else {
//                    current = current.getRight();
//                }
//            }
//        }
    }

    private void removeRoot(Node node)
    {
        //100% root == node
        Node replacingNode = null;
        if (root.getRight() != null) {
            replacingNode = findMostLeftNode(root.getRight());
            replacingNode.setLeft(root.getLeft());
            replacingNode.setRight(root.getRight());
            root.getRight().setLeft(null);
        }
        else if (root.getLeft() != null) {
            replacingNode = findMostRightNode(root.getLeft());
            replacingNode.setLeft(root.getLeft());
            root.getLeft().setRight(null);
        }

        root = replacingNode;
    }

    //    private Node replace(Node root)
//    {
//        Node replacingNode = null;
//
//        if (root.getRight() != null) {
//            replacingNode = findMostLeftNode(root.getRight());
//        }
//        else if (root.getLeft() != null) {
//            replacingNode = findMostRightNode(root.getLeft());
//        }
//
//        return replacingNode;
//    }
//
    private Node findMostLeftNode(Node right)
    {
        Node mostLeft = right;

        while (mostLeft.getLeft() != null) {
            mostLeft = mostLeft.getLeft();
        }

        return mostLeft;
    }

    private Node findMostRightNode(Node left)
    {
        Node mostRight = left;

        while (mostRight.getRight() != null) {
            mostRight = mostRight.getRight();
        }

        return mostRight;
    }
}
