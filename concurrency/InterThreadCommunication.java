package concurrency;

import java.util.Scanner;

class Comman {
	String str ;
	Scanner s = new Scanner(System.in);
	public void produce() throws InterruptedException {
		// synchronized block ensures only one thread running at a time.
		synchronized (this) {
			System.out.println("producer running");
			System.out.println("i/p");
			str =s.next();
			wait();

			// and waits till some other method invokes notify().
			System.out.println("\nProducer notified");
		}
	}

	public void consume() throws InterruptedException {
		// this makes the produce thread to run first.
		Thread.sleep(1000);

		// synchronized block ensures only one thread running at a time.
		synchronized (this) {
			System.out.println("consumer got Input");
				System.out.print("o/p: "+str);		
				// notifies the producer to wake up.
				notify();
				Thread.sleep(2000);
		}
	}

}

class ProducerThread extends Thread {
	Comman resource;

	ProducerThread(Comman resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		try {
			resource.produce();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class ConsumerThread extends Thread {
	Comman resource;

	ConsumerThread(Comman resource) {
		this.resource = resource;
	}

	public void run() {
		try {
			resource.consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

public class InterThreadCommunication {

	public static void main(String[] args) {

		Comman common = new Comman();

		ProducerThread p = new ProducerThread(common);
		ConsumerThread c = new ConsumerThread(common);
		p.start();
		c.start();

		// t1 finishes before t2
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
