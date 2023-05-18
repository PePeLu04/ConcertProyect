package org.proyect.Model.DAO;

import org.proyect.Model.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
public class DAOUser<user> implements DAO {
    protected Connection conn;

    public DAOUser() {
        this.conn = this.conn;
    }
    @Override
    public void insert(User user) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (id, name, last_name, location) VALUES (?, ?, ?, ?)");
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getLast_name());
            ps.setString(4, user.getLocation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Instrument entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE instruments SET name = ?, sound = ?, price = ? WHERE id = ?");
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getSound());
            ps.setDouble(3, entity.getPrice());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM instruments WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }

    }

    @Override
    public Instrument searchById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Instrument(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("sound"),
                            rs.getDouble("price")
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
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String sound = rs.getString("sound");
                    double price = rs.getDouble("price");
                    Instrument instrument = new Instrument(id, name, sound, price);
                    instruments.add(instrument);
                }
            }
        }
        return instruments;
    }

}*/
