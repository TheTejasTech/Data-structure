#include <bits/stdc++.h>
using namespace std;

const int N = 1e5 + 5;
int val[105], wt[105];

int main() {
    int n = 4;  // Number of items
    int W = 5;  // Knapsack capacity

    // Predefined weights and values of the items
    int weights[] = {2, 3, 4, 5};
    int values[] = {3, 4, 5, 6};

    // Copy predefined weights and values into the arrays
    for (int i = 0; i < n; ++i) {
        wt[i] = weights[i];
        val[i] = values[i];
    }

    vector<int> dp(W + 1, 0);

    // Dynamic Programming to solve 0/1 Knapsack
    for (int i = 0; i < n; ++i) {
        for (int w = W; w >= wt[i]; --w) {
            dp[w] = max(dp[w], dp[w - wt[i]] + val[i]);
        }
    }

    // Output the maximum value that can be obtained
    cout << dp[W] << endl;
    return 0;
}
