package p5_package;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CarterWrobel
 */
public class SimpleQueueStackMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SimpleQueueClass test1 = new SimpleQueueClass(2);
        SimpleQueueClass test2 = new SimpleQueueClass();
        SimpleStackClass testStack = new SimpleStackClass();
/*
        testStack.push(6);
        System.out.println(testStack.peekTop());
        testStack.push(7);
        System.out.println(testStack.peekTop());
        testStack.push(8);
        System.out.println(testStack.peekTop());
        testStack.pop();
        */
  
     //    SimpleStackClass testStack2 = new SimpleStackClass(testStack);
     //    testStack2.pop();

      //   System.out.println(testStack2.peekTop());


        
        test1.enqueue(2);
        test1.enqueue(3);
        test1.enqueue(4);
        test1.enqueue(5);
        test1.enqueue(6);
        test1.enqueue(7);
        test1.enqueue(8);
        test1.enqueue(9);
        
        test2.enqueue(2);
        test2.enqueue(3);
        test2.enqueue(4);
        
        SimpleQueueClass test3 = new SimpleQueueClass(test1);        
        System.out.println(test3.peekFront());

        
        System.out.println(test1.dequeue());
        System.out.println(test1.peekFront());
        System.out.println(test1.dequeue());
        System.out.println(test1.peekFront());
        System.out.println(test1.dequeue());
        System.out.println(test1.peekFront());
        System.out.println(test1.dequeue());
        test1.enqueue(10);
        System.out.println(test1.peekFront());
        
    System.out.println(test1.dequeue());


    }

    
}
