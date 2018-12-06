package algorithms.rkeda.coolings;

public class LinearCooling2 extends Cooling{

	int maxgens;
	double initialTemp;
	int pow = 1;
	double themax;
	double amax;
//	double minTemp = 0.0;
	double minTemp;
	
	public LinearCooling2(double initialTemperature, int maxgens, double minTemp){
		this.currentTemp = initialTemperature;
		this.initialTemp = initialTemperature;
		this.minTemp = minTemp;
		this.maxgens = maxgens;
		
		themax = Math.pow(maxgens, pow);	
		amax = Math.pow(maxgens, pow) / themax; // amax = 1
	}
	
	@Override
	public double getNewTemperature(int currentGen) {
//		double weight = 1 - ((double) currentGen / maxgens);
//		currentTemp = initialTemp * weight;
		
		double a = Math.pow(currentGen, pow) / themax;
//		if(!this.isNatural){
			a = amax - a;
//		}
		currentTemp = minTemp + a*(initialTemp-minTemp);
		
		return currentTemp;
	}

}
