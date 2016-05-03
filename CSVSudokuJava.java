import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Sudoku {

	static int[][] puzzle=new int[9][9];
	static int n=9;

	public static boolean putXinPosition(int x, int y, int val) {

        // Checking if  'val' is in the row.
        for (int i = 0; i < n; i++) {
            if (puzzle[x][i] == val) {
                return false;
            }
        }

     // Checking if  'val' is in column.
        for (int j = 0; j < n; j++) {
            if (puzzle[j][y] == val) {
                return false;
            }
        }

     // Checking if  'val' is in 3x3 box.
        int boxRow = x - x % 3;
        int boxColumn = y - y % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[boxRow + i][boxColumn + j] == val) {
                    return false;
                }
            }
        }

        // If val not there in row or column or box.
        return true;
    }


        public static boolean sudokuBacktrack() {
	        int i = 0, j = 0;
	        boolean isThereEmptyCell = false;

	        for (int ii = 0; ii < n && !isThereEmptyCell; ii++) {
	            for (int jj = 0; jj < n && !isThereEmptyCell; jj++) {
	                if (puzzle[ii][jj] == 0) {
	                    isThereEmptyCell = true;
	                    i = ii;
	                    j = jj;
	                }
	            }
	        }

	        // If there are not empty cells we are done with the puzzle
	        if (!isThereEmptyCell) {
	            return true;
	        }

	        for (int x = 1; x < 10; x++) {

	            if (putXinPosition(i, j, x)) {
	                puzzle[i][j] = x;

	                if (sudokuBacktrack()) {
	                    return true;
	                }

	                puzzle[i][j] = 0; // We've failed.
	            }

	        }

	        return false; // Backtracking
	    }

	public static  void solveThePuzzle(){

		if (!sudokuBacktrack()) {
            System.out.println("This puzzle can't be solved.");
        }

	}

	public static void main(String[] args) throws IOException {
		// Get input from CSV file
		String input="input.csv";
		BufferedReader br = new BufferedReader(new FileReader(input));
		String lines="";
		int i=0,j=0;
		while((lines=br.readLine()) !=null){

		String[] value=lines.split(",");

		for(int k=0;k<value.length;k++){
			puzzle[i][j++]=Integer.parseInt(value[k]);//read values into puzzle array( two dimensional array )
			if(j==9 && i<9)  { i++;  j=0; }
			if(i==9) break;

			}

		}

		solveThePuzzle();

		//Write the solution to CSV file
		String output="output.csv";
		@SuppressWarnings("resource")
		BufferedWriter bw = new BufferedWriter(new FileWriter(output));
		for (int l = 0; l < n; l++) {
	            for (int m = 0; m < n; m++) {
	                bw.write(Integer.toString(puzzle[l][m]));
	                bw.append(",");
	    	            }
	            bw.append("\n");
	         }
		    bw.flush();
	            bw.close();
		}

}
