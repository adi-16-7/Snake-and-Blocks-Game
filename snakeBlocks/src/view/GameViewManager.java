//Mayank Rohilla	2017299
//Aditya Gupta		2017325

package view;

import java.util.ArrayList;
import java.util.Random;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Snake;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameViewManager {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private ArrayList<Circle> snakeObj = new ArrayList<Circle>();
	
	private final static String DESTROY_BLOCK = "view/resources/bolt_gold.png";
	private ImageView[] DESTROY_ARRAY;
	
	private final static String SHIELD = "view/resources/shield_silver.png";
	private ImageView[] SHIELD_ARRAY;
	
	private Circle BALL_TEST;
	private Circle[] BALL_ARRAY;
	
	private Rectangle BLOCK_TEST;
	private Rectangle[] BLOCK_ARRAY;
	
	Random randomPos;
	
	private Stage menuStage;
	private Circle snake;
	private Circle ball1;
	private Circle ball2;
	private Circle ball3;
		
	private Rectangle rect;
	
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
		createMouseListeners();
		randomPos=new Random();
	}
	
	private void createMouseListeners() {
		gameScene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for(int i = 0;i<=5;i++) {
					snakeObj.get(i).setLayoutX(event.getX());
					snakeObj.get(i).setRotate(30);
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
		createSnake(choosenSnake, 5);
		createGameElements();
		createGameLoop();
		gameStage.show();
	}
	
	private void createGameElements() {
		
		//block
		BLOCK_ARRAY = new Rectangle[3];
		for(int i = 0 ; i<BLOCK_ARRAY.length;i++) {
			BLOCK_TEST = new Rectangle((GAME_WIDTH/5),0,(GAME_WIDTH/5),(GAME_WIDTH/5));
			BLOCK_TEST.setStroke(Color.BLACK);
			BLOCK_TEST.setFill(Color.AQUA);
			BLOCK_ARRAY[i] = BLOCK_TEST;
			setNewElementPosRect(BLOCK_ARRAY[i]);
			gamePane.getChildren().add(BLOCK_ARRAY[i]);
		}
		
		//balls
		BALL_ARRAY = new Circle[6];
		for(int i = 0 ; i<BALL_ARRAY.length;i++) {
			BALL_TEST = new Circle((GAME_WIDTH/5),0,10,Color.ORANGE);
			BALL_TEST.setStroke(Color.RED);
			BALL_ARRAY[i] = BALL_TEST;
			setNewElementPosCir(BALL_ARRAY[i]);
			gamePane.getChildren().add(BALL_ARRAY[i]);
		}
		
		//destroy block
		DESTROY_ARRAY = new ImageView[1];
		for(int i = 0; i<DESTROY_ARRAY.length;i++) {
			DESTROY_ARRAY[i] = new ImageView(DESTROY_BLOCK);
			setNewElementPos(DESTROY_ARRAY[i]);
			gamePane.getChildren().add(DESTROY_ARRAY[i]);
		}
		
		//shield
		SHIELD_ARRAY = new ImageView[1];
		for(int i = 0; i<DESTROY_ARRAY.length;i++) {
			SHIELD_ARRAY[i] = new ImageView(SHIELD);
			setNewElementPos(SHIELD_ARRAY[i]);
			gamePane.getChildren().add(SHIELD_ARRAY[i]);
		}
	}
	
	private void moveGameElements() {
		for(int i = 0; i<BLOCK_ARRAY.length;i++) {
			BLOCK_ARRAY[i].setLayoutY(BLOCK_ARRAY[i].getLayoutY()+7);
		}
		for(int i = 0; i<BALL_ARRAY.length;i++) {
			BALL_ARRAY[i].setLayoutY(BALL_ARRAY[i].getLayoutY()+7);
		}
		for(int i = 0; i<DESTROY_ARRAY.length;i++) {
			DESTROY_ARRAY[i].setLayoutY(DESTROY_ARRAY[i].getLayoutY()+7);
		}
		for(int i = 0; i<SHIELD_ARRAY.length;i++) {
			SHIELD_ARRAY[i].setLayoutY(SHIELD_ARRAY[i].getLayoutY()+7);
		}
	}
	
	private void checkIfElementsAreBehind() {
		for(int i = 0; i<BLOCK_ARRAY.length;i++) {
			if(BLOCK_ARRAY[i].getLayoutY()>900) {
				setNewElementPosRect(BLOCK_ARRAY[i]);
			}
		}
		for(int i = 0; i<BALL_ARRAY.length;i++) {
			if(BALL_ARRAY[i].getLayoutY()>900) {
				setNewElementPosCir(BALL_ARRAY[i]);
			}
		}
		for(int i = 0; i<DESTROY_ARRAY.length;i++) {
			if(DESTROY_ARRAY[i].getLayoutY()>900) {
				setNewElementPos(DESTROY_ARRAY[i]);
			}
		}
		for(int i = 0; i<SHIELD_ARRAY.length;i++) {
			if(SHIELD_ARRAY[i].getLayoutY()>900) {
				setNewElementPos(SHIELD_ARRAY[i]);
			}
		}
	}
	
	private void setNewElementPos(ImageView image) {
		image.setLayoutX(randomPos.nextInt(370));
		image.setLayoutY(-(randomPos.nextInt(3200)+600));	
	}
	
	private void setNewElementPosCir(Circle cir) {
		cir.setLayoutX(randomPos.nextInt(370));
		cir.setLayoutY(-(randomPos.nextInt(3200)+600));	
	}
	
	private void setNewElementPosRect(Rectangle rect) {
		rect.setLayoutX(randomPos.nextInt(370));
		rect.setLayoutY(-(randomPos.nextInt(3200)+600));
	}
	
	private void createSnake(Snake choosenSnake, int n) {
		snake = new Circle(0,GAME_HEIGHT/2,10,Color.AQUA);
		snakeObj.add(snake);
		for (int i=0; i<n; i++) {
			snake = new Circle(0,GAME_HEIGHT/2+(20*i),10,Color.AQUA);
			snakeObj.add(snake);
		}
		gamePane.getChildren().addAll(snakeObj);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveBackground();
				moveGameElements();
				checkIfElementsAreBehind();
//				moveSnake();
			}
		};
		gameTimer.start();
	}
	
	private void moveSnake() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle>-30) {
				angle -= 5;
			}
			snake.setRotate(angle);
			if(snake.getLayoutX()>5) {
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
			if(snake.getLayoutX()<560) {
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
