#include <bits/stdc++.h>
using namespace std;

const int N = 1e5 + 5;
int val[105], wt[105];

int main() {
    int n, W;
    cin >> n;
    for (int i = 0; i < n; ++i) cin >> wt[i];
    for (int i = 0; i < n; ++i) cin >> val[i];
    cin >> W;

    vector<int> dp(W + 1, 0);

    for (int i = 0; i < n; ++i) {
        for (int w = W; w >= wt[i]; --w) {
            dp[w] = max(dp[w], dp[w - wt[i]] + val[i]);
        }
    }

    cout << dp[W] << endl;
    return 0;
}
