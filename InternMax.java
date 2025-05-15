import java.util.ArrayList;

public class InternshipHeap {

    static class Student {
        int rank;
        String name;

        Student(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }
    }

    static class MaxHeap {
        private ArrayList<Student> heap = new ArrayList<>();

        private void heapifyUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap.get(index).rank <= heap.get(parent).rank)
                    break;
                swap(index, parent);
                index = parent;
            }
        }

        private void heapifyDown(int index) {
            int size = heap.size();
            while (index < size) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int largest = index;

                if (left < size && heap.get(left).rank > heap.get(largest).rank)
                    largest = left;
                if (right < size && heap.get(right).rank > heap.get(largest).rank)
                    largest = right;

                if (largest == index) break;
                swap(index, largest);
                index = largest;
            }
        }

        private void swap(int i, int j) {
            Student temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public void insert(int rank, String name) {
            heap.add(new Student(rank, name));
            heapifyUp(heap.size() - 1);
        }

        public Student getTopper() {
            if (heap.isEmpty()) {
                System.out.println("No students available!");
                return null;
            }
            return heap.get(0); // Don't remove, just return the topper
        }

        public Student extractTopper() {
            if (heap.isEmpty()) {
                System.out.println("No students available!");
                return null;
            }
            Student topper = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapifyDown(0);
            return topper;
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        // Add student records
        maxHeap.insert(95, "Aditya");
        maxHeap.insert(90, "Vishal");
        maxHeap.insert(98, "Shivraj"); // Topper
        maxHeap.insert(85, "Nikhil");
        maxHeap.insert(92, "Harshad");

        // Select topper for internship
        Student topper = maxHeap.getTopper();
        if (topper != null) {
            System.out.println("Internship is awarded to:");
            System.out.println("Topper: " + topper.name + ", Rank: " + topper.rank);
        }
    }
}
