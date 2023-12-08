package DAO;

import java.sql.SQLException;

public interface BookedWalkDAO extends DAO<BookedWalk> {
    BookedWalk get(int idUser, int idAnimal) throws SQLException;
}
