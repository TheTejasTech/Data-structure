#include <bits/stdc++.h>
using namespace std;

#define INF INT_MAX
#define V 4

void printMatrix(vector<vector<int>> matrix) {
    for (auto& row : matrix) {
        for (int val : row) {
            if (val == INF)
                cout << "INF\t";
            else
                cout << val << "\t";
        }
        cout << endl;
    }
}

// Function to reduce rows and columns and return the reduction cost
int reduceMatrix(vector<vector<int>>& matrix) {
    int cost = 0;

    // Row Reduction
    for (int i = 0; i < V; i++) {
        int rowMin = INF;
        for (int j = 0; j < V; j++)
            rowMin = min(rowMin, matrix[i][j]);
        if (rowMin != INF && rowMin > 0) {
            cost += rowMin;
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] != INF)
                    matrix[i][j] -= rowMin;
            }
        }
    }

    // Column Reduction
    for (int j = 0; j < V; j++) {
        int colMin = INF;
        for (int i = 0; i < V; i++)
            colMin = min(colMin, matrix[i][j]);
        if (colMin != INF && colMin > 0) {
            cost += colMin;
            for (int i = 0; i < V; i++) {
                if (matrix[i][j] != INF)
                    matrix[i][j] -= colMin;
            }
        }
    }

    return cost;
}

int main() {
    vector<vector<int>> graph = {
        {INF, 10, 15, 20},
        {10, INF, 35, 25},
        {15, 35, INF, 30},
        {20, 25, 30, INF}
    };

    cout << "🔹 Original Cost Matrix:\n";
    printMatrix(graph);

    vector<vector<int>> reduced = graph; // Clone matrix
    int cost = reduceMatrix(reduced);

    cout << "\n Reduced Cost Matrix (after row & column reduction):\n";
    printMatrix(reduced);

    cout << "\n Reduction Cost (lower bound for this node): " << cost << endl;

    return 0;
}
