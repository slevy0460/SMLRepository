package stevelevy;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableTest {
    private static final int IMPORTANT_COLUMN = 2;

    public static void createAndShowGUI() {
        Object[][] data = new Object[2][4];
        String[] realRowData = { "1", "One", "1.0.2", "compile" };
        String[] fakeRowData = { "2", "Two", "1.3.2-FAKE", "compile" };

        for (int i = 0; i < realRowData.length; i++) {
            data[0][i] = realRowData[i];
            data[1][i] = fakeRowData[i];
        }

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(data, new String[] { "ID #", "Group #", "version", "Action" }) {
            Class[] types = new Class[] { Integer.class, String.class, String.class, String.class };
            boolean[] editable = new boolean[] { false, false, true, false };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editable[columnIndex];
            }
        });

        table.setDefaultRenderer(String.class, new CustomTableRenderer());

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(400, 400));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        f.add(scrollPane);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static class CustomTableRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Check if this is the important column (e.g., column 2)
            if (column == IMPORTANT_COLUMN) {
                c.setForeground(Color.RED); // Set the font color to red
            } else {
                c.setForeground(table.getForeground()); // Use default font color
            }

            return c;
        }
    }
}
