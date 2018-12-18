/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package launchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import benchmarkGenerators.Dynamism;
import benchmarkGenerators.InsertionEliminationDynamism;
import benchmarkGenerators.RotationDynamism;
import benchmarkGenerators.ModificationDynamism;
import problems.FSP;

public class mainDynamicBG{

	public static void main(String[] args) throws IOException{
		
		// STATIC PROBLEM
		String staticProblemPath = "./inputData/instances/FSP/tai20_10_0.fsp";
		int nameInstance = staticProblemPath.split("/").length - 1;
		String instance = staticProblemPath.split("/")[nameInstance].substring(0, staticProblemPath.split("/")[nameInstance].lastIndexOf("."));
		
		// DYNAMISM PARAMETERS:
//		String changeType = "rotation";
//		String changeType = "insertRemove";
		String changeType = "modification";
		
		// GENERAL PARAMETERS:
//		String changeFrequency = "periodical";
		int numOfChanges = 2;
		String changeFrequency = "poisson";
		double lambda = 5;
		
		int changeMagnitude = 1;
		String resultsPath = "./inputData/dynamicInstances/";
		String saveAs;
		
		long startTime = System.currentTimeMillis();
		
		// Benchmark generator
		FSP PFSP = new FSP();
		PFSP.read(staticProblemPath);
		
		Dynamism dynamicInstance;
		saveAs = "dynamic_" + instance +"-";
		if(changeType.contains("rotation")){
			Files.createDirectories(Paths.get("./inputData/dynamicInstances/rotation/" + instance));
			resultsPath += "rotation/" + instance + "/";
			saveAs += "Cr-M" + changeMagnitude + "-T";	
			dynamicInstance = new RotationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, PFSP, "cayley");			
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(instance, resultsPath, saveAs);
		}else if(changeType.contains("insertRemove")){
			Files.createDirectories(Paths.get("./inputData/dynamicInstances/insertion/" + instance));
			resultsPath += "insertion/" + instance + "/";
			saveAs += "Ci-M" + changeMagnitude + "-T";	
			int changingAmount = 1;
			dynamicInstance = new InsertionEliminationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingAmount, PFSP);
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(instance, resultsPath, saveAs);
		}else if(changeType.contains("modification")){
			Files.createDirectories(Paths.get("./inputData/dynamicInstances/modification/" + instance));
			resultsPath += "modification/" + instance + "/";
			saveAs += "Cm-M" + changeMagnitude + "-T";	
			double changingPercentage = 0.2;
			dynamicInstance = new ModificationDynamism(numOfChanges, changeFrequency, changeMagnitude, lambda, changingPercentage, PFSP);	
			dynamicInstance.generateDynamism();
			dynamicInstance.printDynamicInstance();
			dynamicInstance.getDynamicProcessingTimes();
			dynamicInstance.createDynamicInstance(instance, resultsPath, saveAs);
		}
		
		
		
//		saveAs = "dynProfile-n" + problemSize + "-f" + frequency + "-m" + distance +".txt";

		
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
			
	

	}

}
