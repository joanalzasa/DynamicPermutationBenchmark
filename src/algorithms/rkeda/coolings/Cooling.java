package algorithms.rkeda.coolings;

public abstract class Cooling {

	double currentTemp;
	
	public abstract double getNewTemperature(int currentGen);
	
}
