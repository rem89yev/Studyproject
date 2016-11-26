package com.brainacad.studyproject.gui.renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Yevhen-PC on 12.11.2016.
 */
public class TableButtonCellRenderer extends JButton implements TableCellRenderer {

    public TableButtonCellRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }

}
