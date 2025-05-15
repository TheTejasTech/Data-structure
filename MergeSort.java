//import java.util.Scanner;
public class MergeSort {

    public static void main(String[] args) {
        //Scanner sc= new Scanner();
        int[] arr = {2,3,6,7,34,12,10,100,65,8,4,5,64,51,554,11,111}; // Example of an unsorted array
        divide(arr, 0, arr.length - 1);
 
        // Print sorted array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
 
    // Divide the array into subarrays and sort them
    public static void divide(int[] arr, int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = (si + ei) / 2; // Calculate midpoint
        divide(arr, si, mid); // Recursively divide the left half
        divide(arr, mid + 1, ei); // Recursively divide the right half
        merge(arr, si, mid, ei); // Merge the sorted halves
    } 

    // Merge two sorted halves of the array
    public static void merge(int[] arr, int si, int mid, int ei) {
        int[] merged = new int[ei - si + 1];
        int indx1 = si; // Starting index of the left half
        int indx2 = mid + 1; // Starting index of the right half
        int x = 0; // Index for the merged array
 
        // Merge elements from both halves
        while (indx1 <= mid && indx2 <= ei) {
            if (arr[indx1] <= arr[indx2]) {
                merged[x++] = arr[indx1++];
            } else {
                merged[x++] = arr[indx2++];
            }
        }

        // Copy remaining elements from the left half, if any
        while (indx1 <= mid) {
            merged[x++] = arr[indx1++];
        }

        // Copy remaining elements from the right half, if any
        while (indx2 <= ei) {
            merged[x++] = arr[indx2++];
        }

        // Copy the merged elements back to the original array
        for (int i = 0, j = si; i < merged.length; i++, j++) {
            arr[j] = merged[i];
        }
    }
}
