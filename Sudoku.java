package sudoku;

public class Sudoku {
	
	private int[][] sudoku;
	
	

	/**
	 * Default constructor -- construct an empty Sudoku puzzle
	 */
	public Sudoku() {
		
	}
    public Sudoku(int sudoku[][])
    {
        this.sudoku= sudoku;
    }


	/**
	 * @param board
	 * @throws Exception
	 */
	
	public void inputBoard(char [][] board) throws Exception {
		
		
		if(board.length > 9) {
			throw new Exception("This board is not acceptable to be used for this solver!");
		}
		for(int row= 0; row <9; row++ ) {
			for(int col = 0; col <9; col++) {
				board[row][col] = (char) Character.getNumericValue(board[row][col]);
				if(board[row][col] > 10 || board[row][col] < 0 ) {
					//throw new Exception("The values that are used in this board are not acceptable for this solver!");
				}
			}
			
		}
		
	}

	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 */
    public boolean solveSudoku(){
    	for(int row=0;row<9;row++)
        {
            for(int col=0;col<9;col++)
            {
                if(sudoku[row][col]==0)
                {
                    for(int number=1;number<=9;number++)
                    {
                        if(isSafe(row, col, number))
                        {
                            sudoku[row][col] = number;
                            if(solveSudoku())
                            {
                                return true;
                            }
                            else
                            {
                                sudoku[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
	
	public boolean isSafeRowAndCol(int row, int col, int numberInput) {
		for(int i = 0; i < 9; i++) {
			if(this.sudoku[row][i] == numberInput) {
				return false;
			}
		}
		for(int i = 0; i < 9; i++) {
			if(this.sudoku[i][col] == numberInput) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isSafeBox(int row, int col, int numberInput) {
	
		for(int myRow = 0; myRow < 3; myRow++) {
			for(int myCol = 0; myCol < 3; myCol++) {
				if(this.sudoku[myRow + row][myCol + col] == numberInput) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSafe(int row, int col, int inputNumber) {
		return (isSafeBox(row - (row % 3), col - (col % 3), inputNumber) && isSafeRowAndCol(row, col, inputNumber));
	}
	
	

	/**
	 * Prints the Sudoku board to the console.
	 */
	public void printBoard() 
	{
		System.out.println("________________________________");
        for(int i=0;i<9;i++)
        {
            if(i%3==0 && i!=0)
            {
                System.out.println("--------------------------------");
            }
            for(int j=0;j<9;j++)
            {
                if(j%3==0 && j!=0)
                {
                    System.out.print(" | ");
                }
                System.out.print(" " + sudoku[i][j] + " ");
      
            }
      
            System.out.println();
        }
        System.out.println("________________________________");
    }
    

	public static void main(String[] args) {
		int[][] array = {    
                {3, 8, 2, 0, 0, 0, 0, 0, 0},       
                {0, 0, 4, 0, 0, 5, 2, 0, 0},                                                                                       
                {6, 5, 0, 7, 2, 0, 3, 4, 0},
                {0, 3, 0, 0, 0, 9, 0, 0, 0},
                {4, 6, 1, 0, 0, 2, 9, 5, 3},
                {8, 9, 5, 4, 0, 0, 0, 2, 7},
                {5, 0, 0, 9, 0, 0, 0, 0, 2},
                {9, 4, 0, 0, 0, 7, 8, 3, 5},
                {1, 2, 0, 5, 4, 3, 7, 9, 0}
               };
		
		char[][] array2 = {    
                {'3', '8', '2', '0', '0', '0', '0', '0', '0'},       
                {'0', '0', '4', '0', '0', '5', '2', '0', '0'},                                                                                       
                {'6', '5', '0', '7', '2', '0', '3', '4', '0'},
                {'0', '3', '0', '0', '0', '9', '0', '0', '0'},
                {'4', '6', '1', '0', '0', '2', '9', '5', '3'},
                {'8', '9', '5', '4', '0', '0', '0', '2', '7'},
                {'5', '0', '0', '9', '0', '0', '0', '0', '2'},
                {'9', '4', '0', '0', '0', '7', '8', '3', '5'},
                {'1', '2', '0', '5', '4', '3', '7', '9', '0'}
               };
		
		Sudoku sudoku = new Sudoku(array);
		Sudoku sudoku2 = new Sudoku();
		
		try {
			sudoku2.inputBoard(array2);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		sudoku.solveSudoku();
		sudoku.printBoard();
	}
	
}
