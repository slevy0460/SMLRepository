
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

public class FormInquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtDescAdd;
	private JTextField txtClassAdd;
	private JTextField txtCategoryAdd;
	private JTextField txtIconAdd;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormInquiry frame = new FormInquiry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormInquiry() throws UnknownHostException, SQLException {
		setTitle("Form Inquiry/Maintence");
		int screen = SMLUtility.getCurrentMonitorInfo(FormInquiry.this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBounds(0, 10, 1500, 60);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Form  Inquiry/Maintence");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(5, 5, 150, 20);
		toppanel.add(lblTitle);

		JLabel lblNewLabel = new JLabel("Use this form to view Form");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(5, 30, 200, 13);
		toppanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 75, 1475, 197);
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Description", "Class", "Category", "Icon"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		JPanel addpanel = new JPanel();
		addpanel.setBounds(0, 271, 1500, 67);
		contentPane.add(addpanel);
		addpanel.setLayout(null);

		txtDescAdd = new JTextField();
		txtDescAdd.setBounds(110, 37, 200, 20);
		addpanel.add(txtDescAdd);
		txtDescAdd.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescription.setBounds(20, 37, 65, 13);
		addpanel.add(lblDescription);

		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Dialog", Font.BOLD, 12));
		lblClass.setBounds(315, 37, 40, 13);
		addpanel.add(lblClass);

		txtClassAdd = new JTextField();
		txtClassAdd.setColumns(10);
		txtClassAdd.setBounds(361, 37, 200, 20);
		addpanel.add(txtClassAdd);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDescAdd.getText().trim().length() == 0 || (txtDescAdd.getText().trim() == null)) {
					txtDescAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter description to add new record");
				} else if (txtClassAdd.getText().trim().length() == 0 || (txtClassAdd.getText().trim() == null)) {
					txtClassAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter class to add new record");
				} else if (txtCategoryAdd.getText().trim().length() == 0 || (txtCategoryAdd.getText().trim() == null)) {
					txtCategoryAdd.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Please enter category to add new record");
				} else {
					doAdd();
					doPopulateTable();
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(FormInquiry.class.getResource("/images/plus.png")));
		btnAdd.setBounds(10, 6, 83, 21);
		addpanel.add(btnAdd);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCategory.setBounds(595, 37, 60, 13);
		addpanel.add(lblCategory);

		txtCategoryAdd = new JTextField();
		txtCategoryAdd.setColumns(10);
		txtCategoryAdd.setBounds(661, 37, 125, 20);
		addpanel.add(txtCategoryAdd);
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIcon.setBounds(816, 37, 60, 13);
		addpanel.add(lblIcon);
		
		txtIconAdd = new JTextField();
		txtIconAdd.setColumns(10);
		txtIconAdd.setBounds(882, 37, 200, 20);
		addpanel.add(txtIconAdd);

		JPanel bottompanel = new JPanel();
		bottompanel.setBounds(0, 334, 1500, 40);
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

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSaveTable();
				doPopulateTable();
			}
		});
		btnSave.setIcon(new ImageIcon(FormInquiry.class.getResource("/images/updated.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		bottompanel.add(btnSave);
		btnDelete.setIcon(new ImageIcon(FormInquiry.class.getResource("/images/bin.png")));
		bottompanel.add(btnDelete);
		btnClose.setIcon(new ImageIcon(FormInquiry.class.getResource("/images/close.png")));
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		bottompanel.add(btnClose);

		JPanel panel = new JPanel();
		panel.setBounds(0, 466, 1500, 40);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("FormsInquiry");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setForeground(Color.BLUE);
		panel.add(lblClassName);
		setTitle("Forms Inquiry/Maintence");
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
			sql += "FROM forms ";
			sql += "ORDER BY description";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {

				model.addRow(new Object[] { rs.getInt("id"), rs.getString("description"), rs.getString("class"),
						rs.getString("category"), rs.getString("icon") });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void doAdd() {
		ResultSet rs = null;
		String sql = "Insert into forms (description, class, category, icon) VALUES ( ";
		sql += " '" + txtDescAdd.getText() + "' , ";
		sql += " '" + txtClassAdd.getText() + "' , ";
		sql += " '" + txtCategoryAdd.getText() + "' , ";
		sql += " '" + txtIconAdd.getText() + "' ) ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
		String sql = "Delete From forms where id = ";
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
			int descColno = SMLUtility.getColumnIndex(table, "Description");
			int classColno = SMLUtility.getColumnIndex(table, "Class");
			int categoryColno = SMLUtility.getColumnIndex(table, "Category");
			int iconColno = SMLUtility.getColumnIndex(table, "Icon");
			
			tableData[i][idColno] = model.getValueAt(i, idColno);
			String id = String.valueOf(tableData[i][idColno]);

			tableData[i][descColno] = model.getValueAt(i, descColno);
			String desc = String.valueOf(tableData[i][descColno]);

			tableData[i][classColno] = model.getValueAt(i, classColno);
			String classname = String.valueOf(tableData[i][classColno]);

			tableData[i][categoryColno] = model.getValueAt(i, categoryColno);
			String category = String.valueOf(tableData[i][categoryColno]);
			
			tableData[i][iconColno] = model.getValueAt(i, iconColno);
			String icon = String.valueOf(tableData[i][iconColno]);

			doUpdateTable(id, desc, classname, category, icon);
		}

	}

	private void doUpdateTable(String id, String desc, String classname, String category, String icon) {
		String sql = null;
		ResultSet rs = null;

		sql = "UPDATE forms ";
		sql += "SET description = '" + desc + "', ";
		sql += "class = '" + classname + "', ";
		sql += "category = '" + category + "', ";
		sql += "icon = '" + icon + "' ";

		sql += "WHERE id = '" + id + "' ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
