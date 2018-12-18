package benchmarkGenerators;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import problems.FSP;
import tools.ArrayListUtils;
import tools.ArrayUtils;

public class ModificationDynamism extends DynamicFSP{

	FSP dynamicProblem;
	int changingAmount;
	ArrayList<ArrayList<int[]>> modifyingLocation;
	
	public ModificationDynamism(int changes, String band, int multiplier, double intensity,
			double modificationPercentage, FSP problem){
		super();
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		staticSequenceTimes = new ArrayList<>();
		// Time of changes
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("poisson")){
			generatePoissonProcess();
		}
		magnitude = multiplier;
		schedulingProblem = problem;
		dynamicProblem = problem;
		
		changingAmount = (int) Math.round(schedulingProblem.jobs * schedulingProblem.machines * modificationPercentage);
		
		// Initialise changeables
		modifyingLocation = new ArrayList<>();
		dynamicProcessingTimes = new ArrayList<>();
	}

	@Override
	public void generateDynamism() {		
		// Generate random locations of the cost-matrix to introduce the dynamism
		for (int i = 0; i < numberOfChanges; i++) {
			ArrayList<int[]> modifiyingPositions = new ArrayList<>();
			for (int j = 0; j < changingAmount; j++) {
				int[] modifications = {new Random().nextInt(schedulingProblem.machines), new Random().nextInt(schedulingProblem.jobs)};
				modifiyingPositions.add(modifications);
			}
			modifyingLocation.add(modifiyingPositions);
		}
	}
	
	@Override
	public void getDynamicProcessingTimes() {
		// Get original processing times and store it on a 2 dimensional array
		dynamicProcessingTimes.add(ArrayListUtils.deepCopy((dynamicProblem.processingTimes)));
				
		// Get dynamic processing times
		for (int i = 0; i < numberOfChanges; i++) {
			ArrayList<ArrayList<Integer>> tranposedProcessingTimes = ArrayListUtils.transpose(dynamicProblem.processingTimes);
			for ( int j = 0; j < modifyingLocation.get(i).size(); j++){
				int machineIndex = modifyingLocation.get(i).get(j)[0];
				int jobIndex = modifyingLocation.get(i).get(j)[1];
				int[] jobs = ArrayUtils.arrayListToIntegerArray(tranposedProcessingTimes.get(jobIndex));
				double sdJob = ArrayUtils.stdev(ArrayUtils.copyFromIntArray(jobs));
				int newPT = Math.abs((int)((defaultR.nextGaussian() * sdJob * magnitude) + dynamicProblem.processingTimes.get(machineIndex).get(jobIndex)));
				dynamicProblem.processingTimes.get(machineIndex).set(jobIndex, newPT);
			}
			dynamicProcessingTimes.add(ArrayListUtils.deepCopy((dynamicProblem.processingTimes)));
		}
	}

	@Override
	public void printDynamicInstance() {
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 1; i < staticSequenceTimes.size(); i++){
			System.out.print(df.format(staticSequenceTimes.get(i)) + ";");
			for (int j = 0; j < modifyingLocation.get(i-1).size(); j++)
				System.out.print(Arrays.toString(modifyingLocation.get(i-1).get(j)));
			System.out.println();
		}
	}
	
}
