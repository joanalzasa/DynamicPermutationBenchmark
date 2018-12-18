package problems.dynamic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import problems.FSP;

public class DFSP extends FSP{
	
	ArrayList<Double> changingTimes;
	ArrayList<FSP> dynamicProblems;
	
	public DFSP(){
		changingTimes = new ArrayList<>();
		dynamicProblems = new ArrayList<>();
	}
	
	public void readFromDirectory(String directory){
		File folder = new File(directory);
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	System.out.println("This directory is not suitable. The directory must contain only instances.");
	        } else {
	        	FSP staticProblem = new FSP();
	        	staticProblem.read(fileEntry.getPath());
	        	getChangingTime(fileEntry.getName());
	            dynamicProblems.add(staticProblem);
	        }
	    }
		System.out.println();
	}

	private void getChangingTime(String name) {
		int nameInstance = name.split("-").length - 1;
		String changingTime = name.split("-")[nameInstance].substring(0, name.split("-")[nameInstance].lastIndexOf(".")).replaceAll("[^0-9.]", "");
		changingTimes.add(Double.valueOf(changingTime));
	}

	// Getters and sett
	public ArrayList<Double> getChangingTimes() {
		return changingTimes;
	}

	public void setChangingTimes(ArrayList<Double> changingTimes) {
		this.changingTimes = changingTimes;
	}

	public ArrayList<FSP> getDynamicProblems() {
		return dynamicProblems;
	}

	public void setDynamicProblems(ArrayList<FSP> dynamicProblems) {
		this.dynamicProblems = dynamicProblems;
	}
	
	

}
