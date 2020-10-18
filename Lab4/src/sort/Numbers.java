package sort;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Neil Kingdom
 * @since 2020-09-22
 * @version 1.3
 * This class contains all the methods required for generating an array of a given length with random integer values.
 * The user may search for the number of occurrences of a given number, n, using binary search.
 * */
public class Numbers {

    private int size;
    private ArrayList<Integer> numbers;
    private ArrayList<Integer> numbersCopy;
    private ArrayList<Integer> temp;

    public Numbers() {
    }

    /**
     * @since 2020-09-22
     * @param size The size used to initialize the array
     * This parameterized constructor initializes a new array of type Integer
     * */
    public Numbers(int size) {
        this.size = size;
        numbers = new ArrayList<>(size);
        numbersCopy = new ArrayList<>(size);
        temp = new ArrayList<>(size);
    }

    /**
     * @since 2020-09-22
     * This method fills the array with random integers using the Random class.
     * */
    public void generateNumbers() {
        for (int i = 0; i < size; i++) {
            //51 in order to make boundaries 1 - 50
            int random = ThreadLocalRandom.current().nextInt(1,51);
            numbers.add(random);
            numbersCopy.add(random);
        }
    }

    /**
     * @since 2020-09-22
     * @return Returns the index at which key was found, else -1
     * This method uses linear search to find the key
     * */
    public int findIndex(int key) {
        for(int i = 0; i < size; i++)
            if(numbers.get(i) == key)
                return i;
        return -1;
    }

    /**
     * @since 2020-10-06
     * Copy over the numbers from copy list to numbers list
     * */
    public void reinstateNumbers() {
        for(int i = 0; i < size; i++)
            numbers.set(i, numbersCopy.get(i));
    }

    /**
     * @since 2020-10-06
     * Sort the array using bubble sort
     * */
    public void bubbleSort() {

        for(int i = 0; i < size - 1; i++) {
            for(int j = 0; j < size-i-1; j++) {
                //Check 2 side by side indexes and swap accordingly
                if(numbers.get(j) > numbers.get(j+1)) {
                    int temp = numbers.get(j+1);
                    numbers.set(j+1, numbers.get(j));
                    numbers.set(j, temp);
                }
            }
        }
    }

    /**
     * @since 2020-10-06
     * Sort the array using selection sort
     * */
    public void selectionSort() {

        for(int i = 0; i < size-1; i++) {
            int smallest = i;
            //Check entire row until smallest number is found. Swap smallest with next index (i)
            for(int j = i+1; j < size; j++)
                if(numbers.get(j) < numbers.get(smallest))
                    smallest = j;
            int temp = numbers.get(smallest);
            numbers.set(smallest, numbers.get(i));
            numbers.set(i, temp);
        }
    }

    /**
     * @since 2020-10-06
     * Sort the array using insertion sort
     * */
    public void insertionSort() {

        for(int i = 1; i < size; i++) {
            for(int j = i; j > 0; j--) {
                //If index j is smallest, put it at the back
                if(numbers.get(j) < numbers.get(j-1)) {
                    int temp = numbers.get(j-1);
                    numbers.set(j - 1, numbers.get(j));
                    numbers.set(j, temp);
                }
            }
        }
    }

