package org.proyect.Model.DAO;

import org.proyect.Model.Connections.ConnectionMySql;
import org.proyect.Model.Domain.Instrument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOInstrument implements DAO<Instrument> {
    private Connection conn;
    public DAOInstrument(Connection conn) {
        this.conn = conn;
    }
    public DAOInstrument() {
        this.conn= ConnectionMySql.getConnect();
    }

    @Override
    public Instrument insert(Instrument entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO instruments (id, name, sound, price) VALUES (?, ?, ?, ?)");
            ps.setString(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getSound());
            ps.setString(4, entity.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Instrument update(Instrument entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE instruments SET name = ?, sound = ?, price = ? WHERE id = ?");
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getSound());
            ps.setString(3, entity.getPrice());
            ps.setString(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(String instrument) throws SQLException {
        String sql = "DELETE FROM instruments WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, instrument);

            stmt.executeUpdate();
        }

    }

    @Override
    public Instrument searchById(String id) throws SQLException {
        String sql = "SELECT * FROM instruments WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Instrument(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("sound"),
                            rs.getString("price")
                    );
                }
            }
        }
        return null;
    }
    @Override
    public List<Instrument> findAll() throws SQLException {
        List<Instrument> instruments = new ArrayList<>();
        String sql = "SELECT * FROM instruments";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String sound = rs.getString("sound");
                    String price = rs.getString("price");
                    Instrument instrument = new Instrument(id, name, sound, price);
                    instruments.add(instrument);
                }
            }
        }
        return instruments;
    }

    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

}
