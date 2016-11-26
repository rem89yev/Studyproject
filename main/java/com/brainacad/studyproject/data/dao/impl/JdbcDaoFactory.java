package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.dao.AdDao;
import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.dao.UserDao;

/**
 * Created by Yevhen-PC on 16.11.2016.
 */
public class JdbcDaoFactory extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public AdDao getAdDao() {
        return new JdbcAdDao();
    }
}
