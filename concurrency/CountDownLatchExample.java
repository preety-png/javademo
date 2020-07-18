package concurrency;

import java.util.concurrent.CountDownLatch;

class ClientService extends Thread 
{ 
    private int delay; 
    private CountDownLatch latch; 
  
    public ClientService(int delay, CountDownLatch latch, 
                                    String name) 
    { 
        super(name); 
        this.delay = delay; 
        this.latch = latch; 
    } 

    @Override
    public void run() 
    {
    	try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	 latch.countDown();   //cout keeps track whichall thread is finishes by decrement
    	 
         System.out.println(Thread.currentThread().getName() 
                         + " finished"); 
    }
}
    
public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(4); 
		
		ClientService t1 = new ClientService(1000,latch,"T1");
		ClientService t2 = new ClientService(1000,latch,"T2");
		ClientService t3 = new ClientService(1000,latch,"T3");
		ClientService t4 = new ClientService(1000,latch,"T4");

		t1.start();
		t2.start();
		t3.start();
    	t4.start();
    	
    	
    	latch.await();            //wait till all client thread finishes
    	
        
    	Thread.sleep(1000);
    	// Main thread has started 
        System.out.println(Thread.currentThread().getName() + 
                           " has finished"); 
    	


	}
}
