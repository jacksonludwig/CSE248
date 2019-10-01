package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Utilities {

	public static String[] getWordsArray(File file) {
		ArrayList<String> names = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			names = new ArrayList<String>();

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				names.add(currentLine);
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return names.toArray(new String[0]);
	}

	public static String getRandomName(String[] names) {
		Random rand = new Random();
		return names[rand.nextInt(names.length)];
	}

}
