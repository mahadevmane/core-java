package org.open.corejava.jbasics.collections;

public class Address implements Cloneable, Comparable<Address> {
	String street, city, state, country;

	public Address() {
		street = "";
		city = "";
		state = "";
		country = "";
	}

	public Address(String street, String city, String state, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Address a = (Address) obj;
		return this.street == a.street && this.city == a.city && this.state == a.state && this.country == a.country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 1;
		hash = hash * prime + street.hashCode();
		hash = hash * prime + city.hashCode();
		hash = hash * prime + state.hashCode();
		hash = hash * prime + country.hashCode();
		return hash;
	}

	@Override
	public int compareTo(Address o) {
		if (this.street.compareTo(o.street) > 0)
			return 1;
		else if (this.street.compareTo(o.street) == 0) {
			if (this.city.compareTo(o.city) > 0)
				return 1;
			else if (this.city.compareTo(o.city) == 0) {
				if (this.state.compareTo(o.state) > 0)
					return 1;
				else if (this.state.compareTo(o.state) == 0) {
					if (this.country.compareTo(o.country) > 0)
						return 1;
					else if (this.country.compareTo(o.country) == 0) {
						return 0;
					} else
						return -1;
				} else
					return -1;
			} else
				return -1;
		} else
			return -1;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Street: " + street + "\tCity: " + city + "\tState: " + state + "\tCountry: " + country;
	}
}
