package stevelevy;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.text.DecimalFormat;

public class CellLeadingZeroRenderer extends DefaultTableCellRenderer {
	DecimalFormat formatter = new DecimalFormat("00"); // Adjust the number of zeros as needed

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof Number) {
			value = formatter.format((Number) value);
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}
