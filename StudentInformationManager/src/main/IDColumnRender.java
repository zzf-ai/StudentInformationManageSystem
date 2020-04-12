package main;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *学号列的显示样式
 */
public class IDColumnRender extends JCheckBox implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setSelected(isSelected);
        if(value!=null)
            this.setText(value.toString());
        //背景设置
        this.setOpaque(true);
        //被选中
        if(isSelected){
            this.setBackground(table.getSelectionBackground());
            this.setForeground(table.getSelectionForeground());
        }
        //未选中
        else {
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }
        return this;
    }
}
