import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {

	public static int LIVES = 3;
	public static int score = 0;
	
	public void tick(){
		LIVES = Game.clamp(LIVES, 0, 3);
	}
	
	public void render(Graphics g){
		
		for (int i = 0; i < LIVES; i ++){
			g.setColor(Color.green);
			g.fillRect(25 + i*50,25,40,10);
			g.fillRect(25 + i*50 + 3*40/8, 25-10*3/4, 40/4, 10*3/4);
			g.fillRect(25 + i*50 + 7*40/16, 25-10*3/4-10*3/8, 40/8, 10*3/8);
			g.setColor(Color.white);
			g.drawRect(25 + i*50,25,40,10);
			g.drawRect(25 + i*50 + 3*40/8, 25-10*3/4, 40/4, 10*3/4);
			g.drawRect(25 + i*50 + 7*40/16, 25-10*3/4-10*3/8, 40/8, 10*3/8);
			
		}
		 g. drawString("Score: " + score, 25, 48);

	}
	
	public void setLives(int lives){
		this.LIVES = lives;
	}
}
