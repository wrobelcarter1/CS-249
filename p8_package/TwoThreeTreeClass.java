package p8_package;

/**
 * 2-3 Tree class that stores integers
 * @author cjw446
 */
public class TwoThreeTreeClass extends java.lang.Object
{
    /**
     * Two Three Node internal class
     */
    class TwoThreeNodeClass
       {
        /**
         * internal 2-3 node data
         */
        private int leftData, centerData, rightData, numItems;

        /**
         * references from 2-3 node
         */
        private TwoThreeNodeClass leftChildRef, centerChildRef, rightChildRef;

        /**
         * references for managing 2-3 node adjustments
         */
        private TwoThreeNodeClass auxLeftRef, auxRightRef;

        /**
         * parent reference for 2-3 node
         */
        private TwoThreeNodeClass parentRef;

        /**
         * Default 2-3 node class constructor
         */
        private TwoThreeNodeClass()
           {
            leftData = centerData = rightData = numItems = 0;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
           }

        /**
         * Initialization 2-3 node class constructor
         *
         * @param centerIn integer data sets first node initialization
         */
        private TwoThreeNodeClass( int centerIn )
           {
            centerData = centerIn;

            leftData = rightData = 0;

            numItems = 1;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
           }

        /**
         * Private constructor used to create new left or right child node
         * of given parent with the children linked from
         * a current three-node object
         *
         * @param childSelect integer selection value that informs
         * the constructor to create a left or a right child
         * along with making all the sub-child links;
         * uses class constants LEFT_CHILD_SELECT and RIGHT_CHILD_SELECT
         *
         * @param localRef TwoThreeNodeClass reference
         * with three-node data and associated references
         *
         * @param parRef TwoThreeNodeclass parent reference
         * for linking with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect,
                       TwoThreeNodeClass localRef, TwoThreeNodeClass parRef )
           {
            if( childSelect == LEFT_CHILD_SELECT )
               {
                this.centerData = localRef.leftData;
                this.leftChildRef = localRef.leftChildRef;
                this.rightChildRef = localRef.auxLeftRef;

                if( leftChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }

            else  // assume right child select
               {
                this.centerData = localRef.rightData;
                this.leftChildRef = localRef.auxRightRef;
                this.rightChildRef = localRef.rightChildRef;

                if( rightChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }

            this.leftData = this.rightData = 0;
            this.numItems = 1;
            this.centerChildRef = null;
            this.auxLeftRef = this.auxRightRef = null;
            this.parentRef = parRef;
           }

        /**
         * Copy 2-3 node class constructor
         * <p>
         * Note: Only copies data; does not copy links
         * as these would be incorrect for the new tree
         * (i.e., they would be related to the copied tree)
         *
         * @param copied TwoThreeNodeClass object to be copied
         */
        private TwoThreeNodeClass( TwoThreeNodeClass copied )
           {
            leftData = copied.leftData;
            centerData = copied.centerData;
            rightData = copied.rightData;

            numItems = copied.numItems;

            leftChildRef = centerChildRef = rightChildRef = null;

            auxLeftRef = auxRightRef = parentRef = null;
           }
       }
    /**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;

    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEMS = 2;

    /**
     * constant used for identifying three data items stored
     */
    private final int THREE_DATA_ITEMS = 3;

    /**
     * constant used in constructor to indicate which child to create - Left
     * <p>
     * Note: set as package private
     */
    private final int LEFT_CHILD_SELECT = 101;

    /**
     * constant used in constructor to indicate which child to create - Right
     * <p>
     * Note: set as package private
     */
    private final int RIGHT_CHILD_SELECT = 102;

    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private java.lang.String outputString;

    /**
     * root of tree
     */
    private TwoThreeNodeClass root;

    /**
     * Default 2-3 tree constructor
     */
    public TwoThreeTreeClass()
    {
        root = null;
        outputString = "'";
    }

    /**
     * Copy 2-3 tree constructor
     * <p>
     * @param copied - TwoThreeTreeClass object to be duplicated; data is
     * copied, but new nodes and references must be created
     */
    public TwoThreeTreeClass( TwoThreeTreeClass copied )
    {
        // create a new node and assign it
        root = copyConstructorHelper(copied.root);
    }

