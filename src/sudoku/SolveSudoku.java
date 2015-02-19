package sudoku;

public class SolveSudoku {
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		System.out.println(s);
		if(s.solve()){
			System.out.println(s);
		}else{
			System.out.println("Sudoko kan ej lösas");
		}
	}
	
}