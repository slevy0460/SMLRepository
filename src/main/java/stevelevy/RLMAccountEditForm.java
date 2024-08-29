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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class RLMAccountEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAccountName;
	private JTextField txtForms;
	private JTextField txtSystem;
	private JTextField txtAccountNumber;
	private JTextField txtPrimaryManager;
	private JTextField txtecondaryManager;
	private JComboBox cmbActive;
	private JComboBox cmbRFUser;
	private JComboBox btnEDI9Ord;
	private JComboBox cmbProductionOrder;
	private JComboBox cmbPurged;
	private JTextField txtPurgewrkbk;

	String username = null;
	String password = null;
	String Driver = null;
	String URL = null;
	String formsearch = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLMAccountEditForm frame = new RLMAccountEditForm(null);
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
	 * @throws SQLException 
	 * @throws UnknownHostException 
	 */
	public RLMAccountEditForm(String id) throws UnknownHostException, SQLException {
		int screen = SMLUtility.getCurrentMonitorInfo(RLMAccountEditForm.this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 856, 61);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblAccountName = new JLabel("Account Name");
		lblAccountName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAccountName.setBounds(10, 11, 90, 14);
		jPanelTop.add(lblAccountName);

		txtAccountName = new JTextField();
		txtAccountName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAccountName.setBounds(254, 11, 280, 20);
		jPanelTop.add(txtAccountName);
		txtAccountName.setColumns(10);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 59, 856, 364);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JLabel lblForms = new JLabel("Forms");
		lblForms.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblForms.setBounds(10, 17, 163, 14);
		jPanelCenter.add(lblForms);

		txtForms = new JTextField();
		txtForms.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtForms.setColumns(10);
		txtForms.setBounds(254, 12, 114, 20);
		jPanelCenter.add(txtForms);

		JLabel lblSystem = new JLabel("System");
		lblSystem.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSystem.setBounds(10, 48, 163, 14);
		jPanelCenter.add(lblSystem);

		txtSystem = new JTextField();
		txtSystem.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtSystem.setColumns(10);
		txtSystem.setBounds(254, 44, 114, 20);
		jPanelCenter.add(txtSystem);

		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAccountNumber.setBounds(10, 79, 163, 14);
		jPanelCenter.add(lblAccountNumber);

		txtAccountNumber = new JTextField();
		txtAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtAccountNumber.setColumns(10);
		txtAccountNumber.setBounds(254, 76, 114, 20);
		jPanelCenter.add(txtAccountNumber);

		JLabel lblPrimaryManager = new JLabel("Primary Manage");
		lblPrimaryManager.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPrimaryManager.setBounds(10, 110, 163, 14);
		jPanelCenter.add(lblPrimaryManager);

		txtPrimaryManager = new JTextField();
		txtPrimaryManager.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPrimaryManager.setColumns(10);
		txtPrimaryManager.setBounds(254, 108, 114, 20);
		jPanelCenter.add(txtPrimaryManager);

		JLabel lblSecondaryManager = new JLabel("Seconday Manager");
		lblSecondaryManager.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSecondaryManager.setBounds(10, 141, 163, 14);
		jPanelCenter.add(lblSecondaryManager);

		txtecondaryManager = new JTextField();
		txtecondaryManager.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtecondaryManager.setColumns(10);
		txtecondaryManager.setBounds(254, 140, 114, 20);
		jPanelCenter.add(txtecondaryManager);

		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblActive.setBounds(10, 172, 163, 14);
		jPanelCenter.add(lblActive);

		cmbActive = new JComboBox();
		cmbActive.setFont(new Font("Tahoma", Font.BOLD, 10));
		cmbActive.setModel(new DefaultComboBoxModel(new String[] { "Active", "In-Active" }));
		cmbActive.setBounds(254, 172, 114, 20);
		jPanelCenter.add(cmbActive);

		JLabel lblRFUser = new JLabel("RF User");
		lblRFUser.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblRFUser.setBounds(10, 203, 163, 14);
		jPanelCenter.add(lblRFUser);

		cmbRFUser = new JComboBox();
		cmbRFUser.setFont(new Font("Tahoma", Font.BOLD, 10));
		cmbRFUser.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		cmbRFUser.setBounds(254, 204, 114, 20);
		jPanelCenter.add(cmbRFUser);

		JLabel lblEDI9Ord = new JLabel("EDI Order 9 positions");
		lblEDI9Ord.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEDI9Ord.setBounds(10, 234, 163, 14);
		jPanelCenter.add(lblEDI9Ord);

		btnEDI9Ord = new JComboBox();
		btnEDI9Ord.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEDI9Ord.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		btnEDI9Ord.setBounds(254, 236, 114, 20);
		jPanelCenter.add(btnEDI9Ord);

		JLabel lblProductionOrder = new JLabel("Production Order 9 positions");
		lblProductionOrder.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblProductionOrder.setBounds(10, 265, 163, 14);
		jPanelCenter.add(lblProductionOrder);

		cmbProductionOrder = new JComboBox();
		cmbProductionOrder.setFont(new Font("Tahoma", Font.BOLD, 10));
		cmbProductionOrder.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		cmbProductionOrder.setBounds(254, 268, 114, 20);
		jPanelCenter.add(cmbProductionOrder);

		JLabel lblPurged = new JLabel("Purged");
		lblPurged.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPurged.setBounds(10, 296, 163, 14);
		jPanelCenter.add(lblPurged);

		cmbPurged = new JComboBox();
		cmbPurged.setFont(new Font("Tahoma", Font.BOLD, 10));
		cmbPurged.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		cmbPurged.setBounds(254, 300, 114, 20);
		jPanelCenter.add(cmbPurged);

		JLabel txtpurgewrkbk = new JLabel("Purged Library");
		txtpurgewrkbk.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtpurgewrkbk.setBounds(10, 327, 163, 14);
		jPanelCenter.add(txtpurgewrkbk);

		txtPurgewrkbk = new JTextField();
		txtPurgewrkbk.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPurgewrkbk.setColumns(10);
		txtPurgewrkbk.setBounds(254, 332, 114, 20);
		jPanelCenter.add(txtPurgewrkbk);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 424, 856, 73);
		contentPane.add(jPanelButtons);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPrint.setIcon(new ImageIcon(RLMAccountEditForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});

		JButton btnInquiry = new JButton("I nquiry");
		btnInquiry.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInquiry.setIcon(new ImageIcon(RLMAccountEditForm.class.getResource("/images/task-planning.png")));
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doInquiry();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelButtons.add(btnInquiry);
		jPanelButtons.add(btnPrint);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(RLMAccountEditForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 492, 856, 77);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		setTitle("RLM Account Edit/Maintenance");
		doCenterForm();
		doPopulate(id);

	}

	private void doMenu() throws SQLException {
		Menu a = new Menu();
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

	private void doInquiry() throws SQLException, UnknownHostException {
		RLMAccountInquiryForm a = new RLMAccountInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();

	}

	private void doPopulate(String id) {
		ResultSet rs = null;
		String sql = "SELECT QGPL.PEXPACT.* FROM QGPL.PEXPACT ";
		sql += " WHERE R@#EXP = " + id;

		try {
			try {

				rs = SMLUtility.getResultSet(sql, "SYSP03", "INQ");
				while (rs.next()) {

					txtAccountName.setText(rs.getString("ACCTNAME"));
					txtForms.setText(rs.getString("FORMS"));
					txtSystem.setText(rs.getString("SYSTEM"));
					txtAccountNumber.setText(rs.getString("ACCOUNT"));
					txtPrimaryManager.setText(rs.getString("PRIMARY"));
					txtecondaryManager.setText(rs.getString("SECONDARY"));
					txtPurgewrkbk.setText(rs.getString("PRGEOLD2BK"));
					String inactive = (rs.getString("INACTIVE"));

					if (inactive.equals("I")) {
						cmbActive.setSelectedIndex(1);
					} else {
						cmbActive.setSelectedIndex(0);
					}

					String rfuser = (rs.getString("RFUSE"));
					if (rfuser.equals("YES")) {
						cmbRFUser.setSelectedIndex(1);
					} else {
						cmbRFUser.setSelectedIndex(0);
					}

					String EDI9Ord = (rs.getString("EDI9ORD#"));
					if (EDI9Ord.equals("YES")) {
						btnEDI9Ord.setSelectedIndex(1);
					} else {
						btnEDI9Ord.setSelectedIndex(0);
					}

					String PROD9Ord = (rs.getString("PROD9ORD#"));
					if (PROD9Ord.equals("YES")) {
						cmbProductionOrder.setSelectedIndex(1);
					} else {
						cmbProductionOrder.setSelectedIndex(0);
					}

					String purge = (rs.getString("PRGEOLD2"));
					if (purge.equals("YES")) {
						cmbPurged.setSelectedIndex(1);
					} else {
						cmbPurged.setSelectedIndex(0);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

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
