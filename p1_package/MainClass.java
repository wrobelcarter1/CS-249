/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1_package;

/**
 *
 * @author carterwrobel
 */
public class MainClass {
    
        public static void main(String[] args) 
        {
        SetClass test1 = new SetClass();
        
        test1.addItem(2);
        test1.addItem(3);
        test1.addItem(4);
        test1.addItem(5);
        test1.addItem(6);
        
        System.out.println(test1.toString());
        
        test1.removeValue(3);
        System.out.println(test1.toString());
        
        SetClass test2 = new SetClass();
 
        test2.addItem(2);
        test2.addItem(3);
        test2.addItem(4);
        
        System.out.println(test2.toString());
        System.out.println(test1.findUnion(test2));
        System.out.println(test1.findRelativeComplementOfThisSetIn(test2));
        System.out.println(test2.isSubsetOf(test1));
        
        
        SetClass test3 = new SetClass();
        SetClass test4 = new SetClass();
        
        test3.loadItems(6, 8, 103, 0);
        test4.loadItems(6, 4, 103, 0);
        System.out.println(test3.toString());
        System.out.println(test4.toString());
        System.out.println(test4.isSubsetOf(test3));
        
        
        SetClass test5 = new SetClass();
        
        test5.loadItems(3, 15, 104, 0);
        System.out.println(test5.toString());
        
        
       
        
    }
    
}
