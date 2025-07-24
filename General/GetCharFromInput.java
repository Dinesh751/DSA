package General;

import java.util.Scanner;

public class GetCharFromInput {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Method 1: Get single character using charAt(0)
        System.out.print("Enter a character: ");
        char ch1 = scanner.next().charAt(0);
        System.out.println("Character entered: " + ch1);
        
        // Method 2: Get character from string input
        System.out.print("Enter a string: ");
        String str = scanner.next();
        char ch2 = str.charAt(0); // First character
        System.out.println("First character of string: " + ch2);
        
        // Method 3: Get multiple characters as array
        System.out.print("Enter number of characters: ");
        int n = scanner.nextInt();
        char[] charArray = new char[n];
        
        System.out.println("Enter " + n + " characters (space separated): ");
        for (int i = 0; i < n; i++) {
            charArray[i] = scanner.next().charAt(0);
        }
        
        System.out.print("Characters entered: ");
        for (char c : charArray) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        // Method 4: Convert string to character array
        System.out.print("Enter a word: ");
        String word = scanner.next();
        char[] wordChars = word.toCharArray();
        
        System.out.print("Characters in word: ");
        for (char c : wordChars) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        scanner.close();
    }
}
