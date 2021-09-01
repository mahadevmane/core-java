package org.open.corejava.jbasics.serilization.samepkg;

import java.io.*;

public class FileDemo {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Address ad = new Address("Jankar Colony", "Satara", "Maharashtra", "India");
        Address ad1 = new Address("Pandharkawada", "Yavtmal", "Maharashtra", "India");
        Employee emp1 = new Employee("Mahadev", "+919404275209", ad, 6792, 25000);
        Employee emp2 = new Employee("Prachi", "+917588591421", ad1, 9792, 25000);
        Employee emp3 = new Employee("Mahadev", "+919404275209", ad, 6792, 25000);
        Employee emp4 = new Employee("Mahadev", "+919404275209", ad1, 6792, 25000);
        Employee emp5 = new Employee("Mahadev", "+919404275209", ad, 6792, 25000);

        File f = new File("File.txt");
        try {
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(emp1);
            oos.writeObject(emp2);
            oos.writeObject(emp3);
            oos.writeObject(emp4);
            oos.writeObject(emp5);
            oos.writeObject(emp2);
            /* Write null at the end of file, if using readObject() */
            oos.writeObject(null);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employee out = null;
            /*
             * The readObject() method throw an EOFException while EOF
             * encounter.
             */
            while ((out = (Employee) ois.readObject()) != null)
                System.out.println(out);
        } catch (EOFException e) {
            System.out.println("End of file is not proper.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Wel done...");
        }
    }
}
