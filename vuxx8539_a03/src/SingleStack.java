package cp213;

/**
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleStacks into the current
     * SingleStack. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods pop or push. left and right SingleStacks are empty
     * when done. Nodes are moved alternately from left and right to this
     * SingleStack.
     *
     * You have two source stacks named left and right. Move all nodes from these
     * two stacks to the current stack. It does not make a difference if the current
     * stack is empty or not, just get nodes from the right and left stacks and add
     * them to the current stack. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left stacks are of the same length.
     *
     * @param left  The first SingleStack to extract nodes from.
     * @param right The second SingleStack to extract nodes from.
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {
        SingleNode<T> leftNode = left.front;
        SingleNode<T> rightNode = right.front;

        while (leftNode != null || rightNode != null){
            if(leftNode != null){
                SingleNode<T> nextNode = leftNode.getNext();
                leftNode.setNext(null);
                if (this.front == null){
                    this.front = leftNode;
                }else{
                    leftNode.setNext(this.front);
                    this.front = leftNode;
                }
                this.length++;
                leftNode = nextNode;
            }
            if(rightNode != null){
                SingleNode<T> nextNode = rightNode.getNext();
                rightNode.setNext(null);
                if (this.front == null){
                    this.front = rightNode;
                }else{
                    rightNode.setNext(this.front);
                    this.front = rightNode;
                }
                this.length++;
                rightNode = nextNode;
            }

        }

        left.front = null;
        left.length = 0;
        right.front = null;
        right.length = 0;
        return;
    }

    /**
     * Returns the top object of the stack and removes that object from the stack.
     * The next node in the stack becomes the new top node. Decrements the stack
     * length.
     *
     * @return The object at the top of the stack.
     */
    public T pop() {
    //check if it's empty
    if (this.front == null) {
        return null;
    }

    T data = this.front.getObject();
    this.front = this.front.getNext();  
    this.length--;

	return data;
    }

    /**
     * Adds data to the top of the stack. Increments the stack length.
     *
     * @param object The object to add to the top of the stack.
     */
    public void push(final T object) {
    SingleNode<T> newNode = new SingleNode<T>(object, null);
    if (this.front == null){
        this.front = newNode;
    }else{
        //if the stack already has items then we set the new node next pointer to point the the front of the stack
        newNode.setNext(this.front);
        //then we set the the front node to this new node which essentially makes the new node the front of the stack
        this.front = newNode;
    }
    //obviously we have to increment the length
    this.length++;
	return;
    }

    /**
     * Splits the contents of the current SingleStack into the left and right
     * SingleStacks. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleStack is empty when done. Nodes are
     * moved alternately from this SingleStack to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleStack to move nodes to.
     * @param right The second SingleStack to move nodes to.
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {
        SingleNode<T> currentNode = this.front;
        boolean isLeft = true; 
    
        while (currentNode != null) {
            //save the next node for later
            SingleNode<T> nextNode = currentNode.getNext();
            currentNode.setNext(null);
            if (isLeft) {
                if (left.front == null) {
                    left.front = currentNode;
                } else {        
                    currentNode.setNext(left.front);
                    left.front = currentNode; 
                }
                left.length++;
            } else {
                if (right.front == null) {
                    right.front = currentNode;
                } else {          
                    currentNode.setNext(right.front);
                    right.front = currentNode; 
                }
                right.length++;
            }

            currentNode = nextNode;
            isLeft = !isLeft;
        }
    
        this.front = null; 
        this.length = 0;   
    }
    
    
    
}