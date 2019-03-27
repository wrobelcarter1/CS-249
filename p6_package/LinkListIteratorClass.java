package p6_package;

/**
 *
 * @author carterwrobel
 */
public class LinkListIteratorClass extends java.lang.Object
{
    private class NodeClass
    {
        int nodeData;
        
        NodeClass nextRef;
        
        private NodeClass()
           {
            nodeData = 0;
            
            nextRef = null;
           }
        
        private NodeClass( int newData )
           {
            nodeData = newData;
            
            nextRef = null;
           }
       }
    /**
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    
    /**
     * reference to head node
     */
    private LinkListIteratorClass.NodeClass headRef;
    
    /**
     * reference to current/cursor node
     */
    private LinkListIteratorClass.NodeClass cursorRef;
    
    /**
     * Default constructor
     */
    public LinkListIteratorClass()
    {
        // Set the refs to null
        headRef = null;
        cursorRef = null;
    }
    
    /**
     * Copy constructor
     * @param copied - LinkListIteratorClass object to be copied
     */
    public LinkListIteratorClass(LinkListIteratorClass copied)
    {
        if( copied.headRef != null && copied.cursorRef != null )
        {
            // create new headref
            headRef = new NodeClass(copied.headRef.nodeData);
            // TWR is this working reference
            // CWR is copied working reference
            NodeClass TWR = headRef;
            NodeClass CWR = copied.headRef;
            // loop values and copies them into list
            while( CWR.nextRef != null )
            {
                // update CWR
                CWR = CWR.nextRef;
                // check for cursor ref
                if( CWR == copied.cursorRef )
                {
                    cursorRef = new NodeClass(CWR.nodeData);
                    TWR.nextRef = cursorRef;
                    TWR = TWR.nextRef;
                }
                else
                    // Copy the node
                {
                    TWR.nextRef = new NodeClass(CWR.nodeData);
                    TWR = TWR.nextRef;
                }
            }
        }
    }
    
    /**
     * Clears list
     */
    public void clear()
    {
        // Sets the ref to null
        headRef = null;
        cursorRef = null;
    }
    
    /**
     * Displays linked list for diagnostic purposes
     */
    public void displayList()
    {
        System.out.print("Data Display: ");
        NodeClass tempNode = headRef;
        while( tempNode != null )
        {
            // Checks if the node is the cursor to put brackets around it
            if( tempNode == cursorRef )
            {
                System.out.print("[" + 
                        Integer.toString(tempNode.nodeData) + "] ");
                tempNode = tempNode.nextRef;
            }
            // If the node is not the cursor then print it normally
            else
            {
                System.out.print(Integer.toString(tempNode.nodeData) + " ");
                tempNode = tempNode.nextRef;
            }
        }
        // set to next line after finnish printing
        System.out.println("");
    }
    
    /**
     * Acquires data at cursor
     * <p>
     * @return integer value at cursor location if available, 
     *  FAILED_ACCESS otherwise
     */
    public int getDataAtCursor()
    {
        // checks if it is null
        if(cursorRef == null)
        {
            return FAILED_ACCESS;
        }
        // returns the data at cursor
        return headRef.nodeData;
    }
    
    /**
     * Recursive method finds a reference to the node just prior to the cursor;
     * initially called with head reference
     * <p>
     * @param workingRef - current NodeClass reference in the list
     * <p>
     * @return NodeClass reference to the item just prior to 
     *  the cursor location
     */
    private LinkListIteratorClass.NodeClass getRefBeforeCursor(
                        LinkListIteratorClass.NodeClass workingRef)
    {
        // sets the working ref to the head
         workingRef = headRef;
         // Recursviely calls the function to find the previous node
         if(workingRef.nextRef != cursorRef )
         {
             workingRef = workingRef.nextRef;
             return getRefBeforeCursor(workingRef.nextRef);
         }
         return workingRef;
    }
    
