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
import tools.ArrayListUtils;
import tools.ArrayUtils;
import tools.PermutationUtils;

public class RotationDynamism extends DynamicFSP{

	String distanceType;
	ArrayList<ArrayList<Integer>> permutations;
	
	// Constructor
	public RotationDynamism( int changes, String band, int distance, double intensity,
			FSP problem, String type){
		super();
		
		numberOfChanges = changes;
		frequency = band;
		lambda = intensity;
		staticSequenceTimes = new ArrayList<>();;
		// Time of changes
		if (frequency.contains("periodical"))
			generatePeriodicalChanges();
		else if(frequency.contains("poisson"))
			generatePoissonProcess();	
		magnitude = distance;
		schedulingProblem = problem;
				
		distanceType = type;
		
		// Initialise changeables
		permutations = new ArrayList<>();
		dynamicProcessingTimes = new ArrayList<>();
	}
	

	// Abstract methods
	@Override
	public void generateDynamism() {		
		// Generate identity permutation
		ArrayList<Integer> permutation = new ArrayList<>();
		for (int i = 0; i < schedulingProblem.jobs; i++) 
			permutation.add(i);
		permutations.add(permutation);
		
		// Disturb identity permutation by distance
		if(distanceType.contains("cayley")){
			for (int j = 0; j < numberOfChanges; j++){
				permutations.add(ArrayListUtils.arrayToArrayList(changeIdentityPermutation(ArrayUtils.arrayListToIntegerArray(permutations.get(j)), magnitude))); 
//				System.out.println("Cayley distance: " + tools.PermutationUtils.getCayleyDistance(ArrayUtils.arrayListToIntegerArray(permutations.get( j+1 )), ArrayUtils.arrayListToIntegerArray(permutation)));
			}
				
		}	
	}
	
	public void getDynamicProcessingTimes(){		
		// Invert processing times to distribute it by jobs
		ArrayList<ArrayList<Integer>> transposedProcessingT = ArrayListUtils.transpose(schedulingProblem.processingTimes);
		
		// Get dynamic processing times
		for (int i = 0; i < permutations.size(); i++) {
			ArrayList<ArrayList<Integer>> processingT = new ArrayList<>();
			for (int j = 0; j < schedulingProblem.jobs; j++)
				processingT.add(transposedProcessingT.get(permutations.get(i).indexOf(j)));
			dynamicProcessingTimes.add(ArrayListUtils.transpose(processingT));
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
		for(int i = 0; i < staticSequenceTimes.size(); i++)
			System.out.println(df.format(staticSequenceTimes.get(i).doubleValue()) + ";" + ArrayUtils.tableToString(ArrayUtils.arrayListToIntegerArray(permutations.get(i)))); 
	}


	@Override
	public void createDynamicInstance(String path, String saveAs) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(path + "" + saveAs));
			DecimalFormat df = new DecimalFormat("#.####");
			String output = "Number of changes, number of job, number of machines \n";
			output += numberOfChanges + "\t\t\t" + schedulingProblem.jobs + "\t\t\t" + schedulingProblem.machines + "\n";
			for (int i = 0; i < staticSequenceTimes.size(); i++){
				output += "Change time:" + df.format(staticSequenceTimes.get(i)) +"\n Processing times: \n";
				for (int j = 0; j < schedulingProblem.machines; j++) {
					for (int k = 0; k < schedulingProblem.jobs; k++)
						output += dynamicProcessingTimes.get(i).get(j).get(k) + "\t";
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
