package problems;

import java.util.ArrayList;

import problems.representations.Permutation;

public abstract class PermutationProblem implements OptimisationProblem {

	public ArrayList<Integer> identityPermutation;
	public int problemSize;
//    public double globalOptimum;
	
    // Abstract methods
	public abstract void read(String fileName);
	public abstract void evaluate(Permutation individual);

	
}
