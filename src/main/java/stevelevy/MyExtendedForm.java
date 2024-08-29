package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class MyExtendedForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private String title;

			public void run() {
				try {
					String instructions = null;;
					String id = null;;
					MyExtendedForm frame = new MyExtendedForm(title, instructions, id);
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
	 * @param classname
	 * 
	 * @param string
	 */
	public MyExtendedForm(String title, String instructions, String classname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new java.awt.Color(255, 255, 255));
		topPanel.setPreferredSize(new java.awt.Dimension(100, 60));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		JPanel infoPanel = new JPanel();
		topPanel.add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new java.awt.Font("Tahoma", 1, 12));
		lblTitle.setForeground(new java.awt.Color(0, 0, 204));
		infoPanel.add(lblTitle, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Instructions");
		infoPanel.add(lblInstructions, BorderLayout.WEST);

		lblTitle.setText(title);
		lblInstructions.setText(instructions);

		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
//		bottompanel.setBackground(new java.awt.Color(255, 255, 255));
		bottomPanel.setPreferredSize(new java.awt.Dimension(100, 60));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		northPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottomPanel.add(northPanel, BorderLayout.NORTH);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doMenu();
			}
		});
		btnMenu.setIcon(new ImageIcon(MyExtendedForm.class.getResource("/images/menu.png")));
		northPanel.add(btnMenu);

		JButton btnClose = new JButton("Close");
		northPanel.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(MyExtendedForm.class.getResource("/images/close.png")));

		// Center Form
		doCenter();

	}

	private void doCenter() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void doMenu() {
//		// TODO Auto-generated method stub
//		Menu2 a = new Menu2();
//		a.addWindowListener(new java.awt.event.WindowAdapter() {
//		});
//		a.setVisible(true);
		dispose();
	}

}
