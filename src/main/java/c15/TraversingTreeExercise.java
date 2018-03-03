package c15;

public class TraversingTreeExercise {


    public static void main(String[] args) {
        BinaryNode hNode = new BinaryNode("H", null, null);
        BinaryNode iNode = new BinaryNode("I", null, null);
        BinaryNode eNode = new BinaryNode("E", null, null);
        BinaryNode fNode = new BinaryNode("F", null, null);
        BinaryNode jNode = new BinaryNode("J", null, null);
        BinaryNode kNode = new BinaryNode("K", null, null);

        BinaryNode dNode = new BinaryNode("D", hNode, iNode);
        BinaryNode gNode = new BinaryNode("G", jNode, kNode);
        BinaryNode bNode = new BinaryNode("B", dNode, eNode);
        BinaryNode cNode = new BinaryNode("C", fNode, gNode);

        BinaryNode rootNode = new BinaryNode("A", bNode, cNode);

        printInOrder(rootNode);
        //printPreOrder(rootNode);
        //printPostOrder(rootNode);
        //printLevelOrder(rootNode);
        //System.out.println(height(rootNode));

    }

    private static void printInOrder(BinaryNode rootNode){
        if(rootNode == null) return;

        printInOrder(rootNode.leftNode);
        System.out.print(rootNode.value + " ");
        printInOrder(rootNode.rightNode);
    }

    private static void printPreOrder(BinaryNode rootNode){
        if(rootNode == null) return;

        System.out.println(rootNode.value + " ");
        printPreOrder(rootNode.leftNode);
        printPreOrder(rootNode.rightNode);
    }

    private static void printPostOrder(BinaryNode rootNode){
        if(rootNode == null) return;

        printPostOrder(rootNode.leftNode);
        printPostOrder(rootNode.rightNode);
        System.out.println(rootNode.value + " ");
    }


    /**
     * Print level order
     * @param rootNode
     */
    private static void printLevelOrder(BinaryNode rootNode){
        int h = height(rootNode);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(rootNode, i);
    }

    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
    static int height(BinaryNode root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.leftNode);
            int rheight = height(root.rightNode);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else
                return(rheight+1);
        }
    }

    /* Print nodes at the given level */
    static void printGivenLevel (BinaryNode root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.value + " ");
        else if (level > 1)
        {
            printGivenLevel(root.leftNode, level-1);
            printGivenLevel(root.rightNode, level-1);
        }
    }

    static class BinaryNode{
        String value;
        BinaryNode leftNode;
        BinaryNode rightNode;

        public BinaryNode(String value, BinaryNode leftNode, BinaryNode rightNode) {
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }
}
