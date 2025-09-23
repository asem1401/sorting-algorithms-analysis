import java.util.Random;

public class quickSorting {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    private static final Random RND = new Random();

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        while (l < r) {
            if (r - l + 1 <= INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, l, r);
                return;
            }
            int p = partition(arr, l, r);
            if (p - l < r - p) {         // рекурсией в меньшую часть
                quickSort(arr, l, p - 1);
                l = p + 1;               // большая часть — циклом (bounded stack)
            } else {
                quickSort(arr, p + 1, r);
                r = p - 1;
            }
        }
    }

    private static int partition(int[] a, int l, int r) {
        int m = l + ((r - l) >>> 1);
        if (a[m] < a[l]) swap(a, l, m);
        if (a[r] < a[l]) swap(a, l, r);
        if (a[r] < a[m]) swap(a, m, r);
        int pivot = a[m];
        swap(a, m, l);
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && a[i] < pivot) i++;
            while (j >= l + 1 && a[j] > pivot) j--;
            if (i >= j) break;
            swap(a, i++, j--);
        }
        swap(a, l, j);
        return j;
    }

    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i], j = i - 1;
            while (j >= l && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }

    private static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RND.nextInt(i + 1);
            swap(a, i, j);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}