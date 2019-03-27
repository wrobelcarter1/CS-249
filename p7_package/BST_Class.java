package p7_package;

/**
 * Binary search tree (BST) class that stores StudentClassNode
 * data using the student ID as the key
 * <p>
 * @author carterwrobel
 */
public class BST_Class
{
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;
    
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private java.lang.String outputString;
    
    /**
     * Default class constructor, initializes BST
     */
    public BST_Class()
    {
        // Initialize variables
        BST_Root = null;
        outputString = "";
    }
    
    /**
     * Copy constructor
     * <p>
     * Note: Uses copyConstHelper
     * <p>
     * @param copied 
     */
    public BST_Class(BST_Class copied)
    {
        // create a new node and assign it
        BST_Root = new StudentClassNode(copied.BST_Root.name,
        copied.BST_Root.studentID, copied.BST_Root.gender, 
                copied.BST_Root.gpa);
        // call the helper with the new node
        copyConstHelper(copied.BST_Root);
        
    }
    
    /**
     * Copy constructor helper, recursively adds nodes to duplicate tree
     * <p>
     * @param copiedRef - StudentClassNode reference for 
     *  accessing copied object data
     * <p>
     * @return StudentClassNode reference to node added 
     *  at current level of recursion
     */
    private StudentClassNode copyConstHelper(StudentClassNode copiedRef)
    {
        // set the new node to null
        StudentClassNode newNode = null;
        if(copiedRef != null)
        {
            newNode = new StudentClassNode(copiedRef);
            // insert the node
            insert(newNode);
            // recursively call to the left and right children
            copyConstHelper(newNode.leftChildRef);
            copyConstHelper(newNode.rightChildRef);
        }
        return newNode;
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: uses insert helper method
     * <p>
     * @param inData  - StudentClassNode data to be added to BST
     */
    public void insert(StudentClassNode inData)
    {
        // call the helper
        insertHelper(BST_Root, inData);
    }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * @param localRoot - StudentClassNode tree root reference 
     * at the current recursion level
     * <p>
     * @param inData - StudentClassNode item to be added to BST
     */
    private void insertHelper(StudentClassNode localRoot,
                          StudentClassNode inData)
    {
        // check if the tree is empty
        if( isEmpty() )
        {
            BST_Root = inData;
        }
        // check if the data added is less than the root
        else if( inData.studentID < localRoot.studentID && 
                localRoot.leftChildRef == null)
        {
            // if it is and the child is null, set the data
            localRoot.leftChildRef = inData;
        }
        // check if the data added is greater than the root
        else if( inData.studentID > localRoot.studentID && 
                localRoot.rightChildRef == null)
        {
            // if it is and the child is null  then set the data
            localRoot.rightChildRef = inData;
        }
        // if not then recursively call to the left or right
        else if( inData.studentID < localRoot.studentID )
        {
            insertHelper(localRoot.leftChildRef, inData);
        }
        
        else if( inData.studentID > localRoot.studentID )
        {
            insertHelper(localRoot.rightChildRef, inData);
        }
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * <p>
     * @param inData - StudentClassNode that includes the necessary key
     * @return StudentClassNode result of remove action
     */
    public StudentClassNode removeNode(StudentClassNode inData)
    {
        // call the helper with the data
       return removeNodeHelper(BST_Root, inData);
    }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: uses removeFromMin method
     * <p>
     * @param localRoot - StudentClassNode tree root reference 
     *  at the current recursion level
     * <p>
     * @param outData- StudentClassNode item that includes 
     *  the necessary key
     * <p>
     * @return StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper(StudentClassNode
            localRoot, StudentClassNode outData)
    {
        // check if it is null
        if(localRoot == null)
        {
            return null;
        }
        // check if we need to go to the left side
        if(outData.studentID < localRoot.studentID && localRoot.leftChildRef 
                != null)
        {
            // recursivley call the to the left
            removeNodeHelper(localRoot.leftChildRef, outData);
        }
        // check to go down the right side
        else if(outData.studentID > localRoot.studentID && 
                localRoot.rightChildRef != null)
        {
            // recursively call to the right
            removeNodeHelper(localRoot.rightChildRef, outData);
        }
        // check when it is found
        else if( outData.studentID == localRoot.studentID)
        {
            if(localRoot.leftChildRef == null && localRoot.rightChildRef 
                    == null)
            {
                localRoot = null;
                return outData;
            }
         }
        return outData;
    }
    
    /**
     * Searches tree from given node to minimum value node below it,
     * stores data value found, and then unlinks the node
     * <p>
     * @param minParent - StudentClassNode reference to current node
     * <p>
     * @param minChild - StudentClassNode reference to child 
     *  node to be tested
     * <p>
     * @return StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMin(StudentClassNode minParent,
                                       StudentClassNode minChild)
    {
        // set the value to return
        int minValue = minParent.studentID;
        // if the parent isnt null
        while( minParent != null)
        {
            // set the value to the student id
            minValue = minParent.leftChildRef.studentID;
            // set the parent to the next child
            minParent = minParent.leftChildRef;
        }
       return minChild;
    }
    
    /**
     * Searches for data in BST given StudentClassNode with necessary key
     * <p>
     * @param searchData - StudentClassNode item containing key
     * <p>
     * @return StudentClassNode reference to found data
     */
    public StudentClassNode search(StudentClassNode searchData)
    {
        // create a node
        StudentClassNode nodeFound = searchHelper(BST_Root, searchData);
        return nodeFound;
    }
    
    /**
     * Helper method for BST search action
     * <p>
     * @param localRoot - StudentClassNode tree root reference at
     *  the current recursion level
     * <p>
     * @param searchData - StudentClassNode item containing key
     * <p>
     * @return StudentClassNode item found
     */
    private StudentClassNode searchHelper(StudentClassNode localRoot,
                                      StudentClassNode searchData)
    {
        // check if they are equal
        if(searchData.studentID == localRoot.studentID)
        {
            return localRoot;
        }
        // check to the left side
        else if( searchData.studentID < localRoot.studentID )
        {
            return searchHelper(localRoot.leftChildRef, searchData);
        }
        // check to the right side
        else if( searchData.studentID > localRoot.studentID )
        {
            return searchHelper(localRoot.rightChildRef, searchData);
        }
        return null;
        
    }
    
    /**
     * Provides preOrder traversal for user as a string
     * <p>
     * @return String containing pre order output
     */
    public java.lang.String outputPreOrder()
    {
        outputString = "";
        outputPreOrderHelper(BST_Root);
        return outputString;
    }
    
    /**
     * Provides preOrder traversal action helper
     * <p>
     * @param localRoot - StudentClassNode tree root reference at 
     *  the current recursion level
     */
    private void outputPreOrderHelper(StudentClassNode localRoot)
    {
        if(localRoot != null)
        {
        outputString += localRoot.toString() + "\n";
        outputPreOrderHelper(localRoot.leftChildRef);
        outputPreOrderHelper(localRoot.rightChildRef);
        }
         
    }
    
    /**
     * Provides postOrder traversal for use as a string
     * <p>
     * @return String containing post order output
     */
    public java.lang.String outputPostOrder()
    {
        outputString = "";
        outputPostOrderHelper(BST_Root);
        return outputString;
    }
    
    /**
     * Provides postOrder traversal action helper
     * <p>
     * @param localRoot  - StudentClassNode tree root reference
     *  at the current recursion level
     */
    private void outputPostOrderHelper(StudentClassNode localRoot)
    {
        
         if( localRoot != null )
            {
                outputPostOrderHelper(localRoot.leftChildRef);
                outputPostOrderHelper(localRoot.rightChildRef);
                outputString += localRoot.toString() + "\n";
            }
         
    }
    
    /**
     * Provides inOrder traversal for user as a string
     * <p>
     * @return String containing in order output
     */
    public java.lang.String outputInOrder()
    {
        outputString = "";
        outputInOrderHelper(BST_Root);
        return outputString;
    }
    
    /**
     * Provides inOrder traversal action helper
     * <p>
     * @param localRoot - StudentClassNode tree root reference
     * at the current recursion level
     */
    public void outputInOrderHelper(StudentClassNode localRoot)
    {
        if(localRoot != null)
        {
            outputInOrderHelper(localRoot.leftChildRef);
            outputString += localRoot.toString() + "\n";
            outputInOrderHelper(localRoot.rightChildRef);
        }
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = null;
    }
    
    /**
     * Test for empty tree
     * <p>
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return BST_Root == null;
    }
    
}
