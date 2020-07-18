package concurrency;

//used for background services for other thread
//setDaemon before start of thread

//class Util1 {
//	static void print(String s) throws InterruptedException {
//		System.out.println(s);
//		Thread.sleep(2000);
//
//	}
//}

class UtilReader {
	synchronized void update(UtilWriter u2) throws InterruptedException {
		System.out.println("write begin");
		Thread.sleep(2000);
		u2.write();
		read(); // locked or needed
		System.out.println("write ends");
	}

	synchronized void read() throws InterruptedException {
		System.out.println("reading");

	}
}

class UtilWriter {

	synchronized void search(UtilReader u1) throws InterruptedException {
		System.out.println("search begin");
		Thread.sleep(2000);
		u1.read();
		write();// locked or needed
		System.out.println("search ends");
	}

	synchronized void write() throws InterruptedException {
		System.out.println("writing");


	}

}

class ThreadA1 extends Thread {

	private UtilReader reader;
	private UtilWriter writer;

	public ThreadA1(UtilReader reader, UtilWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public void run() {

		try {
			reader.update(writer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadB1 extends Thread {
	private UtilReader reader;
	private UtilWriter writer;

	public ThreadB1(UtilReader reader, UtilWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public void run() {

		try {
			writer.search(reader);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class DeadLockExample1 {

	public static void main(String[] args) {

		UtilReader reader = new UtilReader();
		UtilWriter writer = new UtilWriter();

		ThreadA1 t1 = new ThreadA1(reader, writer);
		t1.start();

		ThreadB1 t2 = new ThreadB1(reader, writer);
		t2.start();

	}

	// collect thread dump
	// jps -l in cmd , get pid
	// jcmd $PID Thread.print
	
	//get pid
//	C:\backup\BigDataProject\programing_practice\example\Java\src\concurrency>jps -l
//	19152
//	8336 sun.tools.jps.Jps
//	8736 concurrency.DeadLockExample1

	// print in thread dump file
	//C:\backup\BigDataProject\programing_practice\example\Java\src\concurrency>jstack -l 8736 > threaddump.txt


}
