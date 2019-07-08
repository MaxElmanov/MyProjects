package binaryTree;

public class BinaryTree
{
    private Node root;
    private int amountElements;

    public BinaryTree()
    {
    }

    public Node getRoot()
    {
        return root;
    }

    public int getAmountElements()
    {
        return amountElements;
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

        amountElements++;
    }

    public void print(Node startNode)
    {
        if (startNode != null) {
            print(startNode.getLeft());
            System.out.println(startNode);
            print(startNode.getRight());
        }
    }

    public void printEntire()
    {
        if (root != null) {
            print(root.getLeft());
            System.out.println(root);
            print(root.getRight());
        }
    }

    //todo i must do removing for nodes with 1, 2 children, without children, leaves
    public void remove(int key)
    {
        Node node = search(key);
        if (node == null) {
            return;
        }

        //node == root
        if (node == root) {
            removeRoot();
            amountElements--;
            return;
        }

        Node parentCurrent = findNodeParent(node);

        //node has no children (leaves)
        if (node.getLeft() == null && node.getRight() == null) {
            removeLorRchild(parentCurrent, node);
            amountElements--;
            return;
        }

        //node has only right child
        if (node.getRight() != null && node.getLeft() == null) {
            replaceRightChild(parentCurrent, node);
            amountElements--;
            return;
        }

        //node has only left child
        if (node.getLeft() != null && node.getRight() == null) {
            replaceLeftChild(parentCurrent, node);
            amountElements--;
            return;
        }

        //node has left and right child (current has children (Left && Right). It's 100%)
        if (node.getLeft() != null && node.getRight() != null) {
            Node replacingNode = findMostLeftNode(node.getRight());
            replacingNode.setLeft(node.getLeft());
            Node parentNode = findNodeParent(node);

            if (node.getRight() != replacingNode) {
                Node replacingNodeParent = findNodeParent(replacingNode);
                removeLorRchild(replacingNodeParent, replacingNode);
                replacingNode.setRight(replacingNodeParent);
            }

            if (parentNode.compareTo(node.getKey()) > 0) {
                parentNode.setLeft(replacingNode);
            }
            else {
                parentNode.setRight(replacingNode);
            }

            amountElements--;
            return;
        }
    }

    private void removeRoot()
    {
        Node replacingNode = null;

        //100% root == node
        if (root.getRight() != null) {
            replacingNode = findMostLeftNode(root.getRight());
            replacingNode.setLeft(root.getLeft());
            removeLorRchild(findNodeParent(replacingNode), replacingNode);
            replacingNode.setRight(root.getRight());
        }
        else if (root.getLeft() != null) {
            replacingNode = findMostRightNode(root.getLeft());
            replacingNode.setRight(root.getRight());
            removeLorRchild(findNodeParent(replacingNode), replacingNode);
            replacingNode.setLeft(root.getLeft());
        }

        root = replacingNode;
    }

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

    private void removeLorRchild(Node previousCurrent, Node current)
    {
        if (previousCurrent.compareTo(current.getKey()) > 0) {
            previousCurrent.setLeft(null);
        }
        else {
            previousCurrent.setRight(null);
        }
    }

    private void replaceRightChild(Node previousCurrent, Node current)
    {
        if (previousCurrent.compareTo(current.getKey()) > 0) {
            previousCurrent.setLeft(current.getRight());
        }
        else {
            previousCurrent.setRight(current.getRight());
        }

    }

    private void replaceLeftChild(Node previousCurrent, Node current)
    {
        if (previousCurrent.compareTo(current.getKey()) > 0) {
            previousCurrent.setLeft(current.getLeft());
        }
        else {
            previousCurrent.setRight(current.getLeft());
        }
    }

    private Node findNodeParent(Node replacingNode)
    {
        Node current = root;
        Node currentParent = null;

        while (current != null) {
            int cmp = current.compareTo(replacingNode.getKey());
            if (cmp == 0) {
                return currentParent;
            }
            else {
                currentParent = current;

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

    public void clear()
    {
        root = null;
    }
}
