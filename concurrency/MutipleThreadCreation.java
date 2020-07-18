package concurrency;


// extends to Thread class, not good as java not support mutiple inheritance 
class Thread1 extends Thread
{
	public void run()
	{
		System.out.println("Thread Id: "+Thread.currentThread().getId());
		System.out.println("Thread Name: "+Thread.currentThread().getName());
		System.out.println("Thread Priority: "+Thread.currentThread().getPriority());
	}
}




public class MutipleThreadCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	   // 8 Number of threads 
        for (int i=0; i<8; i++) 
        { 
		Thread1 t1= new Thread1();
		t1.start();
		
        }
	}

}
