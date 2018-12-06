package problems;

import java.util.ArrayList;

import representations.Permutation;
import tools.PermutationUtils;

public abstract class PermutationProblem<E> implements OptimisationProblem<E> {

	int problemSize;
    double globalOptimum;
	
    // Abstract methods
	public abstract void read(String fileName);
	public abstract double evaluate(ArrayList<E> population);

}
