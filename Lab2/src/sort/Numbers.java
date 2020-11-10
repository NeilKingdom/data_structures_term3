package sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Neil Kingdom
 * @since 2020-09-22
 * @version 1.0
 * This class contains all the methods required for generating an array of a given length with random integer values.
 * The user may search for the number of occurrences of a given number, n, using binary search.
 * */
public class Numbers {

    private int size;
    private Integer[] randomArr;

    public Numbers() {
    }

    /**
     * @since 2020-09-22
     * @param size The size used to initialize the array
     * This parameterized constructor initializes a new array of type Integer
     * */
    public Numbers(int size) {
        this.size = size;
        randomArr = new Integer[size];
    }

    /**
     * @since 2020-09-22
     * This method fills the array with random integers using the Random class.
     * */
    public void generateNumbers() {
        for (int i = 0; i < size; i++) {
            //51 in order to make boundaries 1 - 50
            randomArr[i] = ThreadLocalRandom.current().nextInt(1,51);
        }
    }

    /**
     * @since 2020-09-22
     * @param key The value which the user requests to be searched.
     * @return Returns the number of occurrences of the value of key within the array.
     * This function searches for the number of occurrences of key and returns that number using binary search.
     * */
    public int findCount(int key) {

        /*******Linear Search********/
        int nOccurs = 0;
        for(int i = 0; i < size; i++) {
            if(randomArr[i] == key)
                nOccurs++;
        }
        return nOccurs;

        /*******Binary Search********/
        /*int low = 0, high = randomArr.length-1;
        Integer[] copy = new Integer[size];

        System.arraycopy(randomArr, 0, copy, 0, randomArr.length);
        Arrays.sort(copy);

        do {
            int mid = (low+high)/2;
            if(copy[mid] == key) {
                low = high = mid;
                while(low > 0 && copy[low-1] == key)
                    --low;
                while(high < copy.length-1 && copy[high+1] == key)
                    ++high;
                return(high-low+1); //Add one because the last index counts as well
            }
            else if(key < copy[mid]) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        } while(low <= high);
        return 0;*/
    }

    /**
     * @since 2020-09-22
     * @return Returns a string of the concatenated values in the array.
     * This method prints out the values in the randomly generated array.
     * */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
            s += randomArr[i] + " ";
        return s;
    }

    /**
     * @since 2020-09-22
     * @return Returns exit status (-1 if false, 0 if true).
     * This method simply returns 0 if the array has been initialized, or -1 if not.
     * */
    public int isArrayCreated() {
        if(randomArr == null)
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
        if(size == 0 || randomArr[0] == null)
            return -1;
        else
            return 0;
    }
}
