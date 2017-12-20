import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Defender extends GameObject{
	private static int I_WIDTH = 80;
	private static int I_HEIGHT = I_WIDTH/4;
	private Handler handler;

	public Defender(int inputX, int inputY, ID id, Handler handle) {
		super(inputX, inputY, id);
		this.handler = handle;
		velX = 0;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 80);
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x,y,I_WIDTH,I_HEIGHT);
		g.fillRect(x + 3*I_WIDTH/8, y-I_HEIGHT/2, I_WIDTH/4, I_HEIGHT/2);
		g.fillRect(x + 7*I_WIDTH/16, y-I_HEIGHT/2-I_HEIGHT/4, I_WIDTH/8, I_HEIGHT/4);
	}
	
	public Shot shoot(){
		return new Shot(x + 7*I_WIDTH/16, y-I_HEIGHT*3/4-I_HEIGHT*3/8,ID.Shot,this.handler,ID.Defender);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,I_WIDTH,I_HEIGHT*3/2);
	}

}