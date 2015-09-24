package jSnake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	
	int direction = Constants.DIR_RIGHT;
	Model model;
	Boolean paused = false;
	View view;
	
	public Controller(Model model){
		this.model = model;
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) direction = e.getKeyCode();
		
	}

	public void keyReleased(KeyEvent e) {}
	
	public void update(){
		model.move(direction);
	}
	
	public void startGame(){
		model.addToSnake(new Pair(0, 20));
		model.addToSnake(new Pair(1, 20));
		model.addToSnake(new Pair(2, 20));
		model.placeFood();
		
		Thread t = new Thread(new Movement(this));
		t.start();
	}
	public void pauseGame(){
		paused = true;
	}
	public void resumeGame(){
		paused = false;
	}
	public void setView(View view){
		this.view = view;
	}

	

}

class Movement implements Runnable{
	
	private Controller controller;
	
	Movement(Controller controller){
		this.controller = controller;
	}

	public void run() {
		while(controller.view.wallCheck()){
			
			if(!controller.paused){
				
				controller.update();
			}
			
		}
	}
	
}