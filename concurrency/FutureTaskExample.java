package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//used for background services for other thread
//setDaemon before start of thread

public class FutureTaskExample implements Callable {

	@Override
	public Object call() throws Exception {
		Random generator = new Random();
		Integer randomNumber = generator.nextInt(5);

		Thread.sleep(randomNumber * 1000);

		return randomNumber;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask[] futureTasks = new FutureTask[5];

		// mutiple threads
		for (int i = 0; i < 5; i++) {
			FutureTaskExample call = new FutureTaskExample();

			futureTasks[i] = new FutureTask(call);
			Thread t = new Thread(futureTasks[i]);
			t.start();
		}
		for (int i = 0; i < 5; i++) {
		System.out.println(futureTasks[i].get());
		}
	}

}
