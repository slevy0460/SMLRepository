package stevelevy;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JButton;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Color;

public class RLMProjectInquiryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable1;
	private JTextField txtUserSearch;
	private JComboBox comboStatus;
	private JTextField txtFormsSearch;
	private JTextField txtDescriptionSearch;
	String ticket = null;
	String onedrive = null;
	String classname = getClass().getSimpleName();
	JRadioButton rdbtnSortbyPriority = new JRadioButton("Sort by Priority");
	JRadioButton rdbtnSortbyProject = new JRadioButton("Sort Project");
	String className = getClass().getSimpleName();
	JButton btnSearch = new JButton("Search");
	JProgressBar progressBar = new JProgressBar();
	JPanel panel_1 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLMProjectInquiryForm frame = new RLMProjectInquiryForm();
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
	 * @throws UnknownHostException 
	 */
	public RLMProjectInquiryForm() throws SQLException, ParseException, UnknownHostException {
		int screen = SMLUtility.getCurrentMonitorInfo(RLMProjectInquiryForm.this);
		doCheck("GET");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1500, 690);
		doCenterForm();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 1500, 200);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblUserSearch = new JLabel("Created By");
		lblUserSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUserSearch.setBounds(645, 48, 102, 17);
		jPanelTop.add(lblUserSearch);

		txtUserSearch = new JTextField();
		txtUserSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtUserSearch.setBounds(645, 67, 86, 20);
		jPanelTop.add(txtUserSearch);
		txtUserSearch.setColumns(10);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 59, 1500, 392);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 1475, 195);
		jPanelCenter.add(scrollPane);

		jTable1 = new JTable();
		jTable1.setFont(new Font("Dialog", Font.BOLD, 12));
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
				String mode = "UPDATE";
				if (e.getClickCount() == 2) {
					int column = 0;
					int row = jTable1.getSelectedRow();
					if (row >= 0) {
						String id = jTable1.getModel().getValueAt(row, column).toString();
						try {
							doEdit(id, mode);
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
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Priority", "Project#", "Customer", "Ticket#", "Description", "Created", "Requested", "Date Started", "Status", "Ticket", "Folder"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(4).setPreferredWidth(400);
		jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(250);
		jTable1.getColumnModel().getColumn(8).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(9).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(10).setPreferredWidth(80);

		jTable1.setAutoCreateRowSorter(false);

		scrollPane.setViewportView(jTable1);

		JButton btnAdd = new JButton("Add New Project");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doAdd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/plus.png")));
		btnAdd.setBounds(10, 348, 199, 25);
		jPanelCenter.add(btnAdd);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 450, 1500, 40);
		contentPane.add(jPanelButtons);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEdit.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/edit.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mode = "UPDATE";
				int column = 1;
				int row = jTable1.getSelectedRow();
				if (row >= 0) {
					String project = jTable1.getModel().getValueAt(row, column).toString();
					try {
						doEdit(project, mode);
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

		JButton btnOnedrive_1 = new JButton("OneDrive Online");
		btnOnedrive_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnOnedrive_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOneDrive();
			}
		});
		btnOnedrive_1.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/folder.png")));
		jPanelButtons.add(btnOnedrive_1);
		jPanelButtons.add(btnEdit);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});
		btnPrint.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/printer.png")));
		jPanelButtons.add(btnPrint);

		JButton btnTicket = new JButton("Ticket");
		btnTicket.setFont(new Font("Dialog", Font.BOLD, 12));
		btnTicket.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/website.png")));
		btnTicket.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int column = SMLUtility.getColumnIndex(jTable1, "Project#");
//				System.out.println("Column is : " + column);
//				int column = 1;
				int row = jTable1.getSelectedRow();
				if (row >= 0) {
					String project = jTable1.getModel().getValueAt(row, column).toString();
					String ticket = doTicket(project);
					String url = ticket.trim();
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to show ticket");
				}

			}
		});

		JButton btnExcel = new JButton("Excel");
		btnExcel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/excel.png")));
		jPanelButtons.add(btnExcel);
		jPanelButtons.add(btnTicket);

		JButton btnOnedrive = new JButton("OneDrive");
		btnOnedrive.setFont(new Font("Dialog", Font.BOLD, 12));
		btnOnedrive.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/folder.png")));
		btnOnedrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = SMLUtility.getColumnIndex(jTable1, "Project#");
