package stevelevy;


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;

public class CellJustifyRendererCenter extends DefaultTableCellRenderer {
    public CellJustifyRendererCenter() {
        setHorizontalAlignment(JLabel.CENTER);
    }
}