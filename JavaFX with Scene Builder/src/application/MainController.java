package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MainController implements Initializable {
	
	@FXML
	private ComboBox<String> stateBox;
	@FXML
	private Button okBtn;
	
	private ObservableList<String> list 
		= FXCollections.observableArrayList("NY", "MA", "CT", "NJ");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stateBox.setItems(list);
	}
	
	public void selectionMade(ActionEvent event) {
		System.out.println(stateBox.getValue());
	}
	
	public void btnClicked(ActionEvent event) {
		System.out.println("The OK button was clicked!");
	}
	

}
