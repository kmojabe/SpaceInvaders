import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public int direction;
	public static int InvaderSpeed = 1;
	private int rowInvader = 0;
	
	public void tick(){
		for (int i = 0; i < object.size(); i++){
			GameObject newObject = object.get(i); //gets the object i within the linked list
			if (newObject.remove){
				this.removeObject(newObject);
			} else{
				newObject.tick();
			}
			if (newObject.id == ID.Invader){
				commandInvaders(newObject);
			}
		}
	}
	
	public void render(Graphics g){
		for (int i = 0; i < object.size(); i++){
			GameObject newObject = object.get(i); //gets the object i within the linked list
			
			newObject.render(g);
		}
	}
	
	public void addObject(GameObject newObject){
		this.object.add(newObject);
	}
	
	public void setInvaderSpeed(int speed){
		this.InvaderSpeed = speed;
	}

	public void removeObject(GameObject oldObject){
		this.object.remove(oldObject);
	}

	public void commandInvaders(GameObject invaderObject){
		if (((invaderObject.x <= 5) || (invaderObject.x >= Game.WIDTH - 45)) && invaderObject.dropBomb){
			this.direction = this.direction * -1;
			for (int i = 0; i < object.size(); i++){
				GameObject tempObject = object.get(i);
				if (tempObject.id == ID.Invader){
					//Invader tempInvader = (Invader) tempObject;
					tempObject.setY(tempObject.getY() + 80);
				}
			}
		}
		
		invaderObject.setVelX(InvaderSpeed*direction);
	}
}
