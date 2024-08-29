package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BankEditForm_2 extends JFrame {

	private JPanel contentPane;
	private JTable BankExpenseTable;
	private JTextField txtStartingBalance;
	private JTextField txtDebit;
	private JTextField txtCredit;
	private JTextField txtOutgoing;
	private JTextField txtIncoming;
	private JTextField txtAvailableBalance;
	private JTextField txtMonthlyPayments;
	private JTextField txtEndingBalance;
	JComboBox cmbAccounts;
	String bankid;
	String bankdesc;
	private JTable CurrencyTable;
	private JTextField txtCurrencySymbol;
	private JTextField txtCurrencyRate;
	JRadioButton rdbtnSortbydate = new JRadioButton("Date");
	JRadioButton rdbtnSortbyid = new JRadioButton("Id");
	String className = getClass().getSimpleName();

	String field = null;
	String value = null;
	boolean isBoolean = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankEditForm_2 frame = new BankEditForm_2(null, null);
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
	 * @param id
	 * @param acctdesc
	 * @throws SQLException
	 */
	public BankEditForm_2(String id, String acctdesc) throws SQLException {
		doCheck("GET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Bank Expense Edit/Maintenance");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Use this form to edit Bank Expences");
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel tab1 = new JPanel();
		tabbedPane.addTab(acctdesc, null, tab1, null);
		tab1.setLayout(null);

		JPanel accountselectionpanel = new JPanel();
		accountselectionpanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		accountselectionpanel.setBounds(0, 0, 211, 475);
		tab1.add(accountselectionpanel);
		accountselectionpanel.setLayout(null);

		JButton btnWellsFargoChecking = new JButton("Wells Fargo Checking");
		btnWellsFargoChecking.setFont(new Font("Tahoma", Font.BOLD, 10));
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
		btnWellsFargoChecking.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnWellsFargoChecking.setBounds(10, 23, 195, 23);
		accountselectionpanel.add(btnWellsFargoChecking);

		JButton btnWellsFargoSavomgs = new JButton("Wells Fargo Savings");
		btnWellsFargoSavomgs.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnWellsFargoSavomgs.addActionListener(new ActionListener() {
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
		btnWellsFargoSavomgs.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnWellsFargoSavomgs.setBounds(10, 63, 195, 23);
		accountselectionpanel.add(btnWellsFargoSavomgs);

		JButton btnWellsFargoInsurance = new JButton("Wells Fargo Insurance");
		btnWellsFargoInsurance.setFont(new Font("Tahoma", Font.BOLD, 10));
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
		btnWellsFargoInsurance.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnWellsFargoInsurance.setBounds(10, 103, 195, 23);
		accountselectionpanel.add(btnWellsFargoInsurance);

		JButton btnScotiabankSavings = new JButton("Scotiabank Savings");
		btnScotiabankSavings.setFont(new Font("Tahoma", Font.BOLD, 10));
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
		btnScotiabankSavings.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnScotiabankSavings.setBounds(10, 143, 195, 23);
		accountselectionpanel.add(btnScotiabankSavings);

		JButton btnScotiabankColones = new JButton("Scotiabank Colones");
		btnScotiabankColones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnScotiabankColones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "6";
				String acctdesc = "Scotiabank Colones";
				try {
					doNewInquiry(id, acctdesc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnScotiabankColones.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnScotiabankColones.setBounds(10, 183, 195, 23);
		accountselectionpanel.add(btnScotiabankColones);

		JPanel entryfieldpanel = new JPanel();
		entryfieldpanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Account Balances", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		entryfieldpanel.setBounds(new Rectangle(221, 0, 284, 475));
		tab1.add(entryfieldpanel);
		entryfieldpanel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Starting Balance");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 28, 145, 14);
		entryfieldpanel.add(lblNewLabel_4);

		txtStartingBalance = new JTextField();
		txtStartingBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
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
		txtStartingBalance.setBounds(165, 28, 98, 20);
		entryfieldpanel.add(txtStartingBalance);
		txtStartingBalance.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Debits/Withdrawals");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 56, 145, 14);
		entryfieldpanel.add(lblNewLabel_2);

		txtDebit = new JTextField();
		txtDebit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDebit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDebit.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
//				try {
//					doSaveTab1();
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				doUpdateBankAccount(id);
//				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		txtDebit.setColumns(10);
		txtDebit.setBounds(165, 56, 98, 20);
		entryfieldpanel.add(txtDebit);

		JLabel lblNewLabel_3 = new JLabel("Credits/Deposits");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 84, 145, 14);
		entryfieldpanel.add(lblNewLabel_3);

		txtCredit = new JTextField();
		txtCredit.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCredit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCredit.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
//				try {
//					doSaveTab1();
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				doPopulateExpenses(id);
				doUpdateBankAccount(id);
				doPopulateBankAccount(id);
			}
		});
		txtCredit.setColumns(10);
		txtCredit.setBounds(165, 84, 98, 20);
		entryfieldpanel.add(txtCredit);

		JLabel lblNewLabel = new JLabel("Outgoing Money");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 140, 145, 14);
		entryfieldpanel.add(lblNewLabel);

		txtOutgoing = new JTextField();
		txtOutgoing.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtOutgoing.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtOutgoing.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
//				try {
//					doSaveTab1();
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				doPopulateExpenses(id);
				doUpdateBankAccount(id);
				doPopulateBankAccount(id);
			}
		});
		txtOutgoing.setColumns(10);
		txtOutgoing.setBounds(165, 140, 98, 20);
		entryfieldpanel.add(txtOutgoing);

		JLabel lblNewLabel_1 = new JLabel("Incoming Money");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 168, 145, 14);
		entryfieldpanel.add(lblNewLabel_1);

		txtIncoming = new JTextField();
		txtIncoming.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIncoming.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtIncoming.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
