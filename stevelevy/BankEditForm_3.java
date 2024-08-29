package stevelevy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BankEditForm_3 extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable BankExpenseTable;
	String bankid;
	String bankdesc;
	JRadioButton rdbtnSortbydate = new JRadioButton("Date");
	JRadioButton rdbtnSortbyid = new JRadioButton("Id");
	String field = null;
	String value = null;
	boolean isBoolean = false;
	String className = getClass().getSimpleName();

	private JTextField txtEndingBalance;
	private JTextField txtDebit;
	private JTextField txtCredit;
	private JTextField txtOutgoing;
	private JTextField txtIncoming;
	private JTextField txtAvailableBalance;
	private JTextField txtMonthlyPayments;

	JComboBox cmbAccounts;

	private JTextField txtCurrencySymbol;
	private JTextField txtCurrencyRate;
	private JTextField txtStartingBalance;
	private JTable CurrencyTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BankEditForm_3 dialog = new BankEditForm_3("1", "Test");
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BankEditForm_3(String id, String acctdesc) {

		try {
			int screen = SMLUtility.getCurrentMonitorInfo(BankEditForm_3.this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doCheck("GET");
		setTitle("Bank Expense Edit/Maintenance");
		setBounds(100, 100, 1500, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		// Create a JScrollPane and add the contentPanel to it
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(800, 50));
		contentPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Bank Expense Edit/Maintenance");
		lblTitle.setPreferredSize(new Dimension(50, 25));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		northPanel.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Use this form to edit Bank Expences");
		lblInstructions.setPreferredSize(new Dimension(50, 25));
		lblInstructions.setForeground(Color.BLACK);
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanel.add(lblInstructions, BorderLayout.SOUTH);

		JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(centertabbedPane, BorderLayout.CENTER);

		JPanel tab1 = new JPanel();
		centertabbedPane.addTab(acctdesc, null, tab1, null);
		tab1.setLayout(new BorderLayout(0, 0));

		JPanel bankselectionpanel = new JPanel();
		bankselectionpanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Bank Selection", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		bankselectionpanel.setPreferredSize(new Dimension(200, 500));
		tab1.add(bankselectionpanel, BorderLayout.EAST);
		bankselectionpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnWellsFargoChecking = new JButton("Wells Fargo Checking ");
		btnWellsFargoChecking.setIconTextGap(5); // Set the desired gap (in pixels)		
		btnWellsFargoChecking.setVerticalTextPosition(SwingConstants.CENTER);
		btnWellsFargoChecking.setHorizontalTextPosition(SwingConstants.LEFT);
		btnWellsFargoChecking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id = "1";
				String acctdesc = "Wells Fargo Checking";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWellsFargoChecking.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnWellsFargoChecking.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnWellsFargoChecking.setPreferredSize(new Dimension(200, 30));
		bankselectionpanel.add(btnWellsFargoChecking);

		JButton btnWellsFargoSavings = new JButton("Wells Fargo Savings  ");
		btnWellsFargoSavings.setIconTextGap(5); // Set the desired gap (in pixels)		
		btnWellsFargoSavings.setVerticalTextPosition(SwingConstants.CENTER);
		btnWellsFargoSavings.setHorizontalTextPosition(SwingConstants.LEFT);
		btnWellsFargoSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "2";
				String acctdesc = "Wells Fargo Savings";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWellsFargoSavings.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnWellsFargoSavings.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnWellsFargoSavings.setPreferredSize(new Dimension(200, 30));
		bankselectionpanel.add(btnWellsFargoSavings);

		JButton btnWellsFargoInsurance = new JButton("Wells Fargo Insurance");
		btnWellsFargoInsurance.setVerticalTextPosition(SwingConstants.CENTER);
		btnWellsFargoInsurance.setHorizontalTextPosition(SwingConstants.LEFT);
		btnWellsFargoInsurance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "3";
				String acctdesc = "Wells Fargo Insurance";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWellsFargoInsurance.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnWellsFargoInsurance.setIconTextGap(5); // Set the desired gap (in pixels)
		btnWellsFargoInsurance.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnWellsFargoInsurance.setPreferredSize(new Dimension(200, 30));
		bankselectionpanel.add(btnWellsFargoInsurance);

		JButton btnScotiabankSavings = new JButton("Scotiabank Savings   ");
		btnScotiabankSavings.setIconTextGap(5); // Set the desired gap (in pixels)
		btnScotiabankSavings.setVerticalTextPosition(SwingConstants.CENTER);
		btnScotiabankSavings.setHorizontalTextPosition(SwingConstants.LEFT);
		btnScotiabankSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "5";
				String acctdesc = "Scotiabank Savings";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnScotiabankSavings.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnScotiabankSavings.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnScotiabankSavings.setPreferredSize(new Dimension(200, 30));
		bankselectionpanel.add(btnScotiabankSavings);

		JButton btnScotiabankColones = new JButton("Scotiabank Colones  ");
		btnScotiabankColones.setIconTextGap(5); // Set the desired
		btnScotiabankColones.setVerticalTextPosition(SwingConstants.CENTER);
		btnScotiabankColones.setHorizontalTextPosition(SwingConstants.LEFT);
		btnScotiabankColones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "6";
				String acctdesc = "Scotiabank Colones   ";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnScotiabankColones.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnScotiabankColones.setFont(new Font("Courier New", Font.PLAIN, 10));
		btnScotiabankColones.setPreferredSize(new Dimension(200, 30));
		bankselectionpanel.add(btnScotiabankColones);

		JButton btnBacSavings = new JButton("BAC Savings         ");
		btnBacSavings.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bank.png")));
		btnBacSavings.setIconTextGap(5); // Set the desired
		btnBacSavings.setVerticalTextPosition(SwingConstants.CENTER);
		btnBacSavings.setHorizontalTextPosition(SwingConstants.LEFT);
		btnBacSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "7";
				String acctdesc = "BAC Savings";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBacSavings.setPreferredSize(new Dimension(200, 30));
		btnBacSavings.setFont(new Font("Courier New", Font.PLAIN, 10));
		bankselectionpanel.add(btnBacSavings);

		JPanel accountbalancepanel = new JPanel();
		accountbalancepanel.setBorder(new TitledBorder(null, "Account Balances", TitledBorder.CENTER, TitledBorder.TOP,
				null, new Color(0, 0, 255)));
		accountbalancepanel.setPreferredSize(new Dimension(300, 450));
		tab1.add(accountbalancepanel, BorderLayout.WEST);
		accountbalancepanel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Starting Balance");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_4.setBounds(8, 23, 145, 14);
		accountbalancepanel.add(lblNewLabel_4);

		txtEndingBalance = new JTextField();
		txtEndingBalance.setText("0.00");
		txtEndingBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEndingBalance.setColumns(10);
		txtEndingBalance.setBounds(161, 32, 98, 20);
		accountbalancepanel.add(txtEndingBalance);

		JLabel lblNewLabel_2 = new JLabel("Debits/Withdrawals");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(8, 51, 145, 14);
		accountbalancepanel.add(lblNewLabel_2);

		txtDebit = new JTextField();
		txtDebit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDebit.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtDebit.setText("0.00");
		txtDebit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDebit.setColumns(10);
		txtDebit.setBounds(163, 51, 98, 20);
		accountbalancepanel.add(txtDebit);

		JLabel lblNewLabel_3 = new JLabel("Credits/Deposits");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_3.setBounds(8, 79, 145, 14);
		accountbalancepanel.add(lblNewLabel_3);

		txtCredit = new JTextField();
		txtCredit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCredit.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtCredit.setText("0.00");
		txtCredit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCredit.setColumns(10);
		txtCredit.setBounds(163, 79, 98, 20);
		accountbalancepanel.add(txtCredit);

		JLabel lblNewLabel_5 = new JLabel("Avaiable Balance");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(8, 107, 145, 14);
		accountbalancepanel.add(lblNewLabel_5);

		txtAvailableBalance = new JTextField();
		txtAvailableBalance.setText("0.00");
		txtAvailableBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAvailableBalance.setEditable(false);
		txtAvailableBalance.setColumns(10);
		txtAvailableBalance.setBounds(163, 107, 98, 20);
		accountbalancepanel.add(txtAvailableBalance);

		JLabel lblNewLabel = new JLabel("Outgoing Money");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(8, 135, 145, 14);
		accountbalancepanel.add(lblNewLabel);

		txtOutgoing = new JTextField();
		txtOutgoing.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtOutgoing.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtOutgoing.setText("0.00");
		txtOutgoing.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtOutgoing.setColumns(10);
		txtOutgoing.setBounds(163, 135, 98, 20);
		accountbalancepanel.add(txtOutgoing);

		JLabel lblNewLabel_1 = new JLabel("Incoming Money");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(8, 163, 145, 14);
		accountbalancepanel.add(lblNewLabel_1);

		txtIncoming = new JTextField();
		txtIncoming.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtIncoming.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtIncoming.setText("0.00");
		txtIncoming.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIncoming.setColumns(10);
		txtIncoming.setBounds(163, 163, 98, 20);
		accountbalancepanel.add(txtIncoming);

		JLabel lblNewLabel_5_1 = new JLabel("Monthly Payments");
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(8, 191, 145, 14);
		accountbalancepanel.add(lblNewLabel_5_1);

		txtMonthlyPayments = new JTextField();
		txtMonthlyPayments.setText("0.00");
		txtMonthlyPayments.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMonthlyPayments.setEditable(false);
		txtMonthlyPayments.setColumns(10);
		txtMonthlyPayments.setBackground(new Color(128, 255, 255));
		txtMonthlyPayments.setBounds(163, 191, 98, 20);
		accountbalancepanel.add(txtMonthlyPayments);

		JLabel lblNewLabel_5_1_1 = new JLabel("Ending Balance");
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(8, 219, 145, 14);
		accountbalancepanel.add(lblNewLabel_5_1_1);

		txtEndingBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEndingBalance.setEditable(false);
		txtEndingBalance.setColumns(10);
		txtEndingBalance.setBounds(163, 219, 98, 20);
		accountbalancepanel.add(txtEndingBalance);

		txtStartingBalance = new JTextField();
		txtStartingBalance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStartingBalance.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtStartingBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtStartingBalance.setColumns(10);
		txtStartingBalance.setBounds(163, 22, 98, 20);
		accountbalancepanel.add(txtStartingBalance);

		JPanel expensespanel = new JPanel();
		expensespanel.setBorder(
				new TitledBorder(null, "Expenses", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		expensespanel.setPreferredSize(new Dimension(200, 200));
		tab1.add(expensespanel, BorderLayout.CENTER);
		expensespanel.setLayout(null);

		JScrollPane BankExpensecrollPane = new JScrollPane();
		BankExpensecrollPane.setBounds(0, 49, 970, 450);
		expensespanel.add(BankExpensecrollPane);

		BankExpenseTable = new JTable();
		BankExpenseTable.setFont(new Font("Tahoma", Font.BOLD, 10));
		BankExpenseTable
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
						new String[] { "Bank ID", "Exp. ID", "Description (double click to edit)",
								"Due Date (double click to edit)", "Amount (double click to edit)", "Include",
								"Category", "Frequency" }) {
					Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class,
							Object.class, Boolean.class, Object.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		BankExpenseTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		BankExpenseTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		BankExpenseTable.getColumnModel().getColumn(2).setPreferredWidth(175);
		BankExpenseTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		BankExpenseTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		BankExpenseTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		BankExpenseTable.getColumnModel().getColumn(6).setPreferredWidth(125);

		BankExpensecrollPane.setViewportView(BankExpenseTable);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Sort By", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 970, 51);
		expensespanel.add(panel);

		rdbtnSortbydate.setBounds(50, 15, 100, 25);
		panel.add(rdbtnSortbydate);

		rdbtnSortbyid.setBounds(150, 15, 109, 25);
		panel.add(rdbtnSortbyid);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 500, 953, 60);
		expensespanel.add(panel_1);

		JLabel lblNewLabel_6 = new JLabel("Currency");
		panel_1.add(lblNewLabel_6);
		txtCurrencySymbol = new JTextField();
		txtCurrencySymbol.setText("$");
		txtCurrencySymbol.setEditable(false);
		txtCurrencySymbol.setColumns(10);
		panel_1.add(txtCurrencySymbol);

		txtCurrencyRate = new JTextField();
		txtCurrencyRate.setText("1.00");
		txtCurrencyRate.setEditable(false);
		txtCurrencyRate.setColumns(10);
		panel_1.add(txtCurrencyRate);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doCheck("CHK");
					doSaveTab1();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});

		btnSave.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/updated.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(btnSave);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCheck("CHK");
				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		btnRefresh.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/refresh.png")));
		panel_1.add(btnRefresh);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab1);
			}
		});

		JButton btnExpenseSummary = new JButton("Expense Summary");
		btnExpenseSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpenseSummaryForm_3 a = null;
				try {
					a = new ExpenseSummaryForm_3(id, acctdesc);
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
		btnExpenseSummary.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/expense-16.png")));
		btnExpenseSummary.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel_1.add(btnExpenseSummary);
		btnPrint.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/printer.png")));
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(btnPrint);

		JButton btnExcel = new JButton("Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/excel.png")));
		panel_1.add(btnExcel);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doAdd();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/plus.png")));
		panel_1.add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doEdit();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/edit.png")));
		panel_1.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/bin.png")));
		panel_1.add(btnDelete);

		JPanel tab2 = new JPanel();
		centertabbedPane.addTab("Currency", null, tab2, null);
		tab2.setLayout(null);

		JScrollPane CurrencyscrollPane = new JScrollPane();
		CurrencyscrollPane.setBounds(0, 0, 1500, 500);
		tab2.add(CurrencyscrollPane);

		CurrencyTable = new JTable();
		CurrencyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int column = 2;
					int row = CurrencyTable.getSelectedRow();
					if (row >= 0) {
						String symbol = CurrencyTable.getModel().getValueAt(row, column).toString();
						txtCurrencySymbol.setText(symbol);
						String rate = getCurrency(txtCurrencySymbol.getText());
						txtCurrencyRate.setText(rate);
					}

				}

			}
		});
		CurrencyTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Currency ID", "Description", "Symbol", "Rate (double clisk to edit)" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		CurrencyTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		CurrencyTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		CurrencyTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		CurrencyTable.getColumnModel().getColumn(3).setPreferredWidth(50);

		CurrencyscrollPane.setViewportView(CurrencyTable);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 500, 1438, 50);
		tab2.add(panel_2);

		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) CurrencyTable.getModel();
				int nRow = model.getRowCount(), nCol = model.getColumnCount();
				Object[][] tableData = new Object[nRow][nCol];
				for (int i = 0; i < nRow; i++) {

					tableData[i][0] = model.getValueAt(i, 0);
					String currencyid = String.valueOf(tableData[i][0]);
					tableData[i][3] = model.getValueAt(i, 3);
					String rate = String.valueOf(tableData[i][3]);

					doUpdateCurrency(currencyid, rate);
				}
			}
		});
		btnSave_1.setIcon(new ImageIcon(BankEditForm_3.class.getResource("/images/updated.png")));
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(btnSave_1);

		JPanel bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(800, 100));
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));

		JPanel classpanel = new JPanel();
		classpanel.setPreferredSize(new Dimension(1500, 50));
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("SMLDefaultJFrame");
		lblClassName.setPreferredSize(new Dimension(200, 14));
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setText(className);
		classpanel.add(lblClassName);

		// Add the scrollPane to the contentPane instead of the contentPanel
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// ...
		{

		}

		doPopulateExpenses(id);
		this.bankid = id;
		this.bankdesc = acctdesc;
		doPopulateBankAccount(id);
		doPopulateCurrency();
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

	private void doInquiry() throws SQLException {
		BankInquiryForm_2 a = new BankInquiryForm_2();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doNewInquiry(String id, String acctdesc) throws SQLException {
		BankEditForm_3 a;
		a = new BankEditForm_3(id, acctdesc);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
//		int screen = SMLUtility.getCurrentMonitorInfo(a);
//		int screen = -1;
//		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doPopulateExpenses(String id) {
		ResultSet rs = null;
		String sql = null;
		String bankid = id;
		String expenceid;
		String description;
		String amount;
		String IncludeYN;
		boolean isInclude;
		java.util.Date workdate;
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		String duedate = "";
		String frequency = null;
		String category = null;

		sql = "SELECT ";
		sql += "A.ID, A.DESCRIPTION, A.DUEDATE, A.BANKID, A.INCLUDE, A.CATEGORY, A.FREQUENCY, ";
		sql += "(CASE WHEN A.FREQUENCY = 'MONTHLY' THEN A.AMOUNT WHEN A.FREQUENCY = 'YEARLY' THEN A.AMOUNT/12 WHEN A.FREQUENCY = 'QUARTELY' THEN A.AMOUNT/4 WHEN A.FREQUENCY = 'Bimonthly' THEN A.AMOUNT/2 ELSE A.AMOUNT END) AS AMOUNT ";
		sql += "FROM PEXPENSE A ";
		sql += "WHERE A.BANKID = '" + id + "' ";
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");

		if (rdbtnSortbydate.isSelected()) {
			sql += "ORDER BY A.DUEDATE, A.ID ";
		} else {
			sql += "ORDER BY A.ID ";
		}

		try {
			TableModel model = BankExpenseTable.getModel();

			((DefaultTableModel) model).setRowCount(0);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				expenceid = (rs.getString("id"));
				description = (rs.getString("description"));
				amount = decAmt$Format.format(rs.getDouble("amount"));
				IncludeYN = (rs.getString("include"));
				category = (rs.getString("Category"));
				frequency = (rs.getString("Frequency"));

				if (IncludeYN.equals("Yes")) {
					isInclude = true;
				} else {
					isInclude = false;
				}
				duedate = mmddyy.format(rs.getDate("DUEDATE"));
				Vector row = new Vector();

				BankExpenseTable.setDefaultRenderer(Object.class, new CellHighlighterRenderer());

				row.add(bankid);
				row.add(expenceid);
				row.add(description);
				row.add(duedate);
				row.add(amount);
				row.add(isInclude);
				row.add(category);
				row.add(frequency);

				((DefaultTableModel) model).addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doCheck(String type) {
		field = "rdbtnSortbydate";
		isBoolean = rdbtnSortbydate.isSelected();
		value = String.valueOf(isBoolean);
		try {
			value = SMLUtility.getValue(className, field, value, type);
			if (type.equals("GET")) {
				Boolean isBoolean = Boolean.valueOf(value);
				if (isBoolean) {
					rdbtnSortbydate.setSelected(true);
					rdbtnSortbyid.setSelected(false);
				} else {
					rdbtnSortbydate.setSelected(false);
					rdbtnSortbyid.setSelected(true);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void doPopulateBankAccount(String id) {
		ResultSet rs = null;
		String sql = null;
		String bankid;
		String description;
		String startingbalance;
		String debits;
		String credits;
		String availablebalance;
		String incoming;
		String outgoing;
		String monthlypayments = null;
		String endingbalance;
		Double workdouble;
		String currency = txtCurrencySymbol.getText();
		String currencyformat = currency + "0.00";
		Double rate = Double.valueOf(txtCurrencyRate.getText());
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color cyan = new Color(0, 255, 255);

		sql = "SELECT ";
		sql += "A.ID, A.DESCRIPTION, A.STARTINGBALANCE, A.DEBIT, A.CREDIT, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT) AS DECIMAL (8,2))  AVAILABLEBALANCE, ";
		sql += "A.OUTGOING, A.INCOMING, ";
		sql += "(SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE = 'Yes') MONTHLYPAYMENTS, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT - A.OUTGOING + A.INCOMING  - (SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE = 'Yes')) AS DECIMAL (8,2)) ENDINGBALANCE ";
		sql += "FROM PBANKACCOUNT A ";
		sql += "WHERE A.ID = '" + id + "'";
		DecimalFormat decAmt$Format = new DecimalFormat(currencyformat);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {

				workdouble = rs.getDouble("STARTINGBALANCE");
				workdouble = (workdouble * rate);
				txtStartingBalance.setText(decAmt$Format.format(Double.valueOf((workdouble))));
				txtDebit.setText(decAmt$Format.format(rs.getDouble("DEBIT")));
				txtCredit.setText(decAmt$Format.format(rs.getDouble("CREDIT")));
				txtAvailableBalance.setText(decAmt$Format.format(rs.getDouble("AVAILABLEBALANCE")));
				txtOutgoing.setText(decAmt$Format.format(rs.getDouble("OUTGOING")));
				txtIncoming.setText(decAmt$Format.format(rs.getDouble("INCOMING")));
				txtMonthlyPayments.setText(decAmt$Format.format(rs.getDouble("MONTHLYPAYMENTS")));
				txtEndingBalance.setText(decAmt$Format.format(rs.getDouble("ENDINGBALANCE")));

				workdouble = rs.getDouble("AVAILABLEBALANCE");

				if (workdouble < 0) {
					txtAvailableBalance.setBackground(new Color(255, 127, 127));
					;
				} else {
					txtAvailableBalance.setBackground(green);
					;
				}

				workdouble = rs.getDouble("ENDINGBALANCE");

				if (workdouble < 0) {
					txtEndingBalance.setBackground(new Color(255, 127, 127));
					;
				} else {
					txtEndingBalance.setBackground(green);
					;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doPopulateBankAccountTest(String id) throws SQLException {
		ResultSet rs = null;
		String sql = null;
		String bankid = null;
		String description;
		String startingbalance;
		String debits;
		String credits;
		String availablebalance;
		String incoming;
		String outgoing;
		String monthlypayments = null;
		String endingbalance;
		Double workdouble;
		String currency = txtCurrencySymbol.getText();
		String currencyformat = currency + "0.00";
		Double rate = Double.valueOf(txtCurrencyRate.getText());
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color cyan = new Color(0, 255, 255);

		sql = "SELECT ";
		sql += "A.ID, A.DESCRIPTION, A.STARTINGBALANCE, A.DEBIT, A.CREDIT, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT) AS DECIMAL (8,2))  AVAILABLEBALANCE, ";
		sql += "A.OUTGOING, A.INCOMING, ";
		sql += "(SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE = 'Yes') MONTHLYPAYMENTS, ";
		sql += "CAST((A.STARTINGBALANCE - A.DEBIT + A.CREDIT - A.OUTGOING + A.INCOMING  - (SELECT COALESCE (SUM(B.AMOUNT),0) FROM PEXPENSE B WHERE B.BANKID = A.ID AND B.INCLUDE = 'Yes')) AS DECIMAL (8,2)) ENDINGBALANCE ";
		sql += "FROM PBANKACCOUNT A ";
		sql += "WHERE A.ID = ? ";
		DecimalFormat decAmt$Format = new DecimalFormat(currencyformat);

		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, bankid);

//		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.doPreparedStatementSelect();

			while (rs.next()) {

				workdouble = rs.getDouble("STARTINGBALANCE");
				workdouble = (workdouble * rate);
				txtEndingBalance.setText(decAmt$Format.format(Double.valueOf((workdouble))));
				txtDebit.setText(decAmt$Format.format(rs.getDouble("DEBIT")));
				txtCredit.setText(decAmt$Format.format(rs.getDouble("CREDIT")));
				txtAvailableBalance.setText(decAmt$Format.format(rs.getDouble("AVAILABLEBALANCE")));
				txtOutgoing.setText(decAmt$Format.format(rs.getDouble("OUTGOING")));
				txtIncoming.setText(decAmt$Format.format(rs.getDouble("INCOMING")));
				txtMonthlyPayments.setText(decAmt$Format.format(rs.getDouble("MONTHLYPAYMENTS")));
				txtEndingBalance.setText(decAmt$Format.format(rs.getDouble("ENDINGBALANCE")));

				workdouble = rs.getDouble("AVAILABLEBALANCE");

				if (workdouble < 0) {
					txtAvailableBalance.setBackground(new Color(255, 127, 127));
					;
				} else {
					txtAvailableBalance.setBackground(green);
					;
				}

				workdouble = rs.getDouble("ENDINGBALANCE");

				if (workdouble < 0) {
					txtEndingBalance.setBackground(new Color(255, 127, 127));
					;
				} else {
					txtEndingBalance.setBackground(green);
					;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doPopulateCurrency() {
		ResultSet rs = null;
		String sql = null;
		String currencyid;
		String description;
		String symbol;
		String rate;
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");
		TableModel model = CurrencyTable.getModel();
		((DefaultTableModel) model).setRowCount(0);
		sql = "SELECT ";
		sql += "* ";
		sql += "FROM PCURRENCY ";
		sql += "ORDER BY ID ";

//		

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
				while (rs.next()) {
					Vector row = new Vector();

					currencyid = (rs.getString("ID"));
					description = (rs.getString("DESCRIPTION"));
					symbol = (rs.getString("SYMBOL"));
					rate = decAmt$Format.format(rs.getDouble("RATE"));

					row.add(currencyid);
					row.add(description);
					row.add(symbol);
					row.add(rate);

					((DefaultTableModel) model).addRow(row);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
	}

	private String getCurrency(String symbol) {
		ResultSet rs = null;
		String sql = null;
		String rate = null;
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");

		sql = "SELECT ";
		sql += "* ";
		sql += "FROM PCURRENCY ";
		sql += "WHERE SYMBOL = '" + symbol + "' ";

//		System.out.println("SQL is : " + sql);

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
				while (rs.next()) {
					rate = (rs.getString("RATE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
		return rate;
	}

	private void doSaveTab1() throws ParseException {
		DefaultTableModel model = (DefaultTableModel) BankExpenseTable.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {

			int bankidColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Bank ID");
//			System.out.println("Column for Bank ID is  : " + bankidColNo);

			int expenseidColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Exp. ID");
//			System.out.println("Column for Expense ID is  : " + expenseidColNo);

			int descColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Description (double click to edit)");
//			System.out.println("Column for Description is  : " + amountColNo);

			int duedateColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Due Date (double click to edit)");
//			System.out.println("Column for Due Date is  : " + amountColNo);

			int amountColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Amount (double click to edit)");
//			System.out.println("Column for Amount is  : " + amountColNo);

			int includeColNo = SMLUtility.getColumnIndex(BankExpenseTable, "Include");
//			System.out.println("Column for Include is  : " + includeColNo);

			tableData[i][bankidColNo] = model.getValueAt(i, bankidColNo);
			String bankid = String.valueOf(tableData[i][bankidColNo]);

			tableData[i][expenseidColNo] = model.getValueAt(i, expenseidColNo);
			String expenseid = String.valueOf(tableData[i][expenseidColNo]);

			tableData[i][descColNo] = model.getValueAt(i, descColNo);
			String desc = String.valueOf(tableData[i][descColNo]);

			tableData[i][duedateColNo] = model.getValueAt(i, duedateColNo);
			String duedate = String.valueOf(tableData[i][duedateColNo]);

			tableData[i][amountColNo] = model.getValueAt(i, amountColNo);
			String amount = String.valueOf(tableData[i][amountColNo]);

			tableData[i][includeColNo] = model.getValueAt(i, includeColNo);
			boolean isInclude = (boolean) BankExpenseTable.getValueAt(i, includeColNo);

			doUpdateExpense(bankid, expenseid, desc, duedate, amount, isInclude);

		}
		doUpdateBankAccount(this.bankid);
	}

	private void doUpdateExpense(String bankid, String expenseid, String desc, String duedate, String amount,
			boolean isInclude) throws ParseException {
		String sql = null;
		ResultSet rs = null;
		amount = amount.replace("$", "");
		String include = "";
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		String workdate;

		if (amount == null) {
			amount = "0.00";
		}

		if (isInclude) {
			include = "Yes";
		} else {
			include = "No";
		}

		java.util.Date date1 = mmddyy.parse(duedate);

		duedate = yyyy_mm_dd.format(date1);

		sql = "UPDATE PEXPENSE ";
		sql += "SET AMOUNT = '" + amount + "', ";
		sql += "INCLUDE = '" + include + "', ";
		sql += "DESCRIPTION = '" + desc + "', ";
		sql += "DUEDATE = '" + duedate + "' ";
		sql += "WHERE BANKID = '" + bankid + "' ";

		sql += "AND ID = '" + expenseid + "' ";

		DecimalFormat decAmt$Format = new DecimalFormat("0.00");
//		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doUpdateBankAccount(String bankid) {
		String sql = null;
		ResultSet rs = null;
		String startingbalance = txtStartingBalance.getText();
		String debit = txtDebit.getText();
		String credit = txtCredit.getText();
		String outgoing = txtOutgoing.getText();
		String incomeing = txtIncoming.getText();
		String endingbalance = txtEndingBalance.getText();
		Double workdouble;
		String currency = txtCurrencySymbol.getText();
		Double rate = Double.valueOf(txtCurrencyRate.getText());
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		java.util.Date workdate;

		startingbalance = startingbalance.replace(currency, "");
		debit = debit.replace(currency, "");
		credit = credit.replace(currency, "");
		outgoing = outgoing.replace(currency, "");
		incomeing = incomeing.replace(currency, "");
		endingbalance = endingbalance.replace(currency, "");
		currency = "$";
		startingbalance = startingbalance.replace("$", "");
		debit = debit.replace(currency, "");
		credit = credit.replace(currency, "");
		outgoing = outgoing.replace(currency, "");
		incomeing = incomeing.replace(currency, "");
		endingbalance = endingbalance.replace(currency, "");

		if (startingbalance == null || startingbalance.length() == 0) {
			startingbalance = "0.00";
		} else {
			workdouble = Double.valueOf(startingbalance);
			workdouble = (workdouble / rate);
			startingbalance = Double.toString(workdouble);

		}

		if (debit == null || debit.length() == 0) {
			debit = "0.00";
		}

		if (credit == null || credit.length() == 0) {
			credit = "0.00";
		}

		if (outgoing == null || outgoing.length() == 0) {
			outgoing = "0.00";
		}

		if (incomeing == null || incomeing.length() == 0) {
			incomeing = "0.00";
		}

		if (endingbalance == null || endingbalance.length() == 0) {
			endingbalance = "0.00";
		}

		sql = "UPDATE PBANKACCOUNT ";
		sql += "SET  ";
		sql += "STARTINGBALANCE = '" + startingbalance + "', ";
		sql += "DEBIT = '" + debit + "', ";
		sql += "CREDIT = '" + credit + "', ";
		sql += "OUTGOING = '" + outgoing + "', ";
		sql += "INCOMING = '" + incomeing + "', ";
		sql += "ENDINGBALANCE = '" + endingbalance + "' ";
		sql += "WHERE ID = '" + bankid + "' ";

		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doSaveTab2() {
		DefaultTableModel model = (DefaultTableModel) CurrencyTable.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {

			tableData[i][0] = model.getValueAt(i, 0);
			String currencyid = String.valueOf(tableData[i][0]);
			tableData[i][3] = model.getValueAt(i, 3);
			String rate = String.valueOf(tableData[i][3]);

			doUpdateCurrency(currencyid, rate);
		}

	}

	private void doUpdateCurrency(String currencyid, String amount) {
		String sql = null;
		ResultSet rs = null;
		amount = amount.replace("$", "");

		if (amount == null) {
			amount = "0.00";
		}

		sql = "UPDATE PCURRENCY ";
		sql += "SET RATE = '" + amount + "' ";
		sql += "WHERE ID = '" + currencyid + "' ";

		DecimalFormat decAmt$Format = new DecimalFormat("0.00");
//		System.out.println("SQL is : " + sql);

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

	private void doAdd(String desc) {
		ResultSet rs = null;
		String sql = "Insert into Pexpense (Description, Bankid) VALUES ( ";

		sql += " '" + desc + "', ";
		sql += " '" + this.bankid + "') ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = BankExpenseTable.getModel();
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

	private void doAdd() throws ParseException {
		String bankid = this.bankid;
		String bankdesc = this.bankdesc;

		ExpenseMaintenanceForm_2 a;
		a = new ExpenseMaintenanceForm_2(bankid, bankdesc, null, null);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
	}

	private void doEdit() throws ParseException {

		int column = 1;
		int row = BankExpenseTable.getSelectedRow();
		if (row >= 0) {
			String bankid = this.bankid;
			String bankdesc = this.bankdesc;
			String expenseid = BankExpenseTable.getModel().getValueAt(row, 1).toString();
			String expensedesc = BankExpenseTable.getModel().getValueAt(row, 2).toString();
			String amount = BankExpenseTable.getModel().getValueAt(row, 6).toString();

			ExpenseMaintenanceForm_2 a;
			a = new ExpenseMaintenanceForm_2(bankid, bankdesc, expenseid, expensedesc);
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			a.setVisible(true);
			dispose();
		} else {

			JOptionPane.showMessageDialog(null, "Please select a row to edit");

		}
	}

	private void doDelete() {
		int column = 0;
		int row = BankExpenseTable.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Please select a row to delete");
		} else {
			column = 1;
			String id = BankExpenseTable.getModel().getValueAt(row, column).toString();
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Verify Exit ",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				deleteIt(row, id);
				doCheck("CHK");
				column = 0;
				String bankid = BankExpenseTable.getModel().getValueAt(row, column).toString();
				doPopulateExpenses(bankid);
				doPopulateBankAccount(bankid);
			}
		}
	}

	private void deleteIt(int row, String id) {
		ResultSet rs = null;
		String sql = "Delete From pexpense where id = ";
		sql += " '" + id + "'  ";
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}

	}
}
