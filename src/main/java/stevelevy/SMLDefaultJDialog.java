package stevelevy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SMLDefaultJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SMLDefaultJDialog dialog = new SMLDefaultJDialog();
			dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SMLDefaultJDialog() {
		 setBounds(100, 100, 1000, 800);
		  getContentPane().setLayout(new BorderLayout());
		  contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		  // Create a JScrollPane and add the contentPanel to it
		  JScrollPane scrollPane = new JScrollPane(contentPanel);
		  contentPanel.setLayout(new BorderLayout(0, 0));
		  
		  JPanel northPanel = new JPanel();
		  northPanel.setPreferredSize(new Dimension(800, 50));
		  contentPanel.add(northPanel, BorderLayout.NORTH);
		  northPanel.setLayout(new BorderLayout(0, 0));
		  
		  JLabel lblTitle = new JLabel("Title of Form");
		  lblTitle.setPreferredSize(new Dimension(50, 25));
		  lblTitle.setForeground(Color.BLUE);
		  lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		  northPanel.add(lblTitle, BorderLayout.NORTH);
		  
		  JLabel lblInstructions = new JLabel("Instructions");
		  lblInstructions.setPreferredSize(new Dimension(50, 25));
		  lblInstructions.setForeground(Color.BLACK);
		  lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		  northPanel.add(lblInstructions, BorderLayout.SOUTH);
		  
		  JTabbedPane centertabbedPane = new JTabbedPane(JTabbedPane.TOP);
		  centertabbedPane.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		  centertabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  contentPanel.add(centertabbedPane, BorderLayout.CENTER);
		  
		  JPanel bottompanel = new JPanel();
		  bottompanel.setPreferredSize(new Dimension(800, 100));
		  bottompanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  contentPanel.add(bottompanel, BorderLayout.SOUTH);
		  bottompanel.setLayout(new BorderLayout(0, 0));
		  
		  JPanel buttonpanel = new JPanel();
		  buttonpanel.setPreferredSize(new Dimension(1500, 50));
		  buttonpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  bottompanel.add(buttonpanel, BorderLayout.NORTH);
		  buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		  
		  JButton btnClose = new JButton("Close");
		  btnClose.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		dispose();
		  	}
		  });
		  btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
		  btnClose.setIcon(new ImageIcon(SMLDefaultJDialog.class.getResource("/images/close.png")));
		  buttonpanel.add(btnClose);
		  
		  JPanel classpanel = new JPanel();
		  classpanel.setPreferredSize(new Dimension(1500, 50));
		  classpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  bottompanel.add(classpanel, BorderLayout.SOUTH);
		  classpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		  
		  JLabel lblClassName = new JLabel("SMLDefaultJFrame");
		  lblClassName.setPreferredSize(new Dimension(100, 25));
		  lblClassName.setFont(new Font("Tahoma", Font.BOLD, 10));
		  classpanel.add(lblClassName);

		  // Add the scrollPane to the contentPane instead of the contentPanel
		  getContentPane().add(scrollPane, BorderLayout.CENTER);

		  // ...
		{
		
		}
	}

}
