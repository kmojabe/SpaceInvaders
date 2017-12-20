import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shot extends GameObject {
	
	public int SPEED = -20;
	private Handler handler;
	private ID owner;
	public Shot(int inputX, int inputY, ID id, Handler handle, ID owner) {
		super(inputX, inputY, id);
		this.velY =SPEED;
		this.handler = handle;
		this.owner = owner;
	}

	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 30);
		collision();
		outOfBounds();
	}

	public void render(Graphics g) {
		if (this.owner == ID.Defender){
			g.setColor(Color.green);
		} else{
			g.setColor(Color.white);
		}
		g.fillRect(x,y,7,7);
	}

	public Shot shoot() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,7,7);
	}

	private void collision(){
		for (int i =0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if ((tempObject.getID() == ID.Invader) && (this.owner == ID.Defender)){
				if (getBounds().intersects(tempObject.getBounds())){
					//collision has occured
					Invader myInvader = (Invader) tempObject;
					if (myInvader.aboveMe != null){
						myInvader.aboveMe.setDropBomb(true);
					}
					handler.removeObject(tempObject);
					this.remove = true;
					HealthBar.score +=1;
				}
			}			
			else if ((tempObject.getID() == ID.Barrier)){
				if (getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					this.remove = true;
				}
			}
			else if ((tempObject.getID() == ID.Defender)&& (this.owner == ID.Invader)){
				if (getBounds().intersects(tempObject.getBounds())){
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

	private void outOfBounds(){
		//System.out.println(this.y + " " + Game.HEIGHT);
		if ((this.y == 0) || (this.y >= Game.HEIGHT - 30)){
			this.remove = true;
		}
	}
}
