package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collections;

import problems.representations.Permutation;
import tools.ArrayListUtils;
import tools.PermutationUtils;

public class FSP extends PermutationProblem implements OptimisationProblem{

	public int jobs;
	public int machines;
	public ArrayList<ArrayList<Integer>> processingTimes;
//	int seed;
	
	public FSP(){
		processingTimes = new ArrayList<ArrayList<Integer>>();
	}
	
	@SuppressWarnings("resource")
	@Override
	public void read(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));

			String line = "";
			int counter = 0;
			while (reader.ready()) {
				line = reader.readLine().trim();
				if (counter == 1) {
					String[] information = line.trim().split("\\s+");
					jobs = Integer.parseInt(information[0]);
					machines = Integer.parseInt(information[1]);
//					seed = Integer.parseInt(information[2]);
//					globalOptimum = Double.parseDouble(information[3]);
				}
				if (counter > 2 && counter <= (machines + 2)) {
					ArrayList<Integer> arr = new ArrayList<Integer>();
					// System.out.println("line");
					// System.out.println(line);
					String lineArray[] = line.trim().split("\\s+");
					for (int i = 0; i < jobs; i++) {
						// System.out.println("arrays");
						// System.out.println(lineArray[i]);
						int val = Integer.parseInt(lineArray[i]);
						arr.add(val);
					}
					processingTimes.add(arr);

				}
				counter++;
			}
			
			// Initialisation
			problemSize = jobs;
			identityPermutation = PermutationUtils.generateIdentityPermutation(problemSize);
		} catch (IOException e) {
			System.out.println("Couldn't find file: " + fileName);
		}
	}
	
	@Override
	public void evaluate(Permutation individual) {
		ArrayList<ArrayList<Integer>> transposedProcessingTimes = ArrayListUtils.transpose(processingTimes);
		
		ArrayList<Integer> totalFlowTime = ArrayListUtils.cumSum(transposedProcessingTimes.get(identityPermutation.get(individual.getSolution().get(0))));

		for (int j = 1; j < jobs; j++) {
			totalFlowTime.set(0, totalFlowTime.get(0) + transposedProcessingTimes.get(identityPermutation.get(individual.getSolution().get(j))).get(0));
			for (int m = 1; m < machines; m++) {
				int max = Math.max(totalFlowTime.get(m-1),totalFlowTime.get(m)); 
				totalFlowTime.set(m, max + transposedProcessingTimes.get(identityPermutation.get(individual.getSolution().get(j))).get(m));
			}
		}
		
		individual.setFitness(totalFlowTime.get(totalFlowTime.size() - 1));
	}

//	public int getSeed() {
//		return seed;
//	}
//
//	public void setSeed(int seed) {
//		this.seed = seed;
//	}


}
