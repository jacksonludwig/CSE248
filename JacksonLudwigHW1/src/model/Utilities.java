package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Utilities {

	// ignores duplicate last names
	public static ArrayList<String> getWordsArray(File file) {
		HashSet<String> names = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			names = new HashSet<String>();

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				names.add(currentLine);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> nameList = new ArrayList<>(names);
		return nameList;
	}

	public static String getRandomFirstName(ArrayList<String> names) {
		Random rand = new Random();
		return names.get(rand.nextInt(names.size()));
	}

	// removes last name from last name list so that no repeats are allowed until
	// deletions
	public static String getRandomLastName(ArrayList<String> names) {
		Random rand = new Random();
		int randomSpot = rand.nextInt(names.size());
		String name = names.get(randomSpot);
		names.remove(randomSpot);
		return name;
	}

}
