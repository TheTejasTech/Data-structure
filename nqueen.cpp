#include <iostream>
#include <vector>
#include <string>
using namespace std;

bool isSafe(vector<vector<char>> &board, int row, int col, int n) {
    // Check the column
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }

    // Check the left diagonal
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }

    // Check the right diagonal
    for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }

    return true;
}

void nQueen(vector<vector<char>> &board, int row, int n, vector<vector<string>> &ans) {
    if (row == n) {
        vector<string> solution;
        for (const auto &r : board) {
            solution.push_back(string(r.begin(), r.end()));
        }
        ans.push_back(solution);
        return;
    }

    for (int j = 0; j < n; j++) {
        if (isSafe(board, row, j, n)) {
            board[row][j] = 'Q'; // Place queen
            nQueen(board, row + 1, n, ans); // Recur to place rest of the queens
            board[row][j] = '.'; // Backtrack
        }
    }
}

vector<vector<string>> solveNQueens(int n) {
    vector<vector<char>> board(n, vector<char>(n, '.'));
    vector<vector<string>> ans;
    nQueen(board, 0, n, ans);
    return ans;
}

int main() {
    int n = 4; 
    vector<vector<string>> solutions = solveNQueens(n);

    for (const auto &solution : solutions) {
        for (const auto &row : solution) {
            cout << row << endl;
        }
        cout << endl; // Separate different solutions
    }

    return 0;
}