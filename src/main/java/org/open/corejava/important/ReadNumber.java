package org.open.corejava.important;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class ReadNumber {
    public static final String[] UNIT = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static final String[] TEN = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static final String[] OTHER = {"", "Hundred and ", "Thousand, ", "Lac, ", "Crore, ", "Billion, ", "Trillion, "};

    public static void main(String[] args) {
        long num;
        int ch = 0;
        Vector<Integer> digits;
        Vector<String> readedNumber = new Vector<>();
        ReadNumber rn = new ReadNumber();

        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Number: ");
            try {
                num = Long.parseLong(br.readLine());
                digits = rn.separateDigits(num);
                for (int i = 0; i < digits.size(); i++)
                    readedNumber.add(rn.read2DigitNumber(digits.elementAt(i)));

                for (int i = readedNumber.size() - 1; i >= 0; i--)
                    if (!readedNumber.elementAt(i).equals("Zero"))
                        System.out.print(readedNumber.elementAt(i) + " " + ReadNumber.OTHER[i]);

                readedNumber.clear();
                System.out.println("\n\n\nWant to try again (Y/N)? ");
                ch = br.read();
            } catch (NumberFormatException e) {
                System.out.println("Please input valid number...");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Sorry, number is out of range...");
            } catch (IOException e) {
                System.out.println("Sorry, error occured while reading...");
            } catch (Exception e) {
                System.out.println("Sorry, number out of range...");
            }
        } while (ch == 'Y' || ch == 'y');
        System.out.println("\n\n\nThanks, for using me!!!");
    }

    private String read2DigitNumber(Integer num) {
        if (num < 20)
            return ReadNumber.UNIT[num];
        if (num % 10 == 0)
            return ReadNumber.TEN[num / 10 - 2];
        return ReadNumber.TEN[num / 10 - 2] + " " + ReadNumber.UNIT[num % 10];
    }

    private Vector<Integer> separateDigits(long num) {
        Vector<Integer> digits = new Vector<>();
        if (num == 0) {
            digits.add(0);
            return digits;
        }
        digits.add((int) (num % 100));
        if ((num /= 100) > 0)
            digits.add((int) (num % 10));
        for (num /= 10; num > 0; num /= 100)
            digits.add((int) (num % 100));
        return digits;
    }
}
