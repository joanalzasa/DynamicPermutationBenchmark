/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import tools.*;

public abstract class Dynamism {
	
	// Global variables
	public int numberOfChanges;
	public String frequency;
	public int magnitude;
	public double lambda = 1;
	
	public double[] changeTime;
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void printDynamicInstance();

	// Other methods
	public void generatePeriodicalChanges(){
		changeTime = new double[numberOfChanges];
		for (int i = 0; i < (changeTime.length); i++) {
			changeTime[i] = (double)(i + 1) / (double)(numberOfChanges + 1);
		}
	}
	
	public void generateRandomExponentialDistribution(){
		RandomNumberGenerator statistic = RandomNumberGenerator.Exponential;
		changeTime = new double[numberOfChanges];
		double[] poissonPointProcess = new double[numberOfChanges + 1];
		double totalTime = 0;
		for (int i = 0; i < (poissonPointProcess.length); i++) {
			totalTime += statistic.getRandom(lambda);
			poissonPointProcess[i] = totalTime;
		}
		changeTime = normalise(poissonPointProcess);
	}
	private double[] normalise(double[] poissonPointProcess) {
		double min = 0;
		double max = poissonPointProcess[poissonPointProcess.length - 1];
		double[] normalisedChangeTimes = new double[numberOfChanges];
		
		for (int i = 0; i < poissonPointProcess.length - 1; i++) {
			normalisedChangeTimes[i] = (poissonPointProcess[i] - min) / (max - min);
		}
		return normalisedChangeTimes;
	}
}
