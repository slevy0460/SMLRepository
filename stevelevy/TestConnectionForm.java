package stevelevy;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TestConnectionForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestConnectionForm frame = new TestConnectionForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestConnectionForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBounds(0, 0, 856, 61);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBounds(0, 59, 856, 127);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBounds(0, 183, 856, 73);
		contentPane.add(jPanelButtons);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doMenu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelButtons.add(btnMenu);

		JButton btnPrint = new JButton("Print");
		jPanelButtons.add(btnPrint);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doConnection();
			}
		});
		jPanelButtons.add(btnConnect);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBounds(0, 254, 856, 77);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("lblClassName");
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		setTitle("Test Connection");
		doCenterForm();

	}

	private void doMenu() throws SQLException {
		// TODO Auto-generated method stub
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

	private void doConnection() {
		// TODO Auto-generated method stub
		String dbURL = "jdbc:mysql://192.168.0.3:3306/stevelevy";
		String username = "seve";
		String password = "Gtwh2022#mysql";
		Connection dbCon = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM passwords ";
		sql += "WHERE 1 = 1";

		try {
			try {
				dbCon = DriverManager.getConnection(dbURL, username, password);
				PreparedStatement stmt = dbCon.prepareStatement(sql);
				rs = stmt.executeQuery();

				while (rs.next()) {
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				JOptionPane.showMessageDialog(null, this, "Print Error: " + e.getMessage(), 0);
			}
		} finally {
			try {
				dbCon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, this, "Print Error: " + e.getMessage(), 0);
			}
		}
	}

}
