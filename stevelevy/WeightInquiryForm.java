package stevelevy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class WeightInquiryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jTable1;
	private JTextField txtCurrentDate;
	private JTextField txtCurrentWeight;
	private JTextField txtBalancetolosecurrent;
	private JTextField txtGoalWeight;
	private JTextField txtStartDate;
	private JTextField txtStartWeight;
	private JTextField txtGainLost;
	private JTextField txtLowestDate;
	private JTextField txtLowestWeight;
	private JTextField txtLowesGainLost;
	String lastweight = "0";
	String lastdate = "0";
	String classname = getClass().getSimpleName();
	private JTextField txtWeightLost;
	private JDateChooser jdatechosserFrom = new JDateChooser();
	private JDateChooser jdatechosserTo = new JDateChooser();
	private Double current = null;
	private Double start = null;
	private Double goal = null;
	private Double bal = null;
	private String balformat = null;
	private float lowest = 0;
	JButton btnSearch = new JButton("Search");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeightInquiryForm frame = new WeightInquiryForm();
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
	 * @throws SQLException
	 */
	public WeightInquiryForm() throws SQLException, ParseException {
		try {
			int screen = SMLUtility.getCurrentMonitorInfo(WeightInquiryForm.this);
		} catch (UnknownHostException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(0, 0, 1500, 165);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		jdatechosserFrom.setFont(new Font("Tahoma", Font.BOLD, 10));
		jdatechosserFrom.setDateFormatString("MM/dd/yy");
		jdatechosserFrom.setBounds(20, 80, 149, 20);
		jPanelTop.add(jdatechosserFrom);

		jdatechosserTo.setFont(new Font("Tahoma", Font.BOLD, 10));
		jdatechosserTo.setDateFormatString("MM/dd/yy");
		jdatechosserTo.setBounds(193, 80, 149, 20);
		jPanelTop.add(jdatechosserTo);

		btnSearch.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSearch.setIcon(new ImageIcon(WeightInquiryForm.class.getResource("/images/search.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					chkSearch();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					doPopulate();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnSearch.setBounds(20, 124, 150, 30);
		jPanelTop.add(btnSearch);

		JLabel lblNewLabel = new JLabel("From Date");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 60, 115, 14);
		jPanelTop.add(lblNewLabel);

		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblToDate.setBounds(193, 60, 115, 14);
		jPanelTop.add(lblToDate);

		JLabel lblPreiodWeightLoss = new JLabel("Period Weight Loss");
		lblPreiodWeightLoss.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPreiodWeightLoss.setBounds(365, 80, 141, 14);
		jPanelTop.add(lblPreiodWeightLoss);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(0, 165, 1500, 282);
		contentPane.add(jPanelCenter);
		jPanelCenter.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1475, 272);
		jPanelCenter.add(scrollPane);

		jTable1 = new JTable();
		jTable1.setFont(new Font("Tahoma", Font.BOLD, 12));
		// Assuming you have a JTable named "table"
		JTableHeader header = jTable1.getTableHeader();
		// Set the foreground color
		header.setForeground(Color.RED);

		// Set the background color
		header.setBackground(Color.CYAN);
		jTable1.setPreferredScrollableViewportSize(new Dimension(1100, 400));
		jTable1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Id", "Date", "Weight", "Gain/Loss", "BMI", "Body Fat", "Viseral Fat", "Water",
						"Muscles", "Bones", "Metabolic Age" }) {

			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(9).setPreferredWidth(120);
		jTable1.getColumnModel().getColumn(10).setPreferredWidth(120);
		jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		jTable1.setAutoCreateRowSorter(false);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(new CellJustifyRendererCenter());
		scrollPane.setViewportView(jTable1);

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(0, 440, 1500, 39);
		contentPane.add(jPanelButtons);

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImport.setIcon(new ImageIcon(WeightInquiryForm.class.getResource("/images/download.png")));
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnImport.setEnabled(false);
					doImport();
					doGetLastDate();
					doGetLastWeight();
					doGainLoss();
					doPopulate();
					btnImport.setEnabled(true);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPrint.setIcon(new ImageIcon(WeightInquiryForm.class.getResource("/images/printer.png")));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(jPanelCenter);
			}
		});

		JButton btnGym = new JButton("Gym Inquiry");
		btnGym.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGym.setIcon(new ImageIcon(WeightInquiryForm.class.getResource("/images/barbell.png")));
		btnGym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GymInquiryForm a = null;
				try {
					a = new GymInquiryForm();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.addWindowListener(new java.awt.event.WindowAdapter() {
				});
				int screen = -1;
				try {
					SMLUtility.showOnScreen(screen, a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a.setVisible(true);
				dispose();
			}
		});
		jPanelButtons.add(btnGym);
		jPanelButtons.add(btnPrint);
		jPanelButtons.add(btnImport);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(WeightInquiryForm.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		jPanelBottom.setBounds(0, 480, 1500, 40);
		contentPane.add(jPanelBottom);
		jPanelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("New Label");
		jPanelBottom.add(lblClassName);
		lblClassName.setPreferredSize(new Dimension(200, 14));
		lblClassName.setForeground(Color.BLUE);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);
		setTitle("Weight Inquiry");
		doCenterForm();
		getSearch();
		String fdate = null;
		String tdate = null;
		String cdate = null;

		JLabel lblTitle = new JLabel("Weight Inquiry");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setBounds(20, 10, 422, 15);
		jPanelTop.add(lblTitle);

		JLabel lblInstructions = new JLabel("Use this form to view weights");
		lblInstructions.setBounds(20, 30, 422, 13);
		jPanelTop.add(lblInstructions);

		txtWeightLost = new JTextField();
		txtWeightLost.setFont(new Font("Dialog", Font.BOLD, 12));
		txtWeightLost.setEditable(false);
		txtWeightLost.setColumns(10);
		txtWeightLost.setBounds(536, 80, 75, 20);
		jPanelTop.add(txtWeightLost);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(675, 10, 800, 128);
		jPanelTop.add(panel);
		panel.setLayout(null);

		txtCurrentDate = new JTextField();
		txtCurrentDate.setBounds(195, 11, 86, 20);
		panel.add(txtCurrentDate);
		txtCurrentDate.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCurrentDate.setEditable(false);
		txtCurrentDate.setColumns(10);

		txtCurrentWeight = new JTextField();
		txtCurrentWeight.setBounds(291, 11, 64, 20);
		panel.add(txtCurrentWeight);
		txtCurrentWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCurrentWeight.setEditable(false);
		txtCurrentWeight.setColumns(10);

		JLabel lblGoalWeight = new JLabel("Goal Weight");
		lblGoalWeight.setBounds(365, 11, 75, 14);
		panel.add(lblGoalWeight);
		lblGoalWeight.setFont(new Font("Dialog", Font.BOLD, 12));

		txtGoalWeight = new JTextField();
		txtGoalWeight.setBounds(443, 11, 64, 20);
		panel.add(txtGoalWeight);
		txtGoalWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtGoalWeight.setText("195");
		txtGoalWeight.setColumns(10);

		JLabel lblBalanceToLose = new JLabel("Balance to Lose");
		lblBalanceToLose.setBounds(517, 11, 129, 20);
		panel.add(lblBalanceToLose);
		lblBalanceToLose.setFont(new Font("Dialog", Font.BOLD, 12));

		txtBalancetolosecurrent = new JTextField();
		txtBalancetolosecurrent.setBounds(717, 11, 75, 20);
		panel.add(txtBalancetolosecurrent);
		txtBalancetolosecurrent.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBalancetolosecurrent.setEditable(false);
		txtBalancetolosecurrent.setColumns(10);

		JLabel lblStartDate = new JLabel("Start Date & Weight");
		lblStartDate.setBounds(10, 42, 161, 14);
		panel.add(lblStartDate);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 12));

		txtStartDate = new JTextField();
		txtStartDate.setBounds(195, 42, 86, 20);
		panel.add(txtStartDate);
		txtStartDate.setFont(new Font("Dialog", Font.BOLD, 12));
		txtStartDate.setEditable(false);
		txtStartDate.setColumns(10);

		txtStartWeight = new JTextField();
		txtStartWeight.setBounds(291, 42, 64, 20);
		panel.add(txtStartWeight);
		txtStartWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtStartWeight.setEditable(false);
		txtStartWeight.setColumns(10);

		JLabel lblTotalWeightGainlost = new JLabel("Total Weight Gain/Lost");
		lblTotalWeightGainlost.setBounds(517, 42, 129, 19);
		panel.add(lblTotalWeightGainlost);
		lblTotalWeightGainlost.setFont(new Font("Dialog", Font.BOLD, 12));

		txtGainLost = new JTextField();
		txtGainLost.setBounds(717, 42, 75, 20);
		panel.add(txtGainLost);
		txtGainLost.setFont(new Font("Dialog", Font.BOLD, 12));
		txtGainLost.setEditable(false);
		txtGainLost.setColumns(10);

		JLabel lblLowesttDate = new JLabel("Lowest Date & Weight");
		lblLowesttDate.setBounds(10, 86, 161, 14);
		panel.add(lblLowesttDate);
		lblLowesttDate.setFont(new Font("Dialog", Font.BOLD, 12));

		txtLowestDate = new JTextField();
		txtLowestDate.setBounds(195, 86, 86, 20);
		panel.add(txtLowestDate);
		txtLowestDate.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLowestDate.setEditable(false);
		txtLowestDate.setColumns(10);

		txtLowestWeight = new JTextField();
		txtLowestWeight.setBounds(291, 86, 64, 20);
		panel.add(txtLowestWeight);
		txtLowestWeight.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLowestWeight.setEditable(false);
		txtLowestWeight.setColumns(10);

		JLabel lblLowestWeightGainlost = new JLabel("Lowest Weight Gain/Lost");
		lblLowestWeightGainlost.setBounds(517, 86, 170, 19);
		panel.add(lblLowestWeightGainlost);
		lblLowestWeightGainlost.setFont(new Font("Dialog", Font.BOLD, 12));

		txtLowesGainLost = new JTextField();
		txtLowesGainLost.setBounds(717, 86, 75, 20);
		panel.add(txtLowesGainLost);
		txtLowesGainLost.setFont(new Font("Dialog", Font.BOLD, 12));
		txtLowesGainLost.setEditable(false);
		txtLowesGainLost.setColumns(10);

		JLabel lblCurrentDate = new JLabel("Current Date & Weight");
		lblCurrentDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCurrentDate.setBounds(10, 11, 161, 14);
		panel.add(lblCurrentDate);
		doGetLastDate();
		doGetLastWeight();
		doGainLoss();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date wdate = jdatechosserFrom.getDate();
		if (wdate != null) {
			fdate = sdf.format(jdatechosserFrom.getDate());
		}

		wdate = jdatechosserTo.getDate();
		if (wdate != null) {
			tdate = sdf.format(jdatechosserTo.getDate());
		}

		// Entry
		doPopulate();
		String weight = getlowestweight();
		getlowestweightdate(weight);
		getStartDateAndWeight();

	}

	private void doCenterForm() {
		// TODO Auto-generated method stub
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}

	private void doPopulate() throws ParseException {
		btnSearch.setEnabled(false);
		String[] dates = getSearchDates();

		String fdate = dates[0];
		String tdate = dates[1];
		String cdate = dates[2];

		Connection dbCon = null;
		ResultSet rs = null;
		String id;
		String date;
		String weight;
		String gainloss;
		String bmi;
		String bodyfat;
		String viseral;
		String bodywater;
		String skeletalmuscle;
		String bones;
		String bmr;
		String age;
		String year = null;
		String month = null;
		String day = null;
		String thedate = null;
		int intdate = 0;
		float lowestweight = 0;
		DecimalFormat decAmtFormat1 = new DecimalFormat("00");
		DecimalFormat decAmtFormat2 = new DecimalFormat("00.00");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		String currendate = null;
		String comparedate = null;
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);

		String sql = "SELECT * FROM WEIGHT ";
		sql += "WHERE 1 = 1 ";

		if (fdate != null) {
			sql += " AND DATE >=" + fdate;
		}

		if (tdate != null) {
			sql += " AND DATE <=" + tdate;
		}

		sql += " ORDER BY DATE DESC";

		try {
			try {
				rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
				TableModel model = jTable1.getModel();

				((DefaultTableModel) model).setRowCount(0);

				while (rs.next()) {
					id = (rs.getString("id"));
					date = (rs.getString("date"));
					year = date.substring(0, 4);
					month = date.substring(4, 6);
					day = date.substring(6, 8);
					thedate = month + '/' + day + '/' + year;
					weight = (rs.getString("weight"));

					if (currendate == null) {
						currendate = thedate;
						txtCurrentDate.setText(thedate);
						txtCurrentWeight.setText(weight);
						current = rs.getDouble("weight");
						goal = Double.parseDouble(txtGoalWeight.getText());
						bal = current - goal;
						balformat = decAmtFormat2.format(bal);
						txtBalancetolosecurrent.setText(balformat.toString());
					}

					intdate = Integer.valueOf(date);
					if (intdate == 20160306) {
						year = date.substring(0, 4);
						month = date.substring(4, 6);
						day = date.substring(6, 8);
						thedate = month + '/' + day + '/' + year;
						txtStartDate.setText(thedate);
						txtStartWeight.setText(weight);
						start = rs.getDouble("weight");
						goal = Double.parseDouble(txtStartWeight.getText());
						bal = current - start;
						balformat = decAmtFormat2.format(bal);
						txtGainLost.setText(balformat.toString());
						if (bal <= 0) {
							txtGainLost.setBackground(green);
						} else {
							txtGainLost.setBackground(red);
						}
					}

					lowest = rs.getFloat("weight");
					if (lowestweight == 0.0) {
						lowestweight = lowest;
					}

//					if (lowest < lowestweight) {
//						lowestweight = lowest;
//						year = date.substring(0, 4);
//						month = date.substring(4, 6);
//						day = date.substring(6, 8);
//						thedate = month + '/' + day + '/' + year;
//						txtLowestDate.setText(thedate);
//						txtLowestWeight.setText(weight);
//
//						start = Double.parseDouble(txtStartWeight.getText());
//						goal = start;
//						bal = lowest - start;
//						balformat = decAmtFormat2.format(bal);
//						txtLowesGainLost.setText(balformat.toString());
//					}

					gainloss = (rs.getString("gainloss"));
					bmi = (rs.getString("BMI"));
					bodyfat = (rs.getString("bodyfat"));
					viseral = (rs.getString("viseralfat"));
					bodywater = (rs.getString("bodywater"));
					skeletalmuscle = (rs.getString("skeletalmuscle"));
					bones = (rs.getString("bonemass"));
					bmr = (rs.getString("bmr"));
					bmr = (rs.getString("bmr"));
					age = decAmtFormat1.format(rs.getDouble("metabolicage"));

					Vector row = new Vector();

					row.add(id);
					row.add(thedate);
					row.add(weight);
					row.add(gainloss);
					row.add(bmi);
					row.add(bodyfat);
					row.add(viseral);
					row.add(bodywater);
					row.add(skeletalmuscle);
					row.add(bones);
					row.add(age);

					((DefaultTableModel) model).addRow(row);
				}
				btnSearch.setEnabled(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally

		{
		}
	}

	private void getStartDateAndWeight() throws ParseException {
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		DecimalFormat decAmtFormat1 = new DecimalFormat("00");
		DecimalFormat decAmtFormat2 = new DecimalFormat("00.00");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yyyy");
		Double fromweight = 0.00;
		String fdate = "20160306";
		Double start = null;
		Double current = null;
		Double goal = null;
		Double bal = null;
		String balformat = null;
		fromweight = (getweight(fdate, "F"));
		fdate = "03/06/2016";
		Date date = mmddyy.parse(fdate);
		txtStartDate.setText(fdate);
		txtStartWeight.setText(Double.toString(fromweight));
		goal = Double.parseDouble(txtStartWeight.getText());
		start = fromweight;
		if (this.current != null) {
			bal = this.current - start;
			balformat = decAmtFormat2.format(bal);
			txtGainLost.setText(balformat.toString());

			if (bal < 0) {
				txtGainLost.setBackground(green);
			} else {
				txtGainLost.setBackground(red);
			}
		}

		bal = this.lowest - start;
		balformat = decAmtFormat2.format(bal);
		txtLowesGainLost.setText(balformat.toString());
		if (bal < 0) {
			txtLowesGainLost.setBackground(green);
		} else {
			txtLowesGainLost.setBackground(red);
		}
	}

	private String[] getSearchDates() {
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color cyan = new Color(0, 255, 255);
		String fdate = null;
		String tdate = null;
		String cdate = null;
		String[] dates = new String[3];
		dates[0] = "0";
		dates[1] = "0";
		dates[2] = "0";
		Double fromweight = 0.00;
		Double toweight = 0.00;
		Double lossweight = 0.00;
		String date = null;
		DecimalFormat decAmtFormat2 = new DecimalFormat("00.00");
		String balformat = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date wdate = jdatechosserFrom.getDate();
		if (wdate != null) {
			fdate = sdf.format(jdatechosserFrom.getDate());
		}

		wdate = jdatechosserTo.getDate();
		if (wdate != null) {
			tdate = sdf.format(jdatechosserTo.getDate());
		}

		fromweight = (getweightdaterange(fdate, tdate, "F"));
		if (fromweight == null) {
			fromweight = Double.valueOf(0.00);
		}

		toweight = (getweightdaterange(fdate, tdate, "T"));
		if (toweight == null) {
			toweight = Double.valueOf(0.00);
		}

		lossweight = toweight - fromweight;
		balformat = decAmtFormat2.format(lossweight);
		txtWeightLost.setText(balformat.toString());
		if (lossweight <= 0) {
			txtWeightLost.setBackground(green);
		} else {
			txtWeightLost.setBackground(red);
		}
		dates[0] = fdate;
		dates[1] = tdate;
		dates[2] = cdate;
		return dates;
	}

	private Double getweight(String date, String type) {
		Double weight = null;
		Connection dbCon = null;

		try {
			String sql = "SELECT WEIGHT FROM weight WHERE gainlossupdated = 'Y' ";

			if (date != null && type == "F") {
				sql += " AND DATE = " + date + " ";
			}

			if (date != null && type == "T") {
				sql += " AND DATE = " + date + " ";
			}

			sql += "ORDER BY DATE DESC ";
			sql += "LIMIT 1 ";

			ResultSet rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				weight = Double.valueOf(rs.getString("weight"));

			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}

		return weight;
	}

	private Double getweightdaterange(String fdate, String tdate, String type) {
		Double weight = null;
		Connection dbCon = null;

		try {
			String sql = "SELECT DATE, WEIGHT FROM weight WHERE gainlossupdated = 'Y' ";

			if (fdate != null && type == "F") {
				sql += " AND DATE BETWEEN " + fdate + " and " + tdate + " ";
				sql += "ORDER BY DATE ";
			}

			if (tdate != null && type == "T") {
				sql += " AND DATE BETWEEN " + fdate + " and " + tdate + " ";
				sql += "ORDER BY DATE DESC ";
			}
			ResultSet rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				weight = Double.valueOf(rs.getString("weight"));

				return weight;
			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}

		return weight;
	}

	private String getlowestweight() {
		String weight = null;
		Connection dbCon = null;

		try {
			String sql = "SELECT MIN(WEIGHT)AS WEIGHT FROM weight WHERE gainlossupdated = 'Y' ";

			ResultSet rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				weight = (rs.getString("weight"));
				this.lowest = Float.parseFloat(weight);
				txtLowestWeight.setText(rs.getString("weight"));
			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
		return weight;
	}

	private void getlowestweightdate(String weight) {
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		Connection dbCon = null;
		String sql = null;
		String dateReceivedFromUser = null;

		try {
			sql = "SELECT MAX(DATE)AS DATE FROM WEIGHT WHERE GAINLOSSUPDATED = 'Y' ";
			sql += "AND WEIGHT =" + weight;

			ResultSet rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				dateReceivedFromUser = (rs.getString("date"));
				Date date = yyyymmdd.parse(dateReceivedFromUser);
				String convertedDate = mmddyy.format(date);

				txtLowestDate.setText(convertedDate);

			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
		return;

	}

	private void doGainLoss() {
		Connection dbCon = null;
		ResultSet rs = null;
		String first = null;
		String weight = null;
		Double currentweight = 0.00;
		Double previousweight = Double.valueOf(this.lastweight);
		Double gainlossweight = 0.0;
		Double Height = 69.0;
		Double BMI = 0.0;
		String currentdate = null;
		String previousdate = null;
		String date = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			String sql = "SELECT ID, DATE, ";
			sql += "EXTRACT(MONTH FROM DATE) AS MONTH,";
			sql += "EXTRACT(DAY FROM DATE) AS DAY, ";
			sql += "EXTRACT(YEAR FROM DATE) AS YEAR, ";
			sql += "WEIGHT, bodyfat, bodywater, skeletalmuscle, bonemass, BMR, AMR, GAINLOSS ";
			sql += "FROM Weight ";
			sql += "WHERE 1 = 1 AND GAINLOSS = 0";
			sql += (" ORDER BY DATE");
//			System.out.println("doGainLoss SQL is  : " + sql);
			PreparedStatement pst = null;

			DecimalFormat df = new DecimalFormat("#####.##");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			model.setRowCount(0);

			while (rs.next()) {
				String gainlossid = (rs.getString("ID"));
				weight = (rs.getString("Weight"));
				date = (rs.getString("DATE"));
				previousdate = currentdate;
				currentweight = Double.valueOf(weight);
				currentdate = date;
				gainlossweight = currentweight - previousweight;
				previousweight = currentweight;
				weight = (String.format("%.2f", currentweight - previousweight));
				BMI = currentweight / (Height * Height) * 703; // BMI = [Weight (lbs) / Height (inches)²] x 703
				String bmi = (String.format("%.2f", BMI));

				doUpdate(gainlossid, gainlossweight, bmi);

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
	}

	private void doGetLastWeight() {
		try {
			Connection dbCon = null;

			String sql = "SELECT weight FROM weight ";
			sql += " WHERE date = '" + this.lastdate + "'";

			PreparedStatement pst = null;
			ResultSet rs = null;

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				this.lastweight = (rs.getString("weight"));

			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	private void doGetLastDate() {
		Connection dbCon = null;

		try {
			String sql = "SELECT MAX(date) AS date FROM weight WHERE gainlossupdated = 'Y' ";

			ResultSet rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			if (rs.next()) {

				this.lastdate = (rs.getString("date"));

			} else {

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	private void doUpdate(String gainlossid, double weight, String bmi) {
		Connection dbCon = null;
		ResultSet rs = null;
		String sql = null;
		String gainlossupdated = "Y";

		try {
			// SELECT * FROM [AvailableCash]
			sql = "UPDATE Weight " + "SET " + "GainLoss = '" + (String.format("%.2f", weight)) + "', "
					+ "gainlossupdated = '" + gainlossupdated + "', " + "BMI = '" + bmi + "' " + " WHERE ID = "
					+ (gainlossid);

			PreparedStatement pst = null;

			ResultSet RS = SMLUtility.getResultSet(sql, "SQL", "UPD");

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private void doMenu() throws SQLException {
		// TODO Auto-generated method stub
		Menu a = new Menu();
		a.addWindowListener(new java.awt.event.WindowAdapter() {
		});
		int screen = -1;
		SMLUtility.showOnScreen(screen, a);
		a.setVisible(true);
		dispose();
	}

	private void doImport() throws ParseException {
//		MegafitImportForm a = new MegafitImportForm();
//		a.addWindowListener(new java.awt.event.WindowAdapter() {
//		});
//		a.setVisible(true);
//		dispose();
		getCSV();
	}

	private void printRecord(JPanel panel) {
		// Create PrinterJob Here
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		// Set Printer Job Name
		printerJob.setJobName("Print Record");
		// Set Printable
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				// Check If No Printable Content
				if (pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;
				}

				// Make 2D Graphics to map content
				Graphics2D graphics2D = (Graphics2D) graphics;
				// Set Graphics Translations
				// A Little Correction here Multiplication was not working so I replaced with
				// addition
				graphics2D.translate(pageFormat.getImageableX() + 10, pageFormat.getImageableY() + 10);
				// This is a page scale. Default should be 0.3 I am using 0.5
				graphics2D.scale(0.5, 0.5);

				// Now paint panel as graphics2D
				panel.paint(graphics2D);

				// return if page exists
				return Printable.PAGE_EXISTS;
			}
		});
		// Store printerDialog as boolean
		boolean returningResult = printerJob.printDialog();
		// check if dilog is showing
		if (returningResult) {
			// Use try catch exeption for failure
			try {
				// Now call print method inside printerJob to print
				printerJob.print();
			} catch (PrinterException printerException) {
				JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
			}
		}
	}

	/**
	 * This method is used to import data from a CSV file. The CSV file is selected
	 * by the user through a file chooser dialog. The data from the CSV file is read
	 * line by line, and each line is split into an array of strings. The first line
	 * is assumed to be the header and is skipped. For each subsequent line, the
	 * data is trimmed and passed to the doAdd method. If the file import is
	 * successful, a dialog box is displayed to inform the user that the import is
	 * complete.
	 * 
	 * @throws ParseException if there is an error parsing the date
	 */
	private void getCSV() throws ParseException {
		String csvFile = null;
		String line = "";
		String cvsSplitBy = ",";
		boolean header = true;
		// Create a SimpleDateFormat with the date format from the database
		SimpleDateFormat sdfParse = new SimpleDateFormat("MMMM dd, yyyy");
		// Create a SimpleDateFormat with the desired date format
		SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyyMMdd");
		// Create a file chooser
		JFileChooser fileChooser = new JFileChooser();
		// Set the default directory for the file chooser
		fileChooser.setCurrentDirectory(new File("S:\\Users\\slevy\\OneDrive\\Desktop"));
		// Show the file chooser dialog and get the user's response
		int returnValue = fileChooser.showOpenDialog(null);
		// If the user approves the file selection, get the absolute path of the
		// selected file
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			csvFile = fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			// If the user cancels the file selection, display a message and return
			JOptionPane.showMessageDialog(null, "Import Canceled");
			return;
		}

		// Try to read the file
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// Read each line of the file
			while ((line = br.readLine()) != null) {
				// Split the line into an array of strings
				String[] data = line.split(cvsSplitBy);
				// Skip the header line
				if (!header) {
					// Trim the data and pass it to the doAdd method
					String date = data[0].trim();
					date = date.substring(0, 12); // "May 31, 2024"
					// Trim the data and pass it to the doAdd method
					date = data[0].trim().replace("，", ",");
					Date dateParse = sdfParse.parse(date);
					date = sdfFormat.format(dateParse);
					String weight = data[1].trim();
					String gainloss = "0";
					String bmi = data[2].trim();
					String bodyfat = data[3].trim();
					String fatfreebodyweight = data[4].trim();
					String subcutaneousfat = data[5].trim();
					String viseralfat = data[6].trim();
					String water = data[7].trim();
					String muscles = data[8].trim();
					String musclemass = data[9].trim();
					String musclestorageabilitylevel = data[10].trim();
					String bones = data[11].trim();
					String protein = data[12].trim();
					String bmr = data[13].trim();
					String amr = "0";
					String age = data[14].trim();
					String gainlossupdated = " ";

					doAdd(date, weight, gainloss, bmi, bodyfat, fatfreebodyweight, subcutaneousfat, viseralfat, water,
							muscles, musclemass, musclestorageabilitylevel, bones, protein, bmr, amr, age,
							gainlossupdated);

				}
				header = false;

			}
			// Display a message to inform the user that the import is complete
			JOptionPane.showMessageDialog(null, "Import Complete");
		} catch (IOException e) {
			// Print the stack trace if there is an IOException
			e.printStackTrace();
		}
	}

	private void doAdd(String date, String weight, String gainloss, String bmi, String bodyfat,
			String fatfreebodyweight, String subcutaneousfat, String viseralfat, String water, String muscles,
			String musclemass, String musclestorageabilitylevel, String bones, String protein, String bmr, String amr,
			String age, String gainlossupdated) {
		ResultSet rs = null;
		PreparedStatement pst = null;

		String query = "Insert into weight (date, weight, gainloss, bmi, bodyfat, fatfreebodyweight, subcutaneousfat, viseralfat, "
				+ "bodywater, skeletalmuscle, MUSCLEMAS, MUSSTOLEV, bonemass, protein,  bmr, amr, metabolicage, gainlossupdated) VALUES ( ";

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

	private void chkSearch() throws SQLException, ParseException {
		String field = null;
		String value = null;
		String type = "CHK";
		String date;
		String strdate = "01 Jan 1960";
		java.util.Date searchdate;
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date wdate;

		wdate = jdatechosserFrom.getDate();
		if (wdate == null) {
			String dateintial = "01/01/60";
			java.util.Date date2 = new SimpleDateFormat("MM/dd/yy").parse(dateintial);
			jdatechosserFrom.setDate(date2);
		}
		field = "jdatechosserFrom";
		date = yyyymmdd.format(jdatechosserFrom.getDate());
		value = "" + date;
		SMLUtility.getValue(classname, field, value, type);

		wdate = jdatechosserTo.getDate();
		if (wdate == null) {
			Date today = new Date();
			jdatechosserTo.setDate(today);
		}
		field = "jdatechosserTo";
		date = yyyymmdd.format(jdatechosserTo.getDate());
		value = "" + date;
		SMLUtility.getValue(classname, field, value, type);
	}

	private void getSearch() throws SQLException, ParseException {
		String field = null;
		String value = "";
		String type = "GET";
		String date;
		java.util.Date wdate;
		java.util.Date searchdate;
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		DateValidator validator = new DateValidatorUsingDateFormat("yyy-MM-dd");
		boolean ok = false;
		field = "jdatechosserFrom";
		value = SMLUtility.getValue(classname, field, value, type);
		ok = (validator.isValid(value));
		if (ok) {
			searchdate = yyyymmdd.parse(value);
			jdatechosserFrom.setDate(searchdate);
		}

		field = "jdatechosserTo";
		value = SMLUtility.getValue(classname, field, value, type);
		ok = (validator.isValid(value));
		if (ok) {
			searchdate = yyyymmdd.parse(value);
			jdatechosserTo.setDate(searchdate);
		}

	}
}
