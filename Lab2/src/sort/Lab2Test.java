package sort;

import java.util.Scanner;

/**
 * @author Neil Kingdom
 * @since 2020-09-22
 * @version 1.0
 * This class is home to the main method which is in charge of the menu functionality.
 * */
public class Lab2Test {

    public static void main(String[] args) {

        Numbers num = new Numbers();
        boolean loop = true;

        while(loop) {

            Scanner scan = new Scanner(System.in);
            String uInput;
            int toInt = 0;

            do {
                System.out.println("1. Create an array with new size");
                System.out.println("2. Generate random numbers and store it in the array");
                System.out.println("3. Search a number and display it's number of occurrences");
                System.out.println("4. Display array");
                System.out.println("5. Quit");
                System.out.println("Enter your option:");

                try {
                    uInput = scan.nextLine();
                    toInt = Integer.parseInt(uInput);

                    if (toInt < 1 || toInt > 6)
                        System.out.println("Invalid Input");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
            while (toInt < 1 || toInt > 5);

            switch (toInt) {
                case 1:
                    System.out.println("Enter required size:");
                    try {
                        uInput = scan.nextLine();
                        toInt = Integer.parseInt(uInput);
                        num = new Numbers(toInt);
                    } catch (NumberFormatException | NegativeArraySizeException e) {
                        System.out.println("Invalid input");
                    }
                    break;
                case 2:
                    if (num.isArrayCreated() == -1)
                        System.err.println("Array is not created... please create the array first");
                    else
                        num.generateNumbers();
                    break;
                case 3:
                    if (num.isArrayCreated() == -1) {
                        System.err.println("Array is not created... please create the array first");
                        break;
                    } else if (num.isEmpty() == -1) {
                        System.err.println("Array is empty");
                        break;
                    } else {
                        System.out.println("Enter the number to be searched:");
                        try {
                            do {
                                uInput = scan.nextLine();
                                toInt = Integer.parseInt(uInput);
                                if (toInt < 1 || toInt > 50)
                                    System.out.println("Array only contains numbers between 1 and 50. Please try again...");
                            } while (toInt < 1 || toInt > 50);
                            System.out.println("Number " + toInt + " occurred " + num.findCount(toInt) + " time(s) in the array");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }
                    }
                    break;
                case 4:
                    if (num.isArrayCreated() == -1) {
                        System.err.println("Array is not created... please create the array first");
                        break;
                    } else if (num.isEmpty() == -1) {
                        System.err.println("Array is empty");
                        break;
                    } else
                        System.out.println(num);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scan.close();
                    loop = false;
                    break;
            }
        }
    }
}
