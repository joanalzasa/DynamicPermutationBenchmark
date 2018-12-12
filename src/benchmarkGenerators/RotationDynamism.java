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
import java.util.Arrays;
import java.util.Random;

import problems.FSP;
import tools.ArrayUtils;
import tools.PermutationUtils;

public class RotationDynamism extends DynamicFSP{

	String distanceType;
	int[][] permutations;
	
	// Constructor
	public RotationDynamism( int changes, String band, int distance, double intensity,
			FSP problem, String type){
		super();
		
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		// Time of changes
		if (frequency.contains("periodical"))
			generatePeriodicalChanges();
		else if(frequency.contains("poisson"))
			generatePoissonProcess();
		magnitude = distance;
				
		distanceType = type;
		schedulingProblem = problem;
		
		// Initialise changeables
		permutations = new int[numberOfChanges + 1][schedulingProblem.jobs];
		dynamicProcessingTimes = new int[numberOfChanges + 1][schedulingProblem.machines][schedulingProblem.jobs];
	}
	

	// Abstract methods
	@Override
	public void generateDynamism() {		
		// Generate identity permutation
		for (int i = 0; i < schedulingProblem.jobs; i++) 
			permutations[0][i] = i;
		
		// Disturb identity permutation by distance
		if(distanceType.contains("cayley")){
			for (int j = 1; j < permutations.length; j++)
				permutations[j] = changeIdentityPermutation(permutations[0], magnitude);
		}	
	}
	
	public void getDynamicProcessingTimes(){
		// Initialise variables
		ArrayList<ArrayList<Integer>> processingTimesAL = schedulingProblem.processingTimes;
		
		// Get original processing times and store it on a 2 dimensional array
		for (int i = 0; i < processingTimesAL.size() ; i++)
			dynamicProcessingTimes[0][i] = ArrayUtils.arrayListToIntegerArray(processingTimesAL.get(i));
		
		// Get dynamic processing times
		for (int i = 1; i < dynamicProcessingTimes.length; i++) {
			for (int j = 0; j < schedulingProblem.machines; j++) {
				for (int k = 0; k < schedulingProblem.jobs; k++)
					dynamicProcessingTimes[i][j][k] = dynamicProcessingTimes[0][j][permutations[i][k]];			
			}
		}
		
	}
	
	public int[] changeIdentityPermutation(int[] identity, int distance){
		int k = schedulingProblem.jobs - (int) distance;
		int[] sigma = sample(k);
		int[] newIdentity = PermutationUtils.compose(identity, sigma);
		return newIdentity;
	}
	
	public int[] sample(int k) {
		int[] newsol = new int[schedulingProblem.jobs];
		
		int[] X = generateRandomBinaryArray(schedulingProblem.jobs, k);
		generatePermutationFromX(X,newsol);

		return newsol;
	}
	
	// Create a binary array "X" that contains 1 "Cayley distance" times
	public int[] generateRandomBinaryArray(int n, int k){
		int[] binaryArray = new int[n-1];
		Arrays.fill(binaryArray, 1);
		
		for (int i = 0; i < k - 1; i++)
			binaryArray[i] = 0;
		
		ArrayUtils.shuffleArray(binaryArray);
		
		int[] X = new int[n-1];
		for(int i = 0; i < binaryArray.length; i++) 
			X[i] = binaryArray[i];
		return X;
	}
	
	public int[] generatePermutationFromX(int[] X, int[] sigma){
		Random rand = new Random();
		int random, aux,i;
		for (i = 0; i < schedulingProblem.jobs ; i++)
			sigma[i]=i;
		for (i = 0; i < X.length; i++){
			if (X[i]==1){
				random = i + 1 + rand.nextInt(schedulingProblem.jobs - 1 - i); // random=[i+1,n-1]
				aux = sigma[random];
				sigma[random] = sigma[i];
				sigma[i] = aux;
			}
		}
		return sigma;
	}

	
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(numberOfChanges);
		for(int i = 0; i < staticSequenceTimes.length; i++)
			System.out.println(df.format(staticSequenceTimes[i]) + ";" + ArrayUtils.tableToString(permutations[i])); 
	}


	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = "Number of changes, number of job, number of machines \n";
			output += numberOfChanges + "\t\t\t" + schedulingProblem.jobs + "\t\t\t" + schedulingProblem.machines + "\n";
			for (int i = 0; i < staticSequenceTimes.length; i++){
				output += "Change time:" + df.format(staticSequenceTimes[i]) +"\n Processing times: \n";
				for (int j = 0; j < schedulingProblem.machines; j++) {
					for (int k = 0; k < schedulingProblem.jobs; k++)
						output += dynamicProcessingTimes[i][j][k] + "\t";
					output += "\n";
				} 
			}
			br.write(output);
	        br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
