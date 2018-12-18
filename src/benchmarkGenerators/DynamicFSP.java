/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import problems.FSP;

public abstract class DynamicFSP extends Dynamism{
	
	// Global variables
	public FSP schedulingProblem;
	public ArrayList<ArrayList<ArrayList<Integer>>> dynamicProcessingTimes ;
	
	
	// Abstract methods
	public abstract void generateDynamism();
	public abstract void getDynamicProcessingTimes();
	public abstract void printDynamicInstance();

	@Override
	public void createDynamicInstance(String instance, String path, String saveAs) {
		try {
			DecimalFormat df = new DecimalFormat("#.####");
			for (int i = 0; i < staticSequenceTimes.size(); i++){
				String output = "Number of job, number of machines \n";
				output += schedulingProblem.jobs + "\t\t\t" + schedulingProblem.machines + "\n Processing times: \n";
				BufferedWriter br = new BufferedWriter(new FileWriter((String) (path + "" + saveAs + df.format(staticSequenceTimes.get(i)) +".txt")));
				for (int j = 0; j < dynamicProcessingTimes.get(i).size(); j++) {
					for (int k = 0; k < dynamicProcessingTimes.get(i).get(j).size(); k++)
						output += dynamicProcessingTimes.get(i).get(j).get(k) + "\t";
					output += "\n";
				} 
				br.write(output);
		        br.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
