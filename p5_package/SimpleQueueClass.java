package p5_package;

/**
 *
 * @author carterwrobel
 */
public class SimpleQueueClass
{
    /**
     * Stores current capacity of stack class
     */
    private int capacity;
    
    /**
     * Provides constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    
    /**
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    
    /**
     * Stores queue head index
     */
    private int headIndex;
    
    /**
     * Integer array stores queue data
     */
    private int[] queueData;
    
    /**
     * Stores current size of stack class
     */
    private int size;
    
    /**
     * Stores queue tail index
     */
    private int tailIndex;
    
    /**
     * Default constructor
     */
    public SimpleQueueClass()
    {
        capacity = DEFAULT_CAPACITY;
        queueData = new int[capacity];
        size = 0;
        headIndex = 0;
        tailIndex = -1;
    }
    
    /**
     * Initialization constructor
     * @param capacitySetting - initial capacity of stackData class
     */
    public SimpleQueueClass(int capacitySetting)
    {
        capacity = capacitySetting;
        queueData = new int[capacity];
        size = 0;
        headIndex = 0;
        tailIndex = -1;
    }
    
    /**
     * Copy constructor
     * @param copied - SimpleStackClass object to be copied
     */
    public SimpleQueueClass(SimpleQueueClass copied)
    {
        capacity = copied.capacity;
        headIndex = copied.headIndex;
        tailIndex = copied.tailIndex;
        size = copied.size;
        queueData = new int[capacity];
        int index = 0;
        while( index < size)
        {
            queueData[index] = copied.queueData[index];
            index++;
        }
        // NEED THIS
        headIndex = 0;
        tailIndex = size - 1;
    }
    
    /**
     * Checks for resize, then enqueues value
     * <p>
     * Note: Updates tail index, then appends value to array at tail index
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
    {
        // checks if  empty
        isEmpty();
        // checksfor resize
        checkForReSize();
        // updates the tail
        updateTailIndex();
        // store the value
        queueData[tailIndex] = newValue;
        size++;
    }
    
    /**
     * Removes and returns value from front of queue
     * <p>
     * Note: Acquires data from head of queue, then updates head index
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        // stores the value being removed
        int valueRemoved = queueData[headIndex];
       // updates the head
        updateHeadIndex();
        // decreases the size
        size--;
        // returns the value removed
        return valueRemoved;
    }
    
    /**
     * Provides peek at front of queue
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        // checks if its null
        if( queueData == null)
        {
            // returns faild if empty
            return FAILED_ACCESS;
        }
        return queueData[headIndex];
    }
    
    /**
     * Reports queue empty state
     * <p>
     * Note: Does not use if/else
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        // Returns if the queue is empty
        return headIndex < 0;
    }
    
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted;
     * returns false if no action is taken
     * @return success of operation
     */
    private boolean checkForReSize()
    {
        // checks the size of the queue
        if( size == capacity )
        {
            int copySize = ( tailIndex + 1 ) - headIndex;
            int[] newArray = new int[ capacity * 3 ];    
            this.clear();
            int index = 0;
            // copys all values into new array
            while( index < copySize ) 
            {
                newArray[ index ] = queueData[ headIndex + index ];
                index++;
            }
            queueData = newArray;
        }
        return false;
    }
    
    /**
     * Clears the queue by setting the size to zero,
     * the tail index to -1 and the head index to zero
     */
    public void clear()
    {
        // resets to default values
        headIndex = 0;
        tailIndex = -1;
        size = 0;
    }
    
    /**
     * Updates queue head index to wrap around array as needed
     * <p>
     * Note: Does not use if/else
     */
    private void updateHeadIndex()
    {
        // updates the head index
        headIndex = (headIndex + 1)  % (capacity - 1);
    }
    
    /**
     * Updates queue tail index to wrap around array as needed
     * <p>
     * Note: Does not use if/else
     */
    private void updateTailIndex()
    {
        // updates the tail index
        tailIndex = (tailIndex + 1)  % (capacity - 1);
    }   
    
}
