package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import model.Student;

public class testDemo {

	public static void main(String[] args) throws IOException {
		Student student = new Student();
		System.out.println(student);
//		File file = new File("inputFiles/firstNames.txt");
//		RandomAccessFile raf = null;
//
//		raf = new RandomAccessFile(file, "r");
//		
//		raf.seek(8);
//		
//		System.out.println(raf.readLine());
	}

}
