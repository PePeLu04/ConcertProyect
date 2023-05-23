package org.proyect.Model.DAO;

import org.proyect.Model.Connections.ConnectionMySql;
import org.proyect.Model.Domain.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBand implements DAO<Band> {
    private Connection conn;
    public DAOBand(Connection conn) {
        this.conn = conn;
    }
    public DAOBand() {
        this.conn= ConnectionMySql.getConnect();
    }

    @Override
    public Band insert(Band entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bands (id, name, components) VALUES (?, ?, ?)");
            ps.setString(1, entity.getId());
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
            PreparedStatement ps = conn.prepareStatement("UPDATE bands SET name = ?, components = ? WHERE id = ?");
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getComponents());
            ps.setString(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(String entity) throws SQLException {
        String sql = "DELETE FROM bands WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity);

            stmt.executeUpdate();
        }

    }

    @Override
    public Band searchById(String id) throws SQLException {
        String sql = "SELECT * FROM bands WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Band(
                            rs.getString("id"),
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
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String components = rs.getString("components");
                    Band band = new Band(id, name, components);
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