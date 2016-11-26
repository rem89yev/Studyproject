package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.AdService;
import com.brainacad.studyproject.service.impl.AdServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ADD_ADVERTISEMENT;
import static com.brainacad.studyproject.gui.view.View.MY_ADS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class AddAdvertisementView extends RefreshableView {

    private AdService adService;
    private JLabel shortDescriptionLabel;
    private JLabel fullDescriptionLabel;
    private JTextField shortDescriptionField;
    private JTextField fullDescriptionField;
    private int userEnterId;
    private JButton createButton;

    public AddAdvertisementView() {

        adService = new AdServiceImpl();
        shortDescriptionLabel = new JLabel("Short description of advertisement");
        content.add(shortDescriptionLabel);
        shortDescriptionField = new JTextField();
        shortDescriptionField.setColumns(15);
        content.add(shortDescriptionField);
        fullDescriptionLabel = new JLabel("Full description of advertisement");
        content.add(fullDescriptionLabel);
        fullDescriptionField = new JTextField();
        fullDescriptionField.setColumns(30);
        content.add(fullDescriptionField);

        createButton = new JButton("CREATE");
        content.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = userEnterId;
                String shortDescript = shortDescriptionField.getText();
                String fullDescription = fullDescriptionField.getText();
                Ad ad = new Ad();
                ad.setUserId(userId);
                ad.setShortDescription(shortDescript);
                ad.setFullDescription(fullDescription);

                if (adService.addAd(ad) == 0) {
                    JOptionPane.showMessageDialog(null, "Failed to create advertisement");
                }
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), MY_ADS, userEnterId);
            }
        });


    }

    @Override
    public View getName() {
        return ADD_ADVERTISEMENT;
    }

    @Override
    public void refresh(Object... params) {
        shortDescriptionField.setText("");
        fullDescriptionField.setText("");
        userEnterId = (int) params[0];

    }
}
