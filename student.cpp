#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class MaxHeap {
private:
    vector<pair<int, string>> heap; // Pair of <rank, student_name>

    void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index].first <= heap[parent].first)
                break;
            swap(heap[index], heap[parent]);
            index = parent;
        }
    }

    void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap[left].first > heap[largest].first)
                largest = left;
            if (right < size && heap[right].first > heap[largest].first)
                largest = right;
            if (largest == index)
                break;
            swap(heap[index], heap[largest]);
            index = largest;
        }
    }

public:
    void insert(int rank, string name) {
        heap.push_back({rank, name});
        heapifyUp(heap.size() - 1);
    }

    pair<int, string> extractMax() {
        if (heap.empty()) {
            cout << "Heap is empty!" << endl;
            return {-1, ""};
        }
        pair<int, string> maxStudent = heap[0];
        heap[0] = heap.back();
        heap.pop_back();
        heapifyDown(0);
        return maxStudent;
    }

    bool isEmpty() {
        return heap.empty();
    }
};

int main() {
    vector<pair<int, string>> students = {
        {95, "Alex"},
        {90, "Ander"},
        {98, "Polito"},
        {85, "Lucrecia"},
        {92, "Sameul"}
    };

    // Sort students in ascending order of grades
    sort(students.begin(), students.end());
   
    cout << "Students sorted in ascending order of grades:\n";
    for (const auto &student : students) {
        cout << "Grade: " << student.first << ", Student: " << student.second << endl;
    }
    return 0;
}
