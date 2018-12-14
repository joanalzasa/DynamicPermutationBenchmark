/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package launchers;

import java.io.IOException;

import benchmarkGenerators.Dynamism;
import benchmarkGenerators.InsertionEliminationDynamism;
import benchmarkGenerators.RotationDynamism;
import benchmarkGenerators.ModificationDynamism;
import problems.FSP;

public class mainDynamicBG{

	public static void main(String[] args) throws IOException{
		
		// STATIC PROBLEM
		String staticProblemPath = "./inputData/instances/FSP/tai20_5_0.fsp";
		
		// DYNAMISM PARAMETERS:
//		String changeType = "rotation";
//		String changeType = "insertRemove";
		String changeType = "modification";
		
		// GENERAL PARAMETERS:
//		String changeFrequency = "periodical";
		int numOfChanges = 2;
		String changeFrequency = "poisson";
		double lambda = 3;
		
		int changeMagnitude = 1;
		String resultsPath = "./inputData/dynamicInstances/";
		String saveAs;
		
		long startTime = System.currentTimeMillis();
		
		// Benchmark generator
		FSP PFSP = new FSP();
		PFSP.read(staticProblemPath);
		
		Dynamism dynamicInstance;
		saveAs = "DFSP-" + PFSP.jobs + "x" + PFSP.machines + "-";
		if(changeType.contains("rotation")){
			saveAs += "Cr-M" + changeMagnitude;	
			dynamicInstance = new RotationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, PFSP, "cayley");
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp" + dynamicInstance.numberOfChanges + ".dfsp" ;
			else
				saveAs += "-T" + dynamicInstance.numberOfChanges +".dfsp";
			
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(resultsPath, saveAs);
		}else if(changeType.contains("insertRemove")){
			saveAs += "Ci-M" + changeMagnitude;	
			int changingAmount = 1;
			dynamicInstance = new InsertionEliminationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingAmount, PFSP);
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp" + dynamicInstance.numberOfChanges + ".dfsp" ;
			else
				saveAs += "-T" + dynamicInstance.numberOfChanges +".dfsp";	
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(resultsPath, saveAs);
		}else if(changeType.contains("modification")){
			saveAs += "Cm-M" + changeMagnitude;	
			double changingPercentage = 0.2;
			dynamicInstance = new ModificationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingPercentage, PFSP);
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp" + dynamicInstance.numberOfChanges + ".dfsp" ;
			else
				saveAs += "-T" + dynamicInstance.numberOfChanges +".dfsp";	
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(resultsPath, saveAs);
		}
		
		
		
//		saveAs = "dynProfile-n" + problemSize + "-f" + frequency + "-m" + distance +".txt";

		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
			
	

	}

}
