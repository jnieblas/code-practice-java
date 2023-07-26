package src.com.jnieblas.problems;

import java.util.Arrays;

class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();

        char[][] board = new char[][]{
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        solver.solveSudoku(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // a column can only contain a single instance of 1-9
    // a row can only contain a single instance of 1-9
    // a 3x3 box can only contain a single instance of 1-9
    // a 3x3 box is defined like so:
        /*
            subvided in sects of 3
            0-2, 3-5, 6-8

            00 10 20 | 30 40 50 | 60 70 80
            01 11 21 | 31 41 51 | 61 71 81
            02 ...   |          |
            ------------------------------
            03 ...   |          |
            04 ...   |          |
            05 ...   |          |
            ------------------------------
            06 ...   |          |
            07 ...   |          |
            08 18 28 | 38 48 58 | 68 78 88
        */
    // Say, [1][1] is empty, how would I solve that?
    // We need to look at the values in [1][0] -> [1][8] (colums), [0][1] -> [8][1] (row), and [0][0] -> [2][2] (square) to see if it exists
    // Not only that, but the solution I provide for [1][1] needs to make sense. [1][1] also needs to be solvable, which is part of the problem with sudoku:
    // not all cells are immediately solvable.
    // How do we find the entrypoint into the array?

    // From reading the posts, it seems we need to do a backtrack algorithm via recursion
    // Backtracking will involve solving where we can, then moving backwards if we get stuck I think...
    public void solveSudoku(char[][] board) {
        // Start solving sudoku from the first cell
        // So we do this recursively? Seems inefficient, but makes sense...
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {
        // Base case: if row is equal to board length, entire board has been filled
        // row == 9, then we've gone through all 9 rows [0] -> [8]
        // Only gets to the end IF we can solve via for loop
        if (row == board.length) {
            return true;
        }

        // Move to next row when current row is fully filled
        // if col == 9, then we need to move to the next row [0] -> [8]
        if (col == board[0].length) {
            return solve(board, row + 1, 0);
        }

        // Skip cells that are already filled
        // Iterate on column to move to the next value in row
        if (board[row][col] != '.') {
            return solve(board, row, col + 1);
        }

        // Try different numbers in current cell
        // Plug in numbers until we get a match
        for (char num = '1'; num <= '9'; num++) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num;

                // returns true if this value resulted in solution
                if (solve(board, row, col + 1)) {
                    return true;
                }

                // otherwise, reverts this val to '.' if it's not part of the solution
                // backtrack to previous state if solution not found
                board[row][col] = '.';
            }
        }

        // No valid solution found
        return false;
    }

    private boolean isValidPlacement(char[][] board, int row, int col, char num) {
        // Check if num is already in the same row, column or 3x3 subgrid
        for (int i = 0; i < board.length; i++) {
            // Check row, return false if num is found
            // use [col] to consistently iterate across all nine vals on a row
            if (board[i][col] == num) {
                return false;
            }

            // Check column, return false if num is found
            // use [row] to consistently iterate across all nine vals on a row
            if (board[row][i] == num) {
                return false;
            }

            // Check 3x3 subgrid
            // to produce row:
            /*
                multiply row / 3 by 3, then add curr index / 3
                ex:
                row = 5, col = 8, i = 0
                subgridRow = 3 * ((row = 5) / 3) + ((i = 0) / 3) = 3 * (5 / 3) + (0 / 3) = 3 * 1 + 0 = 3
                subgridCol = 3 * ((col = 8) / 3) + ((i = 0) % 3) = 3 * (8 / 3) + (0 % 3) = 3 * 2 + 0 = 6

                row = 5, col = 8, i = 8
                subgridRow = 3 * ((row = 5) / 3) + ((i = 8) / 3) = 3 * (5 / 3) + (8 / 3) = (3 * 1) + 2 = 5
                subgridCol = 3 * ((col = 8) / 3) + ((i = 8) % 3) = 3 * (8 / 3) + (8 % 3) = (3 * 2) + 2 = 8


                if we're checking the 6th row and the 9th column, we will check from the
                4th row to the 6th row [3 -> 5]
                7th column to the 9th column [6 -> 8]

                How does this work?
                Dividing row index by three will always give us the row of squares which the individual row belong to.
                That can be the top three boxes (0), the middle (1) or the bottom (2).
                Multiplying that number by 3 will give use the beginning index of the rows of square(0 * 3 = 0, 1 * 3 = 3, 2 * 3 = 6)
                Using that first product, we can use i / 3 to iterate through the three row indices every 3 i's.
                So while we wait for i to increase by 3 so that we can move to the next row index, we will simultaneously
                leverage modulus to iterate through the column indices before we move to the next row.
                That's why the column uses modulus: as i / 3 stays the same for 3 indices, i % 3 iterates from 0 -> 2,
                allowing us to traverse a square of double array values.

                KEEP THIS IN MIND - this is a super helpful way to traverse a 2D array in a square shape automatically
             */
            int subgridRow = 3 * (row / 3) + i / 3; // Calculate row index of subgrid
            int subgridCol = 3 * (col / 3) + i % 3; // Calculate column index of subgrid

            if (board[subgridRow][subgridCol] == num) {
                return false;
            }
        }

        // Placement is valid
        return true;
    }
}