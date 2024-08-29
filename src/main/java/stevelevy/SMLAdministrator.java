package stevelevy;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SMLAdministrator {

	@SuppressWarnings("null")
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

//		System.out.println("Administrator is : " + array[0]);
//		System.out.println("User          is : " + array[1]);
//		System.out.println("Class in      is : " + classname);
//		System.out.println("Authorized       : " + array[2]);

		return array;
	}

	private static String getAdmin() throws SQLException {
		ResultSet rs = null;
		String admin = null;
		String sql = "SELECT * FROM ADMIN WHERE ID = 1";
		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		while (rs.next()) {
			admin = (rs.getString("user"));
		}
		return admin;
	}

}
