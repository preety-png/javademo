package concurrency;

import java.util.Scanner;


class ResourceQueue {
	   private int data;
	   private boolean available = false;
	   
	   public synchronized int get() {
	      while (available == false) {
	         try {
	            wait();
	         } catch (InterruptedException e) {}
	      }
	      available = false;
	   //   notifyAll();
	      notify();
	      return data;
	   }
	   public synchronized void put(int value) {
	      while (available == true) {
	         try {
	            wait();
	         } catch (InterruptedException e) { } 
	      }
	      data = value;
	      available = true;
	      notify();
	   }
	}
class Consumer extends Thread {
	   private ResourceQueue queue;
	   private int number;
	   
	   public Consumer(ResourceQueue queue , int number) {
		   this.queue = queue;
	      this.number = number;
	   }
	   public void run() {
	      int value = 0;
	      for (int i = 0; i < 10; i++) {
	         value = queue.get();
	         System.out.println("Consumer #" + this.number + " got: " + value);
	      }
	   }
	}

class Producer extends Thread {
	   private ResourceQueue queue;
	   private int number;
	   public Producer(ResourceQueue c, int number) {
		   queue = c;
	      this.number = number;
	   } 
	   public void run() {
	      for (int i = 0; i < 10; i++) {
	    	  queue.put(i);
	         System.out.println("Producer #" + this.number + " put: " + i);
	         try {
	            sleep((int)(Math.random() * 100));
	         } catch (InterruptedException e) { }
	      } 
	   }
	} 


public class ProducerConsumerProblem {
	   public static void main(String[] args) {
		  ResourceQueue c = new ResourceQueue();
	      Producer p1 = new Producer(c, 1);
	      Consumer c1 = new Consumer(c, 1);
	      p1.start(); 
	      c1.start();
	   }
	}