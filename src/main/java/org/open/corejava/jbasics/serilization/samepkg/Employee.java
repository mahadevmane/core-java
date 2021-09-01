package org.open.corejava.jbasics.serilization.samepkg;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    int empId;
    double sal;

    public Employee(String name, String mobNo, Address address, int empId, double sal) {
        super(name, mobNo, address);
        this.empId = empId;
        this.sal = sal;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Employee [EmpId: " + empId + ", Sal: " + sal + ", Name: " + name + ", Mobile: " + mobNo + "\nAddress-\n"
                + address + "]\n";
    }
}