//				try {
//					doSaveTab1();
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				doPopulateExpenses(id);
				doUpdateBankAccount(id);
				doPopulateBankAccount(id);
			}
		});
		txtIncoming.setColumns(10);
		txtIncoming.setBounds(165, 168, 98, 20);
		entryfieldpanel.add(txtIncoming);

		JLabel lblNewLabel_5 = new JLabel("Avaiable Balance");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 112, 145, 14);
		entryfieldpanel.add(lblNewLabel_5);

		txtAvailableBalance = new JTextField();
		txtAvailableBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAvailableBalance.setEditable(false);
		txtAvailableBalance.setColumns(10);
		txtAvailableBalance.setBounds(165, 112, 98, 20);
		entryfieldpanel.add(txtAvailableBalance);

		JLabel lblNewLabel_5_1 = new JLabel("Monthly Payments");
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(10, 196, 145, 14);
		entryfieldpanel.add(lblNewLabel_5_1);

		txtMonthlyPayments = new JTextField();
		txtMonthlyPayments.setBackground(new Color(128, 255, 255));
		txtMonthlyPayments.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtMonthlyPayments.setEditable(false);
		txtMonthlyPayments.setColumns(10);
		txtMonthlyPayments.setBounds(165, 196, 98, 20);
		entryfieldpanel.add(txtMonthlyPayments);

		JLabel lblNewLabel_5_1_1 = new JLabel("Ending Balance");
		lblNewLabel_5_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5_1_1.setBounds(10, 224, 145, 14);
		entryfieldpanel.add(lblNewLabel_5_1_1);

		txtEndingBalance = new JTextField();
		txtEndingBalance.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEndingBalance.setEditable(false);
		txtEndingBalance.setColumns(10);
		txtEndingBalance.setBounds(165, 224, 98, 20);
		entryfieldpanel.add(txtEndingBalance);

		JPanel gridpanel = new JPanel();
		gridpanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Expenses - Hit Save button to update", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 255)));
		gridpanel.setBounds(507, 11, 952, 450);
		tab1.add(gridpanel);
		gridpanel.setLayout(null);

		JScrollPane BankExpensecrollPane = new JScrollPane();
		BankExpensecrollPane.setBounds(10, 77, 941, 375);
		gridpanel.add(BankExpensecrollPane);

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
		panel.setBorder(new TitledBorder(null, "Sort By", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 22, 941, 51);
		gridpanel.add(panel);
		panel.setLayout(null);

//		rdbtnSortbydate.setSelected(true);
		rdbtnSortbydate.setBounds(50, 15, 100, 25);
		panel.add(rdbtnSortbydate);

		rdbtnSortbyid.setBounds(150, 15, 109, 25);
		panel.add(rdbtnSortbyid);

		ButtonGroup bgroupsort = new ButtonGroup();
		bgroupsort.add(rdbtnSortbydate);
		bgroupsort.add(rdbtnSortbyid);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(0, 475, 1370, 39);
		tab1.add(panel_2);

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

		JLabel lblNewLabel_6 = new JLabel("Currency");
		panel_2.add(lblNewLabel_6);

		txtCurrencySymbol = new JTextField();
		txtCurrencySymbol.setEditable(false);
		txtCurrencySymbol.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String symbol = txtCurrencySymbol.getText();
				String rate = null;
				rate = getCurrency(txtCurrencySymbol.getText());
				txtCurrencyRate.setText(rate);
			}
		});
		txtCurrencySymbol.setColumns(10);
		panel_2.add(txtCurrencySymbol);

		txtCurrencyRate = new JTextField();
		txtCurrencyRate.setEditable(false);
		txtCurrencyRate.setColumns(10);
		panel_2.add(txtCurrencyRate);
		btnSave.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/updated.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(btnSave);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab1);
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCheck("CHK");
				doPopulateExpenses(id);
				doPopulateBankAccount(id);
			}
		});
		btnRefresh.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/refresh.png")));
		panel_2.add(btnRefresh);
		btnPrint.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/printer.png")));
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(btnPrint);

		JPanel tab2 = new JPanel();
		tabbedPane.addTab("Currency", null, tab2, null);
		tab2.setLayout(null);

		JPanel panel_1_tab2 = new JPanel();
		panel_1_tab2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1_tab2.setBounds(10, 0, 1000, 50);
		tab2.add(panel_1_tab2);

		JPanel panel_2_tab2 = new JPanel();
		panel_2_tab2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2_tab2.setBounds(10, 46, 1000, 160);
		tab2.add(panel_2_tab2);
		panel_2_tab2.setLayout(null);

		JScrollPane CurrencyscrollPane = new JScrollPane();
		CurrencyscrollPane.setBounds(0, 0, 1000, 160);
		panel_2_tab2.add(CurrencyscrollPane);

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

		JPanel panel_3_tab2 = new JPanel();
		panel_3_tab2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3_tab2.setBounds(10, 203, 1000, 80);
		tab2.add(panel_3_tab2);

		JPanel panel_4_tab2 = new JPanel();
		panel_4_tab2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4_tab2.setBounds(10, 283, 1018, 39);
		tab2.add(panel_4_tab2);

		JButton btnPrint_1 = new JButton("Print");
		btnPrint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(tab2);
			}
		});

		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSaveTab2();
			}
		});
		btnSave_1.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/updated.png")));
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4_tab2.add(btnSave_1);
		btnPrint_1.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/printer.png")));
		btnPrint_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4_tab2.add(btnPrint_1);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSouth.add(panel_1, BorderLayout.SOUTH);

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInquiry.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/task-planning.png")));
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doInquiry();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnInquiry);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClose.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/close.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
		btnExpenseSummary.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/expense-16.png")));
		panel_1.add(btnExpenseSummary);
		panel_1.add(btnClose);

		JPanel panel_1_1 = new JPanel();
		panelSouth.add(panel_1_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("lblClassName");
		panel_1_1.add(lblClassName);

		setTitle("Bank Expense Edit/Maintenance");
		className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		doCenterForm();
		txtCurrencySymbol.setText("$");
		String rate = null;
		rate = getCurrency(txtCurrencySymbol.getText());
		txtCurrencyRate.setText(rate);

		JButton btnExcel = new JButton("Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/excel.png")));
		panel_2.add(btnExcel);

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
		btnAdd.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/plus.png")));
		panel_2.add(btnAdd);

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
		btnEdit.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/edit.png")));
		panel_2.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setIcon(new ImageIcon(BankEditForm_2.class.getResource("/images/bin.png")));
		panel_2.add(btnDelete);

		doPopulateExpenses(id);
		this.bankid = id;
		this.bankdesc = acctdesc;
		doPopulateBankAccount(id);