    /**
     * @since 2020-10-06
     * Merges the two sides of the array after they have been sorted
     * */
    void merge(int low, int mid, int high) {

        //Create a temporary array to compare with normal array
        for (int i = low; i <= high; i++)
            temp.add(i, numbers.get(i));

        int i = low; //left
        int j = mid + 1; //middle
        int k = low; //index

        while (i <= mid && j <= high) {
            //Choosing between temp's left sorted half and right sorted half, to see which has lower number
            //Place whichever is lowest into the next index of numbers array
            if (temp.get(i) <= temp.get(j)) {
                numbers.set(k, temp.get(i));
                i++;
            } else {
                numbers.set(k, temp.get(j));
                j++;
            }
            k++;
        }
        //If i <= mid, it means that right half is finished, and the remaining numbers in left half
        //occupy the remainder of numbers since it is already sorted
        while (i <= mid) {
            numbers.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    /**
     * @since 2020-10-06
     * Sort the array using merge sort
     * */
    void mergeSort(int low, int high) {

        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(low, mid); //Sort left side of array
            mergeSort(mid + 1, high); //Sort right side of array
            merge(low, mid, high); //Combine the two
        }
    }

    /**
     * Sort the array using quick sort
     * @since 2020-10-06
     * Sort the array using quick sort
     * */
    public void quickSort(int low, int high) {

        if(low >= high) return;
        int pivot = low, left = low+1, right = high;
        //partition until left and right are in the middle (on both sides of pivot)
        while(left < right) {
            while(numbers.get(left) < numbers.get(pivot) && left < right)
                left++; //Find first number bigger than pivot going left to right
            while(numbers.get(right) > numbers.get(pivot) && left < right)
                right--; //Find first number smaller than pivot going right to left

            //Swap left and right
            int temp = numbers.get(left);
            numbers.set(left, numbers.get(right));
            numbers.set(right, temp);

            //Shrink partition area by a factor of 1 on each side
            if(left < right) {
                left++;
                right--;
            }
        }
        //Recursively call the function, subdividing the left array, then right array, to sort each side
        if(numbers.get(left) > numbers.get(pivot)) {

            int temp = numbers.get(pivot);
            numbers.set(pivot, numbers.get(left-1));
            numbers.set(left-1, temp);

            quickSort(low, left-2);
            quickSort(left, high);
        }
        else {

            int temp = numbers.get(pivot);
            numbers.set(pivot, numbers.get(left));
            numbers.set(left, temp);

            quickSort(low, left-1);
            quickSort(left+1, high);
        }
    }

    /**
     * @since 2020-09-22
     * @param key The value which the user requests to be searched.
     * @return Returns the number of occurrences of the value of key within the array.
     * This function searches for the number of occurrences of key and returns that number using linear search.
     * */
    public int findCount(int key) {

        int nOccurs = 0;
        for(int i = 0; i < size; i++) {
            if(numbers.get(i) == key)
                nOccurs++;
        }
        return nOccurs;
    }

    /**
     * @since 2020-09-29
     * @param key The value which the user requests to be searched.
     * @return Returns the number of occurrences of the value of key within the array.
     * This function searches for the number of occurrences of key and returns that number using iterative binary search.
     * */
    public int iterativeBinarySearch(int key) {

        int iterations = 0, low = 0, high = numbers.size()-1;
        String s = "";

        do {
            int mid = (low+high)/2;

            for(int i = low; i <= high; i++)
                s += String.format("%2d ", numbers.get(i));
            s += "\n";
            iterations++;

            if(numbers.get(mid) == key) {
                low = mid;
                while(low > 0 && numbers.get(low-1) == key)
                    --low;
                System.out.print("Printing halves of each iteration\n" + s);
                System.out.println("Number of iterations: " + iterations + "\n");
                return(low);
            }
            else if(key < numbers.get(mid))
                high = mid-1;
            else
                low = mid+1;
        } while(low <= high);

        System.out.print("Printing halves of each iteration\n" + s);
        System.out.println("Number of iterations: " + iterations + "\n");
        return -1;
    }

    /**
     * @since 2020-09-29
     * @param key The value which the user requests to be searched.
     * @param low The starting search point.
     * @param high The ending search point.
     * @param iterations The number of passes it took to find the key.
     * @param s Formatted String which prints the halves of each pass.
     * @return Returns the number of occurrences of the value of key within the array.
     * This function searches for the number of occurrences of key and returns that number using recursive binary search.
     * */
    public int recursiveBinarySearch(int key, int low, int high, int iterations, String s) {

        if(low > high) {
            System.out.print("Printing halves of each iteration\n" + s);
            System.out.println("Number of iterations: " + iterations + "\n");
            return -1;
        }
        int mid = (low+high)/2;

        for(int i = low; i <= high; i++)
            s += String.format("%2d ", numbers.get(i));
        s += "\n";
        iterations++;

        //Base condition
        if(numbers.get(mid) == key) {
            low = mid;
            while(low > 0 && numbers.get(low-1) == key)
                --low;
            System.out.print("Printing halves of each iteration\n" + s);
            System.out.println("Number of iterations: " + iterations + "\n");
            return(low);
        }
        else if(key < numbers.get(mid))
                return recursiveBinarySearch(key, low, mid-1, iterations, s);
        else
            return recursiveBinarySearch(key, mid + 1, high, iterations, s);
    }

    /**
     * @since 2020-09-22
     * @return Returns a string of the concatenated values and indexes in the array.
     * This method prints out the values in the randomly generated array as well as their respective indexes.
     * */
    @Override
    public String toString() {
        String s = "";
        s += String.format("%9s", "Index");
        for(int i = 0;  i < size; i++)
            s += String.format("%4d |", i);
        s += "\n";
        s += String.format("%9s", "Element");
        for(int i = 0; i < size; i++)
            s += String.format("%4d |", numbers.get(i));
        return s;
    }

    /**
     * @since 2020-09-22
     * @return Returns exit status (-1 if false, 0 if true).
     * This method simply returns 0 if the array has been initialized, or -1 if not.
     * */
    public int isArrayCreated() {
        if(numbers == null)
            return -1;
        else
            return 0;
    }

    /**
     * @since 2020-09-22
     * @return Returns exit status (-1 if false, 0 if true).
     * This method returns 0 if the array contains data, otherwise it returns -1.
     * */
    public int isEmpty() {
        if(size == 0 || numbers.isEmpty())
            return -1;
        else
            return 0;
    }
}
