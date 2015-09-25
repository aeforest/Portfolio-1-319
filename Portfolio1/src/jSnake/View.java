package jSnake;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;

public class View extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static Model model = new Model(50,100);
	private static Controller controller = new Controller(model);
	private static int highScore = 0;
	private JLabel lblScore;
	private JLabel lblHighScore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			File file = new File("HighScore.txt");
			Scanner s = new Scanner(file);
			highScore = s.nextInt();
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
					frame.addKeyListener(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		model.setView(this);
		controller.setView(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblScore = new JLabel("Score: " + 3);
		lblScore.setBounds(87, 0, 60, 15);
		contentPane.add(lblScore);
		table = new JTable(model);
		table.setBounds(0, 15, 1000, 500);
		table.setRowHeight(10);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < 100; i++){
			table.getColumnModel().getColumn(i).setMaxWidth(10);
			table.getColumnModel().getColumn(i).setMinWidth(10);
			table.getColumnModel().getColumn(i).setPreferredWidth(10);
		}
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column){ 
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value == null){
					c.setBackground(table.getBackground());
					return c;
				}
				
				if (value.equals(Constants.TILE_SNAKE)){
					c.setBackground(Color.BLACK);
					c.setForeground(Color.BLACK);
				}
				else if(value.equals(Constants.TILE_FOOD)){
					c.setBackground(Color.RED);
					c.setForeground(Color.RED);
				}
				else{
					c.setBackground(table.getBackground());
					c.setForeground(table.getBackground());
				}
				return c; 
			 }
		});
		
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object source = e.getSource();
		        if (source instanceof JButton) {
		            JButton btn = (JButton)source;
		            
		            if(btn.getText().equals("Start")){
		            	controller.startGame();
		            	btn.setText("Pause");
					}
					else if(btn.getText().equals("Pause")){
						controller.pauseGame();
						btn.setText("Resume");
					}
					else{
						controller.resumeGame();
						btn.setText("Pause");
					}
		        }
		        
				
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(0, 0, 87, 15);
		contentPane.add(btnNewButton);
		
		lblHighScore = new JLabel("High Score: ");
		lblHighScore.setBounds(150, 0, 96, 14);
		lblHighScore.setText("High Score: " + highScore);
		contentPane.add(lblHighScore);
		
		
	}
	
	public void refreshScore(int score){
		
		if(score > highScore){
		highScore = score;	
		lblHighScore.setText("High Score: " + score);
		}
		lblScore.setText("Score: " + score);
	}
	public Boolean wallCheck(){
		Object[] options = {"New Game",
                "Exit"};
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(model.head.getX() == 0 && controller.direction == 37 ||
		model.head.getY() == 0 && controller.direction == 38 ||
		model.head.getX() == 99 && controller.direction == 39 ||
		model.head.getY() == 49 && controller.direction == 40){
			
			int n = JOptionPane.showOptionDialog(null, "High Score is: " + highScore +
					 "\nYour " + lblScore.getText(), "Game Over!",
					 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
					 null, options, options[0]);
			try {
				FileWriter fileOut = new FileWriter("HighScore.txt");
				fileOut.write("");
				String str = "" + highScore;
				fileOut.write(str);
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(n == 0){
				this.dispose();
				model = new Model(50,100);
				controller = new Controller(model);
				
				View.main(null);	
			}
			else{
				
				System.exit(0);
			}
			return false;
		}
		return true;
	}
}