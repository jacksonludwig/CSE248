package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Utilities {
	public static String getRandomLine(File file) throws IOException {
		RandomAccessFile raf = null;

		raf = new RandomAccessFile(file, "r");

		long leftLimit = 0;
		long rightLimit = file.length();
		long randomByteValue = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

		raf.seek(randomByteValue);

		do {
			randomByteValue -= 2;
			raf.seek(randomByteValue);
		} while (randomByteValue > 0 && raf.readChar() != '\n');

		raf.close();
		return raf.readLine();
	}

}
