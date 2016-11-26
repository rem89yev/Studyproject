package com.brainacad.studyproject.service.impl;

import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.LoginService;

/**
 * Created by Yevhen-PC on 02.11.2016.
 */
public class LoginServiceImpl implements LoginService {

    private UserDao userDao; //= DaoFactory.getDaoFactory().getUserDao();

    public LoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Role login(String username, String password) {

        User user = userDao.getUserByName(username);
        if (user != null && password.equals(user.getPassword())) {
            return user.getRole();
        }
        return null;
    }

    @Override
    public int getUserId(String username, String password) {
        User user = userDao.getUserByName(username);
        if (user != null && password.equals(user.getPassword())) {
            return user.getId();
        }
        return 0;
    }
}
