package p9_package;

/**
 * Array-based generic min heap class used as a priority queue for generic data
 * @author carterwrobel
 */
public class GenericHeapClass<GenericData extends java.lang.Comparable<GenericData>>
        extends java.lang.Object
{
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Array for heap
     */
    private java.lang.Object[] heapArray;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    
    /**
     * Default constructor sets up array management conditions and default display flag setting
     */
    public GenericHeapClass()
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        heapArray = new Object[arrayCapacity];
    } 
    
    /**
     * Copy constructor copies array and array management conditions and default display flag setting
     * <p>
     * @param copied - GenericHeapClass object to be copied
     */
    public GenericHeapClass(GenericHeapClass<GenericData> copied)
    {
        // Copy the array capacity
        this.arrayCapacity = copied.arrayCapacity;
        // Set the new array to the new size
        this.heapArray = new Object[copied.arrayCapacity];
        // Update the array size
        this.arraySize = copied.arraySize;
        // Loop to Copy the old array to the new one.
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            this.heapArray[index] = copied.heapArray[index];
        }
    }
    
    /**
     * Accepts GenericData item and adds it to heap 
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data addition
     * <p>
     * @param newItem - GenericData item to be added 
     */
    public void addItem(GenericData newItem)
    {
        checkForResize();
        heapArray[arraySize] = (Object) newItem;
        bubbleUpArrayHeap( arraySize );
        arraySize++;
    }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap after new data addition
     * <p>
     * @param currentIndex - index of current item being assessed, and moved up as needed 
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
        int parentIndex = ( ( currentIndex - 1) / 2 );
        GenericData child;
        GenericData parent;
        child = ( GenericData ) heapArray[ currentIndex ];
        parent = ( GenericData ) heapArray[ parentIndex ];
        Object tempVal = heapArray[ currentIndex ];
         if( parent != null );
            {
                if( parent.compareTo( child ) > 0 )
                    {
                        heapArray[ currentIndex ] = heapArray[ parentIndex ];
                        heapArray[ parentIndex ] = tempVal;
                        bubbleUpArrayHeap( parentIndex );
                    }
            }
    }
    
    /**
     * Automatic resize operation used prior to any new data addition in the heap 
     * <p>
     * Tests for full heap array, and resizes to twice the current capacity as required
     */
    private void checkForResize()
    {
        if(arraySize == arrayCapacity)
        {
            // Double the array capacity and sets to new array
            Object[] newArray = new Object[this.arrayCapacity * 3];
            // Loop through old array and copy into new array.
            int index;
            for(index = 0; index < this.arrayCapacity; index++)
            {
                // Update the value
                newArray[index] = this.heapArray[index];
            }
            // Set the new array to this.setArray to use new array
            this.heapArray = newArray;
        }
    }
    
    /**
     * Tests for empty heap
     * <p>
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        // Tests if the size of the array is 0, if true it is empty
        return ( arraySize == 0 );
    }
    
    /**
     * Removes GenericData item from top of min heap, thus being the operation with the lowest priority value 
     * <p>
     *  Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data removal
     * <p>
     * @return GenericData item removed
     */
    public GenericData removeItem()
    {
        // save item tbeing removed to return
        GenericData removedItem = (GenericData) heapArray[0];
        // swap with last item
        heapArray[0] = heapArray[arraySize - 1];
        // set the end to null
        heapArray[arraySize - 1] = null;
        // decrease the array size
        arraySize--;
        // Call the trickle down helper
        trickleDownArrayHeap(0);
        // return the item removed
        return removedItem;
    }
    
    /**
     * Utility method to set the display flag for displaying internal operations of the heap bubble and trickle operations
     * <p>
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
    {
        setState = true;
    }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        
        // loop through the array to get values
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            System.out.print( heapArray[ index ].toString() + " - "); 
            if(index == arraySize - 1)
            {
                System.out.print("\n");
            }
        }  
    }
    
    /**
     * Recursive operation to reset data in the correct order for the min heap after data removal
     * <p>
     * @param currentIndex - index of current item being assessed, and moved down as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
       int rightChildIndex = ( currentIndex * 2 ) + 2;
       int leftChildIndex = ( currentIndex * 2 ) + 1;
       
       // check if at the end
       if( rightChildIndex < arraySize )
       {
            
            GenericData rightChild, leftChild, current;
       
            leftChild = (GenericData) heapArray[leftChildIndex];
            
            rightChild = (GenericData) heapArray[rightChildIndex];
            
            current = (GenericData) heapArray[currentIndex];
            
            // check if there are two children 
            if( leftChild != null && rightChild != null )
            {
                // check if the left if bigger
                if( leftChild.compareTo( rightChild ) > 0 )
                {
                    // check if smaller than the current index
                    if( current.compareTo( leftChild ) > 0 )
                    {
                        GenericData tempData = (GenericData) heapArray[ currentIndex ];
                        heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
                        heapArray[ leftChildIndex ] = tempData;
                        trickleDownArrayHeap( leftChildIndex );
                    }
                }
                // if the right is bigger
                else
                {
                    if( current.compareTo( rightChild ) > 0 )
                    {
                        GenericData tempData = (GenericData) heapArray[ currentIndex ];
                        heapArray[ currentIndex ] = heapArray[ rightChildIndex ];
                        heapArray[ rightChildIndex ] = tempData;
                        trickleDownArrayHeap( rightChildIndex );
                    }                    
                }
            }
            // check if there is just a left child
            else if( leftChild != null && rightChild == null )
            {
                    if( current.compareTo( leftChild ) > 0 )
                    {
                        GenericData tempData = (GenericData) heapArray[ currentIndex ];
                        heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
                        heapArray[ leftChildIndex ] = tempData;
                        trickleDownArrayHeap( leftChildIndex );
                    }
            }
            
       }
       
   }
            
}
