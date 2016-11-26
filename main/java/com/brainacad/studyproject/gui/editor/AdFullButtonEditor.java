package com.brainacad.studyproject.gui.editor;

import com.brainacad.studyproject.gui.ViewRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ALL_ADS;
import static com.brainacad.studyproject.gui.view.View.FULL_AD_DESCRIPTION;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class AdFullButtonEditor extends DefaultCellEditor {

    private JTable table;
    private JButton button;
    private int row;
    private String label;
    private boolean isPushed;

    public AdFullButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            int adId = (int) table.getValueAt(row, 0);
            int userEnterId = (int) table.getValueAt(row, 2);
            ViewRouter viewRouter = ViewRouter.getInstance();
            viewRouter.switchView(ALL_ADS, FULL_AD_DESCRIPTION, adId, userEnterId);
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
