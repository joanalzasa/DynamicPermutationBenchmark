package problems;

import problems.representations.Permutation;

public interface OptimisationProblem {
	
	public void read (String fileName);
	public void evaluate (Permutation individual);
}
