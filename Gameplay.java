package snakeBricks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private Random rand = new Random(50);
	private int score = 0;
	int ycor = 0;
	private int totalBalls = 10 ;
	private Timer timer;
	private int delay = 100;
	private int playerX = 225;
	private int playerY = 300;
	private int playerXdir = 0 ;
	private int playerYdir = -1 ;
	
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();		
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0,100,470,600);
		
		

		/* borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 442, 3);
		g.fillRect(442, 0, 3, 592);
		*/
		// score
		
		/* matrix
		g.setColor(Color.white);
		g.fillRect(5, ycor, 85 , 85);
		g.fillRect(95, ycor, 85, 85);
		g.fillRect(185, ycor, 85, 85);
		g.fillRect(275, ycor, 85, 85);
		g.fillRect(365, ycor, 85, 85);
		*/
		createBlock(g);
		// draw tape
		g.setColor(Color.blue);
		g.fillRect(0,0,470,100);
		
		// score
		g.setColor(Color.red);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,420,30);
			
		//ball
		g.setColor(Color.yellow);
		g.fillOval(playerX,playerY, 30, 30);
		g.setColor(Color.green);

		
		if(totalBalls<=0) {
			play = false;
			playerXdir = 0;
			playerYdir = 0;
			
			g.setColor(Color.red);
			g.setColor(Color.red);
			g.setFont(new Font("SansSerif",Font.BOLD,35));
			g.drawString("GAME OVER (score :" + score + ")",190,300);
			
			g.setFont(new Font("SansSerif",Font.PLAIN,25));
			g.drawString("Press Enter To Restart",230,350);
		}
		
		else {
			ycor+= 15;
		}
		
		g.dispose();
	}	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 420) {
				playerX = 420;
			}
			else {
				moveRight();
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 0) {
				playerX = 0;
			}
			else {
				moveLeft();
			}
		} 
	}
	public void moveRight() {
		play = true;
		playerX+=30;
	}
	
	public void moveLeft() {
		play = true;
		playerX-=30;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	public void createBlock(Graphics g) {
		int block_score = rand.nextInt(50);
		int x_use = 0;
		g.setColor(Color.white);
		g.fillRect(0,0,85,85);
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+block_score,x_use+40,ycor+40);
	}
	

}
