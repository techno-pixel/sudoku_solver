package Soodookoo;

class Soodookoo {
    private int[][] grid;
    private boolean[][] original = new boolean[9][9];
    private int location = 0;
    private int counter = 1;

    /*initialize sudoku grid
    implement original boolean that checks if a number was there previously
    program can only add numbers if the initial value is 0*/
    public boolean initializeGrid() {
         grid = new int[][]{
                {8,0,0,0,0,0,0,0,0},
                {0,0,3,6,0,0,0,0,0},
                {0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
                {0,0,0,0,4,5,7,0,0},
                {0,0,0,1,0,0,0,3,0},
                {0,0,1,0,0,0,0,6,8},
                {0,0,8,5,0,0,0,1,0},
                {0,9,0,0,0,0,4,0,0}
        };

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(grid[row][col] != 0) {
                    original[row][col] = true;
                } else {
                    original[row][col] = false;
                }
            }
        }
        // prints our the grid
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        return true;
    }

    /*
    * this checks the surrounding 3x3 personal grid if it contains the same value
    * we can conceptually visualize the grid to contain 9 separate blocks or grids
    * (0,0) (0,1) (0,2) -- (1,0) (1,1) (1,2) -- (2,0) (2,1) (2,2)
    * since we are using integers, the division is a FLOOR division. we have a simple arithmetic solution
    * if we are in row 4, col 2 for example
    * 4/3 = 1 -- 2/3 = 0 .... so we are in block (1,0) which is correct BLOCK
    * by multiplying the answer by 3, and also adding a 2 later on, we can find the corners of the block
    * 1*3 && 1*3+2 = 3 & 5 - so the row boundaries will be from row 3 and row 5
    * 0*3 && 0*3+2 = 0 & 2 - so the column boundaries will be from col 0 and col 2
     */
    public boolean checkGrid(int row, int col, int value) {
        boolean existsInGrid = false;
        for(int i = (row/3*3); i<=(row/3*3+2); i++) {
            for(int j=(col/3*3); j<=(col/3*3+2); j++) {
                if(grid[i][j] == value){
                    existsInGrid = true;
                }
            }
        }
        return existsInGrid;
    }

    /*checks against the columns to see if the same value exists in row*/
    public boolean checkRow(int row, int value) {
        boolean existsInRow = false;
        for(int col = 0; col < 9; col++) {
            if(grid[row][col] == value){
                existsInRow = true;
            }
        }
        return existsInRow;
    }

    /*checks against the rows to see if the same value exists in col*/
    public boolean checkCol(int col, int value) {
        boolean existsInCol = false;
        for(int row = 0; row < 9; row++){
            if(grid[row][col] == value){
                existsInCol = true;
            }
        }
        return existsInCol;
    }

    /*add all the booleans into one boolean method, to know if it is allowed to place the value*/
    private boolean isAllowed(int row, int col, int value) {
        if ((checkCol(col,value) == false && checkRow(row, value) == false && checkGrid(row, col, value) == false)) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * here we set the location and use a while loop with recursion (calling the same method from within)
    * set counter at 1 (the value from 1-9 that we add, which we will increase or decrease)
    * location will be under 81 because there are 81 squares on a 9x9 sudoku grid
    * to find the location, we do location / 9 and for column location % 9 to get a remainder
    * apply the booleans, set the value, increase location, reiterate through the loop
    * if it can no longer add a value, this is where the } else { after ret == true comes in
    * we set the value at 0 again, and backtrack with l--
    * increase the count of the last number if able to, or set to 0 and backtrack again
     */
    public boolean setLocation(int l) {
        int count=1;
        while(l < 81 && l >=0) {
            int row =  (l/9);
            int col = (l%9);
            if(!original[row][col]) {
                while (count < 10) {
                    if (isAllowed(row, col, count) == true) {
                        grid[row][col] = count;
                        l++;
                        boolean ret = setLocation(l);
                        if (ret == true) {
                            return true;
                        } else {
                            grid[row][col] = 0;
                            l--;
                        }
                    }
                    count++;
                }
                return false;
            }
            l++;
        }
        return true;
    }

    /*set the location to 0, start in top left corner. print the grind and call it in the main method*/
    public boolean solveGrid() {
        setLocation(0);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
        return true;
    }
}

public class Sudoku {
    public static void main(String[] args) {
        Soodookoo sudoku = new Soodookoo();
        sudoku.initializeGrid();
        sudoku.solveGrid();
    }
}
