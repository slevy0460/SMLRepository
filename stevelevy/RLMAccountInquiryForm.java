package stevelevy;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.UnknownHostException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Color;

public class RLMAccountInquiryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable1;
	private JTextField txtFormsSarch;
	private JTextField txtSystemSearch;
	private JComboBox cmbStatusSearch;
	String classname = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLMAccountInquiryForm frame = new RLMAccountInquiryForm();
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
	public RLMAccountInquiryForm() throws SQLException, UnknownHostException {
		int screen = SMLUtility.getCurrentMonitorInfo(RLMAccountInquiryForm.this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 1500, 160);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblFromsSearch = new JLabel("Forms");
		lblFromsSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFromsSearch.setBounds(58, 58, 55, 14);
		jPanelTop.add(lblFromsSearch);

		txtFormsSarch = new JTextField();
		txtFormsSarch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtFormsSarch.setBounds(58, 75, 86, 20);
		jPanelTop.add(txtFormsSarch);
		txtFormsSarch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSearch.setIcon(new ImageIcon(RLMAccountInquiryForm.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = null;
				String value = null;

				field = "txtFormsSarch";
				value = txtFormsSarch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "txtSystemSearch";
				value = txtSystemSearch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "cmbStatusSearch";
				value = "" + cmbStatusSearch.getSelectedIndex();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				doPopulate();
			}
		});
		btnSearch.setBounds(58, 108, 171, 23);
		jPanelTop.add(btnSearch);

		JLabel lblSystemSearch = new JLabel("System");
		lblSystemSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSystemSearch.setBounds(168, 58, 55, 14);
		jPanelTop.add(lblSystemSearch);

		txtSystemSearch = new JTextField();
		txtSystemSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtSystemSearch.setColumns(10);
		txtSystemSearch.setBounds(168, 75, 86, 20);
		jPanelTop.add(txtSystemSearch);

		JLabel lblSatisSearch = new JLabel("Status");
		lblSatisSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSatisSearch.setBounds(308, 58, 55, 14);
		jPanelTop.add(lblSatisSearch);

		cmbStatusSearch = new JComboBox();
		cmbStatusSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbStatusSearch.setModel(new DefaultComboBoxModel(new String[] { "All", "Active", "In-Active" }));
		cmbStatusSearch.setBounds(308, 75, 141, 17);
		jPanelTop.add(cmbStatusSearch);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 170, 1004, 510);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 1500, 637);
		jPanelCenter.add(scrollPane);

		jTable1 = new JTable();
		jTable1.setFont(new Font("Tahoma", Font.BOLD, 12));
		// Assuming you have a JTable named "table"
		JTableHeader header = jTable1.getTableHeader();

		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
		// Make table alternate colors
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(192, 192, 192));
		jTable1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int column = 0;
					int row = jTable1.getSelectedRow();
					if (row >= 0) {
						String id = jTable1.getModel().getValueAt(row, column).toString();

						try {
							doEdit(id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a row to edit");
					}
				}
			}
		});
		jTable1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, "", null, null }, },
				new String[] { "Id", "Forms", "System", "Name", "Account", "EDI 9", "Prod 9", "Purged", "Status" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jTable1.getColumnModel().getColumn(0).setCellRenderer(new CellJustifyRendererCenter());
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
		jTable1.setAutoCreateRowSorter(false);
		scrollPane.setViewportView(jTable1);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 690, 1500, 46);
		contentPane.add(jPanelButtons);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPrint.setIcon(new ImageIcon(RLMAccountInquiryForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEdit.setIcon(new ImageIcon(RLMAccountInquiryForm.class.getResource("/images/edit.png")));
		btnEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = jTable1.getSelectedRow();
				if (row >= 0) {
					String id = jTable1.getModel().getValueAt(row, column).toString();
					try {
						doEdit(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to edit");
				}
			}
		});
		jPanelButtons.add(btnEdit);
		jPanelButtons.add(btnPrint);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(RLMAccountInquiryForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 731, 1500, 43);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Dialog", Font.BOLD, 12));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		lblClassName.setForeground(Color.BLUE);
		this.classname = className;

		JLabel lblTitle = new JLabel("RLM Account Inquiry");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setBounds(10, 10, 422, 15);
		jPanelTop.add(lblTitle);

		JLabel lblInstructions = new JLabel("Use this form to view RLM accounts");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInstructions.setBounds(10, 30, 422, 13);
		jPanelTop.add(lblInstructions);
		setTitle("RLM Account Inquiry");
		doCenterFrom();
		String value = null;
		value = SMLUtility.getValue(classname, "txtFormsSarch", "", "GET");
		txtFormsSarch.setText(value);

		value = SMLUtility.getValue(classname, "txtSystemSearch", "", "GET");
		txtSystemSearch.setText(value);

		value = SMLUtility.getValue(classname, "cmbStatusSearch", "", "GET");
		cmbStatusSearch.setSelectedIndex(Integer.valueOf(value));

		doPopulate();

	}

	private void doPopulate() {

		ResultSet rs = null;
		String sql = "SELECT QGPL.PEXPACT.*, (CASE WHEN INACTIVE  = 'I' THEN 'In-Active' ELSE 'Active' END) INACTIVE  FROM QGPL.PEXPACT ";
		sql += "WHERE 1 = 1";

		if (txtFormsSarch.getText().trim().length() > 0) {
			String upper = txtFormsSarch.getText().trim().toUpperCase();
			sql += " AND (FORMS)  like '%" + upper + "%'";
		}

		if (txtSystemSearch.getText().trim().length() > 0) {
			String upper = txtSystemSearch.getText().trim().toUpperCase();
			sql += " AND (SYSTEM)  like '%" + upper + "%'";
		}

		if (cmbStatusSearch.getSelectedIndex() == 1) { // active
			sql += " AND (INACTIVE)  <> 'I'";
		}

		if (cmbStatusSearch.getSelectedIndex() == 2) { // in-active
			sql += " AND (INACTIVE)  = 'I'";
		}

		try {
			rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableModel model = jTable1.getModel();

		((DefaultTableModel) model).setRowCount(0);
		try {
			while (rs.next()) {
				String id = (rs.getString("R@#EXP"));
				String forms = (rs.getString("FORMS"));
				String system = (rs.getString("SYSTEM"));
				String accountname = (rs.getString("ACCTNAME"));
				String account = (rs.getString("ACCOUNT"));
				String edi9 = (rs.getString("EDI9ORD#"));
				String prod9 = (rs.getString("PROD9ORD#"));
				String purge = (rs.getString("PRGEOLD2"));
				String active = (rs.getString("INACTIVE"));

				Vector row = new Vector();

				row.add(id);
				row.add(forms);
				row.add(system);
				row.add(accountname);
				row.add(account);
				row.add(edi9);
				row.add(prod9);
				row.add(purge);
				row.add(active);

				((DefaultTableModel) model).addRow(row);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doCenterFrom() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doEdit(String id) throws SQLException, UnknownHostException {
		RLMAccountEditForm a = new RLMAccountEditForm(id);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
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
