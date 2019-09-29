package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Utilities {
	public static String getRandomLine(File file) throws IOException {
		RandomAccessFile raf = null;

		raf = new RandomAccessFile(file, "r");

		long randomByteValue = (long) (Math.random() * file.length());

		raf.seek(randomByteValue);

		while (randomByteValue > 0 && raf.readChar() != '\n') {
			randomByteValue -= 1;
			raf.seek(randomByteValue);
		}
		
//		raf.readLine();
		String random = raf.readLine();
		raf.close();

		return random;
	}

}
