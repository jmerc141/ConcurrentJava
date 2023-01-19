import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NonPrimes {

	private Map<Integer, Integer[]> np = new ConcurrentHashMap<Integer, Integer[]>();

	public Map<Integer, Integer[]> getMap() {
		return np;
	}

	/**
	 * Helper method to get an array of factors
	 * for a non prime number
	 * @param x non prime number
	 * @return An array of Integer with all the factors
	 */
	public Integer[] getFactors(int x) {
		
		// ArrayList object to hold the factors
		List<Integer> f = new ArrayList<Integer>();

	    // loop runs from 1 to x
	    for (int i = 1; i <= x; ++i) {

	      // if number is divided by i
	      // i is the factor
	      if (x % i == 0) {
	    	  f.add(i);
	    	}
	      }
	    // convert back to an array of Integer
		return f.toArray(new Integer[0]);
	}
	
	@Override
	public String toString() {
		String s = null;
		for(Map.Entry<Integer, Integer[]> m : np.entrySet()) {
			s += "\n" + m.getKey() + ", ";
			for(int x : m.getValue()) {
				s += x + " ";
			}
		}
		return s;
		
	}
	
}
