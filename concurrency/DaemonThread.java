package concurrency;

//used for background services for other thread
//setDaemon before start of thread

public class DaemonThread {

	public static void main(String[] args) {
		ThreadMy1 t1 = new ThreadMy1();

		System.out.println("is daemon: " + t1.isDaemon());

		t1.setDaemon(true);

		t1.start();

		System.out.println("after set daemon is daemon: " + t1.isDaemon());

	}

}
