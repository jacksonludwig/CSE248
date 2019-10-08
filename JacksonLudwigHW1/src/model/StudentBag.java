package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class StudentBag {
	private static int studentCount = 0;
	private static final int INITIAL_AMOUNT = 300;

	private HashMap<String, Student> students;
	private File first;
	private File last;
	private ArrayList<String> firstNames;
	private ArrayList<String> lastNames;

	public StudentBag() {
		students = new HashMap<>();
		first = new File("inputFiles/firstNames.txt");
		last = new File("inputFiles/lastNames.txt");
		firstNames = Utilities.getWordsArray(first);
		lastNames = Utilities.getWordsArray(last);
		initialize();
	}

	private void initialize() {
		IntStream.range(0, INITIAL_AMOUNT).forEach(i -> insert(generateStudent()));
	}

	public Student generateStudent() {
		return new Student(firstNames, lastNames);
	}

	public boolean insert(Student student) {
		if (students.containsKey(student.getLastName()) || isFull()) {
			return false;
		} else {
			students.put(student.getLastName(), student);
			students.put(student.getId(), student);
			studentCount++;
			return true;
		}
	}

	public Student deleteByLastName(String lastName) {
		Student student = students.remove(lastName);
		if (student != null) {
			lastNames.add(students.remove(student.getId()).getLastName());
			studentCount--;
			return student;
		}
		return null;
	}

	public Student deleteById(String id) {
		Student student = students.remove(id);
		if (student != null) {
			lastNames.add(students.remove(student.getLastName()).getLastName());
			studentCount--;
			return student;
		}
		return null;
	}

	public String getAverageGpa(String key) {
		Student student = students.get(key);
		if (student != null) {
			return student.getGpa();
		} else {
			return null;
		}
	}

	public boolean isFull() {
		return studentCount >= lastNames.size();
	}

	public Student findByLastName(String lastName) {
		return students.get(lastName);
	}

	public Student findById(String id) {
		return students.get(id);
	}

	public void printAllStudents() {
		System.out.println(students + "\n" + students.size());
	}

	public int getNumStudents() {
		return studentCount;
	}
}
