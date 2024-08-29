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
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SMLConnectonTest extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String query = null;
	private JPanel contentPane;
	private JTextField txtRecords;
	String classname = getClass().getSimpleName();
	String[]arr = SMLAdministrator.getAdministrator(classname);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMLConnectonTest frame = new SMLConnectonTest();
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
	public SMLConnectonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 480);
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

		JLabel lblNewLabel = new JLabel("Number of records");
		lblNewLabel.setBounds(22, 237, 124, 14);
		panelCenter.add(lblNewLabel);

		txtRecords = new JTextField();
		txtRecords.setBounds(156, 234, 86, 20);
		panelCenter.add(txtRecords);
		txtRecords.setColumns(10);

		JComboBox cmdDataBase = new JComboBox();
		cmdDataBase.setModel(new DefaultComboBoxModel(new String[] { "SQL", "SYSP03", "JORD" }));
		cmdDataBase.setBounds(22, 78, 112, 22);
		panelCenter.add(cmdDataBase);

		JComboBox cmdType = new JComboBox();
		cmdType.setModel(new DefaultComboBoxModel(new String[] {"INQ", "UPD", "INS", "DLT"}));
		cmdType.setBounds(144, 78, 76, 22);
		panelCenter.add(cmdType);

		JTextField txtArea = new JTextField();
		txtArea.setSize(366, 33);
		panelCenter.add(txtArea);
		txtArea.setLocation(22, 133);

		JLabel lblNewLabel_1 = new JLabel("Database");
		lblNewLabel_1.setBounds(23, 39, 67, 14);
		panelCenter.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(144, 39, 98, 14);
		panelCenter.add(lblNewLabel_2);

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
		lblMenu.setIcon(new ImageIcon(SMLConnectonTest.class.getResource("/images/menu.png")));
		panel.add(lblMenu);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnTest = new JButton("Test");
		btnTest.setIcon(new ImageIcon(SMLConnectonTest.class.getResource("/images/test.png")));
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object selectedItem = null;
				String database = null;
				String type = null;

				selectedItem = cmdDataBase.getSelectedItem();
				database = selectedItem.toString();

				selectedItem = cmdType.getSelectedItem();
				type = selectedItem.toString();

				String query = txtArea.getText();
				doTest(query, database, type);
			}
		});
		panel.add(btnTest);
		btnClose.setIcon(new ImageIcon(SMLConnectonTest.class.getResource("/images/close.png")));
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		panel_1.add(lblClassName);

		setTitle("Test SMLUtility");
		lblTitle.setText("Use this form to test SML Universal Connection");
		lblInstructions.setText("Instructions of Jframe");
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		lblTitle.setText("Test SMLUtility");
		doCenterForm();

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

	private void doTest(String query, String database, String type) {
		ResultSet rs = null;
		int count = 0;
		try {
			rs = SMLUtility.getResultSet(query, database, type);
			if (rs != null) {
				while (rs.next()) {
					count += 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String x = Integer.toString(count);
		txtRecords.setText(x);
	}
}
