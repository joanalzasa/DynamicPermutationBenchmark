package benchmarkGenerators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import problems.FSP;
import tools.ArrayUtils;

public class ModificationDynamism extends DynamicFSP{

	int changingAmount;
	int[][][] modifyingLocation;
	
	public ModificationDynamism(int changes, String band, int multiplier, double intensity,
			double modificationPercentage, FSP problem){
		super();
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		// Time of changes
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("poisson")){
			generatePoissonProcess();
		}
		magnitude = multiplier;
		
		schedulingProblem = problem;
		changingAmount = (int) Math.round((double)schedulingProblem.getJobs() * schedulingProblem.getMachines() * modificationPercentage);
		modifyingLocation = new int[numberOfChanges][changingAmount][2];
	}

	@Override
	public void generateDynamism() {		
		// Generate random locations of the cost-matrix to introduce the dynamism
		for (int i = 0; i < numberOfChanges; i++) {
			for (int j = 0; j < changingAmount; j++) {
				modifyingLocation[i][j][0] = new Random().nextInt(schedulingProblem.getJobs());
				modifyingLocation[i][j][1] = new Random().nextInt(schedulingProblem.getMachines());
			}
		}
	}
	
	@Override
	public void getDynamicProcessingTimes() {
		
	}

	@Override
	public void printDynamicInstance() {
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges + ";" + magnitude);
		for(int i = 0; i < staticSequenceTimes.length; i++){
			System.out.print(df.format(staticSequenceTimes[i]) + ";");
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
			String output = numberOfChanges + ";" + magnitude + "\n";
			for(int i = 0; i < staticSequenceTimes.length; i++){
				output += df.format(staticSequenceTimes[i]) + ";";
				for (int j = 0; j < changingAmount; j++) {
					output += ArrayUtils.tableToString(modifyingLocation[i][j]) + "|";
				}
				output += "\n"; 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
