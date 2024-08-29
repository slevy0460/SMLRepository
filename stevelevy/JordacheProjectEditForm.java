package stevelevy;

import java.awt.Color;
import java.awt.Desktop;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class JordacheProjectEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProjectNumber;
	private JTextField txtUser;
	private JTextField txtRequstedby;
	private JTextField txtSupervisedby;
	private JDateChooser jDateChooserStarted;
	private JDateChooser jDateChooserTested;
	private JDateChooser jDateChooserProduction;
	private JComboBox cmbLanguage;
	private JComboBox cmbTypeOfChange;
	private JTextField txtEDIDocument;
	private JTextField txtProjectDescription;
	private JTextField txtAdditionalNotes;
	private JComboBox cmbStatus;
	private JComboBox cmbTypeOfApplication;

	String ticket = null;
	String onedrive = null;
	String username = null;
	String password = null;
	String Driver = null;
	String URL = "jdbc:as400://172.20.193.10";
	String formsearch = null;
	Boolean ticketyn = false;
	Boolean onedriveyn = false;
	private JTextField txtAreaTicket;
	private JTextField txtOneDriveFolder;
	private JTextField txtForms;
	private JTextField txtTicketNo;
	String mode = null;
	private JTextField txtPriority;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JordacheProjectEditForm frame = new JordacheProjectEditForm(null, null, null, null, null, null);
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
	 * @param pass
	 * @param user
	 * @param url
	 * @param drv
	 * @param mode
	 */
	public JordacheProjectEditForm(String project, String drv, String url, String user, String pass, String modein) {
		this.mode = modein;
		this.Driver = drv;
		this.URL = url;
		this.username = user;
		this.password = pass;
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(JordacheProjectEditForm.this);
		} catch (UnknownHostException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1328, 743);
		doCenterForm();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 1300, 61);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblProjectNumber = new JLabel("Project Number");
		lblProjectNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblProjectNumber.setBounds(10, 23, 96, 14);
		jPanelTop.add(lblProjectNumber);

		txtProjectNumber = new JTextField();
		txtProjectNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		txtProjectNumber.setEditable(false);
		txtProjectNumber.setBounds(234, 20, 86, 20);
		jPanelTop.add(txtProjectNumber);
		txtProjectNumber.setColumns(10);
		txtProjectNumber.setText(project);
		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 624, 1300, 48);
		contentPane.add(jPanelButtons);

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.setFont(new Font("Dialog", Font.BOLD, 12));
		btnInquiry.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/task-planning.png")));
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doUpdate();
					doInquiry();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jPanelButtons.add(btnInquiry);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		btnUpdate.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/updated.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnUpdate.setText("Update");
			}
		});
		jPanelButtons.add(btnUpdate);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/printer.png")));
		jPanelButtons.add(btnPrint);

		JButton btnTicket = new JButton("Ticket");
		btnTicket.setFont(new Font("Dialog", Font.BOLD, 12));
		btnTicket.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/website.png")));
		jPanelButtons.add(btnTicket);

		JButton btnOnedrive = new JButton("OneDrive");
		btnOnedrive.setFont(new Font("Dialog", Font.BOLD, 12));
		btnOnedrive.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/folder.png")));
		jPanelButtons.add(btnOnedrive);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(JordacheProjectEditForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);
		btnOnedrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Desktop desktop = Desktop.getDesktop();
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(onedrive));
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnTicket.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String url = ticket.trim();
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 671, 1300, 33);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Dialog", Font.BOLD, 12));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 72, 1302, 548);
		contentPane.add(tabbedPane);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setSize(1302, 520);
		tabbedPane.addTab("General Information", null, jPanelCenter, null);
		jPanelCenter.setLayout(null);

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUser.setBounds(10, 34, 162, 20);
		jPanelCenter.add(lblUser);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Dialog", Font.BOLD, 12));
		txtUser.setColumns(10);
		txtUser.setBounds(234, 34, 86, 20);
		jPanelCenter.add(txtUser);
		txtUser.setText("RLMSTEVE");
		JLabel lblRequstedby = new JLabel("Requested By");
		lblRequstedby.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRequstedby.setBounds(10, 82, 195, 14);
		jPanelCenter.add(lblRequstedby);

		txtRequstedby = new JTextField();
		txtRequstedby.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRequstedby.setText("Johnny");
		txtRequstedby.setColumns(10);
		txtRequstedby.setBounds(234, 82, 86, 20);
		jPanelCenter.add(txtRequstedby);

		JLabel lblSupervisedBy = new JLabel("Supervised By");
		lblSupervisedBy.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSupervisedBy.setBounds(362, 82, 96, 14);
		jPanelCenter.add(lblSupervisedBy);

		txtSupervisedby = new JTextField();
		txtSupervisedby.setFont(new Font("Dialog", Font.BOLD, 12));
		txtSupervisedby.setText("Johnny");
		txtSupervisedby.setColumns(10);
		txtSupervisedby.setBounds(473, 82, 86, 20);
		jPanelCenter.add(txtSupervisedby);

		JLabel lblDateStarted = new JLabel("Date Started");
		lblDateStarted.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDateStarted.setBounds(10, 130, 195, 14);
		jPanelCenter.add(lblDateStarted);

		jDateChooserStarted = new JDateChooser();
		jDateChooserStarted.setDateFormatString("yyyyMMdd");
		jDateChooserStarted.setBounds(234, 130, 90, 20);
		jPanelCenter.add(jDateChooserStarted);
		Date today = new Date();
		jDateChooserStarted.setDate(today);

		JLabel lblDateTested = new JLabel("Date Tested");
		lblDateTested.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDateTested.setBounds(362, 130, 96, 14);
		jPanelCenter.add(lblDateTested);

		jDateChooserTested = new JDateChooser();
		jDateChooserTested.setDateFormatString("yyyyMMdd");
		jDateChooserTested.setBounds(473, 130, 90, 20);
		jPanelCenter.add(jDateChooserTested);

		JLabel lblDateToProduction = new JLabel("Date to Production");
		lblDateToProduction.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDateToProduction.setBounds(585, 130, 150, 20);
		jPanelCenter.add(lblDateToProduction);

		jDateChooserProduction = new JDateChooser();
		jDateChooserProduction.setDateFormatString("yyyyMMdd");
		jDateChooserProduction.setBounds(750, 130, 90, 20);
		jPanelCenter.add(jDateChooserProduction);

		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLanguage.setBounds(10, 178, 195, 14);
		jPanelCenter.add(lblLanguage);

		cmbLanguage = new JComboBox();
		cmbLanguage.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbLanguage.setModel(new DefaultComboBoxModel(new String[] { "RPGLE", "JAVA", "PF" }));
		cmbLanguage.setBounds(234, 176, 86, 20);
		jPanelCenter.add(cmbLanguage);

		JLabel lblTypeofChange = new JLabel("Type of Change");
		lblTypeofChange.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTypeofChange.setBounds(10, 226, 195, 14);
		jPanelCenter.add(lblTypeofChange);

		cmbTypeOfChange = new JComboBox();
		cmbTypeOfChange.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbTypeOfChange.setModel(new DefaultComboBoxModel(new String[] { "Report", "Update", "Inquiry" }));
		cmbTypeOfChange.setBounds(234, 225, 86, 20);
		jPanelCenter.add(cmbTypeOfChange);

		JLabel lblEdiDocument = new JLabel("EDI Document");
		lblEdiDocument.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEdiDocument.setBounds(10, 322, 195, 14);
		jPanelCenter.add(lblEdiDocument);

		txtEDIDocument = new JTextField();
		txtEDIDocument.setFont(new Font("Dialog", Font.BOLD, 12));
		txtEDIDocument.setColumns(10);
		txtEDIDocument.setBounds(234, 323, 86, 20);
		jPanelCenter.add(txtEDIDocument);

		JLabel lblProjectDescription = new JLabel("Project Description");
		lblProjectDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		lblProjectDescription.setBounds(10, 370, 195, 14);
		jPanelCenter.add(lblProjectDescription);

		txtProjectDescription = new JTextField();
		txtProjectDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		txtProjectDescription.setColumns(10);
		txtProjectDescription.setBounds(234, 372, 680, 20);
		jPanelCenter.add(txtProjectDescription);
		txtProjectDescription.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtProjectDescription.getText().length() >= 80) // limit textfield to 80 characters
					e.consume();
			}
		});

		JLabel lblAdditionalNotes = new JLabel("Additional Notes");
		lblAdditionalNotes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAdditionalNotes.setBounds(10, 418, 195, 14);
		jPanelCenter.add(lblAdditionalNotes);

		txtAdditionalNotes = new JTextField();
		txtAdditionalNotes.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAdditionalNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtAdditionalNotes.getText().length() >= 240) // limit textfield to 80 characters
					e.consume();
			}
		});
		txtAdditionalNotes.setColumns(10);
		txtAdditionalNotes.setBounds(234, 421, 1042, 20);
		jPanelCenter.add(txtAdditionalNotes);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStatus.setBounds(10, 466, 195, 14);
		jPanelCenter.add(lblStatus);

		cmbStatus = new JComboBox();
		cmbStatus.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] { "Open", "Complete", "In Review", "Abandon" }));
		cmbStatus.setBounds(234, 470, 162, 20);
		jPanelCenter.add(cmbStatus);

		JLabel lblTypeOfApplication = new JLabel("Type Of Application");
		lblTypeOfApplication.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTypeOfApplication.setBounds(10, 274, 195, 14);
		jPanelCenter.add(lblTypeOfApplication);

		cmbTypeOfApplication = new JComboBox();
		cmbTypeOfApplication.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbTypeOfApplication
				.setModel(new DefaultComboBoxModel(new String[] { "Sales", "Production", "Warehouse", "Finance" }));
		cmbTypeOfApplication.setBounds(234, 274, 162, 20);
		jPanelCenter.add(cmbTypeOfApplication);

		JLabel lblCustomerForms = new JLabel("Customer Forms");
		lblCustomerForms.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCustomerForms.setBounds(362, 34, 96, 14);
		jPanelCenter.add(lblCustomerForms);

		txtForms = new JTextField();
		txtForms.setFont(new Font("Dialog", Font.BOLD, 12));
		txtForms.setColumns(10);
		txtForms.setBounds(473, 34, 86, 20);
		jPanelCenter.add(txtForms);

		JPanel JPanelCenter2 = new JPanel();
		tabbedPane.addTab("Ticket/Folder Information", null, JPanelCenter2, null);
		JPanelCenter2.setLayout(null);

		JLabel lblTicket = new JLabel("Ticket");
		lblTicket.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTicket.setBounds(24, 28, 137, 26);
		JPanelCenter2.add(lblTicket);

		txtAreaTicket = new JTextField();
		txtAreaTicket.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAreaTicket.setBounds(24, 67, 1263, 20);
		JPanelCenter2.add(txtAreaTicket);
		txtAreaTicket.setColumns(10);

		JLabel lblFolder = new JLabel("Folder");
		lblFolder.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFolder.setBounds(24, 108, 137, 26);
		JPanelCenter2.add(lblFolder);

		txtOneDriveFolder = new JTextField();
		txtOneDriveFolder.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtOneDriveFolder.setColumns(10);
		txtOneDriveFolder.setBounds(24, 132, 1263, 20);
		JPanelCenter2.add(txtOneDriveFolder);

		txtTicketNo = new JTextField();
		txtTicketNo.setColumns(10);
		txtTicketNo.setBounds(75, 31, 86, 20);
		JPanelCenter2.add(txtTicketNo);

		setTitle("Jordache Project Edit/Maintenance");
		
		if (mode.equals("ADD")) {
			btnUpdate.setText("Add");
		}
		txtForms.setText("JORD");
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPriority.setBounds(606, 34, 46, 14);
		jPanelCenter.add(lblPriority);
		
		txtPriority = new JTextField();
		txtPriority.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPriority.setText("99");
		txtPriority.setColumns(10);
		txtPriority.setBounds(662, 34, 86, 20);
		jPanelCenter.add(txtPriority);
		doPopulate(project);

		if (ticketyn == false) {
			btnTicket.setEnabled(false);
		} else {
			btnTicket.setEnabled(true);
		}

		if (onedriveyn == false) {
			btnOnedrive.setEnabled(false);
		} else {
			btnOnedrive.setEnabled(true);
		}

	}

	private void doMenu() throws SQLException {
//		doUpdate();
		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
	}

	private void doInquiry() throws SQLException {
		JordacheProjectInquiryForm a = new JordacheProjectInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
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

	private void doPopulate(String project) {
		ResultSet rs = null;
		String userselect = "RLMSL";
		String sql = null;
		String stringdate = null;
		int intdate = 0;
		Date date = null;
		String checkblanks;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		sql = "SELECT * FROM RLMPGM.PSRC799J ";
		sql += "LEFT JOIN RLMFILES.PSRC999J ON PRJ999 = PRJ799 ";
		sql += "WHERE 1 = 1 ";
		sql += "AND PRJ799 = '" + project + "' ";

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "JORD", "INQ");

				while (rs.next()) {
					txtProjectNumber.setText(rs.getString("PRJ799"));
					txtForms.setText(rs.getString("ACT999"));
					txtUser.setText(rs.getString("USR799"));
					txtRequstedby.setText(rs.getString("RBY799"));
					txtSupervisedby.setText(rs.getString("SBY799"));
					// Start Date
					stringdate = (rs.getString("DAT799"));
					intdate = Integer.parseInt(stringdate);
					if (intdate != 0) {
						try {
							date = dateformat.parse(stringdate);
							jDateChooserStarted.setDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						Date today = new Date();
						jDateChooserStarted.setDate(date);
					}

					// Test Date
					stringdate = (rs.getString("DTT799"));
					intdate = Integer.parseInt(stringdate);
					if (intdate != 0) {
						try {
							date = dateformat.parse(stringdate);
							jDateChooserTested.setDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}

					}

					// Production Date
					stringdate = (rs.getString("DPP799"));
					intdate = Integer.parseInt(stringdate);
					if (intdate != 0) {
						try {
							date = dateformat.parse(stringdate);
							jDateChooserProduction.setDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					if (rs.getString("LNG799").trim().equals("RPGLE")) {
						cmbLanguage.setSelectedIndex(0);
					} else {
						if (rs.getString("LNG799").trim().equals("JAVA")) {
							cmbLanguage.setSelectedIndex(1);
						} else {
							cmbLanguage.setSelectedIndex(2);
						}
					}
					if (rs.getString("UPD799").trim().equals("R")) {
						cmbTypeOfChange.setSelectedIndex(0);
					} else {
						if (rs.getString("UPD799").trim().equals("U")) {
							cmbTypeOfChange.setSelectedIndex(1);
						} else {
							cmbTypeOfChange.setSelectedIndex(2);
						}
					}

					if (rs.getString("APL799").trim().equals("S")) {
						cmbTypeOfApplication.setSelectedIndex(0);
					} else {
						if (rs.getString("APL799").trim().equals("P")) {
							cmbTypeOfApplication.setSelectedIndex(1);
						} else {
							if (rs.getString("APL799").trim().equals("W")) {
								cmbTypeOfApplication.setSelectedIndex(2);
							} else {
								cmbTypeOfApplication.setSelectedIndex(3);
							}
						}
					}

					txtEDIDocument.setText(rs.getString("DOC799"));
					txtProjectDescription.setText(rs.getString("PRD799"));
					txtAdditionalNotes.setText(rs.getString("PEC799"));

					if (rs.getString("COM799").trim().equals("*")) {
						cmbStatus.setSelectedIndex(3);
					} else {
						if (rs.getString("COM799").trim().equals("?")) {
							cmbStatus.setSelectedIndex(2);
						} else {
							if (rs.getString("COM799").trim().equals("C")) {
								cmbStatus.setSelectedIndex(1);
							} else {
								cmbStatus.setSelectedIndex(0);
							}
						}
					}

					String ticketno = (rs.getString("TC#999"));
					if (ticketno == null) {
						txtTicketNo.setText(" ");
					} else {
						txtTicketNo.setText(rs.getString("TC#999"));
					}

					this.ticket = (rs.getString("TCK999"));
					this.onedrive = (rs.getString("ONE999"));
					if (this.ticket != null) {
						txtAreaTicket.setText(this.ticket.trim());
					}

					if (this.onedrive != null) {
						txtOneDriveFolder.setText(this.onedrive.trim());
					}
					txtPriority.setText(rs.getString("PRY999"));
					checkblanks = txtUser.getText();
					if (checkblanks == null || checkblanks.trim().length() == 0) {
						txtUser.setText("RLMSTEVE");
					}
					checkblanks = txtRequstedby.getText();
					if (checkblanks == null || checkblanks.trim().length() == 0) {
						txtRequstedby.setText("RLMSTEVE");
					}
					checkblanks = txtSupervisedby.getText();
					if (checkblanks == null || checkblanks.trim().length() == 0) {
						txtSupervisedby.setText("RLMSTEVE");
					}

					if (this.ticket != null && this.ticket.trim().length() > 0) {
						ticketyn = true;
					} else {
						ticketyn = false;
					}

					if (this.onedrive != null && this.onedrive.trim().length() > 0) {
						onedriveyn = true;
					} else {
						onedriveyn = false;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
	}

	private void doInsert799(String project, String mode) {
		Connection dbCon = null;
		ResultSet rs = null;
		String language = null;
		String startdate = null;
		String testdate = null;
		String proddate = null;
		String document = null;
		String selectedvalue = null;
		int selectedindex = 0;
		Object selectedItem = null;

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");

		String sql = null;

		sql = "INSERT INTO RLMPGM.PSRC799J ";
		sql += "(USR799, DAT799, PRJ799, RBY799, PRD799, ";
		sql += "DTT799, DPP799, SBY799, LNG799, UPD799, ";
		sql += "APL799, PEC799, DOC799, COM799) ";

		sql += "VALUES(";

		sql += "'" + txtUser.getText().trim().toUpperCase() + "', ";
		if (jDateChooserStarted.getDate() != null) {
			startdate = dateformat.format(jDateChooserStarted.getDate());
			sql += "" + startdate.trim() + ", ";
		} else {
			startdate = "0";
			sql += "" + startdate.trim() + ", ";
		}
		sql += "" + txtProjectNumber.getText().trim() + ", ";
		sql += "'" + txtRequstedby.getText().trim().toUpperCase() + "', ";
		sql += "'" + txtProjectDescription.getText().trim() + "', ";
		if (jDateChooserTested.getDate() != null) {
			testdate = dateformat.format(jDateChooserTested.getDate());
			sql += "" + testdate.trim() + ", ";
		} else {
			testdate = "0";
			sql += "" + testdate.trim() + ", ";

		}
		if (jDateChooserProduction.getDate() != null) {
			proddate = dateformat.format(jDateChooserProduction.getDate());
			sql += "" + proddate.trim() + ", ";
		} else {
			proddate = "0";
			sql += "" + proddate.trim() + ", ";
		}
		sql += "'" + txtSupervisedby.getText().trim().toUpperCase() + "', ";
		selectedItem = cmbLanguage.getSelectedItem();
		language = selectedItem.toString();
		language = language.trim();
		sql += "'" + language + "', ";
		selectedindex = cmbTypeOfChange.getSelectedIndex();
		if (selectedindex == 0) {
			selectedvalue = "R";
		} else {
			if (selectedindex == 1) {
				selectedvalue = "U";
			} else {
				selectedvalue = "I";
			}
		}
		sql += "'" + selectedvalue + "', ";
		selectedindex = cmbTypeOfApplication.getSelectedIndex();
		if (selectedindex == 0) {
			selectedvalue = "S";
		} else {
			if (selectedindex == 1) {
				selectedvalue = "P";
			} else {
				if (selectedindex == 2) {
					selectedvalue = "W";
				} else {
					selectedvalue = "F";
				}
			}
		}
		sql += "'" + selectedvalue + "', ";
		sql += "'" + txtAdditionalNotes.getText().trim() + "', ";
		document = "0";
		sql += "" + document.trim() + ", ";
		selectedindex = cmbStatus.getSelectedIndex();
		if (selectedindex == 3) {
			selectedvalue = "*";
		} else {
			if (selectedindex == 2) {
				selectedvalue = "?";
			} else {
				if (selectedindex == 1) {
					selectedvalue = "C";
				} else {
					selectedvalue = " ";
				}
			}
		}
		sql += "'" + selectedvalue + "')  ";

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "JORD", "INS");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
		}

	}

	private void doInsert999(String project, String mode) throws SQLException {
		Connection dbCon = null;
		ResultSet rs = null;
		String type = null;
		String sql = null;
		mode = "UPDATE";
		this.mode = "UPDATE";
		
		if (txtTicketNo.getText() == null || txtTicketNo.getText().length() == 0) {
			txtTicketNo.setText("0");
		}
		
		int tckno = 0;
		if (tckno != 0) {
			tckno = Integer.valueOf(txtTicketNo.getText().trim());
		}

		sql = "INSERT INTO RLMFILES.PSRC999J ";
		sql += "(PRJ999, ACT999, TC#999, TCK999, ONE999, PRY999 ) ";
		sql += "VALUES(";

		sql += "'" + txtProjectNumber.getText().trim() + "', ";
		sql += "'" + txtForms.getText().trim().toUpperCase() + "', ";
		sql += "'" + tckno + "', ";
//		sql += "'" + txtTicketNo.getText().trim() + "', ";
		sql += "'" + txtAreaTicket.getText().trim() + "', ";
		sql += "'" + txtOneDriveFolder.getText().trim() + "', ";
		sql += "'" + txtPriority.getText().trim() + "') ";
//		System.out.println("SQL is : " + sql);

		rs = SMLUtility.getResultSet(sql, "JORD", "INS");
	}

	private void doUpdate() throws SQLException {
		String mode = this.mode;
		if (mode == "UPDATE") {
			doUpdate799(txtProjectNumber.getText(), mode);
			doUpdate999(txtProjectNumber.getText(), mode);
		} else {
			doInsert799(txtProjectNumber.getText(), mode);
			doInsert999(txtProjectNumber.getText(), mode);
		}

	}

	private void doUpdate799(String project, String mode) throws SQLException {
		Connection dbCon = null;
		ResultSet rs = null;
		String language = null;
		String startdate = null;
		String testdate = null;
		String proddate = null;
		String selectedvalue = null;
		int selectedindex = 0;
		Object selectedItem = null;

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");

		if (jDateChooserStarted.getDate() != null) {
			startdate = dateformat.format(jDateChooserStarted.getDate());
			try {
				Date date = dateformat.parse(startdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (jDateChooserTested.getDate() != null) {
			testdate = dateformat.format(jDateChooserTested.getDate());
			try {
				Date date = dateformat.parse(testdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (jDateChooserProduction.getDate() != null) {
			proddate = dateformat.format(jDateChooserProduction.getDate());
			testdate = dateformat.format(jDateChooserTested.getDate());
			try {
				Date date = dateformat.parse(proddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		String sql = null;

		sql = "UPDATE RLMPGM.PSRC799J SET ";
		sql += "RBY799 = '" + txtRequstedby.getText().trim().toUpperCase() + "', ";
		sql += "SBY799 = '" + txtSupervisedby.getText().trim().toUpperCase() + "', ";
		if (jDateChooserStarted.getDate() != null) {
			sql += "DAT799 = '" + startdate.trim() + "', ";
		} else {
			startdate = "0";
			sql += "DAT799 = '" + startdate.trim() + "', ";
		}

		if (jDateChooserTested.getDate() != null) {
			sql += "DTT799 = '" + testdate.trim() + "', ";
		} else {
			testdate = "0";
			sql += "DTT799 = '" + testdate.trim() + "', ";
		}

		if (jDateChooserProduction.getDate() != null) {
			sql += "DPP799 = '" + proddate.trim() + "', ";
		} else {
			proddate = "0";
			sql += "DPP799 = '" + proddate.trim() + "', ";
		}

		selectedItem = cmbLanguage.getSelectedItem();
		language = selectedItem.toString();
		language = language.trim();
		sql += "LNG799 = '" + language + "', ";
		selectedindex = cmbTypeOfChange.getSelectedIndex();
		if (selectedindex == 0) {
			selectedvalue = "R";
		} else {
			if (selectedindex == 1) {
				selectedvalue = "U";
			} else {
				selectedvalue = "I";
			}
		}
		sql += "UPD799 = '" + selectedvalue + "', ";
		selectedindex = cmbTypeOfApplication.getSelectedIndex();

		if (selectedindex == 0) {
			selectedvalue = "S";
		} else {
			if (selectedindex == 1) {
				selectedvalue = "P";
			} else {
				if (selectedindex == 2) {
					selectedvalue = "W";
				} else {
					selectedvalue = "F";
				}
			}
		}
		sql += "APL799 = '" + selectedvalue + "', ";

		sql += "DOC799 = '" + txtEDIDocument.getText().trim() + "', ";
		sql += "PRD799 = '" + txtProjectDescription.getText().trim() + "', ";
		sql += "PEC799 = '" + txtAdditionalNotes.getText().trim() + "', ";

		selectedindex = cmbStatus.getSelectedIndex();
		if (selectedindex == 3) {
			selectedvalue = "*";
		} else {
			if (selectedindex == 2) {
				selectedvalue = "?";
			} else {
				if (selectedindex == 1) {
					selectedvalue = "C";
				} else {
					selectedvalue = " ";
				}
			}
		}
		sql += "COM799 = '" + selectedvalue + "'  ";
		sql += " WHERE PRJ799 = " + project;

		rs = SMLUtility.getResultSet(sql, "JORD", "UPD");
	}

	private void doUpdate999(String project, String mode) throws SQLException {
		Connection dbCon = null;
		ResultSet rs = null;
		String type = null;

		String sql = null;

		sql = "UPDATE RLMFILES.PSRC999J SET ";
		sql += "ACT999 = '" + txtForms.getText().trim().toUpperCase() + "', ";
		sql += "TC#999 = '" + txtTicketNo.getText().trim() + "', ";
		sql += "TCK999 = '" + txtAreaTicket.getText().trim() + "', ";
		sql += "ONE999 = '" + txtOneDriveFolder.getText().trim() + "', ";
		sql += "PRY999 = '" + txtPriority.getText().trim() + "' ";
		sql += " WHERE PRJ999 = " + project;

		rs = SMLUtility.getResultSet(sql, "JORD", "UPD");

	}
}
