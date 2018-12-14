/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import java.util.ArrayList;

import problems.FSP;

public abstract class DynamicFSP extends Dynamism{
	
	// Global variables
	public FSP schedulingProblem;
	public ArrayList<ArrayList<ArrayList<Integer>>> dynamicProcessingTimes ;
	
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void getDynamicProcessingTimes();
	public abstract void printDynamicInstance();
	public abstract void createDynamicInstance(String resultsPath, String saveAs);
	
}
