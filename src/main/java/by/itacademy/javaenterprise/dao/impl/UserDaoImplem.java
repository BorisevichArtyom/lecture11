package by.itacademy.javaenterprise.dao.impl;

import by.itacademy.javaenterprise.connection.DataSource;
import by.itacademy.javaenterprise.dao.UserDao;
import by.itacademy.javaenterprise.exception.DAOException;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImplem implements UserDao {

    private static final String ADD_QUERY = "INSERT Diary_user(email,user_password,first_name,last_name)  " +
            "VALUES (?,?,?,?)";

    private static final int EMAIL_ADD_QUERY_INDEX = 1;
    private static final int PASSWORD_ADD_QUERY_INDEX = 2;
    private static final int FIRST_NAME_ADD_QUERY_INDEX = 3;
    private static final int LAST_NAME_ADD_QUERY_INDEX = 4;

    private static final String DELETE_QUERY = "DELETE FROM Diary_user WHERE email=?";

    private static final int EMAIL_DELETE_QUERY_INDEX = 1;


    private static final Logger logger = LoggerFactory.getLogger(
            UserDaoImplem.class);


    @Override
    public void addUser(User user) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ADD_QUERY)) {
            prepareAddStatement(pst, user);
            con.setAutoCommit(false);
            pst.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException throwables) {
            logger.error("Error while adding a user{}", user.toString(), throwables);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
            connection.setAutoCommit(false);
            prepareDeleteStatement(statement, user);
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error while deleting a user{}", user.toString(), e);
            throw new DAOException(e);
        }
    }

    private void prepareDeleteStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(EMAIL_DELETE_QUERY_INDEX, user.getEmail());
    }

    private void prepareAddStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(EMAIL_ADD_QUERY_INDEX, user.getEmail());
        statement.setString(PASSWORD_ADD_QUERY_INDEX, user.getPassword());
        statement.setString(FIRST_NAME_ADD_QUERY_INDEX, user.getFirstName());
        statement.setString(LAST_NAME_ADD_QUERY_INDEX, user.getLastName());
    }


    public static void main(String[] args) throws DAOException {
        User user = new User();
        user.setEmail("'griib@tut.by'");
        user.setFirstName("'Denis'");
        user.setLastName("'Samusenko'");
        user.setPassword("'Bicep'");
        UserDaoImplem userDaoImplem = new UserDaoImplem();

        userDaoImplem.addUser(user);
        userDaoImplem.delete(user);
        userDaoImplem.addUser(user);


    }


}
