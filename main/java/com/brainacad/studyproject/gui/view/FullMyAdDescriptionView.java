package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.AdService;
import com.brainacad.studyproject.service.impl.AdServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.FULL_MY_AD_DESCRIPTION;
import static com.brainacad.studyproject.gui.view.View.MY_ADS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class FullMyAdDescriptionView extends RefreshableView {

    int userEnterId;
    private AdService adService;
    private JLabel adIdLabel;
    private JTextField shortDescription;
    private JTextField fullAdDescription;
    private JButton updateButton;
    private JButton deleteButton;

    public FullMyAdDescriptionView() {

        adService = new AdServiceImpl();
        JLabel nameLabel = new JLabel("Advertisement number");
        content.add(nameLabel);
        adIdLabel = new JLabel();
        shortDescription = new JTextField();
        shortDescription.setColumns(10);
        fullAdDescription = new JTextField();
        fullAdDescription.setColumns(20);
        content.add(adIdLabel);
        content.add(shortDescription);
        content.add(fullAdDescription);

        updateButton = new JButton("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = adIdLabel.getText().trim();
                String shortDescrip = shortDescription.getText();
                String fullDescrip = fullAdDescription.getText();
                Ad ad = new Ad();
                ad.setAdId(Integer.parseInt(id));
                ad.setShortDescription(shortDescrip);
                ad.setFullDescription(fullDescrip);

                if (!adService.update(ad)) {
                    JOptionPane.showMessageDialog(null, "Failed to update user");
                }
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), MY_ADS, userEnterId);
            }
        });
        content.add(updateButton);

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = adIdLabel.getText().trim();
                if (!adService.delete(Integer.parseInt(id))) {
                    JOptionPane.showMessageDialog(null, "Failed to delete");
                }
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), MY_ADS, userEnterId);
            }
        });
        content.add(deleteButton);


    }


    @Override
    public View getName() {
        return FULL_MY_AD_DESCRIPTION;
    }

    @Override
    public void refresh(Object... params) {
        userEnterId = (int) params[1];
        Ad ad = adService.getAdById((Integer) params[0]);
        adIdLabel.setText("" + ad.getAdId());
        shortDescription.setText(ad.getShortDescription());
        fullAdDescription.setText(ad.getFullDescription());

    }
}
