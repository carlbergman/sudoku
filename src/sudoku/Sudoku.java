package sudoku;

public class Sudoku {
	int[][] board;
	int counter = 0;
	boolean solved=false;

	public Sudoku() {
		this.board = new int[9][9];

		 setValue(0, 0, 1);
		 setValue(0, 1, 2);
		 setValue(0, 2, 3);
		 setValue(1, 0, 5);
		 //setValue(1, 1, 5);
		 setValue(1, 2, 6);
		 setValue(2, 0, 7);
		 setValue(2, 1, 8);
		 setValue(2, 2, 9);
		 setValue(0, 3, 1);
		 setValue(0, 3, 4);
		 setValue(7, 0, 8);
		 setValue(8, 2, 9);
	}

	/**
	 * Sets the value of cell i,j
	 * 
	 * @param i
	 *            row
	 * @param j
	 *            col
	 * @param value
	 *            to set
	 * @return
	 */
	public boolean setValue(int i, int j, int value) {
		if (value >= 0 && value <= 9) {
			board[i][j] = value;
			return true;
		} else
			return false;
	}

	/**
	 * Gets the value in a cell at position i,j
	 * 
	 * @param i
	 *            row
	 * @param j
	 *            col
	 * @return value in cell
	 */
	public int getValue(int i, int j) {
		return board[i][j];
	}

	/**
	 * String representation of the class
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

	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {
		System.out.println(counter);
		counter++;
//		if(counter==200){
//			System.out.println("Finally...");
//		}
		boolean solveReturn = false;

		int v = getValue(row, col);
		if (v != 0) {
			setValue(row, col, 0);
			if (valid(row, col, v)) {
				setValue(row, col, v);
				if (col < 8) {
					solveReturn = solve(row, col + 1);
				} else if (row < 8) {
					solveReturn = solve(row + 1, 0);
				} else {
					solved=true;
					solveReturn = true;
				}
			} else {
				solved=true;
				return false;
			}
		} else {
			for (int val = 1; val <= 9; val++) {
				if(solved==true)
					break;
				if (valid(row, col, val)) {
					if (col < 8) {
						setValue(row, col, val);
						solveReturn = solve(row, col + 1);
					} else if (row < 8) {
						setValue(row, col, val);
						solveReturn = solve(row + 1, 0);
					} else {
						setValue(row, col, val);
						solved=true;
						return true;
					}
				} else {
					if (val == 9) {
						setValue(row, col, 0);
						solveReturn = false;
					}
				}
			}
		}
		return solveReturn;
	}

	private boolean valid(int i, int j, int value) {
		// Can always set value to zero
		if (value == 0)
			return true;

		int[][] set = getSetToCheck(i, j, value);

		// System.out.print("Row: ");
		for (int val : set[0]) {
			if (val == value)
				return false;
			// System.out.print(set[0][a] + " ");
		}
		// System.out.print("\n");

		// System.out.print("Col: ");
		for (int val : set[1]) {
			if (val == value)
				return false;
			// System.out.print(set[1][a] + " ");
		}
		// System.out.print("\n");

		// System.out.print("Box: ");
		for (int val : set[2]) {
			if (val == value)
				return false;
			// System.out.print(set[2][a] + " ");
		}
		// System.out.print("\n");

		return true;
	}

	private int[][] getSetToCheck(int row, int col, int value) {

		// System.out.println(row + ", " + col);

		int[][] set = new int[3][9];
		set[0] = new int[9];
		set[1] = new int[9];
		set[2] = new int[9];

		// Get the row array
		for (int c = 0; c < 8; c++) {
			set[0][c] = board[row][c];
		}

		// Get the column array
		for (int r = 0; r < 8; r++) {
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
