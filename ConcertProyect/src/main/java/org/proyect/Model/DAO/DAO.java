package org.proyect.Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> extends AutoCloseable {
    T insert(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(String entity) throws SQLException;

    T searchById(String id) throws SQLException;

    List<T> findAll() throws SQLException;
}
