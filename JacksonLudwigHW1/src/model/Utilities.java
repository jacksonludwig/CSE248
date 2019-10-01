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

		String random;
		do {
			raf.readLine();
			random = raf.readLine();
		} while(random == null);

		raf.close();
		System.out.println(random);
		return random;
	}

//	public static String getRandomLine(File file) throws IOException {
//		RandomAccessFile raf = null;
//
//		raf = new RandomAccessFile(file, "r");
//		
//		long start = 5000;
//
//		long pos = (start % 2 == 0) ? start : start -1;
//
//	    if(pos == 0) return raf.readLine();
//
//	    do{
//	        pos -= 2;
//	        raf.seek(pos);
//	    }while(pos > 0 && raf.readChar() != '\n');
//
//	    pos = (pos <= 0) ? 0 : pos + 2;
//	    raf.seek(pos);
//	    return raf.readLine();
//	}

}
