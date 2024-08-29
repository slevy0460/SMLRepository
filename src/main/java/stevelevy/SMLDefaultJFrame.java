package stevelevy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class SMLDefaultJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
	String className = getClass().getSimpleName();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMLDefaultJFrame frame = new SMLDefaultJFrame();
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

	public SMLDefaultJFrame() {
		setTitle("Set Title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1500, 800);
		// Initialize the contentPane
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create a JScrollPane
		JScrollPane mainscrollPane = new JScrollPane();
		// Set the contentPane as the viewport view of the JScrollPane
		mainscrollPane.setViewportView(contentPane);

		// Create a top panel with a preferred height
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(1500, 50));
		// Add the top panel to the North region of the BorderLayout
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		// Title of Form
		JLabel lblTitle = new JLabel("Title of Form");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitle.setPreferredSize(new Dimension(50, 25));
		northPanel.add(lblTitle, BorderLayout.NORTH);
		// Instructions of form
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setForeground(new Color(0, 0, 0));
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructions.setPreferredSize(new Dimension(50, 25));
		northPanel.add(lblInstructions, BorderLayout.CENTER);
		
		centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// Set your tab icon
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(SMLDefaultJFrame.class.getResource("/images/menu.png")));
		contentPane.add(centertabbedPane, BorderLayout.CENTER);

		// Set the custom panel as the tab component
		centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		// Set the bottom panel
		JPanel bottompanel = new JPanel();
		bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bottompanel.setPreferredSize(new Dimension(1500, 100));
		contentPane.add(bottompanel, BorderLayout.SOUTH);
		bottompanel.setLayout(new BorderLayout(0, 0));
		// Set the button panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonpanel.setPreferredSize(new Dimension(1500, 50));
		bottompanel.add(buttonpanel, BorderLayout.NORTH);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(SMLDefaultJFrame.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);
		// Set the button panel
		JPanel classpanel = new JPanel();
		classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		classpanel.setPreferredSize(new Dimension(1500, 50));
		bottompanel.add(classpanel, BorderLayout.SOUTH);
		classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClassName.setText(className);
		lblClassName.setPreferredSize(new Dimension(100, 25));
		classpanel.add(lblClassName);
		// Set the JScrollPane as the contentPane of the JFrame
		setContentPane(mainscrollPane);

		// Center form on monitor
		doCenterForm();
		// call doPopulateTable at the end of the constructor

	}

	private void doCenterForm() {
		// Center Form
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

}
