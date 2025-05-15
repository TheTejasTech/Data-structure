import java.util.*;

class Internmaxheap {

    // Function to heapify a subtree rooted at index i
    static void heapify(int[] arr, int n, int i) {
        int largest = i;       // Initialize largest as root
        int left = 2 * i + 1;  // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    // Main function to do heap sort
    static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Max element is now at index 0
        int maxElement = arr[0];

        // 🎉 Internship Offer Announcement
        System.out.println("🏆 Max element (internship offer goes to): " + maxElement);
        System.out.println("\n📄 OFFER LETTER");
        System.out.println("----------------------------");
        System.out.println("Dear Candidate " + maxElement + ",");
        System.out.println("We are thrilled to offer you an internship at HeapTech Solutions!");
        System.out.println("Your outstanding performance at the top of the heap was noticed.");
        System.out.println("Welcome aboard! 🚀");
        System.out.println("----------------------------\n");

        // Perform heap sort
        for (int i = n - 1; i >= 0; i--) {
            // Move root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify reduced heap
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {95, 98, 94, 76, 83, 85};

        System.out.println("Original array: " + Arrays.toString(arr));

        heapSort(arr);

        System.out.println("Sorted array (Heap Sort): " + Arrays.toString(arr));
    }
}
