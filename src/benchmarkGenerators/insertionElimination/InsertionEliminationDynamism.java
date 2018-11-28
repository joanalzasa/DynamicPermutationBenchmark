package benchmarkGenerators.insertionElimination;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import benchmarkGenerators.Dynamism;
import tools.ArrayUtils;

public class InsertionEliminationDynamism extends Dynamism {

	int numberOfMachines;
	int changingAmount;
	int[] decisionsBool;
	int[][] machinePositions;
	
	

	public InsertionEliminationDynamism(int changes, String band, int multiplier, double averageTimeSpace,
			int changingMachines, int machines) {
		super();
		numberOfChanges = changes;
		frequency = band;
		magnitude = multiplier;
		lambda = averageTimeSpace;
		
		numberOfMachines = machines;
		changingAmount = changingMachines;
		decisionsBool = new int[numberOfChanges];
		machinePositions = new int[numberOfChanges][changingAmount];
	}

	// Abstract methods
	@Override
	public void generateDynamism() {
		// Time of changes
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("exponential")){
			generateRandomExponentialDistribution();
		}
		
		// Generate dynamism
		for (int i = 0; i < numberOfChanges; i++) {
			// Random add/remove decision
			int boolAddDelete= (int) Math.round(Math.random());
			if (boolAddDelete == 1)
				numberOfMachines++;
			else
				numberOfMachines--;
			decisionsBool[i] = boolAddDelete;
			
			//Generate randomly the index of the adding/deleting machine
			for (int j = 0; j < changingAmount; j++) {
				machinePositions[i][j] = new Random().nextInt(numberOfMachines);
			}
			Arrays.sort(machinePositions[i]);
		}
	}
	
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 0; i < changeTime.length; i++){
			System.out.println(df.format(changeTime[i]) + ";" + decisionsBool[i] + ";" + ArrayUtils.tableToString(machinePositions[i])); 
		}
	}
	
}