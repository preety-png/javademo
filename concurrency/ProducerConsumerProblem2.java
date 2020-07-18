package concurrency;

import java.util.LinkedList;
import java.util.Scanner;


 class PC { 
	  
    // Create a list shared by producer and consumer 
    LinkedList<Integer> list = new LinkedList<>(); 
    int capacity = 3; 

  
    public void produce() throws InterruptedException 
    { 
        int data = 1; 
        while (true) { 
            synchronized (this) 
            { 
                while (list.size() != capacity) {     
                	   list.add(data); 
                       System.out.println("data produced-"
                               + data); 
                       data++;
                }
                notify(); 
                if(list.size()== capacity)
                {
                    wait(); 
                }   

          

                // to insert the jobs in the list 
            //    list.add(data++); 
                // now it can start consuming 
               

                Thread.sleep(1000); 
            } 
        } 
    } 

    // Function called by consumer thread 
    public void consume() throws InterruptedException 
    { 
        while (true) { 
            synchronized (this) 
            {  
                while (list.size() != 0) {
              
                	  int data = list.removeFirst(); 
                      System.out.println("data consumed-"
                              + data); 
            }
                notify(); 
                Thread.sleep(1000); 
                
                if(list.size() == 0) {
                wait(); 
                }
                // to retrive data
              


                // Wake up producer thread 
             
            } 
        } 
    } 
} 

public class ProducerConsumerProblem2 {
	public static void main(String[] args) 
	        throws InterruptedException 
	    { 
	   
	        final PC pc = new PC(); 
	  
	        // Create producer thread 
	        Thread t1 = new Thread(new Runnable() { 
	            @Override
	            public void run() 
	            { 
	                try { 
	                    pc.produce(); 
	                } 
	                catch (InterruptedException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	        }); 
	        
	        //consumer thread
	        Thread t2 = new Thread(new Runnable() { 
	            @Override
	            public void run() 
	            { 
	                try { 
	                    pc.consume(); 
	                } 
	                catch (InterruptedException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	        });
	        
	        t1.start(); 
	        t2.start(); 
	        t1.join(); 
	        t2.join(); 
	}
	
}