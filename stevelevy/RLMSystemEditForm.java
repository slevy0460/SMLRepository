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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class RLMSystemEditForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtSystemName;
	private JTextField txtDescription;
	private JTextField txtQsecofrPassword;
	private JTextField txtQsecRLMPassord;
	private JTextField txtSerialNumber;
	private JTextField txtPrivateIp;
	private JTextField txtPublicIp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLMSystemEditForm frame = new RLMSystemEditForm(null);
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
	public RLMSystemEditForm(String id) throws SQLException, UnknownHostException {
		int screen = SMLUtility.getCurrentMonitorInfo(RLMSystemEditForm.this);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 872, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 856, 61);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		txtSystemName = new JTextField();
		txtSystemName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtSystemName.setBounds(211, 14, 230, 20);
		jPanelTop.add(txtSystemName);
		txtSystemName.setColumns(10);

		JLabel lblSystemName = new JLabel("System Name");
		lblSystemName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSystemName.setBounds(10, 14, 96, 14);
		jPanelTop.add(lblSystemName);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 59, 856, 361);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDescription.setBounds(10, 39, 96, 14);
		jPanelCenter.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDescription.setColumns(10);
		txtDescription.setBounds(211, 34, 230, 20);
		jPanelCenter.add(txtDescription);

		JLabel lblQsecofrPassword = new JLabel("Qsecofr Pasword");
		lblQsecofrPassword.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblQsecofrPassword.setBounds(10, 198, 96, 14);
		jPanelCenter.add(lblQsecofrPassword);

		txtQsecofrPassword = new JTextField();
		txtQsecofrPassword.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtQsecofrPassword.setColumns(10);
		txtQsecofrPassword.setBounds(211, 196, 230, 20);
		jPanelCenter.add(txtQsecofrPassword);

		JLabel lblQsecRLMPassord = new JLabel("Qsecrlm Password");
		lblQsecRLMPassord.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblQsecRLMPassord.setBounds(10, 251, 96, 14);
		jPanelCenter.add(lblQsecRLMPassord);

		txtQsecRLMPassord = new JTextField();
		txtQsecRLMPassord.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtQsecRLMPassord.setColumns(10);
		txtQsecRLMPassord.setBounds(211, 250, 230, 20);
		jPanelCenter.add(txtQsecRLMPassord);

		JLabel lblSerialNumber = new JLabel("Serial Number");
		lblSerialNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSerialNumber.setBounds(10, 304, 96, 14);
		jPanelCenter.add(lblSerialNumber);

		txtSerialNumber = new JTextField();
		txtSerialNumber.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtSerialNumber.setColumns(10);
		txtSerialNumber.setBounds(211, 304, 230, 20);
		jPanelCenter.add(txtSerialNumber);

		JLabel lblPrivateIp = new JLabel("Private Ip");
		lblPrivateIp.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPrivateIp.setBounds(10, 92, 96, 14);
		jPanelCenter.add(lblPrivateIp);

		txtPrivateIp = new JTextField();
		txtPrivateIp.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPrivateIp.setColumns(10);
		txtPrivateIp.setBounds(211, 88, 230, 20);
		jPanelCenter.add(txtPrivateIp);

		JLabel lblPublicIp = new JLabel("Public Ip");
		lblPublicIp.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPublicIp.setBounds(10, 145, 96, 14);
		jPanelCenter.add(lblPublicIp);

		txtPublicIp = new JTextField();
		txtPublicIp.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPublicIp.setColumns(10);
		txtPublicIp.setBounds(211, 142, 230, 20);
		jPanelCenter.add(txtPublicIp);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 419, 856, 73);
		contentPane.add(jPanelButtons);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPrint.setIcon(new ImageIcon(RLMSystemEditForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});
		
				JButton btnInquiry = new JButton("I nquiry");
				btnInquiry.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnInquiry.setIcon(new ImageIcon(RLMSystemEditForm.class.getResource("/images/task-planning.png")));
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
		btnClose.setIcon(new ImageIcon(RLMSystemEditForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JLabel lblClassName = new JLabel("lblClassName");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setBounds(468, 518, 125, 13);
		contentPane.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		
		setTitle("RLM System Edit/Maintenance");
		doCenterForm();
		doPopulate(id);
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

	private void doInquiry() throws SQLException, UnknownHostException {
		RLMSystemInquiryForm a = new RLMSystemInquiryForm();
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

	private void doPopulate(String idpas) throws SQLException {
		Connection dbCon = null;
		ResultSet rs = null;
		String id = idpas;

		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color cyan = new Color(0, 255, 255);
		String sql = "SELECT * FROM rlmpserv01 WHERE id = '" + id + "'";

		DecimalFormat decAmt$Format = new DecimalFormat("$0.00");

		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

		while (rs.next()) {

			id = (rs.getString("id"));

			txtSystemName.setText(rs.getString("servername"));
			txtDescription.setText(rs.getString("description"));
			txtPrivateIp.setText(rs.getString("Internalip"));
			txtPublicIp.setText(rs.getString("Externalip"));
			txtQsecofrPassword.setText(rs.getString("qsecofrpass"));
			txtQsecRLMPassord.setText(rs.getString("qsecrlmpass"));
			txtSerialNumber.setText(rs.getString("serialno"));

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
