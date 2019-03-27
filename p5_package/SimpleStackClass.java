package p5_package;

/**
 *
 * @author carterwrobel
 */
public class SimpleStackClass extends java.lang.Object
{
    /**
     * Provides constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    /**
     * Stores current capacity of stack class
     */
    int capacity;
    /**
     * Stores current size of stack class
     */
    private int size;
    /**
     * Stores stack top index
     */
    private int stackTopIndex;
    /**
     * Integer array stores stack data
     */
    private int[] stackData;
    /**
     * Default constructor
     */
    public SimpleStackClass()
    {
        capacity = DEFAULT_CAPACITY;
        stackData = new int[capacity];
        stackTopIndex = -1;
        size = 0;
        
    }
    /**
     * Initialization constructor
     * @param capacitySetting - initial capacity of stackData class
     */
    public SimpleStackClass(int capacitySetting)
    {
        capacity = capacitySetting;
        stackData = new int[capacity];
        stackTopIndex = -1;
        size = 0;
    }
    /**
     * Copy constructor
     * @param - SimpleStackClass object to be copied
     */
    public SimpleStackClass(SimpleStackClass copied)
    {
        // Reset the values to the copied values
        capacity = copied.capacity;
        size = copied.size;
        stackData = new int[capacity];
        int index = 0;
        // Loop through and copy the values
        while(index < size)
        {
            stackData[index] = copied.stackData[index];
            index++;
        }
            
        
    }
    /**
     * Reports stack empty status
     * <p>
     * Note: Does not use if/else
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        // Returns if the stack is empty or not
        return stackTopIndex < 0;
    }
    /**
     * Checks for resize, then pushes value onto stack
     * <p>
     * Note: end of array is always the top of the stack;
     * index is incremented and then value is appended to array
     * @param newValue - Value to be pushed onto stack
     */
    public void push(int newValue)
    {
        // checks if the stack is empty
        isEmpty();
        // checks for resize
        checkForReSize();
        // if  not it adds the value
        stackTopIndex++;
        // sets the index to the value added
        stackData[stackTopIndex] = newValue;
        // increase the size
        size++;
    }
    /**
     * Provides peek at top of stack
     * @return value if successful, FAILED_ACCESS if not
     */
    public int pop()
    {
        // checks if the stack is empty and returns if it failed
         if(isEmpty())
         {
             return FAILED_ACCESS;
         }
         // if not it returns the top value
         else
         {
             int top = stackData[stackTopIndex];
             stackData[stackTopIndex] = 0;
             stackTopIndex--;
             return top;
         }
         
    }
    /**
     * provides peek at top of stack
     * <p>
     * @return value if successful, FAILED_ACCESS if not
     */
    public int peekTop()
    {
        // Checks if theres a value at the top
        if( stackData[ stackTopIndex ] == 0 )
        {
            return FAILED_ACCESS;
        }
        // return the value
        return stackData[stackTopIndex];
    }
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted;
     * returns false if no action is taken
     * @return 
     */
    private boolean checkForReSize()
    {
        if( capacity == size - 1)
        {
            // changes the capacity size and updates the stack
            capacity = capacity * 3;
            int[] newArray = stackData;
            stackData = new int[capacity];
            int index = 0;
            
            while( index < size )
            {
                stackData[index] = newArray[index];
                index++;
            }
            return true;
            
        }
        return false;
    }
    /**
     * Clears stack by setting size to zero and top index to negative one
     */
    public void clear()
    {
        // Reset values to default
        size = 0;
        stackData = new int[DEFAULT_CAPACITY];
        stackTopIndex = -1;   
    }   
    
}
