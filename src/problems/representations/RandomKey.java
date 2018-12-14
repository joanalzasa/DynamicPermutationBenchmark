package problems.representations;

import java.util.ArrayList;

public class RandomKey extends Permutation{
	
	ArrayList<Double> randomKeys;
	
	// Constructors
	public RandomKey() {}
	
	public RandomKey(ArrayList<Integer> solution, double fitness, ArrayList<Double> randomKeys) {
		super(solution, fitness);
		this.randomKeys = randomKeys;
	}

	public RandomKey(ArrayList<Double> rk) {
		super();
		this.randomKeys = rk;
	}

	// Getters and setters
	public ArrayList<Double> getRandomKeys() {
		return randomKeys;
	}

	public void setRandomKeys(ArrayList<Double> randomKeys) {
		this.randomKeys = randomKeys;
	}

}
