package tools;

import tools.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class PermutationUtils {
	
	public static ArrayList<Integer> generateRandomPermutation(Random rand, int n) {
		ArrayList<Integer> sol = new ArrayList<Integer>();
		ArrayList<Integer> temp = generateIdentityPermutation(n);
		int j = 0;
		while (temp.size() > 0) {
			int index = rand.nextInt(temp.size());
			sol.set(j, temp.get(index));
			temp.remove(index);
			j++;
		}
		return sol;
	}
	
	public static ArrayList<Integer> generateIdentityPermutation(int n){
		ArrayList<Integer> identityPerm = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			identityPerm.add(i);
		return identityPerm;
	}

	public static ArrayList<Double> generateRandomRK(Random rand, int n) {
		ArrayList<Double> rk = new ArrayList<>();
		for (int i = 0; i < n; i++)
			rk.add(rand.nextDouble());
		return rk;
	}

	public static ArrayList<Integer> rkToPermutation(ArrayList<Double> rk) {
		ArrayList<Integer> solution = new ArrayList<Integer>();
		ArrayList<Double> orderedRK = new ArrayList<>(rk);
		Collections.sort(orderedRK);
		for (int i = 0; i < orderedRK.size(); i++)
			solution.add(orderedRK.indexOf((rk.get(i))));
		return solution;
	}

	public static ArrayList<Double> normalise(ArrayList<Double> rk) {
		ArrayList<Double> solution = new ArrayList<Double>();
		ArrayList<Double> orderedRK = new ArrayList<>(rk);
		Collections.sort(orderedRK);
		for (int i = 0; i < orderedRK.size(); i++)
			solution.add((double) orderedRK.indexOf((rk.get(i))) / (rk.size() - 1));
		return solution;
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
						indexSigma = ArrayUtils.getIndexOf(sigma, tau[indexSigma]);
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
	
	public static <E> int getHammingDistance(ArrayList<E> sigma, ArrayList<E> tau){
		int sameElem = 0;
		if (sigma.size() == tau.size()) {
			for (int i = 0; i < sigma.size(); i++) {
				if (!sigma.get(i).equals(tau.get(i))){
					sameElem++;
				}
			}
			return sameElem;
		}
		return 0;
	}
	
	public static int[] compose(int[] identity, int[] sigma) {
		int[] composedPerm = new int[identity.length];
		for (int i = 0; i < identity.length; i++) {
			composedPerm[i] = identity[sigma[i]];
		}
		return composedPerm;
	}
}
