package sudoku;

public class SolveSudoku {
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
//		System.out.println(s.toString());
//		if(s.solve()){
//			System.out.println(s.toString());
//		}else{
//			System.out.println("Sudoko kan ej lösas");
//		}
		
		SudokuGUI sgui = new SudokuGUI(s);		
		
	}
	
}