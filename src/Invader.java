import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Invader extends GameObject{
	public static int I_WIDTH = 40;
	public static int I_HEIGHT = 32;
	public static int rateFiring = 100;
	private Handler handler;
	private Random r;
	//new below
	public Invader aboveMe;
	//public Boolean dropBomb = false;
	
	public Invader(int inputX, int inputY, ID id, Handler handle, Invader aboveMe) {
		super(inputX, inputY, id);
		velX = 0;
		this.handler = handle;
		this.r = new Random();
		this.aboveMe = aboveMe;
	}

	public void tick() {

		x += velX;
		y += velY;
		int num = r.nextInt(rateFiring);
		if ((num == 1) && dropBomb){
			Shot newShot = shoot();
			handler.addObject(newShot);
		}
		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,y,I_WIDTH,I_HEIGHT);
		g.fillRect(x, y+I_HEIGHT, I_WIDTH/8, I_WIDTH - I_HEIGHT);
		g.fillRect(x+I_WIDTH*7/8, y+I_HEIGHT, I_WIDTH/8, I_WIDTH - I_HEIGHT);
		g.fillRect(x+I_WIDTH/8, y+I_WIDTH, I_WIDTH*5/16, I_WIDTH/8);
		g.fillRect(x+I_WIDTH*9/16, y+I_WIDTH, I_WIDTH*5/16, I_WIDTH/8);
		g.fillRect(x+I_WIDTH/4, y-I_WIDTH/8, I_WIDTH/8, I_WIDTH/8);
		g.fillRect(x+I_WIDTH*5/8, y-I_WIDTH/8, I_WIDTH/8, I_WIDTH/8);
		g.fillRect(x+I_WIDTH/8, y-I_WIDTH/4, I_WIDTH/8, I_WIDTH/8);
		g.fillRect(x+I_WIDTH*6/8, y-I_WIDTH/4, I_WIDTH/8, I_WIDTH/8);
		
		g.setColor(Color.black);
		g.fillRect(x+I_WIDTH/4, y+I_HEIGHT/4, I_WIDTH/8, I_WIDTH/8);
		g.fillRect(x+I_WIDTH*5/8, y+I_HEIGHT/4, I_WIDTH/8, I_WIDTH/8);
	}

	@Override
	public Shot shoot() {
		Shot retShot = new Shot(x + I_WIDTH/2, y + I_HEIGHT,ID.Shot,this.handler, ID.Invader);
		retShot.setVelY(-1 * retShot.SPEED/4);
		AudioPlayer.getSound("bomb").play();
		return retShot;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,I_WIDTH,I_WIDTH*9/8);
	}
	
	public void setDropBomb(Boolean dropBomb){
		this.dropBomb = dropBomb;
	}
	
	private void collision(){
		for (int i =0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Defender) {
				if (getBounds().intersects(tempObject.getBounds())){
					//collision has occured
					HealthBar.LIVES -= 1;
					handler.removeObject(tempObject);
					if (HealthBar.LIVES > 0){
						handler.addObject(new Defender(Game.WIDTH/2 - 40, Game.HEIGHT*7/8 - 10, ID.Defender, this.handler));
					}
					else{
						System.exit(1);
					}
					this.remove = true;
				}
			}			
			
		}
	}

}
