package stevelevy;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;

class CellHighlighterRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public CellHighlighterRenderer getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		DecimalFormat decAmt$Format = new DecimalFormat("0.00");

		setOpaque(true); // Or color won't be displayed!

		Boolean neg = null;
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Object Number = table.getModel().getValueAt(row, column);
		String str = Number.toString();
		str = str.replace("$", "");

		if (column == 0) {
			table.getColumnModel().getColumn(column).setCellRenderer(new CellJustifyRendererCenter());
		}
//
//		if (column > 1) {
//			table.getColumnModel().getColumn(column).setCellRenderer(new JustifyRendererRight());
//		}

//		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
//		table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);

		if (column == 5 || column == 9) {

			Double d = Double.parseDouble(str);

			if (d >= 0) {
				neg = false;
			} else {
				neg = true;
			}
			
			if (neg) {
				cell.setBackground(new Color(255, 127, 127));
			} else {
				cell.setBackground(Color.green);
			}

		} else {
			cell.setBackground(table.getBackground());
		}

		return this;
	}

}
