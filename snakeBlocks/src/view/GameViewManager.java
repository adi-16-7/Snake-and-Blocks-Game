//Mayank Rohilla	2017299
//Aditya Gupta		2017325

package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Snake;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView snake;
	private ImageView ball1;
	private ImageView ball2;
	private ImageView ball3;
	
	private ImageView block;
	private String blockImage = "view/resources/snakeChooser/blue_button08.png";
	private ImageView block1;
	private String blockImage1 = "view/resources/snakeChooser/blue_button08.png";
	private ImageView block2;
	private String blockImage2 = "view/resources/snakeChooser/blue_button08.png";
	private ImageView block3;
	private String blockImage3 = "view/resources/snakeChooser/blue_button08.png";
	private ImageView block4;
	private String blockImage4 = "view/resources/snakeChooser/blue_button08.png";
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	
	private int pos = 0;
	
	private int angle;
	private AnimationTimer gameTimer;
	
	private int row=0;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "view/resources/blue.png";
	
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
	}
	
	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}
				else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}
				else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;					
				}
			}
			
		});
	}

	public void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH,GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);	
	}
	
	public void createNewGame(Stage menuStage , Snake choosenSnake) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createSnake(choosenSnake);
		createGameLoop();
		gameStage.show();
	}
	
	private void createSnake(Snake choosenSnake) {
		snake = new ImageView(choosenSnake.getUrl());
		snake.setLayoutX(GAME_WIDTH/2);
		snake.setLayoutY(GAME_HEIGHT/2);
		snake.setFitWidth(40);
		snake.setFitHeight(40);
		gamePane.getChildren().add(snake);
		//ball 1
		ball1 = new ImageView(choosenSnake.getUrl());
		ball1.setLayoutX(snake.getLayoutX());
		ball1.setLayoutY(snake.getLayoutY()+37);
		ball1.setFitWidth(40);
		ball1.setFitHeight(40);
		gamePane.getChildren().add(ball1);
		//ball 2
		ball2 = new ImageView(choosenSnake.getUrl());
		ball2.setLayoutX(ball1.getLayoutX());
		ball2.setLayoutY(ball1.getLayoutY()+37);
		ball2.setFitWidth(40);
		ball2.setFitHeight(40);
		gamePane.getChildren().add(ball2);
		//ball 3
		ball3 = new ImageView(choosenSnake.getUrl());
		ball3.setLayoutX(ball2.getLayoutX());
		ball3.setLayoutY(ball2.getLayoutY()+37);
		ball3.setFitWidth(40);
		ball3.setFitHeight(40);
		gamePane.getChildren().add(ball3);
		
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveBackground();
				moveSnake();
				moveBlocks();
				row++;
				pos += 3;
			}
		};
		gameTimer.start();
	}
	
	protected void moveBlocks() {
		createBlocks();
		block.setLayoutY(pos);
		block1.setLayoutY(pos);
		block2.setLayoutY(pos);
		block3.setLayoutY(pos);
		block4.setLayoutY(pos);
		
	}

	private void createBlocks() {
		if(row%500==0) {
			block = new ImageView(blockImage);
			block1 = new ImageView(blockImage1);
			block2 = new ImageView(blockImage2);
			block3 = new ImageView(blockImage3);
			block4 = new ImageView(blockImage4);
			block.setLayoutX(0);
			block1.setLayoutX(120);
			block2.setLayoutX(240);
			block3.setLayoutX(360);
			block4.setLayoutX(480);
			block.setLayoutY(0);
			block1.setLayoutY(0);
			block2.setLayoutY(0);
			block3.setLayoutY(0);
			block4.setLayoutY(0);
			block.setFitWidth(120);
			block.setFitHeight(120);
			block1.setFitWidth(120);
			block1.setFitHeight(120);
			block2.setFitWidth(120);
			block2.setFitHeight(120);
			block3.setFitWidth(120);
			block3.setFitHeight(120);
			block4.setFitWidth(120);
			block4.setFitHeight(120);
			gamePane.getChildren().add(block);
			gamePane.getChildren().add(block1);
			gamePane.getChildren().add(block2);
			gamePane.getChildren().add(block3);
			gamePane.getChildren().add(block4);
		}
	}

	private void moveSnake() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle>-30) {
				angle -= 5;
			}
			snake.setRotate(angle);
			if(snake.getLayoutX()>0) {
				snake.setLayoutX(snake.getLayoutX()-5);
				ball1.setLayoutX(ball1.getLayoutX()-5);
				ball2.setLayoutX(ball2.getLayoutX()-5);
				ball3.setLayoutX(ball3.getLayoutX()-5);
				
			}
		}
		if(isRightKeyPressed && !isLeftKeyPressed ) {
			if(angle<30) {
				angle += 5;
			}
			snake.setRotate(angle);
			if(snake.getLayoutX()<565) {
				snake.setLayoutX(snake.getLayoutX()+5);
				ball1.setLayoutX(ball1.getLayoutX()+5);
				ball2.setLayoutX(ball2.getLayoutX()+5);
				ball3.setLayoutX(ball3.getLayoutX()+5);
			}
		}
		if(!isLeftKeyPressed && !isRightKeyPressed) {
			if(angle<0) {
				angle += 5;
			}
			else if(angle>0) {
				angle -= 5;
			}
			snake.setRotate(angle);			
		}
		if(isLeftKeyPressed && isRightKeyPressed) {
			if(angle<0) {
				angle += 5;
			}
			else if(angle>0) {
				angle -= 5;
			}
			snake.setRotate(angle);	
		}
	}
	
	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for(int i = 0 ; i<12 ; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1,i%3 , i/3);
			GridPane.setConstraints(backgroundImage2,i%3 , i/3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
		}
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1,gridPane2);
		
	}
	
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY()+0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY()+0.5);
		if(gridPane1.getLayoutY()>= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		if(gridPane2.getLayoutY()>= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
}
