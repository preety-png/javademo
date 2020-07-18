package concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class worker implements Runnable {
	String name;
	ReentrantLock reenter;

	public worker(ReentrantLock rl, String n) {
		reenter = rl;
		name = n;
	}

	@Override
	public void run() {
		boolean inner = false;

		while (!inner) {
			
			boolean ans = reenter.tryLock();

			if (ans) {
				// outer try block
				try {

					Date d = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
					System.out.println(name + ": outer lock acquired 'tryLock'=" + ft.format(d));

					Thread.sleep(1500);

				
					reenter.lock();
					// inner try block
					try {
						d = new Date();
						ft = new SimpleDateFormat("hh:mm:ss");
						System.out.println(name + ": inner lock acquired 'lock'=" + ft.format(d));
						System.out.println("LOCKHOLD COUNT: " + reenter.getHoldCount());

						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					finally {

						// inner try lock release
						System.out.println(name + ": inner unlock");
					
						reenter.unlock();
					}
					System.out.println("LOCKHOLD COUNT: " + reenter.getHoldCount());
					System.out.println(name + ": DONE");
					inner = true;

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// Outer try lock release
					System.out.println(name + ": outer unlock");

					reenter.unlock();
					System.out.println("LOCKHOLD CO UNT: " + reenter.getHoldCount());
				}
			}

			else {
				System.out.println(name + ": WAITING FOR LOCK");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}

public class ReenterLockExample {

	static final int MAX_T = 2;

	public static void main(String[] args) {

		ReentrantLock rel = new ReentrantLock();
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		Runnable w1 = new worker(rel, "t1");
		Runnable w2 = new worker(rel, "t2");
		Runnable w3 = new worker(rel, "t3");
		Runnable w4 = new worker(rel, "t4");
		pool.execute(w1);
		pool.execute(w2);
		pool.execute(w3);
		pool.execute(w4);
		pool.shutdown();

	}

}
