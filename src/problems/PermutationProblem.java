package problems;

import java.util.ArrayList;

public abstract class PermutationProblem implements OptimisationProblem {

	public int problemSize;
    public double globalOptimum;
	
    // Abstract methods
	public abstract void read(String fileName);
	public abstract double evaluate(ArrayList<?> population);

	
}
