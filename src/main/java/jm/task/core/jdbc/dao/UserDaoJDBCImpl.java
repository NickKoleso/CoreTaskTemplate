package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT primary key, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL);";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(createTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана успешно");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS Users";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(dropTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена успешно");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO Users (name, lastName, age) VALUES (?, ? , ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем- " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при добавление User");
            throwables.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        String removeUser = "DELETE FROM Users WHERE ID = ? ";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(removeUser)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User удален успешно");
        } catch (SQLException throwables) {
            System.out.println("Ошибка при удаление User");
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAllUsers = "SELECT name, lastName, age FROM Users";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(getAllUsers)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanUsers = "DELETE FROM Users";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(cleanUsers)) {
            preparedStatement.executeUpdate();
            System.out.println("Очистка таблицы проведена успешно");

        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы");
            e.printStackTrace();
        }
    }
}
