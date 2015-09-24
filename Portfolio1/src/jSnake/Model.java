package jSnake;


import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.table.DefaultTableModel;

public class Model extends DefaultTableModel{
	private Queue<Pair> snake = new ConcurrentLinkedQueue<Pair>();
	Pair head;
	Random rand = new Random();
	private Pair food = new Pair(20, 0);
	private int score = 3;
	private View view;
	
	public Model(int i, int j) {
		super(i, j);
	}
	
	public void setView(View view){
		this.view = view;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int mColIndex) {
		return false;
	}
	
	public void move(int direction){
		Pair location = new Pair(head);
		switch(direction){
		case Constants.DIR_LEFT:
			location.setX(location.getX() - 1);
			break;
		case Constants.DIR_UP:
			location.setY(location.getY() - 1);
			break;
		case Constants.DIR_RIGHT:
			location.setX(location.getX() + 1);
			break;
		case Constants.DIR_DOWN:
			location.setY(location.getY() + 1);
			break;
		}
		if(Constants.TILE_FOOD.equals(getValueAt(location.getY(), location.getX()))){
			addToSnake(location);
			score++;
			placeFood();
		} else{
			addToSnake(location);
			Pair tail = snake.remove();
			setValueAt("", tail.getY(), tail.getX());
		}
	}
	
	public void addToSnake(Pair location){
		snake.add(location);
		setValueAt(Constants.TILE_SNAKE, location.getY(), location.getX());
		head = location;
	}
	
	public void placeFood(){
		while(Constants.TILE_SNAKE.equals(getValueAt(food.getY(), food.getX()))){
			food.setX(rand.nextInt(100));
			food.setY(rand.nextInt(50));
		}
		setValueAt(Constants.TILE_FOOD, food.getY(), food.getX());
		view.refreshScore(score);
	}

}
