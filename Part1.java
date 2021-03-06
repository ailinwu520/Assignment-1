import java.util.Arrays;
import java.util.Random;
public class Part1 {
    final static int SIZE = 50000;


    // swap function
    static void swap(double[] arr, int i, int j)
    {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // selection sort function
    public void selectionSort (double [] arr) {
        // loop through each element
        for (int i = 0; i < arr.length - 1; i++) {
            // find the first index in the middle
            int firstIndex = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[firstIndex])
                    firstIndex = j;
            //swap the first index with the first element
            swap(arr, firstIndex, i);
        }
    }

    // bubble sort
    public void bubbleSort (double [] arr ){
            // loop through each element
            for (int i = 0; i < arr.length - 1; i++)
                for (int j = 0; j < arr.length - i - 1; j++)
                    if (arr[j] > arr[j+1])
                    {
                        // swap arr[j+1] and arr[j]
                        swap(arr, j, j+1);
                    }
    }

    // insertion sort
    public void insertionSort (double [] arr){
        for (int i = 1; i < arr.length; ++i) {
            double key = arr[i];
            int j = i - 1;
            // if arr[i] greater than key, then move one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // merge sort
    public void mergeSort (double [] arr, int l, int m, int r){
        // two sub-arrays
        int n1 = m - l + 1;
        int n2 = r - m;
        // temp arrays for left and right side
        double L[] = new double[n1];
        double R[] = new double[n2];
        // copy elements to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        // merge two sub-arrays, initial indexes of two sub-arrays
        int i = 0, j = 0;
        // initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // copy remaining elements to left array
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // copy remaining elements to right array
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //merge sort helper
    void mergeSortHelper(double arr[], int l, int r)
    {
        if (l < r) {
            // find middle element
            int m =l+ (r-l)/2;
            // sort the two arrays
            mergeSortHelper(arr, l, m);
            mergeSortHelper(arr, m + 1, r);
            // merge the two sorted arrays
            mergeSort(arr, l, m, r);
        }
    }

    // quick sort
    public int quickSort(double[] arr, int low, int high)
    {
        // initialize pivot
        double pivot = arr[high];
        // index of small element and the right position of pivot
        int i = (low - 1);
        for(int j = low; j <= high - 1; j++)
        {
            // if smaller then pivot
            if (arr[j] < pivot)
            {
                // increment
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // quick sort helper
    void quickSortHelper (double [] arr, int low, int high){
        if (low < high)
        {
            // partitioning index at right place
            int pi = quickSort(arr, low, high);
            // sort elements before and after partition
            quickSortHelper(arr, low, quickSort(arr, low, high) - 1);
            quickSortHelper(arr, quickSort(arr, low, high) + 1, high);
        }
    }

    //generate random double array
    private static double[] randomNumbers(int count) {
        double[] numbers = new double[count];
        Random random = new Random();
        for (int i = 0; i < count; i++)
//            numbers[i] = Math.random();
            numbers[i] = 50000 + random.nextInt(500000-50000);
        return numbers;
    }

    public static void main(String[] args) {
        Part1 sorting = new Part1 ();

        long startTime;  // time when a sort begins
        long endTime;    // time when a sort ends

        double[] numberList1;  // array of random numbers.
        double[] numberList2;  // copy of numberList1.
        double[] numberList3;  // copy of numberList1.
        double[] numberList4;  // copy of numberList1.
        double[] numberList5;  // copy of numberList1.


        numberList1 = randomNumbers(10);
        sorting.selectionSort(numberList1);
        for (double n : numberList1)
            System.out.println( "   " + n);
        System.out.println();

        numberList1 = randomNumbers(SIZE);
        numberList2 = Arrays.copyOf(numberList1, SIZE);
        numberList3 = Arrays.copyOf(numberList2, SIZE);
        numberList4 = Arrays.copyOf(numberList3, SIZE);
        numberList5 = Arrays.copyOf(numberList4, SIZE);



        startTime = System.currentTimeMillis();
        sorting.selectionSort(numberList1);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with selectionSort: %d",
                SIZE, endTime-startTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        sorting.bubbleSort(numberList2);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with bubbleSort: %d",
                SIZE, endTime-startTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        sorting.insertionSort(numberList3);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with inserstionSort: %d",
                SIZE, endTime-startTime);
        System.out.println();

        startTime = System.currentTimeMillis();
       sorting.mergeSortHelper(numberList4, 0, numberList4.length - 1);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with mergeSort: %d",
                SIZE, endTime-startTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        sorting.quickSortHelper(numberList5,0, numberList5.length - 1);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with quickSort: %d",
                SIZE, endTime-startTime);
        System.out.println();
    }
}
