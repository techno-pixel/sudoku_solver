# sudoku-solver
Solve any sudoku grid automatically

How it works:

                {8,0,0,0,0,0,0,0,0},
                {0,0,3,6,0,0,0,0,0},
                {0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
                {0,0,0,0,4,5,7,0,0},
                {0,0,0,1,0,0,0,3,0},
                {0,0,1,0,0,0,0,6,8},
                {0,0,8,5,0,0,0,1,0},
                {0,9,0,0,0,0,4,0,0}
                
You can change the numbers as you like in the program, so long as it's solvable.

The program creates an "original" boolean grid against this grid, to determine if there is an original value, i.e if it is a value > 0

Create a boolean to check the personal 3x3 grid:
  * we can conceptually visualize the grid to contain 9 separate blocks or grids
  * (0,0) (0,1) (0,2) -- (1,0) (1,1) (1,2) -- (2,0) (2,1) (2,2)
  * since we are using integers, the division is a FLOOR division. we have a simple arithmetic solution
  * if we are in row 4, col 2 for example
  * 4/3 = 1 -- 2/3 = 0 .... so we are in block (1,0) which is correct BLOCK
  * by multiplying the answer by 3, and also adding a 2 later on, we can find the corners of the block
  * 1*3 && 1*3+2 = 3 & 5 - so the row boundaries will be from row 3 and row 5
  * 0*3 && 0*3+2 = 0 & 2 - so the column boundaries will be from col 0 and col 2
  
Create a boolean for checking Row and one for checking Column 

With these 3 booleans, we can make another boolean called "isAllowed" to combine them, since all 3 need to match to false.

Finally, set the location using a while loop with recursion:
    * set counter at 1 (the value from 1-9 that we add, which we will increase or decrease)
    * location will be under 81 because there are 81 squares on a 9x9 sudoku grid
    * to find the location, we do location / 9 and for column location % 9 to get a remainder
    * apply the booleans, set the value, increase location, reiterate through the loop
    * if it can no longer add a value, this is where the } else { after ret == true comes in
    * we set the value at 0 again, and backtrack with l--
    * increase the count of the last number if able to, or set to 0 and backtrack again
    
Creeate a solveGrid function, set the location to 0 to start in top left corner. Print the grid within solveGrid

Call the original grid in main method and then call the solveGrid method to compare the two in output.

Solved!
