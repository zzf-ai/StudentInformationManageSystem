package main;
/**
 * 性别列的显示
 */

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SexColumnRender extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Boolean sex=(Boolean)value;
        if(sex!=null&&sex==true)
            this.setText("男");
        else
            this.setText("女");
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
