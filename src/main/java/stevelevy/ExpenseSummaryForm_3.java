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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ExpenseSummaryForm_3 extends JFrame {

	private JPanel contentPane;
	private JTable Expensetable;
	SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
	String currencyformat = '$' + "0.00";
	DecimalFormat decAmt$Format = new DecimalFormat(currencyformat);
	String bankidparm = null;
	JTextField txtBank = new JTextField();
	JLabel lblBankName = new JLabel("Bank Name");
	private JTable categorytable;
	JRadioButton rdbtnSortbyAmount = new JRadioButton("Sort by amount descending");
	JRadioButton rdbtnSortbyExpense = new JRadioButton("Sort expense description");
	String salary = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseSummaryForm_3 frame = new ExpenseSummaryForm_3(null, null);
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
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ExpenseSummaryForm_3(String id, String acctdesc) throws SQLException, ParseException {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		doCenterForm();
		// Create a JScrollPane
		JScrollPane mainscrollPane = new JScrollPane();
		// Set the contentPane as the viewport view of the JScrollPane
		mainscrollPane.setViewportView(contentPane);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelNorth = new JPanel();
		panelNorth.setBounds(0, 0, 886, 32);
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth);
		panelNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JPanel panelCenter = new JPanel();
		panelCenter.setBounds(0, 32, 886, 775);
		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter);
		panelCenter.setLayout(null);
		;

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 885, 35);
		panelCenter.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bank");
		lblNewLabel.setBounds(10, 10, 75, 14);
		panel_2.add(lblNewLabel);

//		JTextField txtBank = new JTextField();
		txtBank.setBounds(105, 10, 86, 20);
		panel_2.add(txtBank);
		txtBank.setColumns(10);

