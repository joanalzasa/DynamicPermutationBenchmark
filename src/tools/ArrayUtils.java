package tools;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {

	public static int[] arrayListToIntegerArray(ArrayList<Integer> list){

		int[] arr = new int[list.size()];
		for(int i=0; i<list.size(); i++){
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static double[] arrayListToDoubleArray(ArrayList<Double> list){

		double[] arr = new double[list.size()];
		for(int i=0; i<list.size(); i++){
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static String tableToString(Object obj){
		return tableToString(obj, ",");
	}

	public static String tableToString(Object obj, String separator){
		String s = "";
		// To avoid putting "," after all the objects
		boolean firstLine = true;
		
		// Put the object into a string
        if (!(obj instanceof Object[])) {
            if (obj instanceof int[]) {
                for (int i : (int[]) obj) {
                	if(!firstLine)
        				s+=separator;
        			else 
        				firstLine = false;
        			s += i;
                }
            }else if (obj instanceof double[]) {
                for (double d : (double[]) obj) {
                	if(!firstLine)
        				s+=separator;
        			else 
        				firstLine = false;
        			s += d;
                }
            }else if (obj instanceof char[]) {
                for (char c : (char[]) obj) {
                	if(!firstLine)
        				s+=separator;
        			else 
        				firstLine = false;
        			s += c;
                }
            }
            //and so on, for every primitive type.
        }else{
            s = Arrays.toString((Object[]) obj);
        }
		return s;
	}

	public static double mean(double[] vals){
		double rval = 0;
		for (int i = 0; i < vals.length; i++)
			rval += vals[i];
		return rval / vals.length;
	}
	
	public static double sum(double[] vals){
		double rval = 0;
		for (int i = 0; i < vals.length; i++)
			rval += vals[i];
		return rval;
	}

	public static double weightedMean(double[] vals){
		double rval = 0;
		int weight = vals.length;
		int totalweight = 0;
		for (int i = 0; i < vals.length; i++)
		{
			rval += weight *vals[i];
			totalweight += weight;
			weight --;
		}
		return rval / totalweight;
	}
	
	public static int getIndexOf(Object obj, Object toSearch ){
		int i = 0;
		if (!(obj instanceof Object[])) {
			if (obj instanceof int[] && toSearch instanceof Integer) {
            	 for (int element : (int[]) obj) {
            		 if(element == (int)toSearch)
            			 return i;
            		 else
            			 i++;	 
            	 }
            }else if (obj instanceof double[] && toSearch instanceof Double) {
            	for (double element : (double[]) obj) {
            		if(element == (double)toSearch)
           			 return i;
           		 else
           			 i++;
            	}
            }
		}
		return -1;
	}

	public static double stdev(double[] vals){
		double mean = mean(vals);
		double thevalue = 0;
		for (int i = 0; i<vals.length; i++){
			thevalue=thevalue + Math.pow(vals[i]-mean,2);
		}
		thevalue=Math.sqrt(thevalue/vals.length);
		return thevalue;
	}

	public static double min(double[] array){
		double rval = Double.NaN;
		for (int i = 0; i < array.length; i++)
			if (!(rval < array[i]))
				rval = array[i];

		return rval;
	}


	public static double max(double[] array){
		double rval = Double.NaN;
		for (int i = 0; i < array.length; i++)
			if (!(rval > array[i]))
				rval = array[i];

		return rval;
	}
	
	 // Implementing Fisher–Yates shuffle
	public static void shuffleArray(int[] ar){
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	}
	
}
