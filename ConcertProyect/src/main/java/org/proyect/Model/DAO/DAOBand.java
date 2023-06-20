package org.proyect.Model.DAO;

import org.proyect.Model.Connections.ConnectionMySql;
import org.proyect.Model.Domain.Band;
import org.proyect.Model.Domain.Instrument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBand extends DAO<Band> {
    private Connection conn;
    /**
     * Constructor que acepta una conexión de base de datos como parámetro.
     *
     * @param conn La conexión de base de datos
     */
    public DAOBand(Connection conn) {
        this.conn = conn;
    }
    /**
     * Constructor predeterminado que utiliza una conexión de base de datos predefinida.
     * Obtener una conexión de base de datos utilizando ConnectionMySql.getConnect().
     */
    public DAOBand() {
        this.conn= ConnectionMySql.getConnect();
    }

    private DAO<Band> bandDAO;
    private DAO<Instrument> instrumentDAO;

    // Constructor, inicialización de DAOs, etc.

    @Override
    public Band insert(Band entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bands (band_id, name, components) VALUES (?, ?, ?)");
            ps.setString(1, entity.getBand_id());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getComponents());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Band update(Band entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE bands SET name = ?, components = ? WHERE band_id = ?");
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getComponents());
            ps.setString(3, entity.getBand_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(String entity) throws SQLException {
        String sql = "DELETE FROM bands WHERE band_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity);

            stmt.executeUpdate();
        }

    }

    @Override
    public Band searchById(String band_id) throws SQLException {
        String sql = "SELECT * FROM bands WHERE band_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, band_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Band(
                            rs.getString("band_id"),
                            rs.getString("name"),
                            rs.getString("components")
                    );
                }
            }
        }
        return null;
    }
    @Override
    public List<Band> findAll() throws SQLException {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String band_id = rs.getString("band_id");
                    String name = rs.getString("name");
                    String components = rs.getString("components");
                    Band band = new Band(band_id, name, components);
                    bands.add(band);
                }
            }
        }
        return bands;
    }

    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

}