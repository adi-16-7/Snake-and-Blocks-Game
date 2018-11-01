package view;

import javafx.event.EventHandler;

import java.lang.reflect.Array;
import java.util.List;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.SnakeBlockButton;

public class ViewManager {
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 100;
	
	private static int NO_OF_BUTTONS = 0;
	public ViewManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
	} 
	
	public Stage getMainStage() {
		return mainStage; 
	}
	
	private void addMenuButton(SnakeBlockButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y+ NO_OF_BUTTONS *100);
		NO_OF_BUTTONS++;
		mainPane.getChildren().add(button);		
	}
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createExitButton();
	}
	private void createStartButton() {
		SnakeBlockButton startButton = new SnakeBlockButton("PLAY");
		addMenuButton(startButton);	
	}
	
	private void createScoresButton() {
		SnakeBlockButton scoreButton = new SnakeBlockButton("LEADERBOARD");
		addMenuButton(scoreButton);	
	}
	
	private void createHelpButton() {
		SnakeBlockButton helpButton = new SnakeBlockButton("HELP");
		addMenuButton(helpButton);	
	}
	
	private void createExitButton() {
		SnakeBlockButton exitButton = new SnakeBlockButton("EXIT");
		addMenuButton(exitButton);	
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/purple.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
}
