package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//used for computation , once all thread done then dependent thraed starts to combine the result

class Quantity implements Runnable {
	public static int product = 0;

	public void run() {
		product = 2 * 3;

		try {
			Payment.barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class Price implements Runnable 
{ 
    public static int sum = 0; 
    public void run() 
    { 
        // check if newBarrier is broken or not 
        System.out.println("Is the barrier broken? - " + Payment.barrier.isBroken()); 
        sum = 10 + 20; 
       
        	try {
				Payment.barrier.await(3000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
          
            // number of parties waiting at the barrier 
            System.out.println("No of waiting at barrier: " + Payment.barrier.getNumberWaiting()); 
      
     
  } 
} 
 
class Payment implements Runnable 
{ 
    public static CyclicBarrier barrier = new CyclicBarrier(3); 
      
    public void run() 
    { 
        System.out.println("No of parties = "+ barrier.getParties());   
         
        // objects on which the child thread has to run 
        Quantity comp1 = new Quantity(); 
        Price comp2 = new Price(); 
          
        // creation of child thread 
        Thread t1 = new Thread(comp1); 
        Thread t2 = new Thread(comp2); 
          
        // moving child thread to runnable state 
        t1.start(); 
        t2.start(); 
  
        try
        { 
        	barrier.await(); 
        }  
        catch (InterruptedException | BrokenBarrierException e)  
        { 
            e.printStackTrace(); 
        } 
          
        // barrier breaks as the number of thread waiting for the barrier 
        // at this point = 3 
        System.out.println("Final output = " + (Quantity.product +  
                Price.sum)); 
                  
        // Resetting the newBarrier 
        barrier.reset(); 
        System.out.println("Barrier reset successful"); 
    } 
} 

public class CyclicBarrierExample {

	public static void main(String[] args) {
        // parent thread 
        Payment finalAction = new Payment(); 
          
        Thread t1 = new Thread(finalAction); 
        t1.start(); 
    } 

}
