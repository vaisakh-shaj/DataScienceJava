
/**
 * Final Project - Analyzing baby names data from 1880 to 2014
 * 
 * @author (Vaisakh) 
 * @version (0.1)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
	/*
	* This method returns the total number of births males and females in a file
	*/
	public int totalBirths(FileResource fr) {
		int totalBirths = 0;
		int totalGirls = 0;
		int totalBoys = 0;
		int gNameCount = 0;
		CSVParser parser = fr.getCSVParser(false);

		for(CSVRecord record : parser) {
			int numBorn = Integer.parseInt(record.get(2));
			String gender = record.get(1);
			totalBirths += numBorn;
			if(gender.equals("M")) {
				totalBoys += 1;
			} else {
				totalGirls += 1;
				gNameCount +=1;
			}
		}

		System.out.println("Total: " + totalBirths);
		System.out.println("Boys: " + totalBoys);
		System.out.println("Girls: " + totalGirls);
		return gNameCount;
	}

	/*
	* This method returns the rank of the name in the file for the given gender, 
	* where rank 1 is the name with the largest number of births. 
	* If the name is not in the file, then -1 is returned.
	*/
	public long getRank(int year, String name, String gender) {
		long rank = -1;
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
		CSVParser parser = fr.getCSVParser(false);
                int fc = totalBirths(fr);
		for(CSVRecord record : parser) {
			String currName = record.get(0);
			String currGender = record.get(1);
			
			if(currGender.equals(gender) && currName.equals(name)) {
				if(gender=="F") rank = record.getRecordNumber();
			        else rank = record.getRecordNumber()-fc;
                        }
		}
		return rank;
	}

	/*
	* This method returns the name of the person in the file at this rank, 
	* for the given gender, where rank 1 is the name with the largest number of births. 
	* If the rank does not exist in the file, then “NO NAME” is returned.
	*/
	public String getName(int year, int rank, String gender) {
		String name = "";
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
		CSVParser parser = fr.getCSVParser(false);
                int fc = totalBirths(fr);
		for(CSVRecord record : parser) {
			long currRank ;
			
			if(gender=="F") currRank = record.getRecordNumber();
			else currRank = record.getRecordNumber()-fc;
                       
			String currGender = record.get(1);
			String currName = record.get(0);

			if(currRank == rank && currGender.equals(gender)) {
				name = currName;
			}
		}

		if(name != "") {
			return name;
		} 
		else {
			return "NO NAME";
		}
	}

	/*
	* This method determines what name would have been named if they were born 
	* in a different year, based on the same popularity.
	*/
	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
		FileResource newFr = new FileResource("us_babynames/us_babynames_by_year/yob" + newYear + ".csv");
		CSVParser parserOld = fr.getCSVParser(false);
		CSVParser parserNew = newFr.getCSVParser(false);
		String newName = "";
		long popularity = 0;
                int fc = totalBirths(fr);
                int fcN = totalBirths(newFr);
                long currPopularity =0 ;
		for(CSVRecord record : parserOld) {
			String currName = record.get(0);
			String currGender = record.get(1);

			if(currName.equals(name) && currGender.equals(gender)) {
				if(gender=="F") popularity = record.getRecordNumber();
			         else popularity = record.getRecordNumber()-fc;
			}
		}
                   System.out.println(popularity);
		for(CSVRecord record : parserNew) {
		        String currName = record.get(0);
			String currGender = record.get(1);
			
			
				if(gender=="F") currPopularity = record.getRecordNumber();
			         else currPopularity = record.getRecordNumber()-fcN;
			

			if(currGender.equals(gender) && popularity == currPopularity) {
				newName = record.get(0);
			}
		}
                System.out.println(currPopularity);
		System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
	}

	/*
	* This method selects a range of files to process and returns an integer, 
	* the year with the highest rank for the name and gender. 
	* If the name and gender are not in any of the selected files, it should return -1.
	*/
	public int yearOfHighestRank(String name, String gender) {
		long highestRank = 0;
		int yearOfHighestRank = -1;
		String fileName = "";
		DirectoryResource dr = new DirectoryResource();
		//int fc = totalBirths(fr);
		// Iterate through all files
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			int fc = totalBirths(fr);
			// Iterate through all records in file
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);

				if(currName.equals(name) && currGender.equals(gender)) {
					long currRank = 0;
					if(gender=="F") currRank = record.getRecordNumber();
			                else currRank = record.getRecordNumber()-fc;
					if(highestRank == 0) {
						highestRank = currRank;
						fileName = f.getName();
					} 
					else {
						if(highestRank > currRank) {
							highestRank = currRank;
							fileName = f.getName();
						}
					}
				}
			}
		}

		// Remove all non-numeric characters from the filename
		fileName = fileName.replaceAll("[^\\d]", "");
		
		// Convert String fileName to Integer
		yearOfHighestRank = Integer.parseInt(fileName);

		return yearOfHighestRank;
	}
  
  /*
	* This method returns the average rank of a name in multiple files
	*/
	public double getAverageRank(String name, String gender) {
		// Initialize a DirectoryResource
		DirectoryResource dr = new DirectoryResource();
		// Define rankTotal, howMany
		double rankTotal = 0.0;
		int howMany = 0;
		// For every file the directory add name rank to agvRank
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			int fc = totalBirths(fr);
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);
				if(currName.equals(name) && currGender.equals(gender)){
					long currRank = 0;
					if(gender=="F") currRank = record.getRecordNumber();
			                else currRank = record.getRecordNumber()-fc;
					rankTotal += (double)currRank;
					howMany += 1;
				}
			}
		}
		// Define avgRank = rankTotal / howMany
		double avgRank = rankTotal / (double)howMany;
		return avgRank;
	}
  
  /*
	* This method returns the total births of the same gender that are ranked higher
  * than the parameter name
	*/
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int numBorn = 0;
		long rank = getRank(year, name, gender);
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			int currBorn = Integer.parseInt(record.get(2));
			String currGender = record.get(1);
			long currRank = record.getRecordNumber();
			if(gender.equals(currGender) && rank > currRank) {
				numBorn += currBorn;
			}
		}
		return numBorn;
	}

  /*
	* For testing the methods
	*/
	public void testTotlaBirth() {
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
		totalBirths(fr);

		//long rank = getRank(1971, "Frank", "M");
		//System.out.println("Rank is: " + rank);

		 //String name = getName(1982, 450, "M");
		 //System.out.println("Name: " + name);

		 //whatIsNameInYear("Susan", 1972, 2014, "F");
                 //whatIsNameInYear("Owen", 1974, 2014, "M");
		 System.out.println(yearOfHighestRank("Genevieve", "F"));
		System.out.println(yearOfHighestRank("Mich", "M"));
		System.out.println(getAverageRank("Susan", "F"));
		System.out.println(getAverageRank("Robert", "M"));
		
		// System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
	}

	public static void main() {
		BabyNames names = new BabyNames();
		names.testTotlaBirth();
	}
}
