package problems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import representations.RandomKey;
import tools.PermutationUtils;

public class FSP extends PermutationProblem<RandomKey> implements OptimisationProblem <RandomKey>{

	int jobs;
	int machines;
	ArrayList<ArrayList<Integer>> processingTimes;
	int seed;
	
	@SuppressWarnings("resource")
	@Override
	public void read(String fileName) {

		processingTimes = new ArrayList<ArrayList<Integer>>();
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
					seed = Integer.parseInt(information[2]);
					globalOptimum = Double.parseDouble(information[3]);
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
		} catch (IOException e) {
			System.out.println("Couldn't find file: " + fileName);
		}

		// Other initialisation
		problemSize = jobs;
	}
	
	@Override
	public double evaluate(ArrayList<RandomKey> population) {
//		int m_machines = processingTimes.length;
//		int m_jobs = processingTimes[0].length;
//		int[] m_timeTable = new int[m_machines];
//		// int[] m_aux= new int[m_jobs];
//
//		for (int i = 0; i < m_machines; i++)
//			m_timeTable[i] = 0;
//		int j, z, job;
//		int machine;
//		int prev_machine = 0;
//
//		// int first_gene=genes[0];
//		int first_gene = this.identityPerm[genes[0]];
//
//		m_timeTable[0] = processingTimes[0][first_gene];
//		for (j = 1; j < m_machines; j++) {
//			m_timeTable[j] = m_timeTable[j - 1] + processingTimes[j][first_gene];
//		}
//
//		double fitness = m_timeTable[m_machines - 1];
//		for (z = 1; z < m_jobs; z++) {
//			// job=genes[z];
//			job = this.identityPerm[genes[z]];
//
//			// machine 0 is always incremental, so:
//			m_timeTable[0] += processingTimes[0][job];
//			prev_machine = m_timeTable[0];
//			for (machine = 1; machine < m_machines; machine++) {
//				m_timeTable[machine] = Math.max(prev_machine, m_timeTable[machine]) + processingTimes[machine][job];
//				prev_machine = m_timeTable[machine];
//			}
//
//			fitness += m_timeTable[m_machines - 1];
//		}
//
//		// return -fitness;
//		return fitness;
		return 0;
	}




}
