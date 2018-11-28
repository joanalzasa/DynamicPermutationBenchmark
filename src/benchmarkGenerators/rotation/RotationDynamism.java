/**
 * @author 1715818
 *
 * Joan Alza Santos
 */
package benchmarkGenerators.rotation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import benchmarkGenerators.*;
import tools.ArrayUtils;

public class RotationDynamism extends Dynamism{

	String distanceType;
	static int permSize;
	int[][] permutations;
	
	
	public RotationDynamism(int size, int changes, String rate, int distance, String type, double averageTimeSpace){
		super();
		permSize = size;
		numberOfChanges = changes;
		frequency = rate;
		magnitude = distance;
		distanceType = type;
		lambda = averageTimeSpace;

		this.permutations = new int[numberOfChanges][permSize];
	}
	

	// Abstract method
	@Override
	public void generateDynamism() {
		if (frequency.contains("periodical")) {
			generatePeriodicalChanges();
		}else if(frequency.contains("exponential")){
			generateRandomExponentialDistribution();
		}
		
		// Generate identity permutation
		int[] identityPermutation = new int[permSize];
		for (int i = 0; i < permSize; i++) {
			identityPermutation[i] = i;
		}
		
		// Disturb identity permutation by distance
		if(distanceType.contains("cayley")){
			for (int j = 0; j < numberOfChanges; j++) {
				permutations[j] = changeIdentityPermutation(identityPermutation, magnitude);
				System.out.println("Cayley distance: " + tools.ArrayUtils.getCayleyDistance(permutations[j], identityPermutation));
				identityPermutation = permutations[j];
			}
		}	
	}
	
	public int[] changeIdentityPermutation(int[] identity, int distance){
		int k = permSize - (int) distance;
		int[] sigma = sample(k);
		int[] newIdentity = compose(identity, sigma);
		return newIdentity;
	}
	
	public int[] sample(int k) {
		int[] newsol = new int[permSize];
		
		int[] X = generateRandomBinaryArray(permSize,k);
		generatePermutationFromX(X,newsol);

		return newsol;
	}
	
	// Create a binary array "X" that contains 1 "Cayley distance" times
	public int[] generateRandomBinaryArray(int n, int k){
		List<Integer> solution = new ArrayList<Integer>(Collections.nCopies(n-1, 1));
		for (int i = 0; i < k -1; i++) {
		    solution.set(i,0);
		}
		Collections.shuffle(solution);
		
		int[] X = new int[n-1];
		for(int i = 0; i < solution.size(); i++) 
			X[i] = solution.get(i);
		return X;
	}
	
	public int[] generatePermutationFromX(int[] X, int[] sigma){
		Random rand = new Random();
		int random, aux,i;
		for (i = 0; i < permSize; i++)
			sigma[i]=i;
		for (i = 0; i < X.length; i++){
			if (X[i]==1){
				random = i + 1 + rand.nextInt(permSize-1-i); // random=[i+1,n-1]
				aux = sigma[random];
				sigma[random] = sigma[i];
				sigma[i] = aux;
			}
		}
		return sigma;
	}
	
	private int[] compose(int[] identity, int[] sigma) {
		int[] composedPerm = new int[identity.length];
		for (int i = 0; i < identity.length; i++) {
			composedPerm[i] = identity[sigma[i]];
		}
		return composedPerm;
	}
	
	public void printDynamicInstance(){
		DecimalFormat df = new DecimalFormat("#.####");
		System.out.println(frequency);
		for(int i = 0; i < numberOfChanges; i++){
			System.out.println(df.format(changeTime[i]) + ";" + ArrayUtils.tableToString(permutations[i])); 
		}
	}

}
