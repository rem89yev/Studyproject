package com.brainacad.studyproject.service;

import com.brainacad.studyproject.data.domain.User;

import java.util.Collection;

/**
 * Created by Yevhen-PC on 11.11.2016.
 */
public interface UserService {

    Collection<User> getAllUsers();

    User getUserById(int id);

    int addUser(User user);

    boolean update(User user);

    boolean delete(int id);
}
