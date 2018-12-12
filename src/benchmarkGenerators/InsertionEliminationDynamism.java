package benchmarkGenerators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import problems.FSP;
import tools.ArrayListUtils;
import tools.ArrayUtils;

public class InsertionEliminationDynamism extends DynamicFSP {

	FSP dynamicProblem;
	int changingAmount;
	int[] decisionsBool;
	int[][] machinePositions;
	
	

	public InsertionEliminationDynamism(int changes, String band, int multiplier, double intensity,
			int changingMachines, FSP problem) {
		super();
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		// Time of changes
		if (frequency.contains("periodical")) 
			generatePeriodicalChanges();
		else if(frequency.contains("poisson"))
			generatePoissonProcess();
		
		magnitude = multiplier;	
		changingAmount = changingMachines;
		schedulingProblem = problem;
		dynamicProblem = problem;
		
		// Initialise changeables
		decisionsBool = new int[numberOfChanges];
		machinePositions = new int[numberOfChanges][changingAmount];
		dynamicProcessingTimes = new int[numberOfChanges + 1][schedulingProblem.machines][schedulingProblem.jobs];
	}

	// Abstract methods
	@Override
	public void generateDynamism() {
		// Generate dynamism
		int numberOfMachines = dynamicProblem.machines;
		for (int i = 0; i < numberOfChanges; i++) {
			// Random add/remove decision
			int boolAddDelete= (int) Math.round(Math.random());
			decisionsBool[i] = boolAddDelete;
			if (boolAddDelete == 1)
				numberOfMachines++;
			else
				numberOfMachines--;
			
			//Generate randomly the index of the adding/deleting machine
			for (int j = 0; j < changingAmount; j++) {
				machinePositions[i][j] = new Random().nextInt(numberOfMachines);
			}
			Arrays.sort(machinePositions[i]);
		}
	}
	
	@Override
	public void getDynamicProcessingTimes() {
		// Get original processing times and store it on a 2 dimensional array
		for (int i = 0; i < dynamicProblem.processingTimes.size() ; i++)
			dynamicProcessingTimes[0][i] = ArrayUtils.arrayListToIntegerArray(dynamicProblem.processingTimes.get(i));
		
		// Get dynamic processing times
		for (int i = 1; i < dynamicProcessingTimes.length; i++) {
				if(decisionsBool[i - 1] == 0){
					for (int j = 0; j < changingAmount; j++){
						dynamicProblem.processingTimes.remove(machinePositions[i - 1][j]);
						dynamicProblem.machines--;
					}
				}else{
					for (int j = 0; j < changingAmount; j++){
						ArrayList<Double> jobsProcessingAverage = new ArrayList<Double>();
						ArrayList<ArrayList<Integer>> invertedProcessingTimes = ArrayListUtils.transpose(dynamicProblem.processingTimes);
						for (int k1 = 0; k1 < invertedProcessingTimes.size() ; k1++){
							jobsProcessingAverage.add(ArrayListUtils.mean(invertedProcessingTimes.get(k1)));
						}
						double std = ArrayListUtils.stdev(jobsProcessingAverage);
						ArrayList<Integer> newMachine = new ArrayList<Integer>();
						for (int k2 = 0; k2 < invertedProcessingTimes.size() ; k2++){
							newMachine.add(Math.abs((int)((defaultR.nextGaussian() * std * magnitude) + jobsProcessingAverage.get(k2))));
						}
						dynamicProblem.processingTimes.add(machinePositions[i - 1][j], newMachine);
						dynamicProblem.machines++;
					}
				}
				for (int j = 0; j < dynamicProblem.processingTimes.size() ; j++)
					dynamicProcessingTimes[i][j] = ArrayUtils.arrayListToIntegerArray(dynamicProblem.processingTimes.get(j));
		}
	}
	
	@Override
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 0; i < numberOfChanges; i++){
			System.out.println(df.format(staticSequenceTimes[i + 1]) + ";" + decisionsBool[i] + ";" + ArrayUtils.tableToString(machinePositions[i])); 
		}
	}
	
	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = "Number of changes, number of job, number of machines \n";
			output += numberOfChanges + "\t\t\t" + schedulingProblem.jobs + "\t\t\t" + schedulingProblem.machines + "\n";
			for (int i = 0; i < staticSequenceTimes.length; i++){
				output += "Change time:" + df.format(staticSequenceTimes[i]) +"\n Processing times: \n";
				for (int j = 0; j < schedulingProblem.machines; j++) {
					for (int k = 0; k < schedulingProblem.jobs; k++)
						output += dynamicProcessingTimes[i][j][k] + "\t";
					output += "\n";
				} 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


	
}
