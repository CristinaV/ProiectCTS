package com.ibm.ro.contactsapp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.ro.contactsapp.database.DbConnection;
import com.ibm.ro.contactsapp.models.Address;
import com.ibm.ro.contactsapp.models.Contact;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class ApplicationTest extends TestCase {

	Address adresa;
	Contact contact;
	Contact contactNou;
	private static final String DB_NAME = "contacts.bin";
	private List<Contact> contacts;
	ObjectInputStream in;
	Contact me;
	
	public ApplicationTest(String message) {
		super(message);
	}

	@Before
	protected void setUp() throws Exception {
		in = new ObjectInputStream(new FileInputStream(DB_NAME));
		this.contacts = (List<Contact>) in.readObject();
		adresa = new Address(27L, "Independentei", " Bucuresti", "Romania");
		contact = new Contact(1L, "Cristina", "Voicu", "0760123",
				"cristina@gmail.com", "www.cristina.ro", "cristina.jpg", adresa);
		contactNou = new Contact(3L,"Marius","Baltaretu","0760234","marius@yahoo.com", "www.marius.ro","marius.jpg",adresa);
		me = mock(Contact.class);
		when(me.getName()).thenReturn(contact.getName());
		
	}

	@After
	public void tearDown() throws Exception{
		in.close();
	}
	
	@Test
	public void testMock(){
		//System.out.println(me.getName());
		assertEquals("Voicu", me.getName());
	}
	
	
	@Test
	public void testConstructorContact() {
		Contact contact1 = contact;
		assertEquals(contact1, contact);
	}

	@Test
	public void testConstructorAddress(){
		Address adresa1 = adresa;
		assertEquals(adresa1, adresa);
	}

	@Test
	public void testGetSurname() {
		String contactSurname = contact.getSurname();
		assertEquals("Cristina", contact.getSurname());
	}

	@Test
	public void testGetName() {
		String contactName = contact.getName();
		assertEquals("Voicu", contact.getName());
	}

	@Test
	public void testGetPhone(){
		String contactPhone = contact.getPhone();
		assertEquals("0760123", contact.getPhone());
	}
	
	@Test
	public void testGetStreet(){
		String contactStreet = contact.getStreet();
		assertEquals("Independentei", contact.getStreet());
	}

	@Test
	public void testSetNameNotNull() {
		try {
			Contact contact1 = new Contact(3L);
			contact1.setName("");
		} catch (Exception e) {
			// System.out.println("parametru gresit");
			assertEquals("Nume gresit!", e.getMessage());
		}
	}

	@Test
	public void testNameNotOk() {
		try {
			Contact contact2 = new Contact(4L);
			contact2.setName("Voicu12");
		} catch (Exception e) {
			assertEquals("Nume gresit!", e.getMessage());
		}
	}

	@Test
	public void testSetSurnameNotNull() {
		try {
			contact.setSurname("");
		} catch (Exception e) {
			assertEquals("Prenume gresit!", e.getMessage());
		}
	}

	@Test
	public void testSurnameNotOk() {
		try {
			Contact contact2 = new Contact(4L);
			contact2.setSurname("Voicu12");
		} catch (Exception e) {
			assertEquals("Prenume gresit!", e.getMessage());
		}
	}

	@Test
	public void testPhoneNotNull() {
		try {
			contact.setPhone("");
		} catch (Exception e) {
			assertEquals("Numar de telefon gresit!", e.getMessage());
		}
	}
	
	@Test
	public void testPhoneContainsLetters(){
		try{
			contact.setPhone("12ab");
		}catch(Exception e){
			assertEquals("Numar de telefon gresit!", e.getMessage());
		}
	}
	
	@Test
	public void testEmailNotNull(){
		try{
			contact.setEmail("");
		}catch(Exception e){
			assertEquals("Email gresit!", e.getMessage());
		}
	}
	
	@Test
	public void testEmailNotOk(){
		try{
			contact.setEmail("cri@a");
		}catch(Exception e){
			assertEquals("Email gresit!", e.getMessage());
			
		}
	}
	
	@Test
	public void testWebPageNotNull(){
		try{
			Contact contact1 = new Contact(5L);
			contact1.setWebPage("");
		}catch(Exception e){
			assertEquals("WebPage gresit!", e.getMessage());
		}
	}
	
	@Test
	public void testWebPageNotOk(){
		try{
			Contact contact1 = new Contact(5L);
			contact1.setWebPage("cristina.ro");
		}catch(Exception e){
			assertEquals("WebPage gresit!", e.getMessage());
		}
	}

	@Test
	public void testAddressStreetNotNull(){
		try{
			contact.setStreet("");
		}catch(Exception e){
			assertEquals("Strada gresita!", e.getMessage());
		}
	}
	
	@Test
	public void testAddressStreetContainsDigits(){
		try{
			Contact contact1= new Contact(5L);
			contact.setStreet("Magheru1");
		}catch(Exception e){
			assertEquals("Strada gresita!", e.getMessage());
		}
	}
	
	@Test
	public void testAddressTownNotNull(){
		try{
			contact.setTown("");
		}catch(Exception e){
			assertEquals("Introdu alt oras!", e.getMessage());
		}
	}
	
	@Test
	public void testAddressTownContainsDigit(){
		try{
			Contact contactNou = new Contact(10L);
			contactNou.setStreet("Caracal10");
		}catch(Exception e){
			assertEquals("Strada gresita!", e.getMessage());
		}
	}
	
	@Test
	public void testAddressCountryNotNull(){
		try{
			Contact contactNou = new Contact(10L);
			contactNou.setCountry("");
		}catch(Exception e){
			assertEquals("Introdu alta tara!", e.getMessage());
		}
	}
	
	@Test
	public void testAddressCountryContainsDigit(){
		try{
			Contact contactNou = new Contact(10L);
			contactNou.setCountry("10Romania ");
		}catch(Exception e){
			assertEquals("Introdu alta tara!", e.getMessage());
		}
	}
	
	@Test 
	public void testEqualsContacts(){
		Contact contactNou = new Contact(3L);
		 contactNou=contact;
		 System.out.println("Obiectele sunt egale!");
		 assertTrue(contactNou.equals(contact));
	}
	
	@Test 
	public void testNbDigitPhone(){
		try{
			Contact contactNou = new Contact(2L, "Alin", "Voicu", "076023",
						 "alin@yahoo.com", "www.alin.ro", "alin.jpg", adresa);
			contactNou.setPhone("0760654897");
		}catch(Exception e){
			assertEquals("Introdu din nou numarul de telefon!", e.getMessage());
		}
	}
	
	@Test
	public void testGetIdContact(){
			contact.getId();
			assertEquals(1L, 1L);
	}
	
	@Test 
	public void testSetIdContact(){
		try{
		Contact contactNou = new Contact(10L);
		contactNou.setId(null);
		}catch(Exception e){
			assertEquals("Id-ul este null", e.getMessage());
		}
		
	}
	
	@Test 
	public void testSetNbAddress(){
		try{
		Contact contactNou = new Contact(10L);
		contactNou.setNumber(null);
		}catch(Exception e){
			assertEquals("Numarul este null", e.getMessage());
		}
		
	}
	
	
//	@Test
//	public void testAllContacts() throws Exception{
//		boolean rezultat = false;
//		
//		try {
//			in = new ObjectInputStream(new FileInputStream(DB_NAME));
//			this.contacts = (List<Contact>) in.readObject();
//			System.out.println(this.contacts.size());
//			while(this.contacts.size()!=0){
//				if(this.contacts.size()==10){
//					rezultat=true;
//				}else{
//					rezultat =false;
//				}
//				
//			}
//			
//			System.out.println("Aia eeee!!");
//			assertEquals(true, rezultat);
//			in.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 System.out.println("Fisier!");
//	}

	
}