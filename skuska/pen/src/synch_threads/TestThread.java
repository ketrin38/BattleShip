/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synch_threads;

/**
 *
 * @author Mino
 */
public class TestThread {
      public static void main(String args[]) {
      int counter = 0; // zdielana premenna
      
      PrintDemo PD = new PrintDemo(counter);

      ThreadDemo T1 = new ThreadDemo( "Vlákno - 1 ", PD );
      ThreadDemo T2 = new ThreadDemo( "Vlákno - 2 ", PD );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      } catch( Exception e) {
         System.out.println("Interrupted");
      }
   }
}
