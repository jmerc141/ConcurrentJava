/**
 * Extends thread class and holds and integer to be calculated
 * @author james
 *
 */
public class MyThread implements Runnable {
	/**
	 * n integer to pass to calculate
	 * p instance of Primes class to call calculate
	 */
	private int n;
	private Primes p;
	
	public MyThread(int n, Primes p) {
		this.n = n;
		this.p = p;
	}
	
	@Override
	public void run() {
		p.calc(n);
		
	}

}
