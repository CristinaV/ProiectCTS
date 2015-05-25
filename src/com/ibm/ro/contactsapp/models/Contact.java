package com.ibm.ro.contactsapp.models;

import java.io.*;

@SuppressWarnings("serial")
public class Contact implements Serializable {

	/**
	 * ATTRIBUTES
	 * */
	private Long id; // unique
	private String surname;
	private String name;
	private String phone;
	private String email;
	private String webPage;
	private String imageUrl;
	private Address address;

	/**
	 * DEFAULT CONSTRUCTOR
	 * */
	public Contact(Long id) {
		this.id = id;
		this.address = new Address();
	}

	/**
	 * CONSTRUCTOR
	 * */
	public Contact(Long id, String surname, String name, String phone,
			String email, String webPage, String imageUrl, Address address) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.webPage = webPage;
		this.imageUrl = imageUrl;
		this.address = address;
	}

	/**
	 * GET / SET METHODS
	 * */
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) throws Exception{
		if(id != null){
		this.id = id;
		}else{
			throw new Exception("Id-ul este null");
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) throws Exception {
		if(surname.matches("[a-zA-Z- ]+") && surname!=null){
			this.surname = surname;
		}else{
		System.out.println("Introdu din nou prenumele");
		throw new Exception("Prenume gresit!");
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception{
		if(name.matches("[a-zA-Z-]+") && name!=null){
			this.name = name;
		}else{
		System.out.println("Introdu din nou numele");
		throw new Exception("Nume gresit!");
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws Exception{
		if(phone.matches("[0-9]+") && phone!=null && phone.length()==10){
			this.phone = phone;
		}else{
			System.out.println("Introdu din nou numarul de telefon!");
			throw new Exception("Numar de telefon gresit!");
		}
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception{
		if(email.matches("^[A-Za-z0-9+_.-]+@[a-zA-Z]+(.)[a-zA-Z]+$")&& email!=null){
		this.email = email;
		}else{
			System.out.println("Email gresit");
			throw new Exception("Email gresit!");
		}
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) throws Exception{
		if(webPage.matches("(www.)+[a-zA-Z0-9+_.-]+(.)[a-zA-Z]+$")){
			this.webPage = webPage;
		}else{
			System.out.println("WebPage gresit!");
			throw new Exception("WebPage gresit!"); 
		}
		
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getNumber() {
		return this.address.getNumber();
	}

	public void setNumber(Long number) throws Exception{
		this.address.setNumber(number);
	}

	public String getStreet() {
		return this.address.getStreet();
	}

	public void setStreet(String street) throws Exception {
		this.address.setStreet(street);
	}

	public String getTown() {
		return this.address.getTown();
	}

	public void setTown(String town) throws Exception{
			this.address.setTown(town);
	}

	public String getCountry() {
		return this.address.getCountry();
	}

	public void setCountry(String country) throws Exception{
		this.address.setCountry(country);
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Contact) {
			Contact c = (Contact) obj;
			if (this.getId().equals(c.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.getSurname() + " " + this.getPhone()
				+ " " + this.getEmail() + " " + this.getCountry() + " "
				+ this.getTown();
	}
}
