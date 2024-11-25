package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-10-15
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

    if (node == null) {
        return 0;
    }
    return nodeHeight(node.getLeft()) - nodeHeight(node.getRight());
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {
        int balanceFactor = balance(node);

        // Left heavy case
        if (balanceFactor > 1) {
            // Left-Right case (left child is right heavy)
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRight(node);
        }
        // Right heavy case
        else if (balanceFactor < -1) {
            // Right-Left case (right child is left heavy)
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            node = rotateLeft(node);
        }
        return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
        TreeNode<T> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {
        TreeNode<T> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
        if (node == null) {
            return new TreeNode<>(data);
        }

        int comparison = data.compareTo(node.getData());
        if (comparison < 0) {
            node.setLeft(insertAux(node.getLeft(), data));
        } else if (comparison > 0) {
            node.setRight(insertAux(node.getRight(), data));
        } else {
            // If equal, just increment count
            node.getData().incrementCount();
        }

        node.updateHeight();
        return rebalance(node);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
        if (node == null) {
            return true;
        }

        if ((minNode != null && node.getData().compareTo(minNode.getData()) <= 0) ||
            (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0)) {
            return false;
        }

        if (Math.abs(balance(node)) > 1) {
            return false;
        }

        return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedData<T> data) {
        if (node == null) {
            return null;
        }
    
        final int result = data.compareTo(node.getData());
    
        if (result < 0) {
            node.setLeft(removeAux(node.getLeft(), data));
        } else if (result > 0) {
            node.setRight(removeAux(node.getRight(), data));
        } else {
            if (node.getLeft() == null) {
            return node.getRight();
            } else if (node.getRight() == null) {
            return node.getLeft();
            } else {
            TreeNode<T> uno = node;
            TreeNode<T> dos = node.getRight();
    
            while (dos.getLeft() != null) {
                uno = dos;
                dos = dos.getLeft();
            }
    
            if (uno != node) {
                uno.setLeft(dos.getRight());
                dos.setRight(node.getRight());
            }
    
            dos.setLeft(node.getLeft());
            node = dos;
            }
        }
    
        node.updateHeight();
        return rebalance(node);
    
        }

}
