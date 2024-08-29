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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class PTOForm_2 extends JFrame {

	private JPanel contentPane;
	private JTable tablePTOdays;
	String classname = getClass().getSimpleName();
	private JTextField txtTotalHoliday;
	private JTextField txtNumberofVacation;
	private JTextField txtTotalPTO;
	private JTextField txtPTOBalance;
	private JTextField txtNumberofHoliday;
	private JTextField txtHolidayBalance;
	private JComboBox cmdCountry = new JComboBox();
	private JComboBox cmdType = new JComboBox();
	private JComboBox cmdTypeNew = new JComboBox();
	private JTextField txtNumberofVacationtScheduled;
	private JTextField txtNumberofHolidayScheduled;
	private JDateChooser jdatechosserDate = new JDateChooser();
	private JDateChooser jdatechosserFrom = new JDateChooser();
	private JDateChooser jdatechosserTo = new JDateChooser();
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PTOForm_2 frame = new PTOForm_2();
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
	 * @throws SQLException
	 * @throws ParseException
	 */
	public PTOForm_2() throws SQLException, ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 1500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		panelNorth.setBounds(0, 0, 1500, 40);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(new Color(0, 0, 204));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNorth.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 10));
		panelNorth.add(lblInstructions, BorderLayout.SOUTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setBounds(0, 40, 1500, 410);
		
		JPanel tabPTODays = new JPanel();
		tabbedPane.addTab("PTO Days", null, tabPTODays, null);
		tabPTODays.setLayout(null);

		JPanel panelSearchPTOdays = new JPanel();
		panelSearchPTOdays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSearchPTOdays.setBounds(0, 0, 1500, 134);
		tabPTODays.add(panelSearchPTOdays);
		panelSearchPTOdays.setLayout(null);

		JLabel lblCountrySearch = new JLabel("Country");
		lblCountrySearch.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCountrySearch.setBounds(10, 10, 66, 17);
		panelSearchPTOdays.add(lblCountrySearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chkSearch();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doPopulatePTOdays();
			}
		});
		btnSearch.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/search.png")));
		btnSearch.setBounds(10, 64, 200, 30);
		panelSearchPTOdays.add(btnSearch);

		JPanel panelHolidays = new JPanel();
		panelHolidays.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHolidays.setBounds(0, 110, 1500, 300);
		tabPTODays.add(panelHolidays);
		panelHolidays.setLayout(null);

		JScrollPane scrollPanePTOdays = new JScrollPane();
		scrollPanePTOdays.setBounds(0, 0, 1500, 263);
		panelHolidays.add(scrollPanePTOdays);

		tablePTOdays = new JTable();
		tablePTOdays.setFont(new Font("Tahoma", Font.BOLD, 12));
		JTableHeader header = this.tablePTOdays.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 14));
		header.setForeground(Color.RED);
		header.setBackground(Color.CYAN);
		// Make table alternate colors
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(192, 192, 192));

		tablePTOdays.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Id", "Country", "Description (double click to edit)", "Type", "Date",
						"Day of Week (double click to edit)", "Took", "Took PTO", "Moved (double click to edit)",
						"Notes (double click to edit)" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Boolean.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, true, true, true, false, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePTOdays.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablePTOdays.getColumnModel().getColumn(1).setPreferredWidth(40);
		tablePTOdays.getColumnModel().getColumn(2).setPreferredWidth(175);
		tablePTOdays.getColumnModel().getColumn(3).setPreferredWidth(30);
		tablePTOdays.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablePTOdays.getColumnModel().getColumn(5).setPreferredWidth(175);
		tablePTOdays.getColumnModel().getColumn(6).setPreferredWidth(20);
		tablePTOdays.getColumnModel().getColumn(8).setPreferredWidth(150);
		tablePTOdays.getColumnModel().getColumn(9).setPreferredWidth(200);

		scrollPanePTOdays.setViewportView(tablePTOdays);

