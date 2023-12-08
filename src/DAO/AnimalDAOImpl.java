package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOImpl implements AnimalDAO{
    @Override
    public Animal get(int id) throws SQLException {
        Connection connection = Connector.getConnection();
        Animal animal = null;
        String sql = "SELECT id, name, age, date_of_birth, species, breed FROM pzdb.animals WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int resultId = resultSet.getInt("id");
            String resultName = resultSet.getString("name");
            int resultAge = resultSet.getInt("age");
            Date resultDate = resultSet.getDate("date_of_birth");
            String resultSpecies = resultSet.getString("species");
            String resultBreed = resultSet.getString("breed");
            animal = new Animal(resultId, resultName, resultAge, resultDate.toLocalDate(), resultSpecies, resultBreed);
        }
        Connector.closeResultSet(resultSet);
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);
        AnimalFactory af = new AnimalFactory();
        return af.createAnimal(animal);
    }

    @Override
    public List<Animal> getAll() throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "SELECT id, name, age, date_of_birth, species, breed FROM pzdb.animals";
        List<Animal> animals = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int resultId = resultSet.getInt("id");
            String resultName = resultSet.getString("name");
            int resultAge = resultSet.getInt("age");
            Date resultDate = resultSet.getDate("date_of_birth");
            String resultSpecies = resultSet.getString("species");
            String resultBreed = resultSet.getString("breed");
            Animal animal = new Animal(resultId, resultName, resultAge, resultDate.toLocalDate(), resultSpecies, resultBreed);
            AnimalFactory af = new AnimalFactory();
            animals.add(af.createAnimal(animal));
        }
        Connector.closeResultSet(resultSet);
        Connector.closeConnection(connection);
        return animals;
    }

    @Override
    public int delete(Animal animal) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "DELETE FROM pzdb.animals WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animal.getId());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }
    @Override
    public int update(Animal animal) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "UPDATE pzdb.animals SET name = ?, age = ?, date_of_birth = ?, species = ?, breed = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setInt(2, animal.getAge());
        preparedStatement.setDate(3, Date.valueOf(animal.getDateOfBirth()));
        preparedStatement.setString(4, animal.getSpecies());
        preparedStatement.setString(5, animal.getBreed());
        preparedStatement.setInt(6, animal.getId());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);
        return result;
    }
    @Override
    public int insert(Animal animal) throws SQLException {
        Connection connection = Connector.getConnection();
        String sql = "INSERT INTO pzdb.animals (id, name, age, date_of_birth, species, breed) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animal.getId());
        preparedStatement.setString(2, animal.getName());
        preparedStatement.setInt(3, animal.getAge());
        preparedStatement.setDate(4, Date.valueOf(animal.getDateOfBirth()));
        preparedStatement.setString(5, animal.getSpecies());
        preparedStatement.setString(6, animal.getBreed());
        int result = preparedStatement.executeUpdate();
        Connector.closePreparedStatement(preparedStatement);
        Connector.closeConnection(connection);

        return result;
    }
    @Override
    public int save(Animal user) throws SQLException {
        return 0;
    }
}
