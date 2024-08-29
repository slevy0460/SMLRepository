package stevelevy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyForm extends MyExtendedForm {

	public MyForm(String title, String instructions) {
		super(title, instructions, " ");
		// TODO Auto-generated constructor stub
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyForm frame = new MyForm("My Form exmaple", "My form instructions");
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

	public class MyExtendedForm extends MyForm {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		public MyExtendedForm() {
			super("My Form example", "Test MyForm");
			// Set up your extended form here
		}
	}

}
