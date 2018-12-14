package algorithms.rkeda;

import java.util.ArrayList;

import algorithms.EvolutionaryAgorithm;
import algorithms.rkeda.coolings.Cooling;
import problems.representations.RandomKey;

public class RKEDA extends EvolutionaryAgorithm<RandomKey>{

	String coolingSchedule;
	Cooling cooling;
	
	ArrayList<RandomKey> population;
	
	public RKEDA() {
		
	}
	
	public RKEDA(String problempath, ArrayList<RandomKey> population, int populationSize, int fEs, int truncSize, boolean elitism,
			String resultsPath, String saveAs, String schedule, Cooling cooling) {
		super(problempath, population, populationSize, fEs, truncSize, elitism, resultsPath, saveAs);
		this.coolingSchedule = schedule;
		this.cooling = cooling;
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
