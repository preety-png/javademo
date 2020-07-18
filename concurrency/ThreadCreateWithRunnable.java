package concurrency;

//some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable interface.
class MyThread implements Runnable
{
	public void run()
	{
		System.out.println("Thread Id: "+Thread.currentThread().getId());
	}
}




public class ThreadCreateWithRunnable {

	public static void main(String[] args) {
	
		MyThread ref = new MyThread();
		
	   // 8 Number of threads 
        for (int i=0; i<8; i++) 
        { 
		Thread t1= new Thread(ref);
		t1.start();
		
        }
	}

}