    /**
     * Implements tree duplication effort with recursive method; copies data
     * into newly created nodes and creates all new references to child nodes
     * <p>
     * @param workingCopiedRef - TwoThreeNodeClass reference that is updated to
     * lower level children with each recursive call
     * <p>
     * @return TwoThreeNodeClass reference to next higher level node; last
     * return is to root node of THIS object
     */
    private TwoThreeTreeClass.TwoThreeNodeClass copyConstructorHelper(
            TwoThreeTreeClass.TwoThreeNodeClass workingCopiedRef )
    {

        if(workingCopiedRef == null)
        {
            return workingCopiedRef;
        }
        TwoThreeNodeClass newNode = new TwoThreeNodeClass(workingCopiedRef);
        // Update the left child
        newNode.leftChildRef = copyConstructorHelper(
                              workingCopiedRef.leftChildRef);
        // Update the center child
        newNode.centerChildRef = copyConstructorHelper(
                              workingCopiedRef.centerChildRef);
        // update the right child
        newNode.rightChildRef = copyConstructorHelper(
                              workingCopiedRef.rightChildRef);
        // return the list
        return newNode;
    }

    /**
     * Method is called when addItemHelper arrives at the bottom of the 2-3
     * search tree (i.e., all node's children are null);
     * <p>
     * Assumes one- or two- value nodes and adds one more to the appropriate
     * one resulting in a two- or three- value node
     * <p>
     * @param localRef - TwoThreeNodeClass reference has original node data
     * and contains added value when method completes; method does not manage
     * any node links
     * <p>
     * @param itemVal - integer value to be added to 2-3 node
     */
    private void addAndOrganizeData( TwoThreeTreeClass.
            TwoThreeNodeClass localRef, int itemVal )
    {
        // If it is a one item node
        if( localRef.numItems == ONE_DATA_ITEM )
        {
            // Checks for the left of the center
            if( itemVal < localRef.centerData )
            {
                localRef.rightData = localRef.centerData;
                localRef.leftData = itemVal;
                localRef.numItems = 2;
            }
            // Otherwise it is to the right
            else
            {
                localRef.leftData = localRef.centerData;
                localRef.rightData = itemVal;
                localRef.numItems = 2;
            }
        }
        // If it is a two item node
        else
        {
            // Checks to the far left
            if( itemVal < localRef.leftData )
            {
                localRef.centerData = localRef.leftData;
                localRef.leftData = itemVal;
                localRef.numItems = 3;
            }
            // checks to the far right
            else if( localRef.rightData < itemVal )
            {
                localRef.centerData = localRef.rightData;
                localRef.rightData = itemVal;
                localRef.numItems = 3;
            }
            // otherwise it is in the middle
            else
            {
                localRef.centerData = itemVal;
                localRef.numItems = 3;
            }
        }

    }

    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     * <p>
     * @param itemVal - integer value to be added to the tree
     */
    public void addItem( int itemVal )
    {
        // Checks if empty
        if( root == null )
        {

            root = new TwoThreeNodeClass(itemVal);
        }
        // if not call the helper
        else
        {
           addItemHelper(root.parentRef, root, itemVal);
        }

    }

    /**
     * Helper method searches from top of tree to bottom using divide and
     * conquer strategy to find correct location (node) for new added value;
     * once location is found, item is added to node using addAndOrganizeData
     * and then fixUpInsert is called in case the updated node has become a
     * three-value node
     * <p>
     * @param parRef - TwoThreeNodeClass reference to the parent of the current
     * reference at a given point in the recursion process
     * <p>
     * @param localRef - TwoThreeNodeClass reference to the current item at the
     * same given point in the recursion process
     * <p>
     * @param itemVal - integer value to be added to the tree
     */
    private void addItemHelper( TwoThreeTreeClass.TwoThreeNodeClass parRef,
                           TwoThreeTreeClass.TwoThreeNodeClass localRef,
                           int itemVal )
    {
        // Checks if it is one item node and not a leaf
        if( (localRef.numItems == ONE_DATA_ITEM ) &&
            ( localRef.leftChildRef != null ) &&
            ( localRef.rightChildRef != null )
          )
        {
            // checks for data less than the center and calls to to left
            if( itemVal < localRef.centerData )
            {
                addItemHelper( localRef, localRef.leftChildRef, itemVal );
            }
            // else it is greater than so it calls to the right
            else
            {
                addItemHelper( localRef, localRef.rightChildRef, itemVal );
            }

        }
        // Checks if it is a two item node and not a leaf
        else if( ( localRef.numItems == TWO_DATA_ITEMS ) &&
                 ( localRef.leftChildRef != null ) &&
                 ( localRef.rightChildRef != null ) &&
                 ( localRef.centerChildRef != null )
               )
        {
            // checks if it is less thn the left item and calls to the left
            if( itemVal < localRef.leftData )
            {
                 addItemHelper( localRef, localRef.leftChildRef, itemVal );
            }
            // checks if it greater than the right data and calls to the right
            else if( localRef.rightData < itemVal )
            {
                addItemHelper( localRef, localRef.rightChildRef, itemVal );
            }
            // otherwise it is in the center and calls down the center
            else
            {
                addItemHelper( localRef, localRef.centerChildRef, itemVal );
            }
        }
        // assume leaf is found
        else
        {
                addAndOrganizeData( localRef, itemVal );
                fixUpInsert( localRef );
         }

    }

