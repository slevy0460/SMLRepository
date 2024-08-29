package stevelevy;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import javax.swing.*;

public class Monitor {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("My Java App");
		myFrame.setSize(800, 600); // Set your preferred size
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Show the JFrame on the second monitor (if available)
		showOnScreen(1, myFrame);

		myFrame.setVisible(true);
	}

	public static void showOnScreen(int screen, JFrame frame) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		screen = 2;
		if (screen > -1 && screen < gd.length) {
			frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
		} else if (gd.length > 0) {
			frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
		} else {
			throw new RuntimeException("No Screens Found");
		}
	}
}
