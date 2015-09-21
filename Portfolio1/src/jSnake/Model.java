package jSnake;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.table.DefaultTableModel;

public class Model extends DefaultTableModel{
	private Queue<Pair> snake = new ConcurrentLinkedQueue<Pair>();
	
	public Model(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int mColIndex) {
		return false;
	}

}
