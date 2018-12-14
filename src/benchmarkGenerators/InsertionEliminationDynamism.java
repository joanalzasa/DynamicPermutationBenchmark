package benchmarkGenerators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import problems.FSP;
import tools.ArrayListUtils;

public class InsertionEliminationDynamism extends DynamicFSP {

	FSP dynamicProblem;
	int changingAmount;
	ArrayList<Integer> decisionsBool;
	ArrayList<ArrayList<Integer>> machinePositions;
	
	

	public InsertionEliminationDynamism(int changes, String band, int multiplier, double intensity,
			int changingMachines, FSP problem) {
		super();
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		staticSequenceTimes = new ArrayList<>();
		// Time of changes
		if (frequency.contains("periodical")) 
			generatePeriodicalChanges();
		else if(frequency.contains("poisson"))
			generatePoissonProcess();
		magnitude = multiplier;	
		schedulingProblem = problem;
		
		changingAmount = changingMachines;
		dynamicProblem = problem;
		
		// Initialise changeables
		decisionsBool = new ArrayList<>();
		machinePositions = new ArrayList<>();
		dynamicProcessingTimes = new ArrayList<>();
	}

	// Abstract methods
	@Override
	public void generateDynamism() {
		// Generate dynamism
		int numberOfMachines = dynamicProblem.machines;
		for (int i = 0; i < numberOfChanges; i++) {
			// Random add/remove decision
			int boolAddDelete= (int) Math.round(Math.random());
			decisionsBool.add(boolAddDelete);
			if (boolAddDelete == 1)
				numberOfMachines++;
			else
				numberOfMachines--;
			
			//Generate randomly the index of the adding/deleting machine
			ArrayList<Integer> newMachinesIndex = new ArrayList<>();
			for (int j = 0; j < changingAmount; j++) {
				newMachinesIndex.add(new Random().nextInt(numberOfMachines - 1));
			}
			Collections.sort(newMachinesIndex);
			machinePositions.add(newMachinesIndex);
		}
	}
	
	@Override
	public void getDynamicProcessingTimes() {
		// Get original processing times and store it on a 2 dimensional array
		dynamicProcessingTimes.add(ArrayListUtils.deepCopy((dynamicProblem.processingTimes)));
		
		// Get dynamic processing times
		for (int i = 0; i < numberOfChanges; i++) {
			if(decisionsBool.get(i) == 0){
				dynamicProblem.processingTimes.remove(machinePositions.get(i));
				dynamicProblem.machines -= changingAmount;
	
			}else{
				for (int j = 0; j < changingAmount; j++){
					ArrayList<Double> jobsProcessingAverage = new ArrayList<Double>();
					ArrayList<ArrayList<Integer>> invertedProcessingTimes = ArrayListUtils.transpose(dynamicProblem.processingTimes);
					for (int k1 = 0; k1 < invertedProcessingTimes.size() ; k1++)
						jobsProcessingAverage.add(ArrayListUtils.mean(invertedProcessingTimes.get(k1)));
					double std = ArrayListUtils.stdev(jobsProcessingAverage);
					ArrayList<Integer> newMachine = new ArrayList<Integer>();
					for (int k2 = 0; k2 < invertedProcessingTimes.size() ; k2++)
						newMachine.add(Math.abs((int)((defaultR.nextGaussian() * std * magnitude) + jobsProcessingAverage.get(k2))));
					dynamicProblem.processingTimes.add(machinePositions.get(i).get(j), newMachine);
					dynamicProblem.machines++;
				}
			}
			dynamicProcessingTimes.add(ArrayListUtils.deepCopy((dynamicProblem.processingTimes)));
		}
	}
	
	@Override
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 0; i < numberOfChanges; i++){
			System.out.println(df.format(staticSequenceTimes.get(i + 1)) + ";" + decisionsBool.get(i) + ";" + machinePositions.get(i).toString()); 
		}
	}
	
	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = "Number of changes, number of job, number of machines \n";
			output += numberOfChanges + "\t\t\t" + schedulingProblem.jobs + "\t\t\t" + schedulingProblem.machines + "\n";
			for (int i = 0; i < staticSequenceTimes.size(); i++){
				output += "Change time:" + df.format(staticSequenceTimes.get(i)) +"\n Processing times: \n";
				for (int j = 0; j < dynamicProcessingTimes.get(i).size(); j++) {
					for (int k = 0; k < dynamicProcessingTimes.get(i).get(j).size(); k++)
						output += dynamicProcessingTimes.get(i).get(j).get(k) + "\t";
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