//				System.out.println("Column is : " + column);
//				int column = 1;
				int row = jTable1.getSelectedRow();
				if (row >= 0) {
					String project = jTable1.getModel().getValueAt(row, column).toString();
					String folder = doFolder(project);
					String onedrive = folder.trim();
					Desktop desktop = Desktop.getDesktop();
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(onedrive));
					} catch (IOException e1) {

						JOptionPane.showMessageDialog(null, e1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to show folder");
				}

			}
		});
		jPanelButtons.add(btnOnedrive);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int column = SMLUtility.getColumnIndex(jTable1, "Project#");
				int row = jTable1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row to delete");
				} else {
					String project = jTable1.getModel().getValueAt(row, column).toString();
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
							"Verify Exit ", JOptionPane.YES_NO_OPTION);
					if (n == 0) {
						deleteIt(row, project);
						try {
							doPopulateInquiry();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/bin.png")));
		jPanelButtons.add(btnDelete);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 573, 1500, 77);
		contentPane.add(jPanelBottom);
		jPanelBottom.setLayout(null);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblClassName.setBounds(491, 7, 150, 13);
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);

		panel_1.setBounds(5, 28, 1080, 50);
		jPanelBottom.add(panel_1);
		panel_1.setLayout(null);

		progressBar.setVisible(false);
		progressBar.setBounds(3, 19, 50, 18);
		panel_1.add(progressBar);

		this.classname = className;
		setTitle("RLM Project Inquiry");

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStatus.setBounds(816, 48, 102, 17);
		jPanelTop.add(lblStatus);

		comboStatus = new JComboBox();
		comboStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		comboStatus
				.setModel(new DefaultComboBoxModel(new String[] { "All", "Open", "Complete", "In Review", "Abandon" }));
		comboStatus.setBounds(816, 67, 133, 20);
		jPanelTop.add(comboStatus);
		comboStatus.setSelectedIndex(1);

		JLabel lblFormsSearch = new JLabel("Customer");
		lblFormsSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFormsSearch.setBounds(50, 49, 66, 17);
		jPanelTop.add(lblFormsSearch);

		txtFormsSearch = new JTextField();
		txtFormsSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtFormsSearch.setColumns(10);
		txtFormsSearch.setBounds(50, 68, 66, 20);
		jPanelTop.add(txtFormsSearch);

		JLabel lblDesciptionSearch = new JLabel("Description");
		lblDesciptionSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDesciptionSearch.setBounds(227, 49, 102, 17);
		jPanelTop.add(lblDesciptionSearch);

		txtDescriptionSearch = new JTextField();
		txtDescriptionSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDescriptionSearch.setColumns(10);
		txtDescriptionSearch.setBounds(227, 67, 400, 20);
		jPanelTop.add(txtDescriptionSearch);

		JLabel lblTitle = new JLabel("RLM Project Inquiry");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setBounds(10, 10, 422, 15);
		jPanelTop.add(lblTitle);

		JLabel lblInstructions = new JLabel("Use this form to view RLM projects");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInstructions.setBounds(10, 31, 422, 13);
		jPanelTop.add(lblInstructions);

		String value = null;
		value = SMLUtility.getValue(classname, "txtFormsSearch", "", "GET");
		txtFormsSearch.setText(value);
		value = SMLUtility.getValue(classname, "txtDescriptionSearch", "", "GET");
		txtDescriptionSearch.setText(value);
		value = SMLUtility.getValue(classname, "txtUserSearch", "", "GET");
		txtUserSearch.setText(value);
		if (txtUserSearch.getText() == null) {
			txtUserSearch.setText(System.getProperty("user.name"));
		}

		value = SMLUtility.getValue(classname, "comboStatus", "", "GET");
		if (value.equals(" ")) {
			value = "0";
		}
		comboStatus.setSelectedIndex(Integer.valueOf(value));

		JPanel panel = new JPanel();
		panel.setBounds(10, 155, 1064, 34);
		jPanelTop.add(panel);
		panel.setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSearch.setBounds(10, 5, 200, 25);
		panel.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(RLMProjectInquiryForm.class.getResource("/images/search.png")));
		rdbtnSortbyPriority.setFont(new Font("Dialog", Font.BOLD, 12));

//		JRadioButton rdbtnSortbyPriority = new JRadioButton("Sort by Priority");
		rdbtnSortbyPriority.setBounds(50, 96, 125, 25);
		jPanelTop.add(rdbtnSortbyPriority);
		rdbtnSortbyProject.setFont(new Font("Dialog", Font.BOLD, 12));

