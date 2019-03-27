package p6_package;

/**
 *
 * @author carterwrobel
 */
public class LinkListQueueClass extends java.lang.Object
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
     * Stores queue head reference
     */
    private LinkListQueueClass.NodeClass headRef;
    
    /**
     * Stores queue tail reference
     */
    private LinkListQueueClass.NodeClass tailRef;
    
    /**
     * Default constructor
     */
    public LinkListQueueClass()
    {
        headRef = null;
        tailRef = null;
        
    }
    
    /**
     * Copy constructor
     * <p>
     * @param copied - SimpleQueueClass object to be copied 
     */
    public LinkListQueueClass(LinkListQueueClass copied)
    {
        if( copied.headRef != null && copied.tailRef != null )
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
                // check for cursor
                if( CWR == copied.tailRef )
                {
                    tailRef = new NodeClass(CWR.nodeData);
                    TWR.nextRef = tailRef;
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
     * Appends value to end of queue
     * <p>
     * @param newValue - Value to be enqueued
     */
    public void enqueue(int newValue)
    {
        // checks if it is empty
        if( isEmpty() )
        {
            headRef = new NodeClass(newValue);
            tailRef = headRef.nextRef;
        }
        // checks if its the end
        else if( tailRef == null)
        {
            tailRef = new NodeClass(newValue);
            headRef.nextRef = tailRef;
        }
        // adds the node
        else
        {
            NodeClass newNode = new NodeClass( newValue );
            tailRef.nextRef = newNode;
            tailRef = newNode;
        }
    }
    
    /**
     * Removes and returns value from front of queue
     * <p>
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        if( headRef != null )
        {
            int returnedData = headRef.nodeData;
            headRef = headRef.nextRef;
            return returnedData;
        }
        return FAILED_ACCESS;
    }
    
    /**
     * Provides peek at front of queue
     * <p>
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        // checks if empty
        if( headRef != null )
        {
            // returns head ref
            return headRef.nodeData;
        }
        return FAILED_ACCESS;
    }
    /**
     * Reports queue empty state
     * <p>
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return( (headRef == null) && (tailRef == null) );
    }
    
    /**
     * Clears the queue by setting the head and tail references to null
     */
    public void clear()
    {
        headRef = null;
        tailRef = null;
    } 
     
}
