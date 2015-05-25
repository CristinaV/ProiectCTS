package com.ibm.ro.contactsapp.comparators;

import java.util.*;

import com.ibm.ro.contactsapp.models.*;

public class ComparatorByName implements Comparator<Contact> {

	@Override
	public int compare(Contact contact1, Contact contact2) {
		return contact1.getName().compareTo(contact2.getName());
	}	
	
}