//		JRadioButton rdbtnSortbyProject = new JRadioButton("Sort by Project");
		rdbtnSortbyProject.setBounds(50, 121, 125, 25);
		jPanelTop.add(rdbtnSortbyProject);

		ButtonGroup bgroupsort = new ButtonGroup();
		bgroupsort.add(rdbtnSortbyPriority);
		bgroupsort.add(rdbtnSortbyProject);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = null;
				String value = null;

				field = "txtFormsSearch";
				value = txtFormsSearch.getText().trim();
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

				field = "txtUserSearch";
				value = txtUserSearch.getText().trim();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				field = "comboStatus";
				value = "" + comboStatus.getSelectedIndex();
				try {
					value = SMLUtility.getValue(classname, field, value, "CHK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					doPopulateInquiry();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		doPopulateInquiry();

	}

	private void doEdit(String project, String mode) throws SQLException, UnknownHostException {
		RLMProjectEditForm a = new RLMProjectEditForm(project, mode);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private String doTicket(String project) {
		Connection dbCon = null;
		ResultSet rs = null;
		String userselect = "RLMSTEVE";
		String sql = null;
		String checkblanks;
		sql = "SELECT * FROM QUSRSYS.PSRC999J ";
		sql += "WHERE 1 = 1 ";
		sql += "AND PRJ999 = '" + project + "' ";

		try {
			try {

				rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");

				while (rs.next()) {
					String ticket = (rs.getString("TCK999"));
					return ticket;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
		return null;
	}

	private String doFolder(String project) {
		Connection dbCon = null;
		ResultSet rs = null;
		String userselect = "RLMSTEVE";
		String sql = null;
		String checkblanks;
		sql = "SELECT * FROM QUSRSYS.PSRC999J ";
		sql += "WHERE 1 = 1 ";
		sql += "AND PRJ999 = '" + project + "' ";

		try {
			try {

				rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");

				while (rs.next()) {
					String folder = (rs.getString("ONE999"));
					return folder;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
		return null;
	}

	private void doAdd() throws SQLException, UnknownHostException {
		String id = null;
		int lastproject = 0;
		int newproject = 0;
		String mode = "ADD";
		lastproject = dogetLastProject();
		newproject = lastproject + 1;
		id = Integer.toString(newproject);
		doEdit(id, mode);
	}

	private void deleteIt(int row, String project) {
		doDeleteProject799(project);
		doDeleteProject999(project);
	}

	private void doDeleteProject799(String project) {
		String user = "RLMSTEVE";
		Connection dbCon = null;
		ResultSet rs = null;
		String sql = "DELETE FROM QUSRSYS.PSRC799J ";
		sql += " WHERE PRJ799 = " + project;

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SYSP03", "DLT");
				;
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
			}
		} finally {

		}
	}

	private void doDeleteProject999(String project) {
		String user = "RLMSTEVE";
		Connection dbCon = null;
		ResultSet rs = null;
		String sql = "DELETE FROM QUSRSYS.PSRC999J ";
		sql += " WHERE PRJ999 = " + project;

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SYSP03", "DLT");
				;
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
			}
		} finally {

		}
	}

	private int dogetLastProject() {
		Connection dbCon = null;
		ResultSet rs = null;

		String sql = "SELECT MAX(PRJ799) AS PRJ799 ";
		sql += "FROM QUSRSYS.PSRC799J ";

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");
				;
				TableModel model = jTable1.getModel();

				((DefaultTableModel) model).setRowCount(0);
				while (rs.next()) {
					int project = Integer.valueOf(rs.getString("PRJ799"));
					return project;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
		}
		return 0;
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
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

	private void doPopulateInquiry() throws SQLException, ParseException {
		btnSearch.setEnabled(false);
		panel_1.add(this.progressBar);
		this.progressBar.setVisible(true);
		this.progressBar.setIndeterminate(true);
		doPopulate();
		btnSearch.setEnabled(true);
		this.progressBar.setVisible(false);
		this.progressBar.setIndeterminate(false);
		panel_1.remove(this.progressBar);
	}

	private void doPopulate() throws ParseException {
		Date date = null;
		// Create a SimpleDateFormat with the date format from the database
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyyMMdd");
		// Create a SimpleDateFormat with the desired date format
		SimpleDateFormat sdfFormat = new SimpleDateFormat("MMMM dd yyyy");
		doCheck("CHK");
		Connection dbCon = null;
		ResultSet rs = null;

		String sql = "SELECT A.PRJ799, A.PRD799, A.USR799, A.RBY799, A.COM799, B.ACT999, B.TC#999, B.TCK999, B.ONE999, A.DAT799, B.PRY999, ";
		sql += "(CASE ";
		sql += "WHEN A.COM799 = 'C' THEN 'Complete' ";
		sql += "WHEN A.COM799 = '?' THEN 'In Review' ";
		sql += "WHEN A.COM799 = '*' THEN 'Abandon' ";
		sql += "ELSE 'Open' ";
		sql += "END) AS STATUS ";
		sql += "FROM QUSRSYS.PSRC799J A ";
		sql += "LEFT JOIN QUSRSYS.PSRC999J B ON B.PRJ999 = A.PRJ799 ";
		sql += "WHERE 1 = 1 ";

		if (txtUserSearch.getText().trim().length() > 0) {
			String upper = txtUserSearch.getText().trim().toUpperCase();
			sql += " AND (A.USR799)  like '%" + upper + "%'";
		}

		if (txtFormsSearch.getText().trim().length() > 0) {
			String upper = txtFormsSearch.getText().trim().toUpperCase();
			sql += " AND (B.ACT999)  like '%" + upper + "%'";
		}

		if (txtDescriptionSearch.getText().trim().length() > 0) {
			String upper = txtDescriptionSearch.getText().trim().toUpperCase();
			sql += " AND UPPER(A.PRD799)  like '%" + upper + "%'";
		}

		if (comboStatus.getSelectedIndex() != 0) {
			int index = comboStatus.getSelectedIndex();

			if (index == 1) {
				sql += " AND (TRIM(A.COM799))  = ' " + " " + " '";
			}

			if (index == 2) {
				sql += " AND (TRIM(A.COM799))  = 'C'";
			}

			if (index == 3) {
				sql += " AND (TRIM(A.COM799))  = '?'";
			}

			if (index == 4) {
				sql += " AND (TRIM(A.COM799)))  = = '*' ";
			}
		}

//		int index = comboSort.getSelectedIndex();
//
//		if (index == 1) {
//			sql += " ORDER BY B.PRY999, A.PRJ799 DESC";
//		} else {
//			sql += " ORDER BY A.PRJ799 DESC";
//		}

		if (rdbtnSortbyPriority.isSelected()) {
			sql += " ORDER BY B.PRY999, A.PRJ799 DESC ";
		} else {
			sql += " ORDER BY A.PRJ799 DESC ";
		}

//		System.out.println("SQL is : " + sql);
		try {
			try {

				rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");

				TableModel model = jTable1.getModel();

				((DefaultTableModel) model).setRowCount(0);
				while (rs.next()) {
					String project = (rs.getString("PRJ799"));
					String forms = (rs.getString("ACT999"));
					String ticketno = (rs.getString("TC#999"));
					int x = 0;
					if (ticketno != " " && ticketno != null) {
						x = Integer.valueOf(ticketno);
					}
					if (x == 0) {
						ticketno = " ";
					}
					String priority = (rs.getString("PRY999"));
					String description = (rs.getString("PRD799"));
					String createdby = (rs.getString("USR799"));
					String requestedby = (rs.getString("RBY799"));
					String datestarted = (rs.getString("DAT799"));
					date = sdfParse.parse(datestarted);
					// Format the date to a string in the desired format
					datestarted = sdfFormat.format(date);
					String status = (rs.getString("STATUS"));
					this.ticket = (rs.getString("TCK999"));
					this.onedrive = (rs.getString("ONE999"));

					Boolean ticketyn = false;
					if (this.ticket != null && this.ticket.trim().length() > 0) {
						ticketyn = true;
					}

					Boolean folderyn = false;
					if (this.onedrive != null && this.onedrive.trim().length() > 0) {
						folderyn = true;
					}

					Vector row = new Vector();

					row.add(priority);
					row.add(project);
					row.add(forms);
					row.add(ticketno);
					row.add(description);
					row.add(createdby);
					row.add(requestedby);
					row.add(datestarted);
					row.add(status);
					row.add(ticketyn);
					row.add(folderyn);

					((DefaultTableModel) model).addRow(row);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				dbCon.close();
			} catch (Exception e) {
			}
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

	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = jTable1.getModel();
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

		String filePath = "S:\\Users\\slevy\\OneDrive\\Desktop/RLMProjects.xlsx";
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

	private void doCheck(String type) {
		String field = "rdbtnSortbydate";
		Boolean isBoolean = rdbtnSortbyPriority.isSelected();
		String value = String.valueOf(isBoolean);
		try {
			value = SMLUtility.getValue(className, field, value, type);
			if (type.equals("GET")) {
				isBoolean = Boolean.valueOf(value);
				if (isBoolean) {
					rdbtnSortbyPriority.setSelected(true);
					rdbtnSortbyProject.setSelected(false);
				} else {
					rdbtnSortbyPriority.setSelected(false);
					rdbtnSortbyProject.setSelected(true);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void doOneDrive() {
		String onedrive = "https://apteanonline-my.sharepoint.com/";
		Desktop desktop = Desktop.getDesktop();
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(onedrive));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
