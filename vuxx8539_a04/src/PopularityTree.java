package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-10-15
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {

        if (node == null) {
            return null;
        }

        int compare = node.getData().compareTo(key);
        this.comparisons++;

        if (compare == 0) {
            node.getData().incrementCount();
            return node;
        //if the currente node doesn't match then decide to go either left or right
        } else if (compare > 0) {
            //Go left until we find the key
            TreeNode<T> foundNode = this.retrieveAux(node.getLeft(), key);

            //if the returned node is not null, then check if the count of the left child is less than the count of the found node
            if (foundNode != null) {
                if (node.getLeft().getData().getCount() < foundNode.getData().getCount()) {
                    node.setLeft(foundNode);
                }
                if (node.getData().getCount() < foundNode.getData().getCount()) {
                    TreeNode<T> newRoot = this.rotateRight(node);
                    if (node.getData().compareTo(this.root.getData()) == 0) {
                        this.root = newRoot;
                    }
                }
            }

            return foundNode;

        } else {
            TreeNode<T> foundNode = this.retrieveAux(node.getRight(), key);

            if (foundNode != null) {
                if (node.getRight().getData().getCount() < foundNode.getData().getCount()) {
                    node.setRight(foundNode);
                }
                if (node.getData().getCount() < foundNode.getData().getCount()) {
                    TreeNode<T> newRoot = this.rotateLeft(node);
                    if (node.getData().compareTo(this.root.getData()) == 0) {
                        this.root = newRoot;
                    }
                }
            }

            return foundNode;
        }
    }
    

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {
        TreeNode<T> newRoot = parent.getRight();
        parent.setRight(newRoot.getLeft());
        newRoot.setLeft(parent);
        parent.updateHeight();
        newRoot.updateHeight();
        return newRoot; 
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {
        TreeNode<T> newRoot = parent.getLeft();
        parent.setLeft(newRoot.getRight());
        newRoot.setRight(parent);
        parent.updateHeight();
        newRoot.updateHeight();
        return newRoot; 
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

        if (node == null) {
            node = new TreeNode<T>(data);
            this.size++;
        } else {
            // Compare the node data against the insert data.
            final int result = node.getData().compareTo(data);
            if (result > 0) {
            // General case - check the left subtree.
            node.setLeft(this.insertAux(node.getLeft(), data));
            } else if (result < 0) {
            // General case - check the right subtree.
            node.setRight(this.insertAux(node.getRight(), data));
            } else {
            }
        }
        node.updateHeight();
        return node;
        }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
    if (node == null) {
        return true; 
    }

    if (minNode != null && node.getData().compareTo(minNode.getData()) <= 0) {
        return false;
    }
    if (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0) {
        return false;
    }

    int leftCount = (node.getLeft() != null) ? node.getLeft().getData().getCount() : 0;
    int rightCount = (node.getRight() != null) ? node.getRight().getData().getCount() : 0;

    if (node.getData().getCount() < leftCount || node.getData().getCount() < rightCount) {
        return false; 
    }

    return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
}


    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedData<T> retrieve(CountedData<T> key) {

        // your code here
        TreeNode<T> rnode = retrieveAux(this.root, key);
        CountedData<T> r = rnode != null ? rnode.getData() : null;
        fixSillyheights(this.root);
        return r;
    }
    
    private void fixSillyheights(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        fixSillyheights(root.getLeft());
        fixSillyheights(root.getRight());
        root.updateHeight();

        return;
    }


}
