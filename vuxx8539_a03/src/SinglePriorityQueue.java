package cp213;

/**
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> the SinglePriorityQueue data type.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SinglePriorityQueues into the
     * current SinglePriorityQueue. Moves nodes only - does not move object or call
     * the high-level methods insert or remove. left and right SinglePriorityQueues
     * are empty when done. Nodes are moved alternately from left and right to this
     * SinglePriorityQueue. When finished all nodes must be in priority order from
     * front to rear.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * Do not assume that both right and left priority queues are of the same
     * length.
     *
     * @param left  The first SinglePriorityQueue to extract nodes from.
     * @param right The second SinglePriorityQueue to extract nodes from.
     */
    public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
	assert this.front == null : "May combine into an empty Priority Queue only";

        SingleNode<T> LeftNode = left.front;
        SingleNode<T> RightNode = right.front;

        while(LeftNode != null || RightNode != null){
            if(LeftNode != null){
                SingleNode<T> NextLeft = LeftNode.getNext();
                LeftNode.setNext(null);
                if(this.front == null){
                    this.front = LeftNode;
                    this.rear = LeftNode;
                    LeftNode.setNext(null);
                }else{
                    SingleNode<T> CurrentNode = this.front;
                    SingleNode<T> PreviousNode = null;

                    while(CurrentNode != null && CurrentNode.getObject().compareTo(LeftNode.getObject()) < 0){
                        PreviousNode = CurrentNode;
                        CurrentNode = CurrentNode.getNext();
                    }

                    if(PreviousNode == null){
                        CurrentNode.setNext(LeftNode);
                        this.front = CurrentNode;
                    }else{
                        PreviousNode.setNext(LeftNode);
                        LeftNode.setNext(CurrentNode);
                    }

                    if (CurrentNode == null) {
                        this.rear = LeftNode;
                    }

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
                    RightNode.setNext(null);
                }else{

                    SingleNode<T> CurrentNode = this.front;
                    SingleNode<T> PreviousNode = null;

                    while(CurrentNode != null && CurrentNode.getObject().compareTo(RightNode.getObject()) < 0){
                        PreviousNode = CurrentNode;
                        CurrentNode = CurrentNode.getNext();
                    }

                    if(PreviousNode == null){
                        CurrentNode.setNext(RightNode);
                        this.front = CurrentNode;
                    }else{
                        PreviousNode.setNext(RightNode);
                        RightNode.setNext(CurrentNode);
                    }

                    if (CurrentNode == null) {
                        this.rear = RightNode;
                    }

                }
                this.length++;
                RightNode = NextRight;
            }
        }   


        left.front = null;
        right.front = null;
        left.length = 0;
        right.length = 0;
	return;
    }

    /**
     * Adds object to the SinglePriorityQueue. Data is stored in priority order,
     * with highest priority object at the front of the SinglePriorityQueue, and
     * lowest at the rear. Priority is determined by simple comparison - lower
     * objects have higher priority. For example, 1 has a higher priority than 2
     * because 1 is a lower object than 2. (Think of the phrase, "We're number one!"
     * as an indication of priority.)
     *
     * When inserting object to the priority queue, the queue must remain sorted.
     * Hence you need to find the proper location of inserting object. use the head
     * pointer to go through the queue. e.g., use SingleNode&lt;T&gt; current =
     * this.head;
     *
     * use current = current.getNext(); to traverse the queue.
     *
     * To get access to the object inside a node of queue use current.getValue().
     *
     * @param object object to insert in sorted order in priority queue.
     */
    public void insert(final T object) {
    //Create a new node first
    SingleNode<T> NewNode = new SingleNode<T>(object, null);

    if (this.front == null){
        this.front = NewNode;
        this.rear = NewNode;
    }else{
        SingleNode<T> CurrentNode = this.front;
        SingleNode<T> PreviousNode = null;

        //Keep looping until we have found the correct position
        while(CurrentNode != null && CurrentNode.getObject().compareTo(NewNode.getObject()) < 0){
            PreviousNode = CurrentNode;
            CurrentNode = CurrentNode.getNext(); 
        }


        if(PreviousNode == null){
            NewNode.setNext(this.front);
            this.front = NewNode;
        }else{
            PreviousNode.setNext(NewNode);
            NewNode.setNext(CurrentNode);

            if(CurrentNode == null){
                this.rear = NewNode;
            }
        }


    }
    this.length++;

	return;
    }

    /**
     * Returns the highest priority object in the SinglePriorityQueue. Decrements
     * the count.
     *
     * @return the highest priority object currently in the SinglePriorityQueue.
     */
    public T remove() {
        if(this.front == null){
            return null;
        }

        T data = this.front.getObject();
        this.front = this.front.getNext();
        this.length -= 1;

	return data;
    }

    /**
     * Splits the contents of this SinglePriorityQueue into the higher and lower
     * SinglePriorityQueue. Moves nodes only - does not move object or call the
     * high-level methods insert or remove. this SinglePriorityQueue is empty when
     * done. Nodes with priority object higher than key are moved to the
     * SinglePriorityQueue higher. Nodes with a priority object lower than or equal
     * to key are moved to the SinglePriorityQueue lower.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * @param key    object to compare against node objects in SinglePriorityQueue
     * @param higher an initially empty SinglePriorityQueue queue that ends up with
     *               all objects with priority higher than key.
     * @param lower  an initially empty SinglePriorityQueue queue that ends up with
     *               all objects with priority lower than or equal to key.
     */
    public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {

	while(this.front != null){
        SingleNode<T> CurrentNode = this.front;
        this.front = this.front.getNext();
        //First we have to do a key compare to figure out which location the key is going to go in.
        if(CurrentNode.getObject().compareTo(key) < 0){
            //put the node in the higher stack
            if(higher.front == null){//Meaning if it's empty
                higher.front = CurrentNode;
                higher.rear = CurrentNode;    
                CurrentNode.setNext(null);
            }else{//so if there are already items in this higher stack then we have to traverse through to find the correct position to insert it
                SingleNode<T> HigherCurrent = higher.front;
                SingleNode<T> HigherPrevious = null;
                
                while(HigherCurrent != null && HigherCurrent.getObject().compareTo(CurrentNode.getObject()) < 0){
                    HigherPrevious = HigherCurrent;
                    HigherCurrent = HigherCurrent.getNext();
                }

                //if we haven't reached the end yet but we've found the correct spot then HigherPrevious will either be null or not null
                //and depending on if it's either null or not null then we set the position accordingly
                if (HigherPrevious == null){
                    //insert in the front
                    CurrentNode.setNext(higher.front);
                    higher.front = CurrentNode;
                }else {
                    //insert in between the two nodes
                    HigherPrevious.setNext(CurrentNode);
                    CurrentNode.setNext(HigherCurrent);
                }
                
                //if the current is null then it means we've reached the end
                if (HigherCurrent == null) {
                    higher.rear = CurrentNode;
                }
            }
            higher.length++;
        }else{


            if(lower.front == null){//Meaning if it's empty
                lower.front = CurrentNode;
                lower.rear = CurrentNode;    
                CurrentNode.setNext(null);
            }else{
                //so if there are already items in this lower stack then we have to traverse through to find the correct position to insert it
                SingleNode<T> lowerCurrent = lower.front;
                SingleNode<T> lowerPrevious = null;
                
                while(lowerCurrent != null && lowerCurrent.getObject().compareTo(CurrentNode.getObject()) < 0){
                    lowerPrevious = lowerCurrent;
                    lowerCurrent = lowerCurrent.getNext();
                }

                //if we haven't reached the end yet but we've found the correct spot then lowerPrevious will either be null or not null
                //and depending on if it's either null or not null then we set the position accordingly
                if (lowerPrevious == null){
                    //insert in the front
                    CurrentNode.setNext(lower.front);
                    lower.front = CurrentNode;
                }else {
                    //insert in between the two nodes
                    lowerPrevious.setNext(CurrentNode);
                    CurrentNode.setNext(lowerCurrent);
                }
                
                //if the current is null then it means we've reached the end
                if (lowerCurrent == null) {
                    lower.rear = CurrentNode;
                }
            
            }
        }

    }

    //empty the current stack yo
    this.front = null;
    this.length = 0;
	return;
    }
}
