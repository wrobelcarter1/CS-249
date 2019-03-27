 package p1_package;

/**
 * Class for managing sets of integers, 
 * has capacity to generate various sets.
 * @author Carter Wrobel
 */
public class SetClass extends java.lang.Object 
{
    /**
     * Capacity of array
     */
    int arrayCapacity;
    /**
     * Number of values in array
     */
    int arraySize;
    /**
     * SetClass array for management of power sets
     */
    SetClass[] powerSetArray;
    /**
     * Integer array for data
     */
    int[] setArray;
    /**
     * Constant used for base two exponential calculations
     */
    private static final int BASE_TWO = 2;
    /**
     * Constant with default array capacity
     */
    static int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Constants for specifying set data
     */
    public static final int EVEN = 103;
    public static final int INCREMENTED = 101;
    public static final int ODD = 102;
    public static final int PRIME = 104;
    
    /**
     * Default Constructor
     * <p>
     * Initializes set array but sets power set array to null
    */
    public SetClass() 
    {
        // Set the power set array to null
        powerSetArray = null;
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        // Set the Array to the default capacity
        setArray = new int[arrayCapacity];
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
    public SetClass(int initialCapacity)
    {
        // Set the power set array to null
        powerSetArray = null;
        // Set array capacity to the specified initial capacity
        arrayCapacity = initialCapacity;
        // Set the array to the capacity 
        setArray = new int[arrayCapacity];
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
    public SetClass(SetClass copied)
    {
        // Copy the array capacity
        this.arrayCapacity = copied.arrayCapacity;
        // Set the new array to the new size
        this.setArray = new int[copied.arrayCapacity];
        // Update the array size
        this.arraySize = copied.arraySize;
        // Loop to Copy the old array to the new one.
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            this.setArray[index] = copied.setArray[index];
        }
    }
    
    /**
     * Adds integer to set
     * <p>
     * Increases capacity using checkForResize if array is full
     * <p>
     * Does not allow duplicate values in set
     * <p>
     * @param item - integer value to be added to set
    */
    public void addItem(int item)
    {
        // Check if array needs to be resized
        checkForResize();
            // Check if array already contains element
            if(!hasElement(item))
            {
                // Adds the item to the array
                this.setArray[arraySize] = item;
                // Update the array size
                this.arraySize += 1;
            }
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
        if(this.arraySize == this.arrayCapacity)
        {
            // Double the array capacity and sets to new array
            this.arrayCapacity *= 2;
            int[] newArray = new int[arrayCapacity];
            // Loop through old array and copy into new array.
            int index;
            for(index = 0; index < this.arrayCapacity; index++)
            {
                // Update the value
                newArray[index] = this.setArray[index];
            }
            // Set the new array to this.setArray to use new array
            this.setArray = newArray;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return SetClass object with union of two sets
     */
    public SetClass findIntersection(SetClass other)
    {
        // Create a new setclass object
       SetClass intersectionSet = new SetClass();
       // loop through This array and the other array
       int index;
       for(index = 0; index < arraySize; index++)
       {
           // create a variable for the value added
           int valueAdded = this.setArray[index];
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
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting with THIS set
     * <p>
     * @param other - SetClass object from which THIS SetClass 
     * items will be removed
     * <p>
     * @return SetClass object with data as specified
     */
    public SetClass findRelativeComplementOfThisSetIn(SetClass other)
    {
        // Crete new set
        SetClass relativeComplementSet = new SetClass();
        // Take the other set and copy it into the new set class
        int index;
        // Loop through THIS array
        for(index = 0; index < this.arraySize; index++)
        {
            // Set the value added to a variable
            int newElement = this.setArray[index];
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
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return SetClass object with union of two sets
     */
    public SetClass findUnion(SetClass other)
    {
        // Creates new array
        SetClass unionSet = new SetClass();
        // Take the other set and copy it into the new set class
        int index;
        for(index = 0; index < other.arraySize; index++)
        {
            int element = other.setArray[index];
            unionSet.addItem(element);
        }
        // Take each value from the other set and check if it is in
        // the union set, if it isn't then add it.
        for(index = 0; index < this.arraySize; index++)
        {
            int newElement = this.setArray[index];
            if(!unionSet.hasElement(newElement))
            {
                unionSet.addItem(newElement);
            }
        }
        // return the new set
        return unionSet;
    }
    
    /**
     * Seeks and finds prime starting at given value
     * <p>
     * @param value - Integer value to start search for prime
     * <p>
     * @return Next prime number
     */
    private int getNextPrime(int value)
	{
            // create a next prime variable
          int nextPrime;
          // While the value is not prime, loop
          while(!isPrime(value))
          {
              // update the value
           value += 1;   
          }
          // set the next prime to the value when the loop is done
          nextPrime = value;
          // return the next prime
          return nextPrime;
	}

    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - Integer element to be found in set
     * <p>
     * @return Boolean result of test
     */
    public boolean hasElement(int testElement)
    {
        // Initialize index for loop
        int index;
        // Loop through array
        for(index = 0; index < arraySize; index++)
        {
            // If the index of the array equals the test element,
            // then it contains the element.
            if(this.setArray[index] == testElement)
                {
                return true;
                }
        }
        // If the element is not in the array then return false.
        return false;
    }
    
    /**
     * Tests for even, reports
     * <p>
     * @param value - integer value to be tested
     * <p>
     * @return boolean result as specified
     */
    private boolean isEven(int value)
    {
        // If the remainder from division is 0 then it is even.
        return ((value % 2) == 0);
    }
    
    /**
     * Tests to indicate if given integer value is prime
     * <p>
     * @param testVal - Integer value given
     * <p>
     * @return Boolean result of test
     */
    private boolean isPrime(int testVal)
    {
        // Create prime checker variable
        int primeChecker = 2;
        // loop throguh to check the prime number
        int index;
        for(index = 2; primeChecker < testVal; primeChecker++)
        {
            // if the test val == 0 then it isnt prime
            if((testVal % primeChecker) == 0)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Tests to indicate if set is subclass another given set
     * <p>
     * @param other - SetClass object to be tested if THIS set
     * is a subset of it
     * <p>
     * @return Boolean result of test
     */
    public boolean isSubsetOf(SetClass other)
    {
        // Create element count
        int elementCount = 0;
        // Loop through this array
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            // set the number to the index variable
            int elementCheck = this.setArray[index];
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
    * Loads a number of specified integers to set
    * <p>
    * Characteristics may be odd, even, incremented, or prime
    * <p>
    * Parameter four is only used with INCREMENTED
    * <p>
    * @param startValue - Integer value indicates starting value
    * <p>
    * @param numItems - Integer number of items to load
    * <p>
    * @param valueCharacteristic - Integer characteristic code 
    * (ODD, EVEN, INCREMENTED, PRIME)
    * <p>
    * @param incrementBy - Integer value used to specify increment 
    * if INCREMENTED characteristic is set
    */
    public void loadItems(int startValue,
                          int numItems,
                          int valueCharacteristic,
                          int incrementBy)
    {
        // Count for the items bieng added
        int itemCount;
        // If the valueCharacteristic is Odd
        int newValue = startValue;
        
        switch (valueCharacteristic) {
            case ODD:
                // Check if the start value is odd or even
                if(isEven(startValue))
                {
                    startValue += 1;
                }   
                this.addItem(startValue);
                incrementBy = 2;
                // Loop to add items
                for(itemCount = 0; itemCount < numItems; itemCount++)
                {
                    newValue += incrementBy;
                    this.addItem(newValue);
                }   break;
            case EVEN:
                // Check if the start value is odd or even
                if(!isEven(startValue))
                {
                    startValue += 1;
                }   
                this.addItem(startValue);
                incrementBy = 2;
                // Loop to add items
                for(itemCount = 0; itemCount < numItems; itemCount++)
                {
                    newValue += incrementBy;
                    this.addItem(newValue);
                }   break;
            case INCREMENTED:
                // Add the start value
                this.addItem(startValue);
                // Loop to add items
                for(itemCount = 0; itemCount < numItems; itemCount++)
                {
                    newValue += incrementBy;
                    this.addItem(newValue);
                }   break;
            case PRIME:
                // Add the start value
                this.addItem(getNextPrime(startValue));
                // Update a new value to be bigger than the start value.
                newValue = startValue + 1;
                // Loop through the amount of times needed to add numbers
                for(itemCount = 1; itemCount < numItems; itemCount++)
                {
                    // Add the next Prime number to the set
                    this.addItem(getNextPrime(newValue));
                    // Set the newValue to the prime number you just added
                    newValue = getNextPrime(newValue);
                    // Add 1 to the number so you dont check the number twice
                    newValue += 1;
                }   break;
            default: 
                break;
        }
        }
    
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - Integer index of element value to remove
     */
    private void removeAtIndex(int indexToRemove)
    {
        // Initialize index variable
        int index;
        for(index = indexToRemove; index < this.arraySize; index++)
        {
            // Sets the nect index value to the previous one
            this.setArray[index] = this.setArray[index + 1];
        }
        // Decreases the array size
        this.arraySize -= 1;
    }
    
    /**
     * Removes value if it is found in set
     * <p>
     * All values equal to given value will be removed 
     * in case there is more than one
     * <p>
     * @param valToRemove - Integer value to be removed
     * <p>
     * @return Boolean result of operation success
     */
    public boolean removeValue(int valToRemove)
    {
        // check if the array has the element
        if(hasElement(valToRemove))
        {
            int index;
            int indexOfValue = 0;
            // Loop through the array to find the index of the value to remove
            for(index = 0; index < this.arraySize; index++)
            {
                if(this.setArray[index] == valToRemove)
                {
                    indexOfValue = index;
                }
            }
            // Once you have the index, call removeAtIndex to remove it
            removeAtIndex(indexOfValue);
            return true;
        }
        return false;
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

    /**
     * Provides list of set array elements as comma-delimited string
     * <p>
     * @return - Returns array in string form
     * @Overrides toString in class java.lang.Object
     */
    @Override
    public java.lang.String toString() 
    {
        // Creat an empty string
        String outputString  = "";
        // loop through the array to get values
        int index;
        for(index = 0; index < this.arraySize; index++)
        {
            // convert each value to a string using the wrapper class
            String stringOfInt = Integer.toString(setArray[index]);
            // add it to the empty string
            outputString += stringOfInt;
            // check if you are at the end of the array to not have an extra ,
            if(index != this.arraySize - 1)
            {
                outputString += ", ";
            }
        }
        // return string of array
        return outputString;
    }
    
}