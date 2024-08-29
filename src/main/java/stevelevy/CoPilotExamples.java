package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;

public class CoPilotExamples extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String className = getClass().getSimpleName();
	JButton btnPreparedStatementUpdate = new JButton("PreparedStatement  Update");
	JButton btnPreparedStatementSelect = new JButton("PreparedStatement  Select");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoPilotExamples frame = new CoPilotExamples();
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
	public CoPilotExamples() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel toppanel = new JPanel();
		toppanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toppanel.setBounds(0, 10, 440, 50);
		contentPane.add(toppanel);
		toppanel.setLayout(null);

		JLabel lblTitle = new JLabel("CoPilot Examples");
		lblTitle.setBounds(10, 10, 100, 20);
		lblTitle.setForeground(Color.BLUE);
		toppanel.add(lblTitle);

		JPanel centerpanel = new JPanel();
		centerpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerpanel.setBounds(0, 60, 440, 200);
		contentPane.add(centerpanel);

		btnPreparedStatementUpdate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPreparedStatementUpdate.setBounds(140, 50, 200, 21);
		btnPreparedStatementUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doPreparedStatementUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		centerpanel.setLayout(null);
		centerpanel.add(btnPreparedStatementUpdate);

		btnPreparedStatementSelect.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPreparedStatementSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPreparedStatementSelect();
			}
		});
		btnPreparedStatementSelect.setBounds(140, 130, 200, 21);
		centerpanel.add(btnPreparedStatementSelect);

		JButton btnPreparedStatementInsert = new JButton("PreparedStatement  Insert");
		btnPreparedStatementInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doPreparedStatementInsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPreparedStatementInsert.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPreparedStatementInsert.setBounds(140, 10, 200, 21);
		centerpanel.add(btnPreparedStatementInsert);

		JButton btnPreparedStatementDeleted = new JButton("PreparedStatement  Delete");
		btnPreparedStatementDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doPreparedStatementDelete();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPreparedStatementDeleted.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPreparedStatementDeleted.setBounds(140, 90, 200, 21);
		centerpanel.add(btnPreparedStatementDeleted);

		JPanel bottompanel = new JPanel();
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.setBounds(0, 275, 440, 40);
		contentPane.add(bottompanel);
		bottompanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton lblClose = new JButton("Close");
		lblClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		lblClose.setIcon(new ImageIcon(CoPilotExamples.class.getResource("/images/close.png")));
		bottompanel.add(lblClose);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 315, 440, 33);
		contentPane.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				JLabel lblClass = new JLabel("Class");
				panel.add(lblClass);
				lblClass.setForeground(Color.BLUE);
				lblClass.setText(className);
		
		setTitle("Copilot Examples");
	}

	private void doPreparedStatementInsert() throws SQLException {
		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";

		try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
			String sql = "INSERT INTO testdatabase (description) VALUES (?) ";
			try (PreparedStatement ps = conn.prepareStatement(sql);) {
				// Set the corresponding parameters
				ps.setString(1, "David Levy"); // Set current monitor
				ps.executeUpdate();
				// Update
				int rowsUpdated = ps.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("Inserted successfully!");
				} else {
					System.out.println("Inserted not successfully!");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void doPreparedStatementUpdate() throws SQLException {

		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";

		try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
			String sql = "UPDATE testdatabase SET description = ? WHERE id = ?  ";
			try (PreparedStatement ps = conn.prepareStatement(sql);) {
				// Set the corresponding parameters
				ps.setString(1, "David Alejandro Levy"); // Set current monitor
				ps.setString(2, "8"); // Set id
				ps.executeUpdate();
				// Update
				int rowsUpdated = ps.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("Updated successfully!");
				} else {
					System.out.println("Updated not successfully!");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void doPreparedStatementDelete() throws SQLException {

		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";

		try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
			String sql = "DELETE FROM testdatabase WHERE id = ?  ";
			try (PreparedStatement ps = conn.prepareStatement(sql);) {
				ps.setString(1, "4"); // Set id
				ps.executeUpdate();
				// Delete
				System.out.println("Delete successfully!");
			} catch (SQLException e) {

				System.out.println(e.getMessage());
				JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void doPreparedStatementSelect() {
		String dbUrl = "jdbc:mysql://192.168.0.4:3306/stevelevy";
		String username = "steve";
		String password = "Gtwh2023@mysql";
		String id = null;
		String description = null;

		try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
			String sql = "SELECT * from testdatabase WHERE id <> ?  ";
			try (PreparedStatement ps = conn.prepareStatement(sql);) {
				// Set the corresponding parameters
				ps.setString(1, "0"); // Set id
				ResultSet resultSet = ps.executeQuery();
				// Result set
				int rowCount = 0;
				while (resultSet.next()) {
					rowCount += 1;
					id = resultSet.getString("id");
					description = resultSet.getString("description");
					System.out.println("Row #" + rowCount + " " + "Id #" + id + " " + description);
				}
				if (rowCount > 0) {
					btnPreparedStatementSelect.setText("Row count: " + rowCount);
					System.out.println("Selected successfully!");
				} else {
					System.out.println("Selected not successfully!");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
