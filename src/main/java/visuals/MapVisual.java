package main.java.visuals;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MapVisual extends JFrame {
	private static final long serialVersionUID = -4788147486642761656L;
	
	private JLabel[][] grid;
	
	@SuppressWarnings("unused")
	private MapVisual() {
	}
	
	public MapVisual(int columns, int rows) {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		panel.setLayout(new GridLayout(rows, columns));

		grid = new JLabel[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new JLabel();
				grid[i][j].setBorder(new LineBorder(Color.BLACK));
				grid[i][j].setOpaque(true);
				grid[i][j].setForeground(Color.WHITE);
				panel.add(grid[i][j]);
			}
		}
		grid[0][0].setText("PLAYER");
		grid[rows - 1][columns - 1].setText("WUMPUS");
		
		grid[1][3].setBackground(Color.YELLOW);
		grid[1][3].setForeground(Color.BLACK);
		grid[1][3].setText("PIT");		
		grid[2][1].setBackground(Color.YELLOW);
		grid[2][1].setForeground(Color.BLACK);
		grid[2][1].setText("PIT");		
		grid[0][4].setBackground(Color.GREEN);
		grid[0][4].setForeground(Color.WHITE);
		grid[0][4].setText("BATS");		
	}

	public JLabel[][] getGrid() {
		return grid;
	}
}
