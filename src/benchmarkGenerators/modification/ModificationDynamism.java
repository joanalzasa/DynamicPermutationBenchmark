package benchmarkGenerators.modification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import benchmarkGenerators.Dynamism;
import tools.ArrayUtils;

public class ModificationDynamism extends Dynamism{

	int numberOfJobs;
	int numberOfMachines;
	int changingAmount;
	int[][][] modifyingLocation;
	
	public ModificationDynamism(int changes, String band, int multiplier, double averageTimeSpace,
			double modificationPercentage, int jobs, int machines){
		super();
		numberOfChanges = changes;
		frequency = band;
		magnitude = multiplier;
		lambda = averageTimeSpace;
		
		numberOfJobs = jobs;
		numberOfMachines = machines;
		changingAmount = (int) Math.round((double)numberOfMachines * numberOfJobs * modificationPercentage);
		modifyingLocation = new int[numberOfChanges][changingAmount][2];
	}

	@Override
	public void generateDynamism() {
		// Time of changes
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("exponential")){
			generateRandomExponentialDistribution();
		}
		
		// Generate random locations of the cost-matrix to introduce the dynamism
		for (int i = 0; i < numberOfChanges; i++) {
			for (int j = 0; j < changingAmount; j++) {
				modifyingLocation[i][j][0] = new Random().nextInt(numberOfJobs);
				modifyingLocation[i][j][1] = new Random().nextInt(numberOfMachines);
			}
		}
	}

	@Override
	public void printDynamicInstance() {
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 0; i < changeTime.length; i++){
			System.out.print(df.format(changeTime[i]) + ";");
			for (int j = 0; j < changingAmount; j++) {
				System.out.print("(" + ArrayUtils.tableToString(modifyingLocation[i][j]) + ")");
			}
			System.out.println();
		}
	}

	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String out = numberOfChanges + ";" + magnitude + "\n";
			for(int i = 0; i < changeTime.length; i++){
				out += df.format(changeTime[i]) + ";";
				for (int j = 0; j < changingAmount; j++) {
					out += ArrayUtils.tableToString(modifyingLocation[i][j]) + "|";
				}
				out += "\n"; 
			}
			br.write(out);
	        br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
