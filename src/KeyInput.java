import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private int VelX = 8;
	
	private Handler handler;
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void setVelX(int newVelX){
		this.VelX = newVelX;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		//System.out.println(key);
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.id == ID.Defender){
				//all the key events for Defender
				if (key == 37){
					tempObject.setVelX(-1*this.VelX);
				}
				else if (key == 39){
					tempObject.setVelX(this.VelX);
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.id == ID.Defender){
				//all the key events for player one
				if (key == 37){
					tempObject.setVelX(0);
				}
				else if (key == 39){
					tempObject.setVelX(0);
				}
				else if (key == KeyEvent.VK_SPACE){
					Shot newShot = tempObject.shoot();
					handler.addObject(newShot);
					AudioPlayer.getSound("click").play();
				}
			}
		}
	}


}
