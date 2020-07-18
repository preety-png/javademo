package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask; 

//used for background services for other thread
//setDaemon before start of thread

public class CallableExample implements Callable {

	  public Object call() 
	  {   
	    return 5; 
	  } 
	 
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableExample cal = new CallableExample();
		FutureTask future = new FutureTask(cal);
		Thread t1 = new Thread(future);
		  t1.start(); 
		  System.out.println(future.get()); 
	}

}
