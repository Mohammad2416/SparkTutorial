package com.mohammadmirzakhani.courses.interfaces;

import com.mohammadmirzakhani.courses.model.User;
import org.omg.CORBA.UserException;

import java.util.Collection;

public interface UserService {

    void addUser(User user);

    Collection<User> getUsers();

    User getUser(String id);

    User editUser(User user) throws UserException;

    void deleteUser(String id);

    boolean userExist(String id);
}