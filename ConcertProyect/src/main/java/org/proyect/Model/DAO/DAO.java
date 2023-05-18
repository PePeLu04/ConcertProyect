package org.proyect.Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> extends AutoCloseable {
    T insert(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(int entity) throws SQLException;

    T searchById(int id) throws SQLException;

    List<T> findAll() throws SQLException;
}
