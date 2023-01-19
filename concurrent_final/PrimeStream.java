import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * @author james
 *
 */

public class PrimeStream {

	/**
	 * populates prime list and composite map
	 * using streams
	 * @param i upper bound
	 * @param p Prime to be populated
	 */
	public static void streamCalc(int i, Primes p) {
		//keep reference to composite map
		Map<Integer, Integer[]> npmap = p.getnp().getMap();
		
		/**
		 * create map where values are a list of
		 * prime and non prime numbers
		 */
		Map<Boolean, List<Integer>> imap = IntStream.rangeClosed(2, i)
			.parallel()
			.boxed()
			.collect(Collectors.partitioningBy(n -> isPrime(n)));
		
		// reference to the lists
		List<List<Integer>> sub = new ArrayList<List<Integer>>
		(imap.values());
		
		/*
		 *  stream list of composites concurrently and puts them
		 *  and their factors in a concurrent map
		 */
		sub.get(0).parallelStream()
			.forEach(n -> npmap.put(n, streamFactors(n)));
		
		// copy reference of prime list to Primes list
		p.setList(sub.get(1));
	}
	
	/**
	 * tests if i is prime
	 * @param i number to test
	 * @return if i is prime
	 */
	private static boolean isPrime(int i) {
		if(i <= 2)
			return i == 2;
		else
			return (i % 2) != 0
			&&
			IntStream.rangeClosed(3, (int) Math.sqrt(i))
				.filter(num -> num % 2 != 0)
				.noneMatch(num -> (i % num == 0));
	}
	
	/**
	 * returns an Integer array of factors of x
	 * @param x number to factor
	 * @return an Integer array of factors of x
	 */
	private static Integer[] streamFactors(int x) {
		return IntStream.rangeClosed(1, x)
				.boxed()
				.filter(i -> x % i == 0)
				.toArray(Integer[]::new);
	}
}
