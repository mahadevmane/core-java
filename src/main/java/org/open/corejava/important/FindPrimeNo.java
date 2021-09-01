package org.open.corejava.important;

import java.io.*;

public class FindPrimeNo {

    public static void main(String[] args) {
        String line;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("Input.txt"));
            bw = new BufferedWriter(new FileWriter(new File("Output.txt")));
            while ((line = br.readLine()) != null) {
                if (isPrime(Integer.parseInt(line))) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number.");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, input file not found.");
        } catch (IOException e) {
            System.out.println("I/O operation causes an error.");
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                System.out
                        .println("Error occured while closing reader or writer.");
            }
        }
    }

    private static boolean isPrime(int no) {
        int lmt = no / 2 + 1, div = 2;
        while (lmt > div) {
            if ((no % div) == 0)
                break;
            div++;
        }
        return lmt <= div && no != 1;
    }
}
