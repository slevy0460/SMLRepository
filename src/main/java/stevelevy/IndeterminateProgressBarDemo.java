package stevelevy;
import javax.swing.*;
import java.awt.*;

public class IndeterminateProgressBarDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Indeterminate Progress Bar Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true); // Set indeterminate mode

            frame.add(progressBar, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}