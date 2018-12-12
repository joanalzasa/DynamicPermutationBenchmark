package benchmarkGenerators;

import java.util.Random;

public abstract class Dynamism {

	public int numberOfChanges;
	public String frequency;
	public int magnitude;
	public double lambda;
	
	public double[] staticSequenceTimes;
	public static final Random defaultR = new Random();
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void getDynamicProcessingTimes();
	public abstract void printDynamicInstance();
	public abstract void createDynamicInstance(String resultsPath, String saveAs);
}
