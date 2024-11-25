package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> object contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2024-09-01
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The object to look for.
     * @return A pointer to the node previous to the node containing key.
     */
	private SingleNode<T> linearSearch(final T key) {
		SingleNode<T> current = this.front;
		if (current == null) {
			return null;
		}


		while (current.getNext() != null) {
			if (current.getNext().getObject().compareTo(key) == 0) {
				return current;
			}
			current = current.getNext();
		}

		return current;
	}
    

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The object to append.
     */
    public void append(final T data) {
    SingleNode<T> NewNode = new SingleNode<T>(data, null);
	
    if(this.front == null){
        this.front = NewNode;
        this.rear = NewNode;
    }else{
        this.rear.setNext(NewNode);
        this.rear = NewNode;
    }
    this.length++;

	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each object formerly present in this SingleList. The first occurrence of
     * each object is preserved.
     */
    public void clean() {
        if (this.front == null){
            return;
        }

        SingleNode<T> current = this.front;

        while(current!=null){
            SingleNode<T> Tracker = current;
            while (Tracker.getNext() != null){
                if(current.getObject().equals(Tracker.getNext().getObject())){
                    //remove the center node by essentially setting the next node to the next-next node instead.
                    Tracker.setNext(Tracker.getNext().getNext());
                }else{
                    Tracker = Tracker.getNext();
                }
            }
            current = current.getNext();
        }
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {
        this.front = null;
        SingleNode<T> lastNode = null;
        SingleNode<T> leftCurrent = left.front;
        SingleNode<T> rightCurrent = right.front;
    
        while (leftCurrent != null || rightCurrent != null) {
            if (leftCurrent != null) {
                if (this.front == null) {
                    this.front = leftCurrent;
                } else {
                    lastNode.setNext(leftCurrent);
                }
                lastNode = leftCurrent;
                leftCurrent = leftCurrent.getNext();
                this.length++;
            }
    
            if (rightCurrent != null) {
                if (this.front == null) {
                    this.front = rightCurrent;
                } else {
                    lastNode.setNext(rightCurrent);
                }
                lastNode = rightCurrent;
                rightCurrent = rightCurrent.getNext();
                this.length++;
            }
        }
    
        left.front = null;
        right.front = null;
        left.length = 0;
        right.length = 0;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key object to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
    SingleNode<T> Current = this.front;

    while(Current !=null){
        if (key.equals(Current.getObject())){
            return true;
        }
        Current = Current.getNext();
    }
	return false;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The object to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {
    if (key == null){
        return 0;
    }

	int count = 0;
    SingleNode<T> current = this.front;

    while(current != null){
        if(current.getObject().equals(key)){
            count++;
        }
        current = current.getNext();
    }

	return count;
    }

    /**
     * Finds and returns the object in list that matches key.
     *
     * @param key The object to search for.
     * @return The object that matches key, null otherwise.
     */
    public T find(final T key) {
    if (key == null){
        return null;
    }
    SingleNode<T> Current = this.front;

    while(Current != null){
        if(key.equals(Current.getObject())){
            return Current.getObject();
        }
        Current = Current.getNext();
    }

	return null;
    }

    /**
     * Get the nth object in this SingleList.
     *
     * @param n The index of the object to return.
     * @return The nth object in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.getLength()) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        
        SingleNode<T> Current = this.front;
        for (int i = 0 ; i < n; ++i){
            Current = Current.getNext();
        }

	return Current.getObject();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same objects in the same order
     *         as source, false otherwise.
     */
    public boolean equals(final SingleList<T> source) {
        if (source == null) {
            return false;
        }
    
        SingleNode<T> currentThis = this.front;
        SingleNode<T> currentSource = source.front;
    
        while (currentThis != null && currentSource != null) {
            if (!currentThis.getObject().equals(currentSource.getObject())) {
                return false;
            }
            currentThis = currentThis.getNext();
            currentSource = currentSource.getNext();
        }
    
        return currentThis == null && currentSource == null;
    }

    /**
     * Finds the first location of a object by key in this SingleList.
     *
     * @param key The object to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {
    if (key == null){
        return -1;
    }

    int index = 0;
    SingleNode<T> current = this.front;

    while(current != null){
        if(key.equals(current.getObject())){
            return index;
        }
        index++;
        current = current.getNext();
    }

	return -1;
    }

    /**
     * Inserts object into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i    The index to insert the new data at.
     * @param data The new object to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	SingleNode<T> NewNode = new SingleNode<T>(data, null);

    if (i <= 0){
        NewNode.setNext(this.front);
        this.front = NewNode;
        if(length == 0){
            //set up the rear node to point to the same as well
            this.rear = NewNode;
        }
    }else if(i > this.length){
        if(this.rear != null){
            this.rear.setNext(NewNode);
        }else{
            this.front = NewNode;
        }
        this.rear = NewNode;
    }else{
        //if it's neither the front or back then loop through until we get to the spot
        SingleNode<T> current = this.front;
        for (int index = 0; index < i - 1; index++) {
            current = current.getNext(); 
        }
        NewNode.setNext(current.getNext()); // Link new node to the next node
        current.setNext(NewNode);      // Link the previous node to the new node
    }

    length++; // Increment the length of the list

	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then objects from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {
    
        SingleNode<T> currentLeft = left.front;
    
        while (currentLeft != null) {
            SingleNode<T> currentRight = right.front;
            boolean found = false;
    
            while (currentRight != null) {
                if (currentLeft.getObject().equals(currentRight.getObject())) {
                    found = true;
                    break; 
                }
                currentRight = currentRight.getNext();
            }
    
            if (found) {
                SingleNode<T> newNode = new SingleNode<T>(currentLeft.getObject(), null);  
                newNode.setNext(this.front);
                this.front = newNode; 
            }
    
            currentLeft = currentLeft.getNext(); 
        }
    
        this.reverse();
    }
    

    /**
     * Finds the maximum object in this SingleList.
     *
     * @return The maximum object.
     */
    public T max() {
    SingleNode<T> max = this.front;
    SingleNode<T> current = this.front;
    
    while(current != null){
        if(current.getObject().compareTo(max.getObject()) > 0){
            max = current;
        }
        current = current.getNext();
    }

	return max.getObject();
    }

    /**
     * Finds the minimum object in this SingleList.
     *
     * @return The minimum object.
     */
    public T min() {
    SingleNode<T> min = this.front;
    SingleNode<T> current = this.front;
    
    while(current != null){
        if(current.getObject().compareTo(min.getObject()) < 0){
            min = current;
        }
        current = current.getNext();
    }

    return min.getObject();
    }

    /**
     * Inserts object into the front of this SingleList.
     *
     * @param data The object to insert into the front of this SingleList.
     */
    public void prepend(final T data) {
        
    SingleNode<T> NewNode = new SingleNode<T>(data, null);
    if(this.front == null){
        this.front = NewNode;
        this.rear = NewNode;
    }else{
        NewNode.setNext(this.front);
        this.front = NewNode;
    }

    this.length++;
	return;
    }

    /**
     * Finds, removes, and returns the object in this SingleList that matches key.
     *
     * @param key The object to search for.
     * @return The object matching key, null otherwise.
     */
    public T remove(final T key) {
        if (key == null) {
            return null;
        }
    
        SingleNode<T> current = this.front;
        SingleNode<T> previous = null;
        T saved = null;
    
        while (current != null) {
            if (current.getObject().compareTo(key) == 0) {
                saved = current.getObject();
                if (previous == null) {
                    this.front = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                break;
            }     
    
            previous = current;
            current = current.getNext();
        }
    
        return saved;
    }

    /**
     * Removes the object at the front of this SingleList.
     *
     * @return The object at the front of this SingleList.
     */
    public T removeFront() {
    if (this.front == null){
        return null;
    }
    SingleNode<T> returnnode = this.front;
	this.front = this.front.getNext();
    this.length--;

	return returnnode.getObject();
    }

    /**
     * Finds and removes all objects in this SingleList that match key.
     *
     * @param key The object to search for.
     */
    public void removeMany(final T key) {
    if (key == null){
        return;
    }

    SingleNode<T> CurrentNode = this.front;
    SingleNode<T> PreviousNode = null;

    while(CurrentNode != null){ 
        if(CurrentNode.getObject().compareTo(key) == 0){
            // check if we're in the front or not
            if(PreviousNode == null){
                this.front = CurrentNode.getNext();
            }else{
                //if we're not in the front then set the previous node to link to the next-next node
                PreviousNode.setNext(CurrentNode.getNext());
            }
        }else{
            //if there isn't a match then move the previous one forwards 
            PreviousNode = CurrentNode;
        }
        CurrentNode = CurrentNode.getNext();
    }

	return;
    }

    /**
     * Reverses the order of the objects in this SingleList.
     */
    public void reverse() {
    SingleNode<T> previous = null;
    SingleNode<T> current = this.front;
    SingleNode<T> next = null;

	while(current!=null){
        next = current.getNext();
        current.setNext(previous); 
        previous = current; 
        current = next;  
    }

    this.front = previous;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * object than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {
        int length = this.getLength();
        int splitPoint = (length + 1) / 2; 

        SingleNode<T> current = this.front;
        SingleNode<T> lastLeftNode = left.front;
        SingleNode<T> lastRightNode = right.front; 

        for (int i = 0; i < splitPoint; i++) {
            SingleNode<T> currentNext = current.getNext();
            current.setNext(null);
            if (current != null) {
                if (lastLeftNode == null) {
                    left.front = current; 
                } else {
                    lastLeftNode.setNext(current); 
                }
                lastLeftNode = current;
                current = currentNext;
                left.length++;
            }
        }
    
        //now we use a while to dump the rest into the other stack
        while (current != null) {
            SingleNode<T> currentNext = current.getNext();
            current.setNext(null);
            if (lastRightNode == null) {
                right.front = current; 
            } else {
                lastRightNode.setNext(current); 
            }
            lastRightNode = current;
            current = currentNext;
            right.length++;
        }
    
        this.front = null; 
        this.length = 0; 
        this.rear = null; 
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {
        boolean isLeft = true;
        SingleNode<T> current = this.front;
        SingleNode<T> lastLeftNode = null;
        SingleNode<T> lastRightNode = null;
    
        while (current != null) {
            SingleNode<T> currentNext = current.getNext();
            current.setNext(null);
            if (isLeft) {
                if (lastLeftNode == null) {
                    left.front = current;
                } else {
                    lastLeftNode.setNext(current);
                }
                lastLeftNode = current;
                left.length++;
            } else {
                if (lastRightNode == null) {
                    right.front = current;
                } else {
                    lastRightNode.setNext(current);
                }
                lastRightNode = current;
                right.length++;
            }
    
            current = currentNext;
            isLeft = !isLeft;
        }
    
        this.front = null;
        this.length = 0;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies object
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then objects from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {    
        SingleNode<T> current = left.front;
        while (current != null) {
            SingleNode<T> newNode = new SingleNode<T>(current.getObject(), null);
            if (this.front == null) {
                this.front = newNode;
            } else {
                SingleNode<T> last = this.front;
                while (last.getNext() != null) {
                    last = last.getNext();
                }
                last.setNext(newNode);
            }
            current = current.getNext();
        }
    
        current = right.front;
        while (current != null) {
            SingleNode<T> newNode = new SingleNode<T>(current.getObject(), null);
            if (this.front == null) {
                this.front = newNode;
            } else {
                SingleNode<T> last = this.front;
                while (last.getNext() != null) {
                    last = last.getNext();
                }
                last.setNext(newNode);
            }
            current = current.getNext();
        }
    }
}
