package com.ibm.ro.contactsapp.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import com.ibm.ro.contactsapp.database.DbConnection;
import com.ibm.ro.contactsapp.models.Contact;

@SuppressWarnings("serial")
public class ContactFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblWebPage;
	private JLabel lblAddress;
	private JLabel lblStreet;
	private JButton btnSubmit;
	private JButton btnBack;
	private JLabel lblNumber;
	private JLabel lblTown;
	private JLabel lblSurname;
	private JLabel lblCountry;
	private JLabel lblUploadPhoto;
	private JButton btnBrowse;
	private JTextField txtFldName;
	private JTextField txtFldPhone;
	private JTextField txtFldEmail;
	private JTextField txtFldWebPage;
	private JTextField txtFldStreet;
	private JTextField txtFldTown;
	private JTextField txtFldSurname;
	private JTextField txtFldNumber;
	private JTextField txtFldBrowse;
	private JTextField txtFldCountry;
	
	private DbConnection db;
	private int operationType;
	private Long contactId;
	private ArrayList<String> error = new ArrayList<String>();
	
	private String filePath;
	
	/**
	 * Create the frame.
	 */
	public ContactFrame() {
		operationType = 1;
		try {
			db = DbConnection.getInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		initializeComponent();
	}
	
	public ContactFrame(Contact contact) {
		contactId = contact.getId();
		operationType = 2;
		try {
			db = DbConnection.getInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		initializeComponent();
		displayContact(contact);
	}
	
	private void initializeComponent() {
		setTitle("Create new contact");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 531, 412);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblName = new JLabel("Name : ");
		lblName.setBounds(10, 11, 46, 14);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(10, 44, 46, 14);
		contentPane.add(lblSurname);
		
		lblPhone = new JLabel("Phone number : ");
		lblPhone.setBounds(10, 69, 107, 23);
		contentPane.add(lblPhone);
		
		lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(10, 100, 46, 23);
		contentPane.add(lblEmail);
		
		lblWebPage = new JLabel("Webpage : ");
		lblWebPage.setBounds(10, 131, 76, 23);
		contentPane.add(lblWebPage);
		
		lblAddress = new JLabel("Address ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAddress.setBounds(10, 165, 88, 20);
		contentPane.add(lblAddress);
		
		lblCountry = new JLabel("Country :");
		lblCountry.setBounds(10, 196, 55, 20);
		contentPane.add(lblCountry);
		
		lblTown = new JLabel("Town :");
		lblTown.setBounds(10, 227, 68, 20);
		contentPane.add(lblTown);
		
		lblStreet = new JLabel("Street :");
		lblStreet.setBounds(10, 258, 78, 14);
		contentPane.add(lblStreet);
		
		lblNumber = new JLabel("Number :");
		lblNumber.setBounds(10, 283, 78, 14);
		contentPane.add(lblNumber);
		
		lblUploadPhoto = new JLabel("Upload photo :");
		lblUploadPhoto.setBounds(290, 196, 88, 20);
		contentPane.add(lblUploadPhoto);
		
		txtFldName = new JTextField();
		txtFldName.setBounds(138, 8, 242, 20);
		txtFldName.setColumns(10);
		contentPane.add(txtFldName);
		
		txtFldSurname = new JTextField();
		txtFldSurname.setBounds(138, 39, 242, 20);
		txtFldSurname.setColumns(10);
		contentPane.add(txtFldSurname);
		
		txtFldPhone = new JTextField();
		txtFldPhone.setBounds(138, 70, 242, 20);
		txtFldPhone.setColumns(10);
		contentPane.add(txtFldPhone);
		
		txtFldWebPage = new JTextField();
		txtFldWebPage.setBounds(138, 132, 242, 20);
		txtFldWebPage.setColumns(10);
		contentPane.add(txtFldWebPage);
		
		txtFldEmail = new JTextField();
		txtFldEmail.setBounds(138, 101, 242, 20);
		txtFldEmail.setColumns(10);
		contentPane.add(txtFldEmail);
		
		txtFldCountry = new JTextField();
		txtFldCountry.setBounds(138, 196, 126, 20);
		txtFldCountry.setColumns(10);
		contentPane.add(txtFldCountry);
		
		txtFldTown = new JTextField();
		txtFldTown.setBounds(138, 227, 126, 20);
		contentPane.add(txtFldTown);
		txtFldTown.setColumns(10);
		
		txtFldStreet = new JTextField();
		txtFldStreet.setBounds(138, 255, 126, 20);
		contentPane.add(txtFldStreet);
		txtFldStreet.setColumns(10);
		
		txtFldNumber = new JTextField();
		txtFldNumber.setBounds(138, 283, 126, 20);
		contentPane.add(txtFldNumber);
		txtFldNumber.setColumns(10);
		
		txtFldBrowse = new JTextField();
		txtFldBrowse.setBounds(290, 227, 203, 20);
		txtFldBrowse.setColumns(10);
		txtFldBrowse.setEnabled(false);
		contentPane.add(txtFldBrowse);
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.setBounds(290, 258, 88, 23);
		contentPane.add(btnBrowse);
		
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileFilter() {
			        @Override
			        public boolean accept(File f) {
			            if (f.isDirectory()) {
			                return true;
			            }
			            final String name = f.getName();
			            return name.endsWith(".png") || name.endsWith(".jpg");
			        }

			        @Override
			        public String getDescription() {
			            return "*.png,*.jpg";
			        }
			    });
			    chooser.showOpenDialog(null);
			    BufferedImage image = null;
			    if(chooser.getSelectedFile() != null){
			    	txtFldBrowse.setText(chooser.getSelectedFile().getAbsolutePath());
			    	String filename = chooser.getSelectedFile().getAbsolutePath();
			    	String extension = filename.substring(filename.lastIndexOf(".") + 1,filename.length());
			    	String name = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
			    	try {
						image  = ImageIO.read(chooser.getSelectedFile());
						File file =  new File("pictures\\" + name);						
						ImageIO.write(image, extension, file);
						filePath = file.getAbsolutePath();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    }
			}
		});
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(119, 344, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact contact = new Contact(db.getNextId());
				try {
					contact.setName(txtFldName.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setSurname(txtFldSurname.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setPhone(txtFldPhone.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setEmail(txtFldEmail.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setWebPage(txtFldWebPage.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setCountry(txtFldCountry.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setTown(txtFldTown.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					contact.setStreet(txtFldStreet.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try{
					try {
						contact.setNumber(Long.parseLong(txtFldNumber.getText()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}catch(NumberFormatException ee){
					JOptionPane.showMessageDialog(null, "Please enter a number!");
				}
				contact.setImageUrl(filePath);
				if(validation()){
					try {
						if(operationType == 1) {
							db.createContact(contact);
							JOptionPane.showMessageDialog(null, "Contact created!");
						} else {
							db.updateContact(contactId, contact);
							JOptionPane.showMessageDialog(null, "Contact updated!");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					MainFrame backToSearch = new MainFrame();
					backToSearch.setVisible(true);
					dispose();
				}else{
					String errors = "";
					for(String s : error){
						errors += s + " ";
					}
					JOptionPane.showMessageDialog(null, "Invalid Field For " + errors);
					error.clear();;
				}
			
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setBounds(330, 344, 89, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame searchFrame = new MainFrame();
				searchFrame.setVisible(true);
				dispose();
			}
		});
	}
	
	private void displayContact(Contact contact) {
		if(contact != null) {
			if(contact.getName() != null) {
				txtFldName.setText(contact.getName());
			}
			if(contact.getSurname() != null) {
				txtFldSurname.setText(contact.getSurname());
			}
			if(contact.getPhone() != null) {
				txtFldPhone.setText(contact.getPhone());
			}
			
			if(contact.getEmail() != null || !contact.getEmail().equals("")) {
				txtFldEmail.setText(contact.getEmail());
			}
			if(contact.getWebPage() != null || !contact.getWebPage().equals("")) {
				txtFldWebPage.setText(contact.getWebPage());
			}
			
			if(contact.getCountry() != null || !contact.getCountry().equals("")) {
				txtFldCountry.setText(contact.getCountry());
			}
			if(contact.getTown() != null || !contact.getTown().equals("")) {
				txtFldTown.setText(contact.getTown());
			}
			if(contact.getStreet() != null || !contact.getStreet().equals("")) {
				txtFldStreet.setText(contact.getStreet());
			}
			if(contact.getNumber() != null || !contact.getNumber().equals("")) {
				txtFldNumber.setText(contact.getNumber().toString());
			}
		}
	}
	
	private boolean validation(){
		boolean aux = true;
		if(!(txtFldName.getText().matches("[a-zA-Z-]+"))){
			aux = false;
			error.add("Name");
		}
		if(!(txtFldSurname.getText().matches("[a-zA-Z- ]+"))){
			aux = false;
			error.add("Surname");
		}
		if(!(txtFldPhone.getText().matches("[0-9]+"))){
			aux = false;
			error.add("Phone number");
		}
		if(!(txtFldEmail.getText().matches("^[A-Za-z0-9+_.-]+@[a-zA-Z]+(.)[a-zA-Z]+$"))){
			aux = false;
			error.add("E-Mail");
		}
		if(!(txtFldWebPage.getText().matches("(www.)+[a-zA-Z0-9+_.-]+(.)[a-zA-Z]+$"))){
			aux = false;
			error.add("WebPage");
		}
		if(!(txtFldCountry.getText().matches("[a-zA-Z- ]+"))){
			aux = false;
			error.add("Country");
		}
		if(!(txtFldTown.getText().matches("[a-zA-Z- ]+"))){
			aux = false;
			error.add("Town");
		}
		if(!(txtFldStreet.getText().matches("[a-zA-Z- ]+"))){
			aux = false;
			error.add("Street");
		}
		if(!(txtFldNumber.getText().matches("[0-9]+"))){
			aux = false;
			error.add("Number");
		}
		return aux;
	}
	
	public static boolean containsOnlyLetters(String str) {
	    for (int i = 0; i < str.length(); i++) {
	      if (!Character.isLetter(str.charAt(i)))
	        return false;
	    }
	    return true;
	  }  
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactFrame frame = new ContactFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
