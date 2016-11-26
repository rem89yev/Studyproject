package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.AdService;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.AdServiceImpl;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ALL_ADS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class FullAdDescriptionView extends RefreshableView {

    private AdService adService;
    private UserService userService;
    private JLabel adIdLabel;
    private JLabel usernameLabel;
    private JLabel fullDescription;
    private int userEnterId;

    private JButton backButton;

    public FullAdDescriptionView() {

        userService = new UserServiceImpl();
        adService = new AdServiceImpl();

        JLabel nameLabel = new JLabel("Advertisement number");
        content.add(nameLabel);
        usernameLabel = new JLabel();
        adIdLabel = new JLabel();
        fullDescription = new JLabel();

        content.add(adIdLabel);
        content.add(usernameLabel);
        content.add(fullDescription, new BorderLayout().CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), ALL_ADS, userEnterId);
            }
        });
        content.add(backButton);

    }

    @Override
    public View getName() {
        return View.FULL_AD_DESCRIPTION;
    }

    @Override
    public void refresh(Object... params) {
        userEnterId = (int) params[1];
        Ad ad = adService.getAdById((Integer) params[0]);
        adIdLabel.setText("" + ad.getAdId());
        usernameLabel.setText(userService.getUserById(ad.getUserId()).getUsername());
        fullDescription.setText(ad.getFullDescription());
    }
}
