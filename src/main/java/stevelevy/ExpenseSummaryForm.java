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
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExpenseSummaryForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
	String currencyformat = '$' + "0.00";
	DecimalFormat decAmt$Format = new DecimalFormat(currencyformat);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseSummaryForm frame = new ExpenseSummaryForm(null, null);
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
	 */
	public ExpenseSummaryForm(String id, String acctdesc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 1000);
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

		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 60, 800, 1400);
		panelCenter.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, ""},
			},
			new String[] {
				"Bankid", "Bank", "Expense", "Amount", "Include"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		scrollPane.setViewportView(table);

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
		lblMenu.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/menu.png")));
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
				a.setVisible(true);
				dispose();
			}
		});
		btnBankInquiry.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/bank.png")));
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
				a.setVisible(true);
				dispose();
			}
		});
		btnBankMaintenance.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/bank.png")));
		panel.add(btnBankMaintenance);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(panelCenter);
			}
		});
		btnPrint.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/printer.png")));
		panel.add(btnPrint);
		
		JButton btnExcel = new JButton("Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/excel.png")));
		panel.add(btnExcel);
		btnClose.setIcon(new ImageIcon(ExpenseSummaryForm.class.getResource("/images/close.png")));
		panel.add(btnClose);
		if (id == null) {
			btnBankMaintenance.setEnabled(false);
		}

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		panel_1.add(lblClassName);

		setTitle("Expense summary");
		lblTitle.setText("Expense summary");
		lblInstructions.setText("View balance of Expense Summary");
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		doCenterForm();
		doPopulateExpenses();

	}

	private void doPopulateExpenses() {

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
		String frequency = " ";
		String income = "8223.00";
		String category = "";
		int i = 0;

		sql = "SELECT ";
		sql += "A.BANKID, ";
		sql += "(select min(b.Description) from pbankaccount b where a.bankid = b.id) as Bankname, ";
		sql += "A.DESCRIPTION, A.CATEGORY, ";
		sql += "(CASE WHEN A.Frequency = 'MONTHLY' THEN A.AMOUNT WHEN A.Frequency = 'YEARLY' THEN A.AMOUNT/12 WHEN A.Frequency = 'QUARTELY' THEN A.AMOUNT/4 ELSE A.AMOUNT END) AS AMOUNT,  ";
		sql += "A.INCLUDE, ";
		sql += "(CASE WHEN A.Frequency = 'MONTHLY' THEN 'M' WHEN A.Frequency = 'YEARLY' THEN 'Y' WHEN A.Frequency = 'QUARTELY' THEN 'Q' ELSE 'M' END) AS Frequency  ";
		sql += "FROM PEXPENSE A ";
		sql += "ORDER BY A.BANKID, A.ID ";
//		System.out.println("SQL is : " + sql);

		try {
			TableModel model = table.getModel();
			((DefaultTableModel) model).setRowCount(0);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			Vector rowblank = new Vector();
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");
			rowblank.add("");

			table.setFont(new Font("Tahoma", Font.PLAIN, 8));
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
					row1.add(decAmt$Format.format(Double.valueOf(breakamount)));
					row1.add("");
					row1.add("");
					((DefaultTableModel) model).addRow(row1);
					((DefaultTableModel) model).addRow(rowblank);
					savebankid = bankid;
					breakamount = 0.00;
					table.setFont(new Font("Tahoma", Font.PLAIN, 8));
				}
				bankname = (rs.getString("Bankname"));
				expensedesc = (rs.getString("description"));
				amount = decAmt$Format.format(Double.valueOf((rs.getString("Amount"))));
				work = Double.valueOf((rs.getString("Amount")));
				breakamount = breakamount + work;
				grandamount = grandamount + work;
				include = (rs.getString("include"));
				frequency = (rs.getString("Frequency"));
				category = (rs.getString("Category"));

				table.setDefaultRenderer(Object.class, new CellHighlighterRenderer());
				table.setFont(new Font("Tahoma", Font.BOLD, 10));
				Vector row = new Vector();
				row.add(savebankid);
				row.add(bankname);
				row.add(expensedesc);
				row.add(amount);
				row.add(include);
//				row.add(category);
//				row.add(frequency);

				((DefaultTableModel) model).addRow(row);
			}
			// Do last bank totals
			Vector row1 = new Vector();
			row1.add("Bank Total " + savebankid);
			row1.add("");
			row1.add("");
			row1.add(decAmt$Format.format(Double.valueOf(breakamount)));
			row1.add("");
//			row1.add("");
			((DefaultTableModel) model).addRow(row1);
			((DefaultTableModel) model).addRow(rowblank);
			// Do grand bank totals
			Vector row2 = new Vector();
			row2.add("Grand Total");
			row2.add("");
			row2.add("");
			row2.add(decAmt$Format.format(Double.valueOf(grandamount)));
			row2.add("");
//			row2.add("");
			((DefaultTableModel) model).addRow(row2);

			// Do income
			((DefaultTableModel) model).addRow(rowblank);
			Vector row3 = new Vector();
			row3.add("Income");
			row3.add("");
			row3.add("");
			row3.add(decAmt$Format.format(Double.valueOf(income)));
			row3.add("");
//			row3.add("");
			((DefaultTableModel) model).addRow(row3);
			// Do Balance
			((DefaultTableModel) model).addRow(rowblank);
			incomeamount = Double.valueOf((income));
			work = incomeamount - grandamount;
			Vector row4 = new Vector();
			row4.add("Balamce");
			row4.add("");
			row4.add("");
			row4.add(decAmt$Format.format(Double.valueOf(work)));
			row4.add("");
//			row4.add("");
			((DefaultTableModel) model).addRow(row4);
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private void doCenterForm() {
		// TODO Auto-generated method stub
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
	
	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = table.getModel();
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

		String filePath = "C:\\Users\\slevy\\OneDrive\\Desktop/ExpenseSummary.xlsx";
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

}
