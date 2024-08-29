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
import java.io.IOException;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class MenuTabbedUI_4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
	String className = getClass().getSimpleName();
	static JButton btnRefresh = new JButton("Refresh");
	static JButton btnOpen = new JButton("Open");
	static JButton btnClose = new JButton("Close");
	JPanel bottompanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	static JPanel menupanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTabbedUI_4 frame = new MenuTabbedUI_4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */

	public MenuTabbedUI_4() throws SQLException, IOException {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(MenuTabbedUI_4.this);
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
		lblMenu.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource("/images/Main Main Menu small.png")));
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
		iconLabel.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource("/images/menu.png")));
		contentPane.add(centertabbedPane, BorderLayout.CENTER);

		menupanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centertabbedPane.addTab("Menu", null, menupanel, null);
		menupanel.setLayout(new BoxLayout(menupanel, BoxLayout.Y_AXIS));
//		menupanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
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
				try {
					doPopulateTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource("/images/refresh.png")));
		buttonpanel.add(btnRefresh);

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnOpen.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource("/images/edit.png")));
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonpanel.add(btnOpen);
		btnClose.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource("/images/close.png")));
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

	private void doPopulateTable() throws SQLException, IOException {
		String userName = System.getProperty("user.name");
		String category = null;
		String savecategory = null;
		ResultSet rs = null;
		String iconbuttonrightarrow = "/Images/arrow-right.png";
		String sql = "SELECT  ";
		sql += "f.id, f.description, f.class, f.category, f.icon  ";
		sql += "FROM FORMS f ";
		sql += "LEFT JOIN formsecurity s on user = '" + userName + "' AND s.form = f.class ";
		sql += "WHERE ";
		sql += "(SELECT COUNT(*) FROM passwords where type = 'Java User' and ";
		sql += "userid = '" + userName + "' and administrator = 'Yes' ) > 0 ";
		sql += "or s.form = f.class ";
		sql += "ORDER BY f.category, f.description";

		System.out.println("SQL is : " + sql);
		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		   // Add vertical glue before adding buttons
	   
		while (rs.next()) {
			String description = rs.getString("description");
			String icon = rs.getString("icon");

			JButton button = new JButton(description);
			button.setIcon(new ImageIcon(MenuTabbedUI_4.class.getResource(icon)));
			 Dimension preferredDimension = new Dimension(300, 30); // Set preferred width and height to 100
		        button.setMaximumSize(preferredDimension); // Set preferred size

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						doForm(description);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		
	        // Add the butten to the menupanel
	        menupanel.add(button);
	        
	        
		}
		
		menupanel.revalidate();
	    menupanel.repaint();
	    int x = menupanel.getComponentCount();
		if (menupanel.getComponentCount() > 10) { // replace 10 with your actual limit
			JScrollPane scrollPane = new JScrollPane(menupanel);
			centertabbedPane.addTab("Menu", null, scrollPane, null);
		} else {
			centertabbedPane.addTab("Menu", null, menupanel, null);
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

	private static void doForm(String description)
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
