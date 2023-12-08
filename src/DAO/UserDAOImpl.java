package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User get(int id) throws SQLException {
        Connection connection = Connector.getConnection();
        User user = null;
        String sql = "SELECT id, name, email, date_of_birth FROM pzdb.users WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int resultId = resultSet.getInt("id");
            String resultName = resultSet.getString("name");
            String resultEmail = resultSet.getString("email");
            Date resultDate = resultSet.getDate("date_of_birth");
            user = new User(resultId, resultName, resultEmail, resultDate.toLocalDate());
        }
        Connector.closeResultSet(resultSet);
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "SELECT id, name, email, date_of_birth FROM pzdb.users";
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date dateOfBirth = resultSet.getDate("date_of_birth");
            User user = new User(id, name, email, dateOfBirth.toLocalDate());
            users.add(user);
        }

        return users;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "DELETE FROM pzdb.users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }
    @Override
    public int update(User user) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "UPDATE pzdb.users SET name = ?, email = ?, date_of_birth = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setDate(3, Date.valueOf(user.getDateOfBirth()));
        preparedStatement.setInt(4, user.getId());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);
        return result;
    }
    @Override
    public int insert(User user) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "INSERT INTO pzdb.users (id, name, email, date_of_birth) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setDate(4, Date.valueOf(user.getDateOfBirth()));
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }
    @Override
    public int save(User user) throws SQLException {
        return 0;
    }
}
