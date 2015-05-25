package com.ibm.ro.contactsapp.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ibm.ro.contactsapp.database.DbConnection;
import com.ibm.ro.contactsapp.models.Contact;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel contentPane;
	private JLabel lblSearch;
	private JTextField txtFldSearch;
	private ButtonGroup buttonGroup;
	private JRadioButton radioBtnName;
	private JRadioButton radioBtnPhone;
	private JRadioButton radioBtnCity;
	private JRadioButton radioBtnCountry;
	private JRadioButton radioBtnSurname;
	private JRadioButton radioBtnEmail;
	private JButton btnSearch;
	private JButton btnView;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnAdd;
	private JLabel lblContacts;
	private JList<String> lstContacts;
	private JScrollPane lstScrollPane;
	private Map<Long, Contact> contactById = new TreeMap<Long, Contact>();

	private DbConnection db;
	private List<Contact> contacts;
	private Contact contact;
	private List<Contact> listCont;
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		try {
			db = DbConnection.getInstance();
			contacts = db.getContacts();
			for(Contact c : contacts){
				contactById.put(c.getId(), c);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		initializeComponent();
	}

	private void setlstContactsData(List<Contact> contacts) {
		String[] lstContactsData = new String[contacts.size()];
		for (int i = 0; i < contacts.size(); i++) {
			lstContactsData[i] = contacts.get(i).toString();
		}
		lstContacts.setListData(lstContactsData);
	}
	
	private void initializeComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 553, 462);
		setTitle("ContactsApp");
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblSearch = new JLabel("Search : ");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSearch.setBounds(10, 11, 30, 30);
		lblSearch.setSize(100, 30);
		contentPane.add(lblSearch, BorderLayout.NORTH);

		txtFldSearch = new JTextField();
		txtFldSearch.setColumns(10);
		txtFldSearch.setBounds(10, 39, 387, 23);
		contentPane.add(txtFldSearch, BorderLayout.NORTH);

		buttonGroup = new ButtonGroup();

		radioBtnName = new JRadioButton("Name");
		radioBtnName.setBounds(22, 83, 60, 23);
		radioBtnName.setSelected(true);
		buttonGroup.add(radioBtnName);
		contentPane.add(radioBtnName);

		radioBtnPhone = new JRadioButton("Phone number");
		radioBtnPhone.setBounds(221, 83, 109, 23);
		buttonGroup.add(radioBtnPhone);
		contentPane.add(radioBtnPhone);

		radioBtnCity = new JRadioButton("City");
		radioBtnCity.setBounds(119, 83, 60, 23);
		buttonGroup.add(radioBtnCity);
		contentPane.add(radioBtnCity);

		radioBtnCountry = new JRadioButton("Country");
		radioBtnCountry.setBounds(119, 109, 88, 23);
		buttonGroup.add(radioBtnCountry);
		contentPane.add(radioBtnCountry);

		radioBtnSurname = new JRadioButton("Surname");
		radioBtnSurname.setBounds(22, 109, 88, 23);
		buttonGroup.add(radioBtnSurname);
		contentPane.add(radioBtnSurname);

		radioBtnEmail = new JRadioButton("Email");
		radioBtnEmail.setBounds(221, 109, 109, 23);
		buttonGroup.add(radioBtnEmail);
		contentPane.add(radioBtnEmail);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(423, 39, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typedText = txtFldSearch.getText();
				if(radioBtnName.isSelected()) {
					listCont = db.searchContactsByCriteria("name", typedText);
				} else if(radioBtnSurname.isSelected()) {
					listCont = db.searchContactsByCriteria("surname", typedText);
				} else if(radioBtnCity.isSelected()) {
					listCont = db.searchContactsByCriteria("town", typedText);
				} else if(radioBtnCountry.isSelected()) {
					listCont = db.searchContactsByCriteria("country", typedText);
				} else if(radioBtnPhone.isSelected()) {
					listCont = db.searchContactsByCriteria("phone", typedText);
				} else if(radioBtnEmail.isSelected())  {
					listCont = db.searchContactsByCriteria("email", typedText);
				} else{}
				setlstContactsData(listCont);
			}
		});

		btnView = new JButton("View");
		btnView.setBounds(423, 234, 89, 23);
		contentPane.add(btnView);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contact = listCont.get(lstContacts.getSelectedIndex());
				ViewContactFrame frame = new ViewContactFrame(contact);
				frame.setVisible(true);
				dispose();
			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(423, 281, 89, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contact = listCont.get(lstContacts.getSelectedIndex());
				ContactFrame frame = new ContactFrame(contact);
				frame.setVisible(true);
				dispose();
			}
		});

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(423, 331, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog((Component) null,
						"Are you sure you want to delete this contact?",
						"alert", JOptionPane.OK_CANCEL_OPTION) == 0) {
					try {
						db.deleteContact(lstContacts.getSelectedIndex());
						JOptionPane.showMessageDialog(null, "Contact deleted!");
						setlstContactsData(contacts);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		btnAdd = new JButton("Add");
		btnAdd.setBounds(423, 377, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContactFrame createFrame = new ContactFrame();
				createFrame.setVisible(true);
				dispose();
			}
		});

		lblContacts = new JLabel("Contacts :");
		lblContacts.setBounds(11, 150, 120, 14);
		lblContacts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblContacts);

		listCont = contacts;
		lstContacts = new JList<String>();
		this.setlstContactsData(contacts);
		lstContacts.setSelectedIndex(0);
	
		lstScrollPane = new JScrollPane();
		lstScrollPane.setBounds(10, 175, 403, 248);
		lstScrollPane.setViewportView(lstContacts);
		contentPane.add(lstScrollPane);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					db.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