    /**
     * Inserts value after cursor
     * <p>
     * @param newValue - Value to be inserted in list
     */
    public void insertAfterCursor(int newValue)
    {
        // if list is empty set first value and head
        if( isEmpty() )
        {
            cursorRef = new NodeClass(newValue);
            headRef = cursorRef;
        }
        // If the list is not empty
        else
        {
            // Checks if there is nothing next to the cursor
            if( cursorRef.nextRef == null )
            {
                cursorRef.nextRef = new NodeClass(newValue);
            }
            // Removes the node and reassigns other nodes
            else
            {
            NodeClass oldNode = cursorRef.nextRef;
            NodeClass newNode = new NodeClass(newValue);
            cursorRef.nextRef = newNode;
            newNode.nextRef = oldNode;
            }
        }
    }
    
    /**
     * Inserts value prior to cursor
     * <p>
     * @param newValue - Value to be inserted in list 
     */
    public void insertBeforeCursor(int newValue)
    {
        // Checks if it empty
        if( isEmpty() )
        {
            cursorRef = new NodeClass(newValue);
            headRef = cursorRef;
        }
        else
        {
            NodeClass newNode = new NodeClass(newValue);
            if(headRef.nextRef == null)
            {
                newNode.nextRef = cursorRef;
                headRef = newNode;
            }
            else
            {
                // moves previous to save the node before
                movePrevious();
                NodeClass oldNode = cursorRef;
                // moves back
                moveNext();
                // updates references
                newNode.nextRef = cursorRef;   
                oldNode.nextRef = newNode;
            }
        }
    }
    
    /**
     * Checks for at last item in list
     * <p>
     * @return Boolean result of test
     */
    public boolean isAtEndOfList()
    {
        // checks if the cursor ref to the next is null
        return ( cursorRef.nextRef == null );
    }
    
    /**
     * Reports list empty status
     * <p>
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        // Checks if the head is null
        return ( headRef == null);
    }
    /**
     * Moves cursor to next node if it is not at end
     */
    public void moveNext()
    {
        // checks if its not at the end
        if(!isAtEndOfList())
        {
            // updates the cursorRef
        cursorRef = cursorRef.nextRef;
        }
    }
    
    /**
     * Moves cursor to previous node if it is not already at head
     */
    public void movePrevious()
    {
        if(cursorRef != headRef)
        {
            NodeClass tempNode = headRef;
            boolean test = true;
            while( test )
            {
                if(tempNode.nextRef == cursorRef)
                {
                    cursorRef = tempNode;
                    test = false;
                }
                tempNode = tempNode.nextRef;
            }
        }
    }
    
    /**
     * Removes item at current location/cursor if available
     * <p>
     * Sets cursor to previous node unless cursor is at head
     * <p>
     * @return integer value removed if available, FAILED_ACCESS otherwise
     */
    public int removeDataAtCursor()
    {
        // checks if it is the last item left
         if( isAtEndOfList() && cursorRef == headRef )
            {
                int valueRemoved = cursorRef.nodeData;
                clear();
                return valueRemoved;
            }
        // If its not the last item
        if(cursorRef != null)
        {
            // checks if it as the head ref
            if(cursorRef == headRef)
            {
                int valueRemoved = cursorRef.nodeData;
                NodeClass tempNode = cursorRef;
                moveNext();
                headRef = cursorRef;
                return valueRemoved;
            }
            // if not then remove it normally
            else
            {
                int valueRemoved = cursorRef.nodeData;
                NodeClass nodeAfter = cursorRef.nextRef;
                movePrevious();
                NodeClass nodeBefore = cursorRef;
                moveNext();
                nodeBefore.nextRef = nodeAfter;
                cursorRef = nodeBefore;
                return valueRemoved;
            }
        }
        return FAILED_ACCESS;
    }
    
    /**
     * Sets cursor to first item in list
     */
    public void setToFirstItem()
    {
        // updates the cursor
        cursorRef = headRef;
    }
    
    /**
     * Sets cursor to last item in list
     */
    public void setToLastItem()
    {
        // Checks if its at the end
        while(!isAtEndOfList())
        {
            cursorRef = cursorRef.nextRef;
        }
    }
    
}
