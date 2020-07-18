package concurrency;

//used for background services for other thread
//setDaemon before start of thread

public class RuntimeClassExample {

	public static void main(String[] args) {
		Runtime r= Runtime.getRuntime();
		
		
		System.out.println("current runtime"+r);
		//garbage collection call
		r.gc();
		

		System.out.println("free mem in jvm: "+r.freeMemory());
		System.out.println("total mem in jvm: "+r.totalMemory());
		System.out.println("process in jvm: "+r.availableProcessors());
	



	}

}
