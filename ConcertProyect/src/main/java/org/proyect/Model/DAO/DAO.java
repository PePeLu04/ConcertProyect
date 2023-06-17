package org.proyect.Model.DAO;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> implements AutoCloseable {
    // insertar una entidad de tipo T en la base de datos. Devuelve la entidad insertada.
    abstract T insert(T entity) throws SQLException;

    //actualizar una entidad de tipo T en la base de datos. Devuelve la entidad actualizada.
    abstract T update(T entity) throws SQLException;

    //eliminar una entidad identificada por su nombre de la base de datos. No devuelve ningún valor.
    abstract void delete(String entity) throws SQLException;

    //buscar una entidad de tipo T en la base de datos por su identificador. Devuelve la entidad encontrada.
    abstract T searchById(String id) throws SQLException;

    //recuperar todas las entidades de tipo T de la base de datos. Devuelve una lista de todas las entidades encontradas.
    abstract List<T> findAll() throws SQLException;
}
