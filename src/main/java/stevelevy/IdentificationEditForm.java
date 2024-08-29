package stevelevy;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * This class represents a form for editing identification information. It
 * extends JFrame and contains several fields for inputting and editing
 * identification data. The form includes fields for name, ID, number, security
 * code, country, type, issue date, and expiration date. It also includes
 * buttons for inquiry, update, and close actions.
 */
public class IdentificationEditForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNumber;
	private JTextField txtSecurityCode;
	JComboBox cmbName = new JComboBox();
	JComboBox cmbCountry = new JComboBox();
	JComboBox cmbType = new JComboBox();
	JDateChooser dateChooserIssueDate = new JDateChooser();
	JDateChooser dateChooserExpirationDate = new JDateChooser();
	JDateChooser dateChooserBirthday = new JDateChooser();
	JLabel LblExpired = new JLabel("Expired");
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationEditForm frame = new IdentificationEditForm(false, null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param existyn - a boolean indicating whether the identification exists
	 * @param name    - the name of the identification
	 * @param object  - the type of the identification
	 * @param id      - the id of the identification
	 */
	public IdentificationEditForm(boolean existyn, String name, String type, String country, String id) {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(IdentificationEditForm.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toppanel.setBounds(0, 0, 790, 50);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Idendtification Inquiry/Maintenance");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTitle.setBounds(8, 10, 200, 13);
		lblTitle.setForeground(Color.BLUE);
		toppanel.add(lblTitle);

		JLabel lblInstructions = new JLabel("User this form for Identification Inquiry/Maintenance");
		lblInstructions.setBounds(8, 27, 250, 13);
		toppanel.add(lblInstructions);

		JPanel searchpanel = new JPanel();
		searchpanel.setLayout(null);
		searchpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchpanel.setBounds(0, 50, 790, 80);
		contentPane.add(searchpanel);

		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 10, 42, 13);
		searchpanel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtId.setBounds(100, 10, 50, 19);
		searchpanel.add(txtId);
		txtId.setColumns(10);

		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerpanel.setBounds(0, 130, 790, 290);
		contentPane.add(centerpanel);
		centerpanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 20, 42, 13);
		centerpanel.add(lblNewLabel);

		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNumber.setBounds(313, 57, 42, 13);
		centerpanel.add(lblNumber);

		txtNumber = new JTextField();
		txtNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumber.selectAll();
			}
		});
		txtNumber.setColumns(10);
		txtNumber.setBounds(403, 54, 150, 19);
		centerpanel.add(txtNumber);

		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIssueDate.setBounds(10, 86, 80, 13);
		centerpanel.add(lblIssueDate);

		JLabel lblExpirationDate = new JLabel("Expiration Date");
		lblExpirationDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExpirationDate.setBounds(10, 119, 80, 13);
		centerpanel.add(lblExpirationDate);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCountry.setBounds(10, 152, 80, 13);
		centerpanel.add(lblCountry);

		JLabel lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSecurityCode.setBounds(10, 185, 80, 13);
		centerpanel.add(lblSecurityCode);

		txtSecurityCode = new JTextField();
		txtSecurityCode.setFont(new Font("Dialog", Font.BOLD, 12));
		txtSecurityCode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSecurityCode.selectAll();
			}
		});
		txtSecurityCode.setColumns(10);
		txtSecurityCode.setBounds(100, 185, 150, 19);
		centerpanel.add(txtSecurityCode);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dialog", Font.BOLD, 12));
		lblType.setBounds(10, 218, 80, 13);
		centerpanel.add(lblType);
		cmbType.setFont(new Font("Dialog", Font.BOLD, 12));

		cmbType.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));
		cmbType.setBounds(100, 218, 150, 21);
		centerpanel.add(cmbType);

		dateChooserIssueDate.setDateFormatString("MM-dd-yyyy");
		dateChooserIssueDate.setBounds(100, 86, 150, 19);
		centerpanel.add(dateChooserIssueDate);

		dateChooserExpirationDate.setDateFormatString("MM-dd-yyyy");
		dateChooserExpirationDate.setBounds(100, 119, 150, 19);
		centerpanel.add(dateChooserExpirationDate);
		cmbCountry.setFont(new Font("Dialog", Font.BOLD, 12));

		cmbCountry.setModel(new DefaultComboBoxModel(new String[] { "Not Select" }));
		cmbCountry.setBounds(100, 152, 150, 21);
		centerpanel.add(cmbCountry);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBirthday.setBounds(313, 20, 80, 13);
		centerpanel.add(lblBirthday);

		dateChooserBirthday.setDateFormatString("MM-dd-yyyy");
		dateChooserBirthday.setBounds(403, 13, 150, 19);
		centerpanel.add(dateChooserBirthday);

		LblExpired.setEnabled(false);
		LblExpired.setVisible(false);
		LblExpired.setFont(new Font("Tahoma", Font.BOLD, 15));
		LblExpired.setBounds(313, 119, 75, 13);
		LblExpired.setForeground(Color.RED);
		centerpanel.add(LblExpired);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescription.setBounds(10, 57, 70, 13);
		centerpanel.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDescription.setColumns(10);
		txtDescription.setBounds(100, 54, 150, 19);
		centerpanel.add(txtDescription);

		cmbName.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));
		cmbName.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbName.setBounds(100, 16, 150, 21);
		centerpanel.add(cmbName);

		JPanel buttonpanel = new JPanel();
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonpanel.setBounds(0, 420, 790, 40);
		contentPane.add(buttonpanel);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate(txtId.getText());
			}
		});

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doUpdate(txtId.getText());
					doInquiry();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnOnedrive = new JButton("OneDrive");
		btnOnedrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOneDrive();
			}
		});
		btnOnedrive.setIcon(new ImageIcon(IdentificationEditForm.class.getResource("/images/folder.png")));
		buttonpanel.add(btnOnedrive);
		btnInquiry.setIcon(new ImageIcon(IdentificationEditForm.class.getResource("/images/task-planning.png")));
		btnInquiry.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnInquiry);
		btnUpdate.setIcon(new ImageIcon(IdentificationEditForm.class.getResource("/images/updated.png")));
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnUpdate);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClose.setIcon(new ImageIcon(IdentificationEditForm.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);

		JPanel bottempanel = new JPanel();
		bottempanel.setBounds(0, 460, 790, 40);
		contentPane.add(bottempanel);
		bottempanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottempanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("IdentificationEditForm");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setForeground(Color.BLUE);
		bottempanel.add(lblClassName);

		setTitle("Idendtification Inquiry/Maintenance");

		// center the form on the monitor
		setLocationRelativeTo(null);
		// call doPopulateTable at the end of the constructor
		addNames();
		addTypes();
		addCountry();
		if (!existyn) {
			doAdd(name, type, country);
			id = getaddid(name, type, country);
		}
		doPopulateTable(id);
	}

	private void doPopulateTable(String id) {
		ResultSet rs = null;

		String name = null;
		String description = null;
		String type = null;
		String number = null;
		String issuedate = null;
		String expirationdate = null;
		String birthday = null;
		String country = null;
		String securitycode = null;

		// Create a SimpleDateFormat with the date format you want
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		try {
			String sql = "SELECT ";
			sql += "i.id, i.name, i.number, i.issuedate, ";
			sql += "i.expirationdate, i.country, ";
			sql += "i.securitycode, i.typedescription, p.birthday, i.description ";
			sql += "FROM identification i ";
			sql += "LEFT JOIN personalinformation p on trim(p.FullName) = trim(i.Name) ";
			sql += "WHERE i.id = '" + id + "'";
//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = (rs.getString("id"));
				name = (rs.getString("name"));
				description = (rs.getString("description"));
				type = (rs.getString("typedescription"));
				number = (rs.getString("number"));
				issuedate = (rs.getString("issuedate"));
				expirationdate = (rs.getString("expirationdate"));
				birthday = (rs.getString("birthday"));
				country = (rs.getString("country"));
				securitycode = (rs.getString("securitycode"));

				// Issue date Parse the date string to a java.util.Date object
				date = sdf.parse(issuedate);
				// Set the date in JDateChooser
				dateChooserIssueDate.setDate(date);

				// Expiration date Parse the date string to a java.util.Date object
				date = sdf.parse(expirationdate);
				// Set the date in JDateChooser
				dateChooserExpirationDate.setDate(date);

				// Compare the two dates
				// Get today's date
				Date today = new Date();
				if (date.before(today)) {
					LblExpired.setEnabled(true);
					LblExpired.setVisible(true);
				}
				// Birthday Parse the date string to a java.util.Date object
				if (birthday == null) {
					birthday = "1001-01-01";
				}
				date = sdf.parse(birthday);
				// Set the date in JDateChooser
				dateChooserBirthday.setDate(date);

				txtId.setText(id);
				this.cmbName.setSelectedItem(name);
				txtDescription.setText(description);
				txtNumber.setText(number);
				cmbCountry.setSelectedItem(country);
				cmbType.setSelectedItem(type);
				txtSecurityCode.setText(securitycode);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void doAdd(String name, String type, String country) {
		String blank = "";
		String date = "0001-01-01";
		String zero = "0";

		ResultSet rs = null;
		String sql = "Insert into identification (name, number, issuedate, expirationdate, country, securitycode, typedescription, description ) VALUES (";
		sql += "'" + name + "' , "; // name
		sql += "'" + blank + "' , "; // number
		sql += "'" + date + "' , "; // issue date
		sql += "'" + date + "' , "; // expiration date
		sql += "'" + country + "',  "; // country
		sql += "'" + blank + "' , "; // security code
		sql += "'" + type + "',  "; // type description
		sql += "'" + blank + "') "; // description

//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
		}
	}

	private String getaddid(String name, String type, String country) {
		ResultSet rs = null;
		String id = null;

		try {
			String sql = "SELECT ";
			sql += "MAX(id) as id ";
			sql += "FROM identification ";
			sql += "WHERE name = '" + name + "' ";
			sql += "AND typedescription = '" + type + "' ";
			sql += "AND country = '" + country + "' ";
			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = (rs.getString("id"));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return id;
	}

	private void doUpdate(String idpas) {

		ResultSet rs = null;
		String countrydescription = null;
		String typedescription = null;

		Object selectedItem;

		String id = idpas;
		String sql = null;

		selectedItem = cmbCountry.getSelectedItem();
		countrydescription = selectedItem.toString();
		selectedItem = cmbType.getSelectedItem();
		typedescription = selectedItem.toString();

		selectedItem = cmbType.getSelectedItem();
		typedescription = selectedItem.toString();
		typedescription = typedescription.trim();

		// Create a SimpleDateFormat with the date format you want
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		// Isssue Date Get the date from JDateChooser Issue Date
		date = dateChooserIssueDate.getDate();
		// Format the date to a string in the format you want
		String issuedate = sdf.format(date);

		// Get Expiration Date the date from JDateChooser Expiration Date
		date = dateChooserExpirationDate.getDate();
		// Format the date to a string in the format you want
		String expirationdate = sdf.format(date);

		// Get Birthday the date from JDateChooser Expiration Date
		date = dateChooserBirthday.getDate();
		// Format the date to a string in the format you want
		String birthday = sdf.format(date);
		String name = (String) this.cmbName.getSelectedItem();

		sql = "UPDATE identification ";
		sql += "SET " + "name = '" + name + "', ";
		sql += "number = '" + txtNumber.getText() + "', ";
		sql += "issuedate = '" + issuedate + "', ";
		sql += "expirationDate = '" + expirationdate + "', ";
//		sql += "birthday = '" + birthday + "', ";
		sql += "country = '" + countrydescription + "', ";
		sql += "securityCode = '" + txtSecurityCode.getText() + "', ";
		sql += "typedescription = '" + typedescription + "', ";
		sql += "description = '" + txtDescription.getText() + "' ";
		sql += " WHERE ID = '" + id + "' ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doInquiry() throws SQLException {

		IdentificationInquiryForm_2 a = new IdentificationInquiryForm_2();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doOneDrive() {
		String onedrive = "https://onedrive.live.com/";
		Desktop desktop = Desktop.getDesktop();
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(onedrive));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method adds names to the combo box from the database.
	 */
	private void addNames() {
		ResultSet rs = null;
		String query = "select * from personalinformation order by fullname";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String fullname = rs.getString("fullname");
				this.cmbName.addItem(fullname);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method adds types to the combo box from the database.
	 */
	private void addTypes() {
		ResultSet rs = null;
		String query = "select * from identificationtype order by description";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String type = rs.getString("description");
				this.cmbType.addItem(type);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method adds country to the combo box from the database.
	 */
	private void addCountry() {
		ResultSet rs = null;
		String query = "select * from identificationCountry order by description";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String country = rs.getString("description");
				this.cmbCountry.addItem(country);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
