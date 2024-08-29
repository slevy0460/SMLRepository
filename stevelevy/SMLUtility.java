package stevelevy;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SMLUtility {

	// Get User Information - SMLStartup
	public static String userName = null;
	public static String computerName = null;
	public static String sysp03user = null;
	public static String sysp03password = null;
	public static String jorduser = null;
	public static String jordpassword = null;

	// Get Administrator password - SMLAdminstrator
	public static String[] getAdministrator(String classname) {
		String array[] = new String[3];
		String addministrator = null;
		try {
			addministrator = getAdmin();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String userName = System.getProperty("user.name");
		String passwordinquiry = "PasswordInquiryForm";
		boolean authyn = true;

		if (!classname.equals(passwordinquiry)) {
			if (!userName.equals(addministrator)) {
				authyn = false;
				if (!classname.equals("Menu")) {
//					JOptionPane.showMessageDialog(null, "Not authorized to this program");
				}

			}
		}

		array[0] = addministrator;
		array[1] = userName;
		array[2] = String.valueOf(authyn);

		return array;
	}

	private static String getAdmin() throws SQLException {
		ResultSet rs = null;
		String admin = null;
		String sql = "SELECT * FROM ADMIN WHERE ID = 1";
		rs = getResultSet(sql, "SQL", "INQ");
		while (rs.next()) {
			admin = (rs.getString("user"));
		}
		return admin;
	}

	// Create Connection - SMLUtility
	public static ResultSet getResultSet(String query, String database, String type) throws SQLException {

		Connection dbcon;
		String url = null;
		String username = null;
		String password = null;
		String connectioninfo[] = null;
		String Driver = "com.ibm.as400.access.AS400JDBCDriver";

		connectioninfo = getConnectionInfo(database);
		url = connectioninfo[0];
		username = connectioninfo[1];
		password = connectioninfo[2];

		if (type == "INQ") {
			ResultSet rs = getResult(url, database, username, password, query, type);
			return rs;
		} else if (type == "CLS") {
			doClose();
		} else {
			int i = doUpdate(url, database, username, password, query, type);
		}
		return null;
	}

	private static int doUpdate(String url, String database, String username, String password, String query,
			String type) throws SQLException {
		Connection dbCon = null;
		int i = 0;
		String Driver = "com.ibm.as400.access.AS400JDBCDriver";

		if (database != "SQL") {
			try {
				Class.forName(Driver);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		dbCon = DriverManager.getConnection(url, username, password);
		PreparedStatement stmt = null;
		stmt = dbCon.prepareStatement(query);
		i = stmt.executeUpdate();

		return i;
	}

	private static ResultSet getResult(String url, String database, String username, String password, String query,
			String type) throws SQLException {
		Connection dbCon = null;
		ResultSet rs = null;
		String Driver = "com.ibm.as400.access.AS400JDBCDriver";

		if (database != "SQL") {
			try {
				Class.forName(Driver);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		try {
			dbCon = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = null;
			stmt = dbCon.prepareStatement(query);
			rs = stmt.executeQuery();
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		} finally {

		}

		return rs;
	}

	@SuppressWarnings("unused")
	private static String[] getConnectionInfo(String database) {
		String url = null;
		String username = null;
		String password = null;

		if (database == "SQL") {
//			url = "jdbc:mysql://192.168.1.12:3306/stevelevy"; // Gene 
			url = "jdbc:mysql://192.168.50.115:3306/stevelevy"; // ethernet
//			url = "jdbc:mysql://localhost:3306/stevelevy"; // ethernet
//			url = "jdbc:mysql://192.168.50.203:3306/stevelevy"; // wifi 
//			url = "jdbc:mysql://sml-mysql.mysql.database.azure.com/stevelevy"; // azure
			username = "steve";
			password = "Gtwh2023@mysql";
		} else if (database == "SYSP03") {
			getIseriesInfo("SYSP03");
			url = "jdbc:as400://10.200.98.247";
			username = sysp03user;
			password = sysp03password;
		} else {
			getIseriesInfo("JORD");
			url = "jdbc:as400://172.20.193.10";
			username = jorduser;
			password = jordpassword;
		}

		String[] connection = new String[3];
		connection[0] = url;
		connection[1] = username;
		connection[2] = password;

		return connection;
	}

	private static void getIseriesInfo(String system) {
		ResultSet rs = null;
		String sql = null;
		sql = "SELECT ";
		sql += "ID, ACCOUNTDESCRIPTION, ACCOUNTNUMBER, USERID, PASSWORD ";
		sql += "FROM PASSWORDS ";
		sql += "WHERE ACCOUNTDESCRIPTION LIKE '%WORK SYSTEMS%' ";
		sql += "AND ACCOUNTNUMBER = '" + system + "'";
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				if (system.equals("SYSP03")) {
					sysp03user = (rs.getString("USERID"));
					sysp03password = (rs.getString("PASSWORD"));
				} else {
					jorduser = (rs.getString("USERID"));
					jordpassword = (rs.getString("PASSWORD"));
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void doClose() {
		Connection dbCon = null;
		try {
			dbCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// Get Search - SmlSearch
	public static String getValue(String classname, String field, String value, String type) throws SQLException {

		if (type == "CHK") {
			boolean foundsearch = checkSearch(classname, field);
			if (!foundsearch) {
				addSearch(classname, field, "");
			} else {
				updateSearch(classname, field, value);
			}
		} else {

			String result = field.substring(0, 3);
			if (result.equals("cmb")) {
				if (value.length() == 0) {
					value = "0";
				}
			} else {
				if (value.length() == 0) {
					value = "";
				}
			}

			value = getSearch(classname, field, value);

		}

		return value;
	}

	private static String getSearch(String classname, String field, String value) {
		ResultSet rs = null;
		String userName = System.getProperty("user.name");

		String sql = "SELECT * FROM SEARCHES ";
		sql += "WHERE CLASS = '" + classname + "' ";
		sql += "AND Windowsuser = '" + userName + "' ";
		sql += "AND FIELD = '" + field + "'";

		try {
			rs = getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				value = (rs.getString("Value"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String result = field.substring(0, 3);
		if (result.equals("cmb")) {
			if (value.length() == 0) {
				value = "0";
			}
		} else {
			if (value.length() == 0) {
				value = " ";
			}
		}
		return value;

	}

	private static boolean checkSearch(String classname, String field) {
		boolean foundsearch = false;
		ResultSet rs = null;
		String userName = System.getProperty("user.name");

		String sql = "SELECT * FROM SEARCHES ";
		sql += "WHERE CLASS = '" + classname + "' ";
		sql += "AND Windowsuser = '" + userName + "' ";
		sql += "AND FIELD = '" + field + "'";

		try {
			rs = getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				foundsearch = true;
				return foundsearch;
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return foundsearch;
	}

	private static void addSearch(String classname, String field, String value) {

		ResultSet rs = null;
		String userName = System.getProperty("user.name");
		String sql = "Insert into searches (class, Windowsuser, field, value) VALUES ( ";

		sql += "'" + classname.trim() + "',";
		sql += "'" + userName.trim() + "',";
		sql += "'" + field.trim() + "', ";
		sql += "'" + value.trim() + "')";

		try {
			rs = getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void updateSearch(String classname, String field, String value) {

		ResultSet rs = null;
		String userName = System.getProperty("user.name");

		String sql = "UPDATE SEARCHES SET ";

		sql += "VALUE = '" + value.trim() + "' ";
		sql += "WHERE CLASS = '" + classname + "' ";
		sql += "AND FIELD = '" + field + "' ";
		sql += "AND Windowsuser = '" + userName + "' ";

		try {
			rs = getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	Get Column index - getColumnIndex
	static int getColumnIndex(JTable table, String header) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumnName(i).equals(header))
				return i;
		}
		return -1;
	}

	public static void showOnScreen(int screen, JFrame frame) throws SQLException {
		String sql = "SELECT * FROM MONITOR ";
		sql += " where computername = '" + computerName + "' ";
		ResultSet rs = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		if (screen == -1) {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				screen = Integer.parseInt((rs.getString("current")));
			}
		}

		if (screen > -1 && screen < gd.length) {
			frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
		} else if (gd.length > 0) {
			frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
		} else {
			throw new RuntimeException("No Screens Found");
		}
	}

	static int getCurrentMonitorInfo(JFrame frame) throws SQLException, UnknownHostException {
		getUserInformation();
		int s = 0;
		// Get all available screens (monitors)
		GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

		// Assuming your application is running in a JFrame
		// You can replace this with your actual JFrame instance
//		JFrame myFrame = new JFrame("My Java App");

		// Get the GraphicsConfiguration associated with the JFrame
		GraphicsConfiguration config = frame.getGraphicsConfiguration();

		// Get the GraphicsDevice (monitor) where the JFrame is currently displayed
		GraphicsDevice currentMonitor = config.getDevice();

		if (currentMonitor.getIDstring().equals("\\Display0")) {
			s = 0;
		} else if (currentMonitor.getIDstring().equals("\\Display1")) {
			s = 1;
		} else if (currentMonitor.getIDstring().equals("\\Display2")) {
			s = 2;
		}
		boolean doAdd = doCheckMonitor(s);
		if (doAdd) {
			doAddMonitor(s);
		} else {
			doUpdateMonitor(s);
		}

		return s;
	}

	private static void getUserInformation() {
		userName = System.getProperty("user.name");
		try {
			computerName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean doCheckMonitor(int s) throws SQLException {

		boolean doAdd = false;
		int rowCount = 0;
		String sql = null;
		ResultSet rs = null;
		sql = "SELECT * FROM  MONITOR ";
		sql += "WHERE computername = '" + computerName + "' ";

		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

		// Result set
		while (rs.next()) {
			rowCount += 1;
		}
		if (rowCount > 0) {
			doAdd = false;
		} else {
			doAdd = true;
		}

		return doAdd;

	}

	private static void doAddMonitor(int s) throws SQLException {
		String sql = null;
		ResultSet rs = null;

		sql = "INSERT MONITOR ";
		sql += "(CURRENT, COMPUTERNAME )";
		sql += "VALUES (";
		sql += s + ", ";
		sql += "'" + computerName + "') ";

		rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
	}

	static void doUpdateMonitor(int screen) throws SQLException {
		String sql = null;
		ResultSet rs = null;

		sql = "UPDATE MONITOR ";
		sql += "SET CURRENT = " + screen + " ";
		sql += "WHERE computername = '" + computerName + "'";

		rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
	}

	public static void PreparedStatmentUpdate(PreparedStatement ps) {
		try {

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {

			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet doPreparedStatementSelect() {
		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";
		ResultSet resultSet = null;

		try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
			String sql = "SELECT * from pbankaccount WHERE id <> ?  ";
			try (PreparedStatement ps = conn.prepareStatement(sql);) {
				// Set the corresponding parameters
				ps.setString(1, "0"); // Set id
				resultSet = ps.executeQuery();
				// Result set
				int rowCount = 0;
				while (resultSet.next()) {
					rowCount += 1;
				}
				if (rowCount > 0) {

				} else {

				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
//			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultSet;

	}

}
