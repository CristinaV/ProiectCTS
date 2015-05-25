package com.ibm.ro.contactsapp.database;

import java.io.*;
import java.util.*;

import com.ibm.ro.contactsapp.models.*;

public class DbConnection {

	/**
	 * ATTRIBUTES
	 */
	private static final String DB_NAME = "contacts.bin";
	private List<Contact> contacts;
	private static DbConnection instance;

	/**
	 * PRIVATE CONSTRUCTOR
	 */
	private DbConnection() throws Exception {
		this.deserialize();
	}

	
	/**
	 * SINGLETON CONSTRUCTOR
	 */
	public static DbConnection getInstance() throws Exception {
		if (instance == null) {
			instance = new DbConnection();
		}
		return instance;
	}

	/**
	 * SERIALIZE
	 */
	private void serialize() throws Exception {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DB_NAME));
			out.writeObject(this.contacts);
			out.close();
		} catch (FileNotFoundException ex) {
			File file = new File(DB_NAME);
			file.createNewFile();
		}
	}

	/**
	 * DESERIALIZE
	 */
	@SuppressWarnings("unchecked")
	private void deserialize() throws Exception {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(DB_NAME));
			this.contacts = (List<Contact>) in.readObject();
			in.close();
		} catch (FileNotFoundException ex) {
			
		}catch(Exception e){
			
		}
	}


	/**
	 * CREATE A NEW CONTACT
	 */
	public void createContact(Contact contact) throws Exception {
		if (this.contacts == null) {
			this.contacts = new ArrayList<>();
		}
		if (contact != null) {
			this.contacts.add(contact);
			this.serialize();
		}
	}

	/**
	 * UPDATE A CONTACT
	 */
	public void updateContact(Long id, Contact newContact) throws Exception {
		if (this.contacts != null) {
			Contact contact = getContactById(id);
			if(contact != null) {
				contact.setName(newContact.getName());
				contact.setSurname(newContact.getSurname());
				contact.setPhone(newContact.getPhone());
				contact.setEmail(newContact.getEmail());
				contact.setWebPage(newContact.getWebPage());
				contact.setCountry(newContact.getCountry());
				contact.setTown(newContact.getTown());
				contact.setStreet(newContact.getStreet());
				contact.setNumber(newContact.getNumber());
				contact.setImageUrl(newContact.getImageUrl());
				this.serialize();
			}
		}
	}

	/**
	 * DELETE A CONTACT BY ID
	 */
	public void deleteContact(Long id) throws Exception {
		if (this.contacts != null) {
			Contact contact = getContactById(id);
			this.contacts.remove(contact);
			this.serialize();
		}
	}
	
	/**
	 * DELETE A CONTACT BY POSITION
	 */
	public void deleteContact(int index) throws Exception {
		if (this.contacts != null) {
			if(index >= 0 && index < this.getNrOfContacts()) {
				this.contacts.remove(index);
				this.serialize();
			}
		}
	}

	/**
	 * NEXT CONTACT ID
	 */
	public Long getNextId() {
		if(this.contacts == null || this.getNrOfContacts() == 0) {
			return new Long(1);
		} else {
			return this.contacts.get(this.getNrOfContacts() - 1).getId() + 1;
		}
	}
	
	/**
	 * NUMBER OF CONTACTS
	 */
	public int getNrOfContacts() {
		return this.contacts.size();
	}
	
	/**
	 * GET CONTACTS LIST
	 */
	public List<Contact> getContacts() {
		return this.contacts;
	}
	
	/**
	 * GET CONTACTS SORTED USING COMPARATOR
	 */
	public List<Contact> getContacts(Comparator<Contact> comparator) {
		List<Contact> sortedContacts = new ArrayList<Contact>(this.contacts);
		sortedContacts.sort(comparator);
		return sortedContacts;
	}
	
	private boolean searchWord(String text, String enteredText) {
		if(text.indexOf(enteredText) != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * GET CONTACTS SERCHED BY AN ATTRIBUTE
	 */
	public List<Contact> searchContactsByCriteria(String criteria, String text) {
		List<Contact> contactResults = new ArrayList<Contact>();
		
		if(criteria.toLowerCase().equals("surname")) {
			for(Contact contact : this.contacts) {
				if(searchWord(contact.getSurname().toLowerCase(), text.toLowerCase())) {
					contactResults.add(contact);
				}
			}
		}
		
		if(criteria.toLowerCase().equals("name")) {
			for(Contact contact : this.contacts) {
				if(searchWord(contact.getName().toLowerCase(), text.toLowerCase())) {
					contactResults.add(contact);
				}
			}
		}
		
		if(criteria.toLowerCase().equals("phone")) {
			for(Contact contact : this.contacts) {
				if(searchWord(contact.getPhone().toLowerCase(), text.toLowerCase())) {
					contactResults.add(contact);
				}
			}
		}
		
		if(criteria.toLowerCase().equals("town")) {
			for(Contact contact : this.contacts) {
				if(searchWord(contact.getTown().toLowerCase(), text.toLowerCase())) {
					contactResults.add(contact);
				}
			}
		}
		
		if(criteria.toLowerCase().equals("country")) {
			for(Contact contact : this.contacts) {
				if(searchWord(contact.getCountry().toLowerCase(), text.toLowerCase())) {
					contactResults.add(contact);
				}
			}
		}
	
		return contactResults;
	}
	
	
	/**
	 * GET CONTACT BY ID 
	 */
	public Contact getContactById(Long id) {
		for (Contact contact : this.contacts) {
			if (contact.getId().equals(id)) {
				return contact;
			}
		}
		return null;
	}
	
	/**
	 * GET CONTACT BY INDEX 
	 */
	public Contact getContact(int index) {
		if (this.contacts != null) {
			if(index >= 0 && index < this.getNrOfContacts()) {
				return this.contacts.get(index);
			}
		}
		return null;
	}
	
	/**
	 * CLOSE DB
	 */
	public void close() throws Exception {
		this.serialize();
	}
}
