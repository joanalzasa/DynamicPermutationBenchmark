/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import java.io.IOException;

import benchmarkGenerators.insertionElimination.GeneralInsertionEliminationDynamism;
import benchmarkGenerators.modification.GeneralModificationDynamism;
import benchmarkGenerators.rotation.GeneralRotationDynamism;

public class mainGeneralDynamicBG{

	public static void main(String[] args) throws IOException{
		
		// DYNAMISM PARAMETERS:
//		String changeType = "rotation";
		String changeType = "insertRemove";
//		String changeType = "modification";
		
		// GENERAL PARAMETERS:
		int numOfChanges = 3;
//		String changeFrequency = "periodical";
		String changeFrequency = "exponential";
		double lambda = 1;
		int changeMagnitude = 5;
		String resultsPath = "./inputData/dynamismGenerator/general/";
		String saveAs;
		
		long startTime = System.currentTimeMillis();
		
		// Benchmark generator
		if(changeType.contains("rotation")){
			saveAs = "generalDynProfile-Cr-M" + changeMagnitude;
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp.txt";
			else
				saveAs += "-T" + lambda +".txt";
			String distanceType = "cayley";
			Dynamism changeInstance = new GeneralRotationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, distanceType);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
			changeInstance.createDynamicInstance(resultsPath, saveAs);
		}else if(changeType.contains("insert")){
			saveAs = "generalDynProfile-Ci-M" + changeMagnitude;
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp.txt";
			else
				saveAs += "-T" + lambda +".txt";
			int changingAmount = 4;
			Dynamism changeInstance = new GeneralInsertionEliminationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingAmount);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
			changeInstance.createDynamicInstance(resultsPath, saveAs);
		}else if(changeType.contains("modification")){
			saveAs = "generalDynProfile-Cm-M" + changeMagnitude;
			if (changeFrequency.contains("periodical"))
				saveAs += "-Tp.txt";
			else
				saveAs += "-T" + lambda +".txt";
			double changingPercentage = 0.4;
			Dynamism changeInstance = new GeneralModificationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingPercentage);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
			changeInstance.createDynamicInstance(resultsPath, saveAs);
		}
		
		
		
//		saveAs = "dynProfile-n" + problemSize + "-f" + frequency + "-m" + distance +".txt";

		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
			
	

	}

}
