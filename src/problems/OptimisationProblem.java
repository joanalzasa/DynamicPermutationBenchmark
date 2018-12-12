package problems;

import java.util.ArrayList;

public interface OptimisationProblem {
	
	public void read (String fileName);
	public double evaluate (ArrayList<?> population);
}
