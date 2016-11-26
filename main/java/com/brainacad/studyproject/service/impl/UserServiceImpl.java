package com.brainacad.studyproject.service.impl;

import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.UserService;

import java.util.Collection;


/**
 * Created by Yevhen-PC on 10.11.2016.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = DaoFactory.getDaoFactory().getUserDao();

    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUserById(int id) {
        return userDao.get(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }
}
