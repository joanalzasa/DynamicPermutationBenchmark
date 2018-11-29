package benchmarkGenerators.insertionElimination;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import benchmarkGenerators.Dynamism;
import tools.ArrayUtils;

public class GeneralInsertionEliminationDynamism extends Dynamism {

	int changingAmount;
	int[] decisionsBool;
	
	

	public GeneralInsertionEliminationDynamism(int changes, String band, int multiplier, double averageTimeSpace,
			int changingMachines) {
		super();
		numberOfChanges = changes;
		frequency = band;
		magnitude = multiplier;
		lambda = averageTimeSpace;
		

		changingAmount = changingMachines;
		decisionsBool = new int[numberOfChanges];
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
			decisionsBool[i] = boolAddDelete;
		}
	}
	
	@Override
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges);
		System.out.println(magnitude);
		System.out.println(changingAmount);
		for(int i = 0; i < changeTime.length; i++){
			System.out.println(df.format(changeTime[i]) + "," + decisionsBool[i]); 
		}
	}
	
	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = numberOfChanges + "\n" + magnitude + "\n" + changingAmount + "\n";
			for(int i = 0; i < changeTime.length; i++){
				output += df.format(changeTime[i]) + "," + decisionsBool[i] + "\n"; 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
