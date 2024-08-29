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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

public class BankInquiryForm_2 extends JFrame {

	private JPanel contentPane;
	private static JTable BankAccountSummaryTable;
	private JTable BankAccountTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankInquiryForm_2 frame = new BankInquiryForm_2();
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
	public BankInquiryForm_2() {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(BankInquiryForm_2.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Bank summary", null, tab1, null);
		tab1.setLayout(null);

		JScrollPane BankAccountSummaryScrollPane = new JScrollPane();
		BankAccountSummaryScrollPane.setBounds(0, 5, 1500, 325);
		tab1.add(BankAccountSummaryScrollPane);

		BankAccountSummaryTable = new JTable();
		// Assuming you have a JTable named "table"
		JTableHeader header = BankAccountSummaryTable.getTableHeader();
		// Set the foreground color
		header.setForeground(Color.RED);
		// Set the background color
		header.setBackground(Color.CYAN);
		BankAccountSummaryTable.setFont(new Font("Tahoma", Font.BOLD, 11));
		BankAccountSummaryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						doEdit();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		BankAccountSummaryTable.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Bank Id", "Account", "Starting Balance", "Debits/Withdrawals", "Credits/Deposits",
						"Available Balance", "Outgoing Money", "Incoming Money", "Monthly Payments",
						"Ending Balance" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		BankAccountSummaryTable.getColumnModel().getColumn(0).setMinWidth(80);
		BankAccountSummaryTable.getColumnModel().getColumn(0).setMaxWidth(80);
		BankAccountSummaryTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		BankAccountSummaryTable.getColumnModel().getColumn(1).setPreferredWidth(170);
		BankAccountSummaryTable.getColumnModel().getColumn(2).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(3).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(4).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(5).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(6).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(7).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(8).setPreferredWidth(125);
		BankAccountSummaryTable.getColumnModel().getColumn(9).setPreferredWidth(125);

		BankAccountSummaryScrollPane.setViewportView(BankAccountSummaryTable);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		BankAccountSummaryScrollPane.setColumnHeaderView(internalFrame);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1378, 35);
		tab1.add(panel_2);
		
		JButton btnExpenseSummary_1 = new JButton("Expense Summary");
		panel_2.add(btnExpenseSummary_1);
		
		JButton lblEdit_1 = new JButton("Edit");
		panel_2.add(lblEdit_1);
		
		JButton btnPrint_2 = new JButton("Print");
		panel_2.add(btnPrint_2);
		
		JButton btnExcel_1 = new JButton("Excel");
		panel_2.add(btnExcel_1);
		
		JButton btnClose_1 = new JButton("Close");
		panel_2.add(btnClose_1);
		internalFrame.setVisible(true);

		JPanel tab2 = new JPanel();
		tabbedPane.addTab("Bank Accounts", null, tab2, null);
		tab2.setLayout(null);

		JPanel panel_1_tab2 = new JPanel();
		panel_1_tab2.setBounds(0, 0, 1227, 50);
		tab2.add(panel_1_tab2);

		JPanel panel_2_tab2 = new JPanel();
		panel_2_tab2.setBounds(0, 52, 1227, 217);
		tab2.add(panel_2_tab2);
		panel_2_tab2.setLayout(null);

		JScrollPane BankAccountScrollPane = new JScrollPane();
		BankAccountScrollPane.setBounds(0, 0, 1223, 175);
		panel_2_tab2.add(BankAccountScrollPane);

		BankAccountTable = new JTable();
		BankAccountTable.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);

