package tools;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;


public class ArrayUtils {

	public static String SEPARATOR = ",";

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

	public static String tableToString(Object table){
		return tableToString(table, SEPARATOR);
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

	public static double mean(double[] vals)
	{
		double rval = 0;
		for (int i = 0; i < vals.length; i++)
			rval += vals[i];
		return rval / vals.length;
	}
	
	public static double sum(double[] vals)
	{
		double rval = 0;
		for (int i = 0; i < vals.length; i++)
			rval += vals[i];
		return rval;
	}

	public static double weightedMean(double[] vals)
	{
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
	
	public static int getIndexOf( int[] tab, int toSearch )
	{
	  for( int i=0; i< tab.length ; i ++ )
	    if( tab[ i ] == toSearch)
	     return i;

	  return -1;
	}

	public static double stdev(double[] vals)
	{
		double mean = mean(vals);
		double thevalue = 0;
		for (int i = 0; i<vals.length; i++){
			thevalue=thevalue + Math.pow(vals[i]-mean,2);
		}
		thevalue=Math.sqrt(thevalue/vals.length);
		return thevalue;
	}

	public static double min(double[] array)
	{
		double rval = Double.NaN;
		for (int i = 0; i < array.length; i++)
			if (!(rval < array[i]))
				rval = array[i];

		return rval;
	}


	public static double max(double[] array)
	{
		double rval = Double.NaN;
		for (int i = 0; i < array.length; i++)
			if (!(rval > array[i]))
				rval = array[i];

		return rval;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int getCayleyDistance(int[] sigma, int[] tau){
		int n = sigma.length ;
		Vector<Integer> cycle = new Vector<Integer>();
		Vector<Vector<Integer>> totalCycles = new Vector<Vector<Integer>>();
		int indexSigma = 0;
		boolean contains = false;
		if (n == tau.length) {
			while (indexSigma < n) {
				Iterator<Vector<Integer>> it = totalCycles.iterator();
				while (it.hasNext() && contains == false)
					contains = ((Vector) it.next()).contains(tau[indexSigma]);
				if ( !contains){
					while ((cycle.isEmpty() || !cycle.contains( tau[indexSigma] ))){
						cycle.add(tau[indexSigma]);
						indexSigma = getIndexOf(sigma, tau[indexSigma]);
					}
					totalCycles.add((Vector<Integer>) cycle.clone());
					cycle.clear();
				}
				contains = false;
				indexSigma++;
			}
			return n - totalCycles.size();
		}
		return 0;
	}
	
	public static int getHammingDistance(int[] sigma, int[] tau){
		int sameElem = 0;
		int n = sigma.length;
		if (n == tau.length) {
			for(int i=0; i<n; i++){
				if(sigma[i]!=tau[i])
					sameElem++;
			}
			return sameElem;
		}
		return 0;
	}


	
}
