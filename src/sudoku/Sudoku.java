package sudoku;

public class Sudoku {
	int[][] board;

	public Sudoku() {
		this.board = new int[9][9];

		setValue(0,0,1);
		setValue(0,1,2);
		setValue(5,6,5);
		
		valid(5,5, getValue(0,0));
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
		board[i][j] = value;
		return true;
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
	
	public boolean solve(){
		return solve(0,0);
	}

	private boolean solve(int row, int col) {

		// getValue från i,j
		// kolla om cellen är ifylld av användaren
		// ifylld: checka giltighet (rad, kolumn, ruta) returnera falskt om
		// ogiltig, annars gå vidare
		// ej ifylld: fyll med 1-9. Checka giltighet. Om giltig, gör rekursivt
		// anrop till nästa cell.
		int v = getValue(row, col);
		if (v != 0) {
			if (valid(row, col, v)) {
				if (row < 8 && col < 8) {
					return solve(row, col + 1);
				} else if (row < 8 && col == 8) {
					return solve(row + 1, 0);
				} else {
					return true;
				}
			} else {
				setValue(row,col,0);
				return false;
			}
		} else {
			for (int k = 1; k <= 9; k++) {
				if (valid(row, col, k)) {
					if (row < 8 && col < 8) {
						setValue(row,col,k);
						return solve(row, col + 1);
					} else if (row < 8 && col == 8) {
						setValue(row,col,k);
						return solve(row + 1, 0);
					} else {
						return true;
					}
				}else{
					setValue(row,col,0);
					return false;
				}
			}
		}
		return false;
	}

	private boolean valid (int i, int j, int value) {
		int[][] set = getSetToCheck(i, j, value);
			
		System.out.print("Row: ");
		for (int a = 0; a < 8; a++) {
			System.out.print(set[0][a] + " ");
		}
		System.out.print("\n");
		
		System.out.print("Col: ");
		for (int a = 0; a < 8; a++) {
			System.out.print(set[1][a] + " ");
		}
		System.out.print("\n");
		
		System.out.print("Box: ");
		for (int a = 0; a < 8; a++) {
			System.out.print(set[2][a] + " ");
		}
		System.out.print("\n");
					
		return false;
	}
	
	private int[][] getSetToCheck (int row, int col, int value) {
		
		System.out.println(row + ", " + col);
		
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
				set[2][c + r*3] = board[row/3 + r][col/3 + c];
			}
		}
		
		return set;
		
	}

}
