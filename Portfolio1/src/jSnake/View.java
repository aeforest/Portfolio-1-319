package jSnake;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class View extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int score = 4;
	private static Model model = new Model(50,100);
	private static Controller controller = new Controller(model);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
					frame.addKeyListener(controller);
					controller.startGame();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblScore = new JLabel("Score: " + score);
		lblScore.setBounds(0, 0, 46, 14);
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
	}
}
