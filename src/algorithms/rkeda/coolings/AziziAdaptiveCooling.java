package algorithms.rkeda.coolings;

public class AziziAdaptiveCooling extends Cooling{

	double minTemperature;
	double heatingCoefficient;
	
	public AziziAdaptiveCooling(double minTemperature, double heatingCoefficient){
		this.minTemperature = minTemperature;
		this.currentTemp = minTemperature;
		this.heatingCoefficient = heatingCoefficient;
	}
	
	@Override
	public double getNewTemperature(int noImprovementCounter) {
		this.currentTemp = this.minTemperature + heatingCoefficient * Math.log(1+noImprovementCounter);
		return this.currentTemp;
	}

}
