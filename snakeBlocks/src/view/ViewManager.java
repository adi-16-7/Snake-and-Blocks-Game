//Mayank Rohilla	2017299
//Aditya Gupta		2017325

package view;

import javafx.event.EventHandler;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.Snake;
import model.SnakeBlockButton;
import model.SnakeBlocksSubscene;
import model.SnakePicker;

public class ViewManager {
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 200;
	
	private SnakeBlocksSubscene playSubscene;
	private SnakeBlocksSubscene scoreSubscene;
	private SnakeBlocksSubscene helpSubscene;
	private SnakeBlocksSubscene creditSubscene;
	
	private SnakeBlocksSubscene sceneToHide;
	
	List<SnakePicker> snakesList;
	private Snake choosenSnake;
	
	
	private static int NO_OF_BUTTONS = 0;
	public ViewManager() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		createLogo();
		createSubscene();

		
	} 
	
	private void showSubscene(SnakeBlocksSubscene subscene) {
		if(sceneToHide != null) {
			sceneToHide.MoveSubscene();
			
		}
		subscene.MoveSubscene();
		sceneToHide = subscene;
	}
	
	private void createSubscene() {
		creditSubscene = new SnakeBlocksSubscene();
		mainPane.getChildren().add(creditSubscene);
		
		helpSubscene = new SnakeBlocksSubscene();
		mainPane.getChildren().add(helpSubscene);
				
		scoreSubscene = new SnakeBlocksSubscene();
		mainPane.getChildren().add(scoreSubscene);
		
		createSnakeChooserSubscene();
		
		
	}

	private void createSnakeChooserSubscene() {
		playSubscene = new SnakeBlocksSubscene();
		mainPane.getChildren().add(playSubscene);	
		
		InfoLabel chooseSnakeLabel = new InfoLabel("Choose Your Snake");
		chooseSnakeLabel.setLayoutX(110);
		chooseSnakeLabel.setLayoutY(25);
		playSubscene.getPane().getChildren().add(chooseSnakeLabel);
		playSubscene.getPane().getChildren().add(createSnakesToChoose());
		playSubscene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createSnakesToChoose() {
		HBox box = new HBox();
		box.setSpacing(40);
		snakesList = new ArrayList<>();
		for(Snake snake : Snake.values()) {
			SnakePicker snakeToPick = new SnakePicker(snake);
			snakesList.add(snakeToPick);
			box.getChildren().add(snakeToPick);
			snakeToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(SnakePicker snake : snakesList) {
						snake.setIsCircleChoosen(false);
					}
					snakeToPick.setIsCircleChoosen(true);
					choosenSnake = snakeToPick.getSnake();	
				}
			});
		}
		box.setLayoutX(408-(118*2));
		box.setLayoutY(125);
		return box;
	}
	
	private SnakeBlockButton createButtonToStart() {
		SnakeBlockButton startButton = new SnakeBlockButton("START");
		startButton.setLayoutX(208);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(choosenSnake != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenSnake);
				}
				
			}
			
		});
		return startButton;
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
		createCreditsButton();
	}
	private void createStartButton() {
		SnakeBlockButton startButton = new SnakeBlockButton("PLAY");
		addMenuButton(startButton);	
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(playSubscene); 
			}
			
		});
	}
	
	private void createScoresButton() {
		SnakeBlockButton scoreButton = new SnakeBlockButton("LEADERBOARD");
		addMenuButton(scoreButton);	
		
		scoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(scoreSubscene);
			}
			
		});
	}
	
	private void createCreditsButton() {
		SnakeBlockButton creditButton = new SnakeBlockButton("CREDITS");
		addMenuButton(creditButton);
		
		creditButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(creditSubscene);
			}
			
		});
	}
	
	private void createHelpButton() {
		SnakeBlockButton helpButton = new SnakeBlockButton("HELP");
		addMenuButton(helpButton);	
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(helpSubscene);
			}
			
		});
	}
	
	private void createExitButton() {
		SnakeBlockButton exitButton = new SnakeBlockButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
			
			
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/blue.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/logoSnake.png");
		logo.setLayoutX(5);
		logo.setLayoutY(-250);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				logo.setEffect(new DropShadow());
			}		
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				logo.setEffect(null);
			}		
		});
		
		mainPane.getChildren().add(logo);
	}
}
