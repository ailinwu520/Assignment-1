import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Part2 {
    final static int SIZE = 50000;

    //sorted region funtion
    static void sortedRegions(double[] arr, ArrayList<Integer> timeStart, ArrayList<Integer> timeEnd) {
        timeStart.add(0);
        // loop through each element
        for (int i = 0; i < arr.length - 1; i++) {
            // if second element is less than the first element,
            // add elements into timeStart and timeEnd
            if (arr[i + 1] < arr[i]){
                timeEnd.add(i);
                timeStart.add(i+1);
            }
        }
        // else
        timeEnd.add(arr.length - 1);
    }

    private static double[] randomNumbers(int count) {
        double[] numbers = new double[count];
        Random random = new Random();
        for (int i = 0; i < count; i++)
            numbers[i] = 50000 + random.nextInt(500000-50000);
        return numbers;
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

    // swap function
    static void swap(double[] arr, int i, int j)
    {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // initialize array list and array
        double[] arr;
        long startTime;  // time when a sort begins
        long endTime;    // time when a sort ends
        ArrayList<Integer> timeStart = new ArrayList<Integer>();
        ArrayList<Integer> timeEnd = new ArrayList<Integer>();
        Part2 sorting = new Part2();
        arr = randomNumbers(10);
        sorting.sortedRegions(arr, timeStart, timeEnd);
        sorting.selectionSort(arr);

        for (double n : arr)
            System.out.println( "   " + n);
        System.out.println();

        arr = randomNumbers(SIZE);
        startTime = System.currentTimeMillis();
        sorting.selectionSort(arr);
        endTime = System.currentTimeMillis();
        System.out.printf("Milliseconds to sort %d numbers with quickThanMergeSort: %d",
                SIZE, endTime-startTime);
        System.out.println();


        for (int i = 0; i <timeStart.size() - 1; i++){
            // initialize elements
            int x1 = timeStart.get(i);
            int x2 = timeStart.get(i +1);
            int y1 = timeEnd.get(i);
            int y2 = timeEnd.get(i +1);

            // temp array
            double [] temp = new double[y2 + 1];
            for (int j = x1; j <= y1; j++){
                temp[j] = arr[j];
            }
            for (int k = x2; k <= y2; k++) {
                temp [k] = arr [k];
            }

            // use selection sort to sort
            sorting.selectionSort(temp);
            for (int l = x1; l <= y1; l++){
                arr[l] = temp[l];
            }
            timeStart.set(i + 1, timeStart.get(i));
            System.out.println(timeEnd + "  " + timeStart);

        }
    }
}

