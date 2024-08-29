package stevelevy;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;

class CellHighlighterRenderer2 extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public CellHighlighterRenderer2 getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		DecimalFormat decAmt$Format = new DecimalFormat("0.00");

		setOpaque(true); // Or color won't be displayed!

		Boolean neg = null;
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Object text = table.getModel().getValueAt(row, 0);
		Object number = table.getModel().getValueAt(row, 5);
		String str = number.toString();
		str = str.replace("$", "");

//		if (column == 0) {
//			table.getColumnModel().getColumn(column).setCellRenderer(new CellJustifyRendererCenter());
//		}
//
//		if (column > 1) {
//			table.getColumnModel().getColumn(column).setCellRenderer(new JustifyRendererRight());
//		}

//		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
//		table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);

		if (column == 5 && text.equals("Balance")) {
			System.out.println("text is : " + text);
			System.out.println("str is : " + str);
			System.out.println("row is : " + row);
			System.out.println("column is : " + column);
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
