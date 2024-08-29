package stevelevy;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.UnknownHostException;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String classname = getClass().getSimpleName();
	String array[] = SMLUtility.getAdministrator(classname);
	private JTextField txtScreen;
	int screen = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					int screen = -1;
					SMLUtility.showOnScreen(screen, frame);
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
	 */
	public Menu() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 509);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel jPanelTop = new JPanel();
		jPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelTop.setBounds(255, 0, 414, 44);
		contentPane.add(jPanelTop);
		jPanelTop.setLayout(null);

		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/images/Main Main Menu.png")));
		lblNewLabel.setBounds(0, 0, 404, 52);
		jPanelTop.add(lblNewLabel);

		JPanel jPanelCenter = new JPanel();
		jPanelCenter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelCenter.setBounds(255, 55, 400, 287);
		contentPane.add(jPanelCenter);

		JButton btnBankInquiry = new JButton("Banks/Available Cash");
		btnBankInquiry.setIcon(new ImageIcon(Menu.class.getResource("/images/bank.png")));
		btnBankInquiry.setBounds(10, 79, 183, 23);
		btnBankInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doBankInquiry();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelCenter.setLayout(null);
		jPanelCenter.add(btnBankInquiry);
		String[] arr = SMLUtility.getAdministrator("BankInquiryForm");
		String auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnBankInquiry);
		}

		JButton btnPasswordInquiry = new JButton("Password Inquiry");
		btnPasswordInquiry.setIcon(new ImageIcon(Menu.class.getResource("/images/key.png")));
		btnPasswordInquiry.setBounds(10, 28, 183, 23);
		btnPasswordInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doPasswordInquiry();

				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanelCenter.add(btnPasswordInquiry);

		JButton btnWeightInquiry = new JButton("Weight Inquiry");
		btnWeightInquiry.setIcon(new ImageIcon(Menu.class.getResource("/images/scale.png")));
		btnWeightInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doWeightInquiry();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnWeightInquiry.setBounds(10, 130, 183, 23);
		jPanelCenter.add(btnWeightInquiry);
		arr = SMLUtility.getAdministrator("WeightInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnWeightInquiry);
		}

		JButton btnRlmAccounts = new JButton("RLM Account Inquiry");
		btnRlmAccounts.setIcon(new ImageIcon(Menu.class.getResource("/images/service.png")));
		btnRlmAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doRLMAccountInquiry();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRlmAccounts.setBounds(203, 28, 183, 23);
		jPanelCenter.add(btnRlmAccounts);
		arr = SMLUtility.getAdministrator("RlmAccountsInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnRlmAccounts);
		}

		JButton btnRlmSystem = new JButton("RLM System Inquiry");
		btnRlmSystem.setIcon(new ImageIcon(Menu.class.getResource("/images/settings.png")));
		btnRlmSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doRLMSystemInquiry();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRlmSystem.setBounds(203, 79, 183, 23);
		jPanelCenter.add(btnRlmSystem);
		arr = SMLUtility.getAdministrator("RlmSystemInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnRlmSystem);
		}

		JButton btnRLMProjects = new JButton("RLM Projects");
		btnRLMProjects.setIcon(new ImageIcon(Menu.class.getResource("/images/task-planning.png")));
		btnRLMProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doRLMProjects();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRLMProjects.setBounds(203, 130, 183, 23);
		jPanelCenter.add(btnRLMProjects);
		arr = SMLUtility.getAdministrator("RLMProjectsInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnRLMProjects);
		}

		JButton btnJordacheProjects = new JButton("Jordache Proects");
		btnJordacheProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doJordacheProjects();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnJordacheProjects.setIcon(new ImageIcon(Menu.class.getResource("/images/task-planning.png")));
		btnJordacheProjects.setBounds(203, 181, 183, 23);
		jPanelCenter.add(btnJordacheProjects);
		arr = SMLUtility.getAdministrator("JordacheProjectInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnJordacheProjects);
		}

		JButton btnPTO = new JButton("PTO");
		btnPTO.setIcon(new ImageIcon(Menu.class.getResource("/images/PTO.png")));
		btnPTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						doPTO();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPTO.setBounds(10, 225, 183, 23);
		jPanelCenter.add(btnPTO);

		JButton btnGym = new JButton("Gym Inquiry");
		btnGym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doGymInquiry();
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGym.setIcon(new ImageIcon(Menu.class.getResource("/images/barbell.png")));
		btnGym.setBounds(10, 181, 183, 23);
		jPanelCenter.add(btnGym);
		arr = SMLUtility.getAdministrator("JordacheProjectsInquiryForm");
		auth = arr[2];
		if (!auth.equals("true")) {
			jPanelCenter.remove(btnPTO);
		}

		JPanel jPanelButtons = new JPanel();
		jPanelButtons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelButtons.setBounds(255, 350, 340, 52);
		contentPane.add(jPanelButtons);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(Menu.class.getResource("/images/close.png")));
		jPanelButtons.add(btnClose);

		JPanel jPanelBottom = new JPanel();
		jPanelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jPanelBottom.setBounds(255, 407, 400, 52);
		contentPane.add(jPanelBottom);

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setIcon(null);
		jPanelBottom.add(lblClassName);
		String className = getClass().getSimpleName();
		lblClassName.setText(className);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 250, 459);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/images/Steve Profile.png")));
		lblNewLabel_1.setBounds(5, 87, 240, 230);
		panel.add(lblNewLabel_1);

		txtScreen = new JTextField();
		txtScreen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtScreen.selectAll();
			}
		});
		txtScreen.setText("0");
		txtScreen.setBounds(612, 369, 25, 19);
		contentPane.add(txtScreen);
		txtScreen.setColumns(10);
		setTitle("Main Menu");
		getCurrentMonitor();

		doCenterForm();

	}

	private void doBankInquiry() throws SQLException {
		String[] arr = SMLUtility.getAdministrator("BankInquiryForm_2");
		String auth = arr[2];
		if (!auth.equals("false")) {
			BankInquiryForm_2 a = new BankInquiryForm_2();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			SMLUtility.showOnScreen(screen, a);
			doCenterForm();
			a.setVisible(true);
			dispose();
		}
	}

	private void doPasswordInquiry() throws SQLException, UnknownHostException {
		String[] arr = SMLUtility.getAdministrator("PasswordInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			PasswordInquiryForm a = new PasswordInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doWeightInquiry() throws SQLException, ParseException {
		String[] arr = SMLUtility.getAdministrator("WeightInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			WeightInquiryForm a = new WeightInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doRLMAccountInquiry() throws SQLException, UnknownHostException {
		String[] arr = SMLUtility.getAdministrator("RLMAccountInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			RLMAccountInquiryForm a = new RLMAccountInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doRLMSystemInquiry() throws SQLException, UnknownHostException {
		String[] arr = SMLUtility.getAdministrator("RLMSystemInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			RLMSystemInquiryForm a = new RLMSystemInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doRLMProjects() throws SQLException, ParseException, UnknownHostException {
		String[] arr = SMLUtility.getAdministrator("RLMProjectInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			RLMProjectInquiryForm a = new RLMProjectInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doJordacheProjects() throws SQLException {
		String[] arr = SMLUtility.getAdministrator("JordacheProjectInquiryForm");
		String auth = arr[2];
		if (!auth.equals("false")) {
			JordacheProjectInquiryForm a = new JordacheProjectInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	}

	private void getCurrentMonitor() throws SQLException {
		String screen = null;
		String sql = null;
		ResultSet rs = null;
		sql = "SELECT * FROM MONITOR ";
		sql += "WHERE ID = 1 ";

//		System.out.println("SQL is : " + sql);
		rs = SMLUtility.getResultSet(sql, "SQL", "INQ");
		while (rs.next()) {
			screen = (rs.getString("current"));
		}

		txtScreen.setText(screen);

	}

	private void doPTO() throws SQLException, ParseException, UnknownHostException {
		String[] arr = SMLUtility.getAdministrator("SMLConnectonTest");
		String auth = arr[2];
		if (!auth.equals("false")) {
			PTOForm a = new PTOForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

	private void doGymInquiry() throws SQLException, ParseException {
		String[] arr = SMLUtility.getAdministrator("SMLConnectonTest");
		String auth = arr[2];
		if (!auth.equals("false")) {
			GymInquiryForm a = new GymInquiryForm();
			a.addWindowListener(new java.awt.event.WindowAdapter() {
			});
			screen = Integer.parseInt(txtScreen.getText());
			screen = -1;
			SMLUtility.showOnScreen(screen, a);
			a.setVisible(true);
			dispose();
		}
	}

}
