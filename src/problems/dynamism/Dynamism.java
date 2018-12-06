package problems.dynamism;

import representations.Permutation;

public abstract class Dynamism {

	String dynProfilePath;
	int numberOfChanges;
	double[] changingTimes; 
	
	public abstract void read (String fileName);
	
}
