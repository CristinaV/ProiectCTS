package com.ibm.ro.contactsapp.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.ibm.ro.contactsapp.models.Contact;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewContactFrame extends JFrame {

	private JPanel contentPane;
	private JPanel imagePanel;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblEmail;
	private JLabel lblWebpage;
	private JLabel lblAdress;
	private JLabel lblStreet;
	private JLabel lblNumber;
	private JLabel lblTown;
	private JLabel lblCountry;
	private JLabel lblPhone;
	private JButton backButton;
	private JLabel lblNameValue;
	private JLabel lblSurnameValue;
	private JLabel lblEmailValue;
	private JLabel lblPhoneValue;
	private JLabel lblWebpageValue;
	private JLabel lblCountryValue;
	private JLabel lblTownValue;
	private JLabel lblStreetValue;
	private JLabel lblNumberValue;

	/**
	 * Create the frame.
	 */
	
	public ViewContactFrame() {
		initializeComponent();
	}
	
	public ViewContactFrame(Contact contact) {
		initializeComponent();
		displayContact(contact);
	}
	
	private void initializeComponent() {
		setTitle("View contact");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 553, 462);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	

		imagePanel = new JPanel();
		imagePanel.setBackground(Color.GRAY);
		imagePanel.setBounds(23, 22, 151, 152);
		imagePanel.setLayout(null);
		contentPane.add(imagePanel);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(211, 33, 45, 14);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(211, 58, 90, 14);
		contentPane.add(lblSurname);
		
		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(211, 83, 46, 14);
		contentPane.add(lblEmail);
		
		lblWebpage = new JLabel("Webpage:");
		lblWebpage.setBounds(208, 133, 81, 14);
		contentPane.add(lblWebpage);
		
		lblAdress = new JLabel("Address");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdress.setBounds(23, 197, 102, 14);
		contentPane.add(lblAdress);
		
		lblStreet = new JLabel("Street:");
		lblStreet.setBounds(23, 285, 66, 14);
		contentPane.add(lblStreet);
		
		lblNumber = new JLabel("Number:");
		lblNumber.setBounds(23, 310, 66, 14);
		contentPane.add(lblNumber);
		
		lblTown = new JLabel("Town:");
		lblTown.setBounds(23, 260, 66, 14);
		contentPane.add(lblTown);
		
		lblCountry = new JLabel("Country:");
		lblCountry.setBounds(23, 235, 66, 14);
		contentPane.add(lblCountry);
		
		lblPhone = new JLabel("Phone number:");
		lblPhone.setBounds(209, 108, 122, 14);
		contentPane.add(lblPhone);
		
		
		lblNameValue = new JLabel("No name");
		lblNameValue.setBounds(325, 33, 152, 14);
		contentPane.add(lblNameValue);
		
		lblSurnameValue = new JLabel("No surname");
		lblSurnameValue.setBounds(325, 58, 171, 14);
		contentPane.add(lblSurnameValue);
		
		lblEmailValue = new JLabel("No email");
		lblEmailValue.setBounds(325, 83, 183, 14);
		contentPane.add(lblEmailValue);
		
		lblPhoneValue = new JLabel("No phone");
		lblPhoneValue.setBounds(325, 108, 171, 14);
		contentPane.add(lblPhoneValue);
		
		lblWebpageValue = new JLabel("No web page");
		lblWebpageValue.setBounds(325, 133, 171, 14);
		contentPane.add(lblWebpageValue);
		
		lblCountryValue = new JLabel("No country");
		lblCountryValue.setBounds(118, 235, 303, 14);
		contentPane.add(lblCountryValue);
		
		lblTownValue = new JLabel("No town");
		lblTownValue.setBounds(118, 260, 259, 14);
		contentPane.add(lblTownValue);
		
		lblStreetValue = new JLabel("No street");
		lblStreetValue.setBounds(118, 285, 270, 14);
		contentPane.add(lblStreetValue);
		
		lblNumberValue = new JLabel("No street number");
		lblNumberValue.setBounds(118, 310, 270, 14);
		contentPane.add(lblNumberValue);
		
		backButton = new JButton("Back");
		backButton.setBounds(228, 348, 90, 23);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				MainFrame backToSearch = new MainFrame();
				backToSearch.setVisible(true);
				dispose();
			}
		});
	}

	private void displayContact(Contact contact) {
		if(contact != null) {
			if(contact.getName() != null || !contact.getName().equals("")) {
				lblNameValue.setText(contact.getName());
			}
			if(contact.getSurname() != null || !contact.getSurname().equals("")) {
				lblSurnameValue.setText(contact.getSurname());
			}
			if(contact.getPhone() != null || !contact.getPhone().equals("")) {
				lblPhoneValue.setText(contact.getPhone());
			}
			if(contact.getEmail() != null || !contact.getEmail().equals("")) {
				lblEmailValue.setText(contact.getEmail());
			}
			if(contact.getWebPage() != null || !contact.getWebPage().equals("")) {
				lblWebpageValue.setText(contact.getWebPage());
			}
			
			if(contact.getCountry() != null || !contact.getCountry().equals("")) {
				lblCountryValue.setText(contact.getCountry());
			}
			if(contact.getTown() != null || !contact.getTown().equals("")) {
				lblTownValue.setText(contact.getTown());
			}
			if(contact.getStreet() != null || !contact.getStreet().equals("")) {
				lblStreetValue.setText(contact.getStreet());
			}
			if(contact.getNumber() != null || !contact.getNumber().equals("")) {
				lblNumberValue.setText(contact.getNumber().toString());
			}
			if(contact.getImageUrl() != null) {
				ImageIcon imageIcon = new ImageIcon(contact.getImageUrl());
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(newimg);
				JLabel label = new JLabel();
				
				label.setBounds(0, 0, imagePanel.getWidth(), imagePanel.getHeight());
				label.setIcon(imageIcon);
				imagePanel.add(label);
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewContactFrame frame = new ViewContactFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
