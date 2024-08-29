package stevelevy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SMLConnection {

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
		
		} finally {

		}

		return rs;
	}

	@SuppressWarnings("unused")

/**
 * This method is used to get the connection information for a specific database.
 * It returns an array of Strings containing the URL, username, and password for the database connection.
 *
 * @param database The name of the database for which the connection information is required.
 *                 It can be "SQL" for a MySQL database, "SYSP03" for a specific AS400 database, 
 *                 or any other value for a default AS400 database.
 * @return A String array containing the URL, username, and password for the database connection in that order.
 */
private static String[] getConnectionInfo(String database) {
    String url = null;
    String username = null;
    String password = null;

    // If the database is a MySQL database
    if (database.equals("SQL")) {
        // The URL for the MySQL database
        // The username for the MySQL database
        username = "steve";
        // The password for the MySQL database
        password = "Gtwh2023@mysql";
    } 
    // If the database is the specific AS400 database "SYSP03"
    else if (database.equals("SYSP03")) {
        // The URL for the AS400 database "SYSP03"
        url = "jdbc:as400://10.200.98.247";
        // The username for the AS400 database "SYSP03"
        username = "rlmsteve";
        // The password for the AS400 database "SYSP03"
        password = "sailboat29";
    } 
    // If the database is any other AS400 JORD database
    else {
        // The URL for the default AS400 database
        url = "jdbc:as400://172.20.193.10";
        // The username for the default AS400 database
        username = "rlmsteve";
        // The password for the default AS400 database
        password = "sailboat29";
    }

    // An array to hold the connection information
    String[] connection = new String[3];
    connection[0] = url;
    connection[1] = username;
    connection[2] = password;

    // Return the connection information
    return connection;
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
}