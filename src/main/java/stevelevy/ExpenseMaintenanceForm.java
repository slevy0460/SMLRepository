package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ExpenseMaintenanceForm extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseMaintenanceForm frame = new ExpenseMaintenanceForm(null, null, null, null);
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
	 * @param acctdesc
	 * @param id
	 * @param expensedesc
	 * @param expenseid
	 * @throws ParseException
	 */
	public ExpenseMaintenanceForm(String bankid, String bankdesc, String expenseid, String expensedesc)
			throws ParseException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Expense Maintenance Form");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Use this form to edit Expences");
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel lblExpenseId = new JLabel("Expense Id");
		lblExpenseId.setBounds(10, 50, 100, 14);
		panelCenter.add(lblExpenseId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(150, 50, 50, 20);
		panelCenter.add(txtId);
		txtId.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 90, 100, 14);
		panelCenter.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setBounds(150, 90, 200, 20);
		panelCenter.add(txtDescription);
		txtDescription.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 170, 100, 14);
		panelCenter.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setBounds(150, 170, 86, 20);
		panelCenter.add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblBankid = new JLabel("Bank Id");
		lblBankid.setBounds(10, 10, 100, 15);
		panelCenter.add(lblBankid);

		txtBankId = new JTextField();
		txtBankId.setBounds(150, 10, 86, 20);
		panelCenter.add(txtBankId);
		txtBankId.setColumns(10);

		JLabel lblNewLabel = new JLabel("Due Date");
		lblNewLabel.setBounds(10, 130, 100, 14);
		panelCenter.add(lblNewLabel);

//		JCheckBox chckbxInclude = new JCheckBox("Include Payment");
		chckbxInclude.setBounds(150, 290, 180, 23);
		panelCenter.add(chckbxInclude);

//		JDateChooser jdatechosserDueDdate = new JDateChooser();
		jdatechosserDueDdate.setDateFormatString("MM/dd/yy");
		jdatechosserDueDdate.setBounds(150, 130, 150, 20);
		panelCenter.add(jdatechosserDueDdate);

//		JComboBox comboBoxFrequency = new JComboBox();
		comboBoxFrequency.setModel(new DefaultComboBoxModel(new String[] {"", "Monthly", "Yearly", "Quartely", "Bimonthly", "Once"}));
		comboBoxFrequency.setBounds(150, 210, 180, 22);
		panelCenter.add(comboBoxFrequency);

		JLabel lblNewLabel_1 = new JLabel("Frequency");
		lblNewLabel_1.setBounds(10, 210, 100, 14);
		panelCenter.add(lblNewLabel_1);

//		JComboBox comboBoxCategory = new JComboBox();
		comboBoxCategory.setModel(new DefaultComboBoxModel(new String[] {"", "Auto", "Charge Accounts", "Computer", "Dining", "Groceries", "Home: Mortgage", "Medical", "Miscellaneous Expense", "Miscellaneous Withdrawal", "Service Charge", "Utilities", "Vacation", "Other"}));
		comboBoxCategory.setBounds(150, 250, 180, 22);
		panelCenter.add(comboBoxCategory);

		JLabel lblNewLabel_2 = new JLabel("Category");
		lblNewLabel_2.setBounds(10, 250, 100, 14);
		panelCenter.add(lblNewLabel_2);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelSouth.add(panel, BorderLayout.CENTER);

		JButton lblMenu = new JButton("Menu");
		lblMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doMenu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblMenu.setIcon(new ImageIcon(ExpenseMaintenanceForm.class.getResource("/images/menu.png")));
		panel.add(lblMenu);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
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
		btnUpdate.setIcon(new ImageIcon(ExpenseMaintenanceForm.class.getResource("/images/updated.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnUpdate);

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankInquiryForm_2 a = new BankInquiryForm_2();
				a.addWindowListener(new java.awt.event.WindowAdapter() {
				});
				a.setVisible(true);
				dispose();
			}
		});
		btnInquiry.setIcon(new ImageIcon(ExpenseMaintenanceForm.class.getResource("/images/task-planning.png")));
		btnInquiry.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(btnInquiry);

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
		btnReturn.setIcon(new ImageIcon(ExpenseMaintenanceForm.class.getResource("/images/left-arrow.png")));
		panel.add(btnReturn);
		btnClose.setIcon(new ImageIcon(ExpenseMaintenanceForm.class.getResource("/images/close.png")));
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		panel_1.add(lblClassName);

		setTitle("Expense Maintenance Form");
		lblTitle.setText("Expense Maintenance Form");
		lblInstructions.setText("Use this form to edit Expences");
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		this.bankid = bankid;
		this.bankdescription = bankdesc;

		doCenterForm();
		if (expenseid == null) {
			btnUpdate.setText("Add");
		}
		txtBankId.setText(bankid);
		doPopulate(expenseid);

	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doMenu() throws SQLException {
		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
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
				txtAmount.setText(rs.getString("amount"));
				txtBankId.setText(rs.getString("Bankid"));
				date = (rs.getString("Duedate"));

				java.util.Date workdate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
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
		
		SimpleDateFormat sdfParse = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("MMMM dd yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String date = sdfParse.format(jdatechosserDueDdate.getDate());

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
				frequency = "Monthly";
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
		sql += "(DESCRIPTION, AMOUNT, BANKID, INCLUDE, DUEDATE, FREQUENCY, CATEGORY ) ";
		sql += "VALUES (";
		sql += " '" + txtDescription.getText() + " ' , ";
		sql += " '" + amount + " ' , ";
		sql += " '" + txtBankId.getText() + "',  ";
		sql += " '" + includeyn + " ' , ";
		sql += " '" + date + " ' , ";
		sql += " '" + frequency + "' , ";
		sql += " '" + category + " ')  ";

//		System.out.println("SQL is : " + sql);

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
		if (chckbxInclude.isSelected()) {
			includeyn = "Yes";
		} else {
			includeyn = "No";
		}
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		String date = yyyymmdd.format(jdatechosserDueDdate.getDate());

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
		sql += "AMOUNT = '" + amount + "', ";
		sql += "BANKID = '" + txtBankId.getText() + "', ";
		sql += "INCLUDE = '" + includeyn + "', ";
		sql += "DUEDATE = '" + date + "', ";
		sql += "FREQUENCY = '" + frequency + "', ";
		sql += "CATEGORY = '" + category + "' ";
		sql += "WHERE ID = '" + id + "' ";

//		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doreturn() throws SQLException {
		BankEditForm_2 a;
		a = new BankEditForm_2(bankid, bankdescription);
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
