/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import java.io.IOException;
import java.util.Random;

import benchmarkGenerators.insertionElimination.InsertionEliminationDynamism;
import benchmarkGenerators.modification.ModificationDynamism;
import benchmarkGenerators.rotation.RotationDynamism;

public class mainDynamicBG{

	public static void main(String[] args) throws IOException{
		
		// DYNAMISM PARAMETERS:
//		String changeType = "rotation";
//		String changeType = "insertRemove";
		String changeType = "modification";
		
		// GENERAL PARAMETERS:
		int jobs = 20;
		int machines = 50;
		int numOfChanges = 3;
//		String changeFrequency = "periodical";
		String changeFrequency = "exponential";
		double lambda = 1;
		int changeMagnitude = 5;
		String resultsPath = "./inputData/dynamismGenerator";
		String saveAs;
		
		long startTime = System.currentTimeMillis();
		
		// Benchmark generator
		if(changeType.contains("rotation")){
			String distanceType = "cayley";
			Dynamism changeInstance = new RotationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, jobs, distanceType);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
		}else if(changeType.contains("insert")){
			int changingAmount = 4;
			Dynamism changeInstance = new InsertionEliminationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingAmount, machines);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
		}else if(changeType.contains("modification")){
			double changingPercentage = 0.4;
			Dynamism changeInstance = new ModificationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingPercentage, jobs, machines);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
		}
		
		
		
//		saveAs = "dynProfile-n" + problemSize + "-f" + frequency + "-m" + distance +".txt";

		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
			
	

	}

}
