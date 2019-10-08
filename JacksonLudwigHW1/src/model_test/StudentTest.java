package model_test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import model.Student;
import model.Utilities;

class StudentTest {

	@Test
	void testID() {
		File first = new File("inputFiles/firstNames.txt");
		File last = new File("inputFiles/lastNames.txt");
		ArrayList<String> firstNames = Utilities.getWordsArray(first);
		ArrayList<String> lastNames = Utilities.getWordsArray(last);
		Student s1 = new Student(firstNames, lastNames);
		Student s2 = new Student(firstNames, lastNames);
		assertThat(Integer.parseInt(s1.getId()), is(not(equalTo(Integer.parseInt(s2.getId())))));
	}

	@Test
	void testUsernameFirst() {
		File first = new File("inputFiles/firstNames.txt");
		File last = new File("inputFiles/lastNames.txt");
		ArrayList<String> firstNames = Utilities.getWordsArray(first);
		ArrayList<String> lastNames = Utilities.getWordsArray(last);
		Student s1 = new Student(firstNames, lastNames);
		assertThat(s1.getUsername(), is(not(equalTo(s1.getFirstName()))));
	}
	
	@Test
	void testUsernameLast() {
		File first = new File("inputFiles/firstNames.txt");
		File last = new File("inputFiles/lastNames.txt");
		ArrayList<String> firstNames = Utilities.getWordsArray(first);
		ArrayList<String> lastNames = Utilities.getWordsArray(last);
		Student s1 = new Student(firstNames, lastNames);
		assertThat(s1.getUsername(), is(not(equalTo(s1.getLastName()))));
	}
}
