/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;


import java.util.Random;

import problems.FSP;
import tools.*;

public abstract class DynamicFSP extends Dynamism{
	
	// Global variables
	public FSP schedulingProblem;
	public int[][][] dynamicProcessingTimes;
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void getDynamicProcessingTimes();
	public abstract void printDynamicInstance();
	public abstract void createDynamicInstance(String resultsPath, String saveAs);

	// Other methods
	public void generatePeriodicalChanges(){
		staticSequenceTimes = new double[numberOfChanges + 1];
		for (int i = 0; i < (staticSequenceTimes.length); i++) {
			staticSequenceTimes[i] = (double)(i) / (double)(numberOfChanges + 1);
		}
	}
	
	public void generatePoissonProcess(){
		ProbabilityDistributions poisson = ProbabilityDistributions.Poisson;
		ProbabilityDistributions exponential = ProbabilityDistributions.Exponential;
		numberOfChanges = (int) poisson.getRandom(lambda);
		staticSequenceTimes = new double[numberOfChanges + 1];
		double[] poissonPointProcess = new double[numberOfChanges + 2];
		double totalTime = 0;
		for (int i = 0; i < (poissonPointProcess.length); i++) {
			poissonPointProcess[i] = totalTime;
			totalTime += exponential.getRandom(lambda);
		}
		staticSequenceTimes = normalise(poissonPointProcess);
	}
	
	private double[] normalise(double[] poissonPointProcess) {
		double min = 0;
		double max = ArrayUtils.max(poissonPointProcess);
		double[] normalisedChangeTimes = new double[numberOfChanges + 1];
		
		for (int i = 0; i < poissonPointProcess.length - 1; i++) {
			normalisedChangeTimes[i] = (poissonPointProcess[i] - min) / (max - min);
		}
		return normalisedChangeTimes;
	}
	
	
}
