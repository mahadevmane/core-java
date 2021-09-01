package org.open.corejava.jbasics.collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CollectionDemo {

    public static void main(String[] args) {
        int ch = 0, pos1, pos2;
        ArrayList<Address> al = new ArrayList<Address>();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n\n\n\n--- Main Menu---");
            System.out.println("\n1. Add Element");
            System.out.println("2. Swap Element");
            System.out.println("3. Copy Element to Whole List");
            System.out.println("4. Display List");
            System.out.println("5. Break List");
            System.out.println("6. Exit");
            System.out.println("\nEnter your choice: ");
            try {
                ch = s.nextInt();
            } catch (Exception e) {
                System.out.println("Please, enter valid choice...");
            }
            switch (ch) {
                case 1:
                    try {
                        add(al);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("\nEnter positions: ");
                    pos1 = s.nextInt();
                    pos2 = s.nextInt();
                    try {
                        swapElement(pos1, pos2, al);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Sorry, list have " + al.size() + " elements.");
                    }
                    break;
                case 3:
                    try {
                        copyToAllPosition(al, new Address("Jankar Colony", "Satara", "Maharashtra", "India"));
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    display(al);
                    break;
                case 5:
                    System.out.println("\nEnter position: ");
                    pos1 = s.nextInt();
                    ArrayList<Address> list = breakListAt(pos1, al);
                    System.out.println("After Splitting...\nFirst List\n");
                    display(al);
                    System.out.println("\nSecond List\n");
                    display(list);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("\n\n\nSorry, invalid choice...");
            }
            s.close();
        }
    }

    private static ArrayList<Address> breakListAt(int pos1, ArrayList<Address> al) {
        int i = 0;
        ArrayList<Address> list = new ArrayList<Address>();
        Iterator<Address> e = al.iterator();
        for (; e.hasNext() && i < pos1; i++)
            e.next();
        for (; e.hasNext(); )
            list.add(al.remove(pos1));
        return list;
    }

    private static void copyToAllPosition(ArrayList<Address> al, Address a) throws CloneNotSupportedException {
        int pos = 0;
        for (Iterator<Address> i = al.iterator(); i.hasNext(); i.next())
            al.set(pos++, (Address) a.clone());

        System.out.println("After Coping...\n");
        display(al);
    }

    private static void swapElement(int pos1, int pos2, ArrayList<Address> al) {
        Address temp = al.get(pos1);
        al.set(pos1, al.get(pos2));
        al.set(pos2, temp);
        System.out.println("After Swapping...\n");
        display(al);
    }

    private static void add(ArrayList<Address> al) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Address a = new Address();
        System.out.println("Enter street: ");
        a.setStreet(br.readLine());
        System.out.println("Enter city: ");
        a.setCity(br.readLine());
        System.out.println("Enter state: ");
        a.setState(br.readLine());
        System.out.println("Enter country: ");
        a.setCountry(br.readLine());
        al.add(a);
        System.out.println("\n\n\nThe object is added to list successfully.");
    }

    private static void display(ArrayList<Address> al) {
        for (Iterator<Address> i = al.iterator(); i.hasNext(); )
            System.out.println(i.next());
    }
}
