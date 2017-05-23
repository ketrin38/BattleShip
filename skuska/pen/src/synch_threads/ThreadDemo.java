/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synch_threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mino
 */
public class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   PrintDemo  PD;

   ThreadDemo( String name,  PrintDemo pd){
       threadName = name;
       PD = pd;
   }
   public void run() {

         for (int i = 0; i < 5; i++) {
//             
//             synchronized(System.out) {

//                 System.out.print(threadName+ ": " );
                 PD.printCount(threadName);   
                 Thread.yield();
                 
//             }
                
         }

     
     //System.out.println("Thread " +  threadName + " exiting.");
   }

   public void start ()
   {
//      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}