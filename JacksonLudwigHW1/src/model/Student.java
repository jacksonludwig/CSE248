// username must be unique
// no array as data structure

package model;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Student {
	public static String idCounter = "00000000";

	private String firstName;
	private String lastName;
	private String id;
	private String username;
	private String password;
	private String gpa;

	public Student() {
		generateName();
		generateId();
		generateUsername();
		generatePassword();
		generateGpa();
	}

	private void generateName() {
		File first = new File("inputFiles/firstNames.txt");
		File last = new File("inputFiles/lastNames.txt");
		try {
			firstName = Utilities.getRandomLine(first);
			lastName = Utilities.getRandomLine(last);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateId() {
		long temp = Long.parseLong(idCounter);
		int padSize = 8 - (int) (Math.log10(temp) + 1);
//		id = String.format("%0" + padSize + "d", temp++);
		id = String.valueOf(temp);
		idCounter = id;
	}

	private void generateUsername() {
		if(firstName.length() >= 3) {
			username = lastName.substring(0, 4) + firstName.charAt(0) + id.charAt(id.length() - 1);
		} else {
			username = lastName.substring(0) + firstName.charAt(0) + id.charAt(id.length() - 1);
		}
		
		username = username.toLowerCase();
	}

	private void generatePassword() {
		if(firstName.length() >= 3) {
			password = lastName.substring(0, 4) + firstName.charAt(0) + id.charAt(id.length() - 1);
		} else {
			password = lastName.substring(0) + firstName.charAt(0) + id.charAt(id.length() - 1);
		}
		
		password = password.substring(0, 1).toUpperCase() + password.substring(1).toLowerCase();
	}

	private void generateGpa() {
		double gpa = new Random().nextInt(400) / 100.0;
		this.gpa = String.valueOf(gpa);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getGpa() {
		return gpa;
	}
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", username=" + username
				+ ", password=" + password + ", gpa=" + gpa + "]";
	}

}
