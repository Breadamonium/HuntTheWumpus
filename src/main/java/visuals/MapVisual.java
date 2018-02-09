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
		grid[3][0].setBackground(Color.BLACK);
		grid[3][0].setForeground(Color.WHITE);
		grid[3][0].setText("BLOCKED");
	}

	public JLabel[][] getGrid() {
		return grid;
	}
}
