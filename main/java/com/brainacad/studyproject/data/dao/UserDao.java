package com.brainacad.studyproject.data.dao;

import com.brainacad.studyproject.data.domain.User;

/**
 * Created by Yevhen-PC on 02.11.2016.
 */
public interface UserDao extends CrudDao<User> {

    User getUserByName(String username);

}
