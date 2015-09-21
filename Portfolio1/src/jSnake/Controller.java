package jSnake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
	
	private int direction = Constants.DIR_RIGHT;
	
	public Controller(){
		Thread t = new Thread(new Movement());
		t.start();
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 47 && e.getKeyCode() <= 50) direction = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {
		
	}

}

class Movement implements Runnable{

	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO move the snake
		}
	}
	
}
