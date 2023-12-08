package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BookedWalkDAOImpl implements BookedWalkDAO {

    @Override
    public BookedWalk get(int idUser, int idAnimal) throws SQLException {
        Connection connection = Connector.getConnection();
        BookedWalk bookedWalk = null;
        String sql = "SELECT id_user, id_animal, date_of_walk, duration " +
                "FROM pzdb.booked_walks " +
                "WHERE id_user = ? AND id_animal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idAnimal);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int resultIdUser = resultSet.getInt("id_user");
            int resultIdAnimal = resultSet.getInt("id_animal");
            Date resultDate = resultSet.getDate("date_of_walk");
            int resultDuration = resultSet.getInt("duration");
            bookedWalk = new BookedWalk(resultIdUser, resultIdAnimal, resultDate.toLocalDate(), resultDuration);
        }

        return bookedWalk;
    }

    @Override
    public List<BookedWalk> getAll() throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "SELECT id_user, id_animal, date_of_walk, duration FROM pzdb.booked_walks ";
        List<BookedWalk> bookedWalks = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int resultIdUser = resultSet.getInt("id_user");
            int resultIdAnimal = resultSet.getInt("id_animal");
            Date resultDate = resultSet.getDate("date_of_walk");
            int resultDuration = resultSet.getInt("duration");
            BookedWalk bookedWalk = new BookedWalk(resultIdUser, resultIdAnimal, resultDate.toLocalDate(), resultDuration);
            bookedWalks.add(bookedWalk);
        }
        Connector.closeResultSet(resultSet);
        Connector.closeConnection(connection);
        return bookedWalks;
    }

    @Override
    public int delete(BookedWalk bookedWalk) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "DELETE FROM pzdb.booked_walks WHERE id_user = ? AND id_animal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, bookedWalk.getIdUser());
        preparedStatement.setInt(2, bookedWalk.getIdAnimal());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }

    @Override
    public int update(BookedWalk bookedWalk) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "UPDATE pzdb.booked_walks SET date_of_walk = ?, duration = ? WHERE id_user = ? AND id_animal = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(bookedWalk.getDateOfWalk()));
        preparedStatement.setInt(2, bookedWalk.getDuration());
        preparedStatement.setInt(3, bookedWalk.getIdUser());
        preparedStatement.setInt(4, bookedWalk.getIdAnimal());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);
        return result;
    }

    @Override
    public int insert(BookedWalk bookedWalk) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "INSERT INTO pzdb.booked_walks (id_user, id_animal, date_of_walk, duration) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, bookedWalk.getIdUser());
        preparedStatement.setInt(2, bookedWalk.getIdAnimal());
        preparedStatement.setDate(3, Date.valueOf(bookedWalk.getDateOfWalk()));
        preparedStatement.setInt(4, bookedWalk.getDuration());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }

    @Override
    public int save(BookedWalk bookedWalk) throws SQLException {
        return 0;
    }

    @Override
    public BookedWalk get (int id) {
        return new BookedWalk();
    }

}
