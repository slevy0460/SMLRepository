package stevelevy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JProgressBar;

public class PasswordInquiryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDescriptionSearch;
	private JComboBox cmbType;
	private JTextField txtDescAdd;
	private JTextField txtWindowsUserSearch;
	JButton btnSearch = new JButton("Search");
	JProgressBar progressBar = new JProgressBar();
	String classname = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordInquiryForm frame = new PasswordInquiryForm();
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
	 * @throws UnknownHostException 
	 */
	public PasswordInquiryForm() throws SQLException, UnknownHostException {
		int screen = SMLUtility.getCurrentMonitorInfo(PasswordInquiryForm.this);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 11, 1500, 141);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblAccount = new JLabel("Account");
		lblAccount.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAccount.setBounds(231, 56, 81, 14);
		jPanelTop.add(lblAccount);

		txtDescriptionSearch = new JTextField();
		txtDescriptionSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDescriptionSearch.setBounds(230, 76, 350, 20);
		jPanelTop.add(txtDescriptionSearch);
		txtDescriptionSearch.setColumns(10);

		txtWindowsUserSearch = new JTextField();
		txtWindowsUserSearch.setBounds(230, 76, 198, 20);
		jPanelTop.add(txtWindowsUserSearch);
		txtWindowsUserSearch.setColumns(10);

		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSearch.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = null;
				String value = null;

				field = "txtWindowsUserSearch";
				value = txtWindowsUserSearch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "txtDescriptionSearch";
				value = txtDescriptionSearch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "cmbType";
				value = "" + cmbType.getSelectedIndex();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				doPopulateInquiry();
			}
		});
		btnSearch.setBounds(25, 107, 123, 23);
		jPanelTop.add(btnSearch);

		JLabel lblTypeSearch = new JLabel("Type");
		lblTypeSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTypeSearch.setBounds(600, 56, 46, 14);
		jPanelTop.add(lblTypeSearch);

		cmbType = new JComboBox();
		cmbType.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbType.setModel(new DefaultComboBoxModel(new String[] { "All" }));
		cmbType.setBounds(600, 76, 218, 20);
		jPanelTop.add(cmbType);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 163, 1500, 212);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 1475, 189);
		jPanelCenter.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int column = 0;
					int row = table.getSelectedRow();
					if (row >= 0) {
						String id = table.getModel().getValueAt(row, column).toString();
						try {
							doEdit(id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a row to edit");
					}
				}

			}
		});
		table.setAutoCreateRowSorter(false);

		scrollPane.setViewportView(table);
		// Assuming you have a JTable named "table"
		JTableHeader header = table.getTableHeader();

		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "User", "Account Description", "Password", "Type", "User Id", "Expiration Date", "WebSite"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);

		progressBar.setBounds(0, 247, 1000, 14);
		jPanelCenter.add(progressBar);

		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

		JPanel jPanelAdd = new JPanel();
		jPanelAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelAdd.setBounds(0, 360, 1500, 73);
		contentPane.add(jPanelAdd);
		jPanelAdd.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Account Desciption");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(25, 11, 261, 14);
		jPanelAdd.add(lblNewLabel_1);

		txtDescAdd = new JTextField();
		txtDescAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDescAdd.setColumns(10);
		txtDescAdd.setBounds(25, 35, 261, 20);
		jPanelAdd.add(txtDescAdd);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDescAdd.getText().trim().length() == 0 || (txtDescAdd.getText().trim() == null)) {

					JOptionPane.showMessageDialog(null, "Please enter description to add new record");
				} else {
					String maxid = getLastId();

					int number = Integer.parseInt(maxid);
					number += 1;
					String newid = String.valueOf(number);

					doAdd(newid, txtDescAdd.getText());
					try {
						doEdit(newid);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnAdd.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/plus.png")));
		btnAdd.setBounds(343, 35, 89, 23);
		jPanelAdd.add(btnAdd);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 430, 1500, 40);
		contentPane.add(jPanelButtons);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/bin.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEdit.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/edit.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if (row >= 0) {
					String id = table.getModel().getValueAt(row, column).toString();
					try {
						doEdit(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to edit");
				}
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});
		jPanelButtons.add(btnPrint);

		JButton btnWebSite = new JButton("WebSite");
		btnWebSite.setFont(new Font("Dialog", Font.BOLD, 12));
		btnWebSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if (row >= 0) {
					String id = table.getModel().getValueAt(row, column).toString();
					String url = getWebSite(id);
					column = 7;
					String web = table.getModel().getValueAt(row, column).toString();
					if (web == "true") {
						try {
							java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid website");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to show website");
				}

			}
		});

		JButton btnExcel = new JButton("Excel");
		btnExcel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/excel.png")));
		jPanelButtons.add(btnExcel);
		btnWebSite.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/website.png")));
		jPanelButtons.add(btnWebSite);
		jPanelButtons.add(btnEdit);
		jPanelButtons.add(btnDelete);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(PasswordInquiryForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 470, 1500, 57);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		lblClassName.setForeground(Color.BLUE);
		this.classname = className;

		JLabel lblTitle = new JLabel("Title of Jframe");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setBounds(10, 10, 110, 15);
		jPanelTop.add(lblTitle);
		setTitle("Password Inquiry");
		lblTitle.setText("Password Inquiry");

		JLabel lblInstructions = new JLabel("");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInstructions.setText("Use this form to view all passwordsts");
		lblInstructions.setBounds(10, 11, 516, 34);
		jPanelTop.add(lblInstructions);

		JLabel lblWindowsusersearch = new JLabel("Windows User");
		lblWindowsusersearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWindowsusersearch.setBounds(25, 56, 123, 14);
		jPanelTop.add(lblWindowsusersearch);

		txtWindowsUserSearch = new JTextField(" ");
		txtWindowsUserSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtWindowsUserSearch.setText("");
		txtWindowsUserSearch.setColumns(10);
		txtWindowsUserSearch.setBounds(25, 76, 198, 20);
		jPanelTop.add(txtWindowsUserSearch);

		String value = null;
		value = SMLUtility.getValue(classname, "txtWindowsUserSearch", "", "GET");
		txtWindowsUserSearch.setText(value);
		value = SMLUtility.getValue(classname, "txtDescriptionSearch", "", "GET");
		txtDescriptionSearch.setText(value.trim());
		addTypes();
		value = SMLUtility.getValue(classname, "cmbType", "", "GET");
		cmbType.setSelectedIndex(Integer.valueOf(value));

		doCenterForm();

		doPopulateInquiry();

	}

	private void doCenterForm() {

		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doPopulateInquiry() {
		btnSearch.setEnabled(false);
		progressBar.setIndeterminate(true);
		doPopulate();
		btnSearch.setEnabled(true);
		progressBar.setIndeterminate(false);
	}

	private void doPopulate() {

		ResultSet rs = null;
		String id;
		String desc;
		String userid;
		String type;
		String expirationdate;
		String password;
		String windowsuser;
		String website;
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");
		String sql = "SELECT * FROM passwords a ";
		sql += "LEFT JOIN passwordtype B ON B.TYPE = A.TYPE ";
		sql += "WHERE 1 = 1";

		if (txtWindowsUserSearch.getText().trim().length() > 0) {
			String upper = txtWindowsUserSearch.getText().trim().toUpperCase();
			sql += " AND (UPPER(Windowsuser))  like '%" + upper + "%'";
		}

		if (txtDescriptionSearch.getText().trim().length() > 0) {
			String upper = txtDescriptionSearch.getText().trim().toUpperCase();
			sql += " AND (UPPER(AccountDescription))  like '%" + upper + "%'";
		}

		if (cmbType.getSelectedIndex() != 0) {
			Object selectedItem = cmbType.getSelectedItem();
			String typeselected = selectedItem.toString();

			if (typeselected.trim().length() > 0) {
				String upper = typeselected.trim().toUpperCase();
				sql += " AND (A.TYPE)  like '%" + upper + "%'";
			}
		}
//		System.out.println("User search is : " + txtWindowsUserSearch.getText());
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			TableModel model = table.getModel();

			((DefaultTableModel) model).setRowCount(0);
			while (rs.next()) {
				id = (rs.getString("a.id"));
				windowsuser = (rs.getString("a.Windowsuser"));
				desc = (rs.getString("a.Accountdescription"));
				userid = (rs.getString("a.userid"));
				type = (rs.getString("a.type"));
				expirationdate = (rs.getString("a.Expirationdate"));
				password = (rs.getString("a.Password"));
				website = (rs.getString("a.Website"));
				Vector row = new Vector();

				Boolean websiteyn = false;
				if (website != null && website.trim().length() > 0) {
					websiteyn = true;
				}

				row.add(id);
				row.add(windowsuser);
				row.add(desc);
				row.add(password);
				row.add(type);
				row.add(userid);
				row.add(expirationdate);
				row.add(websiteyn);

				((DefaultTableModel) model).addRow(row);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void addTypes() {

		ResultSet rs = null;
		String query = "select * from passwordtype order by type";

		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");

			while (rs.next()) {

				String type = (rs.getString("TYPE"));
				cmbType.addItem(type);

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}
	}

	private void doEdit(String id) throws SQLException {

		PasswordEditForm a = new PasswordEditForm(id);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doDelete() {
		int column = 0;
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Please select a row to delete");
		} else {
			String id = table.getModel().getValueAt(row, column).toString();
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Verify Exit ",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				deleteIt(row, id);
				doPopulate();
			}
		}
	}

	private void deleteIt(int row, String id) {

		ResultSet rs = null;
		String sql = "Delete From Passwords where id = ";
		sql += " '" + id + "'  ";

		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}

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

	private String getLastId() {

		ResultSet rs = null;
		String maxid = null;
		String sql = "SELECT MAX(ID)AS id FROM PASSWORDS";

		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				maxid = (rs.getString("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maxid;
	}

	private void doAdd(String newid, String desc) {
		String userName = System.getProperty("user.name");
		String blank = " ";
		String zeros = "0"
				;
		ResultSet rs = null;
		String sql = "Insert into Passwords (ID, Windowsuser, AccountDescription, Accountnumber, Type, Pinnumber, Cardnumber, Expirationdate, Userid, Password, Website, Storage, administrator, tabs) VALUES ( ";
		sql += " '" + newid + "' , ";
		sql += " '" + userName + "' , ";
		sql += "'" + desc + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + zeros + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "', ";
		sql += "'" + blank + "') ";
		
		

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
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

	private String getWebSite(String id) {
		String sql = null;
		ResultSet rs = null;
		sql = "SELECT * FROM PASSWORDS ";
		sql += "WHERE 1 = 1 ";
		sql += "AND ID = '" + id + "' ";
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				String website = (rs.getString("Website"));
				return website;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

		String filePath = "S:\\Users\\slevy\\OneDrive\\Desktop/Passwords.xlsx";
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
