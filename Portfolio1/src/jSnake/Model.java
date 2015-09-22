package jSnake;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.table.DefaultTableModel;

public class Model extends DefaultTableModel{
	private Queue<Pair> snake = new ConcurrentLinkedQueue<Pair>();
	private Pair head;
	
	public Model(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int mColIndex) {
		return false;
	}
	
	public void move(int direction){
		Pair location = new Pair(head);
		switch(direction){
		case Constants.DIR_DOWN:
			location.setY(location.getY() + 1);
		}
		snake.add(location);
	}
	
	public void addToSnake(Pair location){
		snake.add(location);
		setValueAt(Constants.TILE_SNAKE, location.getY(), location.getX());
		head = location;
	}

}
