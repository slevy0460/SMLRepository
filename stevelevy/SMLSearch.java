package stevelevy;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SMLSearch {

	public static String getValue(String classname, String field, String value, String type) throws SQLException {

		if (type == "CHK") {
			boolean foundsearch = checkSearch(classname, field);
			if (!foundsearch) {
				addSearch(classname, field, value);
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
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
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
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
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
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
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
//		System.out.println ("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
