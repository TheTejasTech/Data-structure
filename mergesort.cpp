#include <iostream>
#include <vector>
using namespace std;

void merge(vector<int> &arr, int st, int mid, int e) {
    vector<int> temp;
    int i = st;
    int j = mid + 1;
    while (i <= mid && j <= e) {
        if (arr[i] < arr[j]) {
            temp.push_back(arr[i]);
            i++;
        } else {
            temp.push_back(arr[j]);
            j++;
        }
    }
    while (i <= mid) {
        temp.push_back(arr[i]);
        i++;
    }
    while (j <= e) {
        temp.push_back(arr[j]);
        j++;
    }
    for (int i = 0; i < temp.size(); i++) {
        arr[i + st] = temp[i];
    }
}

void mergeso(vector<int> &arr, int start, int end) {
    if (start < end) {
        int mid = start + (end - start) / 2;
        mergeso(arr, start, mid);
        mergeso(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }
}

void print(const vector<int> &arr) {
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main() {
    vector<int> arr = {9, 5, 2, 6, 7, 8};
    mergeso(arr, 0, arr.size() - 1);
    cout<<"Sorted array of the given data list is: ";
    print(arr);
    cout<<"The minimum element is: "<<arr[0]<<endl;   
    cout<<"The maximum element is: "<<arr[arr.size()-1];

    return 0;
}
