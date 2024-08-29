package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TestArray extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIconCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestArray frame = new TestArray();
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
	public TestArray() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel buttonpanel = new JPanel();
		contentPane.add(buttonpanel, BorderLayout.SOUTH);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doEdit();
			}

		});
		btnEdit.setIcon(new ImageIcon(TestArray.class.getResource("/images/edit.png")));
		buttonpanel.add(btnEdit);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(TestArray.class.getResource("/images/close.png")));
		buttonpanel.add(btnClose);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtIconCode = new JTextField();
		txtIconCode.setBounds(176, 66, 76, 19);
		panel.add(txtIconCode);
		txtIconCode.setColumns(10);
	}

	private void doEdit() {
		String iconcode = txtIconCode.getText();
		String[] IconArray = { "HG", "FP", "FS", "NA" };
		String[] IconDescArray = { "Hanging", "Folded Pants", "Folded Shirts", "'Not applicable" };
		boolean iconFound = false;

		// Check icon code for valid entry
		for (int i = 0; i < IconArray.length; i++) {
			if (IconArray[i].equals(iconcode)) {
				JOptionPane.showMessageDialog(null, "Icon code is valid.  Icon Description is " + IconDescArray[i]);
				iconFound = true;
				break;
			}
		}

		if (!iconFound) {
			JOptionPane.showMessageDialog(null, "Icon code is invalid.  Please re-enter.");
		}
	}
}
