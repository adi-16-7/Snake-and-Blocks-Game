package view;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SnakeBlockButton;

public class ViewManager {
	
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	
	public ViewManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
	}
	
	public Stage getMainStage() {
		return mainStage; 
	}
	
	private void createButtons() {
		SnakeBlockButton button = new SnakeBlockButton("clickMe");
		mainPane.getChildren().add(button);
	}
}
