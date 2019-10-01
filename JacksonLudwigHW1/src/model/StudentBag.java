package model;

import java.util.HashMap;

public class StudentBag {
	private final int maxSize = 2000; // add algorithm to check size and deny adding student
	HashMap<String, Student> students = new HashMap<>();

	public StudentBag() {
		
	}
}
