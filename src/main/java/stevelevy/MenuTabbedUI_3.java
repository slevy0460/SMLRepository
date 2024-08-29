package stevelevy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MenuTabbedUI_3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
	String className = getClass().getSimpleName();
	private static JTable table;
	JButton btnRefresh = new JButton("Refresh");
	JButton btnOpen = new JButton("Open");
	JButton btnClose = new JButton("Close");
	JPanel bottompanel = new JPanel();
	JPanel buttonpanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTabbedUI_3 frame = new MenuTabbedUI_3();
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

	public MenuTabbedUI_3() {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(MenuTabbedUI_3.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		// Set the icon image
//		Image icon = new ImageIcon(getClass().getResource("/Images/Steve-Profile.ico")).getImage();
//		if (icon.getWidth(null) == -1) {
//			System.out.println("Image not found or an error occurred while reading the image");
//		} else {
//			setIconImage(icon);
//		}

		setBounds(100, 100, 1500, 800);
		// Initialize the contentPane
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create a JScrollPane
		JScrollPane mainscrollPane = new JScrollPane();
		// Set the contentPane as the viewport view of the JScrollPane
		mainscrollPane.setViewportView(contentPane);

		// Create a top panel with a preferred height
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1500, 75));
		// Add the top panel to the North region of the BorderLayout
		contentPane.add(topPanel, BorderLayout.NORTH);

		JLabel lblMenu = new JLabel("");
		lblMenu.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/Main Main Menu small.png")));
		topPanel.add(lblMenu);
		centertabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = centertabbedPane.getSelectedIndex();
				if (index == 0) {
					btnRefresh.setEnabled(true);
					btnOpen.setEnabled(true);
					btnClose.setEnabled(true);
				}
			}
		});

		centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// Set your tab icon
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/menu.png")));
		contentPane.add(centertabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centertabbedPane.addTab("Menu", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1500, 555);
		panel.add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				String description = table.getModel().getValueAt(row, column).toString();

				if (e.getClickCount() == 2) {

					// This conditional statement checks if the string 'description' contains either
					// "Choices" or "***".
					// If either condition is met, the method returns immediately, effectively
					// skipping any code that follows this check.
					// This is typically used to filter out certain values of 'description' that
					// should not trigger the subsequent logic.
					if (description.contains("Choices") || description.contains("***")) {
						return;
					}

					try {
						doForm(description);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
							| SecurityException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "", "Description" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);

		table.setFont(new Font("Tahoma", Font.BOLD, 20));

//		tablescrollPane.setViewportView(table);

		// Set the custom panel as the tab component
		centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		// Set the bottom panel
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.setPreferredSize(new Dimension(1500, 100));
		contentPane.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));
		// Set the button panel

		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonpanel.setPreferredSize(new Dimension(1500, 50));
		bottompanel.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPopulateTable();
			}
		});
		btnRefresh.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/refresh.png")));
		buttonpanel.add(btnRefresh);

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "You must select a row to open a form");
					return;
				}
				String description = table.getModel().getValueAt(row, column).toString();

				try {
					doForm(description);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
						| SecurityException | SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnOpen.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/edit.png")));
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonpanel.add(btnOpen);
		btnClose.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);
		// Set the button panel
		JPanel classpanel = new JPanel();
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		classpanel.setPreferredSize(new Dimension(1500, 50));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setText(className);
		lblClassName.setPreferredSize(new Dimension(100, 25));
		lblClassName.setForeground(Color.BLUE);
		classpanel.add(lblClassName);

		// Set the JScrollPane as the contentPane of the JFrame
		setContentPane(mainscrollPane);
		// Center form on monitor
		doCenterForm();
		// call doPopulateTable at the end of the constructor
		getAdmin();
		// Populate the table
		doPopulateTable();

	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private static void doPopulateTable() {

		ResultSet rs = null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.setRowCount(0);
		String userName = System.getProperty("user.name");
		String category = null;
		String savecategory = null;

		String iconbuttonrightarrow = "/Images/arrow-right.png";
		try {
			String sql = "SELECT  ";
			sql += "f.id, f.description, f.class, f.category ";
			sql += "FROM FORMS f ";
			sql += "LEFT JOIN formsecurity s on user = '" + userName + "' AND s.form = f.class ";
			sql += "WHERE ";
			sql += "(SELECT COUNT(*) FROM passwords where type = 'Java User' and ";
			sql += "userid = '" + userName + "' and administrator = 'Yes' ) > 0 ";
			sql += "or s.form = f.class ";
			sql += "ORDER BY f.category, f.description";

//			System.out.println("SQL is : " + sql);
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			table.setForeground(Color.DARK_GRAY);
			while (rs.next()) {

				JLabel iconLabel = new JLabel("");
				iconLabel.setIcon(new ImageIcon(MenuTabbedUI_3.class.getResource("/images/menu.png")));
//				ImageIcon icon = new ImageIcon(MenuTabbedUI_3.class.getResource(iconbuttonrightarrow));
				CellJustifyRendererCenter centerRenderer = new CellJustifyRendererCenter();
//				CellIconColumnRenderer iconRenderer = new CellIconColumnRenderer(icon);
				// Make table alternate colors
				UIDefaults defaults = UIManager.getLookAndFeelDefaults();
				if (defaults.get("Table.alternateRowColor") == null)
					defaults.put("Table.alternateRowColor", new Color(192, 192, 192));
				category = rs.getString("category") + " Choices";
				String description = rs.getString("description");

				// Add category break description to the table
				if (!category.equals(savecategory)) {
					doCategoryBreak(model, category);
					savecategory = category;
				}

				description = rs.getString("description");

				// Iterate over each column in the table
				for (int i = 0; i < table.getColumnCount(); i++) {
					// Set the cell renderer for the current column to centerRenderer
					// centerRenderer is an instance of a class that extends TableCellRenderer
					// This class is responsible for controlling how the cells of the table are
					// displayed
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
					// Set the cell renderer for the first column of the table to iconRenderer
					// iconRenderer is another instance of a class that extends TableCellRenderer
					// This class is likely responsible for rendering cells that contain icons
//					table.getColumnModel().getColumn(0).setCellRenderer(iconRenderer);
				}
				// Add buttons to the first column
//				new ButtonColumn(table, 1, "", "");

				model.addRow(new Object[] { "", description });
			}
            // Set the row height of the table
			int height = 21; 

			for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
				table.setRowHeight(rowIndex, height);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private static void doCategoryBreak(DefaultTableModel model, String category) {
		// TODO Auto-generated method stub
		category = "<html><font color='blue'>" + category + "</font></html>";
		String dash = "<html><font color='blue'>*********************</font></html>";
		model.addRow(new Object[] { null, "" });
		model.addRow(new Object[] { null, dash });
		model.addRow(new Object[] { null, category });
		model.addRow(new Object[] { null, dash });
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

	private void doForm(String description)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		ResultSet rs = null;
		String userName = System.getProperty("user.name");
		String desc = null;
		String classname = null;
		Map<String, String> formClassNames = new HashMap<>();
		// Load the class dynamically
		String sql = "SELECT  ";
		sql += "f.id, f.description, f.class, f.category,  ";
		sql += "(SELECT COUNT(*) FROM admin WHERE user = 'slevy') AS Admin_Count  ";
		sql += "FROM FORMS f ";
		sql += "LEFT JOIN formsecurity s on user = '" + userName + "' AND s.form = f.class ";
		sql += "WHERE ";
		sql += "f.description = '" + description + "' ";
		sql += "ORDER BY category";

//		System.out.println("SQL is : " + sql);

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		} catch (SQLException e) {
			e.printStackTrace();
			// handle the exception
		}

		while (rs.next()) {
			// You'll need to replace this with actual database queries
			desc = rs.getString("description");
			classname = rs.getString("class");
			formClassNames.put(desc, classname);
//			System.out.println("Class is : " + desc + " " + classname);
			// ... add other form descriptions and class names
		}

		Class<?> frameClass = Class.forName(classname);
		// Create an instance of the class
		JFrame frame = (JFrame) frameClass.getDeclaredConstructor().newInstance();
		// Create a panel to hold the content and the close button
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(frame.getContentPane(), BorderLayout.CENTER);
		// Create the close button
		JButton closeButton = new JButton("Close " + description);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the index of the current tab
				int index = centertabbedPane.indexOfComponent(panel);
				if (index != -1) {
					// Remove the tab
					centertabbedPane.removeTabAt(index);
				}
			}
		});
		// Add the close button to the panel
		panel.add(closeButton, BorderLayout.PAGE_END);

		// Add the panel as the new tab
		centertabbedPane.addTab(description, null, panel, "This is a new JFrame tab");
		centertabbedPane.setSelectedIndex(centertabbedPane.getTabCount() - 1);
		int index = centertabbedPane.getSelectedIndex();
		btnRefresh.setEnabled(false);
		btnOpen.setEnabled(false);
		btnClose.setEnabled(false);
	}

}
