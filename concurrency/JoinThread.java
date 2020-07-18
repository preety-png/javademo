package concurrency;

//state example, after sleep, waiting, 

class ThreadMy2 extends Thread {
	public void run() {

		for (int i = 0; i < 5; i++) {

			System.out.println(i + " : " + Thread.currentThread().getName());

		}

	}
}

public class JoinThread {

	public static void main(String[] args) {

		ThreadMy1 t1 = new ThreadMy1();
		ThreadMy2 t2 = new ThreadMy2();
		t1.start();
		System.out.println(Thread.currentThread().getName());
	
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		t2.start();
		System.out.println(Thread.currentThread().getName());

		System.out.println( Thread.currentThread().getName());
	}

}
