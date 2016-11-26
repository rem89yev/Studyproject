package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.EDIT_USER;
import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class EditUserView extends RefreshableView {

    private JButton updateButton;
    private JButton deleteButton;

    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel userIdLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private UserService userService;


    public EditUserView() {

        userService = new UserServiceImpl();


        userIdLabel = new JLabel("ID");
        userIdLabel.setBounds(70, 54, 86, 14);
        content.add(userIdLabel);

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


        updateButton = new JButton("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdLabel.getText().trim();
                String username = usernameField.getText().trim();
                String pass = passwordField.getText().trim();
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setUsername(username);
                user.setPassword(pass);
                if (!userService.update(user)) {
                    JOptionPane.showMessageDialog(null, "Failed to update user");
                }
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), USERS);
            }
        });
        content.add(updateButton);

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdLabel.getText();
                if (!userService.delete(Integer.parseInt(id))) {
                    JOptionPane.showMessageDialog(null, "Failed to delete");
                }
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(getName(), USERS);
            }
        });
        content.add(deleteButton);
    }


    @Override
    public View getName() {
        return EDIT_USER;
    }

    @Override
    public void refresh(Object... params) {
        User user = userService.getUserById((Integer) params[0]);
        userIdLabel.setText("" + user.getId());
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
    }
}