//		doPopulateBankAccountTest(id);
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
		BankEditForm_2 a;
		a = new BankEditForm_2(id, acctdesc);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
//		int screen = SMLUtility.getCurrentMonitorInfo(a);
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
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

		startingbalance = startingbalance.replace("$", "");
		debit = debit.replace("$", "");
		credit = credit.replace("$", "");
		outgoing = outgoing.replace("$", "");
		incomeing = incomeing.replace("$", "");
		endingbalance = endingbalance.replace("$", "");

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

//		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doUpdateBankAccountTest(String bankid) throws SQLException {
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

		startingbalance = startingbalance.replace("$", "");
		debit = debit.replace("$", "");
		credit = credit.replace("$", "");
		outgoing = outgoing.replace("$", "");
		incomeing = incomeing.replace("$", "");
		endingbalance = endingbalance.replace("$", "");

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
		sql += "STARTINGBALANCE = ?, ";
		sql += "DEBIT = ?, ";
		sql += "CREDIT = ?, ";
		sql += "OUTGOING = ?, ";
		sql += "INCOMING = ?, ";
		sql += "ENDINGBALANCE = ? ";
		sql += "WHERE ID = ? ";

		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, startingbalance);
		ps.setString(2, debit);
		ps.setString(3, credit);
		ps.setString(4, outgoing);
		ps.setString(5, incomeing);
		ps.setString(6, endingbalance);
		ps.setString(7, bankid);

//		System.out.println("SQL is : " + sql);

		SMLUtility.PreparedStatmentUpdate(ps);
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

		ExpenseMaintenanceForm a;
		a = new ExpenseMaintenanceForm(bankid, bankdesc, null, null);
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

			ExpenseMaintenanceForm a;
			a = new ExpenseMaintenanceForm(bankid, bankdesc, expenseid, expensedesc);
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
