package org.open.corejava.jbasics;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Program to validate string contain only alphabets.
 */

public class ValidateStringInput {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        String str;
        ValidateStringInput sv = new ValidateStringInput();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your first name: ");
        str = sc.next();
        if (!sv.validateStringUsingRE(str))
            System.out.println("\nSorry, name should be alphabetic. Try again...");

        System.out.println("\nEnter your last name: ");
        str = sc.next();
        if (!sv.validateString(str))
            System.out.println("\nSorry, name should be alphabetic. Try again...");
    }

    /* Method to validate only characters are inputed */
    private boolean validateStringUsingRE(String str) {
        Pattern p = Pattern.compile("[A-Za-z]+$");
        return p.matcher(str).matches();
    }

    /* Method to check for char is alphabet */
    private boolean isChar(char c) {
        return c > 'A' && c < 'Z' || c > 'a' && c < 'z';
    }

    /* Method to validate string using own logic */
    private boolean validateString(String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!isChar(str.charAt(i)))
                return false;
        }
        return true;
    }
}
