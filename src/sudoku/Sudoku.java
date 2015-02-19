package sudoku;

public class Sudoku {
	int[][] board;
	
	public Sudoku() {
		this.board = new int[9][9];
		
		setValue(0,0,1);
		setValue(4,4,5);
		setValue(2,1,9);
		setValue(2,8,2);
		setValue(1,4,9);
	}
	
	/**
	 * Sets the value of cell i,j
	 * @param i row
	 * @param j col
	 * @param value to set
	 * @return
	 */
	public boolean setValue (int i, int j, int value) {
		board[i][j] = value;
		return true;
	}
	
	/**
	 * Gets the value in a cell at position i,j
	 * @param i row
	 * @param j col
	 * @return value in cell
	 */
	public int getValue (int i, int j) {
		return board[i][j];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}