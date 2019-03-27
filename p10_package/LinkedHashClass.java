package p10_package;
/**
 *
 * @author CarterWrobel
 */
public class LinkedHashClass extends java.lang.Object
{
    /**
     * local node class for holding student data and next reference link
     * <p>
     * @author CarterWrobel
     */
    private class LinkedNodeClass
    {
        SimpleStudentClass data;

        LinkedHashClass.LinkedNodeClass nextRef;

        private LinkedNodeClass(SimpleStudentClass inData)
        {
            data = inData;

            nextRef = null;
        }
    }

    /**
     * Table size default
     * <p>
     * Table size (capacity) is recommended to be a prime number
     */
    private final int DEFAULT_TABLE_SIZE = 11;

    /**
     * Size of the base table
     */
    private int tableSize;

    /**
     * Constant used to control access operation
     */
    private boolean REMOVE;

    /**
     * Constant used to control access operation
     */
    private boolean SEARCH;

    /**
     * Array for hash table
     */
    private LinkedHashClass.LinkedNodeClass[] tableArray;

    /**
     * Default constructor
     * <p>
     * Initializes array to default table size
     */
    public LinkedHashClass()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        tableArray = new LinkedHashClass.LinkedNodeClass[ tableSize ];
    }

    /**
     * Initialization constructor
     * <p>
     * initializes array to specified table size
     * <p>
     * @param inTableSize - sets table size
     */
    public LinkedHashClass(int inTableSize)
    {
        tableSize = inTableSize;
        tableArray = new LinkedHashClass.LinkedNodeClass[ tableSize ];
    }

    /**
     * Copy constructor
     * <p>
     * @param copied - LinkedHashClass object to be copied
     */
    public LinkedHashClass(LinkedHashClass copied)
    {
        // updates the size
        tableSize = copied.tableSize;
        // creates a temp array
        LinkedHashClass.LinkedNodeClass[] tempArray = new
                    LinkedHashClass.LinkedNodeClass[ tableSize ];
        // sets the index for the loop
         int index = 0;
         // loop through the array
         while( index < tableSize )
         {
             // create a temp node for the index
             LinkedNodeClass tempNode = tableArray[ index ];
             // loop  to check when the list ends
             while( tempNode != null )
             {
                 // copy the data to the temp array
                 tempArray[ index ] = tempNode;
                 // update to the next node
                 tempNode = tempNode.nextRef;
             }
             // increase the array index
             index++;
         }
         // set the temp array to the table array
         tableArray = tempArray;
    }

    /**
     * Helper method that handles both searching and removing items in
     * linked list at specified index
     * <p>
     * @param linkIndex - integer index specifying location in array
     * <p>
     * @param studentID - integer key for searching and/or removing node
     * <p>
     * @param removeFlag - boolean flag that indicates whether to search or
     * remove (use SEARCH, REMOVE constants to call this method)
     * <p>
     * @return SimpleStudentClass data found and/or removed
     */
    private SimpleStudentClass accessLinkedData(int linkIndex,
                                            int studentID,
                                            boolean removeFlag)
    {
        // create a temp node
        LinkedNodeClass tempNode = tableArray[ linkIndex ];
        // if the beginning is the correct node
        if( tempNode.data.studentID == studentID )
        {
            // checks if we are removing the node or searching
            if ( removeFlag == REMOVE )
            {
                tableArray[ linkIndex ] = tempNode.nextRef;
            }
        return tempNode.data;
        }
        // loops through the list to find the next node
        while( tempNode.nextRef != null )
        {
            // if the next node contains the data
            if( tempNode.nextRef.data.studentID == studentID )
            {
                // save that current node
                SimpleStudentClass savedNode = tempNode.nextRef.data;
                // if we are removing the data
                if( removeFlag == REMOVE )
                {
                    // update the reference before to the one after
                    tempNode.nextRef = tempNode.nextRef.nextRef;
                }
                // return the saved node that was removed
                return savedNode;
            }
            // update the tempnode to check the next node in the list
            else
            {
                tempNode = tempNode.nextRef;
            }
        }
        // else return null
        return null;
    }

    /**
     * Adds item to hash table
     * <p>
     * Uses overloaded addItem with object
     * <p>
     * @param inName - name of student
     * <p>
     * @param inStudentID - student ID
     * <p>
     * @param inGender - gender of student
     * <p>
     * @param inGPA - gpa of student
     * <p>
     * @return Boolean success of operation
     */
    public boolean addItem(java.lang.String inName,
                       int inStudentID,
                       char inGender,
                       double inGPA)
    {
        // create the student object
        SimpleStudentClass newStudent = new SimpleStudentClass( inName,
                                                                inStudentID,
                                                                inGender,
                                                                inGPA);
        // add the object
        return addItem( newStudent );
    }

    /**
     * Adds item to hash table
     * <p>
     * Overloaded method that accepts SimpleStudentClass object
     * <p>
     * @param newItem - student class object
     * <p>
     * @return boolean success of operation
     */
    public boolean addItem(SimpleStudentClass newItem)
    {
        // use generate hash with the object to get the array index and
        // append it to the list
        appendToList( generateHash( newItem ), newItem );
        return false;

    }

    /**
     * Appends new data to end of list at given list index;
     * handles empty node at that index as needed
     * <p>
     * @param listIndex - specified integer index of array
     * <p>
     * @param newNode - SimpleStudentClass node to be appended to array/list
     */
    private void appendToList(int listIndex,
                          SimpleStudentClass newNode)
    {
        // checks if the index is empty to add there
        if( tableArray[ listIndex ] != null )
        {
            LinkedNodeClass currentNode = tableArray[ listIndex ];
            // loops through the list to find the next empty spot
            while( currentNode.nextRef != null )
            {
                // updates the node
                currentNode = currentNode.nextRef;
            }
            // sets the data to the end of the list
            currentNode.nextRef = new LinkedNodeClass( newNode );
        }
        // set the data to the beginning
        else
        {
            tableArray[ listIndex ] = new LinkedNodeClass( newNode );
        }
    }

    /**
     * Clears hash table by clearing all linked list elements
     */
    public void clearHashTable()
    {
        int index = 0;
        // loops through the array to set the indicies to null
        while( index < tableSize )
        {
            tableArray[ index ] = null;
            index++;
        }
    }

    /**
     * Method recursively counts number of nodes in a given linked list
     * <p>
     * @param workingRef - LinkedNodeClass reference used for recursion;
     *  initially set to head
     * <p>
     * @return integer number of nodes found
     */
    private int countNodes(LinkedHashClass.LinkedNodeClass workingRef)
    {
        int index = 0;
        int total = 0;
        // loops through each index
        while( index < tableSize )
        {
            workingRef = tableArray[ index ];
            if( workingRef != null )
            {
                total++;
            }
            else if ( workingRef.nextRef != null )
            {
                total++;
                countNodes( workingRef);
            }
            index++;
        }
        return total;
    }

    /**
     * Searches for item in hash table using student ID as key
     * <p>
     * @param studentID - used for requesting data
     * <p>
     * @return SimpleStudentClass object removed, or null if not found
     */
    public SimpleStudentClass findItem(int studentID)
    {
        // generates a hash for the index in the array
        int hash = generateHash( new SimpleStudentClass( " ",
                                                          studentID,
                                                          ' ',
                                                          0)
                                                         );
        // call access linked data with the hash number
        return accessLinkedData( hash, studentID, SEARCH);
    }

    /**
     * Method uses student ID to generate hash value for
     * use as index in hash table
     * <p>
     * Process sums the Unicode values of the student ID numbers
     * converted to characters
     * <p>
     * @param studentData - SimpleStudentClass object from which hash
     * value will be generated
     * <p>
     * @return integer hash value to be used as array index
     */
    public int generateHash(SimpleStudentClass studentData)
    {
        int sum = 0;
        int studentID = studentData.studentID;
        while( studentID > 0 )
        {
            int num = studentID % 10;
            studentID = studentID / 10;
            sum += ( int )( '0' + num);
        }
        return sum % tableSize;
    }

    /**
     * Removes item from hash table, using student ID as key
     * <p>
     * @param studentID - used for requesting data
     * <p>
     * @return SimpleStudentClass object removed, or null if not found
     */
    public SimpleStudentClass removeItem(int studentID)
    {
        // generates the hash for the index in the array
        int hash = generateHash( new SimpleStudentClass( " ",
                                                          studentID,
                                                          ' ',
                                                          0)
                                                         );
        // calls access linked data with the hash index
        return accessLinkedData(hash, studentID, REMOVE);
    }

    /**
     * traverses through all array bins, finds lengths of each linked list,
     * then displays as table
     * <p>
     * Shows table of list lengths, then shows table size, then shows the
     * number of empty bins and the longest linked list of the set;
     * adapts to any size array
     */
    public void showHashTableStatus()
    {
        System.out.println("Array node report: ");
        int arrayIndex = 0;
        int emptyBinCount = 0;
        int biggestNodeCount = 0;
        System.out.print("  Index:");
        // Prints out the array indicies
        while ( arrayIndex < tableSize )
        {
            System.out.printf( "%10s", arrayIndex);
            arrayIndex++;
        }
        int index = 0;
        System.out.print("\n");
        System.out.print("        ");
        // prints out the dashes inbetween
        System.out.printf("%10s", "       -----");
        while( index < tableSize - 1 )
        {
            System.out.printf("%10s", "-----");
            index++;
        }
        System.out.println("         ");
        // prints out the number of nodes
        index = 0;
        System.out.printf("%9s", "");
        while( index < tableSize )
        {
            // counts the number of nodes
            int currentNodeCount = 0;
            LinkedNodeClass tempNode = tableArray[ index ];
            if( tempNode == null )
            {
                emptyBinCount++;
            }
            while ( tempNode != null )
            {
                currentNodeCount++;
                tempNode = tempNode.nextRef;
            }
            // keeps track of the biggest list of nodes
            if( currentNodeCount > biggestNodeCount )
            {
                biggestNodeCount = currentNodeCount;
            }
            System.out.printf("%10s", currentNodeCount);
            index++;
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("With a table size of 11" );
        System.out.println("The number of empty bins was " + emptyBinCount +
                           ", and the longest linked node list was " +
                           biggestNodeCount + " nodes." );
        System.out.println(" ");
    }

}