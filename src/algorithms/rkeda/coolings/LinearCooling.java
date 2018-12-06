package algorithms.rkeda.coolings;

public class LinearCooling extends Cooling{

	int maxgens;
	double initialTemp;
	
	public LinearCooling(double initialTemperature, int maxgens){
		this.currentTemp = initialTemperature;
		this.initialTemp = initialTemperature;
		this.maxgens = maxgens;
	}
	
	@Override
	public double getNewTemperature(int currentGen) {
		double weight = 1 - ((double) currentGen / maxgens);
		currentTemp = initialTemp * weight;
		return currentTemp;
	}

}
