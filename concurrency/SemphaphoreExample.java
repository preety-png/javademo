package concurrency;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

class SharedResource  
{ 
    static int count = 0; 
} 

 class ResoureThread1 extends Thread{ 
	  
	 Semaphore sem; 
	    String threadName; 
	   
	    public ResoureThread1(Semaphore sem, String threadName)  
	    { 
	        super(threadName); 
	        this.sem = sem; 
	        this.threadName = threadName; 
	    } 
	  
	    public void run() { 
	    	
	    	if(this.getName().equals("A")) {
	    	System.out.println("Starting " + threadName); 
	
				
	    		 try {
					sem.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

	    	 for(int i=0; i < 5; i++) 
             { 
	    		 SharedResource.count++; 
                 System.out.println(threadName + ": " + SharedResource.count); 
       
          
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
             } 
	    	 
	    	 sem.release(); 
	    	
	      }  
	    else { 
            System.out.println("Starting " + threadName); 
            try 
            { 
                // First, get a permit. 
                System.out.println(threadName + " is waiting for a permit.");    
                // acquiring the lock 
                sem.acquire(); 
              
                System.out.println(threadName + " gets a permit."); 
          
                // Now, accessing the shared resource. 
                // other waiting threads will wait, until this  
                // thread release the lock 
                for(int i=0; i < 5; i++) 
                { 
                	SharedResource.count--; 
                    System.out.println(threadName + ": " + SharedResource.count); 
          
                    // Now, allowing a context switch -- if possible. 
                    // for thread A to execute 
                    Thread.sleep(10); 
                } 
            } catch (InterruptedException exc) { 
                    System.out.println(exc); 
                } 
                // Release the permit. 
                System.out.println(threadName + " releases the permit."); 
                sem.release(); 
        } 
    } 
 } 

public class SemphaphoreExample {
	public static void main(String[] args) throws InterruptedException {
		//one thread allowed
	Semaphore sem = new Semaphore(1);
	
	ResoureThread1 t1 = new ResoureThread1(sem, "A"); 
	ResoureThread1 t2 = new ResoureThread1(sem, "B"); 
	        
	        t1.start(); 
	        t2.start(); 
	        t1.join(); 
	        t2.join(); 
	        System.out.println("final count: " + SharedResource.count); 
	}
	
}