package stevelevy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class ExpenseMaintenanceForm_2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDescription;
	private JTextField txtAmount;
	private JTextField txtBankId;
	String bankid = null;
	String bankdescription = null;
	JCheckBox chckbxInclude = new JCheckBox("Include Payment");
	JDateChooser jdatechosserDueDdate = new JDateChooser();
	JComboBox comboBoxFrequency = new JComboBox();
	JComboBox comboBoxCategory = new JComboBox();
	String className = getClass().getSimpleName();
	private JTextField txtDescriptionAdditional;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ExpenseMaintenanceForm_2 dialog = new ExpenseMaintenanceForm_2(null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExpenseMaintenanceForm_2(String bankid, String bankdesc, String expenseid, String expensedesc) {
		this.bankid = bankid;
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		// Create a JScrollPane and add the contentPanel to it
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(800, 50));
		contentPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		setTitle("Expense Maintenance Form");
		JLabel lblTitle = new JLabel("Expense Maintenance Form");
		lblTitle.setPreferredSize(new Dimension(50, 25));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		northPanel.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setText("Use this form to edit Expences");
		lblInstructions.setPreferredSize(new Dimension(50, 25));
		lblInstructions.setForeground(Color.BLACK);
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanel.add(lblInstructions, BorderLayout.SOUTH);

		JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(centertabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		centertabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);

		JLabel lblBankid = new JLabel("Bank Id");
		lblBankid.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblBankid.setBounds(8, 10, 100, 15);
		panel.add(lblBankid);

		JLabel lblExpenseId = new JLabel("Expense Id");
		lblExpenseId.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblExpenseId.setBounds(8, 50, 100, 14);
		panel.add(lblExpenseId);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDescription.setBounds(8, 90, 100, 14);
		panel.add(lblDescription);

		JLabel lblNewLabel = new JLabel("Due Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(8, 130, 100, 14);
		panel.add(lblNewLabel);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAmount.setBounds(8, 170, 100, 14);
		panel.add(lblAmount);

		JLabel lblNewLabel_1 = new JLabel("Frequency");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(8, 210, 100, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Category");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(8, 250, 100, 14);
		panel.add(lblNewLabel_2);

		txtBankId = new JTextField();
		txtBankId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBankId.setText((String) null);
		txtBankId.setColumns(10);
		txtBankId.setBounds(148, 10, 86, 20);
		panel.add(txtBankId);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(148, 50, 50, 20);
		panel.add(txtId);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDescription.setColumns(10);
		txtDescription.setBounds(148, 90, 200, 20);
		panel.add(txtDescription);

		jdatechosserDueDdate.setDateFormatString("MM/dd/yyyy");
		jdatechosserDueDdate.setBounds(148, 130, 150, 20);
		panel.add(jdatechosserDueDdate);

		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAmount.setColumns(10);
		txtAmount.setBounds(148, 170, 86, 20);
		panel.add(txtAmount);

		comboBoxFrequency.setModel(
				new DefaultComboBoxModel(new String[] { "", "Monthly", "Yearly", "Quartely", "Bimonthly", "Once" }));
		comboBoxFrequency.setFont(new Font("Tahoma", Font.BOLD, 10));
		comboBoxFrequency.setBounds(148, 210, 180, 22);
		panel.add(comboBoxFrequency);

		comboBoxCategory.setModel(new DefaultComboBoxModel(new String[] {"", "Auto", "Charge Accounts", "Computer", "Dining", "Groceries", "Home: Mortgage", "Medical", "Miscellaneous Expense", "Miscellaneous Withdrawal", "Service Charge", "Utilities", "Vacation", "Other"}));
		comboBoxCategory.setFont(new Font("Tahoma", Font.BOLD, 10));
		comboBoxCategory.setBounds(148, 250, 180, 22);
		panel.add(comboBoxCategory);

		chckbxInclude.setFont(new Font("Tahoma", Font.BOLD, 10));
		chckbxInclude.setBounds(148, 290, 180, 23);
		panel.add(chckbxInclude);

		JPanel bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(800, 100));
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));

		JPanel buttonpanel = new JPanel();
		buttonpanel.setPreferredSize(new Dimension(1500, 50));
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = true;
				if (expenseid == null) {
					btnUpdate.setText("Add");
					ok = editAdd();
				} else {
					try {
						doUpdate(expenseid);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					try {
						doreturn();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}

				if (ok) {
					try {
						doAdd();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					try {
						doreturn();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(ExpenseMaintenanceForm_2.class.getResource("/images/updated.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonpanel.add(btnUpdate);

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankInuquiryForm_3 a = new BankInuquiryForm_3();
				a.addWindowListener(new java.awt.event.WindowAdapter() {
				});
				a.setVisible(true);
				dispose();
			}
		});
		btnInquiry.setIcon(new ImageIcon(ExpenseMaintenanceForm_2.class.getResource("/images/task-planning.png")));
		btnInquiry.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonpanel.add(btnInquiry);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doreturn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnReturn.setIcon(new ImageIcon(ExpenseMaintenanceForm_2.class.getResource("/images/left-arrow.png")));
		buttonpanel.add(btnReturn);

		JPanel classpanel = new JPanel();
		classpanel.setPreferredSize(new Dimension(1500, 50));
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("SMLDefaultJFrame");
		lblClassName.setPreferredSize(new Dimension(150, 14));
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		classpanel.add(lblClassName);

		// Add the scrollPane to the contentPane instead of the contentPanel
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		txtBankId.setText(this.bankid);
		
		txtDescriptionAdditional = new JTextField();
		txtDescriptionAdditional.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDescriptionAdditional.setColumns(10);
		txtDescriptionAdditional.setBounds(522, 90, 400, 20);
		panel.add(txtDescriptionAdditional);
		
		JLabel lblAdditionalDescription = new JLabel("Additional Description");
		lblAdditionalDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAdditionalDescription.setBounds(377, 90, 150, 14);
		panel.add(lblAdditionalDescription);
		doCenterForm();
		if (expenseid == null) {
			btnUpdate.setText("Add");
		}
		try {
			doPopulate(expenseid);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doPopulate(String id) throws ParseException {
		ResultSet rs = null;
		String sql = null;
		String expenceid = null;
		String description;
		String includeyn = null;
		java.util.Date duedate;
		String date;
		String frequency = null;
		String category = null;

		sql = "SELECT * FROM PEXPENSE ";
		sql += "WHERE ID = '" + id + "' ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			txtBankId.setText(this.bankid);
			while (rs.next()) {
				txtId.setText(rs.getString("id"));
				txtDescription.setText(rs.getString("description"));
				txtDescriptionAdditional.setText(rs.getString("descriptionadditional"));
				txtAmount.setText(rs.getString("amount"));
				txtBankId.setText(rs.getString("Bankid"));
				date = (rs.getString("Duedate"));
				Date workdate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

				jdatechosserDueDdate.setDate(workdate);

				includeyn = (rs.getString("Include"));

				if (includeyn.equals("Yes")) {
					chckbxInclude.setSelected(true);
				} else {
					chckbxInclude.setSelected(false);
				}

				frequency = (rs.getString("Frequency"));
				comboBoxFrequency.setSelectedIndex(0);
				if (frequency.equals("Monthly")) {
					comboBoxFrequency.setSelectedIndex(1);
				}
				if (frequency.equals("Yearly")) {
					comboBoxFrequency.setSelectedIndex(2);
				}
				if (frequency.equals("Quartely")) {
					comboBoxFrequency.setSelectedIndex(3);
				}
				if (frequency.equals("Bimonthly")) {
					comboBoxFrequency.setSelectedIndex(4);
				}
				if (frequency.equals("Once")) {
					comboBoxFrequency.setSelectedIndex(5);
				}
				populateCategory(rs);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	private Boolean editAdd() {
		boolean ok = true;

		if (txtDescription.getText() == null || txtDescription.getText().length() == 0) {
			ok = false;
			JOptionPane.showMessageDialog(null, "Please enter description");
		} else {
			if (txtBankId.getText() == null || txtBankId.getText().length() == 0) {
				ok = false;
				JOptionPane.showMessageDialog(null, "Please enter bank id");
			} else {
				ok = true;
			}
		}

		return ok;

	}

	private void doAdd() throws SQLException {
		String sql = null;
		ResultSet rs = null;
		String amount = txtAmount.getText().replace("$", "");
		String includeyn = null;
		SimpleDateFormat MMddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		// Get Expiration Date the date from JDateChooser Expiration Date
		Date date = jdatechosserDueDdate.getDate();
		// Format the date to a string in the format you want
		String duedate = yyyymmdd.format(date);
		if (chckbxInclude.isSelected()) {
			includeyn = "Yes";
		} else {
			includeyn = "No";
		}

		String frequency = null;
		if (comboBoxFrequency.getSelectedIndex() != 0) {
			int index = comboBoxFrequency.getSelectedIndex();

			if (index == 1) {
				frequency = "Monthly";
			}

			if (index == 2) {
				frequency = "Yearly";
			}

			if (index == 3) {
				frequency = "Quartely";
			}
			if (index == 4) {
				frequency = "Bimonthly";
			}
			if (index == 5) {
				frequency = "Once";
			}

		}
		String category = updateCategory();

		sql = "INSERT PEXPENSE ";
		sql += "(DESCRIPTION, DESCRIPTIONADDITIONAL, AMOUNT, BANKID, INCLUDE, DUEDATE, FREQUENCY, CATEGORY ) ";
		sql += "VALUES (";
		sql += " '" + txtDescription.getText() + " ' , ";
		sql += " '" + txtDescriptionAdditional.getText() + " ' , ";
		sql += " '" + amount + " ' , ";
		sql += " '" + txtBankId.getText() + "',  ";
		sql += " '" + includeyn + " ' , ";
		sql += " '" + duedate + " ' , ";
		sql += " '" + frequency + "' , ";
		sql += " '" + category + " ')  ";

		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doUpdate(String id) throws SQLException {
		String sql = null;
		ResultSet rs = null;
		String amount = txtAmount.getText().replace("$", "");
		String includeyn = null;
		SimpleDateFormat MMddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		// Get Expiration Date the date from JDateChooser Expiration Date
		Date date = jdatechosserDueDdate.getDate();
		// Format the date to a string in the format you want
		String duedate = yyyymmdd.format(date);

		if (chckbxInclude.isSelected()) {
			includeyn = "Yes";
		} else {
			includeyn = "No";
		}

		String frequency = null;
		if (comboBoxFrequency.getSelectedIndex() != 0) {
			int index = comboBoxFrequency.getSelectedIndex();

			if (index == 1) {
				frequency = "Monthly";
			}

			if (index == 2) {
				frequency = "Yearly";
			}

			if (index == 3) {
				frequency = "Quartely";
			}
			if (index == 4) {
				frequency = "Bimonthly";
			}
			if (index == 5) {
				frequency = "Once";
			}
		}

		String category = updateCategory();

		sql = "UPDATE PEXPENSE ";
		sql += "SET  ";
		sql += "DESCRIPTION = '" + txtDescription.getText() + "', ";
		sql += "DESCRIPTIONADDITIONAL = '" + txtDescriptionAdditional.getText() + "', ";
		sql += "AMOUNT = '" + amount + "', ";
		sql += "BANKID = '" + txtBankId.getText() + "', ";
		sql += "INCLUDE = '" + includeyn + "', ";
		sql += "DUEDATE = '" + duedate + "', ";
		sql += "FREQUENCY = '" + frequency + "', ";
		sql += "CATEGORY = '" + category + "' ";
		sql += "WHERE ID = '" + id + "' ";

		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doreturn() throws SQLException {
		BankEditForm_3 a;
		a = new BankEditForm_3(bankid, bankdescription);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
	}

	private void populateCategory(ResultSet rs) throws SQLException {
		String category = (rs.getString("Category"));
		comboBoxCategory.setSelectedIndex(0);
		if (category.equals("Auto")) {
			comboBoxCategory.setSelectedIndex(1);
		}
		if (category.equals("Charge Accounts")) {
			comboBoxCategory.setSelectedIndex(2);
		}
		if (category.equals("Computer")) {
			comboBoxCategory.setSelectedIndex(3);
		}
		if (category.equals("Dining")) {
			comboBoxCategory.setSelectedIndex(4);
		}
		if (category.equals("Groceries")) {
			comboBoxCategory.setSelectedIndex(5);
		}
		if (category.equals("Home: Mortgage")) {
			comboBoxCategory.setSelectedIndex(6);
		}
		if (category.equals("Medical")) {
			comboBoxCategory.setSelectedIndex(7);
		}
		if (category.equals("Miscellaneous Expense")) {
			comboBoxCategory.setSelectedIndex(8);
		}
		if (category.equals("Miscellaneous Withdrawal")) {
			comboBoxCategory.setSelectedIndex(9);
		}
		if (category.equals("Service Charge")) {
			comboBoxCategory.setSelectedIndex(10);
		}
		if (category.equals("Utilities")) {
			comboBoxCategory.setSelectedIndex(11);
		}
		if (category.equals("Vacation")) {
			comboBoxCategory.setSelectedIndex(12);
		}
		if (category.equals("Other")) {
			comboBoxCategory.setSelectedIndex(13);
		}
	}

	private String updateCategory() throws SQLException {
		String category = null;
		if (comboBoxCategory.getSelectedIndex() != 0) {
			int index = comboBoxCategory.getSelectedIndex();

			if (index == 1) {
				category = "Auto";
			}
			if (index == 2) {
				category = "Charge Accounts";
			}
			if (index == 3) {
				category = "Computer";
			}
			if (index == 4) {
				category = "Dining";
			}
			if (index == 5) {
				category = "Groceries";
			}
			if (index == 6) {
				category = "Home: Mortgage";
			}
			if (index == 7) {
				category = "Medical";
			}
			if (index == 8) {
				category = "Miscellaneous Expense";
			}
			if (index == 9) {
				category = "Miscellaneous Withdrawal";
			}
			if (index == 10) {
				category = "Service Charge";
			}
			if (index == 11) {
				category = "Utilities";
			}
			if (index == 12) {
				category = "Vacation";
			}
			if (index == 13) {
				category = "Other";
			}
		}

		return category;

	}
}
