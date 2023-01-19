import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author james
 * Calculates a map of non prime numbers
 * and a list of their factors as well as 
 * a list of prime numbers between 2 and a
 * given upper bound
 * 
 * Is a runnable for threaded option
 * 
 */

public class Primes{
	/**
	 * An array list (nums) of prime numbers
	 * and a Map (np) of non primes and their factors
	 */
	private List<Integer> nums = new ArrayList<Integer>();
	private NonPrimes np = new NonPrimes();
	
	/**
	 * 
	 * @return list of primes
	 */
	public List<Integer> getList(){
		return nums;
	}
	
	public void setList(List<Integer> l) {
		nums = l;
	}
	
	public NonPrimes getnp() {
		return np;
	}
	
	/**
	 * Method that populates the list and map
	 * with primes and non primes
	 * @param b upper bound
	 * @return a List of Integer that are prime
	 */
	public void getPrimes(int bound){
		// a is starting number
		int a = 2, i;
		
		// increments from 2 to given number
		for (i = a; i <= bound; i++) { 
	        calc(i);
	    }
	}
	
	/**
	 * synchronized version in calc
	 * @param i
	 */
	public synchronized void syncCalc(int i) {
		Integer[] facts = np.getFactors(i);
		if(facts.length == 2) {
			getList().add(i);
		}
		else {
			np.getMap().put(i, facts);
		}
	}
	
	/**
	 * Calls getFactors to decide if i is prime,
	 * then adds to list or map
	 * @param i number to calculate
	 */
	public void calc(int i) {
		// initially get the factors of i
		Integer[] facts = np.getFactors(i);
		
		// test the length of factors, if only 2 then it is prime
		if(facts.length == 2) {
			//getList().add(i);
			getList().add(i);
		}
		else {
			np.getMap().put(i, facts);
		}
	}
}

