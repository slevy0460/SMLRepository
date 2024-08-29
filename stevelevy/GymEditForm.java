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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.UnknownHostException;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class GymEditForm extends JFrame {

	private JPanel contentPane;
	private String mode;
	private String id;
	private String date;
	private JTextField txtId;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private JTextField txtChest;
	private JTextField txtGluteus;
	JDateChooser dateChooser = new JDateChooser();
	SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
	SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtMetabolicAge;
	private JTextField txtViseralFat;
	private JTextField txtBmi;
	private JTextField txtBodyFat;
	private JTextField txtMuscle;
	private JTextField txtBmr;
	private JTextField txtBack;
	private JTextField txtRightLeg;
	private JTextField txtAbdomen;
	private JTextField txtRightArm;
	private JTextField txtLeftArm;
	private JTextField txtRightCalf;
	private JTextField txtLeftCalf;
	private JTextField txtLeftLeg;
	JButton btnUpdate = new JButton("Update");
	String className = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GymEditForm frame = new GymEditForm(null, null, null);
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
	 * @param mode
	 * @param id
	 * @throws ParseException
	 */
	public GymEditForm(String mode, String date, String id) throws ParseException {
		this.mode = mode;
		this.id = id;
		this.date = date;
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(GymEditForm.this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1050, 700);

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
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1025, 100);
		panelCenter.add(panel_2);
		panel_2.setLayout(null);

		txtId = new JTextField();
		txtId.setBounds(100, 10, 116, 22);
		txtId.setFont(new Font("Dialog", Font.BOLD, 12));
		txtId.setEditable(false);
		txtId.setColumns(10);
		panel_2.add(txtId);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 10, 55, 22);
		panel_2.add(lblId);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 100, 1025, 475);
		panelCenter.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 10, 100, 16);
		lblDate.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_3.add(lblDate);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHeight.setBounds(10, 53, 126, 14);
		panel_3.add(lblHeight);

		txtHeight = new JTextField();
		txtHeight.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtHeight.selectAll();
			}
		});
		txtHeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtHeight.setColumns(10);
		txtHeight.setBounds(160, 53, 150, 20);
		panel_3.add(txtHeight);
		dateChooser.setDateFormatString("MM/dd/yy");

		dateChooser.setBounds(160, 10, 150, 19);
		panel_3.add(dateChooser);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		lblWeight.setBounds(10, 103, 126, 14);
		panel_3.add(lblWeight);

		txtWeight = new JTextField();
		txtWeight.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtWeight.selectAll();
			}
		});
		txtWeight.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				txtWeight.selectAll();
			}
		});
		txtWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtWeight.setColumns(10);
		txtWeight.setBounds(160, 103, 150, 20);
		panel_3.add(txtWeight);

		JLabel lblChest = new JLabel("Chest");
		lblChest.setFont(new Font("Dialog", Font.BOLD, 12));
		lblChest.setBounds(395, 10, 126, 14);
		panel_3.add(lblChest);

		txtChest = new JTextField();
		txtChest.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtChest.selectAll();
			}
		});
		txtChest.setFont(new Font("Dialog", Font.BOLD, 12));
		txtChest.setColumns(10);
		txtChest.setBounds(545, 10, 150, 20);
		panel_3.add(txtChest);

		JLabel lblGuteus = new JLabel("Gluteus");
		lblGuteus.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGuteus.setBounds(717, 10, 126, 14);
		panel_3.add(lblGuteus);

		txtGluteus = new JTextField();
		txtGluteus.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtGluteus.selectAll();
			}
		});
		txtGluteus.setFont(new Font("Dialog", Font.BOLD, 12));
		txtGluteus.setColumns(10);
		txtGluteus.setBounds(867, 10, 150, 20);
		panel_3.add(txtGluteus);

		JLabel LblMetabolicAge = new JLabel("Metabolic Age");
		LblMetabolicAge.setFont(new Font("Dialog", Font.BOLD, 12));
		LblMetabolicAge.setBounds(10, 339, 126, 14);
		panel_3.add(LblMetabolicAge);

		txtMetabolicAge = new JTextField();
		txtMetabolicAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMetabolicAge.selectAll();
			}
		});
		txtMetabolicAge.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMetabolicAge.setColumns(10);
		txtMetabolicAge.setBounds(160, 339, 150, 20);
		panel_3.add(txtMetabolicAge);

		JLabel lblViseralFat = new JLabel("Viseral Fat");
		lblViseralFat.setFont(new Font("Dialog", Font.BOLD, 12));
		lblViseralFat.setBounds(10, 390, 126, 14);
		panel_3.add(lblViseralFat);

		txtViseralFat = new JTextField();
		txtViseralFat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtViseralFat.selectAll();
			}
		});
		txtViseralFat.setFont(new Font("Dialog", Font.BOLD, 12));
		txtViseralFat.setColumns(10);
		txtViseralFat.setBounds(160, 390, 150, 20);
		panel_3.add(txtViseralFat);

		txtBmi = new JTextField();
		txtBmi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBmi.selectAll();
			}
		});
		txtBmi.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBmi.setColumns(10);
		txtBmi.setBounds(160, 151, 150, 20);
		panel_3.add(txtBmi);

		JLabel lblBmi = new JLabel("BMI");
		lblBmi.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBmi.setBounds(10, 151, 126, 14);
		panel_3.add(lblBmi);

		txtBodyFat = new JTextField();
		txtBodyFat.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBodyFat.selectAll();
			}
		});
		txtBodyFat.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBodyFat.setColumns(10);
		txtBodyFat.setBounds(160, 202, 150, 20);
		panel_3.add(txtBodyFat);

		JLabel lblBodyFat = new JLabel("BodyFat");
		lblBodyFat.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBodyFat.setBounds(10, 202, 126, 14);
		panel_3.add(lblBodyFat);

		JLabel lblMuscle = new JLabel("Muscle");
		lblMuscle.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMuscle.setBounds(10, 247, 126, 14);
		panel_3.add(lblMuscle);

		txtMuscle = new JTextField();
		txtMuscle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMuscle.selectAll();
			}
		});
		txtMuscle.setFont(new Font("Dialog", Font.BOLD, 12));
		txtMuscle.setColumns(10);
		txtMuscle.setBounds(160, 247, 150, 20);
		panel_3.add(txtMuscle);

		JLabel lblBmr = new JLabel("BMR");
		lblBmr.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBmr.setBounds(10, 287, 126, 14);
		panel_3.add(lblBmr);

		txtBmr = new JTextField();
		txtBmr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBmr.selectAll();
			}
		});
		txtBmr.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBmr.setColumns(10);
		txtBmr.setBounds(160, 287, 150, 20);
		panel_3.add(txtBmr);

		JLabel lblBack = new JLabel("Back");
		lblBack.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBack.setBounds(395, 53, 126, 14);
		panel_3.add(lblBack);

		txtBack = new JTextField();
		txtBack.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBack.selectAll();
			}
		});
		txtBack.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBack.setColumns(10);
		txtBack.setBounds(545, 53, 150, 20);
		panel_3.add(txtBack);

		JLabel lblRightLeg = new JLabel("Right Leg");
		lblRightLeg.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRightLeg.setBounds(717, 53, 126, 14);
		panel_3.add(lblRightLeg);

		txtRightLeg = new JTextField();
		txtRightLeg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRightLeg.selectAll();
			}
		});
		txtRightLeg.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRightLeg.setColumns(10);
		txtRightLeg.setBounds(867, 53, 150, 20);
		panel_3.add(txtRightLeg);

		JLabel lblRightArm = new JLabel("Right Arm");
		lblRightArm.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRightArm.setBounds(395, 103, 126, 14);
		panel_3.add(lblRightArm);

		JLabel lblLeftLeg = new JLabel("Left Leg");
		lblLeftLeg.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLeftLeg.setBounds(717, 103, 126, 14);
		panel_3.add(lblLeftLeg);

		JLabel lblLeftARm = new JLabel("Left Arm");
		lblLeftARm.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLeftARm.setBounds(395, 151, 126, 14);
		panel_3.add(lblLeftARm);

		JLabel lblRightCalf = new JLabel("Right Calf");
		lblRightCalf.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRightCalf.setBounds(717, 151, 126, 14);
		panel_3.add(lblRightCalf);

		JLabel lblAbdomen = new JLabel("Abdomen");
		lblAbdomen.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAbdomen.setBounds(395, 202, 126, 14);
		panel_3.add(lblAbdomen);

		txtAbdomen = new JTextField();
		txtAbdomen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAbdomen.selectAll();
			}
		});
		txtAbdomen.setFont(new Font("Dialog", Font.BOLD, 12));
		txtAbdomen.setColumns(10);
		txtAbdomen.setBounds(545, 202, 150, 20);
		panel_3.add(txtAbdomen);

		JLabel lblLeftCalf = new JLabel("Left Calf");
		lblLeftCalf.setFont(new Font("Dialog", Font.BOLD, 12));
		lblLeftCalf.setBounds(717, 202, 126, 14);
		panel_3.add(lblLeftCalf);

		txtRightArm = new JTextField();
		txtRightArm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRightArm.selectAll();
			}
		});
		txtRightArm.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRightArm.setColumns(10);
		txtRightArm.setBounds(545, 103, 150, 20);
		panel_3.add(txtRightArm);

		txtLeftArm = new JTextField();
		txtLeftArm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLeftArm.selectAll();
			}
		});
		txtLeftArm.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLeftArm.setColumns(10);
		txtLeftArm.setBounds(545, 153, 150, 20);
		panel_3.add(txtLeftArm);

		txtRightCalf = new JTextField();
		txtRightCalf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRightCalf.selectAll();
			}
		});
		txtRightCalf.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRightCalf.setColumns(10);
		txtRightCalf.setBounds(867, 153, 150, 20);
		panel_3.add(txtRightCalf);

		txtLeftCalf = new JTextField();
		txtLeftCalf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLeftCalf.selectAll();
			}
		});
		txtLeftCalf.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLeftCalf.setColumns(10);
		txtLeftCalf.setBounds(867, 204, 150, 20);
		panel_3.add(txtLeftCalf);

		txtLeftLeg = new JTextField();
		txtLeftLeg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLeftLeg.selectAll();
			}
		});
		txtLeftLeg.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLeftLeg.setColumns(10);
		txtLeftLeg.setBounds(867, 102, 150, 20);
		panel_3.add(txtLeftLeg);
		panel_3.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{dateChooser, txtHeight, txtWeight, txtBmi, txtBodyFat, txtMuscle, txtBmr, txtMetabolicAge, txtViseralFat, txtChest, txtBack, txtRightArm, txtLeftArm, txtAbdomen, txtGluteus, txtRightLeg, txtLeftLeg, txtRightCalf, txtLeftCalf}));

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

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate(txtId.getText());
			}
		});

		JButton btnGym = new JButton("Gym Inquiry");
		btnGym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doGymInquiry();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGym.setIcon(new ImageIcon(GymEditForm.class.getResource("/images/barbell.png")));
		panel.add(btnGym);
		btnUpdate.setIcon(new ImageIcon(GymEditForm.class.getResource("/images/updated.png")));
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(btnUpdate);
		btnClose.setIcon(new ImageIcon(GymEditForm.class.getResource("/images/close.png")));
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panelSouth.add(panel_1, BorderLayout.EAST);

		JLabel lblClassName = new JLabel("SMLDefaultJInternalFrame");
		lblClassName.setText(className);
		panel_1.add(lblClassName);

		setTitle("Gym Edit Form");
		lblTitle.setText("Gym Edit Form");
		lblInstructions.setText("View and Edit Gym Data");
		String className = getClass().getSimpleName();
		lblClassName.setForeground(Color.BLUE);
		lblClassName.setText(className);
		doCenterForm();

		if (this.mode.equals("ADD")) {
			doAdd(date);
			String newid = getLastId();
			txtId.setText(newid);
			doPopulate(txtId.getText());
		} else {
			this.doPopulate(txtId.getText());

		}

	}

	private String getLastId() {
		ResultSet rs = null;
		String sql = null;
		sql = "SELECT MAX(ID) AS ID FROM GYM ";

//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {
				id = (rs.getString("ID"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return id;

	}

	private void doPopulate(String idpas) throws ParseException {

		ResultSet rs = null;
		String sql = null;
		String id = idpas;
		String date = "0";
		String height = "0";
		String weight = "0";
		String bmi = "0";
		String bodyfat = "0";
		String muscle = "0";
		String bmr = "0";
		String metabolicage = "0";
		String viseralfat = "0";
		String chest = "0";
		String back = "0";
		String rightarm = "0";
		String leftarm = "0";
		String abdomen = "0";
		String gluteus = "0";
		String rightleg = "0";
		String leftleg = "0";
		String rightcalf = "0";
		String leftcalf = "0";
		java.util.Date datefield;
		if (this.mode.equals("ADD")) {
			this.id = id;
		}

		sql = "SELECT * FROM GYM ";
		sql += "WHERE ID = '" + this.id + "'";
//		System.out.println("SQL is : " + sql);
		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			while (rs.next()) {

				txtId.setText(this.id);
				date = (rs.getString("DATE"));
				datefield = yyyymmdd.parse(date);
				date = mmddyy.format(datefield);
				datefield = mmddyy.parse(date);
				dateChooser.setDate(datefield);

				date = mmddyy.format(datefield);
				height = (rs.getString("HEIGHT"));
				weight = (rs.getString("WEIGHT"));
				bmi = (rs.getString("BMI"));
				bodyfat = (rs.getString("BODYFAT"));
				muscle = (rs.getString("SKELETALMUSCLE"));
				bmr = (rs.getString("BMR"));
				metabolicage = (rs.getString("METABOLICAGE"));
				viseralfat = (rs.getString("VISERALFAT"));
				chest = (rs.getString("CHEST"));
				back = (rs.getString("BACK"));
				rightarm = (rs.getString("RIGHTARM"));
				leftarm = (rs.getString("LEFTARM"));
				abdomen = (rs.getString("ABDOMEN"));
				gluteus = (rs.getString("GLUTEUS"));
				rightleg = (rs.getString("RIGHTLEG"));
				leftleg = (rs.getString("LEFTLEG"));
				rightcalf = (rs.getString("RIGHTCALF"));
				leftcalf = (rs.getString("LEFTCALF"));

				txtHeight.setText(height);
				txtWeight.setText(weight);
				txtBmi.setText(bmi);
				txtBodyFat.setText(bodyfat);
				txtMuscle.setText(muscle);
				txtBmr.setText(bmr);
				txtMetabolicAge.setText(metabolicage);
				txtViseralFat.setText(viseralfat);
				txtChest.setText(chest);
				txtBack.setText(back);
				txtRightArm.setText(rightarm);
				txtLeftArm.setText(leftarm);
				txtAbdomen.setText(abdomen);
				txtGluteus.setText(gluteus);
				txtRightLeg.setText(rightleg);
				txtLeftLeg.setText(leftleg);
				txtRightCalf.setText(rightcalf);
				txtLeftCalf.setText(leftcalf);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void doUpdate(String id) {
		ResultSet rs = null;
		String sql = null;
		String newid = "0";
		String date = null;
		java.util.Date wdate = dateChooser.getDate();
		if (wdate != null) {
			date = yyyymmdd.format(dateChooser.getDate());
		}

		String height = txtHeight.getText();
		String weight = txtWeight.getText();
		String bmi = txtBmi.getText();
		String bodyfat = txtBodyFat.getText();
		String muscle = txtMuscle.getText();
		String bmr = txtBmr.getText();
		String metabolicage = txtMetabolicAge.getText();
		String viseralfat = txtViseralFat.getText();
		String chest = txtChest.getText();
		String back = txtBack.getText();
		String rightarm = txtRightArm.getText();
		String leftarm = txtLeftArm.getText();
		String abdomen = txtAbdomen.getText();
		String gluteus = txtGluteus.getText();
		String rightleg = txtRightLeg.getText();
		String leftleg = txtLeftLeg.getText();
		String rightcalf = txtRightCalf.getText();
		String leftcalf = txtLeftCalf.getText();

		sql = "UPDATE GYM SET ";
		sql += "DATE = '" + date + "', ";
		sql += "HEIGHT = '" + height + "', ";
		sql += "WEIGHT = '" + weight + "', ";
		sql += "BMI = '" + bmi + "', ";
		sql += "BODYFAT = '" + bodyfat + "', ";
		sql += "SKELETALMUSCLE = '" + muscle + "', ";
		sql += "BMR = '" + bmr + "', ";
		sql += "METABOLICAGE =  '" + metabolicage + "', ";
		sql += "VISERALFAT = '" + viseralfat + "', ";
		sql += "CHEST = '" + chest + "', ";
		sql += "BACK =  '" + back + "', ";
		sql += "RIGHTARM = '" + rightarm + "', ";
		sql += "LEFTARM =  '" + leftarm + "', ";
		sql += "ABDOMEN =  '" + abdomen + "', ";
		sql += "GLUTEUS = '" + gluteus + "', ";
		sql += "RIGHTLEG = '" + rightleg + "', ";
		sql += "LEFTLEG = '" + leftleg + "', ";
		sql += "RIGHTCALF = '" + rightcalf + "', ";
		sql += "LEFTCALF = '" + leftcalf + "' ";
		sql += " WHERE ID = " + this.id;

//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doAdd(String date) {
		ResultSet rs = null;
		String sql = null;
		String newid = "0";
//		String date = "0";
		String height = "0";
		String weight = "0";
		String bmi = "0";
		String bodyfat = "0";
		String muscle = "0";
		String bmr = "0";
		String metabolicage = "0";
		String viseralfat = "0";
		String chest = "0";
		String back = "0";
		String rightarm = "0";
		String leftarm = "0";
		String abdomen = "0";
		String gluteus = "0";
		String rightleg = "0";
		String leftleg = "0";
		String rightcalf = "0";
		String leftcalf = "0";

		sql = "INSERT INTO GYM ( ";
		sql += "ID, ";
		sql += "DATE, ";
		sql += "HEIGHT, ";
		sql += "WEIGHT, ";
		sql += "BMI, ";
		sql += "BODYFAT, ";
		sql += "SKELETALMUSCLE, ";
		sql += "BMR, ";
		sql += "METABOLICAGE, ";
		sql += "VISERALFAT, ";
		sql += "CHEST, ";
		sql += "BACK, ";
		sql += "RIGHTARM, ";
		sql += "LEFTARM, ";
		sql += "ABDOMEN, ";
		sql += "GLUTEUS, ";
		sql += "RIGHTLEG, ";
		sql += "LEFTLEG, ";
		sql += "RIGHTCALF, ";
		sql += "LEFTCALF) ";
		sql += "VALUES (";
		sql += " '" + newid + "', ";
		sql += " '" + date + "', ";
		sql += " '" + height + "', ";
		sql += "'" + weight + "', ";
		sql += "'" + bmi + "', ";
		sql += "'" + bodyfat + "', ";
		sql += "'" + muscle + "', ";
		sql += "'" + bmr + "', ";
		sql += "'" + metabolicage + "', ";
		sql += "'" + viseralfat + "', ";
		sql += "'" + chest + "', ";
		sql += "'" + back + "', ";
		sql += "'" + rightarm + "', ";
		sql += "'" + leftarm + "', ";
		sql += "'" + abdomen + "', ";
		sql += "'" + gluteus + "', ";
		sql += "'" + rightleg + "', ";
		sql += "'" + leftleg + "', ";
		sql += "'" + rightcalf + "', ";
		sql += "'" + leftcalf + "' ";
		sql += ")";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doCenterForm() {
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

	private void doGymInquiry() throws ParseException, SQLException {
		GymInquiryForm a = new GymInquiryForm();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}
}
