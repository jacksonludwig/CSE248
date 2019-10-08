package model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	public static String deleteByID() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Delete by ID Window");
		dialog.setHeaderText("Use Identification Number to delete Here.");
		dialog.setContentText("Delete student with ID: ");
		Optional<String> result = dialog.showAndWait();
		int temp = Integer.parseInt(result.get());
		String formatResult = String.format("%08d", temp);
		return formatResult;
	}

	public static void showDeleted(Student student) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data delete completed");
		alert.setHeaderText("The Student Was Deleted From The Database.");
		alert.setContentText("Name: " + student.getFirstName() + " " + student.getLastName() + "\nUsername: "
				+ student.getUsername() + "\nPassword: " + student.getPassword() + "\nID: " + student.getId()
				+ "\nGPA: " + student.getGpa());
		alert.showAndWait();
	}

	public static void showSearchFailed() {
		Alert alert = new Alert(AlertType.ERROR, "There is no student with that information in the database.",
				ButtonType.OK);
		alert.setTitle("Data search complete.");
		alert.setHeaderText("There Was a Problem Finding Your Item.");
		alert.showAndWait();
	}

	public static String deleteByName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Delete by Last Name Window");
		dialog.setHeaderText("Use Last Name to delete here.");
		dialog.setContentText("Delete person with Last Name: ");
		Optional<String> result = dialog.showAndWait();
		return result.get();
	}

	public static String findByID() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Find by ID Window");
		dialog.setHeaderText("Use Identification Number to fetch student Here.");
		dialog.setContentText("fetch student with ID: ");
		Optional<String> result = dialog.showAndWait();
		int temp = Integer.parseInt(result.get());
		String formatResult = String.format("%08d", temp);
		return formatResult;
	}

	public static void showFound(Student student) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data search completed");
		alert.setHeaderText("The Student Was Found In The Database.");
		alert.setContentText("Name: " + student.getFirstName() + " " + student.getLastName() + "\nUsername: "
				+ student.getUsername() + "\nPassword: " + student.getPassword() + "\nID: " + student.getId()
				+ "\nGPA: " + student.getGpa());
		alert.showAndWait();
	}

	public static String findByName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Find by Last Name Window");
		dialog.setHeaderText("Use Last Name to search here.");
		dialog.setContentText("Fetch person with Last Name: ");
		Optional<String> result = dialog.showAndWait();
		return result.get();
	}

	public static void findGpaChoooser(StudentBag students) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Get Student GPA Window");
		dialog.setHeaderText("You can find a student's GPA in two ways: searching by ID or by Last Name.");
		dialog.setContentText("Please enter \"0\" to search by ID or \"1\" to search by Last Name");
		Optional<String> result = dialog.showAndWait();
		if (Integer.parseInt(result.get()) == 0) {
			showGpa(findGpaById(students));
		} else if (Integer.parseInt(result.get()) == 1) {
			showGpa(findGpaByName(students));
		} else {
			showSearchFailed();
		}
	}

	public static String findGpaByName(StudentBag students) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Find GPA by Last Name Window");
		dialog.setHeaderText("Use Last Name to search for a student's GPA here.");
		dialog.setContentText("Fetch GPA of student with Last Name: ");
		Optional<String> result = dialog.showAndWait();
		return students.getAverageGpa(result.get());
	}

	public static String findGpaById(StudentBag students) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Find GPA by ID Window");
		dialog.setHeaderText("Use ID to search for a student's here.");
		dialog.setContentText("Fetch GPA of student with ID: ");
		Optional<String> result = dialog.showAndWait();
		int temp = Integer.parseInt(result.get());
		String formatResult = String.format("%08d", temp);
		return students.getAverageGpa(formatResult);
	}

	public static void showGpa(String gpa) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data search completed");
		alert.setHeaderText("The Student's GPA Was Found In The Database.");
		alert.setContentText("GPA: " + gpa);
		alert.showAndWait();
	}
	
	public static void showInserted(Student student) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data insert completed");
		alert.setHeaderText("The New Student Was Entered In The Database.");
		alert.setContentText("Name: " + student.getFirstName() + " " + student.getLastName() + "\nUsername: "
				+ student.getUsername() + "\nPassword: " + student.getPassword() + "\nID: " + student.getId()
				+ "\nGPA: " + student.getGpa());
		alert.showAndWait();
	}
	
	public static void showInsertFailed() {
		Alert alert = new Alert(AlertType.ERROR, "The database has reached the current set maximum",
				ButtonType.OK);
		alert.setTitle("Data Insert Failed.");
		alert.setHeaderText("A student must be deleted before a new one can be entered.");
		alert.showAndWait();
	}
}
