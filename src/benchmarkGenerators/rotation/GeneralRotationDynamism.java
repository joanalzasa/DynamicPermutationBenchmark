/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators.rotation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import benchmarkGenerators.*;

public class GeneralRotationDynamism extends Dynamism{

	String distanceType;
	
	// Constructor
	public GeneralRotationDynamism( int changes, String band, int distance, double averageTimeSpace, String type){
		super();
		
		numberOfChanges = changes;
		frequency = band;
		magnitude = distance;
		lambda = averageTimeSpace;
		
		distanceType = type;
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
	}
	
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges);
		System.out.println(magnitude);
		for(int i = 0; i < changeTime.length; i++){
			System.out.println(df.format(changeTime[i])); 
		}
	}


	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = numberOfChanges + "\n" + magnitude + "\n";
			for(int i = 0; i < changeTime.length; i++){
				output += df.format(changeTime[i]) + "\n"; 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
