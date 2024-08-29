package stevelevy;

//Importing necessary libraries
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * This class represents a form for identification inquiry. It extends JFrame to
 * create a GUI window. It includes several components like panels, labels,
 * buttons, combo boxes, and a table.
 */
public class IdentificationInquiryForm extends JFrame {
	private static final long serialVersionUID = 1L;
	// Declaring the components used in the form
	private JPanel contentPane;
	private JTable table;
	JComboBox cmbName = new JComboBox();
	JComboBox cmbCountryNew = new JComboBox();
	JComboBox cmdTypeNew = new JComboBox();
	JComboBox cmdTypeSearch = new JComboBox();
	JComboBox cmbCountrySearch = new JComboBox();
	String classname = getClass().getSimpleName();
	private JTextField txtNameSearch;

	/**
	 * The main method that launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationInquiryForm frame = new IdentificationInquiryForm();
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
	public IdentificationInquiryForm() {
		// Code for initializing the form and its components
		setDefaultCloseOperation(3);
		setBounds(100, 100, 1222, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout((LayoutManager) null);
		JPanel toppanel = new JPanel();
		toppanel.setBorder(new BevelBorder(1, null, null, null, null));
		toppanel.setBounds(0, 0, 1200, 50);
		this.contentPane.add(toppanel);
		toppanel.setLayout((LayoutManager) null);
		JLabel lblTitle = new JLabel("Idendtification Inquiry");
		lblTitle.setFont(new Font("Tahoma", 1, 10));
		lblTitle.setBounds(8, 10, 125, 13);
		lblTitle.setForeground(Color.BLUE);
		toppanel.add(lblTitle);
		JLabel lblInstructions = new JLabel("User this form for Identification Inquiry");
		lblInstructions.setFont(new Font("Tahoma", 1, 10));
		lblInstructions.setBounds(8, 27, 200, 13);
		toppanel.add(lblInstructions);
		JPanel searchpanel = new JPanel();
		searchpanel.setLayout((LayoutManager) null);
		searchpanel.setBorder(new BevelBorder(1, null, null, null, null));
		searchpanel.setBounds(0, 50, 1200, 80);
		this.contentPane.add(searchpanel);
		JButton btnSearch = new JButton("Searcch");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdentificationInquiryForm.this.doSaveSearch();
				IdentificationInquiryForm.this.doPopulateTable();
			}
		});
		btnSearch.setFont(new Font("Tahoma", 1, 10));
		btnSearch.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/search.png")));
		btnSearch.setBounds(10, 49, 150, 21);
		searchpanel.add(btnSearch);
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Dialog", 1, 12));
		lblNewLabel.setBounds(10, 20, 42, 13);
		searchpanel.add(lblNewLabel);
		this.txtNameSearch = new JTextField();
		this.txtNameSearch.setFont(new Font("Dialog", 1, 12));
		this.txtNameSearch.setBounds(50, 20, 150, 19);
		searchpanel.add(this.txtNameSearch);
		this.txtNameSearch.setColumns(10);
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setFont(new Font("Dialog", 1, 12));
		lblType_1.setBounds(219, 20, 42, 20);
		searchpanel.add(lblType_1);
		this.cmdTypeSearch.setFont(new Font("Dialog", 1, 12));
		this.cmdTypeSearch.setModel(
				new DefaultComboBoxModel<>(new String[] { "All", "Credit Card", "License", "Passport", "Visa" }));
		this.cmdTypeSearch.setBounds(259, 20, 150, 21);
		searchpanel.add(this.cmdTypeSearch);
		JLabel lblCountry_1 = new JLabel("Country");
		lblCountry_1.setFont(new Font("Dialog", 1, 12));
		lblCountry_1.setBounds(429, 20, 80, 13);
		searchpanel.add(lblCountry_1);
		this.cmbCountrySearch.setFont(new Font("Dialog", 1, 12));
		this.cmbCountrySearch.setModel(
				new DefaultComboBoxModel<>(new String[] { "All", "Costa Rica", "Nicaragua", "United States" }));
		this.cmbCountrySearch.setBounds(509, 20, 150, 21);
		searchpanel.add(this.cmbCountrySearch);
		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(new BevelBorder(1, null, null, null, null));
		centerpanel.setBounds(0, 130, 1200, 240);
		this.contentPane.add(centerpanel);
		centerpanel.setLayout((LayoutManager) null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 1200, 173);
		centerpanel.add(scrollPane);
		this.table = new JTable();
		this.table.setFont(new Font("Tahoma", 1, 10));
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					try {
						IdentificationInquiryForm.this.doEdit(true, (String) null);
					} catch (SQLException e1) {
						e1.printStackTrace();
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
		this.table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Id", "Name", "Number", "Issue Date", "Expiration Date", "Expired Y/N", "Birthday",
						"Country", "Security Code", "Type" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true, false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(350);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
		table.getColumnModel().getColumn(6).setPreferredWidth(300);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		table.getColumnModel().getColumn(8).setPreferredWidth(300);
		table.getColumnModel().getColumn(9).setPreferredWidth(300);
		scrollPane.setViewportView(this.table);
		JPanel addpanel = new JPanel();
		addpanel.setBorder(new BevelBorder(1, null, null, null, null));
		addpanel.setBounds(0, 190, 1200, 50);
		centerpanel.add(addpanel);
		addpanel.setLayout((LayoutManager) null);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdentificationInquiryForm.this.doAdd();
			}
		});
		btnAdd.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/plus.png")));
		btnAdd.setFont(new Font("Dialog", 1, 12));
		btnAdd.setBounds(10, 10, 89, 25);
		addpanel.add(btnAdd);
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dialog", 1, 12));
		lblType.setBounds(310, 10, 42, 20);
		addpanel.add(lblType);
		this.cmdTypeNew.setFont(new Font("Dialog", 1, 12));
		this.cmdTypeNew.setModel(new DefaultComboBoxModel<>(
				new String[] { "Not Selected", "Credit Card", "License", "Passport", "Visa", "Resident" }));
		this.cmdTypeNew.setBounds(350, 10, 150, 21);
		addpanel.add(this.cmdTypeNew);
		this.cmbCountryNew.setFont(new Font("Dialog", 1, 12));
		this.cmbCountryNew.setModel(new DefaultComboBoxModel<>(
				new String[] { "Not Selected\t", "Costa Rica", "Nicaragua", "United States" }));
		this.cmbCountryNew.setBounds(600, 10, 150, 21);
		addpanel.add(this.cmbCountryNew);
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Dialog", 1, 12));
		lblCountry.setBounds(520, 10, 80, 13);
		addpanel.add(lblCountry);
		this.cmbName.setModel(new DefaultComboBoxModel<>(new String[] { "Not Selected" }));
		this.cmbName.setFont(new Font("Dialog", 1, 12));
		this.cmbName.setBounds(140, 10, 160, 21);
		addpanel.add(this.cmbName);
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", 1, 12));
		lblName.setBounds(107, 17, 42, 13);
		addpanel.add(lblName);
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBorder(new BevelBorder(1, null, null, null, null));
		buttonpanel.setBounds(0, 376, 1200, 40);
		this.contentPane.add(buttonpanel);
		buttonpanel.setLayout(new FlowLayout(1, 5, 5));
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdentificationInquiryForm.this.dispose();
			}
		});
		JButton lblEdit = new JButton("Edit");
		lblEdit.setFont(new Font("Dialog", 1, 12));
		lblEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IdentificationInquiryForm.this.doEdit(true, (String) null);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdentificationInquiryForm.this.doDelete();
			}
		});
		JButton btnOnedrive_1 = new JButton("OneDrive Online");
		btnOnedrive_1.setFont(new Font("Dialog", 1, 12));
		btnOnedrive_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IdentificationInquiryForm.this.doOneDrive();
			}
		});
		btnOnedrive_1.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/folder.png")));
		buttonpanel.add(btnOnedrive_1);
		btnDelete.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/bin.png")));
		btnDelete.setFont(new Font("Dialog", 1, 12));
		buttonpanel.add(btnDelete);
		lblEdit.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/edit.png")));
		buttonpanel.add(lblEdit);
		btnClose.setFont(new Font("Dialog", 1, 12));
		btnClose.setIcon(new ImageIcon(IdentificationInquiryForm.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);
		JPanel bottempanel = new JPanel();
		bottempanel.setBounds(0, 423, 1200, 40);
		this.contentPane.add(bottempanel);
		bottempanel.setBorder(new BevelBorder(1, null, null, null, null));
		bottempanel.setLayout(new FlowLayout(1, 5, 5));
		JLabel lblClassName = new JLabel("IdentificationInquiryForm");
		lblClassName.setFont(new Font("Tahoma", 1, 10));
		lblClassName.setForeground(Color.BLUE);
		bottempanel.add(lblClassName);
		setTitle("Idendtification Inquiry");
		doGetSearch();
		setLocationRelativeTo(null);
		addNames();
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
		String number = null;
		String issuedate = null;
		String expirationdate = null;
		String expiredYn = null;
		String birthday = null;
		String country = null;
		String securitycode = null;
		String typedescription = null;
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("MMMM dd yyyy");
		try {
			String sql = "SELECT  ";
			sql = String.valueOf(sql) + "*, coalesce(birthday,'1001-01-01') ";
			sql = String.valueOf(sql) + "FROM identification ";
			sql = String.valueOf(sql) + "LEFT JOIN personalinformation on trim(FullName) = trim(Name) ";
			sql = String.valueOf(sql) + "WHERE 1 = 1 ";
			if (this.txtNameSearch.getText().trim().length() > 0) {
				String upper = this.txtNameSearch.getText().trim().toUpperCase();
				sql = String.valueOf(sql) + " AND (UPPER(name))  like '%" + upper + "%'";
			}
			if (this.cmdTypeSearch.getSelectedIndex() != 0) {
				String type = (String) this.cmdTypeSearch.getSelectedItem();
				sql = String.valueOf(sql) + " AND type  = '" + type + "'";
			}
			if (this.cmbCountrySearch.getSelectedIndex() != 0) {
				int i = this.cmbCountrySearch.getSelectedIndex();
				sql = String.valueOf(sql) + " AND countryindex  = '" + i + "'";
			}
			sql = String.valueOf(sql) + "ORDER BY name ";
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				number = rs.getString("number");
				issuedate = rs.getString("issuedate");
				expirationdate = rs.getString("expirationdate");
				birthday = rs.getString("birthday");
				country = rs.getString("country");
				securitycode = rs.getString("securitycode");
				typedescription = rs.getString("typedescription");
				Date date = sdfParse.parse(issuedate);
				issuedate = sdfFormat.format(date);
				date = sdfParse.parse(expirationdate);
				expirationdate = sdfFormat.format(date);
				Date today = new Date();
				expiredYn = "No";
				if (date.before(today))
					expiredYn = "Yes";
				if (birthday == null)
					birthday = "1001-01-01";
				date = sdfParse.parse(birthday);
				birthday = sdfFormat.format(date);
				model.addRow(new Object[] { Integer.valueOf(rs.getInt("id")), rs.getString("name"),
						rs.getString("number"), issuedate, expirationdate, expiredYn, birthday, country, securitycode,
						typedescription });
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
		if (this.cmbName.getSelectedIndex() == 0) {
			this.cmbName.requestFocusInWindow();
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
		try {
			String sql = "SELECT  ";
			sql = String.valueOf(sql) + "* ";
			sql = String.valueOf(sql) + "FROM identification ";
			sql = String.valueOf(sql) + "WHERE 1 = 1 ";
			if (this.cmbName.getSelectedIndex() != 0) {
				Object selectedItem = this.cmbName.getSelectedItem();
				String Nameselected = selectedItem.toString();
				int i = this.cmbCountryNew.getSelectedIndex();
				if (Nameselected.trim().length() > 0) {
					String upper = Nameselected.trim().toUpperCase();
					sql = String.valueOf(sql) + " AND trim(name) = '" + Nameselected + "'";
				}
			}
			if (this.cmbCountryNew.getSelectedIndex() != 0) {
				Object selectedItem = this.cmbCountryNew.getSelectedItem();
				String typeselected = selectedItem.toString();
				int i = this.cmbCountryNew.getSelectedIndex();
				if (typeselected.trim().length() > 0) {
					String upper = typeselected.trim().toUpperCase();
					sql = String.valueOf(sql) + " AND Countryindex = '" + i + "'";
				}
			}
			if (this.cmdTypeNew.getSelectedIndex() != 0) {
				Object selectedItem = this.cmdTypeNew.getSelectedItem();
				String typeselected = selectedItem.toString();
				int i = this.cmdTypeNew.getSelectedIndex();
				if (typeselected.trim().length() > 0) {
					String upper = typeselected.trim().toUpperCase();
					sql = String.valueOf(sql) + " AND typeindex = '" + i + "'";
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
		if (this.cmbName.getSelectedIndex() != 0) {
			Object selectedItem = this.cmbName.getSelectedItem();
			Nameselected = selectedItem.toString();
			int i = this.cmbCountryNew.getSelectedIndex();
		}
		Object selectedItem = this.cmdTypeNew.getSelectedItem();
		String typeselected = selectedItem.toString();
		Object selectedCountry = this.cmbCountryNew.getSelectedItem();
		String countryselected = selectedItem.toString();
		IdentificationEditForm a = new IdentificationEditForm(existyn, Nameselected, typeselected,
				countryselected, id);
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
		field = "txtNameSearch";
		value = this.txtNameSearch.getText().trim();
		try {
			value = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		field = "cmdTypeSearch";
		value = "" + this.cmdTypeSearch.getSelectedIndex();
		try {
			String str = SMLUtility.getValue(this.classname, field, value, "CHK");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		field = "cmbCountrySearch";
		value = "" + this.cmbCountrySearch.getSelectedIndex();
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
		try {
			value = SMLUtility.getValue(this.classname, "txtNameSearch", "", "GET");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.txtNameSearch.setText(value.trim());
		try {
			value = SMLUtility.getValue(this.classname, "cmdTypeSearch", "", "GET");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cmdTypeSearch.setSelectedIndex(Integer.valueOf(value).intValue());
		try {
			value = SMLUtility.getValue(this.classname, "cmbCountrySearch", "", "GET");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cmbCountrySearch.setSelectedIndex(Integer.valueOf(value).intValue());
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
				this.cmbName.addItem(fullname);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
