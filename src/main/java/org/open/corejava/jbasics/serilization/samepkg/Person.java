package org.open.corejava.jbasics.serilization.samepkg;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	String mobNo;
	protected Address address;

	public Person(String name, String mobNo, Address address) {
		this.name = name;
		this.mobNo = mobNo;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
