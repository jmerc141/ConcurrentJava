import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author james
 * class that runs 4 versions of prime list and composite
 * map population and prints execution time
 * 
 */
 
public class Numbers {
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        // holds the string of users choice
        String choice = "";
        
        // while loop to display choice and run either sequential or threaded
        do {
        	System.out.print("Enter 1 for sequential\n"
        			+ "2 for thread\n"
        			+ "3 for bounded thread pool\n"
        			+ "4 for stream version\n"
        			+ "or q to quit: ");
        	choice = sc.next();
        	
        	int upper = 0;
        	
        	// non threaded version
        	if(choice.equals("1")) {
        		System.out.print("Enter upper bound: ");
        		upper = sc.nextInt();
        		Primes p = new Primes();
        		long start = System.currentTimeMillis();
        		p.getPrimes(upper);
        		long end = System.currentTimeMillis();
        		System.out.println("Time sequential: " + (end - start) + "ms");
        		System.out.println("List size: " + p.getList().size() 
        				+ "\nMap size: " + p.getnp().getMap().size()+ "\n");
        	}
        	// threaded version
        	else if(choice.equals("2")) {
        		System.out.print("Enter upper bound: ");
        		upper = sc.nextInt();
        		Primes p = new Primes();
        		long start = System.currentTimeMillis();
        		threadPrimes(upper, p);
        		long end = System.currentTimeMillis();
        		System.out.println("Time threaded: " + (end - start) + "ms");
        		System.out.println("List size: " + p.getList().size() 
        				+ "\nMap size: " + p.getnp().getMap().size()+ "\n");
        	}
        	else if(choice.equals("3")) {
        		System.out.print("Enter upper bound: ");
        		upper = sc.nextInt();
        		System.out.print("Enter number of threads (0 for default): ");
        		// number of threads
        		int t = sc.nextInt();
        		Primes p = new Primes();
        		long start = System.currentTimeMillis();
        		boundedThread(upper, p, t);
        		long end = System.currentTimeMillis();
        		System.out.println("Time bounded thread: " + (end-start) + "ms");
        		System.out.println("List size: " + p.getList().size() 
        				+ "\nMap size: " + p.getnp().getMap().size()+ "\n");
        	}
        	// parallel stream version 
        	else if(choice.equals("4")) {
        		System.out.print("Enter upper bound: ");
        		upper = sc.nextInt();
        		Primes p = new Primes();
        		long start = System.currentTimeMillis();
        		PrimeStream.streamCalc(upper, p);
        		long end = System.currentTimeMillis();
        		System.out.println("Time stream: " + (end-start) + "ms");
        		System.out.println("List size: " + p.getList().size() 
        				+ "\nMap size: " + p.getnp().getMap().size() + "\n");
        	}
        } while(!choice.equals("q"));
        
        sc.close();
    }
    
    /**
	 * Generates a new thread for each number between 2 and bound
	 */
	public static void threadPrimes(int bound, Primes p) {
		// a is starting number
		int a = 2, i;
				
		// increments from 2 to given number
		for (i = a; i <= bound; i++) {
			
	        // instantiate a new thread with i as parameter
		    MyThread mt = new MyThread(i, p);
		    // start the thread (call calculate method)
		    new Thread(mt).start();
		    
		 }
	}
	
	/**
	 * Submits bound number of tasks to ExecutorService
	 * @param bound
	 * @param p
	 * @param threads
	 * @return
	 */
	public static Void boundedThread(int bound, Primes p, int threads) {
		int a=2, i;
		
		ExecutorService ex;
		ex = (threads==0) ? ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1) :
			Executors.newFixedThreadPool(threads);
		
		for(i=a; i<=bound; i++) {
			MyFuture mf = new MyFuture(i, p);
			ex.submit(mf);
		}
		
		try {
			ex.shutdown();
			ex.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}