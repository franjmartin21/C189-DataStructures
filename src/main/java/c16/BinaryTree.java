package c16;

import c4.LinkedStack;
import c4.StackInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 A class that implements the ADT binary tree.
 @author Frank M. Carrano.
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
    private BinaryNodeInterface<T> root;
    public BinaryTree()
    {
        root = null;
    } // end default constructor
    public BinaryTree(T rootData)
    {
        root = new BinaryNode<T>(rootData);
    } // end constructor
    public BinaryTree(T rootData, BinaryTree<T> leftTree,BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor
    public void setTree(T rootData)
    {
        root = new BinaryNode<T>(rootData);
    } // end setTree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,BinaryTreeInterface<T> rightTree)
    {
        privateSetTree(rootData, (BinaryTree<T>)leftTree,(BinaryTree<T>)rightTree);
    } // end setTree

    /**
     * FIRST DRAFT of privateSetTree - See Segments 24.5 - 24.8 for improvements.
    private void privateSetTree(T rootData, BinaryTree<T> leftTree,BinaryTree<T> rightTree)
    {
    // FIRST DRAFT - See Segments 24.5 - 24.8 for improvements.
            root = new BinaryNode<T>(rootData);
        if (leftTree != null)
            root.setLeftChild(leftTree.root);
        if (rightTree != null)
            root.setRightChild(rightTree.root);


    } // end privateSetTree */
    /**
     * PROBLEM: with previous implementation.
     * The implementation of privateSetTree just given is really not sufficient to handle all possible uses of the method.
     * Suppose that the client defines three distinct instances of BinaryTree—treeA, treeB, and treeC—and executes the
     * statement treeA.setTree(a, treeB, treeC);
     *
     * Since setTree calls privateSetTree, treeA shares nodes with treeB and treeC, as Figure 16-1 illustrates. If the
     * client now changes treeB, for example, treeA also changes. This result generally is undesirable.
     *
     * SOLUTION: privateSetTree to copy the nodes in treeB and treeC. Then treeA will be separate and distinct from treeB
     * and treeC. Any subsequent changes to either treeB or treeC will not affect treeA.
     *
     * Since we are copying nodes, we use the method copy as specified in the interface Binary-NodeInterface. To copy a node,
     * we actually must copy the subtree rooted at the node. Beginning with the node, we copy it and then copy the nodes in
     * its left and right subtrees. Thus, we perform a preorder traversal of the subtree. For simplicity, we will not copy
     * the data in the nodes.
     * @param rootData
     * @param leftTree
     * @param rightTree
    private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        root = new BinaryNode<T>(rootData);
        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root.copy());
        if ((rightTree != null) && !rightTree.isEmpty())
            root.setRightChild(rightTree.root.copy());
    } // end privateSetTree
     */
    /**
     * ANOTHER PROBLEM: Previous solution is safe but expensive as it requires to copy all nodes,
     *
     * SECOND SOLUTION. To summarize, privateSetTree should take the following steps:
     * 1. Create a root node r containing the given data.
     * 2. If the left subtree exists and is not empty, attach its root node to r as a left child.
     * 3. If the right subtree exists, is not empty, and is distinct from the left subtree, attach its root node to r as
     * a right child. But if the right and left subtrees are the same, attach a copy of the right subtree to r instead.
     * 4. If the left subtree exists and differs from the tree object used to call privateSetTree, set the subtree's data field root to null.
     * 5. If the right subtree exists and differs from the tree object used to call privateSetTree, set the subtree's data field root to null.
     */
    private void privateSetTree(T rootData, BinaryTree<T> leftTree,BinaryTree<T> rightTree)
    {
        root = new BinaryNode<T>(rootData);
        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        } // end if
        if ((leftTree != null) && (leftTree != this))
            leftTree.clear();
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
    } // end privateSetTree

    public T getRootData()
    {
        T rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
    } // end getRootData
    public boolean isEmpty()
    {
        return root == null;
    } // end isEmpty
    public void clear()
    {
        root = null;
    } // end clear
    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // end setRootData
    protected void setRootNode(BinaryNodeInterface<T> rootNode)
    {
        root = rootNode;
    } // end setRootNode
    protected BinaryNodeInterface<T> getRootNode()
    {
        return root;
    } // end getRootNode
    public int getHeight()
    {
        return root.getHeight();
    } // end getHeight
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    } // end getNumberOfNodes

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        return new PostorderIterator();
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        return new LevelorderIterator();
    }

    private class InorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> currentNode;

        public InorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            BinaryNodeInterface<T> nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                assert nextNode != null; // since nodeStack was not empty
                // before the pop
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();

            return nextNode.getData();
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator

    private class PreorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> currentNode;

        public PreorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            return null;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator

    private class PostorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> currentNode;

        public PostorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            return null;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator

    private class LevelorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> currentNode;

        public LevelorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            return null;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator
} // end BinaryTree