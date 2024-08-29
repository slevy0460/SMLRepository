package stevelevy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class BankInuquiryForm_3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
	String className = getClass().getSimpleName();
	private static JTable BankAccountSummaryTable;
	private JTable BankAccountTable;
	private JTextField txtAccountAdd;
	private JTextField txtTypeAdd;
	private JTextField txtDescriptionAdd;
	private JTextField txtNumberAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankInuquiryForm_3 frame = new BankInuquiryForm_3();
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

	public BankInuquiryForm_3() {
		// Set the icon image
		Image icon = Toolkit.getDefaultToolkit().getImage("/images/bank.png");
		setIconImage(icon);
		setTitle("Bank Inquiry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1500, 800);
		// Initialize the contentPane
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create a JScrollPane
		JScrollPane mainscrollPane = new JScrollPane();
		// Set the contentPane as the viewport view of the JScrollPane
		mainscrollPane.setViewportView(contentPane);

		// Create a top panel with a preferred height
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(1500, 50));
		// Add the top panel to the North region of the BorderLayout
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		// Title of Form
		JLabel lblTitle = new JLabel("Title of Form");
		lblTitle.setText("Bank Inquiry");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setPreferredSize(new Dimension(50, 25));
		northPanel.add(lblTitle, BorderLayout.NORTH);
		// Instructions of form
		JLabel lblInstructions = new JLabel("Use this form to view Bankk Account Summary");
		lblInstructions.setForeground(new Color(0, 0, 0));
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions.setPreferredSize(new Dimension(50, 25));
		northPanel.add(lblInstructions, BorderLayout.CENTER);

		centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// Set your tab icon
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/menu.png")));
		contentPane.add(centertabbedPane, BorderLayout.CENTER);

		// Set the custom panel as the tab component
		centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		JPanel tab1 = new JPanel();
		centertabbedPane.addTab("Bank summary", null, tab1, null);
		tab1.setLayout(null);

		JScrollPane BankAccountSummaryScrollPane = new JScrollPane();
		BankAccountSummaryScrollPane.setBounds(0, 5, 1475, 197);
		tab1.add(BankAccountSummaryScrollPane);
		// Add table
		BankAccountSummaryTable = new JTable();
		BankAccountSummaryTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		BankAccountSummaryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						doEdit();
						doPopulateBankSummary();
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
		// Assuming you have a JTable named "table"
		JTableHeader header = BankAccountSummaryTable.getTableHeader();
		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
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
		// Set the button panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setSize(1500, 50);
		buttonpanel.setLocation(0, 200);
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tab1.add(buttonpanel, BorderLayout.NORTH);

		JButton btnExpenseSummary = new JButton("Expense Summary");
		btnExpenseSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpenseSummaryForm_3 a = null;
				try {
					a = new ExpenseSummaryForm_3(null, null);
					int screen = -1;
					SMLUtility.showOnScreen(screen, a);
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

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPopulateBankSummary();
			}
		});
		btnRefresh.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/refresh.png")));
		buttonpanel.add(btnRefresh);
		btnExpenseSummary.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/expense-16.png")));
		btnExpenseSummary.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonpanel.add(btnExpenseSummary);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doEdit();
					doPopulateBankSummary();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonpanel.add(btnEdit);

		JButton btnExcel = new JButton("Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab1);
			}
		});
		btnPrint.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/printer.png")));
		buttonpanel.add(btnPrint);
		btnExcel.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/close.png")));
		btnExcel.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonpanel.add(btnExcel);

		JPanel tab2 = new JPanel();
		centertabbedPane.addTab("Bank Accounts", null, tab2, null);
		tab2.setLayout(null);

		JPanel panel_1_tab2 = new JPanel();
		panel_1_tab2.setLayout(null);
		panel_1_tab2.setBounds(0, 0, 1500, 179);
		tab2.add(panel_1_tab2);

		JScrollPane BankAccountScrollPane = new JScrollPane();
		BankAccountScrollPane.setBounds(0, 0, 1500, 175);
		panel_1_tab2.add(BankAccountScrollPane);

		BankAccountTable = new JTable();
		BankAccountTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						doEdit();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		BankAccountScrollPane.setViewportView(BankAccountTable);
		BankAccountTable.setFont(new Font("Tahoma", Font.BOLD, 12));

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

		JPanel buttonpanel_1 = new JPanel();
		buttonpanel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonpanel_1.setBounds(0, 251, 1500, 50);
		tab2.add(buttonpanel_1);

		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPopulateBankAccounts();
			}
		});
		btnRefresh_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRefresh_1.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/refresh.png")));
		buttonpanel_1.add(btnRefresh_1);

		JButton btnExpenseSummary_1 = new JButton("Expense Summary");
		btnExpenseSummary_1.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/expense-16.png")));
		btnExpenseSummary_1.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel_1.add(btnExpenseSummary_1);

		JButton btnPrint_1 = new JButton("Print");
		btnPrint_1.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/printer.png")));
		btnPrint_1.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel_1.add(btnPrint_1);

		JButton btnExcel_1 = new JButton("Excel");
		btnExcel_1.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/excel.png")));
		btnExcel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel_1.add(btnExcel_1);

		JPanel panel = new JPanel();
		panel.setBounds(0, 180, 1500, 60);
		tab2.add(panel);
		panel.setLayout(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAddBankAccount();
			}
		});
		btnAdd.setIcon(new ImageIcon(BankInuquiryForm_3.class.getResource("/images/plus.png")));
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.setBounds(10, 20, 150, 25);
		panel.add(btnAdd);

		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(175, 25, 75, 13);
		panel.add(lblNewLabel);

		txtAccountAdd = new JTextField();
		txtAccountAdd.setBounds(240, 20, 200, 19);
		panel.add(txtAccountAdd);
		txtAccountAdd.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblType.setBounds(458, 25, 75, 13);
		panel.add(lblType);

		txtTypeAdd = new JTextField();
		txtTypeAdd.setColumns(10);
		txtTypeAdd.setBounds(523, 20, 200, 19);
		panel.add(txtTypeAdd);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDescription.setBounds(739, 25, 75, 13);
		panel.add(lblDescription);

		txtDescriptionAdd = new JTextField();
		txtDescriptionAdd.setColumns(10);
		txtDescriptionAdd.setBounds(804, 20, 200, 19);
		panel.add(txtDescriptionAdd);

		JLabel lblNumber = new JLabel("Account#");
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumber.setBounds(1023, 25, 75, 13);
		panel.add(lblNumber);

		txtNumberAdd = new JTextField();
		txtNumberAdd.setColumns(10);
		txtNumberAdd.setBounds(1088, 20, 200, 19);
		panel.add(txtNumberAdd);
		BankAccountTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		BankAccountTable.getColumnModel().getColumn(0).setMinWidth(0);
		BankAccountTable.getColumnModel().getColumn(0).setMaxWidth(0);
		BankAccountTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		BankAccountTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		BankAccountTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		BankAccountTable.getColumnModel().getColumn(4).setPreferredWidth(110);
		BankAccountTable.getColumnModel().getColumn(5).setPreferredWidth(150);

		// Set the bottom panel
		JPanel bottompanel = new JPanel();
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.setPreferredSize(new Dimension(1500, 50));
		contentPane.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));
		// Set the class panel
		JPanel classpanel = new JPanel();
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		classpanel.setPreferredSize(new Dimension(1500, 40));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setText(className);
		lblClassName.setPreferredSize(new Dimension(200, 14));
		lblClassName.setForeground(Color.BLUE);
		classpanel.add(lblClassName);

		// Set the JScrollPane as the contentPane of the JFrame
		setContentPane(mainscrollPane);

		// Center form on monitor
		doCenterForm();
		// call doPopulateTable at the end of the constructor
		doPopulateBankSummary();
		doPopulateBankAccounts();
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

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
		sql += "ID, BANK, TYPE, DESCRIPTION, ACCOUNT, CURRENCY, ";
		sql += "(CASE ACTIVE WHEN 'N' THEN 'Not Active' ELSE 'Active' END ) ACTIVE ";
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

			BankEditForm_3 a;
			a = new BankEditForm_3(id, acctdesc);
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			int screen = -1;
//			SMLUtility.showOnScreen(screen, a);
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

	private void doAddBankAccount() {
		String sql = null;
		ResultSet rs = null;

		sql = "INSERT INTO PBANKACCOUNT (BANK, TYPE, DESCRIPTION, ACCOUNT, CURRENCY, STARTINGBALANCE, DEBIT, CREDIT, OUTGOING, INCOMING, ENDINGBALANCE, ACTIVE) ";
		sql += "VALUES ('" + txtAccountAdd.getText() + "', ";
		sql += "'" + txtTypeAdd.getText() + "', ";
		sql += "'" + txtDescriptionAdd.getText() + "', ";
		sql += "'" + txtNumberAdd.getText() + "', ";
		sql += "'$', ";
		sql += "0.00, ";
		sql += "0.00, ";
		sql += "0.00, ";
		sql += "0.00, ";
		sql += "0.00, ";
		sql += "0.00, ";
		sql += "' ')";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
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
