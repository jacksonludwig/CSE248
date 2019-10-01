package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import model.Student;
import model.Utilities;

public class testDemo {

	public static void main(String[] args) throws IOException {
		File first = new File("inputFiles/firstNames.txt");
		File second = new File("inputFiles/lastNames.txt");
		String[] fNames = Utilities.getWordsArray(first);
		String[] lNames = Utilities.getWordsArray(second);
		Student student = new Student(fNames, lNames);
		System.out.println(student);
		int count = 0;
		for(int i = 0; i < 500; i++) {
			Student studentLoop = new Student(fNames, lNames);
			System.out.println(studentLoop);
			System.out.println(count++);
		}
	}

}
