//Mayank Rohilla	2017299
//Aditya Gupta		2017325

package model;

public enum Snake {
	
	BLUE("view/resources/snakeChooser/blue_circle.png"),
	GREEN("view/resources/snakeChooser/green_circle.png"),
	YELLOW("view/resources/snakeChooser/yellow_circle.png"),
	RED("view/resources/snakeChooser/red_circle.png");
	
	private String urlSnake;
	
	private Snake(String urlSnake) {
		this.urlSnake = urlSnake;
	}
	
	
	public String getUrl() {
		return this.urlSnake;
	}
}
