package stevelevy;

//Importing necessary libraries
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents a form for identification inquiry. It extends JFrame to
 * create a GUI window. It includes several components like panels, labels,
 * buttons, combo boxes, and a table.
 */
public class IdentificationInquiryForm_2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNameSearch;
	private JTable table;
	JComboBox cmbTypeSearch = new JComboBox();
	JComboBox cmbCountrySearch = new JComboBox();
	JComboBox cmbNameNew = new JComboBox();
	JComboBox cmdTypeNew = new JComboBox();
	JComboBox cmbCountryNew = new JComboBox();
	String classname = getClass().getSimpleName();
	JRadioButton rdbtnExpiredAll = new JRadioButton("All");
	JRadioButton rdbtnExpiredYes = new JRadioButton("Yes");
	JRadioButton rdbtnExpiredNo = new JRadioButton("No");

	/**
	 * The main method that launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationInquiryForm_2 frame = new IdentificationInquiryForm_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the IdentificationInquiryForm class. It initializes the form
	 * and its components.
	 */
	public IdentificationInquiryForm_2() {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(IdentificationInquiryForm_2.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBorder(new BevelBorder(1, null, null, null, null));
		toppanel.setBounds(0, 0, 1500, 50);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("Idendtification Inquiry");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTitle.setBounds(8, 10, 125, 13);
		toppanel.add(lblTitle);

		JLabel lblInstructions = new JLabel("User this form for Identification Inquiry");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInstructions.setBounds(8, 27, 250, 13);
		toppanel.add(lblInstructions);

		JPanel searchpanel = new JPanel();
		searchpanel.setBorder(new BevelBorder(1, null, null, null, null));
		searchpanel.setBounds(0, 50, 1500, 80);
		contentPane.add(searchpanel);
		searchpanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 42, 13);
		searchpanel.add(lblNewLabel);

		txtNameSearch = new JTextField();
		txtNameSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNameSearch.setBounds(80, 10, 150, 19);
		searchpanel.add(txtNameSearch);
		txtNameSearch.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(250, 10, 42, 13);
		searchpanel.add(lblNewLabel_1);

		cmbTypeSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbTypeSearch.setModel(new DefaultComboBoxModel(new String[] { "All" }));
		cmbTypeSearch.setBounds(300, 10, 150, 21);
		searchpanel.add(cmbTypeSearch);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Expired", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(458, 10, 236, 57);
		searchpanel.add(panel);
		panel.setLayout(null);

		rdbtnExpiredAll.setFont(new Font("Tahoma", Font.BOLD, 10));
		rdbtnExpiredAll.setBounds(21, 29, 50, 20);
		rdbtnExpiredAll.setSelected(true);
		panel.add(rdbtnExpiredAll);

		rdbtnExpiredYes.setFont(new Font("Tahoma", Font.BOLD, 10));
		rdbtnExpiredYes.setBounds(92, 29, 50, 21);
		rdbtnExpiredYes.setSelected(false);
		panel.add(rdbtnExpiredYes);

		rdbtnExpiredNo.setFont(new Font("Tahoma", Font.BOLD, 10));
		rdbtnExpiredNo.setBounds(163, 29, 50, 21);
		rdbtnExpiredNo.setSelected(false);
		panel.add(rdbtnExpiredNo);

		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnExpiredAll);
		bG.add(rdbtnExpiredYes);
		bG.add(rdbtnExpiredNo);

		JLabel lblNewLabel_2 = new JLabel("Country");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(715, 10, 80, 13);
		searchpanel.add(lblNewLabel_2);

		cmbCountrySearch.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbCountrySearch.setModel(new DefaultComboBoxModel(new String[] { "All" }));
		cmbCountrySearch.setBounds(805, 10, 150, 21);
		searchpanel.add(cmbCountrySearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSaveSearch();
				doPopulateTable();
			}
		});
		btnSearch.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/search.png")));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSearch.setBounds(30, 46, 150, 21);
		searchpanel.add(btnSearch);

		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(new BevelBorder(1, null, null, null, null));
		centerpanel.setBounds(0, 130, 1500, 200);
		contentPane.add(centerpanel);
		centerpanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1475, 225);
		centerpanel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						doEdit(true, (String) null);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JTableHeader header = this.table.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 14));
		header.setForeground(Color.RED);
		header.setBackground(Color.CYAN);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Name", "Country", "Type", "Description", "Number", "Issue Date", "Expiration Date", "Expired Y/N", "Birthday", "Security Code"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(400);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
		table.getColumnModel().getColumn(6).setPreferredWidth(300);
		table.getColumnModel().getColumn(7).setPreferredWidth(450);
		table.getColumnModel().getColumn(8).setPreferredWidth(400);
		table.getColumnModel().getColumn(9).setPreferredWidth(300);
		table.getColumnModel().getColumn(10).setPreferredWidth(450);
		scrollPane.setViewportView(table);

		JPanel addpanel = new JPanel();
		addpanel.setBorder(new BevelBorder(1, null, null, null, null));
		addpanel.setBounds(0, 350, 1500, 80);
		contentPane.add(addpanel);
		addpanel.setLayout(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdd();
			}
		});
		btnAdd.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/plus.png")));
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.setBounds(10, 10, 100, 21);
		addpanel.add(btnAdd);

		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 40, 42, 13);
		addpanel.add(lblNewLabel_3);
		cmbNameNew.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));
		cmbNameNew.setFont(new Font("Dialog", Font.BOLD, 12));

		cmbNameNew.setBounds(80, 40, 150, 21);
		addpanel.add(cmbNameNew);

		JLabel lblNewLabel_4 = new JLabel("Type");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_4.setBounds(250, 40, 42, 13);
		addpanel.add(lblNewLabel_4);
		cmdTypeNew.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));

		cmdTypeNew.setFont(new Font("Dialog", Font.BOLD, 12));
		cmdTypeNew.setBounds(325, 40, 150, 21);
		addpanel.add(cmdTypeNew);

		JLabel lblNewLabel_5 = new JLabel("Country");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_5.setBounds(500, 40, 75, 13);
		addpanel.add(lblNewLabel_5);
		cmbCountryNew.setModel(new DefaultComboBoxModel(new String[] { "Not Selected" }));
		cmbCountryNew.setFont(new Font("Dialog", Font.BOLD, 12));

		cmbCountryNew.setBounds(580, 40, 150, 21);
		addpanel.add(cmbCountryNew);

		JPanel buttonpanel = new JPanel();
		buttonpanel.setBorder(new BevelBorder(1, null, null, null, null));
		buttonpanel.setBounds(0, 430, 1500, 40);
		contentPane.add(buttonpanel);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnOnedrive_1 = new JButton("OneDrive Online");
		btnOnedrive_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOneDrive();
			}
		});
		btnOnedrive_1.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/folder.png")));
		btnOnedrive_1.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnOnedrive_1);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/bin.png")));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnDelete);

		JButton lblEdit = new JButton("Edit");
		lblEdit.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/edit.png")));
		lblEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doEdit(true, (String) null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(lblEdit);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(IdentificationInquiryForm_2.class.getResource("/images/close.png")));
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonpanel.add(btnClose);

		JPanel bottempanel = new JPanel();
		bottempanel.setBorder(new BevelBorder(1, null, null, null, null));
		bottempanel.setBounds(0, 480, 1500, 40);
		contentPane.add(bottempanel);
		bottempanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setText(classname);
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setFont(new Font("Dialog", Font.BOLD, 12));
		bottempanel.add(lblClassName);
		setTitle("Idendtification Inquiry");
		setLocationRelativeTo(null);
		addNames();
		addTypes();
		addCountry();
		doGetSearch();
		doPopulateTable();
	}

	/**
	 * This method populates the table with data from the database.
	 */
	private void doPopulateTable() {
		ResultSet rs = null;
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		model.setRowCount(0);
		String id = null;
		String name = null;
		String description = null;
		String number = null;
		String issuedate = null;
		String expirationdate = null;
		String expiredYn = null;
		String birthday = null;
		String country = null;
		String securitycode = null;
		String type = null;
		Date date;
		Date today = new Date();
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("MMMM dd yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			String sql = "SELECT  ";
			sql = String.valueOf(sql) + "*, coalesce(birthday,'1001-01-01') ";
			sql = String.valueOf(sql) + "FROM identification ";
			sql = String.valueOf(sql) + "LEFT JOIN personalinformation on trim(FullName) = trim(Name) ";
			sql = String.valueOf(sql) + "WHERE 1 = 1 ";
			if (this.txtNameSearch.getText().trim().length() > 0) {
				String upper = this.txtNameSearch.getText().trim().toUpperCase();
				sql = String.valueOf(sql) + " AND (UPPER(name))  like '%" + upper + "%' " + "";
			}

			String formattedDate = sdf.format(today);
			if (rdbtnExpiredYes.isSelected()) {
				sql += "AND expirationdate < " + formattedDate + " ";
			}

			if (rdbtnExpiredNo.isSelected()) {
				sql += "AND expirationdate >= " + formattedDate + " ";
			}

			if (this.cmbTypeSearch.getSelectedIndex() != 0) {
				type = (String) this.cmbTypeSearch.getSelectedItem();
				sql = String.valueOf(sql) + " AND typedescription = '" + type + "' ";
			}
			if (this.cmbCountrySearch.getSelectedIndex() != 0) {
				country = (String) this.cmbCountrySearch.getSelectedItem();
				sql = String.valueOf(sql) + " AND country  = '" + country + "'";
			}
			sql = String.valueOf(sql) + "ORDER BY name ";

//			System.out.println("SQL is : " + sql);
			
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				description = rs.getString("description");
				number = rs.getString("number");
				issuedate = rs.getString("issuedate");
				expirationdate = rs.getString("expirationdate");
				birthday = rs.getString("birthday");
				country = rs.getString("country");
				securitycode = rs.getString("securitycode");
				type = rs.getString("typedescription");
				// Check if expired
				date = sdfParse.parse(issuedate);
				issuedate = sdfFormat.format(date);
				date = sdfParse.parse(expirationdate);
				expirationdate = sdfFormat.format(date);

				expiredYn = "No";
				if (date.before(today))
					expiredYn = "Yes";
				if (birthday == null)
					birthday = "1001-01-01";

				date = sdfParse.parse(birthday);
				birthday = sdfFormat.format(date);
				model.addRow(new Object[] { id, name, country, type, description, number, issuedate, expirationdate,
						expiredYn, birthday, securitycode });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method handles the addition of a new record.
	 */
	private void doAdd() {
		boolean existyn = false;
		if (this.cmbNameNew.getSelectedIndex() == 0) {
			this.cmbNameNew.requestFocusInWindow();
			JOptionPane.showMessageDialog(null, "Please select name to add new record");
			return;
		}
		if (this.cmdTypeNew.getSelectedIndex() == 0) {
			this.cmdTypeNew.requestFocusInWindow();
			JOptionPane.showMessageDialog(null, "Please select type to add new record");
			return;
		}
		if (this.cmbCountryNew.getSelectedIndex() == 0) {
			this.cmbCountryNew.requestFocusInWindow();
			JOptionPane.showMessageDialog(null, "Please select country to add new record");
			return;
		}
		existyn = checkidentification();
		if (existyn) {
			int n = JOptionPane.showConfirmDialog(null, "this record already exist, do you want to add another?",
					"Verify Exit ", 0);
			if (n == 1)
				return;
			existyn = false;
			try {
				doEdit(existyn, (String) null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				doEdit(existyn, (String) null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			doPopulateTable();
		}
	}

	/**
	 * This method checks if the identification already exists in the database.
	 * 
	 * @return boolean - true if the identification exists, false otherwise.
	 */
	private boolean checkidentification() {
		ResultSet rs = null;
		int size = 0;
		boolean existyn = false;
		String typeselected = null;
		try {
			String sql = "SELECT  ";
			sql = String.valueOf(sql) + "* ";
			sql = String.valueOf(sql) + "FROM identification ";
			sql = String.valueOf(sql) + "WHERE 1 = 1 ";
			if (this.cmbNameNew.getSelectedIndex() != 0) {
				Object selectedItem = this.cmbNameNew.getSelectedItem();
				String Nameselected = selectedItem.toString();
				int i = this.cmbCountryNew.getSelectedIndex();
				if (Nameselected.trim().length() > 0) {
					String upper = Nameselected.trim().toUpperCase();
					sql = String.valueOf(sql) + " AND trim(name) = '" + Nameselected + "'";
				}
			}
			if (this.cmbCountryNew.getSelectedIndex() != 0) {
				Object selectedItem = this.cmbCountryNew.getSelectedItem();
				typeselected = selectedItem.toString();
				int i = this.cmbCountryNew.getSelectedIndex();
				if (typeselected.trim().length() > 0) {
					String upper = typeselected.trim().toUpperCase();
					sql = String.valueOf(sql) + " AND country = '" + typeselected + "'";
				}
			}
			if (this.cmdTypeNew.getSelectedIndex() != 0) {
				Object selectedItem = this.cmdTypeNew.getSelectedItem();
				typeselected = selectedItem.toString();
				int i = this.cmdTypeNew.getSelectedIndex();
				if (typeselected.trim().length() > 0) {
					String type = typeselected.trim();
					sql = String.valueOf(sql) + " AND typedescription = '" + type + "'";
				}
			}

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next())
				size = 1;
			if (size > 0)
				existyn = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return existyn;
	}

	/**
	 * This method handles the editing of a record.
	 * 
	 * @param existyn - boolean indicating if the record exists.
	 * @param id      - the id of the record to be edited.
	 */
	private void doEdit(boolean existyn, String id) throws SQLException {
		int column = 0;
		int row = this.table.getSelectedRow();
		if (existyn) {
			if (row < 0)
				JOptionPane.showMessageDialog(null, "Please select a row to edit");
			id = this.table.getModel().getValueAt(row, column).toString();
		}
		String Nameselected = null;
		if (this.cmbNameNew.getSelectedIndex() != 0) {
			Object selectedItem = this.cmbNameNew.getSelectedItem();
			Nameselected = selectedItem.toString();
			int i = this.cmbCountryNew.getSelectedIndex();
		}

		String typenew = (String) this.cmdTypeNew.getSelectedItem();
		String Countrynew = (String) this.cmbCountryNew.getSelectedItem();
		IdentificationEditForm a = new IdentificationEditForm(existyn, Nameselected, typenew, Countrynew, id);
		a.addWindowListener(new WindowAdapter() {

		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	/**
	 * This method handles the deletion of a record.
	 */
	private void doDelete() {
		int column = 0;
		int row = this.table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Please select a row to delete");
		} else {
			String id = this.table.getModel().getValueAt(row, column).toString();
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Verify Exit ",
					0);
			if (n == 0) {
				deleteIt(row, id);
				doPopulateTable();
			}
		}
	}

	/**
	 * This method deletes a record from the database.
	 * 
	 * @param row - the row number of the record in the table.
	 * @param id  - the id of the record to be deleted.
	 */
	private void deleteIt(int row, String id) {
		Object selectedItem;
		ResultSet rs = null;
		String sql = "Delete From identification where id = ";
		sql = String.valueOf(sql) + " '" + id + "'  ";
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	/**
	 * This method saves the search criteria.
	 */
	private void doSaveSearch() {
		String field = null;
		String value = null;
		Object selectedItem = null;
		// Text Field Name Search
		field = "txtNameSearch";
		value = this.txtNameSearch.getText().trim();
		try {
			value = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Combo box Type Search
		field = "cmbTypeSearch";
		selectedItem = this.cmbTypeSearch.getSelectedItem();
		value = selectedItem.toString();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Combo box Country Search
		field = "cmbCountrySearch";
		selectedItem = this.cmbCountrySearch.getSelectedItem();
		value = selectedItem.toString();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Radio button rdbtnExpiredAll Search
		field = "rdbtnExpiredAll";
		value = "" + this.rdbtnExpiredAll.isSelected();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Radio button rdbtnExpiredYes Search
		field = "rdbtnExpiredYes";
		value = "" + this.rdbtnExpiredYes.isSelected();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Radio button rdbtnExpiredNo Search
		field = "rdbtnExpiredNo";
		value = "" + this.rdbtnExpiredNo.isSelected();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * This method retrieves the search criteria.
	 */
	private void doGetSearch() {
		String value = null;
		// Text Field Name Search
		try {
			value = SMLUtility.getValue(this.classname, "txtNameSearch", "", "GET");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.txtNameSearch.setText(value.trim());
		// Combo box Type Search
		try {
			value = SMLUtility.getValue(classname, "cmbTypeSearch", "", "GET");
			cmbTypeSearch.setSelectedItem(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Combo box Country Search
		try {
			cmbCountrySearch.setSelectedItem(value);
			value = SMLUtility.getValue(classname, "cmbCountrySearch", "", "GET");
			cmbCountrySearch.setSelectedItem(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Radio button rdbtnExpiredAll Search
		try {
			value = SMLUtility.getValue(this.classname, "rdbtnExpiredAll", "", "GET");
			if (value.equals("true"))
				this.rdbtnExpiredAll.setSelected(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Radio button rdbtnExpiredYes Search
		try {
			value = SMLUtility.getValue(this.classname, "rdbtnExpiredYes", "", "GET");
			if (value.equals("true"))
				this.rdbtnExpiredYes.setSelected(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Radio button rdbtnExpiredNo Search
		try {
			value = SMLUtility.getValue(this.classname, "rdbtnExpiredNo", "", "GET");
			if (value.equals("true"))
				this.rdbtnExpiredNo.setSelected(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method opens the OneDrive online page.
	 */
	private void doOneDrive() {
		String onedrive = "https://onedrive.live.com/";
		Desktop desktop = Desktop.getDesktop();
		try {
			Desktop.getDesktop().browse(URI.create(onedrive));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method adds names to the combo box from the database.
	 */
	private void addNames() {
		ResultSet rs = null;
		String query = "select * from personalinformation order by fullname";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String fullname = rs.getString("fullname");
				this.cmbNameNew.addItem(fullname);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method adds types to the combo box from the database.
	 */
	private void addTypes() {
		ResultSet rs = null;
		String query = "select * from identificationtype order by description";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String type = rs.getString("description");
				this.cmbTypeSearch.addItem(type);
				this.cmdTypeNew.addItem(type);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method adds country to the combo box from the database.
	 */
	private void addCountry() {
		ResultSet rs = null;
		String query = "select * from identificationCountry order by description";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				String country = rs.getString("description");
				this.cmbCountrySearch.addItem(country);
				this.cmbCountryNew.addItem(country);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
