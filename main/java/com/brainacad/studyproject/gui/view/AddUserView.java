package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ADD_USER;
import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class AddUserView extends RefreshableView {

    private UserService userService;

    private JButton createButton;
    private JTextField userIdField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel userIdLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;


    public AddUserView() {
        userService = new UserServiceImpl();

        usernameLabel = new JLabel("Name");
        usernameLabel.setBounds(70, 54, 86, 14);
        content.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setColumns(10);
        content.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 54, 86, 14);
        content.add(passwordLabel);
        passwordField = new JTextField();
        passwordField.setColumns(10);
        content.add(passwordField);


        createButton = new JButton("CREATE");
        createButton.setBounds(131, 165, 89, 23);
        content.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                if (userService.addUser(user) == 0) {
                    JOptionPane.showMessageDialog(null, "Failed to create user");
                } else {
                    ViewRouter viewRouter = ViewRouter.getInstance();
                    viewRouter.switchView(ADD_USER, USERS);
                }

            }
        });
    }

    @Override
    public View getName() {
        return ADD_USER;
    }

    @Override
    public void refresh(Object... params) {
        usernameField.setText("");
        passwordField.setText("");

    }
}
