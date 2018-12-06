package algorithms;

import java.util.ArrayList;

public abstract class EvolutionaryAgorithm<E> {

	String problempath;
	ArrayList<E> population;
	int populationSize;
    int FEs;
    int truncSize;
    boolean elitism;
    String resultsPath;
    String saveAs;
	
    
    // Constructor
    public EvolutionaryAgorithm(){ }
    
	public EvolutionaryAgorithm(String problempath, ArrayList<E> population, int populationSize, int fEs,
			int truncSize, boolean elitism, String resultsPath, String saveAs) {
		this.problempath = problempath;
		this.population = population;
		this.populationSize = populationSize;
		FEs = fEs;
		this.truncSize = truncSize;
		this.elitism = elitism;
		this.resultsPath = resultsPath;
		this.saveAs = saveAs;
	}
    
	// Abstract methods
	public abstract void run();

}
