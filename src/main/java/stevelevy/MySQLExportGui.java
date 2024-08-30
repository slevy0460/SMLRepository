package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class MySQLExportGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblBackupFolder = new JLabel("Backup Folder :");
	private JTextField txtBackupFolder;
	private JTextField txtBackupFile;
	String className = getClass().getSimpleName();
	private JPanel pnlBackupFolderContents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQLExportGui frame = new MySQLExportGui();
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
	public MySQLExportGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1100, 100, 1242, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(800, 50));
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblMysqlBackupForm = new JLabel("MYSQL Backup Form");
		lblMysqlBackupForm.setPreferredSize(new Dimension(50, 25));
		lblMysqlBackupForm.setForeground(Color.BLUE);
		lblMysqlBackupForm.setFont(new Font("Tahoma", Font.BOLD, 12));
		northPanel.add(lblMysqlBackupForm, BorderLayout.NORTH);

		JLabel lblUseThisForm = new JLabel("Use this form to MYSQL data base");
		lblUseThisForm.setPreferredSize(new Dimension(50, 25));
		lblUseThisForm.setForeground(Color.BLACK);
		lblUseThisForm.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanel.add(lblUseThisForm, BorderLayout.SOUTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Backup MYSQL Data", null, panel, null);
		panel.setLayout(null);

		lblBackupFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBackupFolder.setBounds(22, 30, 100, 15);
		panel.add(lblBackupFolder);
		
		JButton btnSelectFolder = new JButton("Select Folder");
		btnSelectFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectFolder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Allow only directories to be selected
		        int option = fileChooser.showOpenDialog(null);
		        if (option == JFileChooser.APPROVE_OPTION) {
		            File file = fileChooser.getSelectedFile();
		            txtBackupFolder.setText(file.getAbsolutePath()); // Set the text of txtBackupFolder to the selected directory
		            updateBackupFolderContents(); // Update the contents of the backup folder
		        }
		    }
		});
		btnSelectFolder.setBounds(642, 27, 120, 20); // Adjust the position and size as needed
		panel.add(btnSelectFolder);
		
		JLabel lblBackupFile = new JLabel("Backup File :");
		lblBackupFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBackupFile.setBounds(22, 80, 100, 14);
		panel.add(lblBackupFile);

		txtBackupFolder = new JTextField();
		txtBackupFolder.setBounds(132, 27, 500, 20);
		panel.add(txtBackupFolder);
		txtBackupFolder.setColumns(10);

		txtBackupFile = new JTextField();
		txtBackupFile.setColumns(10);
		txtBackupFile.setBounds(132, 77, 500, 20);
		panel.add(txtBackupFile);

		JPanel bottompanel = new JPanel();
		bottompanel.setPreferredSize(new Dimension(800, 100));
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));

		JPanel buttonpanel = new JPanel();
		buttonpanel.setPreferredSize(new Dimension(1500, 50));
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pnlBackupFolderContents = new JPanel();
		pnlBackupFolderContents.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Current Folder", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		pnlBackupFolderContents.setLayout(new BoxLayout(pnlBackupFolderContents, BoxLayout.Y_AXIS));
		// as needed
		JScrollPane scrollPane = new JScrollPane(pnlBackupFolderContents); // Add the panel to a scroll pane
		scrollPane.setBounds(800, 25, 300, 300); // Adjust the position and size as needed

		panel.add(scrollPane); // Add the scroll pane to the panel instead of the pnlBackupFolderContents

		JButton btnBackup = new JButton("Backup");
		btnBackup.setIcon(new ImageIcon(MySQLExportGui.class.getResource("/images/icons8-backup-16.png")));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSetupDirectory();
				doBackup();
				updateBackupFolderContents();
			}

		});
		btnBackup.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonpanel.add(btnBackup);

		JPanel classpanel = new JPanel();
		classpanel.setPreferredSize(new Dimension(1500, 50));
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("ExpenseMaintenanceForm_2");
		lblClassName.setPreferredSize(new Dimension(150, 14));
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setText(className);
		classpanel.add(lblClassName);

		doCenterForm();
		doSetupForm();
		updateBackupFolderContents();
	}

	private void doBackup() {
		String forwardSlash = "/";
		int asciiValue = (int) forwardSlash.charAt(0);
		String hexValue = Integer.toHexString(asciiValue);
		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = "Gtwh2023@mysql";
		String database = "stevelevy";
		String backupfilepath = txtBackupFolder.getText() + forwardSlash; // Get the mysqldumpPath and remove quotes
		String backupFileName = txtBackupFile.getText(); // Get the backup file name and remove quot
		String outputFile = backupfilepath + hexValue + backupFileName + ".sql";
		System.err.println("Backup file path is : " + backupfilepath);
		System.err.println("Backup file name is : " + backupFileName);
		System.err.println("Backup file outfile is : " + outputFile);
		exportDatabase(host, port, user, password, database, outputFile);

	}

	private void exportDatabase(String host, String port, String user, String password, String database,
			String outputFile) {

		String mysqldumpPath = "\"C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump\""; // replace with your actual

		String command = String.format("%s -h %s -P %s -u %s -p%s %s -r \"%s\"", mysqldumpPath, host, port, user,
				password, database, outputFile);

		System.err.println(command);
		try {
			Process process = Runtime.getRuntime().exec(command);

			// Read input stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Read error stream
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = errorReader.readLine()) != null) {
				System.err.println(line);
			}

			int exitCode = process.waitFor();
			if (exitCode == 0) {
				JOptionPane.showMessageDialog(null, "Export successful! " + outputFile);
				System.out.println("Export successful!");
			} else {
				JOptionPane.showMessageDialog(null, "Export failed! " + outputFile);
				System.out.println("Export failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doSetupDirectory() {
		String mysqldumpPath = txtBackupFolder.getText().replace("\"", ""); // Get the mysqldumpPath and remove quotes
		String backupFileName = txtBackupFile.getText().replace("\"", ""); // Get the backup file name and remove quotes
		// Combine the mysqldumpPath and backupFileName to create the full directory
		// path
		Path dirPath = Paths.get(mysqldumpPath);

		try {

			// Check if the directory already existed
			if (Files.exists(dirPath)) {
//				JOptionPane.showMessageDialog(null, "Directory already exists: " + dirPath);
//				System.out.println("Directory already exists: " + dirPath);
			} else {
				// Create the directory
				Files.createDirectories(dirPath);

//				JOptionPane.showMessageDialog(null, "Created new directory: " + dirPath);
//				System.out.println("Created new directory: " + dirPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doSetupForm() {

		// with
		String path = "S:\\Users\\slevy\\OneDrive\\Personal Miscellaneous\\Backups\\MySQL Backup"; // your
		// actual
		txtBackupFolder.setText(path.replace("\"", ""));
		// Get today's date
		LocalDate today = LocalDate.now();

		// Format the date as yyyymmdd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDate = today.format(formatter);

		// Set the formatted date as the text of txtBackupFile
		txtBackupFile.setText(formattedDate);
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	// Call this method whenever you want to update the contents of the JPanel
	public void updateBackupFolderContents() {
		String backupFolderPath = txtBackupFolder.getText();
		Path dirPath = Paths.get(backupFolderPath);
		pnlBackupFolderContents.removeAll(); // Remove all existing labels
		try {
			try (Stream<Path> paths = Files.list(dirPath)) {

				paths.sorted(Comparator.comparing((Path path) -> {
					try {
						return Files.readAttributes(path, BasicFileAttributes.class).creationTime();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}).reversed()) // Sort by creation time in descending order
						.forEach(path -> {
							JLabel label = new JLabel(path.getFileName().toString());
							label.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Set the font size to 14
							label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Add some space below each
																							// label
							pnlBackupFolderContents.add(label);
						});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pnlBackupFolderContents.revalidate(); // Re-layout the panel
		pnlBackupFolderContents.repaint(); // Refresh the panel
	}
}
