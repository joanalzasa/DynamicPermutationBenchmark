package benchmarkGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tools.ProbabilityDistributions;

public abstract class Dynamism {

	public int numberOfChanges;
	public String frequency;
	public int magnitude;
	public double lambda;
	
	public ArrayList<Double> staticSequenceTimes;
	public static final Random defaultR = new Random();
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void getDynamicProcessingTimes();
	public abstract void printDynamicInstance();
	public abstract void createDynamicInstance(String resultsPath, String saveAs);
	
	// Other methods
	public void generatePeriodicalChanges(){
		for (int i = 0; i < numberOfChanges + 1; i++) {
			staticSequenceTimes.add((double)(i) / (double)(numberOfChanges + 1));
		}
	}
	
	public void generatePoissonProcess(){
		ProbabilityDistributions poisson = ProbabilityDistributions.Poisson;
		numberOfChanges = (int) poisson.getRandom(lambda);
		staticSequenceTimes.add(0.0);
		for (int i = 0; i < numberOfChanges; i++) {
			staticSequenceTimes.add(defaultR.nextDouble());
		}
		Collections.sort(staticSequenceTimes);
	}
}
