package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGUI extends JFrame {
	private Sudoku s;
	JPanel[][] boxes;
	JTextField[][] board;

	public SudokuGUI(Sudoku s) {

		super("Sudoku");

		this.s = s;

		JFrame frame = new JFrame("Sudoku Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentArea = new JPanel(new GridLayout(3, 3));
		JPanel buttonArea = new JPanel();

		boxes = new JPanel[3][3];
		board = new JTextField[9][9];

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				boxes[r][c] = new JPanel(new GridLayout(3, 3));

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						board[3 * r + i][3 * c + j] = new JTextField(1);
						boxes[r][c].add(board[3 * r + i][3 * c + j]);
					}
				}

				if ((3 * r + c) % 2 == 0) {
					boxes[r][c].setBackground(Color.lightGray);
				} else {
					boxes[r][c].setBackground(Color.white);
				}

				contentArea.add(boxes[r][c]);

			}
		}

		JButton clear = new JButton("Clear");
		JButton solve = new JButton("Solve");

		clear.addActionListener(new ClearButtonListener());
		solve.addActionListener(new SolveButtonListener());

		frame.add(contentArea, BorderLayout.NORTH);
		frame.add(buttonArea, BorderLayout.SOUTH);
		buttonArea.add(clear);
		buttonArea.add(solve);

		frame.pack();
		frame.setVisible(true);
	}

	private void printFields() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(board[r][c].getText() + " ");
			}
			System.out.print("\n");
		}
	}

	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clear");
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {

					// Clear the GUI
					board[r][c].setText("");

					// Clear the model
					s.setValue(r, c, 0);
				}
			}

		}
	}

	private class SolveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {

					// Get the integer in a field
					int i;
					try {
						i = Integer.parseInt(board[r][c].getText());
					} catch (NumberFormatException exc) {
						continue;
					}

					// Set the model value
					s.setValue(r, c, i);
				}
			}

			// Solve
			if (s.solve()) {
				System.out.println("Sudoku lšstes.");
			} else {
				System.out.println("Sudoku kan ej lšsas.");
			}
			
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					board[r][c].setText(Integer.toString(s.getValue(r, c)));
				}
			}
		}
	}
}