		BankAccountTable
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
						new String[] { "Bank ID", "Bank (double click to edit)", "Type (double click to edit)",
								"Description (double click to edit)", "Account (double click to edit)",
								"Currency (double click to edit)", "Active (double click to edit)" }));
		BankAccountTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		BankAccountTable.getColumnModel().getColumn(0).setMinWidth(0);
		BankAccountTable.getColumnModel().getColumn(0).setMaxWidth(0);
		BankAccountTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		BankAccountTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		BankAccountTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		BankAccountTable.getColumnModel().getColumn(4).setPreferredWidth(110);
		BankAccountTable.getColumnModel().getColumn(5).setPreferredWidth(150);

		BankAccountScrollPane.setViewportView(BankAccountTable);

		JPanel panel_3_tab2 = new JPanel();
		panel_3_tab2.setBounds(0, 219, 965, 50);
		tab2.add(panel_3_tab2);

		JPanel panel_4_tab2 = new JPanel();
		panel_4_tab2.setBounds(0, 266, 1127, 65);
		tab2.add(panel_4_tab2);

		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSaveTab2();
			}
		});
		btnSave_1.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/updated.png")));
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4_tab2.add(btnSave_1);

		JButton btnPrint_1 = new JButton("Print");
		btnPrint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab2);
			}
		});
		btnPrint_1.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/printer.png")));
		btnPrint_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4_tab2.add(btnPrint_1);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelSouth.add(panel, BorderLayout.CENTER);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab1);
			}
		});

		JButton lblEdit = new JButton("Edit");
		lblEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doEdit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnExpenseSummary = new JButton("Expense Summary");
		btnExpenseSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpenseSummaryForm_2 a = null;
				try {
					a = new ExpenseSummaryForm_2(null, null);
				} catch (SQLException | ParseException e1) {
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
		btnExpenseSummary.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/expense-16.png")));
		panel.add(btnExpenseSummary);
		lblEdit.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/edit.png")));
		panel.add(lblEdit);
		btnPrint.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/printer.png")));
		panel.add(btnPrint);

		JButton btnExcel = new JButton("Excel");
		btnExcel.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/excel.png")));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		panel.add(btnExcel);
		btnClose.setIcon(new ImageIcon(BankInquiryForm_2.class.getResource("/images/close.png")));
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		panel_1.add(lblClassName);

		setTitle("Bank Inquiry");
		lblTitle.setText("Bank Inquiry");
		lblInstructions.setText("Use this form to view Bank Account Summary");
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		lblClassName.setForeground(Color.BLUE);

		doCenterForm();
		doPopulateBankSummary();
		doPopulateBankAccounts();
	}

	private void doCenterForm() {
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
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

	private static void doPopulateBankSummary() {

		String sql = null;
		String bankid;
		String description;
		String startingbalance;
		String debits;
		String credits;
		String availablebalance = null;
		String incoming;
		String outgoing;
		String monthlypayments = null;
		String endingbalance;

		sql = "SELECT ";
		sql += "A.ID, A.DESCRIPTION, A.STARTINGBALANCE, A.DEBIT, A.CREDIT, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT) AS DECIMAL (8,2))  AVAILABLEBALANCE, ";
		sql += "A.OUTGOING, A.INCOMING, ";
		sql += "(SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE ='Yes') MONTHLYPAYMENTS, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT - A.OUTGOING + A.INCOMING  - (SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE = 'Yes')) AS DECIMAL (8,2)) ENDINGBALANCE ";
		sql += "FROM PBANKACCOUNT A ";
		sql += "WHERE A.ACTIVE <> 'N'";
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");
		try {

			TableModel model = BankAccountSummaryTable.getModel();

			((DefaultTableModel) model).setRowCount(0);
			ResultSet rs = null;
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				bankid = (rs.getString("id"));
				description = (rs.getString("description"));
				startingbalance = decAmt$Format.format(rs.getDouble("startingbalance"));
				debits = decAmt$Format.format(rs.getDouble("debit"));
				credits = decAmt$Format.format(rs.getDouble("credit"));
				availablebalance = decAmt$Format.format(rs.getDouble("availablebalance"));
				outgoing = decAmt$Format.format(rs.getDouble("outgoing"));
				incoming = decAmt$Format.format(rs.getDouble("incoming"));
				monthlypayments = decAmt$Format.format(rs.getDouble("monthlypayments"));
				endingbalance = decAmt$Format.format(rs.getDouble("endingbalance"));

				Vector row = new Vector();

				BankAccountSummaryTable.setDefaultRenderer(Object.class, new CellHighlighterRenderer());

				row.add(bankid);
				row.add(description);
				row.add(startingbalance);
				row.add(debits);
				row.add(credits);
				row.add(availablebalance);
				row.add(outgoing);
				row.add(incoming);
				row.add(monthlypayments);
				row.add(endingbalance);

				((DefaultTableModel) model).addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doPopulateBankAccounts() {
		String sql = null;
		String bankid;
		String bank;
		String type;
		String description;
		String account;
		String currency;
		String active;

		sql = "SELECT ";
		sql += "ID, BANK, TYPE, DESCRIPTION, ACCOUNT, CURRENCY,  ";
		sql += "(CASE ACTIVE WHEN 'N' THEN 'Not Active' ELSE 'Active'  END ) ACTIVE ";
		sql += "FROM PBANKACCOUNT ";
		sql += "ORDER BY ID";

		try {
			TableModel model = BankAccountTable.getModel();

			((DefaultTableModel) model).setRowCount(0);
			ResultSet rs = null;
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				bankid = (rs.getString("id"));
				bank = (rs.getString("bank"));
				type = (rs.getString("type"));
				description = (rs.getString("description"));
				account = (rs.getString("account"));
				currency = (rs.getString("currency"));
				active = (rs.getString("active"));

				Vector row = new Vector();

				row.add(bankid);
				row.add(bank);
				row.add(type);
				row.add(description);
				row.add(account);
				row.add(currency);
				row.add(active);

				((DefaultTableModel) model).addRow(row);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doEdit() throws SQLException {

		int column = 0;
		int row = BankAccountSummaryTable.getSelectedRow();
		if (row >= 0) {
			String id = BankAccountSummaryTable.getModel().getValueAt(row, column).toString();
			String acctdesc = BankAccountSummaryTable.getModel().getValueAt(row, 1).toString();

			BankEditForm_2 a;
			a = new BankEditForm_2(id, acctdesc);
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			int screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		} else {

			JOptionPane.showMessageDialog(null, "Please select a row to edit");

		}
	}

	private void doSaveTab2() {
		DefaultTableModel model = (DefaultTableModel) BankAccountTable.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {

			tableData[i][0] = model.getValueAt(i, 0);
			String bankid = String.valueOf(tableData[i][0]);
			tableData[i][1] = model.getValueAt(i, 1);
			String bank = String.valueOf(tableData[i][1]);
			tableData[i][2] = model.getValueAt(i, 2);
			String type = String.valueOf(tableData[i][2]);
			tableData[i][3] = model.getValueAt(i, 3);
			String description = String.valueOf(tableData[i][3]);
			tableData[i][4] = model.getValueAt(i, 4);
			String account = String.valueOf(tableData[i][4]);
			tableData[i][5] = model.getValueAt(i, 5);
			String currency = String.valueOf(tableData[i][5]);
			tableData[i][6] = model.getValueAt(i, 6);
			String active = String.valueOf(tableData[i][6]);

			doUpdateBankAccount(bankid, bank, type, description, account, currency, active);
		}

	}

	private void doUpdateBankAccount(String bankid, String bank, String type, String description, String account,
			String currency, String active) {
		String sql = null;
		ResultSet rs = null;

		if (active.equals("Not Active")) {
			active = "N";
		} else {
			active = " ";
		}

		sql = "UPDATE PBANKACCOUNT ";
		sql += "SET bank = '" + bank + "', ";
		sql += "type = '" + type + "', ";
		sql += "description = '" + description + "', ";
		sql += "account = '" + account + "', ";
		sql += "currency = '" + currency + "', ";
		sql += "active = '" + active + "' ";
		sql += "WHERE ID = '" + bankid + "' ";

		DecimalFormat decAmt$Format = new DecimalFormat("0.00");

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
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

	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = BankAccountSummaryTable.getModel();
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

		String filePath = "S:\\Users\\slevy\\OneDrive\\Desktop/BankSummary.xlsx";
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
}
