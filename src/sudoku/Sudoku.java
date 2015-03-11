package sudoku;

public class Sudoku {
	int[][] board;
	int counter = 0;

	public Sudoku() {
		this.board = new int[9][9];
	}

	/**
	 * Sets the value of cell row,col.
	 * 
	 * @param sudoku row row
	 * @param sudoku column col
	 * @param value to set
	 * @return true if value was set
	 */
	public boolean setValue(int row, int col, int value) {
		if (value >= 0 && value <= 9) {
			board[row][col] = value;
			return true;
		} else
			return false;
	}

	/**
	 * Gets the value in a cell at position row,col.
	 * 
	 * @param sudoku row row
	 * @param sudoku column col
	 * @return value in cell
	 */
	public int getValue(int row, int col) {
		return board[row][col];
	}

	/**
	 * String representation of the class.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	/**
	 * Solves the sudoku with recursion.
	 * @return true if sudoku was solved.
	 */
	public boolean solve() {
		// Check valid sudoku
		if (!validSudoku(0, 0))
			return false;

		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (row == 9) {
			return true;
		}
		int v = getValue(row, col);
		if (v != 0) {
			if (solve(col == 8 ? row + 1 : row, (col + 1) % 9))
				return true;
		} else {
			for (int val = 1; val <= 9; val++) {
				if (valid(row, col, val)) {
					setValue(row, col, val);
					if (solve(col == 8 ? row + 1 : row, (col + 1) % 9)) {
						return true;
					} else {
						setValue(row, col, 0);
					}
				}
			}
		}
		return false;
	}

	private boolean validSudoku(int row, int col) {
		if (row == 9) {
			return true;
		}
		int value = getValue(row, col);
		if (value != 0) {
			setValue(row, col, 0);
			if (valid(row, col, value)) {
				setValue(row, col, value);
				if (validSudoku(col == 8 ? row + 1 : row, (col + 1) % 9)) {
					return true;
				}
			}
		} else {
			if (validSudoku(col == 8 ? row + 1 : row, (col + 1) % 9)) {
				return true;
			}
		}
		return false;
	}

	private boolean valid(int row, int col, int value) {
		// Can always set value to zero
		if (value == 0)
			return true;

		int[][] set = getSetToCheck(row, col, value);

		for (int val : set[0]) {
			if (val == value)
				return false;
		}
		for (int val : set[1]) {
			if (val == value)
				return false;
		}
		for (int val : set[2]) {
			if (val == value)
				return false;
		}
		return true;
	}

	private int[][] getSetToCheck(int row, int col, int value) {
		int[][] set = new int[3][9];
		set[0] = new int[9];
		set[1] = new int[9];
		set[2] = new int[9];

		// Get the row array
		for (int c = 0; c < 9; c++) {
			set[0][c] = board[row][c];
		}

		// Get the column array
		for (int r = 0; r < 9; r++) {
			set[1][r] = board[r][col];
		}

		// Get the box array
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				set[2][c + r * 3] = board[3 * (row / 3) + r][3 * (col / 3) + c];
			}
		}
		return set;
	}
}
