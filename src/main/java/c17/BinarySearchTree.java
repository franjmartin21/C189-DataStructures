package c17;

import c16.BinaryNode;
import c16.BinaryNodeInterface;
import c16.BinaryTree;
import c16.BinaryTreeInterface;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T>
{
    public BinarySearchTree()
    {
        super();
    } // end default constructor
    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    } // end constructor

    /**
     * Disable setTree. Before we go any further, consider the two setTree methods that our class inherits from BinaryTree.
     * The client could use these methods to create a tree that, unfortunately, is not a binary search tree. This outcome
     * would be impossible if the client used SearchTreeInterface to declare an instance of the tree.
     * @param rootData
     */
    public void setTree(T rootData) // disable setTree (see Segment 25.6)
    {
        throw new UnsupportedOperationException();
    } // end setTree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree
    /*
    < Implementations of contains, getEntry, add, and remove are here. Their definitions appear in subsequent sections of this chapter. Other methods in SearchTreeInterface are inherited from BinaryTree. >
    */
    // Adds newEntry to the nonempty subtree rooted at rootNode.

    /**
     * Recursive implementation of addEntry
     * @param rootNode
     * @param newEntry
     * @return
     */
    private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry)
    {
        assert rootNode != null;
        T result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(newEntry);
        }
        else if (comparison < 0)
        {
            if (rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), newEntry);
            else
                rootNode.setLeftChild(new BinaryNode<T>(newEntry));
        }
        else
        {
            assert comparison > 0;
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), newEntry);
            else
                rootNode.setRightChild(new BinaryNode<T>(newEntry));
        } // end if
        return result;
    } // end addEntry

    /**
     * Iterative implementation of addEntry
     * @param newEntry
     * @return
     */
    private T addEntry(T newEntry)
    {
        BinaryNodeInterface<T> currentNode = getRootNode();
        assert currentNode != null;
        T result = null;
        boolean found = false;
        while (!found)
        {
            T currentEntry = currentNode.getData();
            int comparison = newEntry.compareTo(currentEntry);
            if (comparison == 0)
            { // newEntry matches currentEntry;
                // return and replace currentEntry
                found = true;
                result = currentEntry;
                currentNode.setData(newEntry);
            }
            else if (comparison < 0)
            {
                if (currentNode.hasLeftChild())
                    currentNode = currentNode.getLeftChild();
                else
                {
                    found = true;
                    currentNode.setLeftChild(new BinaryNode<T>(newEntry));
                } // end if
            }
            else
            {
                assert comparison > 0;
                if (currentNode.hasRightChild())
                    currentNode = currentNode.getRightChild();
                else
                {
                    found = true;
                    currentNode.setRightChild(new BinaryNode<T>(newEntry));
                } // end if
            } // end if
        } // end while
        return result;
    } // end addEntry

    @Override
    public T add(T newEntry)
    {
        T result = null;
        if (isEmpty())
            setRootNode(new BinaryNode<T>(newEntry));
        else
            result = addEntry(getRootNode(), newEntry);
        return result;
    } // end add

    @Override
    public boolean contains(T entry) {
        return false;
    }

    @Override
    public T getEntry(T entry) {
        return null;
    }

    @Override
    public T remove(T entry)
    {
        ReturnObject<T> oldEntry = new ReturnObject<>(null);
        BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry,oldEntry);
        setRootNode(newRoot);
        return oldEntry.get();
    } // end yeremove

    // Removes an entry from the tree rooted at a given node.
    // rootNode is a reference to the root of a tree.
    // entry is the object to be removed.
    // oldEntry is an object whose data field is null.
    // Returns the root node of the resulting tree; if entry matches
    // an entry in the tree, oldEntry's data field is the entry
    // that was removed from the tree; otherwise it is null.
    private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode,
                                               T entry, ReturnObject oldEntry)
    {
        if (rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0) // entry == root entry
            {
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            }
            else if (comparison < 0) // entry < root entry
            {
                BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
                BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild,entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            }
            else // entry > root entry
            {
                BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
            } // end if
        } // end if
        return rootNode;
    } // end removeEntry

    // Removes the entry in a given root node of a subtree.
    // rootNode is the root node of the subtree.
    // Returns the root node of the revised subtree.
    private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode)
    {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            // find node with largest entry in left subtree
            BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
            // replace entry in root
            rootNode.setData(largestNode.getData());
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        } // end if
        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        // Assertion: if rootNode was a leaf, it is now null
        return rootNode;
    } // end removeEntry

    // Finds the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the node containing the largest entry in the tree.
    private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode)
    {
        if (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    } // end findLargest

    // Removes the node containing the largest entry in a given tree.
// rootNode is the root node of the tree.
// Returns the root node of the revised tree.
    private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode)
    {
        if (rootNode.hasRightChild())
        {
            BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
            BinaryNodeInterface<T> root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        }
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    } // end removeLargest

    @Override
    public Iterator<T> getInorderIterator() {
        return null;
    }

    class ReturnObject<T>{
        T entry;

        public ReturnObject(T entry) {
            this.entry = entry;
        }

        public T get() {
            return entry;
        }

        public void set(T entry) {
            this.entry = entry;
        }
    }
} // end BinarySearchTree