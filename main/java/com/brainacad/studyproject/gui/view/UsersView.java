package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.gui.ViewRouter;
import com.brainacad.studyproject.gui.editor.UserEditButtonEditor;
import com.brainacad.studyproject.gui.renderer.TableButtonCellRenderer;
import com.brainacad.studyproject.service.UserService;
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
public class UsersView extends RefreshableView {

    private JButton backToLoginButton;
    private JButton addButton;
    private JTable usersTable;
    private DefaultTableModel tableModel;
    private UserService userService;

    public UsersView() {
        userService = new UserServiceImpl();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));
        addButton = new JButton("ADD");
        content.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRouter viewRouter = ViewRouter.getInstance();
                viewRouter.switchView(USERS, ADD_USER);
            }
        });
        String[] columns = {"ID", "USERNAME", ""};
        tableModel = new DefaultTableModel(columns, 0);
        usersTable = new JTable(tableModel);
        usersTable.getColumnModel().getColumn(2).setCellRenderer(new TableButtonCellRenderer());
        usersTable.getColumnModel().getColumn(2).setCellEditor(new UserEditButtonEditor(new JCheckBox()));
        content.add(usersTable);

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
        return USERS;
    }

    @Override
    public void refresh(Object... params) {
        tableModel.setRowCount(0);
        Collection<User> users = userService.getAllUsers();
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                tableModel.addRow(UsersView.this.map(user));
            }
        });
    }

    public Object[] map(User user) {
        return new Object[]{user.getId(), user.getUsername(), "EDIT"};
    }

}
