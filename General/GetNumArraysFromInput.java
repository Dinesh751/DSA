package General;

import java.util.Scanner;

public class GetNumArraysFromInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the lengths of the two arrays
        int length1 = scanner.nextInt();
        int length2 = scanner.nextInt();
        
        // Create first array and read elements (for characters)
        char[] array1 = new char[length1];
        for (int i = 0; i < length1; i++) {
            array1[i] = scanner.next().charAt(0); // Read first character of each input
        }
        
        // Create second array and read elements (for characters)
        char[] array2 = new char[length2];
        for (int i = 0; i < length2; i++) {
            array2[i] = scanner.next().charAt(0); // Read first character of each input
        }
        
        // Print the arrays to verify
        System.out.print("Array 1: ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }
        System.out.println();
        
        System.out.print("Array 2: ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + " ");
        }
        System.out.println();
        
        scanner.close();
    }
}
