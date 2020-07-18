package concurrency;

class Helpher implements Runnable {

	@Override
	public void run() {

		try {
			// System.out.println("Thread 2: sleep 5000");
			System.out.println("1. Helpher  Name: " + Thread.currentThread().getName());
			System.out.println("2. Helpher  ID: " + Thread.currentThread().getName());
			System.out.println("3. Helpher  Priority: " + Thread.currentThread().getPriority());
			System.out.println("3. Helpher  State: " + Thread.currentThread().getState());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Helpher Thread: interrupted");
		}
	}

}

class Test implements Runnable {

	@Override
	public void run() {

		// System.out.println("Thread 1: Hello");
		System.out.println("1. Test Name: " + Thread.currentThread().getName());
		System.out.println("2. Test ID: " + Thread.currentThread().getName());
		System.out.println("3. Test Priority: " + Thread.currentThread().getPriority());
		System.out.println("3. Test  State: " + Thread.currentThread().getState());
	}

}

public class ThreadClassMethodExample extends Thread {

	private static Object Object;

	public static void main(String[] args) {
		Thread t1 = new Thread(new Test());
		Thread t2 = new Thread(new Helpher());

		t1.start();
		t2.start();

		// get classloader : app class loader and ext is parent loader
		ClassLoader loader = t1.getContextClassLoader();
		// System.out.println("Class loader: "+loader.toString());

		// getting number of active threads
		System.out.println("Active thread: " + Thread.activeCount());

		// getting current thread
		System.out.println("Current Thread: " + Thread.currentThread().getName());

		// check access of t1 thread
		t1.checkAccess();

		// interrupt
		t2 = new Thread(new Helpher());
		t2.start();
		t2.interrupt();
		System.out.println("2nd Helpher interrupted?: " + t2.interrupted());
		System.out.println("2nd Helpher ISInterrupted: " + t2.isInterrupted());

		// isAlive
		System.out.println("2nd Helpher Thread interrupted?: " + t2.isAlive());
		
		// check daemon or main thread
		Thread t3 = new Thread(new Helpher()); 
	        t3.setDaemon(true); 
	        System.out.println("T3 daemon thread: " + t3.isDaemon()); 
	        System.out.println("T3 isinterrupted: " + t3.isInterrupted()); 
	        t3.start();
	        
	     // waiting for join
	        System.out.println("t2 (Helpher) dies then other starts "); 
	        try
	        { 
	            t2.join(); 
	        }  
	        catch (InterruptedException e)  
	        { 
	            e.printStackTrace(); 
	        } 
	        
	        //set name, priority
	        t3.setName("Main Helpher");
	        t3.setPriority(MAX_PRIORITY);
	        
	        t1.yield();
	        System.out.println("T3 Helpher: "+t3.toString()); 
	        
	        
	        // getting list of active thread in current thread's group 
	        Thread[] tarray = new Thread[3]; 
	        Thread.enumerate(tarray); 
	        System.out.println("List of active threads:");
	        for(Thread thread : tarray) 
	        { 
	            System.out.print(thread+","); 
	        } 
	       
	        //stacktrace for all thread
	        System.out.println("\n stacktrace starts----"); 
	        System.out.println(Thread.getAllStackTraces()); 
	        
	        
	        //exception
	        System.out.println("\n exception starts----"); 
	        ClassLoader classLoader = t1.getContextClassLoader(); 
	        System.out.println("t1 class loader:"+classLoader.toString()); 
	        System.out.println("t1 default exception: "+t1.getDefaultUncaughtExceptionHandler()); 
	        
	        
	        t2.setUncaughtExceptionHandler(t1.getDefaultUncaughtExceptionHandler()); 
	        t1.setContextClassLoader(t2.getContextClassLoader()); 
	        t1.setDefaultUncaughtExceptionHandler(t2.getUncaughtExceptionHandler()); 
	          
	        //dumpstack
	        t1 = new Thread(new Test()); 
	        t1.start();
	        StackTraceElement[] trace = t1.getStackTrace(); 
	        System.out.println("Printing stack trace elements for thread1:"); 
	        for(StackTraceElement e : trace) 
	        { 
	            System.out.println(e); 
	        } 
	        
	        //grp
	        ThreadGroup grp = t1.getThreadGroup(); 
	        System.out.println("ThreadGroup to which thread1 belongs " +grp.toString()); 
	        System.out.println(t1.getUncaughtExceptionHandler()); 
	        System.out.println("Does thread1 holds Lock? " + t1.holdsLock("main")); 
	              
	          
	        Thread.dumpStack(); 
	}
}
