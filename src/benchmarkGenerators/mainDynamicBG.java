/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators;

import java.io.IOException;
import java.util.Random;

import benchmarkGenerators.rotation.RotationDynamism;

public class mainDynamicBG{

	public static void main(String[] args) throws IOException{
		
		// PARAMETERS:
		String typeOfChange = "rotation";
		String typeOfDistance = "cayley";
		
//		String typeOfChange = "insertRemove";
		
//		String typeOfChange = "modification";
		
		int magnitude = 5;
		int numOfChanges = 3;
//		String frequency = "periodical";
		String frequency = "exponential";
		double lambda = 1;
		
		// ADDITIVE:
//		String resultsPath = "./inputData/dynamismGenerator/rotation";
//		String saveAs;
		int problemSize = 20;
		
		if(typeOfChange.contains("rotation") && typeOfDistance.contains("cayley")){
			Dynamism changeInstance = new RotationDynamism(problemSize, numOfChanges, frequency, magnitude, typeOfDistance, lambda);
			changeInstance.generateDynamism();
			changeInstance.printDynamicInstance();
		}
		
		long startTime = System.currentTimeMillis();
		
//		saveAs = "dynProfile-n" + problemSize + "-f" + frequency + "-m" + distance +".txt";

		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
			
	

	}

}
