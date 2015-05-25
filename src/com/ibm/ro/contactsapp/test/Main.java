package com.ibm.ro.contactsapp.test;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class Main {
	public static void main(String[] args) {
		TestSuite suita = new TestSuite();
		suita.addTest(new ApplicationTest("testConstructorContact"));
		suita.addTest(new ApplicationTest("testConstructorAddress"));
		suita.addTest(new ApplicationTest("testGetSurname"));
		suita.addTest(new ApplicationTest("testSetNameNotNull"));
		suita.addTest(new ApplicationTest("testSurnameNotOk"));
		suita.addTest(new ApplicationTest("testEqualsContacts"));
		suita.addTest(new ApplicationTest("testNbDigitPhone"));
		TestRunner.run(suita);
	}
}
