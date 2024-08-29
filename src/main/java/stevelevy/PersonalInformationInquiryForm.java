
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import javax.swing.UIDefaults;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;

public class PersonalInformationInquiryForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtFullNameAdd;
	String className = getClass().getSimpleName();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalInformationInquiryForm frame = new PersonalInformationInquiryForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersonalInformationInquiryForm() throws UnknownHostException, SQLException {
		int screen = SMLUtility.getCurrentMonitorInfo(PersonalInformationInquiryForm.this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBounds(0, 10, 1500, 60);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Personal Information Inquiry/Maintence");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(5, 5, 250, 20);
		toppanel.add(lblTitle);

		JLabel lblNewLabel = new JLabel("User this form for Personal Information Inquiry/Maintenance");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(5, 30, 400, 13);
		toppanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 75, 1500, 183);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int column = 0;
					int row = table.getSelectedRow();
					if (row >= 0) {
						String id = table.getModel().getValueAt(row, column).toString();
						try {
							doEdit(true, id);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		table.setFont(new Font("Dialog", Font.BOLD, 12));
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
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, }, new String[] {
						"Id", "Full Name", "Address #1", "Address #2", "Address #3", "Address #4", "Birthday" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(500);
		table.getColumnModel().getColumn(3).setPreferredWidth(1200);
		table.getColumnModel().getColumn(4).setPreferredWidth(1200	);
		table.getColumnModel().getColumn(5).setPreferredWidth(500);
		table.getColumnModel().getColumn(6).setPreferredWidth(600);

		scrollPane.setViewportView(table);

		JPanel addpanel = new JPanel();
		addpanel.setBounds(0, 258, 1500, 87);
		contentPane.add(addpanel);
		addpanel.setLayout(null);

		txtFullNameAdd = new JTextField();
		txtFullNameAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		txtFullNameAdd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					doAddPerson();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtFullNameAdd.setBounds(100, 50, 125, 20);
		addpanel.add(txtFullNameAdd);
		txtFullNameAdd.setColumns(10);

		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFullName.setBounds(10, 50, 70, 13);
		addpanel.add(lblFullName);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFullNameAdd.getText().trim().length() == 0 || (txtFullNameAdd.getText().trim() == null)) {
					txtFullNameAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter full name to add new record");
				} else {
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
			}
		});
		btnAdd.setIcon(new ImageIcon(PersonalInformationInquiryForm.class.getResource("/images/plus.png")));
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
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if (row >= 0) {
					String id = table.getModel().getValueAt(row, column).toString();
					try {
						doEdit(true, id);
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
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPopulateTable();
			}
		});
		btnRefresh.setIcon(new ImageIcon(PersonalInformationInquiryForm.class.getResource("/images/refresh.png")));
		bottompanel.add(btnRefresh);
		btnEdit.setIcon(new ImageIcon(PersonalInformationInquiryForm.class.getResource("/images/edit.png")));
		bottompanel.add(btnEdit);
		btnDelete.setIcon(new ImageIcon(PersonalInformationInquiryForm.class.getResource("/images/bin.png")));
		bottompanel.add(btnDelete);
		btnClose.setIcon(new ImageIcon(PersonalInformationInquiryForm.class.getResource("/images/close.png")));
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		bottompanel.add(btnClose);

		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 1500, 40);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassname = new JLabel("Class Name");
		lblClassname.setText(className);
		lblClassname.setFont(new Font("Dialog", Font.BOLD, 12));
		lblClassname.setForeground(Color.BLUE);
		panel.add(lblClassname);
		setTitle("Personal Information Inquiry/Maintence");
		// center the form on the monitor
		setLocationRelativeTo(null);
		// call doPopulateTable at the end of the constructor
		doPopulateTable();
	}

	private void doPopulateTable() {
		Date date = null;
		String birthday = null;
		// Create a SimpleDateFormat with the date format from the database
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
		// Create a SimpleDateFormat with the desired date format
		SimpleDateFormat sdfFormat = new SimpleDateFormat("EEEE MMMM dd yyyy");
		ResultSet rs = null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String userName = System.getProperty("user.name");
		try {
			String sql = "SELECT  ";
			sql += "* ";
			sql += "FROM PersonalInformation ";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				birthday = (rs.getString("birthday"));
				// Issue Date Parse the date string to a java.util.Date object
				try {
					date = sdfParse.parse(birthday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Format the date to a string in the desired format
				birthday = sdfFormat.format(date);
				model.addRow(new Object[] { rs.getInt("id"), rs.getString("fullname"), rs.getString("address1"),
						rs.getString("address2"), rs.getString("address3"), rs.getString("address4"), birthday });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void doAdd() throws SQLException, UnknownHostException {

		if (txtFullNameAdd.getText().trim().length() == 0 || (txtFullNameAdd.getText().trim() == null)) {
			txtFullNameAdd.requestFocusInWindow();
			JOptionPane.showMessageDialog(null, "Please enter name to add new record");
		} else {
			doEdit(false, null);
		}
	}

	private void doEdit(boolean existyn, String id) throws SQLException, UnknownHostException {
		PersonalInformationEditForm a = new PersonalInformationEditForm(existyn, id, txtFullNameAdd.getText());
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
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
		String sql = "Delete From personalinformation where id = ";
		sql += " '" + id + "'  ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doAddPerson() throws SQLException, UnknownHostException {
		String fullname = txtFullNameAdd.getText();
		PersonalInformationEditForm a = new PersonalInformationEditForm(false, null, fullname);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

}
