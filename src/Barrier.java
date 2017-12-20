import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Barrier extends GameObject{
	private static int I_WIDTH = 64;
	private static int I_HEIGHT = I_WIDTH;

	public Barrier(int inputX, int inputY, ID id) {
		super(inputX, inputY, id);
		velX = 0;
		velY = 0;
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 12, 12);
	}

	public Shot shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,12,12);
	}
}