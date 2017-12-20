import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	public static void load(){
		try {
			//musicMap.put("click", new Music("resources/Laser.ogg"));
			soundMap.put("click", new Sound("resources/newLaser.ogg"));
			soundMap.put("bomb", new Sound("resources/bombFalling.ogg"));
			musicMap.put("music", new Music("resources/SpaceInvaders.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
}