    /**
     * Method clears tree so that new items can be added again
     */
    public void clear()
    {
        root = null;
        outputString = "'";
    }

    /**
     * Method used to fix tree any time a three-value node has been added
     * to the bottom of the tree; it is always called but decides to act only
     * if it finds a three-value node
     * <p>
     * Resolves current three-value node which may add a value to the node
     * above; if the node above becomes a three-value node, then this is
     * resolved with the next recursive call
     * <p>
     * Recursively climbs from bottom to top of tree resolving any
     * three-value nodes found
     * <p>
     * @param localRef  - TwoThreeNodeClas reference initially given the
     * currently updated node, then is given parent node recursively each
     * time a three-value node was resolved
     */
    private void fixUpInsert( TwoThreeTreeClass.TwoThreeNodeClass localRef )
    {
        if( localRef.numItems == THREE_DATA_ITEMS )
        {
            // 0 item parent
            if( localRef.parentRef == null )
            {
                // Updates the parent ref
                localRef.parentRef = new TwoThreeNodeClass(
                                        localRef.centerData );
                root = localRef.parentRef;
                // sets the child refs
                localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                          LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                localRef.parentRef.rightChildRef = new TwoThreeNodeClass(
                          RIGHT_CHILD_SELECT, localRef, localRef.parentRef );
            }
            // one item parent
            else if( localRef.parentRef.numItems == ONE_DATA_ITEM )
            {
                // updates the num of items
                localRef.parentRef.numItems = TWO_DATA_ITEMS;
                // checks if the parents lef child is the local
                if( localRef == localRef.parentRef.leftChildRef )
                {
                    // update the parent ref
                    localRef.parentRef.rightData = localRef.parentRef.centerData;
                    localRef.parentRef.leftData = localRef.centerData;
                    // sets the child refs
                    localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                            LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.centerChildRef = new TwoThreeNodeClass(
                            RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                }
                // if its the right
                else
                {
                    // updates the parent
                    localRef.parentRef.leftData = localRef.parentRef.centerData;
                    localRef.parentRef.rightData = localRef.centerData;
                    // updates the child refs
                    localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                            LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.rightChildRef = new TwoThreeNodeClass(
                            RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                }
            }
            // Assume two item parent
            else
            {
                // updates the num of items
                localRef.parentRef.numItems = THREE_DATA_ITEMS;
                // checks if the left child is the local ref
                if( localRef == localRef.parentRef.leftChildRef )
                {
                    // updates the parent
                    localRef.parentRef.centerData = localRef.parentRef.leftData;
                    localRef.parentRef.leftData = localRef.centerData;
                  // updates the child refs
                    localRef.parentRef.leftChildRef = new TwoThreeNodeClass(
                            LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(
                            RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                    localRef.parentRef.auxRightRef =
                                localRef.parentRef.centerChildRef;
                }
                // if its the center child
                else if( localRef == localRef.parentRef.centerChildRef )
                {
                    // update the parent
                    localRef.parentRef.centerData = localRef.centerData;
                    // update the child refs
                    localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(
                          LEFT_CHILD_SELECT, localRef, localRef.parentRef );
                    localRef.parentRef.auxRightRef = new TwoThreeNodeClass(
                          RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                }
                // if its the right
                else
                {
                    // update the parent
                    localRef.parentRef.rightData =
                                      localRef.parentRef.centerData;
                    localRef.parentRef.rightData = localRef.centerData;
                    // update the child
                    localRef.parentRef.rightChildRef = new TwoThreeNodeClass(
                            RIGHT_CHILD_SELECT, localRef, localRef.parentRef);
                }
            }
            // call fix up insert
            fixUpInsert( localRef.parentRef );
        }

    }

    /**
     * Tests center value if single node, tests left and right values if
     * two-value node; returns true if specified data is found in
     * any given node
     * <p>
     * Note: Method does not use any branching operations
     * such as if/else/etc.
     * <p>
     * @param localRef  - TwoThreeNodeClass reference to node with
     * one or two data items in it
     * <p>
     * @param searchVal  - integer value to be found in given node
     * @return boolean result of test
     */
    private boolean foundInNode( TwoThreeTreeClass.TwoThreeNodeClass
                                                    localRef, int searchVal )
    {
        return ( searchVal == localRef.leftData ) ||
                ( searchVal == localRef.leftData ) ||
                ( searchVal == localRef.leftData );
    }

    /**
     * Public method called by user to display data in order
     * <p>
     * @return String result to be displayed and/or analyzed
     */
    public java.lang.String inOrderTraversal()
    {
        outputString = "";
        inOrderTraversalHelper(root);
        return outputString;
    }

    /**
     * Helper method conducts in order traversal with 2-3 tree
     * <p>
     * Shows location of each value in a node: "C" at center of
     * single node "L" or "R" at left or right of two-value node
     * <p>
     * @param localRef - TwoThreeNodeClass reference to current location
     * at any given point in the recursion process
     */
    private void inOrderTraversalHelper( TwoThreeTreeClass.
            TwoThreeNodeClass localRef )
    {
        if( localRef != null )
        {
            if( localRef.numItems == ONE_DATA_ITEM )
            {
            inOrderTraversalHelper(localRef.leftChildRef);
            outputString += "C" + localRef.centerData + " ";
            inOrderTraversalHelper(localRef.rightChildRef);
            }
            else
            {
                inOrderTraversalHelper(localRef.leftChildRef);
                outputString += "L" + localRef.leftData + " ";
                inOrderTraversalHelper(localRef.centerChildRef);
                outputString += "R" + localRef.rightData + " ";
                inOrderTraversalHelper(localRef.rightChildRef);
            }
        }
    }

    /**
     * Search method used by programmer to find specified item in 2-3 tree
     * <p>
     * @param searchVal - integer value to be found
     * <p>
     * @return boolean result of search effort
     */
    public boolean search( int searchVal )
    {
        // call search helper
        return searchHelper( root, searchVal );
    }

    /**
     * Search helper method that traverses through tree in a recursive divide
     * and conquer search fashion to find given integer in 2-3 tree
     * <p>
     * @param localRef - TwoThreeNodeClass reference to given node at
     * any point during the recursive process
     * <p>
     * @param searchVal - integer value to be found in tree
     * <p>
     * @return boolean result of search effort
     */
    private boolean searchHelper( TwoThreeTreeClass.TwoThreeNodeClass
                                                localRef, int searchVal )
    {
        // checks if it is empty
        if(localRef == null)
        {
            return false;
        }
        // finds the node
        else if( foundInNode( localRef, searchVal ) )
        {
            return true;
        }
        // Checks if the current ref is one data item
        else  if( localRef.numItems == ONE_DATA_ITEM)
        {
            // if the one data item equals the search value, return true
            if( searchVal == localRef.centerData)
            {
                return true;
            }
            // if the search val is less than the one item,
            // call to the left child
            else if( searchVal < localRef.centerData )
            {
                searchHelper( localRef.leftChildRef, searchVal );
            }
            // if the search val is greater than the one item,
            // call to the right child
            else
            {
                searchHelper( localRef.rightChildRef, searchVal );
            }

        }
        // Checks if the current ref is two data items
        else if( localRef.numItems == TWO_DATA_ITEMS )
        {
            // checks if the two items are equal to the search val,
            // if so return true
            if( ( searchVal == localRef.leftData ) ||
                ( searchVal == localRef.rightData )
              )
            {
                return true;
            }
            // checks if the search value is less than the leftdata,
            // if so call with the left child
            else if( searchVal < localRef.leftData )
            {
                searchHelper( localRef.leftChildRef, searchVal );
            }
            // checks if the search value is between the two items,
            // if so call with the center child
            else if( ( localRef.leftData < searchVal ) &&
                     ( searchVal < localRef.rightData )
                   )
            {
                searchHelper( localRef.centerChildRef, searchVal );
            }
            // if the search val is greater than the right data
            // then call with the right child
            else
            {
                searchHelper( localRef.rightChildRef, searchVal );
            }

        }
        return false;
    }

}
