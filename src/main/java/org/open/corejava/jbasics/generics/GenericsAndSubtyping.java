package org.open.corejava.jbasics.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericsAndSubtyping {
    public void doSomething(Collection<?> c) {
        c.add(null);
    }
}

class MotorVehicalDepartment {
    public void createList() {
        CensusBeureau c = new CensusBeureau();

        // Following line will not compile because
        // what we are passing is copy of registry of Drivers
        // If this was allowed then the printList
        // will be able to add someone to the list of Drivers
        // who is actually not a Driver
        // c.printList(new ArrayList<Driver>());

        c.printList2(new ArrayList<Driver>());
        c.printList2(new ArrayList<Technician>());
    }
}

class CensusBeureau {

    /**
     * This function is of less use because you cannot pass any ArrayList that
     * is not of type Person.
     * <p>
     * The problem is that If c.printList(new ArrayList&lt;Driver&gt;()) was
     * allowed then
     *
     * <pre>
     * persons.add(new Technician());
     * </pre>
     * <p>
     * will compile which will be which will be terribly wrong. How can a list
     * of Drivers accept Technician - The contract will be broken. Hence the
     * <strong>compiler will not allow c.printList(new
     * ArrayList&lt;Driver&gt;())</strong> in the first place.
     * <p>
     * Instead not using Generic would have been better
     *
     * <pre>
     *   printList(List persons)
     * </pre>
     * <p>
     * Yes; you got it. With the above signature, the function will not be type
     * safe.
     *
     * @param persons
     */
    public void printList(List<Person> persons) {

        // This will break the contract of ArrayList<Driver>() was passed as
        // parameter to this function
        persons.add(new Technician());

    }

    /**
     * This function is better than printList. Now I can pass ArrayList of any
     * class that extends Person. This function is more useful than the previous
     * one.
     * <p>
     * So far so good. Check the next function now in this class now.
     *
     * @param persons
     */
    public void printList2(List<? extends Person> persons) {

        for (Person p : persons) {
            System.out.println(p.getClass().getName());
        }

    }

    /**
     * This function is supposed to check whether the person already exists in
     * the list, if yes then update it or else add it. I will not explain the
     * logic - you guys are more intelligent than me to figure out;
     * <em>Spare people
     * like me who has intelligence of average level.</em>. This reminds me of
     * Sharukh Khan in the movie - "My Name is Khan" tapping on his forehead and
     * saying : "I am very intelligent". <br>
     * <br>
     * Well... lets get back to the point. Now you can pass ArrayList&lt;any
     * class that extends Person&gt; as first parameter and object of any class
     * that extends Person as second Parameter. However, you will come across a
     * bitter truth that you will not be able add a new object to persons in the
     * function though you can get the object from it. Following code will fail
     *
     * <pre>
     * persons.add(person)
     * </pre>
     * <p>
     * Why ? In order to understand the reason let us learn how to read the
     * signature of the function. The perfect trans-literation will be - the
     * first argument of updateList accepts an List of <strong>unknown</strong>
     * that extends from Person. That ? denotes unknown. That unknown is what it
     * is - UNKNOWN - the compiler does not know whether it is Driver or
     * Technician. If you had passed list of Driver then how would the compiler
     * know that it is the list of Driver and must not allow Technician to be
     * added to the list. Hence it will not even allow Object to be added to the
     * list because Object is not type Person. The only thing it will allow to
     * add will be <strong>null</strong> as null is a member of all types (I did
     * not know this before I pounced on Generics)
     *
     * <br>
     * <br>
     * <strong>See updateList2 for solution</strong>
     *
     * @param persons
     * @param person
     */
    public void updateList(List<? extends Person> persons, Person person) {

        // Following will not compile
        // persons.add(person);

    }

    /**
     * Here we are using a Generic Method and not a WildCard. See the definition
     * of T and how it is elegantly used in the signature of the function. The
     * function translates to : updateList2 takes first argument of type List
     * containing object of any class that extends from Person and the second
     * parameter exactly of that type which List contains. <br>
     * <br>
     * Now we can merrily add to persons. In all such situations where you need
     * to update the Generic type passed as parameter to the function, you need
     * to use Generic Method and not a wild card. Also, in cases where the
     * parameters have some relation to each other - you must use generic method
     * and not wildcard. In functions where you only need to read from the
     * generic type passed as parameter, you can use wild card.
     *
     * @param <T>
     * @param persons
     * @param person
     */
    public <T extends Person> void updatedList2(List<T> persons, T person) {

        System.out.println(persons.get(0));
        persons.add(person);

    }

}

class Driver extends Person {

}

class Person {

}

class Technician extends Person {

}