package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class MyAdministrationForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyAdministrationForm frame = new MyAdministrationForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JLabel lblCurrentPasswordSysp03 = new JLabel("Sysp03 Password");
	JLabel lblCurrentPasswordJord = new JLabel("Jordache Password");
	JCheckBox chkbxAdministrator = new JCheckBox("Administrator");
	private JTextField txtSysp03Old;
	private JTextField txtJordOld;
	private JTextField txtSysp03New;
	private JTextField txtJordNew;
	private JTextField txtIDSysP03;
	private JTextField txtIDJord;
	private JTextField txtIDAdmin;
	private JTextField txtAdminOld;

	/**
	 * Create the frame.
	 */
	public MyAdministrationForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel toppanel = new JPanel();
		contentPane.add(toppanel, BorderLayout.NORTH);
		toppanel.setLayout(new BorderLayout(0, 0));

		JPanel centerpanel = new JPanel();
		contentPane.add(centerpanel, BorderLayout.CENTER);
		centerpanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Current Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 438, 194);
		centerpanel.add(panel);
		panel.setLayout(null);
		lblCurrentPasswordSysp03.setBounds(10, 52, 147, 14);
		panel.add(lblCurrentPasswordSysp03);

		lblCurrentPasswordSysp03.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentPasswordJord.setBounds(10, 87, 147, 14);
		panel.add(lblCurrentPasswordJord);

		lblCurrentPasswordJord.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtSysp03Old = new JTextField();
		txtSysp03Old.setEditable(false);
		txtSysp03Old.setBounds(328, 52, 86, 20);
		panel.add(txtSysp03Old);
		txtSysp03Old.setColumns(10);

		txtJordOld = new JTextField();
		txtJordOld.setEditable(false);
		txtJordOld.setBounds(328, 87, 86, 20);
		panel.add(txtJordOld);
		txtJordOld.setColumns(10);

		txtIDSysP03 = new JTextField();
		txtIDSysP03.setEditable(false);
		txtIDSysP03.setColumns(10);
		txtIDSysP03.setBounds(200, 52, 86, 20);
		panel.add(txtIDSysP03);

		txtIDJord = new JTextField();
		txtIDJord.setEditable(false);
		txtIDJord.setColumns(10);
		txtIDJord.setBounds(200, 87, 86, 20);
		panel.add(txtIDJord);

		JLabel lblAdministratorOld = new JLabel("Administrator User");
		lblAdministratorOld.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdministratorOld.setBounds(10, 23, 147, 14);
		panel.add(lblAdministratorOld);

		txtIDAdmin = new JTextField();
		txtIDAdmin.setEditable(false);
		txtIDAdmin.setColumns(10);
		txtIDAdmin.setBounds(200, 23, 86, 20);
		panel.add(txtIDAdmin);

		txtAdminOld = new JTextField();
		txtAdminOld.setEditable(false);
		txtAdminOld.setColumns(10);
		txtAdminOld.setBounds(328, 23, 86, 20);
		panel.add(txtAdminOld);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "New Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(479, 11, 316, 194);
		centerpanel.add(panel_1);

		JLabel lblCurrentPasswordSysp03_1 = new JLabel("Sysp03 Password");
		lblCurrentPasswordSysp03_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentPasswordSysp03_1.setBounds(10, 52, 147, 14);
		panel_1.add(lblCurrentPasswordSysp03_1);

		JLabel lblCurrentPasswordJord_1 = new JLabel("Jordache Password");
		lblCurrentPasswordJord_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentPasswordJord_1.setBounds(10, 87, 147, 14);
		panel_1.add(lblCurrentPasswordJord_1);

		txtSysp03New = new JTextField();
		txtSysp03New.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSysp03New.selectAll();
			}
		});
		txtSysp03New.setColumns(10);
		txtSysp03New.setBounds(220, 52, 86, 20);
		panel_1.add(txtSysp03New);

		txtJordNew = new JTextField();
		txtJordNew.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtJordNew.selectAll();
			}
		});
		txtJordNew.setColumns(10);
		txtJordNew.setBounds(220, 87, 86, 20);
		panel_1.add(txtJordNew);

		chkbxAdministrator.setFont(new Font("Dialog", Font.BOLD, 12));
		chkbxAdministrator.setBounds(10, 23, 126, 21);
		panel_1.add(chkbxAdministrator);

		JPanel buttonpanel = new JPanel();
		contentPane.add(buttonpanel, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdateConnUpdate("SYSP03");
				doUpdateConnUpdate("JORD");
				doUpdateAdminUpdate();
				doPopulateConnPassword();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setIcon(new ImageIcon(MyAdministrationForm.class.getResource("/images/updated.png")));
		buttonpanel.add(btnUpdate);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClose.setIcon(new ImageIcon(MyAdministrationForm.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);

		doCenterForm();
		doPopulateConnPassword();
		doPopulateAdminUser();

	}

	private void doPopulateAdminUser() {
		String admin = null;
		String administrator = null;
		ResultSet rs = null;
		String sql = "SELECT ";
		sql += "id, Windowsuser, Accountdescription, type, administrator, tabs F ";
		sql += "FROM passwords ";
		sql += "where administrator = 'Yes'";

		System.out.println("sql: " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				admin = rs.getString("Windowsuser" + "");

				txtIDAdmin.setText(rs.getString("id"));
				txtAdminOld.setText(rs.getString("Windowsuser"));
				administrator = (rs.getString("administrator"));
				chkbxAdministrator.setSelected(false);

				if (administrator.equals("Yes")) {
					chkbxAdministrator.setSelected(true);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doUpdateAdminUpdate() {
		String id = null;
		String administrator;
		ResultSet rs = null;
		administrator = "No";
		if (chkbxAdministrator.isSelected()) {
			administrator = "Yes";
		}

		String sql = "UPDATE passwords ";
		sql += "SET ";
		sql += "Administrator = '" + administrator + "' ";
		sql += "where ID = '" + txtIDAdmin.getText() + "' ";

		System.out.println("sql: " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doPopulateConnPassword() {
		String account = null;
		ResultSet rs = null;
		String sql = "SELECT ";
		sql += "id, accountdescription, accountnumber, userid, password ";
		sql += "FROM passwords ";
		sql += "where Accountdescription = 'WORK SYSTEMS'";
		System.out.println("sql: " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				account = rs.getString("accountnumber" + "");
				if (account.equals("SYSP03")) {
					txtIDSysP03.setText(rs.getString("id"));
					txtSysp03Old.setText(rs.getString("password"));
					txtSysp03New.setText(rs.getString("password"));
				}
				if (account.equals("JORD")) {
					txtIDJord.setText(rs.getString("id"));
					txtJordOld.setText(rs.getString("password"));
					txtJordNew.setText(rs.getString("password"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doUpdateConnUpdate(String account) {
		String id = null;
		String newpassword = null;
		if (account.equals("SYSP03")) {
			id = txtIDSysP03.getText();
			newpassword = txtSysp03New.getText();
		}

		if (account.equals("JORD")) {
			id = txtIDJord.getText();
			newpassword = txtJordNew.getText();
		}

		ResultSet rs = null;
		String sql = "UPDATE passwords ";
		sql += "SET ";
		sql += "password = '" + newpassword + "' ";
		sql += "where ID = '" + id + "' ";

		System.out.println("sql: " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}
}