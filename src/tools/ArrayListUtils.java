package tools;


import java.util.ArrayList;

public class ArrayListUtils {

	public static ArrayList<Double> arrayToArrayList(double[] array){
		ArrayList<Double> al = new ArrayList<>();
		for (double element : array)
			al.add(element);
		return al;
	}

	public static void addArray(ArrayList<Double> list, double[] array){
		for (double element : array)
			list.add(element);
	}
	
	public static double mean(ArrayList<Double> vals){
		double sum = 0;
		if(!vals.isEmpty()) {
		    for (double d : vals)
		        sum += d;
		    return sum / vals.size();
	    }
	  return sum;
	}
	
	public static double sum(ArrayList<Double> vals){
		double sum = 0;
		for(double d : vals)
		    sum += d;
		return sum;
	}

	public static double stdev(ArrayList<Double> vals){
		double mean = mean(vals);
		double thevalue = 0;
		for (int i = 0; i< vals.size(); i++)
			thevalue = thevalue + Math.pow(vals.get(i) - mean, 2);
		return Math.sqrt(thevalue/vals.size());
	}

	public static double min(ArrayList<Double> vals){
		double rval = Double.NaN;
		if(!vals.isEmpty()) {
			for(double d : vals){
				if (!(rval < d))
					rval = d;
			}
	    }
		return rval;
	}
	
	public static int indexOfMin(ArrayList<Double> vals){
		return vals.indexOf(min(vals));
	}

	public static double max(ArrayList<Double> vals){
		double rval = Double.NaN;
		if(!vals.isEmpty()) {
			for(double d : vals){
				if (!(rval > d))
					rval = d;
			}
	    }
		return rval;
	}
	
	public static int indexOfMax(ArrayList<Double> vals){
		return vals.indexOf(max(vals));
	}
	
}
