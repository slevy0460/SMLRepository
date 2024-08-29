package stevelevy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
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

public class RLMSystemInquiryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable1;
	private JTextField txtSystemNameSearch;
	private JComboBox cmdStatusSearch;

	String systemsearch = null;
	String classname = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLMSystemInquiryForm frame = new RLMSystemInquiryForm();
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
	public RLMSystemInquiryForm() throws SQLException, UnknownHostException {
		int screen = SMLUtility.getCurrentMonitorInfo(RLMSystemInquiryForm.this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 1500, 140);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblNewLabel = new JLabel("System Name");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(49, 53, 93, 14);
		jPanelTop.add(lblNewLabel);

		txtSystemNameSearch = new JTextField();
		txtSystemNameSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtSystemNameSearch.setBounds(49, 73, 86, 20);
		jPanelTop.add(txtSystemNameSearch);
		txtSystemNameSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSearch.setIcon(new ImageIcon(RLMSystemInquiryForm.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = null;
				String value = null;
				field = "txtSystemNameSearch";
				value = txtSystemNameSearch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "cmdStatusSearch";
				value = "" + cmdStatusSearch.getSelectedIndex();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					doPopulate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(49, 107, 139, 23);
		jPanelTop.add(btnSearch);

		JLabel lblSatisSearch = new JLabel("Status");
		lblSatisSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSatisSearch.setBounds(224, 53, 55, 14);
		jPanelTop.add(lblSatisSearch);

		cmdStatusSearch = new JComboBox();
		cmdStatusSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		cmdStatusSearch.setModel(new DefaultComboBoxModel(new String[] { "All", "Active", "In-Active" }));
		cmdStatusSearch.setBounds(224, 77, 162, 20);
		jPanelTop.add(cmdStatusSearch);

		JLabel lblTitle = new JLabel("RLM System Inquiry");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setBounds(10, 10, 422, 15);
		jPanelTop.add(lblTitle);

		JLabel lblInstructions = new JLabel("Use this form to view RLM Systems");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInstructions.setBounds(10, 25, 422, 13);
		jPanelTop.add(lblInstructions);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 59, 1500, 326);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 102, 1500, 223);
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
		jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
				new String[] { "Id", "System Name", "Description", "Private IP", "Public IP", "Qsecofr",
						"Serial Number", "Active" }) {
			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jTable1.getColumnModel().getColumn(0).setCellRenderer(new CellJustifyRendererCenter());
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
		jTable1.setAutoCreateRowSorter(false);
		scrollPane.setViewportView(jTable1);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 385, 1500, 45);
		contentPane.add(jPanelButtons);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.setIcon(new ImageIcon(RLMSystemInquiryForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEdit.setIcon(new ImageIcon(RLMSystemInquiryForm.class.getResource("/images/edit.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = jTable1.getSelectedRow();
				if (row >= 0) {
					String id = jTable1.getModel().getValueAt(row, column).toString();
					try {
						doEdit(id);
					} catch (SQLException e1) {
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
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(RLMSystemInquiryForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 432, 1500, 77);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Dialog", Font.BOLD, 12));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		this.classname = className;
		setTitle("RLM System Inquiry");
		doCenterForm();
		String value = null;
		value = SMLUtility.getValue(classname, "txtSystemNameSearch", "", "GET");
		txtSystemNameSearch.setText(value);
		value = SMLUtility.getValue(classname, "cmdStatusSearch", "", "GET");

		if (value.equals(" ")) {
			value = "0";
		}
		cmdStatusSearch.setSelectedIndex(Integer.valueOf(value));
		doPopulate();
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

	private void doEdit(String id) throws SQLException, UnknownHostException {
		RLMSystemEditForm a = new RLMSystemEditForm(id);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}

	private void doPopulate() throws SQLException {
		ResultSet rs = null;
		String id;
		String forms;
		String desc;
		String privateip;
		String puplicip;
		String password;
		String serialno;
		String active;
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");
		String sql = "SELECT * FROM rlmpserv01 ";
		sql += "WHERE 1 = 1";

		if (txtSystemNameSearch.getText().trim().length() > 0) {
			String upper = txtSystemNameSearch.getText().trim().toUpperCase();
			sql += " AND (UPPER(Servername))  like '%" + upper + "%'";
		}

		if (cmdStatusSearch.getSelectedIndex() == 1) { // active
			sql += " AND (ACTIVE)  = 'YES'";
		}

		if (cmdStatusSearch.getSelectedIndex() == 2) { // IN-active
			sql += " AND (ACTIVE)  = 'NO'";
		}

		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		TableModel model = jTable1.getModel();

		((DefaultTableModel) model).setRowCount(0);
		while (rs.next()) {
			id = (rs.getString("ID"));
			forms = (rs.getString("servername"));
			desc = (rs.getString("description"));
			privateip = (rs.getString("Internalip"));
			puplicip = (rs.getString("Externalip"));
			password = (rs.getString("Qsecofrpass"));
			serialno = (rs.getString("serialno"));
			active = (rs.getString("ACTIVE"));

			Vector row = new Vector();

			row.add(id);
			row.add(forms);
			row.add(desc);
			row.add(privateip);
			row.add(puplicip);
			row.add(password);
			row.add(serialno);
			row.add(active);

			((DefaultTableModel) model).addRow(row);
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
