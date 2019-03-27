package p2_package;

/**
 * Class for managing sets of GenericData that extend Comparable
 * @author carterwrobel
 */
public class GenericSetClass<GenericData extends
        java.lang.Comparable<GenericData>>
{
    /**
     * Constant with default array capacity
     */
    static int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Constant used for base two exponential calculations
     */
    private static final int BASE_TWO = 2;
    /**
     * Object array for data
     */
    private java.lang.Object[] genericSetArray;
    /**
     * GenericSetClass array for management of power sets
     */
    private GenericSetClass<GenericData>[] genericPwrSetArray;
    /**
     * Capacity of array
     */
    private int arrayCapacity;
    /**
     * number of values in array
     */
    private int arraySize;

    /**
     * Default Constructor
     * <p>
     * Initializes set array but sets power set array to null
    */
    public GenericSetClass() 
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        genericSetArray = new Object[arrayCapacity];
    }
    
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array but sets power set array to null
     * <p>
     * @param initialCapacity - Integer that specifies array capacity
    */
    public GenericSetClass(int initialCapacity)
    {
        // Set array capacity to the specified initial capacity
        arrayCapacity = initialCapacity;
        // Set the array to the capacity 
        genericSetArray = new Object[arrayCapacity];
        // Set the Array size to 0
        arraySize = 0;
    }
    
    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update of set class array
     * <p>
     * @param copied - SetClass object to be copied
    */
    public GenericSetClass(GenericSetClass<GenericData> copied)
    {
        // Copy the array capacity
        this.arrayCapacity = copied.arrayCapacity;
        // Set the new array to the new size
        this.genericSetArray = new Object[copied.arrayCapacity];
        // Update the array size
        this.arraySize = copied.arraySize;
        // Loop to Copy the old array to the new one.
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            this.genericSetArray[index] = copied.genericSetArray[index];
        }
    }
    
    /**
     * Adds integer to set
     * <p>
     * Increases capacity using checkForResize if array is full
     * <p>
     * Does not allow duplicate values in set
     * <p>
     * @param item - GenericData value to be added to set
    */
    public void addItem(GenericData item)
    {
        // Check if array needs to be resized
        checkForResize();
            // Check if array already contains element
            if(!hasElement(item))
            {
                // Adds the item to the array
                this.genericSetArray[arraySize] = item;
                // Update the array size
                this.arraySize += 1;
            }
    }
    /**
     * Removes element if it is found in set
     * <p>
     * All values equal to given value will be removed
     * in case there is more than one
     * <p>
     * @param valToRemove - GenericData value to be removed
     * <p>
     * @return Boolean result of operation success
     */
    public boolean removeItem(GenericData valToRemove)
    {
        // check if the array has the element
        if(hasElement(valToRemove))
        {
            int index;
            int indexOfValue = 0;
            // Loop through the array to find the index of the value to remove
            for(index = 0; index < this.arraySize; index++)
            {
                if(this.genericSetArray[index] == valToRemove)
                {
                    indexOfValue = index;
                }
            }
            // Once you have the index, call removeAtIndex to remove it
            removeAtIndex(indexOfValue);
        }
        return false;
    }
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - integer index of element value to remove
     */
    private void removeAtIndex(int indexToRemove)
    {
        // Initialize index variable
        int index;
        for(index = indexToRemove; index < this.arraySize; index++)
        {
            // Sets the next index value to the previous one
            this.genericSetArray[index] = this.genericSetArray[index + 1];
        }
        // Decreases the array size
        this.arraySize -= 1;
    }
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - GenericSetClass with which intersection is found
     * <p>
     * @return GenericSetClass object with intersection of two sets
     */
    @SuppressWarnings("unchecked")
    public GenericSetClass<GenericData> findIntersection
        (GenericSetClass<GenericData> other)
    {
        // Create a new object
       GenericSetClass<GenericData> intersectionSet = new GenericSetClass();
       // loop through This array and the other array
       int index;
       for(index = 0; index < arraySize; index++)
       {
           // create a variable for the value added
           GenericData valueAdded;
           valueAdded =(GenericData) this.genericSetArray[index];
           // Check if the value added is in the other array
           if(other.hasElement(valueAdded))
           {
               // Add the item to the new set
               intersectionSet.addItem(valueAdded);
           }
       }
       // return the new set of items
       return intersectionSet;
    }
    /**
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return SetClass object with union of two sets
     */
    @SuppressWarnings("unchecked")
    public GenericSetClass<GenericData> findUnion
        (GenericSetClass<GenericData> other)
    {
        // Creates new array
        GenericSetClass<GenericData> unionSet = new GenericSetClass();
        // Take the other set and copy it into the new set class
        int index;
        for(index = 0; index < other.arraySize; index++)
        {
            GenericData element;
            element = (GenericData) other.genericSetArray[index];
            unionSet.addItem(element);
        }
        // Take each value from the other set and check if it is in
        // the union set, if it isn't then add it.
        for(index = 0; index < this.arraySize; index++)
        {
            GenericData newElement;
            newElement = (GenericData) this.genericSetArray[index];
            if(!unionSet.hasElement(newElement))
            {
                unionSet.addItem(newElement);
            }
        }
        // return the new set
        return unionSet;
    }
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting with THIS set
     * <p>
     * @param other - SetClass object from which THIS SetClass 
     * items will be removed
     * <p>
     * @return SetClass object with data as specified
     */
    @SuppressWarnings("unchecked")
    public GenericSetClass<GenericData> findRelativeComplementOfThisSetIn
        (GenericSetClass<GenericData> other)
    {
        // Crete new set
        GenericSetClass<GenericData> relativeComplementSet =
                new GenericSetClass();
        // Take the other set and copy it into the new set class
        int index;
        // Loop through THIS array
        for(index = 0; index < this.arraySize; index++)
        {
            // Set the value added to a variable
            GenericData newElement;
            newElement = (GenericData) this.genericSetArray[index];
            // If the other set does not have the element, add it.
            if(!other.hasElement(newElement))
            {
                relativeComplementSet.addItem(newElement);
            }
        }
        // return the new set
        return relativeComplementSet;
    }
    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - Integer element to be found in set
     * <p>
     * @return Boolean result of test
     */
    @SuppressWarnings("unchecked")
    public boolean hasElement(GenericData testElement)
    {
        // Initialize index for loop
        int index;
        // Loop through array
        for(index = 0; index < arraySize; index++)
        {
            // If the index of the array equals the test element,
            // then it contains the element.
            int checkVal = ((GenericData) genericSetArray[index])
                    .compareTo(testElement);
            if(checkVal ==0)
                {
                return true;
                }
        }
        // If the element is not in the array then return false.
        return false;
    }
    /**
     * Tests to indicate if set is subclass another given set
     * <p>
     * @param other - SetClass object to be tested if THIS set
     * is a subset of it
     * <p>
     * @return Boolean result of test
     */
    @SuppressWarnings("unchecked")
    public boolean isSubsetOf(GenericSetClass<GenericData> other)
    {
        // Create element count
        int elementCount = 0;
        // Loop through this array
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            // set the number to the index variable
            GenericData elementCheck;
            elementCheck = (GenericData) this.genericSetArray[index];
            // if other has the element then add the element count
            if(other.hasElement(elementCheck))
            {
                elementCount += 1;
            }
        }
        // return true or false
        return (elementCount == this.arraySize);
    }
    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled;
     * otherwise, no change
     * <p>
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
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
                newArray[index] = this.genericSetArray[index];
            }
            // Set the new array to this.setArray to use new array
            this.genericSetArray = newArray;
            return true;
        }
        else
            return false;
    }
    /**
     * Provides list of set array elements as comma-delimited string
     * <p>
     * @return - Returns array in string form
     * <p>
     * @Overrides toString in class java.lang.Object
     */
    @Override
    @SuppressWarnings("unchecked")
    public java.lang.String toString() 
    {
        // Creat an empty string
        String outputString  = "";
        // loop through the array to get values
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            GenericData gd;
            gd = (GenericData) genericSetArray[index];
            outputString += gd.toString();
            // check if you are at the end of the array to not have an extra ,
            if(index != this.arraySize - 1)
            {
                outputString += ", ";
            }
        }
        // return string of array
        return outputString;
    }

    /**
     * Description: Sorts elements using the bubble sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of 
     * the given data set; the compareTo method decides the key 
     * and the order resulting
     * <p>
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public java.lang.String runBubbleSort()
    {
     int index;
     boolean swap = true;
     // While the swap is true
     while(swap)
     {
         // Set swap to false
         swap = false;
         // Loop through array to check values
         for(index = 0; index < arraySize - 1; index++)
         {
             // set value for being swapped
             int valueSwap = ((GenericData) genericSetArray[index])
                     .compareTo((GenericData) genericSetArray[index +1]);
             // If the it is bigger than the other value
             if(valueSwap > 0)
             {
                 // Swap the values
                 swapValues(genericSetArray, index, index + 1);
                 // Swap is true
                 swap = true;
             }
         }
     }
     // Return string
     return this.toString();
    }
    /**
     * Description: Sorts elements using the selection sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of
     * the given data set; the compareTo method decides the key
     * and the order resulting
     * <p>
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public java.lang.String runSelectionSort()
    {
     // initializes indexes
     int outsideIndex, insideIndex;
     // initialize minimum to check
     GenericData minimum;
     // Loop through outer loop while increasing starting at 1
     for(outsideIndex = 0; outsideIndex < arraySize; outsideIndex++)
     {
         // set the minimum to the outside index
         minimum = (GenericData) genericSetArray[outsideIndex];
         // Save the index of the minimum
         int minIndex = outsideIndex;
         // loop through the inner index and increase at outer index + 1
         for(insideIndex = outsideIndex + 1; insideIndex < arraySize;
                 insideIndex++)
         {
             // Check the outside index value to the inside value
             int checkVal = minimum.compareTo( (GenericData)
                     genericSetArray[insideIndex]);
             // If the value is bigger, reset the minimum
             if(checkVal > 0)
             {
                 // update the minimum value to the inside index
                 minimum = (GenericData) genericSetArray[insideIndex];
                 // update the index value
                 minIndex = insideIndex;
             }
         }
         // Swap the values
         swapValues(genericSetArray, outsideIndex, minIndex); 
     }
     // Return the string
     return this.toString();
    }
    /**
     * Description: Sorts elements using the insertion sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of
     * the given data set; the compareTo method decides the key
     * and the order resulting
     * <p>
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("unchecked")
    public java.lang.String runInsertionSort()
    {
        // Initialize the indexes
        int outsideIndex, insideIndex;
        // Loop throguh the outer index while starting at 1 and increase
        for(outsideIndex = 1; outsideIndex < arraySize; outsideIndex++)
        {
            // Create the value to check
            GenericData checkVal = (GenericData) genericSetArray[outsideIndex];
            // loop through inside index while starting at the index before 
            // and decrease the index
            for(insideIndex = outsideIndex - 1; insideIndex >= 0;
                    insideIndex--)
            {
                int compareVal = checkVal.compareTo((GenericData)
                        genericSetArray[insideIndex]);
                // If the value is bigger, swap the values
                if(compareVal < 0)
                {
                    // Swap the inside index with the next index
                    swapValues(genericSetArray, insideIndex, insideIndex + 1);
                }
            }
        }
        // Return the string
        return this.toString();
    }
    /**
     * copies one Object array to another
     * <p>
     * @param destArray - Object array being copied to
     * <p>
     * @param sourceArray - Object array being copied from
     */
    private void copyArray(java.lang.Object[] destArray,
                       java.lang.Object[] sourceArray)
    {
        int index;
        for(index = 0; index < arraySize; index++)
        {
           destArray[index] = sourceArray[index]; 
        }        
    }
    /**
     * Swaps values within given array
     * <p>
     * @param localArray - array of Objects used for swapping
     * <p>
     * @param indexOne - integer index for one of the two items to 
     * be swapped
     * <p>
     * @param indexOther - integer index for the other of the two 
     * items to be swapped
     */
    private void swapValues(java.lang.Object[] localArray,
                        int indexOne,
                        int indexOther)
    {
        // Holder for the first array
        java.lang.Object indexOneHolder = localArray[indexOne];
        // Set other to one
        localArray[indexOne] = localArray[indexOther];
        // Set the holder of one to other
        localArray[indexOther] = indexOneHolder;
    }
    /**
     * Recursively calculates integer base to power (exponent)
     * <p>
     * @param base - Integer base value
     * <p>
     * @param exponent - Integer exponent value
     * <p>
     * @return Integer base to the exponent result
     */
    private int toPower(int base, int exponent)
	{
	  if(exponent > 0)
		{
                   // Rescursivly call the toPower function to get the power
		  return (base * toPower(base, exponent - 1));
		}
	  return 1;
	}
    
}
