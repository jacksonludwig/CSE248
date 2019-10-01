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
		int count = 0;
		for(int i = 0; i < 2000; i++) {
			Student studentLoop = new Student();
			System.out.println(studentLoop);
			System.out.println(count++);
		}
	}

}
