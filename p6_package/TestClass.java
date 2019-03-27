package p6_package;

/**
 *
 * @author carterwrobel
 */
public class TestClass 
{
	public static void main(String[] args)
	{
	   //--------------------Create Lists------------------------\\
	   LinkListIteratorClass linkedList = new LinkListIteratorClass();
	   LinkListQueueClass queue = new LinkListQueueClass();
	   
	   //--------------------------------------------------------\\
	   //-----------------------Link List------------------------\\
	   //--------------------------------------------------------\\
	   System.out.println("////////////////////////////////\n"
	   		            + "////////  Linked List  /////////\n"
	   		            + "////////////////////////////////");
	   
  	   //--------------------Insert Before------------------------\\
	   System.out.println("\nInserting before cursor");
	   linkedList.insertBeforeCursor(5);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(10);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(15);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(20);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(25);
	   linkedList.displayList();
	   

	   
	   //--------------------Insert After-----------------------\\
	   System.out.println("\nInserting after cursor");
   
	   linkedList.insertAfterCursor(30);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(35);
	   linkedList.displayList();

	   linkedList.insertAfterCursor(40);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(45);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(50);
	   linkedList.displayList();
	   
	   //--------------------Move Previous----------------------\\
	   System.out.println("\nMoving previous");
	   
	   linkedList.movePrevious();
	   linkedList.displayList();
	   
	   linkedList.movePrevious();
	   linkedList.displayList();
	   
	   linkedList.movePrevious();
	   linkedList.displayList();
	   
	   linkedList.movePrevious();
	   linkedList.displayList();
	   
	   linkedList.movePrevious();
	   linkedList.displayList();
	   
	   //-------------------------Copying------------------------\\
	   System.out.println("\nCopy Data");
	   LinkListIteratorClass copiedList = new LinkListIteratorClass(linkedList);
	   copiedList.displayList();
	   
	   //--------------------Removing Data-----------------------\\
	   System.out.println("\nRemoving Data");
	   linkedList.removeDataAtCursor();
	   linkedList.displayList();
	   
	   System.out.println("\nRemoving Data");
	   linkedList.removeDataAtCursor();
	   linkedList.displayList();
	   
	   //-----------------Set to beginning of list---------------\\
	   System.out.println("\nSet to beginning of list");
	   linkedList.setToFirstItem();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
	   
	   linkedList.moveNext();
	   linkedList.displayList();
  
	   //---------------Looping Down and Removing---------------\\
	   System.out.println("\nSetting  to end, looping down, removing");
	   linkedList.setToLastItem();
	   while (!linkedList.isEmpty())
	      {
		   System.out.println("Removed: " + linkedList.removeDataAtCursor());
		   linkedList.displayList();
	      }
	   
	   //--------------------Insert After-----------------------\\	   
	   System.out.println("\nInserting after cursor");
	   linkedList.insertAfterCursor(30);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(35);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(40);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(45);
	   linkedList.displayList();
	   
	   linkedList.insertAfterCursor(50);
	   linkedList.displayList();
	      
  	   //--------------------Insert Before------------------------\\
	   System.out.println("\nInserting before cursor");
	   
	   linkedList.insertBeforeCursor(5);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(10);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(15);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(20);
	   linkedList.displayList();
	   
	   linkedList.insertBeforeCursor(25);
	   linkedList.displayList();
	   
	   //--------------------Removing Data-----------------------\\
	  
                    System.out.println("\nRemoving at cursor");
	   linkedList.removeDataAtCursor();
	   linkedList.displayList();
	   
	   System.out.println("\nRemoving at cursor");
	   linkedList.removeDataAtCursor();
	   linkedList.displayList();
	   
	   System.out.println("\nRemoving at cursor");
	   linkedList.removeDataAtCursor();
	   linkedList.displayList();
	   
	   
	   //--------------------------------------------------------\\
	   //-------------------------Queue--------------------------\\
	   //--------------------------------------------------------\\	
	   System.out.println("\n\n////////////////////////////////\n"
		                    + "///////////   Queue   //////////\n"
		                    + "////////////////////////////////");
	  //-------------------------Enqueue-------------------------\\	
	   System.out.println("Enqueue: ");
	   queue.displayList();
	   
	   queue.enqueue(0);
	   queue.displayList();
	   
	   queue.enqueue(1);
	   queue.displayList();
	   
	   queue.enqueue(2);
	   queue.displayList();
	   
	   queue.enqueue(3);
	   queue.displayList();
	   
	   
	   queue.enqueue(4);
	   queue.displayList();
	   
	   queue.enqueue(5);
	   queue.displayList();
	   
	 //-------------------------Dequeue-------------------------\\	
	   System.out.println("\nDequeue: ");
	   
	   queue.dequeue();
	   queue.displayList();	
	   
	   queue.dequeue();
	   queue.displayList();	 
	   
	   queue.dequeue();
	   queue.displayList();	 
	   
	   queue.dequeue();
	   queue.displayList();	
	   
	   queue.dequeue();
	   queue.displayList();	
	   
	   queue.dequeue();
	   queue.displayList();	
	   
     //-------------------------Clear--------------------------\\	
	   System.out.println("\nClear: ");
	   
	   queue.enqueue(1);
	   queue.enqueue(2);
	   queue.enqueue(3);
	   queue.displayList();
	   
	   queue.clear();
	   queue.displayList();
	   
	   queue.enqueue(4);
	   queue.enqueue(5);
	   queue.enqueue(6);
	   queue.displayList();
	}

   }
        /*
       LinkListIteratorClass test1 = new LinkListIteratorClass();
       
       test1.insertBeforeCursor(5);
      // System.out.println(test1.getDataAtCursor());
       test1.displayList();
       test1.insertBeforeCursor(10);
       test1.displayList();
       test1.insertBeforeCursor(15);
       test1.displayList();
       test1.insertBeforeCursor(20);
       test1.displayList();
       test1.insertBeforeCursor(25);
       test1.displayList();
       test1.insertAfterCursor(30);
       test1.displayList();
       test1.insertAfterCursor(35);
       test1.displayList();
       test1.insertAfterCursor(40);
       test1.displayList();
       test1.insertAfterCursor(45);
       test1.displayList();
       test1.insertAfterCursor(50);
       test1.displayList();
       test1.movePrevious();
       test1.displayList();
       test1.movePrevious();
       test1.displayList();
       test1.movePrevious();
       test1.displayList();
       test1.movePrevious();
       test1.displayList();
       test1.movePrevious();
       test1.displayList();
       test1.removeDataAtCursor();
       test1.displayList();
       test1.removeDataAtCursor();
       test1.displayList();
       test1.moveNext();
       test1.displayList();
       test1.moveNext();
       test1.displayList();
       test1.moveNext();
       test1.displayList();
       test1.moveNext();
       test1.displayList();
        */
