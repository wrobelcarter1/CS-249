package p8_package;

/**
 *
 * @author carterwrobel
 */
public class TestClass 
{
    public static void main(String[] args)
    {
        
        TwoThreeTreeClass testTree = new TwoThreeTreeClass();
    
        testTree.addItem(45);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(42);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(39);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(36);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(33);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(30);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(27);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(24);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(21);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(18);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(15);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(12);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(9);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(6);
        System.out.println(testTree.inOrderTraversal());
        testTree.addItem(3);
        System.out.println(testTree.inOrderTraversal());
        
        TwoThreeTreeClass copyTree = new TwoThreeTreeClass(testTree);
        System.out.println(copyTree.inOrderTraversal());
        
        
        
        
    }
    
}
