package launchers;

import java.util.ArrayList;
import java.util.Arrays;

import problems.FSP;
import problems.PermutationProblem;
import problems.dynamic.DFSP;
import problems.representations.Permutation;

public class mainDynamicOptimisation {

	public static void main(String[] args) {
		
		String problemPath = "./inputData/instances/FSP/test.fsp";
		String dynamicPath = "./inputData/dynamicInstances/insertion/tai20_10_0/";
		
		DFSP as = new DFSP();
		as.readFromDirectory(dynamicPath);
		
		PermutationProblem FSP = new FSP();
		FSP.read(problemPath);
		
		Permutation individual = new Permutation(new ArrayList<Integer>(Arrays.asList(0,1,2,3)));
		FSP.identityPermutation = new ArrayList<Integer>(Arrays.asList(1,2,0,3));
		FSP.evaluate(individual);
		

	}

}
