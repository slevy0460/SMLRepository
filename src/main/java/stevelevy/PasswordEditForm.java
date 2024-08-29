package stevelevy;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;

public class PasswordEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDesc;
	private JTextField txtStorage;
	private JTextField txtWebsite;
	private JTextField txtUserid;
	private JTextField txtExpirationDate;
	private JTextField txtCardNumber;
	private JTextField txtPin;
	private JTextField txtAccountNumber;
	private JPanel jPanelCenter;
	private JPanel jPanelButtons;
	private JButton btnInquiry;
	private JPanel jPanelBottom;
	private JLabel lblClassName;
	private JButton btnUpdate;
	private JLabel lblNewLabel;
	private JButton btnPrint;
	private JComboBox cmbType;
	private JButton btnClose;
	private JTextField txtWindowsuser;
	private JLabel lblWindowsuser;
	private JLabel lblPassword;
	private JTextField txtPassword;
	JCheckBox chkbxAdministrator = new JCheckBox("Administrator");
	JCheckBox chkbxTabs = new JCheckBox("Tabs");
	
	int typeindex = 0;
	String descriptionsearch;
	String classname = null;
	String administrator = "slevy";
	String userName = System.getProperty("user.name");
	String websiteurl = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordEditForm frame = new PasswordEditForm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public PasswordEditForm(String id) {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(PasswordEditForm.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1000, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(10, 11, 1000, 65);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblAccount = new JLabel("Account");
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccount.setBounds(10, 37, 55, 14);
		jPanelTop.add(lblAccount);

		txtId = new JTextField();
		txtId.setFont(new Font("Dialog", Font.BOLD, 12));
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(83, 37, 86, 20);
		jPanelTop.add(txtId);

		txtDesc = new JTextField();
		txtDesc.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDesc.setEditable(false);
		txtDesc.setColumns(10);
		txtDesc.setBounds(179, 37, 235, 20);
		jPanelTop.add(txtDesc);

		lblNewLabel = new JLabel("Account Deescription");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(179, 11, 246, 14);
		jPanelTop.add(lblNewLabel);

		txtWindowsuser = new JTextField();
		txtWindowsuser.setFont(new Font("Dialog", Font.BOLD, 12));
		txtWindowsuser.setEditable(false);
		txtWindowsuser.setBounds(550, 37, 73, 20);
		jPanelTop.add(txtWindowsuser);
		txtWindowsuser.setColumns(10);

		lblWindowsuser = new JLabel("Windows User");
		lblWindowsuser.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWindowsuser.setBounds(443, 37, 97, 14);
		jPanelTop.add(lblWindowsuser);

		jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(10, 87, 1000, 420);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JLabel lblAccount_1 = new JLabel("Account");
		lblAccount_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAccount_1.setBounds(29, 29, 126, 14);
		jPanelCenter.add(lblAccount_1);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dialog", Font.BOLD, 12));
		lblType.setBounds(29, 72, 126, 14);
		jPanelCenter.add(lblType);

		JLabel lblUserid = new JLabel("User Id");
		lblUserid.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUserid.setBounds(29, 201, 126, 14);
		jPanelCenter.add(lblUserid);

		JLabel lblWebsite = new JLabel("Web Site");
		lblWebsite.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWebsite.setBounds(29, 244, 126, 14);
		jPanelCenter.add(lblWebsite);

		JLabel lblStorage = new JLabel("Storage");
		lblStorage.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStorage.setBounds(29, 373, 126, 14);
		jPanelCenter.add(lblStorage);

		txtStorage = new JTextField();
		txtStorage.setFont(new Font("Dialog", Font.BOLD, 12));
		txtStorage.setBounds(179, 373, 299, 20);
		jPanelCenter.add(txtStorage);
		txtStorage.setColumns(10);

		txtWebsite = new JTextField();
		txtWebsite.setFont(new Font("Dialog", Font.BOLD, 12));
		txtWebsite.setBounds(179, 244, 299, 20);
		jPanelCenter.add(txtWebsite);
		txtWebsite.setColumns(10);

		txtUserid = new JTextField();
		txtUserid.setFont(new Font("Dialog", Font.BOLD, 12));
		txtUserid.setBounds(179, 201, 299, 20);
		jPanelCenter.add(txtUserid);
		txtUserid.setColumns(10);

		txtAccountNumber = new JTextField();
		txtAccountNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAccountNumber.setBounds(179, 29, 299, 20);
		jPanelCenter.add(txtAccountNumber);
		txtAccountNumber.setColumns(10);

		JLabel lblOin = new JLabel("Pin");
		lblOin.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOin.setBounds(29, 287, 126, 14);
		jPanelCenter.add(lblOin);

		txtPin = new JTextField();
		txtPin.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPin.setBounds(179, 287, 299, 20);
		jPanelCenter.add(txtPin);
		txtPin.setColumns(10);

		JLabel lblCarNumber = new JLabel("Card Number");
		lblCarNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCarNumber.setBounds(29, 330, 126, 14);
		jPanelCenter.add(lblCarNumber);

		txtCardNumber = new JTextField();
		txtCardNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCardNumber.setBounds(179, 330, 299, 20);
		jPanelCenter.add(txtCardNumber);
		txtCardNumber.setColumns(10);

		JLabel lblExpirationDate = new JLabel("Expiration Date");
		lblExpirationDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExpirationDate.setBounds(29, 158, 126, 14);
		jPanelCenter.add(lblExpirationDate);

		txtExpirationDate = new JTextField();
		txtExpirationDate.setFont(new Font("Dialog", Font.BOLD, 12));
		txtExpirationDate.setBounds(179, 158, 299, 20);
		jPanelCenter.add(txtExpirationDate);
		txtExpirationDate.setColumns(10);

		cmbType = new JComboBox();
		cmbType.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbType.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));
		cmbType.setBounds(179, 72, 299, 20);
		jPanelCenter.add(cmbType);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPassword.setBounds(29, 115, 126, 14);
		jPanelCenter.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPassword.setColumns(10);
		txtPassword.setBounds(179, 115, 299, 20);
		jPanelCenter.add(txtPassword);
		
		chkbxAdministrator.setFont(new Font("Dialog", Font.BOLD, 12));
		chkbxAdministrator.setBounds(550, 29, 126, 21);
		jPanelCenter.add(chkbxAdministrator);
		
		chkbxTabs.setFont(new Font("Dialog", Font.BOLD, 12));
		chkbxTabs.setBounds(550, 72, 126, 21);
		jPanelCenter.add(chkbxTabs);

		jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(10, 506, 1000, 33);
		contentPane.add(jPanelButtons);

		btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.setIcon(new ImageIcon(PasswordEditForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});
		
				btnInquiry = new JButton("Inquiry");
				btnInquiry.setFont(new Font("Dialog", Font.BOLD, 12));
				btnInquiry.setIcon(new ImageIcon(PasswordEditForm.class.getResource("/images/task-planning.png")));
				btnInquiry.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							doInquiry();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				jPanelButtons.add(btnInquiry);
		
				btnUpdate = new JButton("Update");
				btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
				btnUpdate.setIcon(new ImageIcon(PasswordEditForm.class.getResource("/images/updated.png")));
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doUpdate(txtId.getText());
					}
				});
				jPanelButtons.add(btnUpdate);
		jPanelButtons.add(btnPrint);

		JButton btnWebSite = new JButton("WebSite");
		btnWebSite.setFont(new Font("Dialog", Font.BOLD, 12));
		btnWebSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = websiteurl.trim();
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnWebSite.setIcon(new ImageIcon(PasswordEditForm.class.getResource("/images/website.png")));
		jPanelButtons.add(btnWebSite);

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(PasswordEditForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(10, 543, 1000, 50);
		contentPane.add(jPanelBottom);

		lblClassName = new JLabel("lblClassname");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		setTitle("Password Edit/Maintenance");
		
		doCenterForm();
		addTypes();
		doPopulate(id);
		if (this.websiteurl != null && this.websiteurl.trim().length() == 0) {
			btnWebSite.setEnabled(false);
		}
	}

	private void doCenterForm() {

		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doPopulate(String idpas) {

		ResultSet rs = null;
		String id = idpas;
		String desc;
		String number;
		String type;
		String pin;
		String cardnumber;
		String expirationdate;
		String userid;
		String password1;
		String website;
		String storage;
		String windowsuser;
		Integer sort = 0;
		String administrator = null;
		String tabs = null;
				

		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color cyan = new Color(0, 255, 255);
		String sql = null;
		sql = "SELECT * FROM passwords a ";
		sql += "LEFT JOIN PASSWORDTYPE B ON B.TYPE = A.TYPE ";
		sql += "WHERE A.ID = '" + id + "'";

		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");

		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {

				sort = (rs.getInt("SORT"));
//				sort = sort - 1;
				cmbType.setSelectedIndex(sort);

				id = (rs.getString("a.id"));
				desc = (rs.getString("a.Accountdescription"));
				windowsuser = (rs.getString("a.Windowsuser"));
				number = (rs.getString("a.Accountnumber"));
				type = (rs.getString("a.type"));
				pin = (rs.getString("a.Pinnumber"));
				cardnumber = (rs.getString("a.Cardnumber"));
				expirationdate = (rs.getString("a.Expirationdate"));
				userid = (rs.getString("a.userid"));
				password1 = (rs.getString("a.Password"));
				website = (rs.getString("a.website"));
				storage = (rs.getString("a.storage"));
				administrator = (rs.getString("a.administrator"));
				tabs = (rs.getString("a.tabs"));

				this.websiteurl = website;

				txtId.setText(id);
				txtDesc.setText(desc);

				txtAccountNumber.setText(number);
				txtWindowsuser.setText(windowsuser);
				txtPin.setText(pin);
				txtCardNumber.setText(cardnumber);
				txtExpirationDate.setText(expirationdate);
				txtUserid.setText(userid);
				txtPassword.setText(password1);
				txtWebsite.setText(website);
				txtStorage.setText(storage);
				if (expirationdate == null) {
					txtExpirationDate.setText("0");
				}
				
				chkbxAdministrator.setSelected(false);
				
				if (administrator.equals("Yes")) {
					chkbxAdministrator.setSelected(true);
				}
				
				chkbxTabs.setSelected(false);
				if (tabs.equals("Yes")) {
					chkbxTabs.setSelected(true);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void addTypes() {

		ResultSet rs = null;
		String query = "select * from passwordtype order by type";

		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");

			while (rs.next()) {

				String type = (rs.getString("TYPE"));
				cmbType.addItem(type);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void doUpdate(String idpas) {

		ResultSet rs = null;
		Boolean status = false;
		String administrator;
		String tabs;
		String type = null;
		Integer number = Integer.valueOf(txtExpirationDate.getText());
		if (number == 0) {
			txtExpirationDate.setText("0");
		}
		if (cmbType.getSelectedIndex() == 0) {
			type = "";
		}
		Object selectedItem = cmbType.getSelectedItem();
		type = selectedItem.toString();
		type = type.trim();
		
		administrator = "No";
		if (chkbxAdministrator.isSelected()) {
			administrator = "Yes";
		} 
		tabs = "No";
		if (chkbxTabs.isSelected()) {
			tabs = "Yes";
		} 
		String id = idpas;
		String sql = null;
		sql = "UPDATE passwords ";
		sql += "SET " + "AccountDescription = '" + txtDesc.getText() + "', ";
		sql += "AccountNumber = '" + txtAccountNumber.getText() + "', ";
		sql += "Type = '" + type + "', ";
		sql += "Pinnumber = '" + txtPin.getText() + "', ";
		sql += "CardNumber = '" + txtCardNumber.getText() + "', ";
		sql += "Expirationdate = '" + number + "', ";
		sql += "UserID = '" + txtUserid.getText() + "', ";
		sql += "Password = '" + txtPassword.getText() + "', ";
		sql += "WebSite = '" + txtWebsite.getText() + "', ";
		sql += "Storage = '" + txtStorage.getText() + "', ";
		sql += "Administrator = '" + administrator + "', ";
		sql += "Tabs = '" + tabs + "' ";
		sql += " WHERE ID = " + id;
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doMenu() throws SQLException {
		doUpdate(txtId.getText());

		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
	}

	private void doInquiry() throws SQLException, UnknownHostException {

		PasswordInquiryForm a = new PasswordInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void printRecord(JPanel panel) {
		// Create PrinterJob Here
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		// Set Printer Job Name
		printerJob.setJobName("Print Record");
		// Set Printable
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				// Check If No Printable Content
				if (pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;
				}

				// Make 2D Graphics to map content
				Graphics2D graphics2D = (Graphics2D) graphics;
				// Set Graphics Translations
				// A Little Correction here Multiplication was not working so I replaced with
				// addition
				graphics2D.translate(pageFormat.getImageableX() + 10, pageFormat.getImageableY() + 10);
				// This is a page scale. Default should be 0.3 I am using 0.5
				graphics2D.scale(0.5, 0.5);

				// Now paint panel as graphics2D
				panel.paint(graphics2D);

				// return if page exists
				return Printable.PAGE_EXISTS;
			}
		});
		// Store printerDialog as boolean
		boolean returningResult = printerJob.printDialog();
		// check if dilog is showing
		if (returningResult) {
			// Use try catch exeption for failure
			try {
				// Now call print method inside printerJob to print
				printerJob.print();
			} catch (PrinterException printerException) {
				JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
			}
		}
	}
}
