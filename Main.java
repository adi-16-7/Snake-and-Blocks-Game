package snakeBricks;
//Mayank
//Aditya 
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gameplay = new Gameplay();
		obj.setBounds(480,100,470,700);
		obj.setTitle("Snake v/s Ladder");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}

}
