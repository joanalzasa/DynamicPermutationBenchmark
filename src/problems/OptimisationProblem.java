package problems;

import java.util.ArrayList;

public interface OptimisationProblem <E> {
	
	public void read (String fileName);
	public double evaluate (ArrayList<E> population);
}
