package concurrency;

//block and method sychronized 
// has montior and thread can enter which has lock other will be waiting

class Sender {

	String msg;
	
	int i =0;

	void getI() {
		
		System.out.println("i value : "+ ++i);
	}

	// for block synchronized
	void sendMsg1(String msg) {
		System.out.println("Sending Message 1... :" + msg);
	
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sent 1: " + msg);
	}

	//method synchronized
	public synchronized void sendMsg2(String msg) {
		System.out.println("Sending message 2: " + msg);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Thread interrupted.");
		}
		System.out.println("Sent 2: " + msg);
	}
	
	//synchronized only part of a method.
	  public void sendMsg3(String msg) 
	    { 
		  
		
	        synchronized(this) 
	        { 
	        	System.out.println("Sending message 3: " + msg);
	            try 
	            { 
	                Thread.sleep(1000); 
	            }  
	            catch (Exception e)  
	            { 
	                System.out.println("Thread interrupted."); 
	            } 
	        	System.out.println("Sent 3: " + msg);
	        } 

			   System.out.println("done" ); 
	    } 


}

class SenderThread extends Thread {

	String msg;
	Sender senderObj;

	SenderThread(String msg, Sender senderObj) {
		this.msg = msg;
		this.senderObj = senderObj;
	}

	public void run() {

	
		synchronized (senderObj) {
			// synchronizing the send object
			senderObj.sendMsg1(msg);
		}

			senderObj.sendMsg2(msg);
			
			senderObj.sendMsg3(msg);

			senderObj.getI();
	
		
	}
}

public class SyncronisedExample {

	public static void main(String[] args) {

		Sender send = new Sender();
		SenderThread S1 = new SenderThread(" Hi ", send);
		SenderThread S2 = new SenderThread(" Bye ", send);

		S1.start();
		S2.start();

		// wait for threads to end
		try {
		//S1.join();
		//S2.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
	}

}
