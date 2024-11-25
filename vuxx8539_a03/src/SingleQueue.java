package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

    SingleNode<T> LeftNode = left.front;
    SingleNode<T> RightNode = right.front;
    

    while(LeftNode != null || RightNode != null ){
        if(LeftNode != null){
            SingleNode<T> NextLeft = LeftNode.getNext();
            LeftNode.setNext(null);
            if(this.front == null){
                this.front = LeftNode;
                this.rear = LeftNode;
            }else{
                this.rear.setNext(LeftNode);
                this.rear = LeftNode;
            }
            this.length++;
            LeftNode = NextLeft;
        }

        if(RightNode != null){
            SingleNode<T> NextRight = RightNode.getNext();
            RightNode.setNext(null);
            if(this.front == null){
                this.front = RightNode;
                this.rear = RightNode;
            }else{
                this.rear.setNext(RightNode);
                this.rear = RightNode;
            }
            this.length++;
            RightNode = NextRight;
        }
    }
    left.front = null;
    left.rear = null;
    right.front = null;
    right.rear = null;
    left.length = 0;
    right.length = 0;

	return;
    }

    /**
     * Adds object to the rear of the queue. Increments the queue length.
     *
     * @param object The object to added to the rear of the queue.
     */
    public void insert(final T object) {

    SingleNode<T> newNode = new SingleNode<T>(object, null);
    
    if (this.front == null){
        this.front = newNode;
        this.rear = this.front;
    }else{
        this.rear.setNext(newNode);
        this.rear = newNode;
    }
    
    this.length++;
	return;
    }

    /**
     * Returns the front object of the queue and removes that object from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The object at the front of the queue.
     */
    public T remove() {
        if (this.front == null){
            return null;
        }  
        T data = this.front.getObject();
        this.front = this.front.getNext();
        this.length--;
	return data;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {
    SingleNode<T> CurrentNode = this.front;
    boolean Isleft = true;
    
    while(CurrentNode != null){
        //save the next node in the source stack first
        SingleNode<T> NextNode = CurrentNode.getNext();

        CurrentNode.setNext(null);
        if(Isleft){
            if(left.front == null){
                left.front = CurrentNode;
                left.rear = CurrentNode;
            }else{
                left.rear.setNext(CurrentNode);
                left.rear = CurrentNode;
            }
            left.length++;
        }else{
            if(right.front == null){
                right.front = CurrentNode;
                right.rear = CurrentNode;
            }else{
                right.rear.setNext(CurrentNode);
                right.rear = CurrentNode;
            }
            right.length++;
        }
        //update the current node to move to the next one
        CurrentNode = NextNode;
        Isleft = !Isleft;
    }


    this.front = null;
    this.rear = null;
    this.length =0;
	return;
    }
}
