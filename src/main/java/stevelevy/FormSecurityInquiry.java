
package stevelevy;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import javax.swing.UIDefaults;

public class FormSecurityInquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtUserAdd;
	private JTextField txtFormAdd;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSecurityInquiry frame = new FormSecurityInquiry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormSecurityInquiry() {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(FormSecurityInquiry.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBounds(0, 10, 1500, 60);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Form  Seciurity IInquiry/Maintence");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(5, 5, 200, 20);
		toppanel.add(lblTitle);

		JLabel lblNewLabel = new JLabel("Use this form to view Form Security");
		lblNewLabel.setBounds(5, 30, 300, 13);
		toppanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 75, 1475, 183);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		// Assuming you have a JTable named "table"
		JTableHeader header = table.getTableHeader();

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
		table.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null }, }, new String[] { "Id", "User", "Form" }) {
					boolean[] columnEditables = new boolean[] { false, true, true };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		JPanel addpanel = new JPanel();
		addpanel.setBounds(0, 258, 1500, 87);
		contentPane.add(addpanel);
		addpanel.setLayout(null);

		txtUserAdd = new JTextField();
		txtUserAdd.setBounds(100, 50, 125, 20);
		addpanel.add(txtUserAdd);
		txtUserAdd.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 50, 70, 13);
		addpanel.add(lblUser);

		JLabel lblForm = new JLabel("Form");
		lblForm.setBounds(240, 50, 60, 13);
		addpanel.add(lblForm);

		txtFormAdd = new JTextField();
		txtFormAdd.setColumns(10);
		txtFormAdd.setBounds(285, 50, 250, 20);
		addpanel.add(txtFormAdd);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUserAdd.getText().trim().length() == 0 || (txtUserAdd.getText().trim() == null)) {
					txtUserAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter description to add new record");
				} else if (txtFormAdd.getText().trim().length() == 0 || (txtFormAdd.getText().trim() == null)) {
					txtFormAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter class to add new record");
				} else {
					doAdd();
					doPopulateTable();
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(FormSecurityInquiry.class.getResource("/images/plus.png")));
		btnAdd.setBounds(10, 6, 83, 21);
		addpanel.add(btnAdd);

		JPanel bottompanel = new JPanel();
		bottompanel.setBounds(0, 350, 1500, 40);
		contentPane.add(bottompanel);
		bottompanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSaveTable();
				doPopulateTable();
			}
		});
		btnSave.setIcon(new ImageIcon(FormSecurityInquiry.class.getResource("/images/updated.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		bottompanel.add(btnSave);
		btnDelete.setIcon(new ImageIcon(FormSecurityInquiry.class.getResource("/images/bin.png")));
		bottompanel.add(btnDelete);
		btnClose.setIcon(new ImageIcon(FormSecurityInquiry.class.getResource("/images/close.png")));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		bottompanel.add(btnClose);

		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 1500, 40);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassname = new JLabel("FormSecurityInquiry");
		lblClassname.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassname.setForeground(Color.BLUE);
		panel.add(lblClassname);
		setTitle("Forms Security Inquiry/Maintence");
		// center the form on the monitor
		setLocationRelativeTo(null);
		// call doPopulateTable at the end of the constructor
		doPopulateTable();
	}

	private void doPopulateTable() {
		ResultSet rs = null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String userName = System.getProperty("user.name");
		try {
			String sql = "SELECT  ";
			sql += "* ";
			sql += "FROM formsecurity ";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {

				model.addRow(new Object[] { rs.getInt("id"), rs.getString("user"), rs.getString("form") });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void doAdd() {
		ResultSet rs = null;
		String sql = "Insert into formsecurity (user, form) VALUES ( ";
		sql += " '" + txtUserAdd.getText() + "' , ";
		sql += " '" + txtFormAdd.getText() + "')   ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
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
				doPopulateTable();
			}
		}
	}

	private void deleteIt(int row, String id) {

		ResultSet rs = null;
		String sql = "Delete From formsecurity where id = ";
		sql += " '" + id + "'  ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doSaveTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {
			int idColno = SMLUtility.getColumnIndex(table, "Id");
			int userColno = SMLUtility.getColumnIndex(table, "User");
			int formColno = SMLUtility.getColumnIndex(table, "Form");

			tableData[i][idColno] = model.getValueAt(i, idColno);
			String id = String.valueOf(tableData[i][idColno]);

			tableData[i][userColno] = model.getValueAt(i, userColno);
			String user = String.valueOf(tableData[i][userColno]);

			tableData[i][formColno] = model.getValueAt(i, formColno);
			String form = String.valueOf(tableData[i][formColno]);

			doUpdateTable(id, user, form);
		}

	}

	private void doUpdateTable(String id, String user, String form) {
		String sql = null;
		ResultSet rs = null;

		sql = "UPDATE formsecurity ";
		sql += "SET user = '" + user + "', ";
		sql += "form = '" + form + "' ";

		sql += "WHERE id = '" + id + "' ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
