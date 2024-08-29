package stevelevy;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SMLStartup {

	private static String administrator;
	private static String tabs;

	public static void main(String[] args) throws SQLException, IOException {
		getUserInformation();

		if (!tabs.equals("Yes")) {
//			Menu_6 a = new Menu_6();
			MenuTabbedUI_8 a = new MenuTabbedUI_8();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			a.setVisible(true);
		} else {
			MenuTabbedUI_8 a = new MenuTabbedUI_8();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			a.setVisible(true);
		}

	}

	private static void getUserInformation() {
		String userName = System.getProperty("user.name");
		String type = "Java User";
		ResultSet rs = null;
		String sql = null;

		try {
			sql = "select ";
			sql += "type, administrator, tabs ";
			sql += "from passwords ";
			sql += "where ";
			sql += "userid = '" + userName + "' ";
			sql += "and type = '" + type + "' ";

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				administrator = rs.getString("administrator");
				tabs = rs.getString("tabs");

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}
}