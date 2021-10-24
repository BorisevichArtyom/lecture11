package by.itacademy.javaenterprise.dao;

import by.itacademy.javaenterprise.exception.DAOException;
import entity.User;


public interface UserDao {

    void addUser(User user) throws DAOException;

    // TODO: 24.10.2021
//    void update(User user) throws DAOException;

    void delete(User user) throws DAOException;


}
