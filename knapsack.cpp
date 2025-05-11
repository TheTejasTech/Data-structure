#include <bits/stdc++.h>
using namespace std;

struct Item {
    int weight;
    int value;
    double ratio;
};

struct Node {
    int level;
    int profit;
    int weight;
    double bound;
};

double bound(Node u, int n, int W, Item arr[]) {
    if (u.weight >= W)
        return 0;
    double profit_bound = u.profit;
    int j = u.level + 1;
    int total_weight = u.weight;
    while (j < n && total_weight + arr[j].weight <= W) {
        total_weight += arr[j].weight;
        profit_bound += arr[j].value;
        j++;
    }
    if (j < n)
        profit_bound += (W - total_weight) * arr[j].ratio;
    return profit_bound;
}

struct CompareNode {
    bool operator()(const Node &a, const Node &b) {
        return a.bound < b.bound;
    }
};

int knapsack(int W, Item arr[], int n) {
    priority_queue<Node, vector<Node>, CompareNode> Q;
    Node u = {-1, 0, 0, 0};
    Q.push(u);
    int max_profit = 0;
    vector<bool> selected(n, false);  // To keep track of selected items
    vector<int> best_items;

    while (!Q.empty()) {
        u = Q.top();
        Q.pop();
        if (u.level == n - 1)
            continue;
        Node v;
        v.level = u.level + 1;
        v.weight = u.weight + arr[v.level].weight;
        v.profit = u.profit + arr[v.level].value;
        if (v.weight <= W && v.profit > max_profit) {
            max_profit = v.profit;
            best_items.push_back(v.level);  // Add the item to the selected list
        }
        v.bound = bound(v, n, W, arr);
        if (v.bound > max_profit)
            Q.push(v);
        v.weight = u.weight;
        v.profit = u.profit;
        v.bound = bound(v, n, W, arr);
        if (v.bound > max_profit)
            Q.push(v);
    }

    return max_profit;
}

int main() {
    int n = 4;
    int W = 50;
    Item arr[] = {{10, 60, 0}, {20, 100, 0}, {30, 120, 0}, {40, 40, 0}};
    for (int i = 0; i < n; i++) {
        arr[i].ratio = (double)arr[i].value / arr[i].weight;
    }
    sort(arr, arr + n, [](Item a, Item b) {
        return a.ratio > b.ratio;
    });

    int max_profit = knapsack(W, arr, n);

    cout << "Maximum Profit: " << max_profit << endl;
    cout << "Items selected for the knapsack: " << endl;
    for (int i = 0; i < n; i++) {
        if (arr[i].value > 0) {
            cout << "Item " << i + 1 << " (Weight: " << arr[i].weight << ", Value: " << arr[i].value << ")" << endl;
        }
    }

    return 0;
}
