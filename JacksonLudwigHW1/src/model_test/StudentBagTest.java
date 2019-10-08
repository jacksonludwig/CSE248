package model_test;

import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import model.Student;
import model.StudentBag;

class StudentBagTest {

	@Test
	void testDelete() {
		StudentBag students = new StudentBag();
		Student student = students.deleteById("00000001");
		assertThat(student.getId(), is(equalTo("00000001")));
	}
	
	@Test
	void testFind() {
		StudentBag students = new StudentBag();
		Student student = students.findById("00000001");
		assertThat(student.getId(), is(equalTo("00000001")));
	}
	
	@Test
	void numTest() {
		StudentBag students = new StudentBag();
		assertThat(students.getNumStudents(), is(equalTo(1000)));
	}
	
	@Test
	void fullTest() {
		StudentBag students = new StudentBag();
		IntStream.range(0, 300).forEach(i -> students.insert(students.generateStudent()));
		assertThat(students.isFull(), is(true));
	}

}
