/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synch_threads;

/**
 *
 * @author Mino
 */
public class PrintDemo {
    
   public int counter;
   
   public PrintDemo(int counter){
       this.counter=counter;
   }
   
   public synchronized void printCount(String threadName){
    try {
        // for(int i = 5; i > 0; i--) {
            counter = counter+1;
            System.out.println(threadName+"--> počítadlo "  + counter );
         //}
     } catch (Exception e) {
         System.out.println("Thread  interrupted.");
     }
   }

}