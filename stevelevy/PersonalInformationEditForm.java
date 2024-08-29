package stevelevy;

import java.awt.Color;
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
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.UnknownHostException;

/**
 * This class represents a form for editing identification information. It
 * extends JFrame and contains several fields for inputting and editing
 * identification data. The form includes fields for name, ID, number, security
 * code, country, type, issue date, and expiration date. It also includes
 * buttons for inquiry, update, and close actions.
 */
public class PersonalInformationEditForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFullName;
	private JTextField txtId;
	private JTextField txtAddress1;
	JDateChooser dateChooserBirthday = new JDateChooser();
	private JTextField txtAddress2;
	private JTextField txtAddress3;
	private JTextField txtAddress4;
	private JTextField txtSalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalInformationEditForm frame = new PersonalInformationEditForm(null, null, null);
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
	 * @param invalid
	 * 
	 * @param fullname
	 * @param string
	 * @throws SQLException 
	 * @throws UnknownHostException 
	 */
	public PersonalInformationEditForm(Boolean existyn, String id, String fullname) throws UnknownHostException, SQLException {
		int screen = SMLUtility.getCurrentMonitorInfo(PersonalInformationEditForm.this);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1500, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toppanel.setBounds(0, 0, 1500, 50);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Personal Information Inquiry/Maintence");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTitle.setBounds(8, 10, 250, 13);
		lblTitle.setForeground(Color.BLUE);
		toppanel.add(lblTitle);

		JLabel lblInstructions = new JLabel("User this form for Personal Information Inquiry/Maintenance");
		lblInstructions.setBounds(8, 27, 400, 13);
		toppanel.add(lblInstructions);

		JPanel searchpanel = new JPanel();
		searchpanel.setLayout(null);
		searchpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchpanel.setBounds(0, 50, 1500, 40);
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
		centerpanel.setBounds(0, 86, 1500, 334);
		contentPane.add(centerpanel);
		centerpanel.setLayout(null);

		txtFullName = new JTextField();
		txtFullName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFullName.selectAll();
			}
		});
		txtFullName.setBounds(100, 32, 150, 19);
		centerpanel.add(txtFullName);
		txtFullName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Full Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(10, 37, 80, 13);
		centerpanel.add(lblNewLabel);

		JLabel lblAddr1 = new JLabel("Address #1");
		lblAddr1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAddr1.setBounds(10, 141, 80, 13);
		centerpanel.add(lblAddr1);

		txtAddress1 = new JTextField();
		txtAddress1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAddress1.selectAll();
			}
		});
		txtAddress1.setColumns(10);
		txtAddress1.setBounds(100, 137, 500, 19);
		centerpanel.add(txtAddress1);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblBirthday.setBounds(313, 37, 80, 13);
		centerpanel.add(lblBirthday);
		
		// Set the new font to the JDateChooser
		dateChooserBirthday.setFont(new Font("Tahoma", Font.BOLD, 10));
		dateChooserBirthday.setDateFormatString("MM-dd-yyyy");
		dateChooserBirthday.setBounds(403, 32, 150, 19);
		centerpanel.add(dateChooserBirthday);

		JLabel lblAddress = new JLabel("Address #2");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAddress.setBounds(10, 191, 80, 13);
		centerpanel.add(lblAddress);

		txtAddress2 = new JTextField();
		txtAddress2.setColumns(10);
		txtAddress2.setBounds(100, 188, 500, 19);
		centerpanel.add(txtAddress2);

		JLabel lblAddr1_1_1 = new JLabel("Address #3");
		lblAddr1_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAddr1_1_1.setBounds(10, 241, 80, 13);
		centerpanel.add(lblAddr1_1_1);

		txtAddress3 = new JTextField();
		txtAddress3.setColumns(10);
		txtAddress3.setBounds(100, 239, 500, 19);
		centerpanel.add(txtAddress3);

		JLabel lblAddr1_1_1_1 = new JLabel("Address #4");
		lblAddr1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAddr1_1_1_1.setBounds(10, 291, 80, 13);
		centerpanel.add(lblAddr1_1_1_1);

		txtAddress4 = new JTextField();
		txtAddress4.setColumns(10);
		txtAddress4.setBounds(100, 290, 500, 19);
		centerpanel.add(txtAddress4);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(100, 77, 150, 19);
		centerpanel.add(txtSalary);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSalary.setBounds(10, 77, 80, 13);
		centerpanel.add(lblSalary);

		JPanel buttonpanel = new JPanel();
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonpanel.setBounds(0, 420, 1500, 40);
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
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInquiry.setIcon(new ImageIcon(PersonalInformationEditForm.class.getResource("/images/task-planning.png")));
		btnInquiry.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnInquiry);
		btnUpdate.setIcon(new ImageIcon(PersonalInformationEditForm.class.getResource("/images/updated.png")));
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnUpdate);
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClose.setIcon(new ImageIcon(PersonalInformationEditForm.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);

		JPanel bottempanel = new JPanel();
		bottempanel.setBounds(0, 460, 1500, 40);
		contentPane.add(bottempanel);
		bottempanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottempanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("IdentificationEditForm");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setForeground(Color.BLUE);
		bottempanel.add(lblClassName);

		setTitle("Personal Information Inquiry/Maintence");

		// center the form on the monitor
		setLocationRelativeTo(null);
		// call doPopulateTable at the end of the constructor
		if (!existyn) {
			doAdd(fullname);
			id = getaddid(fullname);
		}

		doPopulateTable(existyn, id);
	}

	private void doPopulateTable(boolean existyn, String id) {
		ResultSet rs = null;

		String fullname = null;
		String addr1 = null;
		String addr2 = null;
		String addr3 = null;
		String addr4 = null;
		String birthday = null;
		String salary = null;

		// Create a SimpleDateFormat with the date format you want
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		try {
			String sql = "SELECT ";
			sql += "* ";
			sql += "FROM PersonalInformation ";
			sql += "WHERE id = '" + id + "'";
//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = (rs.getString("id"));
				fullname = (rs.getString("fullname"));
				addr1 = (rs.getString("address1"));
				addr2 = (rs.getString("address2"));
				addr3 = (rs.getString("address3"));
				addr4 = (rs.getString("address4"));
				birthday = (rs.getString("birthday"));
				salary = (rs.getString("salary"));

				// Birthday Parse the date string to a java.util.Date object
				date = sdf.parse(birthday);
				// Set the date in JDateChooser
				dateChooserBirthday.setDate(date);

				txtId.setText(id);
				txtFullName.setText(fullname);
				txtAddress1.setText(addr1);
				txtAddress2.setText(addr2);
				txtAddress3.setText(addr3);
				txtAddress4.setText(addr4);
				txtSalary.setText(salary);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void doAdd(String fullname) {
		ResultSet rs = null;
		String blank = "";
		String date = "0001-01-01";
		String zero = "0";

		String sql = "Insert into PersonalInformation (fullname, address1, address2, address3, address4, birthday, salary) VALUES ( ";
		sql += " '" + fullname + "' , ";
		sql += "'" + blank + "' , "; // address #1
		sql += "'" + blank + "' , "; // address #1
		sql += "'" + blank + "' , "; // address #2
		sql += "'" + blank + "' , "; // address #3
		sql += "'" + date + "' , "; // birthday
		sql += "'" + zero + "' ) "; // salary

//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private String getaddid(String fullname) {
		ResultSet rs = null;
		String id = null;

		try {
			String sql = "SELECT ";
			sql += "MAX(id) as id ";
			sql += "FROM PersonalInformation ";
			sql += "WHERE fullname = '" + fullname + "' ";

//			System.out.println("SQL is : " + sql);
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

		String id = idpas;
		String sql = null;

		// Create a SimpleDateFormat with the date format you want
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;

		// Get Birthday the date from JDateChooser Expiration Date
		date = dateChooserBirthday.getDate();
		// Format the date to a string in the format you want
		String birthday = sdf.format(date);
		String salary = txtSalary.getText();

		sql = "UPDATE PersonalInformation ";
		sql += "SET " + "fullname = '" + txtFullName.getText() + "', ";
		sql += "address1 = '" + txtAddress1.getText() + "', ";
		sql += "address2 = '" + txtAddress2.getText() + "', ";
		sql += "address3 = '" + txtAddress3.getText() + "', ";
		sql += "address4 = '" + txtAddress4.getText() + "', ";
		sql += "birthday = '" + birthday + "', ";
		sql += "salary = '" + salary + "' ";
		sql += " WHERE ID = '" + id + "' ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doInquiry() throws SQLException, UnknownHostException {

		PersonalInformationInquiryForm a = new PersonalInformationInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}
}
