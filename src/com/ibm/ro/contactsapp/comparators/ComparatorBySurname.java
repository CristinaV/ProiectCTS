package com.ibm.ro.contactsapp.comparators;

import java.util.Comparator;

import com.ibm.ro.contactsapp.models.*;

public class ComparatorBySurname implements Comparator<Contact> {

	@Override
	public int compare(Contact contact1, Contact contact2) {
		return contact1.getSurname().compareTo(contact2.getSurname());
	}	
	
}
