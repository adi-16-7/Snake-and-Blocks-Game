//Mayank Rohilla	2017299
//Aditya Gupta		2017325

package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SnakePicker extends VBox {
	
	private ImageView circleImage;
	private ImageView snakeImage;
	
	private String circleNotChoosen = "view/resources/snakeChooser/grey_circle.png";
	private String circleChoosen = "view/resources/snakeChooser/yellow_boxTick.png";
	
	private Snake snake;
	private boolean isCircleChoosen ;
	
	
	public SnakePicker(Snake snake) {
		circleImage = new ImageView(circleNotChoosen);
		snakeImage = new ImageView(snake.getUrl());
		this.snake = snake;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(snakeImage);		
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen:circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
