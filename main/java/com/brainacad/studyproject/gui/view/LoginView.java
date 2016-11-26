package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.service.LoginService;
import com.brainacad.studyproject.service.impl.LoginServiceImpl;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ALL_ADS;
import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class LoginView extends RefreshableView {

    final static Logger LOG = Logger.getLogger(LoginView.class);

    private JTextField usernameField;
    private JTextField passwordField;
    private LoginService loginService;
    private int userEnterId;

    public LoginView() {
        loginService = new LoginServiceImpl(DaoFactory.getDaoFactory().getUserDao());
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        usernameField = new JTextField();
        usernameField.setBounds(188, 51, 99, 20);
        content.add(usernameField);
        usernameField.setColumns(10);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(70, 54, 86, 14);
        content.add(usernameLabel);

        passwordField = new JTextField();
        passwordField.setBounds(188, 106, 99, 20);
        content.add(passwordField);
        passwordField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 109, 86, 14);
        content.add(passwordLabel);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(131, 165, 89, 23);

        content.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                Role login = loginService.login(username, password);
                ViewRouter viewRouter = ViewRouter.getInstance();
                switch (login) {
                    case USER:
                        LOG.info("User " + username + "ID " + loginService.getUserId(username, password) + " logged in");
                        viewRouter.switchView(getName(), ALL_ADS, loginService.getUserId(username, password));
                        break;
                    case ADMIN:
                        LOG.info("User " + username + "ID " + loginService.getUserId(username, password) + " logged in");
                        viewRouter.switchView(getName(), USERS);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Wrong username or password");
                        refresh();
                        break;
                }
//                if (login.equals(ADMIN)) {
//                    ViewRouter viewRouter = ViewRouter.getInstance();
//                    viewRouter.switchView(getName(), USERS);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Wrong username or password");
//                    refresh();
//                }
            }
        });
    }

    @Override
    public View getName() {
        return View.LOGIN;
    }

    @Override
    public JPanel getContent() {
        return content;
    }

    @Override
    public void refresh(Object... params) {
        usernameField.setText("");
        passwordField.setText("");
    }
}
