package tools;


import java.util.ArrayList;
import java.util.Collections;

public class ArrayListUtils {

	public static ArrayList<Double> arrayToArrayList(double[] array){
		ArrayList<Double> al = new ArrayList<>();
		for (double element : array)
			al.add(element);
		return al;
	}
	
	public static ArrayList<Integer> arrayToArrayList(int[] array){
		ArrayList<Integer> al = new ArrayList<>();
		for (int element : array)
			al.add(element);
		return al;
	}

	public static void addArray(ArrayList<Double> list, double[] array){
		for (double element : array)
			list.add(element);
	}
	
	public static <E> ArrayList<ArrayList<E>> transpose(ArrayList<ArrayList<E>> matrixIn) {
		ArrayList<ArrayList<E>> matrixOut = new ArrayList<ArrayList<E>>();
	    if (!matrixIn.isEmpty()) {
	        int noOfElementsInList = matrixIn.get(0).size();
	        for (int i = 0; i < noOfElementsInList; i++) {
	        	ArrayList<E> col = new ArrayList<E>();
	            for (ArrayList<E> row : matrixIn) {
	                col.add(row.get(i));
	            }
	            matrixOut.add(col);
	        }
	    }

	    return matrixOut;
	}
	
	public static <E> double mean(ArrayList<E> vals){
	  return sum(vals)/vals.size();
	}
	
	public static <E> double sum(ArrayList<E> vals){
		double sum = 0;
		if(!vals.isEmpty()) {
		    for (int i = 0; i < vals.size() ; i++)
				sum += Double.valueOf(vals.get(i).toString());
		    return sum / (double) vals.size();
	    }
		return sum;
	}

	public static double stdev(ArrayList<Double> vals){
		double mean = mean(vals);
		double thevalue = 0;
		for (int i = 0; i< vals.size(); i++)
			thevalue = thevalue + Math.pow(vals.get(i) - mean, 2);
		return Math.sqrt(thevalue/vals.size());
	}
	
	public static int indexOfMin(ArrayList<Double> vals){
		return vals.indexOf(Collections.min(vals));
	}
	
	public static int indexOfMax(ArrayList<Double> vals){
		return vals.indexOf(Collections.max(vals));
	}
	
	public static <T> ArrayList<ArrayList<T>> deepCopy(ArrayList<ArrayList<T>> source) {
	    ArrayList<ArrayList<T>> dest = new ArrayList<ArrayList<T>>();
	    for(ArrayList<T> innerList : source) {
	        dest.add(new ArrayList<T>(innerList));
	    }
	    return dest;
	}
	
	public static ArrayList<Integer> cumSum(ArrayList<Integer> vals) {
	    ArrayList<Integer> dest = new ArrayList<>();
	    int sum = 0;
	    for(int i = 0; i<vals.size(); i++) {
	        dest.add(vals.get(i) + sum );
	        sum = dest.get(i);
	    }
	    return dest;
	}
}
