package com.ibm.ro.contactsapp.models;

import java.io.*;

@SuppressWarnings("serial")
public class Address implements Serializable {

	/**
	 * ATTRIBUTES
	 * */
	private Long number;
	private String street;
	private String town;
	private String country;

	/**
	 * DEFAULT CONSTRUCTOR
	 * */
	public Address() {}
	
	/**
	 * CONSTRUCTOR
	 * */
	public Address(Long number, String street, String town, String country) {
		this.number = number;
		this.street = street;
		this.town = town;
		this.country = country;
	}

	/**
	 * GET / SET METHODS
	 * */
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number)throws Exception {
		if(number!= null){
			System.out.println("este ok!");
			this.number = number;
			}else{
				System.out.println("Este gresit");
				throw new Exception("Numarul este null");
			}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street)throws Exception {
		if(street.matches("[a-zA-Z- ]+") && street!=null){
		this.street = street;
		}else{
			System.out.println("Introdu alta strada!");
			throw new Exception("Strada gresita!");
		}
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) throws Exception{
		try{
		if(town.matches("[a-zA-Z- ]+")&& town!=null){
			this.town= town;
		}
		}catch(Exception e){
			System.out.println("Introdu alt oras!");
			throw new Exception("Introdu alt oras!");
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) throws Exception{
		try{
			if(town.matches("[a-zA-Z- ]+")&& country!=null){
				this.country = country;
			}
			}catch(Exception e){
				System.out.println("Introdu alta tara!");
				throw new Exception("Introdu alta tara!");
			}
		
	}
}
