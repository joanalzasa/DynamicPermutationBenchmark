package representations;

import java.util.ArrayList;

public class Permutation {

	ArrayList<Integer> gene;
	double fitness;
	int size;
	
	
	// Constructors
	public Permutation(){ 
		this.gene = new ArrayList<Integer>();
	}
	
	public Permutation(ArrayList<Integer> solution, double fitness){
		this.gene = solution;
		this.fitness = fitness;
		this.size = this.gene.size();
	}
	
	public Permutation(ArrayList<Integer> solution, double fitness, int size) {
		this.gene = solution;
		this.fitness = fitness;
		this.size = size;
	}

	// Getters and setters	
	public double getFitness() {
		return fitness;
	}
	
	public ArrayList<Integer> getSolution() {
		return gene;
	}

	public void setSolution(ArrayList<Integer> solution) {
		this.gene = solution;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
