package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = DBUtil.getConnection();
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users ORDER BY id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public User getUserById(int userId) {
        User user = new User();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setDob(resultSet.getDate(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> addUser(User user) {

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (firstname, lastname, email, dob) " +
                            "VALUES (?,?,?,?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, new Date(user.getDob().getTime()));

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getAllUsers();
    }

    public boolean updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET firstname=?,lastname=?,email=?,dob=? WHERE id=?");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, new Date(user.getDob().getTime()));
            preparedStatement.setInt(5,user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User deleteUser(int userId) {
        User user = getUserById(userId);

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");

            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}