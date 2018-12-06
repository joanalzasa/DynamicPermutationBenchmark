package benchmarkGenerators.modification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import benchmarkGenerators.Dynamism;

public class GeneralModificationDynamism extends Dynamism{


	double changingAmount;
	
	public GeneralModificationDynamism(int changes, String band, int multiplier, double averageTimeSpace,
			double modificationPercentage){
		super();
		numberOfChanges = changes;
		frequency = band;
		magnitude = multiplier;
		lambda = averageTimeSpace;
		changingAmount = modificationPercentage;
	}

	@Override
	public void generateDynamism() {
		// Time of changes
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("exponential")){
			generateRandomExponentialDistribution();
		}
	}

	@Override
	public void printDynamicInstance() {
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges);
		System.out.println(magnitude);
		System.out.println(changingAmount);
		for(int i = 0; i < changeTime.length; i++){
			System.out.print(df.format(changeTime[i]) + ";");
			System.out.println();
		}
	}

	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = numberOfChanges + ";" + magnitude + "\n" + changingAmount + "\n";
			for(int i = 0; i < changeTime.length; i++){
				output += df.format(changeTime[i]) + "\n"; 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
