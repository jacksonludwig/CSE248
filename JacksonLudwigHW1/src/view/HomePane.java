package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePane {
	private GridPane gridPane;
	private Button fetchByName;
	private Button fetchById;
	private Button deleteByName;
	private Button deleteById;
	private Button fetchGpa;
	private Button insert;
	
	private HBox buttonBox;
	private VBox vBox;

	public HomePane() {
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(20);
		gridPane.setVgap(20);

		fetchByName = new Button("Fetch By\nLast Name");
		fetchByName.setPrefSize(150, 50);
		fetchById = new Button("Fetch By\nID");
		fetchById.setPrefSize(150, 50);
		deleteByName = new Button("Delete By\nLast Name");
		deleteByName.setPrefSize(150, 50);
		deleteById = new Button("Delete By\nID");
		deleteById.setPrefSize(150, 50);
		fetchGpa = new Button("Fetch Student\nGPA");
		fetchGpa.setPrefSize(150, 50);
		insert = new Button("Generate\nNew Student");
		insert.setPrefSize(150, 50);
		
		buttonBox = new HBox(40);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(fetchByName, fetchById, deleteByName, deleteById, fetchGpa, insert);
		
		vBox = new VBox(40);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(buttonBox);
		
		gridPane.add(vBox, 0, 0);
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public Button getFetchByName() {
		return fetchByName;
	}

	public void setFetchByName(Button fetchByName) {
		this.fetchByName = fetchByName;
	}

	public Button getFetchById() {
		return fetchById;
	}

	public void setFetchById(Button fetchById) {
		this.fetchById = fetchById;
	}

	public Button getDeleteByName() {
		return deleteByName;
	}

	public void setDeleteByName(Button deleteByName) {
		this.deleteByName = deleteByName;
	}

	public Button getDeleteById() {
		return deleteById;
	}

	public void setDeleteById(Button deleteById) {
		this.deleteById = deleteById;
	}

	public Button getFetchGpa() {
		return fetchGpa;
	}

	public void setFetchGpa(Button fetchGpa) {
		this.fetchGpa = fetchGpa;
	}

	public Button getInsert() {
		return insert;
	}

	public void setInsert(Button insert) {
		this.insert = insert;
	}

	public HBox getButtonBox() {
		return buttonBox;
	}

	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	public VBox getvBox() {
		return vBox;
	}

	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}
}
