package concurrency;

//used for background services for other thread
//setDaemon before start of thread

class Util {
	static void print(String s) throws InterruptedException {
		System.out.println(s);
		Thread.sleep(2000);

	}
}

class Shared {
	synchronized void read(Shared s1) throws InterruptedException {
		System.out.println("read begin");
		Util.print("reading");
		s1.write(this);
		System.out.println("read begin");

	}

	synchronized void write(Shared s2) throws InterruptedException {
		System.out.println("write begin");
		Util.print("writing");
		s2.read(this);
		System.out.println("write ends");
	}
}

class ThreadA extends Thread {

	private Shared s1;
	private Shared s2;

	public ThreadA(Shared s1, Shared s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {

		try {
			s1.read(s2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadB extends Thread {
	private Shared s1;
	private Shared s2;

	public ThreadB(Shared s1, Shared s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {

		try {
			s2.write(s1); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class DeadLockExample {

	public static void main(String[] args) {
		
		    Shared s1 = new Shared(); 
	        Shared s2 = new Shared(); 
	  
		
	    ThreadA t1 = new ThreadA(s1,s2);
		t1.start();
		
		ThreadB t2 = new ThreadB(s1,s2);
		t2.start();

	}
	
	
	//collect thread dump
	// jcmd $PID Thread.print

}
