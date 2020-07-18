package concurrency;

//used for background services for other thread
//setDaemon before start of thread

public class DirectRunMethodCall {

	public static void main(String[] args) {

		ThreadMy1 t1 = new ThreadMy1();
		System.out.println("invoked run() directly: ");

		t1.run();

	}

}
