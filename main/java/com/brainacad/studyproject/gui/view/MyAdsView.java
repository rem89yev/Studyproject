package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.gui.editor.AdMyEditButtonEditor;
import com.brainacad.studyproject.gui.renderer.TableButtonCellRenderer;
import com.brainacad.studyproject.service.AdService;
import com.brainacad.studyproject.service.impl.AdServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.function.Consumer;

import static com.brainacad.studyproject.gui.view.View.*;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class MyAdsView extends RefreshableView {

    private int userId;
    private DefaultTableModel tableModel;
    private AdService adService;
    private JTable adsTable;
    private JButton moveToAllAds;
    private JButton addButton;

    public MyAdsView() {

        adService = new AdServiceImpl();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        String[] columns = {"ID", "SHORTDESCRIPTION", "USERENTERID", ""};
        tableModel = new DefaultTableModel(columns, 0);
        adsTable = new JTable(tableModel);
        adsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        adsTable.getColumnModel().getColumn(3).setCellRenderer(new TableButtonCellRenderer());
        adsTable.getColumnModel().getColumn(3).setCellEditor(new AdMyEditButtonEditor(new JCheckBox()));
        content.add(adsTable);

        moveToAllAds = new JButton("Move to all ads");
        content.add(moveToAllAds);
        moveToAllAds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), ALL_ADS, userId);
            }
        });

        addButton = new JButton("ADD Advertisement");
        content.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), ADD_ADVERTISEMENT, userId);
            }
        });

    }

    @Override
    public View getName() {
        return MY_ADS;
    }

    @Override
    public void refresh(Object... params) {
        userId = (Integer) params[0];
        tableModel.setRowCount(0);
        Collection<Ad> ads = adService.getAllAds();
        ads.forEach(new Consumer<Ad>() {
            @Override
            public void accept(Ad ad) {
                if (ad.getUserId() == userId) {
                    tableModel.addRow(MyAdsView.this.map(ad));
                }
            }
        });

    }

    public Object[] map(Ad ad) {

        return new Object[]{ad.getAdId(), ad.getShortDescription(), userId, "FULL"};
    }
}
