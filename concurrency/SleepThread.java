package concurrency;

//state example, after sleep, waiting, 

class A extends Thread {

	public void run() {

		for (int i = 0; i < 5; i++) {

			System.out.println(i + " : " + Thread.currentThread().getName());
			try {
				// thread to sleep for 1000 milliseconds
				Thread.sleep(3000);
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}

public class SleepThread {

	public static void main(String[] args) {

		A t1 = new A();
		t1.start();

		A t2 = new A();
		t2.start();
	}

}
