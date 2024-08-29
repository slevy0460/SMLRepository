package stevelevy;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

class CellJustifyRendererRight extends DefaultTableCellRenderer {
    public CellJustifyRendererRight() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }
}