package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SQLPreparedStatementTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQLPreparedStatementTest frame = new SQLPreparedStatementTest();
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
	public SQLPreparedStatementTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(SQLPreparedStatementTest.class.getResource("/images/updated.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}

			private void doUpdate() {

				String username = "steve";
				String password = "Gtwh2023@mysql";
				String url = "jdbc:mysql://192.168.0.4:3306/stevelevy";
				String sql = "UPDATE MONITOR SET CURRENT = ? WHERE ID = ?";

				try (Connection connection = DriverManager.getConnection(url, username, password);
						PreparedStatement pstmt = connection.prepareStatement(sql)) {

					pstmt.setString(1, "1");
					pstmt.setString(2, "1");

					int affectedRows = pstmt.executeUpdate();
					if (affectedRows > 0) {
						System.out.println("Update successful");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		});
		btnUpdate.setBounds(179, 100, 144, 21);
		contentPane.add(btnUpdate);
	}

}
