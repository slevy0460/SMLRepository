package stevelevy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_6 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtMonitor;
	String className = getClass().getSimpleName();
	private static JTable table;
	static Menu_6 frame = new Menu_6();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					// center the form on the monitor
					frame.setLocationRelativeTo(frame);
					// call doPopulateTable at the end of the constructor
					getAdmin();
					doPopulateTable();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu_6() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_top = new JPanel();
		panel_top.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_top.setBounds(0, 0, 1500, 150);
		contentPane.add(panel_top);
		panel_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu_6.class.getResource("/images/Main Main Menu.png")));
		panel_top.add(lblNewLabel);

		JPanel panel_center = new JPanel();
		panel_center.setLayout(null);
		panel_center.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_center.setBounds(0, 150, 1500, 440);
		contentPane.add(panel_center);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1500, 450);
		panel_center.add(scrollPane);

		table = new JTable();
		// Make table alternate colors
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(192, 192, 192));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				String description = table.getModel().getValueAt(row, column).toString();
				if (e.getClickCount() == 2) {
					try {
						doForm(description, frame);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
							| SecurityException | SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "", "Description" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(0).setMaxWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);

		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		// Assuming you have a JTable named "table"
		JTableHeader header = table.getTableHeader();

		// Set the font
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);

		ImageIcon icon = new ImageIcon(Menu_6.class.getResource("/images/arrow-right.png"));
		CellIconColumnRenderer iconRenderer = new CellIconColumnRenderer(icon);
		table.getColumnModel().getColumn(0).setCellRenderer(iconRenderer);

		table.getColumnModel().getColumn(1).setCellRenderer(new CellJustifyRendererCenter());

		scrollPane.setViewportView(table);

		JPanel panel_monitor = new JPanel();
		panel_monitor.setLayout(null);
		panel_monitor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_monitor.setBounds(0, 600, 1500, 75);
		contentPane.add(panel_monitor);

		JLabel lblNewLabel_1 = new JLabel("Current Monitor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 30, 100, 13);
		panel_monitor.add(lblNewLabel_1);

		txtMonitor = new JTextField();
		txtMonitor.setText("0");
		txtMonitor.setColumns(10);
		txtMonitor.setBounds(100, 25, 80, 20);
		panel_monitor.add(txtMonitor);

		JLabel lblDate = new JLabel("Date : 05/11/2024");
		lblDate.setBounds(600, 30, 100, 20);
		panel_monitor.add(lblDate);

		JPanel panel_buttons = new JPanel();
		panel_buttons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_buttons.setBounds(0, 674, 1500, 40);
		contentPane.add(panel_buttons);
		panel_buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(Menu_6.class.getResource("/images/refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPopulateTable();
			}
		});
		panel_buttons.add(btnRefresh);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(Menu_6.class.getResource("/images/close.png")));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel_buttons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(-7, 713, 1500, 40);
		contentPane.add(jPanelBottom);
		jPanelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClassName.setText(className);
		lblClassName.setForeground(Color.BLUE);
		jPanelBottom.add(lblClassName);
		setTitle("Main Menu");
		doCenterForm();
		getAdmin();
		doPopulateTable();

	}

	private static void doPopulateTable() {

		ResultSet rs = null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		String userName = System.getProperty("user.name");
		String category = null;
		String savecategory = null;
		String dash = "********************";

		try {
			String sql = "SELECT  ";
			sql += "f.id, f.description, f.class, f.category,  ";
			sql += "(SELECT COUNT(*) FROM admin WHERE user = 'slevy') AS Admin_Count  ";
			sql += "FROM FORMS f ";
			sql += "LEFT JOIN formsecurity s on user = '" + userName + "' AND s.form = f.class ";
			sql += "WHERE (SELECT COUNT(*) FROM admin WHERE user = '" + userName + "') > 0 ";
			sql += "or s.form = f.class ";
			sql += "ORDER BY f.category, f.description";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				category = rs.getString("category") + " Choices";

				if (!category.equals(savecategory)) {
					savecategory = category;
					model.addRow(new Object[] { "", "", "" });
					model.addRow(new Object[] { "", dash, "" });
					model.addRow(new Object[] { "", category, "" });
					model.addRow(new Object[] { "", dash, "" });
				}
//				model.addRow(new Object[] { rs.getInt("id"), rs.getString("description") });
				model.addRow(new Object[] { "", rs.getString("description") });
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private static void doForm(String description, Menu_6 Menu_5) throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ParseException {
		// Simulate data retrieval from the database
		ResultSet rs = null;
		String userName = System.getProperty("user.name");
		String desc = null;
		String classname = null;

		Map<String, String> formClassNames = new HashMap<>();

		try {
			String sql = "SELECT  ";
			sql += "f.id, f.description, f.class, f.category,  ";
			sql += "(SELECT COUNT(*) FROM admin WHERE user = 'slevy') AS Admin_Count  ";
			sql += "FROM FORMS f ";
			sql += "LEFT JOIN formsecurity s on user = '" + userName + "' AND s.form = f.class ";
			sql += "WHERE (SELECT COUNT(*) FROM admin WHERE user = '" + userName + "') > 0 ";
			sql += "or s.form = f.class ";
			sql += "ORDER BY category";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				// You'll need to replace this with actual database queries
				desc = rs.getString("description");
				classname = rs.getString("class");
				formClassNames.put(desc, classname);
//				System.out.println("Class is : " + desc + " " + classname);
				// ... add other form descriptions and class names
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		if (formClassNames.containsKey(description)) {
			String newform = formClassNames.get(description);
			// Load the class dynamically
			try {
				Class<?> frameClass = Class.forName(newform);
				if (JFrame.class.isAssignableFrom(frameClass)) {
					// Instantiate the JFrame form
					JFrame frame = (JFrame) frameClass.getDeclaredConstructor().newInstance();

					// Display the JFrame form
					openForm(Menu_5, frame);
				}
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Class not found: " + newform);
			}
		}
	}

	private static void openForm(JFrame Menu_5, JFrame CalledFrame) throws SQLException, ParseException {
		try {

			frame.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			int screen = SMLUtility.getCurrentMonitorInfo(Menu_5);
			txtMonitor.setText(String.valueOf(screen));
			SMLUtility.showOnScreen(screen, CalledFrame);
			CalledFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean getAdmin() {
		boolean isAdmin = false;
		ResultSet rs = null;
		String userName = System.getProperty("user.name");
		String sql = "SELECT * FROM admin";
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				if (rs.getString("user").equals(userName)) {
					isAdmin = true;
				} else {
					isAdmin = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("isAdmin is : " + isAdmin);
		return isAdmin;
	}

	private void doCenterForm() {
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}
}
