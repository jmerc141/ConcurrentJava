import java.util.concurrent.Callable;

public class MyFuture implements Callable<Void>{
	
	private int i;
	private Primes p;

	public MyFuture(int i, Primes p) {
		this.i = i;
		this.p = p;
	}

	@Override
	public Void call() throws Exception {
		p.syncCalc(i);
		return null;
	}
	
	
}
