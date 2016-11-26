package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.gui.editor.AdFullButtonEditor;
import com.brainacad.studyproject.gui.renderer.TableButtonCellRenderer;
import com.brainacad.studyproject.service.AdService;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.AdServiceImpl;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

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
public class AllAdsView extends RefreshableView {

    private JButton moveToMyAds;
    private JButton backToLoginButton;
    private UserService userService;
    private AdService adService;
    private DefaultTableModel tableModel;
    private JTable adsTable;
    private int userEnterId;
    //private JTabbedPane tabbedPane;

    public AllAdsView() {
        userService = new UserServiceImpl();
        adService = new AdServiceImpl();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));

        String[] columns = {"ID", "USERNAME", "USERENTERID", "SHORTDESCRIPTION", ""};
        tableModel = new DefaultTableModel(columns, 0);
        adsTable = new JTable(tableModel);
        adsTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        adsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        adsTable.getColumnModel().getColumn(4).setCellRenderer(new TableButtonCellRenderer());
        adsTable.getColumnModel().getColumn(4).setCellEditor(new AdFullButtonEditor(new JCheckBox()));
        content.add(adsTable);

//        tabbedPane = new JTabbedPane();
//        JComponent panel1 = new JPanel();
//        panel1.setPreferredSize(new Dimension(500, 300));
//        tabbedPane.addTab("Tab 1", panel1);
//        panel1.add(adsTable);
//        JComponent panel2 = new JPanel();
//        panel2.setPreferredSize(new Dimension(500, 300));
//        tabbedPane.addTab("Tab 2", panel2);
//        content.add(tabbedPane);

        moveToMyAds = new JButton("Move to my ads");
        content.add(moveToMyAds);
        moveToMyAds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), MY_ADS, userEnterId);
            }
        });

        backToLoginButton = new JButton("Back to Login");
        content.add(backToLoginButton);
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), LOGIN);
            }
        });

    }


    @Override
    public View getName() {
        return ALL_ADS;
    }

    @Override
    public void refresh(Object... params) {
        userEnterId = (Integer) params[0];
        tableModel.setRowCount(0);
        Collection<Ad> ads = adService.getAllAds();
        ads.forEach(new Consumer<Ad>() {
            @Override
            public void accept(Ad ad) {
                tableModel.addRow(AllAdsView.this.map(ad));
            }
        });
    }

    public Object[] map(Ad ad) {
        return new Object[]{ad.getAdId(), userService.getUserById(ad.getUserId()).getUsername(), userEnterId, ad.getShortDescription(), "FULL DESCRIPTION"};
    }

}
