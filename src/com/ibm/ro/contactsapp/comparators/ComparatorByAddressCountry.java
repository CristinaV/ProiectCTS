package com.ibm.ro.contactsapp.comparators;

import java.util.*;

import com.ibm.ro.contactsapp.models.*;

public class ComparatorByAddressCountry implements Comparator<Contact> {

	@Override
	public int compare(Contact contact1, Contact contact2) {
		return contact1.getCountry().compareTo(contact2.getCountry());
	}
	
}
