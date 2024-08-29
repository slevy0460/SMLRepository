package stevelevy;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellIconColumnRenderer extends DefaultTableCellRenderer {
    private final ImageIcon icon;

    public CellIconColumnRenderer(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setIcon(icon);
        return label;
    }
}

