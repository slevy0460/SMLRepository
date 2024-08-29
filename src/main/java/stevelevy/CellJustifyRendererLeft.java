package stevelevy;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

class CellJustifyRendererLeft extends DefaultTableCellRenderer {
    public CellJustifyRendererLeft() {
        setHorizontalAlignment(SwingConstants.LEFT);
    }
}