//		JLabel lblBankName = new JLabel("Bank Name");
		lblBankName.setBounds(256, 10, 200, 14);
		panel_2.add(lblBankName);
		if (id == null) {
			this.bankidparm = "";
			txtBank.setText("");
		} else {
			this.bankidparm = id;
			txtBank.setText(id);
		}

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 35, 885, 40);
		panelCenter.add(panel_3);
		panel_3.setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBank.getText().equals("All")) {
					txtBank.setText("");
				}
				if (txtBank.getText().trim().length() != 0) {
					bankidparm = txtBank.getText();
				} else {
					bankidparm = "";
				}

				try {
					doPopulateExpenses();
					doPopulateCategorySummary();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnSearch.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/search.png")));
		btnSearch.setBounds(10, 11, 200, 25);
		panel_3.add(btnSearch);
		rdbtnSortbyAmount.setSelected(true);

//		JRadioButton rdbtnSortbyPriority = new JRadioButton("Sort by amount descending");
		rdbtnSortbyAmount.setBounds(259, 10, 200, 25);
		panel_3.add(rdbtnSortbyAmount);

//		JRadioButton rdbtnSortbyProject = new JRadioButton("Sort expense description");
		rdbtnSortbyExpense.setBounds(500, 10, 200, 25);
		panel_3.add(rdbtnSortbyExpense);

		ButtonGroup bgroupsort = new ButtonGroup();
		bgroupsort.add(rdbtnSortbyAmount);
		bgroupsort.add(rdbtnSortbyExpense);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int tab = tabbedPane.getSelectedIndex();
				showButtons(tab);
			}
		});
		tabbedPane.setBounds(0, 80, 885, 750);
		panelCenter.add(tabbedPane);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Expense summary", null, panel_4, null);
		panel_4.setLayout(null);
		setBounds(100, 100, 910, 910);
		JScrollPane expensescrollPane = new JScrollPane();
		expensescrollPane.setBounds(5, 10, 875, 680);
		panel_4.add(expensescrollPane);

		Expensetable = new JTable();

		// Assuming you have a JTable named "table"
		JTableHeader header = Expensetable.getTableHeader();

		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
		Expensetable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Bank Id", "Bank Name", "Expense Description", "Category", "Frequency", "Amount" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Expensetable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Expensetable.getColumnModel().getColumn(1).setPreferredWidth(100);
		Expensetable.getColumnModel().getColumn(2).setPreferredWidth(150);
		Expensetable.getColumnModel().getColumn(3).setPreferredWidth(100);
		Expensetable.getColumnModel().getColumn(4).setPreferredWidth(100);
		Expensetable.getColumnModel().getColumn(5).setPreferredWidth(100);
		expensescrollPane.setViewportView(Expensetable);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Category Summary", null, panel_5, null);
		panel_5.setLayout(null);

		JScrollPane categoryscrollPane = new JScrollPane();
		categoryscrollPane.setBounds(0, 0, 875, 800);
		panel_5.add(categoryscrollPane);

		categorytable = new JTable();
		// Assuming you have a JTable named "table"
		header = categorytable.getTableHeader();

		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
		categoryscrollPane.setViewportView(categorytable);
		categorytable.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Category", "Amount" }) {
					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		categorytable.getColumnModel().getColumn(0).setPreferredWidth(75);
		categorytable.getColumnModel().getColumn(1).setPreferredWidth(75);

		JPanel panelSouth = new JPanel();
		panelSouth.setBounds(0, 825, 885, 39);
		panelSouth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelSouth);
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
		lblMenu.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/menu.png")));
		panel.add(lblMenu);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnBankInquiry = new JButton("Bank Inquiry");
		btnBankInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankInquiryForm_2 a = new BankInquiryForm_2();
				a.addWindowListener(new java.awt.event.WindowAdapter() {
				});
				int screen = -1;
				try {
					SMLUtility.showOnScreen(screen, a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.setVisible(true);
				dispose();
			}
		});
		btnBankInquiry.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/bank.png")));
		panel.add(btnBankInquiry);

		JButton btnBankMaintenance = new JButton("Bank Maintenance");
		btnBankMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankEditForm_2 a = null;
				try {
					a = new BankEditForm_2(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.addWindowListener(new java.awt.event.WindowAdapter() {
				});
				int screen = -1;
				try {
					SMLUtility.showOnScreen(screen, a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.setVisible(true);
				dispose();
			}
		});
		btnBankMaintenance.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/bank.png")));
		panel.add(btnBankMaintenance);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(panelCenter);
			}
		});
		btnPrint.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/printer.png")));
		panel.add(btnPrint);

		JButton btnExcel = new JButton("Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/excel.png")));
		panel.add(btnExcel);
		btnClose.setIcon(new ImageIcon(ExpenseSummaryForm_3.class.getResource("/images/close.png")));
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		panel_1.add(lblClassName);

		if (id == null) {
			btnBankMaintenance.setEnabled(false);
		}

		setTitle("Expense summary");
		lblTitle.setText("Expense summary");
		lblInstructions.setText("View balance of Expense Summary");
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		
		doGetSalary();
		doPopulateExpenses();
		doPopulateCategorySummary();
	}

	private void doCenterForm() {
		// TODO Auto-generated method stub
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}

	private void doGetSalary() throws SQLException {
		Double salary = 0.00;
		Double totalsalary = 0.00;
		String name = "";
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * from personalinformation";
		
		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

		while (rs.next()) {
			salary = Double.valueOf(rs.getString("salary"));
			name = (rs.getString("fullname"));
			System.out.println("Individual salary for " + name + "is "+ salary);
			totalsalary = totalsalary + salary;
		}
		
		this.salary = String.valueOf(totalsalary);
		System.out.println("Total salary is : " + this.salary);
	}

	private Double todoubleround(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private void doPopulateExpenses() throws SQLException, ParseException {
		int count = getBankName();
		if (this.bankidparm.isEmpty()) {
			txtBank.setText("All");
			lblBankName.setText("All Banks");
		} else {
			if (count == 0) {
				lblBankName.setText("Invalid Bank");
			}

		}
		ResultSet rs = null;
		String sql = "";
		String bankid = "";
		String savebankid = "";
		String bankname = "";
		String expensedesc = "";
		String amount = "";
		Double work = 0.00;
		Double breakamount = 0.00;
		Double grandamount = 0.00;
		Double incomeamount = 0.00;
		String include = "";
		String frequency = "";
		String income = this.salary;
		String category = "";
		int i = 0;

		sql = "SELECT ";
		sql += "A.BANKID, ";
		sql += "(SELECT MIN(B.DESCRIPTION) FROM PBANKACCOUNT B WHERE A.BANKID = B.ID) AS BANKNAME, ";
		sql += "A.DESCRIPTION, ";
		sql += "(CASE WHEN A.FREQUENCY = 'MONTHLY' THEN A.AMOUNT WHEN A.FREQUENCY = 'YEARLY' THEN A.AMOUNT/12 WHEN A.FREQUENCY = 'QUARTELY' THEN A.AMOUNT/4 WHEN A.FREQUENCY = 'Bimonthly' THEN A.AMOUNT*2 ELSE A.AMOUNT END) AS AMOUNT,  ";
		sql += "A.CATEGORY, A.FREQUENCY, A.INCLUDE ";
		sql += "FROM PEXPENSE A ";
		sql += "WHERE 1 = 1 ";

		if (!this.bankidparm.isEmpty()) {
			sql += "AND A.BANKID = ' " + this.bankidparm + "' ";
		}
//		sql += "ORDER BY A.BANKID, A.DESCRIPTION ";
		
		if (rdbtnSortbyAmount.isSelected()) {
			sql += " ORDER BY A.BANKID, A.AMOUNT DESC ";
		} else {
			sql += " ORDER BY A.BANKID, A.DESCRIPTION ";
		}
		
//		System.out.println("SQL is : " + sql);
		try {
			TableModel model = Expensetable.getModel();
			((DefaultTableModel) model).setRowCount(0);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			Vector rowblank = new Vector();
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");

			Expensetable.setFont(new Font("Tahoma", Font.PLAIN, 8));
			while (rs.next()) {

				bankid = (rs.getString("bankid"));
				if (i == 0) {
					savebankid = bankid;
				}

				i += 1;
				// Do bank totals on change in bank
				if (!bankid.equals(savebankid)) {
					Vector row1 = new Vector();
					row1.add("Bank Total " + savebankid);
					row1.add("");
					row1.add("");
					row1.add("");
					row1.add("");
					row1.add(decAmt$Format.format(Double.valueOf(breakamount)));
					((DefaultTableModel) model).addRow(row1);
					((DefaultTableModel) model).addRow(rowblank);
					savebankid = bankid;
					breakamount = 0.00;
					Expensetable.setFont(new Font("Tahoma", Font.PLAIN, 8));
				}
				bankname = (rs.getString("Bankname"));
				expensedesc = (rs.getString("description"));

				include = (rs.getString("include"));
				frequency = (rs.getString("Frequency"));
				category = (rs.getString("Category"));
				amount = decAmt$Format.format(Double.valueOf((rs.getString("Amount"))));

				work = Double.valueOf((rs.getString("Amount")));
				breakamount = breakamount + work;
				grandamount = grandamount + work;

				Expensetable.setFont(new Font("Tahoma", Font.BOLD, 10));
				Vector row = new Vector();
				row.add(savebankid);
				row.add(bankname);
				row.add(expensedesc);
				row.add(category);
				row.add(frequency);
				row.add(amount);

				((DefaultTableModel) model).addRow(row);
			}
			// Do last bank totals
			Vector row1 = new Vector();
			row1.add("Bank Total " + savebankid);
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add(decAmt$Format.format(Double.valueOf(breakamount)));

			((DefaultTableModel) model).addRow(row1);
			((DefaultTableModel) model).addRow(rowblank);
			// Do grand bank totals
			Vector row2 = new Vector();
			row2.add("Grand Total");
			row2.add("");
			row2.add("");
			row2.add("");
			row2.add("");
			row2.add(decAmt$Format.format(Double.valueOf(grandamount)));
			((DefaultTableModel) model).addRow(row2);

			// Do income
			((DefaultTableModel) model).addRow(rowblank);
			Vector row3 = new Vector();
			row3.add("Income");
			row3.add("");
			row3.add("");
			row3.add("");
			row3.add("");
			row3.add(decAmt$Format.format(Double.valueOf(income)));
			((DefaultTableModel) model).addRow(row3);
			// Do Balance
			((DefaultTableModel) model).addRow(rowblank);
			incomeamount = Double.valueOf((income));
			work = incomeamount - grandamount;

			Vector row4 = new Vector();
			Expensetable.setDefaultRenderer(Object.class, new CellHighlighterRenderer2());
			row4.add("Balance");
			row4.add("");
			row4.add("");
			row4.add("");
			row4.add("");
			row4.add(decAmt$Format.format(Double.valueOf(work)));
			((DefaultTableModel) model).addRow(row4);
			((DefaultTableModel) model).addRow(rowblank);
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private void doPopulateCategorySummary() throws SQLException, ParseException {

		ResultSet rs = null;
		String sql = "";
		String bankid = "";
		String category = "";
		String amount = "";
		Double work = 0.00;
		Double breakamount = 0.00;
		Double grandamount = 0.00;
		Double incomeamount = 0.00;
		String income = this.salary;

		sql = "SELECT ";
		sql += "MIN(A.BANKID) BANKID, A.CATEGORY, ";
		sql += "SUM((CASE WHEN A.FREQUENCY = 'MONTHLY' THEN A.AMOUNT WHEN A.FREQUENCY = 'YEARLY' THEN A.AMOUNT/12 WHEN A.FREQUENCY = 'QUARTELY' THEN A.AMOUNT/4 ELSE A.AMOUNT END)) AS AMOUNT  ";
		sql += "FROM PEXPENSE A ";
		sql += "WHERE 1 = 1 ";
		if (!this.bankidparm.isEmpty()) {
			sql += "AND A.BANKID = ' " + this.bankidparm + "' ";
		}
		sql += "GROUP BY A.CATEGORY ";

		if (rdbtnSortbyAmount.isSelected()) {
			sql += " ORDER BY AMOUNT DESC ";
		} else {
			sql += " ORDER BY A.CATEGORY ";
		}

		try {
			TableModel model = categorytable.getModel();
			((DefaultTableModel) model).setRowCount(0);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			Vector rowblank = new Vector();
			rowblank.add("");
			rowblank.add("");

			categorytable.setFont(new Font("Tahoma", Font.PLAIN, 8));
			while (rs.next()) {

				category = (rs.getString("Category"));
				amount = decAmt$Format.format(Double.valueOf((rs.getString("Amount"))));
				categorytable.setFont(new Font("Tahoma", Font.BOLD, 10));

				work = Double.valueOf((rs.getString("Amount")));
				grandamount = grandamount + work;

				Vector row = new Vector();
				row.add(category);
				row.add(amount);

				((DefaultTableModel) model).addRow(row);
			}
			// Do grand bank totals
			((DefaultTableModel) model).addRow(rowblank);
			Vector row2 = new Vector();
			row2.add("Grand Total");
			row2.add(decAmt$Format.format(Double.valueOf(grandamount)));
			((DefaultTableModel) model).addRow(row2);
			// Do income
			((DefaultTableModel) model).addRow(rowblank);
			Vector row3 = new Vector();
			row3.add("Income");
			row3.add(decAmt$Format.format(Double.valueOf(income)));
			((DefaultTableModel) model).addRow(row3);
			// Do Balance
			((DefaultTableModel) model).addRow(rowblank);
			incomeamount = Double.valueOf((income));
			work = incomeamount - grandamount;
			Vector row4 = new Vector();
			if (work < 0) {
				setBackground(new Color(255, 127, 127));
			} else {
				setBackground(Color.green);
			}
			row4.add("Balance");
			row4.add(decAmt$Format.format(Double.valueOf(work)));
			((DefaultTableModel) model).addRow(row4);
			((DefaultTableModel) model).addRow(rowblank);

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private int getBankName() throws ParseException {
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		sql = "SELECT ";
		sql += "ID, BANK, DESCRIPTION ";
		sql += "FROM PBANKACCOUNT ";
		sql += "WHERE 1 = 1 ";

		if (!bankidparm.isEmpty()) {
			sql += "AND ID = '" + bankidparm + "' ";
		}

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				String name = (rs.getString("DESCRIPTION"));
				lblBankName.setText(name);
				count += 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	private void doMenu() throws SQLException {
		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = Expensetable.getModel();
		// write the column headers
		row = sheet.createRow(0);
		for (int c = 0; c < model.getColumnCount(); c++) {
			cell = row.createCell(c);
			cell.setCellValue(model.getColumnName(c));
		}
		// write the data rows
		for (int r = 0; r < model.getRowCount(); r++) {
			row = sheet.createRow(r + 1);
			for (int c = 0; c < model.getColumnCount(); c++) {
				cell = row.createCell(c);
				Object value = model.getValueAt(r, c);
				if (value instanceof String) {
					cell.setCellValue((String) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				}
			}
		}

		String filePath = "S:\\Users\\slevy\\OneDrive\\Desktop/ExpenseSummary.xlsx";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void showButtons(int tab) {
		if (tab == 0) {
			rdbtnSortbyAmount.setVisible(true);
			rdbtnSortbyExpense.setVisible(true);
		} else {
			rdbtnSortbyAmount.setVisible(true);
			rdbtnSortbyExpense.setVisible(true);
		}

	}
}
