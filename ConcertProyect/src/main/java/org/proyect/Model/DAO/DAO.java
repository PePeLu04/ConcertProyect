package org.proyect.Model.DAO;

import org.proyect.Model.DTO.Instrument;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    void insert(Instrument entity) throws SQLException;

    void update(Instrument entity) throws SQLException;

    void delete(int id) throws SQLException;

    Instrument searchById(int id) throws SQLException;

    List<Instrument> findAll() throws SQLException;
}
