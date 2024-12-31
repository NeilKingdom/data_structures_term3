package sort;

import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-09-22
 * @version 1.3
 * This class is home to the main method which is in charge of the menu functionality.
 * */
public class Lab4 {

    public static void main(String[] args) {

        Numbers num = new Numbers();
        String size;
        int iSize = 0;
        boolean loop = true;
        boolean sorted = false;

        while(loop) {

            Scanner scan = new Scanner(System.in);
            String uInput;
            int toInt = 0;

            do {
                System.out.println("\n1. Create an array with new size");
                System.out.println("2. Generate random numbers and store it in the array");
                System.out.println("3. Sort numbers");
                System.out.println("4. Search a number and display it's number of occurrences");
                System.out.println("5. Display array");
                System.out.println("6. Quit");
                System.out.print("Enter your option: ");

                try {
                    uInput = scan.nextLine();
                    toInt = Integer.parseInt(uInput);

                    if (toInt < 1 || toInt > 6)
                        System.out.println("Invalid Input");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
            while (toInt < 1 || toInt > 6);

            switch (toInt) {
                case 1:
                    System.out.print("Enter required size: ");
                    try {
                        size = scan.nextLine();
                        iSize = Integer.parseInt(size);
                        num = new Numbers(iSize);
                    } catch (NumberFormatException | NegativeArraySizeException e) {
                        System.out.println("Invalid input");
                    }
                    break;
                case 2:
                    if (num.isArrayCreated() == -1)
                        System.err.println("Array is not created... please create the array first");
                    else {
                        num.generateNumbers();
                        sorted = false;
                    }
                    break;
                case 3:
                    if (num.isArrayCreated() == -1) {
                        System.err.println("Array is not created... please create the array first");
                        break;
                    } else if (num.isEmpty() == -1) {
                        System.err.println("Array is empty");
                        break;
                    } else {
                        System.out.println("1. Bubble Sort");
                        System.out.println("2. Insertion Sort");
                        System.out.println("3. Selection Sort");
                        System.out.println("4. Merge Sort");
                        System.out.println("5. Quick Sort");
                        System.out.print("Enter your choice: ");
                        do {
                            try {
                                uInput = scan.nextLine();
                                toInt = Integer.parseInt(uInput);
                            } catch (NumberFormatException e) {
                                toInt = 0;
                            }
                            if (toInt < 1 || toInt > 5)
                                System.out.println("Invalid option. Please try again...");
                        } while(toInt < 1 || toInt > 5);

                        long tStart;
                        long tEnd;

                        switch(toInt) {
                            case 1:
                                num.reinstateNumbers();
                                System.out.println(num);
                                tStart = System.nanoTime();
                                num.bubbleSort();
                                tEnd = System.nanoTime();
                                System.out.print("\n");
                                System.out.println("===================================");
                                System.out.println("Time taken for bubble sort: " + (tEnd - tStart));
                                System.out.println("===================================\n");
                                System.out.println("Sorted numbers - Bubble sort");
                                System.out.println(num);
                                break;
                            case 2:
                                num.reinstateNumbers();
                                System.out.println(num);
                                tStart = System.nanoTime();
                                num.insertionSort();
                                tEnd = System.nanoTime();
                                System.out.print("\n");
                                System.out.println("===================================");
                                System.out.println("Time taken for insertion sort: " + (tEnd - tStart));
                                System.out.println("===================================\n");
                                System.out.println("Sorted numbers - Insertion sort");
                                System.out.println(num);
                                break;
                            case 3:
                                num.reinstateNumbers();
                                System.out.println(num);
                                tStart = System.nanoTime();
                                num.selectionSort();
                                tEnd = System.nanoTime();
                                System.out.print("\n");
                                System.out.println("===================================");
                                System.out.println("Time taken for selection sort: " + (tEnd - tStart));
                                System.out.println("===================================\n");
                                System.out.println("Sorted numbers - Selection sort");
                                System.out.println(num);
                                break;
                            case 4:
                                num.reinstateNumbers();
                                System.out.println(num);
                                tStart = System.nanoTime();
                                num.mergeSort(0, iSize-1);
                                tEnd = System.nanoTime();
                                System.out.print("\n");
                                System.out.println("===================================");
                                System.out.println("Time taken for merge sort: " + (tEnd - tStart));
                                System.out.println("===================================\n");
                                System.out.println("Sorted numbers - Merge sort");
                                System.out.println(num);
                                break;
                            case 5:
                                num.reinstateNumbers();
                                System.out.println(num);
                                tStart = System.nanoTime();
                                num.quickSort(0, iSize-1);
                                tEnd = System.nanoTime();
                                System.out.println("===================================");
                                System.out.println("Time taken for quick sort: " + (tEnd - tStart));
                                System.out.println("===================================\n");
                                System.out.println("Sorted numbers - Quick sort");
                                System.out.println(num);
                                break;
                        }

                        sorted = true;
                        break;
                    }
                case 4:
                    if (num.isArrayCreated() == -1) {
                        System.err.println("Array is not created... please create the array first");
                        break;
                    } else if (num.isEmpty() == -1) {
                        System.err.println("Array is empty");
                        break;
                    } else {
                        System.out.println("\n1. Linear search");
                        System.out.println("2. Iterative binary search");
                        System.out.println("3. recursive binary search");
                        System.out.print("Enter your choice: ");

                        do {
                            try {
                                uInput = scan.nextLine();
                                toInt = Integer.parseInt(uInput);
                            } catch (NumberFormatException e) {
                                toInt = 0;
                            }
                            if (toInt < 1 || toInt > 3)
                                System.out.println("Invalid option. Please try again...");
                        } while(toInt < 1 || toInt > 3);

                        String choice;
                        int iChoice;
                        System.out.print("Enter the number to be searched: ");

                        do {
                            try {
                                choice = scan.nextLine();
                                iChoice = Integer.parseInt(choice);
                            } catch (NumberFormatException e) {
                                iChoice = 0;
                            }
                            if (iChoice < 1 || iChoice > 50)
                                System.out.println("Array only contains number between 1 and 50. Please try again...");
                        } while(iChoice < 1 || iChoice > 50);
                        System.out.print("\n");

                        switch(toInt) {
                            case 1:
                                int count = num.findCount(iChoice);
                                System.out.print("Number " + iChoice + " occurred " + count + " time(s) in the array");
                                if(count > 0)
                                    System.out.print(", and it's first occurrence is at index " + num.findIndex(iChoice));
                                System.out.print("\n");
                                break;
                            case 2:
                                int indexI;
                                if(sorted && (indexI = num.iterativeBinarySearch(iChoice)) > 0) {
                                    System.out.println(iChoice + " is found at index " + indexI);
                                }
                                else if(sorted)
                                    System.out.println(iChoice + " not present");
                                else
                                    System.out.println("Array not sorted. Please sort the array before applying binary search");
                                break;
                            case 3:
                                int indexR;
                                if(sorted && (indexR = num.recursiveBinarySearch(iChoice, 0, iSize - 1, 0, "")) > 0) {
                                    System.out.println(iChoice + " is found at index " + indexR);
                                }
                                else if(sorted)
                                    System.out.println(iChoice + " not present");
                                else
                                    System.out.println("Array not sorted. Please sort the array before applying binary search");
                                break;
                        }
                    }
                    break;
                case 5:
                    if (num.isArrayCreated() == -1) {
                        System.err.println("Array is not created... please create the array first");
                        break;
                    } else if (num.isEmpty() == -1) {
                        System.err.println("Array is empty");
                        break;
                    } else
                        System.out.println(num);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scan.close();
                    loop = false;
                    break;
            }
        }
    }
}
