
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1442798787354930462L;

	public static int WIDTH = 1024;
	public static int HEIGHT = WIDTH * 5 /8;
	public static int INV_COL = 10;
	public static int INV_ROW = 3;
	
	
	private Thread myThread;
	private boolean running = false;
	private Handler handler;
	private HealthBar healthBar;
		
	public Game(){
		this.handler = new Handler();
		this.handler.direction = 1;
		this.addKeyListener(new KeyInput(handler));
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT,"Space Invaders", this);
		
		//set Invader Speed
		handler.setInvaderSpeed(1);
		//set Invader Columns
		setInvaderCol(8);
		setInvaderRow(2);
		
		healthBar = new HealthBar();
		
		//adjust number of lives
		//healthBar.setLives(5);
		
		//adjust rate of firing
		createInvaders(3);
		
		handler.addObject(new Defender(WIDTH/2 - 40, HEIGHT*7/8 - 10, ID.Defender, this.handler));
		for (int i =0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				handler.addObject(new Barrier(WIDTH*3/16 - 32 + i*16, HEIGHT*2/3 - 32 + j*16, ID.Barrier));
				handler.addObject(new Barrier(WIDTH/2 - 32 + i*16, HEIGHT*2/3 - 32 + j*16, ID.Barrier));
				handler.addObject(new Barrier(WIDTH*13/16 - 32 + i*16, HEIGHT*2/3 - 32 + j*16, ID.Barrier));
			}
		}
		
	}
	
	public synchronized void start(){
		myThread = new Thread(this);
		myThread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			myThread.join();
			running = false;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta--;
			}
			if (running){
				render();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		healthBar.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		healthBar.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int variable, int min, int max){
		if (variable >= max){
			return variable = max;
		} else if (variable <= min){
			return variable = min;
		}
		else{
			return variable;
		}
	}
	
	public void createInvaders(int rate){
		LinkedList<Invader> invaders = new LinkedList<Invader>();
		for (int i=0; i < INV_COL; i++){
			for (int j=0; j < INV_ROW; j++){
				Invader newInvader;
				if (j == 0){
					newInvader = new Invader(6 + 80*i,HEIGHT/8 - 16 + 80*j,ID.Invader, handler, null);
				} else{
					newInvader = new Invader(6 + 80*i,HEIGHT/8 - 16 + 80*j,ID.Invader, handler, invaders.getLast());
				}
				if (j == (INV_ROW-1)){
					newInvader.setDropBomb(true);
				}
				newInvader.rateFiring = rate*100;
				invaders.add(newInvader);
			}
		}
		for (int i =0; i < invaders.size(); i++){
			Invader newInvader = invaders.get(i);
			handler.addObject(newInvader);
		}
	}
	public static void main(String args[]) {
		new Game();
	}
	
	public void setInvaderCol(int columns){
		this.INV_COL = columns;
	}
	
	public void setInvaderRow(int rows){
		this.INV_ROW = rows;
	}
}
