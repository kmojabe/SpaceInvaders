import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y;
	protected ID id;
	protected int velX, velY;
	public Boolean remove;
	public Boolean dropBomb = false;
	
	public GameObject(int inputX, int inputY, ID id){
		this.id = id;
		this.x = inputX;
		this.y = inputY;
		remove = false;
	}
	
	public abstract void tick();
	public abstract Shot shoot();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int newX){
		this.x = newX;
	}
	public void setY(int newY){
		this.y = newY;
	}
	public void setID(ID newID){
		this.id = newID;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public ID getID(){
		return this.id;
	}
	public void setVelX(int newVelX){
		this.velX = newVelX;
	}
	public void setVelY(int newVelY){
		this.velY = newVelY;
	}
}