//		JPanel panelSouth = new JPanel();
//		panelSouth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		contentPane.add(panelSouth, BorderLayout.SOUTH);
//		panelSouth.setLayout(new BorderLayout(0, 0));
//		panelSouth.setBounds(0, 640, 1500, 60);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(0, 440, 1500, 40);
		contentPane.add(panelButtons, BorderLayout.CENTER);
		

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doDelete();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doSavePTOdays();
					doPopulatePTOdays();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/updated.png")));
		btnSave.setFont(new Font("Dialog", Font.BOLD, 12));
		panelButtons.add(btnSave);
		btnDelete.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/bin.png")));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		panelButtons.add(btnDelete);

		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printRecord(panelHolidays);
			}
		});
		btnPrint.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/printer.png")));
		panelButtons.add(btnPrint);

		JButton btnExcel = new JButton("Excel");
		btnExcel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExcel();
			}
		});
		btnExcel.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/excel.png")));
		panelButtons.add(btnExcel);

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Dialog", Font.BOLD, 12));
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doImport();
					doPopulatePTOdays();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnImport.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/download.png")));
		panelButtons.add(btnImport);
		btnClose.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/close.png")));
		panelButtons.add(btnClose);

//		JPanel panelFormName = new JPanel();
//		panelSouth.add(panelFormName, BorderLayout.EAST);

		setTitle("Paid Time Off");
		lblTitle.setText("Paid Time Off");
		lblInstructions.setText("Use this form to manage PTO");
		String className = getClass().getSimpleName();
		this.classname = className;
		cmdType.setFont(new Font("Dialog", Font.BOLD, 12));

		cmdType.setModel(new DefaultComboBoxModel(new String[] { "All", "Holiday", "Vacation" }));
		cmdType.setSelectedIndex(0);
		cmdType.setBounds(177, 30, 133, 20);
		panelSearchPTOdays.add(cmdType);
		cmdCountry.setFont(new Font("Dialog", Font.BOLD, 12));

		cmdCountry.setModel(new DefaultComboBoxModel(new String[] { "All", "United States", "Costa Rica" }));
		cmdCountry.setSelectedIndex(0);
		cmdCountry.setBounds(10, 30, 133, 20);
		panelSearchPTOdays.add(cmdCountry);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(823, 10, 650, 90);
		panelSearchPTOdays.add(panel);
		panel.setLayout(null);

		txtHolidayBalance = new JTextField();
		txtHolidayBalance.setBounds(381, 27, 86, 20);
		panel.add(txtHolidayBalance);
		txtHolidayBalance.setText((String) null);
		txtHolidayBalance.setFont(new Font("Dialog", Font.BOLD, 12));
		txtHolidayBalance.setEditable(false);
		txtHolidayBalance.setColumns(10);

		txtNumberofHoliday = new JTextField();
		txtNumberofHoliday.setBounds(251, 27, 86, 20);
		panel.add(txtNumberofHoliday);
		txtNumberofHoliday.setText((String) null);
		txtNumberofHoliday.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNumberofHoliday.setEditable(false);
		txtNumberofHoliday.setColumns(10);

		txtTotalHoliday = new JTextField();
		txtTotalHoliday.setBounds(108, 27, 86, 20);
		panel.add(txtTotalHoliday);
		txtTotalHoliday.setEditable(false);
		txtTotalHoliday.setFont(new Font("Dialog", Font.BOLD, 12));
		txtTotalHoliday.setColumns(10);

		txtPTOBalance = new JTextField();
		txtPTOBalance.setBounds(381, 57, 86, 20);
		panel.add(txtPTOBalance);
		txtPTOBalance.setText((String) null);
		txtPTOBalance.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPTOBalance.setEditable(false);
		txtPTOBalance.setColumns(10);

		txtNumberofVacation = new JTextField();
		txtNumberofVacation.setBounds(251, 57, 86, 20);
		panel.add(txtNumberofVacation);
		txtNumberofVacation.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNumberofVacation.setEditable(false);
		txtNumberofVacation.setColumns(10);

		txtTotalPTO = new JTextField();
		txtTotalPTO.setBounds(108, 60, 86, 20);
		panel.add(txtTotalPTO);
		txtTotalPTO.setEditable(false);
		txtTotalPTO.setText("18");
		txtTotalPTO.setFont(new Font("Dialog", Font.BOLD, 12));
		txtTotalPTO.setColumns(10);

		JLabel lblNewLabel = new JLabel("Holidays");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 27, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblVacation = new JLabel("Vacation");
		lblVacation.setFont(new Font("Dialog", Font.BOLD, 12));
		lblVacation.setBounds(22, 60, 86, 14);
		panel.add(lblVacation);

		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(108, 10, 100, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Taken");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(251, 10, 99, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Balance");
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(381, 10, 99, 14);
		panel.add(lblNewLabel_1_1_1);

		txtNumberofVacationtScheduled = new JTextField();
		txtNumberofVacationtScheduled.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNumberofVacationtScheduled.setEditable(false);
		txtNumberofVacationtScheduled.setBounds(522, 57, 85, 20);
		panel.add(txtNumberofVacationtScheduled);
		txtNumberofVacationtScheduled.setColumns(10);

		JLabel lblNewLabel_1_1_2 = new JLabel("Scheduled");
		lblNewLabel_1_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1_2.setBounds(522, 10, 99, 14);
		panel.add(lblNewLabel_1_1_2);

		txtNumberofHolidayScheduled = new JTextField();
		txtNumberofHolidayScheduled.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNumberofHolidayScheduled.setText((String) null);
		txtNumberofHolidayScheduled.setEditable(false);
		txtNumberofHolidayScheduled.setColumns(10);
		txtNumberofHolidayScheduled.setBounds(522, 27, 85, 20);
		panel.add(txtNumberofHolidayScheduled);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dialog", Font.BOLD, 12));
		lblType.setBounds(177, 10, 66, 17);
		panelSearchPTOdays.add(lblType);

		JLabel lblNewLabel_2 = new JLabel("From Date");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(338, 10, 115, 14);
		panelSearchPTOdays.add(lblNewLabel_2);

		jdatechosserFrom.setFont(new Font("Tahoma", Font.BOLD, 10));
		jdatechosserFrom.setDateFormatString("MM/dd/yy");
		jdatechosserFrom.setBounds(338, 30, 149, 20);
		panelSearchPTOdays.add(jdatechosserFrom);

		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblToDate.setBounds(532, 10, 115, 14);
		panelSearchPTOdays.add(lblToDate);

		jdatechosserTo.setFont(new Font("Tahoma", Font.BOLD, 10));
		jdatechosserTo.setDateFormatString("MM/dd/yy");
		jdatechosserTo.setBounds(528, 30, 149, 20);
		panelSearchPTOdays.add(jdatechosserTo);

		JPanel panelEntry = new JPanel();
		panelEntry.setBounds(0, 594, 1235, 57);
		tabPTODays.add(panelEntry);
		panelEntry.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelEntry.setLayout(null);

		jdatechosserDate.setDateFormatString("MM/dd/yy");
		jdatechosserDate.setBounds(110, 20, 98, 20);
		panelEntry.add(jdatechosserDate);

		JLabel lblPTODate = new JLabel("PTO Date");
		lblPTODate.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPTODate.setBounds(110, 5, 98, 14);
		panelEntry.add(lblPTODate);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
				String date = yyyymmdd.format(jdatechosserDate.getDate());
				try {
					doAdd(date);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

				doPopulatePTOdays();
			}

		});
		btnAdd.setIcon(new ImageIcon(PTOForm_2.class.getResource("/images/plus.png")));
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.setBounds(10, 17, 89, 23);
		panelEntry.add(btnAdd);

		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblType_1.setBounds(220, 0, 66, 17);
		panelEntry.add(lblType_1);
		cmdTypeNew.setFont(new Font("Dialog", Font.BOLD, 12));

		cmdTypeNew.setModel(new DefaultComboBoxModel(new String[] { "", "Holiday", "Vacation" }));
		cmdTypeNew.setSelectedIndex(0);
		cmdTypeNew.setBounds(220, 20, 133, 20);
		panelEntry.add(cmdTypeNew);

		JLabel lblType_1_1 = new JLabel("Type");
		lblType_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblType_1_1.setBounds(375, 5, 60, 17);
		panelEntry.add(lblType_1_1);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDescription.setBounds(375, 20, 250, 20);
		panelEntry.add(txtDescription);
		txtDescription.setColumns(10);
		
		JPanel panelbottom = new JPanel();
		panelbottom.setBounds(0, 480, 1500, 30);
		contentPane.add(panelbottom);
		
		JLabel lblClassName = new JLabel("ClassName");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClassName.setPreferredSize(new Dimension(200, 14));
		lblClassName.setText(className);
		lblClassName.setForeground(Color.BLUE);
		panelbottom.add(lblClassName);

		doCenterForm();
		doSetup();
		getSearch();
		doPopulatePTOdays();

	}

	private void doSetup() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_YEAR, 1);
		jdatechosserFrom.setDate(calendar.getTime());

		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		jdatechosserTo.setDate(calendar.getTime());

	}

	private void doCenterForm() {
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

	private void doSavePTOdays() throws ParseException {
		DefaultTableModel model = (DefaultTableModel) tablePTOdays.getModel();
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0; i < nRow; i++) {

			int descidColNo = SMLUtility.getColumnIndex(tablePTOdays, "Description (double click to edit)");

			int typeColNo = SMLUtility.getColumnIndex(tablePTOdays, "Type");

			int dateColNo = SMLUtility.getColumnIndex(tablePTOdays, "Date");

			int dayColNo = SMLUtility.getColumnIndex(tablePTOdays, "Day of Week (double click to edit)");

			int tookColNo = SMLUtility.getColumnIndex(tablePTOdays, "Took");

			int isTakenColNo = SMLUtility.getColumnIndex(tablePTOdays, "Took PTO");

			int movedColNo = SMLUtility.getColumnIndex(tablePTOdays, "Moved (double click to edit)");

			int notesColNo = SMLUtility.getColumnIndex(tablePTOdays, "Notes (double click to edit)");

			tableData[i][descidColNo] = model.getValueAt(i, descidColNo);
			String desc = String.valueOf(tableData[i][descidColNo]);

			tableData[i][typeColNo] = model.getValueAt(i, typeColNo);
			String type = String.valueOf(tableData[i][typeColNo]);

			tableData[i][dateColNo] = model.getValueAt(i, dateColNo);
			String date = String.valueOf(tableData[i][dateColNo]);

			tableData[i][dayColNo] = model.getValueAt(i, dayColNo);
			String day = String.valueOf(tableData[i][dayColNo]);

			tableData[i][tookColNo] = model.getValueAt(i, tookColNo);
			String took = String.valueOf(tableData[i][tookColNo]);

			tableData[i][movedColNo] = model.getValueAt(i, isTakenColNo);
			boolean isTaken = (boolean) tablePTOdays.getValueAt(i, isTakenColNo);

			tableData[i][movedColNo] = model.getValueAt(i, movedColNo);
			String moved = String.valueOf(tableData[i][movedColNo]);

			tableData[i][notesColNo] = model.getValueAt(i, notesColNo);
			String notes = String.valueOf(tableData[i][notesColNo]);

			doUpdatePTOdays(desc, type, date, day, took, isTaken, moved, notes);
		}
	}

	private void doUpdatePTOdays(String desc, String type, String stringdate, String day, String took, Boolean isTaken,
			String moved, String notes) throws ParseException {
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		date = mmddyy.parse(stringdate);
		stringdate = yyyymmdd.format(date);

		if (isTaken) {
			took = "Yes";
		} else {
			if (type.equals("Holiday")) {
				took = "";
			} else {
				took = "Scheduled";
			}
		}

		ResultSet rs = null;
		String sql = null;

		sql = "UPDATE PTODAYS ";
		sql += "SET  ";
		sql += "HOLIDAY = '" + desc + "', ";
		sql += "DAY = '" + day + "', ";
		sql += "TOOK = '" + took + "', ";
		sql += "MOVED = '" + moved + "', ";
		sql += "NOTES = '" + notes + "' ";
		sql += "WHERE DATE = '" + stringdate + "' ";

		int index = 0;
		if (cmdCountry.getSelectedIndex() != 0) {
			index = cmdCountry.getSelectedIndex();

			if (index == 1) {
				sql += " AND (TRIM(COUNTRY))  = 'United States'";
			}

			if (index == 2) {
				sql += " AND (TRIM(COUNTRY))  = 'Costa Rica'";
			}
		}

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doAdd(String date) throws ParseException {
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		ResultSet rs = null;
		java.util.Date datetype;
		datetype = yyyymmdd.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetype);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String dayOfWeekString = daysOfWeek[dayOfWeek - 1];
		String country = "Costa Rica";
		String holiday = "Vacation Day";
		String took = "";
		String moved = "";
		String notes = "";
		String type = "V";
		int index = 0;

//		if (cmdType.getSelectedIndex() != 0) {
		index = cmdTypeNew.getSelectedIndex();

		if (index == 1) {
			type = "H";
		}

		if (index == 2) {
			type = "V";
		}
//		}

		if (type.equals("V")) {
			took = "Scheduled";
		}

		holiday = txtDescription.getText();
		if (holiday.equals("")) {
			if (type.equals("H")) {
				holiday = "Holiday";
			} else {
				holiday = "Vacation";
			}
		}

		String sql = "Insert into PTODAYS ";
		sql += "(COUNTRY, HOLIDAY, DATE, DAY, TOOK, MOVED, NOTES, TYPE) ";
		sql += "VALUES ( ";
		sql += " '" + country + "', ";
		sql += " '" + holiday + "', ";
		sql += " '" + date + "',  ";
		sql += " '" + dayOfWeekString + "', ";
		sql += " '" + took + "',  ";
		sql += " '" + moved + "',  ";
		sql += " '" + notes + "',  ";
		sql += " '" + type + "') ";
//		System.out.println("SQL is : " + sql);
		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "INS");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}
	}

	private void doDelete() throws ParseException {
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
		String stringdate = null;
		String id;
		java.util.Date date;

		int column = SMLUtility.getColumnIndex(tablePTOdays, "Id");

		int row = tablePTOdays.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Please select a row to delete");
		} else {
			id = tablePTOdays.getModel().getValueAt(row, column).toString();
//			date = mmddyy.parse(stringdate);
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Verify Exit ",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
//				stringdate = yyyymmdd.format(date);
				deleteIt(row, id);
				doPopulatePTOdays();
			}
		}
	}

	private void deleteIt(int row, String id) {

		ResultSet rs = null;
		String sql = "DELETE FROM PTODAYS WHERE ID = ";
		sql += " '" + id + "'  ";

		try {

			rs = SMLUtility.getResultSet(sql, "SQL", "DLT");
		} catch (SQLException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
		}

	}

	private void doGetTotals() {
		String PTOType = null;
		String count;
		boolean holidaytaken = false;
		boolean scheduled = false;
		int iholidaytotal = 0;
		int iholidaytaken = 0;
		int iholidaybalance = 0;

		int iptototal = 0;
		int iptotaken = 0;
		int iptobalance = 0;

		PTOType = "H";
		holidaytaken = false;
		scheduled = false;
		count = doPopulateTotals(PTOType, holidaytaken, scheduled);
		txtTotalHoliday.setText(count);

		holidaytaken = true;
		scheduled = false;
		count = doPopulateTotals(PTOType, holidaytaken, scheduled);
		txtNumberofHoliday.setText(count);

		iholidaytotal = Integer.valueOf(txtTotalHoliday.getText());
		iholidaytaken = Integer.valueOf(txtNumberofHoliday.getText());
		iholidaybalance = iholidaytotal - iholidaytaken;
		txtHolidayBalance.setText(Integer.toString(iholidaybalance));

		holidaytaken = true;
		scheduled = true;
		count = doPopulateTotals(PTOType, holidaytaken, scheduled);
		txtNumberofHolidayScheduled.setText(count);

		PTOType = "V";
		holidaytaken = true;
		scheduled = false;
		count = doPopulateTotals(PTOType, holidaytaken, scheduled);
		txtNumberofVacation.setText(count);

		iptototal = Integer.valueOf(txtTotalPTO.getText());
		iptotaken = Integer.valueOf(txtNumberofVacation.getText());
		iptobalance = iptototal - iptotaken;
		txtPTOBalance.setText(Integer.toString(iptobalance));

		PTOType = "V";
		holidaytaken = true;
		scheduled = true;
		count = doPopulateTotals(PTOType, holidaytaken, scheduled);
		txtNumberofVacationtScheduled.setText(count);

	}

	private void doPopulatePTOdays() {
		doGetTotals();
		String sql = null;
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		String id;
		String country;
		String holiday;
		String typedesc;
		String date;
		String day;
		String took;
		Boolean newtook;
		String moved;
		String notes;
		String fdate = null;
		String tdate = null;
		java.util.Date searchdate;
		int index;

		sql = "SELECT ";
		sql += "PTODAYS.*, ";
		sql += "(CASE WHEN TYPE = 'H' THEN 'Holiday' WHEN TYPE = 'V' THEN 'Vacation' ELSE ' ' END) AS TYPEDESCRIPTION ";
		sql += "FROM PTODAYS ";
		sql += "WHERE 1 = 1 ";

		if (cmdCountry.getSelectedIndex() != 0) {
			index = cmdCountry.getSelectedIndex();

			if (index == 1) {
				sql += " AND (TRIM(COUNTRY))  = 'United States'";
			}

			if (index == 2) {
				sql += " AND (TRIM(COUNTRY))  = 'Costa Rica'";
			}
		}

		if (cmdType.getSelectedIndex() != 0) {
			index = cmdType.getSelectedIndex();

			if (index == 1) {
				sql += " AND (TRIM(TYPE))  = 'H'";
			}

			if (index == 2) {
				sql += " AND (TRIM(TYPE))  = 'V'";
			}
		}

		searchdate = jdatechosserFrom.getDate();
		if (searchdate != null) {
			fdate = yyyymmdd.format(jdatechosserFrom.getDate());
			if (fdate != null) {
				fdate = yyyy_mm_dd.format(jdatechosserFrom.getDate());
				sql += " AND DATE BETWEEN '" + fdate + "' ";
			}
		}

		searchdate = jdatechosserTo.getDate();
		if (searchdate != null) {
			tdate = yyyymmdd.format(jdatechosserTo.getDate());
			if (tdate != null) {
				tdate = yyyy_mm_dd.format(jdatechosserTo.getDate());
				sql += " AND DATE '" + tdate + "' ";
			}
		}

		sql += "ORDER BY COUNTRY, TYPE, DATE ";
//		System.out.println("SQL is : " + sql);
		try {
			TableModel model = tablePTOdays.getModel();

			((DefaultTableModel) model).setRowCount(0);
			ResultSet rs = null;
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				id = (rs.getString("id"));
				country = (rs.getString("country"));
				holiday = (rs.getString("holiday"));
				typedesc = (rs.getString("TYPEDESCRIPTION"));
				date = mmddyy.format(rs.getDate("date"));
				day = (rs.getString("day"));
				took = (rs.getString("took"));
				if (took.equals("Yes")) {
					newtook = true;
				} else {
					newtook = false;
				}

				moved = (rs.getString("moved"));
				notes = (rs.getString("notes"));

				Vector row = new Vector();

				row.add(id);
				row.add(country);
				row.add(holiday);
				row.add(typedesc);
				row.add(date);
				row.add(day);
				row.add(took);
				row.add(newtook);
				row.add(moved);
				row.add(notes);

				((DefaultTableModel) model).addRow(row);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String doPopulateTotals(String ptotype, boolean holidaytaken, boolean scheduled) {
		String sql = null;
		String count = null;
		int index;
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		String fdate = null;
		String tdate = null;
		java.util.Date searchdate;

		sql = "SELECT ";
		sql += "COUNT(*) AS COUNT ";
		sql += "FROM PTODAYS ";
		sql += "WHERE 1 = 1 ";

		sql += " AND (DAY)  NOT IN ('Saturday', 'Sunday') ";
		sql += " AND (TYPE)  = '" + ptotype + "' ";

		if (cmdCountry.getSelectedIndex() != 0) {
			index = cmdCountry.getSelectedIndex();

			if (index == 1) {
				sql += " AND (TRIM(COUNTRY))  = 'United States'";
			}

			if (index == 2) {
				sql += " AND (TRIM(COUNTRY))  = 'Costa Rica'";
			}
		}

		if (holidaytaken && !scheduled) {
			String upper = "Yes";
			sql += " AND (TOOK)  like '%" + upper + "%'";
		}

		if (scheduled) {
			String upper = "Scheduled";
			sql += " AND (TOOK)  like '%" + upper + "%'";
		}

		searchdate = jdatechosserFrom.getDate();
		if (searchdate != null) {
			fdate = yyyymmdd.format(jdatechosserFrom.getDate());
			if (fdate != null) {
				fdate = yyyy_mm_dd.format(jdatechosserFrom.getDate());
				sql += " AND DATE BETWEEN '" + fdate + "' ";
			}
		}

		searchdate = jdatechosserTo.getDate();
		if (searchdate != null) {
			tdate = yyyymmdd.format(jdatechosserTo.getDate());
			if (tdate != null) {
				tdate = yyyy_mm_dd.format(jdatechosserTo.getDate());
				sql += " AND DATE '" + tdate + "' ";
			}
		}

		try {
			TableModel model = tablePTOdays.getModel();

			((DefaultTableModel) model).setRowCount(0);
			ResultSet rs = null;
			rs = SMLUtility.getResultSet(sql, "SQL", "INQ");

			while (rs.next()) {
				;
				count = (rs.getString("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	private void chkSearch() throws SQLException {
		String field = null;
		String value = null;
		String type = "CHK";
		String date;
		java.util.Date searchdate;
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");

		field = "cmdCountry";
		value = "" + cmdCountry.getSelectedIndex();
		SMLUtility.getValue(classname, field, value, type);

		field = "cmdType";
		value = "" + cmdType.getSelectedIndex();
		SMLUtility.getValue(classname, field, value, type);

		field = "jdatechosserFrom";
		date = yyyymmdd.format(jdatechosserFrom.getDate());
		value = "" + date;
		SMLUtility.getValue(classname, field, value, type);

		field = "jdatechosserTo";
		date = yyyymmdd.format(jdatechosserTo.getDate());
		value = "" + date;
		SMLUtility.getValue(classname, field, value, type);
	}

	private void getSearch() throws SQLException, ParseException {
		String field = null;
		String value = null;
		String type = "GET";
		String date;
		java.util.Date searchdate;
		SimpleDateFormat mmddyy = new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");

		field = "cmdCountry";
		value = "" + cmdCountry.getSelectedIndex();
		value = SMLUtility.getValue(classname, field, value, type);
		cmdCountry.setSelectedIndex(Integer.valueOf(value));
		DateValidator validator = new DateValidatorUsingDateFormat("yyy-MM-dd");
		boolean ok = false;

		field = "cmdType";
		value = SMLUtility.getValue(classname, field, value, type);
		cmdType.setSelectedIndex(Integer.valueOf(value));

		field = "jdatechosserFrom";
		value = SMLUtility.getValue(classname, field, value, type);
		ok = (validator.isValid(value));
		if (ok) {
			searchdate = yyyymmdd.parse(value);
			value = SMLUtility.getValue(classname, field, value, type);
			searchdate = yyyymmdd.parse(value);
		}

		field = "jdatechosserTo";
		value = SMLUtility.getValue(classname, field, value, type);
		ok = (validator.isValid(value));
		if (ok) {
			searchdate = yyyymmdd.parse(value);
			jdatechosserTo.setDate(searchdate);
		}

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

	private void doExcel() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row;
		Cell cell;
		TableModel model = tablePTOdays.getModel();
		// write the column headers
		row = sheet.createRow(0);
		for (int c = 0; c < model.getColumnCount(); c++) {
			cell = row.createCell(c);
			cell.setCellValue(model.getColumnName(c));
		}
		// write the data rows
		for (int r = 0; r < model.getRowCount(); r++) {
			row = sheet.createRow(r + 1);
			for (int c = 0; c < model.getColumnCount(); c++) {
				cell = row.createCell(c);
				Object value = model.getValueAt(r, c);
				if (value instanceof String) {
					cell.setCellValue((String) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				}
			}
		}

		String filePath = "S:\\Users\\slevy\\OneDrive\\Desktop/PTOSummary.xlsx";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doImport() throws ParseException {
		getCSV();
	}

	private void getCSV() throws ParseException {
		String csvFile = null;
		String line = "";
		String cvsSplitBy = ",";
		boolean header = true;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("S:\\Users\\slevy\\OneDrive\\Desktop"));
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			csvFile = fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			JOptionPane.showMessageDialog(null, "Import Canceled");
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);

				if (!header) {
					String country = data[0].trim();
					String holiday = data[1].trim();
					String date = data[2].trim();
					String day = data[3].trim();
//					String took = data[4].trim();
//					String moved = data[5].trim();
//					String notes = data[6].trim();
//					String type = data[7].trim();
					String took = "";
					String moved = "";
					String notes = "";
					String type = "H";
					doAddimport(country, holiday, date, day, took, moved, notes, type);

				}
				header = false;

			}
			JOptionPane.showMessageDialog(null, "Import Complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doAddimport(String country, String holiday, String date, String day, String took, String moved,
			String notes, String type) {
		ResultSet rs = null;
		PreparedStatement pst = null;

		String sql = "Insert into PTODAYS ";
		sql += "(COUNTRY, HOLIDAY, DATE, DAY, TOOK, MOVED, NOTES, TYPE) ";
		sql += "VALUES ( ";
		sql += " '" + country + "', ";
		sql += " '" + holiday + "', ";
		sql += " '" + date + "',  ";
		sql += " '" + day + "', ";
		sql += " '" + took + "',  ";
		sql += " '" + moved + "',  ";
		sql += " '" + notes + "',  ";
		sql += " '" + type + "') ";

		try {
			rs = SMLUtility.getResultSet(sql, "SQL", "UPD");

		} catch (SQLException ex) {
		} finally {

		}

	}
}
