public class mergeSorting {
    // Cutoff for switching to insertion sort on small subarrays
    private static final int CUTOFF = 24;

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int[] buf = new int[arr.length]; // reusable buffer
        mergeSort(arr, 0, arr.length - 1, buf);
    }

    private static void mergeSort(int[] a, int l, int r, int[] buf) {
        // If subarray is small, use insertion sort instead of recursion
        if (r - l + 1 <= CUTOFF) {
            insertionSort(a, l, r);
            return;
        }

        int m = l + ((r - l) >>> 1);
        mergeSort(a, l, m, buf);
        mergeSort(a, m + 1, r, buf);

        // Skip merge if already sorted
        if (a[m] <= a[m + 1]) return;

        merge(a, l, m, r, buf);
    }

    private static void merge(int[] a, int l, int m, int r, int[] buf) {
        // Copy into buffer
        System.arraycopy(a, l, buf, l, r - l + 1);

        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            a[k++] = (buf[j] < buf[i]) ? buf[j++] : buf[i++];
        }
        while (i <= m) a[k++] = buf[i++];
        while (j <= r) a[k++] = buf[j++];
    }

    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i], j = i - 1;
            while (j >= l && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}