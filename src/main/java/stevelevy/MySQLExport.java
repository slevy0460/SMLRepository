package stevelevy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySQLExport {
	public static void exportDatabase(String host, String port, String user, String password, String database,
			String outputFile) {
		String mysqldumpPath = "\"C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump\""; // replace with your actual
																							// mysqldump path
		outputFile = "\"" + outputFile + "\""; // wrap outputFile in quotes
		String command = String.format("%s -h %s -P %s -u %s -p%s %s -r %s", mysqldumpPath, host, port, user, password,
				database, outputFile);
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
				System.out.println("Export successful!");
			} else {
				System.out.println("Export failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = "Gtwh2023@mysql";
		String database = "stevelevy";
		String outputFile = "S:/Users/slevy/OneDrive/Personal Miscellaneous/Backups/MySQL Backup/20240822/outputfile.sql";

		exportDatabase(host, port, user, password, database, outputFile);
	}
}
