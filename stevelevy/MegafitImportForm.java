package stevelevy;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class MegafitImportForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumberofRecords;
//	String dbURL = "jdbc:mysql://localhost:3306/stevelevy";
	String dbURL = "jdbc:mysql://192.168.0.3:3306/stevelevy";
	String username = "steve";
	String password = "Gtwh2022#mysql";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MegafitImportForm frame = new MegafitImportForm();
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
	public MegafitImportForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setLayout(null);
		jPanelTop.setBounds(0, 0, 465, 65);
		contentPane.add(jPanelTop);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 64, 465, 125);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JLabel lblNewLabel = new JLabel("Number of records imported");
		lblNewLabel.setBounds(20, 30, 140, 14);
		jPanelCenter.add(lblNewLabel);

		txtNumberofRecords = new JTextField();
		txtNumberofRecords.setBounds(188, 27, 86, 20);
		jPanelCenter.add(txtNumberofRecords);
		txtNumberofRecords.setColumns(10);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 189, 465, 41);
		contentPane.add(jPanelButtons);

		JButton btnImport = new JButton("Import");
		btnImport.setIcon(new ImageIcon(MegafitImportForm.class.getResource("/images/download.png")));
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doImport();
			}

		});
		jPanelButtons.add(btnImport);

		JButton btnInquiry = new JButton("Inquiry");
		btnInquiry.setIcon(new ImageIcon(MegafitImportForm.class.getResource("/images/task-planning.png")));
		btnInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doWeightInquiry();
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelButtons.add(btnInquiry);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(MegafitImportForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(0, 230, 465, 41);
		contentPane.add(jPanelBottom);

		JLabel lblClassname = new JLabel("ClassName");
		jPanelBottom.add(lblClassname);
		String className = getClass().getSimpleName();
		lblClassname.setText(className);
		setTitle("Megafit Import");
		// Center Form
		doCenterFrom();
		setTitle("Weight Inquiry");
	}

	private void doCenterFrom() {
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}

	private void doImport() {
		String lastid = doGetLastId();
		String newid = null;
		
		int work = Integer.valueOf(lastid);
//		work += 1;
		newid = Integer.toString(work);
		doMegafit(newid);
	}

	private void doWeightInquiry() throws SQLException, ParseException {
		WeightInquiryForm a = new WeightInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		a.setVisible(true);
		dispose();
	}

	private void doMegafit(String newid) {

		ResultSet rs = null;
		String id = null;
		int totalcount = 0;
		int intid = Integer.valueOf(newid);
		String query = "select * from megafit order by date";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");

			while (rs.next()) {
				totalcount = totalcount + 1;
				intid = intid + 1;
				String date = rs.getString("date");
				if (date.equals("20170330")) {
					String stop = "Yes";
				}
				String weight = rs.getString("weight");
				String gainloss = "0";
				String bmi = rs.getString("bmi");
				String bodyfat = rs.getString("BodyFat");
				String fatfreebodyweight = rs.getString("FatFreeBodyWeight");
				String subcutaneousfat = rs.getString("SubcutaneousFat");
				String viseralfat = rs.getString("VisceralFat");
				String water = rs.getString("BodyWater");
				String muscles = rs.getString("SkeletalMuscle");
				String musclemass = rs.getString("MuscleMass");
				String musclestorageabilitylevel = rs.getString("MuscleStorageAbilityLevel");
				String bones = rs.getString("BoneMass");
				String protein = rs.getString("Protein");
				String bmr = rs.getString("bmr");
				String amr = "0";
				String age = rs.getString("MetabolicAge");
				String gainlossupdated = " ";

				// ==============================
				doAdd(intid, date, weight, gainloss, bmi, bodyfat, fatfreebodyweight, subcutaneousfat, viseralfat,
						water, muscles, musclemass, musclestorageabilitylevel, bones, protein, bmr, amr, age,
						gainlossupdated);
			}
			txtNumberofRecords.setText(String.valueOf(totalcount));
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			{

			}
		}

	}

	private void doAdd(int newid, String date, String weight, String gainloss, String bmi, String bodyfat,
			String fatfreebodyweight, String subcutaneousfat, String viseralfat, String water, String muscles,
			String musclemass, String musclestorageabilitylevel, String bones, String protein, String bmr, String amr,
			String age, String gainlossupdated) {

		ResultSet rs = null;
		PreparedStatement pst = null;

		String query = "Insert into weight (id, date, weight, gainloss, bmi, bodyfat, fatfreebodyweight, subcutaneousfat, viseralfat, "
				+ "bodywater, skeletalmuscle, MUSCLEMAS, MUSSTOLEV, bonemass, protein,  bmr, amr, metabolicage, gainlossupdated) VALUES ( ";
		query += " '" + newid + "', ";
		query += " '" + date + "', ";
		query += " '" + weight + "', ";
		query += " '" + gainloss + "', ";
		query += " '" + bmi + "', ";
		query += " '" + bodyfat + "', ";
		query += " '" + fatfreebodyweight + "', ";
		query += " '" + subcutaneousfat + "', ";
		query += " '" + viseralfat + "', ";
		query += " '" + water + "', ";
		query += " '" + muscles + "', ";
		query += " '" + musclemass + "', ";
		query += " '" + musclestorageabilitylevel + "', ";
		query += " '" + bones + "', ";
		query += " '" + protein + "', ";
		query += " '" + bmr + "', ";
		query += " '" + amr + "', ";
		query += " '" + age + "',";
		query += " '" + gainlossupdated + "') ";

		try {
			rs = SMLUtility.getResultSet(query, "SQL", "UPD");

		} catch (SQLException ex) {
		} finally {

		}
	}

	private String doGetLastId() {

		ResultSet rs = null;
		String id = null;
		String query = "select max(id) id from weight";
		try {
			rs = SMLUtility.getResultSet(query, "SQL", "INQ");
			while (rs.next()) {
				id = (rs.getString("id"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
			{

			}
		}
		return id;

	}

}
