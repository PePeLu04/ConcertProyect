package org.proyect.Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> extends AutoCloseable {
    // insertar una entidad de tipo T en la base de datos. Devuelve la entidad insertada.
    T insert(T entity) throws SQLException;

    //actualizar una entidad de tipo T en la base de datos. Devuelve la entidad actualizada.
    T update(T entity) throws SQLException;

    //eliminar una entidad identificada por su nombre de la base de datos. No devuelve ningún valor.
    void delete(String entity) throws SQLException;

    //buscar una entidad de tipo T en la base de datos por su identificador. Devuelve la entidad encontrada.
    T searchById(String id) throws SQLException;

    //recuperar todas las entidades de tipo T de la base de datos. Devuelve una lista de todas las entidades encontradas.
    List<T> findAll() throws SQLException;
}
