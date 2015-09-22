package jSnake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	
	private int direction = Constants.DIR_RIGHT;
	private Model model;
	
	public Controller(Model model){
		this.model = model;
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 47 && e.getKeyCode() <= 50) direction = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {}
	
	public void update(){
		model.move(direction);
	}
	
	public void startGame(){
		model.addToSnake(new Pair(0, 20));
		model.addToSnake(new Pair(1, 20));
		model.addToSnake(new Pair(2, 20));
		
		Thread t = new Thread(new Movement(this));
		t.start();
	}

}

class Movement implements Runnable{
	
	private Controller controller;
	
	Movement(Controller controller){
		this.controller = controller;
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			controller.update();
		}
	}
	
}
