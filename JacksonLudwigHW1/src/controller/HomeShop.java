package controller;

import javafx.scene.layout.BorderPane;
import model.Alerts;
import model.Student;
import model.StudentBag;
import view.HomePane;

public class HomeShop {
	private HomePane homePane;
	private MenuBarShop menuBarShop;
	private BorderPane root;
	StudentBag students;

	public HomeShop(MenuBarShop menuBarShop, BorderPane root, StudentBag students) {
		homePane = new HomePane();
		this.menuBarShop = menuBarShop;
		this.root = root;
		root.setCenter(homePane.getGridPane());
		this.students = students;
		setCallback();
	}

	private void setCallback() {
		homePane.getDeleteById().setOnAction(e -> {
			Student student = students.deleteById(Alerts.deleteByID());
			if (student != null) {
				Alerts.showDeleted(student);
			} else {
				Alerts.showSearchFailed();
			}
		});

		homePane.getDeleteByName().setOnAction(e -> {
			Student student = students.deleteByLastName(Alerts.deleteByName());
			if (student != null) {
				Alerts.showDeleted(student);
			} else {
				Alerts.showSearchFailed();
			}
		});

		homePane.getFetchById().setOnAction(e -> {
			Student student = students.findById(Alerts.findByID());
			if (student != null) {
				Alerts.showFound(student);
			} else {
				Alerts.showSearchFailed();
			}
		});

		homePane.getFetchByName().setOnAction(e -> {
			Student student = students.findByLastName(Alerts.findByName());
			if (student != null) {
				Alerts.showFound(student);
			} else {
				Alerts.showSearchFailed();
			}
		});

		homePane.getFetchGpa().setOnAction(e -> {
			Alerts.findGpaChoooser(students);
		});

		homePane.getInsert().setOnAction(e -> {
			Student student = students.generateStudent();
			if (student != null) {
				students.insert(student);
				Alerts.showInserted(student);
			} else {
				Alerts.showInsertFailed();
			}
		});

	}
}
