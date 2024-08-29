
package stevelevy;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
 private static final long serialVersionUID = 1L;

 JTable table;
 JButton renderButton;
 JButton editButton;
 String text;
 ImageIcon icon;

 public ButtonColumn(JTable table, int column, String text, String iconPath) {
  super();
  this.table = table;
  this.text = text;
  this.icon = new ImageIcon(getClass().getResource(iconPath));

  renderButton = new JButton();
  renderButton.setIcon(icon);

  editButton = new JButton();
  editButton.setFocusable(false);
  editButton.addActionListener(this);
  editButton.setIcon(icon);

  TableColumnModel columnModel = table.getColumnModel();
  columnModel.getColumn(column).setCellRenderer(this);
  columnModel.getColumn(column).setCellEditor(this);
 }

 @Override
 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
   int row, int column) {
  if (hasFocus) {
   renderButton.setForeground(table.getForeground());
   renderButton.setBackground(UIManager.getColor("Button.background"));
  } else if (isSelected) {
   renderButton.setForeground(table.getSelectionForeground());
   renderButton.setBackground(table.getSelectionBackground());
  } else {
   renderButton.setForeground(table.getForeground());
   renderButton.setBackground(UIManager.getColor("Button.background"));
  }

  renderButton.setText((value == null) ? "" : value.toString());
  return renderButton;
 }

 @Override
 public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
  text = (value == null) ? "" : value.toString();
  editButton.setText(text);
  return editButton;
 }

 @Override
 public Object getCellEditorValue() {
  return text;
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
 }
}