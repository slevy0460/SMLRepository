package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;

public class GymInquiryForm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JDateChooser dateChooser = new JDateChooser();
	SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
	SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
	JRadioButton rdbtnImperial = new JRadioButton("Imperial");
	JRadioButton rdbtnMetric = new JRadioButton("Metric");
	String field = null;
	String value = null;
	boolean isBoolean = false;
	String className = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GymInquiryForm frame = new GymInquiryForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public GymInquiryForm() throws ParseException {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(GymInquiryForm.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doCheck("GET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Dialog", Font.BOLD, 12));
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JPanel panelCenter = new JPanel();
		panelCenter.setBounds(0, 0, 1500, 50);
//		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1500, 50);
		panelCenter.add(panel_2);
		panel_2.setLayout(null);
		rdbtnImperial.setFont(new Font("Dialog", Font.BOLD, 12));

		rdbtnImperial.setBounds(5, 15, 101, 21);
		panel_2.add(rdbtnImperial);
		rdbtnMetric.setFont(new Font("Dialog", Font.BOLD, 12));

		rdbtnMetric.setBounds(139, 15, 101, 21);
		panel_2.add(rdbtnMetric);

		ButtonGroup bgroupsort = new ButtonGroup();
		bgroupsort.add(rdbtnImperial);
		bgroupsort.add(rdbtnMetric);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 50, 1525, 44);
		panelCenter.add(panel_3);
		panel_3.setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCheck("CHK");
				try {
					doPopulate();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/search.png")));
		btnSearch.setBounds(10, 10, 150, 25);
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_3.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 100, 1475, 168);
		panelCenter.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						int row = table.getSelectedRow();
						String id = table.getModel().getValueAt(row, 0).toString();
						String mode = "UPDATE";
						doEdit(mode, id);

					} catch (SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null }, },
				new String[] { "Id", "Date", "Height", "Weight", "BMI", "Body Fat", "Muscle", "BMR", "Metabolic Age",
						"Viseral Fat", "Chest", "Back", "Right Arm", "Left Arm", "Abdomen", "Gluteus", "Right Leg",
						"Left Leg", "Right Calf", "Left Calf" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(0, 266, 1500, 50);
		panelCenter.add(panel_4);
		panel_4.setLayout(null);
		dateChooser.setBounds(150, 10, 150, 25);

		dateChooser.setDateFormatString("MM/dd/yy");
		panel_4.add(dateChooser);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(20, 10, 100, 25);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean dateok = checkDate();
				if (dateok) {
					try {
						doAdd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnAdd.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/plus.png")));
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_4.add(btnAdd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 310, 1500, 40);
		panelCenter.add(panel_1);
		
		JLabel lblClassName = new JLabel("New label");
		lblClassName.setPreferredSize(new Dimension(200, 14));
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		panel_1.add(lblClassName);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelSouth.add(panel, BorderLayout.CENTER);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if (row >= 0) {
					String id = table.getModel().getValueAt(row, column).toString();
					String mode = "UPDATE";
					try {
						doEdit(mode, id);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to edit");
				}
			}
		});

		JButton btnWeightInquiry = new JButton("Weight Inquiry");
		btnWeightInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doWeight();
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWeightInquiry.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/scale.png")));
		panel.add(btnWeightInquiry);
		btnEdit.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/edit.png")));
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row to delete");
				} else {
					String id = table.getModel().getValueAt(row, column).toString();
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Verify Exit ",
							JOptionPane.YES_NO_OPTION);
					if (n == 0) {
						deleteIt(row, id);
						try {
							doPopulate();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/bin.png")));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(btnDelete);
		btnClose.setIcon(new ImageIcon(GymInquiryForm.class.getResource("/images/close.png")));
		panel.add(btnClose);

		setTitle("Gym Inquiry Form");
		lblTitle.setText("Gym Inquiry Form");
		lblInstructions.setText("Use this form to view Gym data");
		String className = getClass().getSimpleName();
		doCenterForm();
		doPopulate();

	}

	private void doCenterForm() {
		// TODO Auto-generated method stub
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doMenu() throws SQLException {
		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doWeight() throws SQLException, ParseException {
		WeightInquiryForm a = new WeightInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private boolean checkDate() {
		String date = null;
		java.util.Date wdate = dateChooser.getDate();
		if (wdate == null) {
			JOptionPane.showMessageDialog(null, "Invalid Date");
			return false;
		}
		return true;
	}

	private void doAdd() throws SQLException {
		String mode = "ADD";
		try {
			doEdit(mode, null);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void doEdit(String mode, String id) throws ParseException, SQLException {
		String date = null;
		java.util.Date wdate = dateChooser.getDate();
		if (wdate != null) {
			date = yyyymmdd.format(dateChooser.getDate());
		}
		GymEditForm a = new GymEditForm(mode, date, id);
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}
	
	private void deleteIt(int row, String id) {

		ResultSet rs = null;
		String sql = "Delete From gym where id = ";
		sql += " '" + id + "'  ";

		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}

	}
	
	private void doPopulate() throws ParseException {
		Connection dbCon = null;
		ResultSet rs = null;
		String sql = null;
		String id;
		String date = null;
		java.util.Date datefield = null;
		String height = null;
		String weight = null;
		String bmi = null;
		String bodyfat = null;
		String muscle = null;
		String bmr = null;
		String metabolicage = null;
		String viseralfat = null;
		String chest = null;
		String back = null;
		String rightarm = null;
		String leftarm = null;
		String abdomen = null;
		String gluteus = null;
		String rightleg = null;
		String leftleg = null;
		String rightcalf = null;
		String leftcalf = null;
		Double meticToFeet = 3.2808399;
		Double KilogramstoPounds = 2.20462262;
		Double CentimetersToInches = 0.39370079;
		double value = 0.00;
		DecimalFormat decAmt$Format = new DecimalFormat("0.00");

		sql = "SELECT ";
		sql += "ID, DATE, ";
		sql += "HEIGHT, ";
		sql += "WEIGHT, ";
		sql += "BMI, BODYFAT, SKELETALMUSCLE, BMR, METABOLICAGE, VISERALFAT, ";
		sql += "CHEST, ";
		sql += "BACK, ";
		sql += "RIGHTARM, ";
		sql += "LEFTARM, ";
		sql += "ABDOMEN, ";
		sql += "GLUTEUS, ";
		sql += "RIGHTLEG, ";
		sql += "LEFTLEG, ";
		sql += "RIGHTCALF, ";
		sql += "LEFTCALF ";
		sql += "FROM GYM ";

//		System.out.println("SQL is : " + sql);

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
				TableModel model = table.getModel();

				((DefaultTableModel) model).setRowCount(0);

				while (rs.next()) {
					id = (rs.getString("ID"));
					date = (rs.getString("DATE"));
					datefield = yyyymmdd.parse(date);
					date = mmddyy.format(datefield);

					height = (rs.getString("HEIGHT"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(height);
						value = value * meticToFeet;
						height = decAmt$Format.format(value);
					}

					weight = (rs.getString("WEIGHT"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(weight);
						value = value * KilogramstoPounds;
						weight = decAmt$Format.format(value);
					}

					bmi = (rs.getString("BMI"));
					bodyfat = (rs.getString("BODYFAT"));
					muscle = (rs.getString("SKELETALMUSCLE"));
					bmr = (rs.getString("BMR"));
					metabolicage = (rs.getString("METABOLICAGE"));
					viseralfat = (rs.getString("VISERALFAT"));

					chest = (rs.getString("CHEST"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(chest);
						value = value * CentimetersToInches;
						chest = decAmt$Format.format(value);
					}
					back = (rs.getString("BACK"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(back);
						value = value * CentimetersToInches;
						back = decAmt$Format.format(value);
					}

					rightarm = (rs.getString("RIGHTARM"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(rightarm);
						value = value * CentimetersToInches;
						rightarm = decAmt$Format.format(value);
					}
					leftarm = (rs.getString("LEFTARM"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(leftarm);
						value = value * CentimetersToInches;
						leftarm = decAmt$Format.format(value);
					}
					abdomen = (rs.getString("ABDOMEN"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(abdomen);
						value = value * CentimetersToInches;
						abdomen = decAmt$Format.format(value);
					}
					gluteus = (rs.getString("GLUTEUS"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(gluteus);
						value = value * CentimetersToInches;
						gluteus = decAmt$Format.format(value);
					}
					rightleg = (rs.getString("RIGHTLEG"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(rightleg);
						value = value * CentimetersToInches;
						rightleg = decAmt$Format.format(value);
					}
					leftleg = (rs.getString("LEFTLEG"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(leftleg);
						value = value * CentimetersToInches;
						leftleg = decAmt$Format.format(value);
					}
					rightcalf = (rs.getString("RIGHTCALF"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(rightcalf);
						value = value * CentimetersToInches;
						rightcalf = decAmt$Format.format(value);
					}
					leftcalf = (rs.getString("LEFTCALF"));
					if (rdbtnImperial.isSelected()) {
						value = Double.parseDouble(leftcalf);
						value = value * CentimetersToInches;
						leftcalf = decAmt$Format.format(value);
					}

					Vector row = new Vector();

					row.add(id);
					row.add(date);
					row.add(height);
					row.add(weight);
					row.add(bmi);
					row.add(bodyfat);
					row.add(muscle);
					row.add(bmr);
					row.add(metabolicage);
					row.add(viseralfat);
					row.add(chest);
					row.add(back);
					row.add(rightarm);
					row.add(leftarm);
					row.add(abdomen);
					row.add(gluteus);
					row.add(rightleg);
					row.add(leftleg);
					row.add(rightcalf);
					row.add(leftcalf);

					((DefaultTableModel) model).addRow(row);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally

		{
		}
	}

	private void doCheck(String type) {
		field = "rdbtnImperial";
		isBoolean = rdbtnImperial.isSelected();
		value = String.valueOf(isBoolean);
		try {
			value = SMLUtility.getValue(className, field, value, type);
			if (type.equals("GET")) {
				Boolean isBoolean = Boolean.valueOf(value);
				if (isBoolean) {
					rdbtnImperial.setSelected(true);
					rdbtnMetric.setSelected(false);
				} else {
					rdbtnImperial.setSelected(false);
					rdbtnMetric.setSelected(true);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
