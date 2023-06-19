package org.proyect.Model.DAO;

import org.proyect.Model.Connections.ConnectionMySql;
import org.proyect.Model.Domain.Band;
import org.proyect.Model.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUser extends DAO<User> {
    private Connection conn;
    public DAOUser(Connection conn) {
        this.conn = conn;
    }
    public DAOUser() {
        this.conn= ConnectionMySql.getConnect();
    }
    private DAO<User> userDAO;
    private DAO<Band> bandDAO;

    @Override
    public User insert(User entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (id, username, password, role, dni, name_band) VALUES (?, ?, ?, ?, ? (SELECT name from bands where name=?))");
            ps.setString(1, entity.getId());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole());
            ps.setString(5, entity.getDni());
            ps.setString(6, entity.getName_band());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public User update(User entity) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET username = ?, password = ?, role = ?, dni = ? + name_band=(SELECT name FROM bands WHERE name=?) WHERE id = ?");
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole());
            ps.setString(4, entity.getId());
            ps.setString(5, entity.getDni());
            ps.setString(6, entity.getName_band());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(String user) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);

            stmt.executeUpdate();
        }

    }

    @Override
    public User searchById(String id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("dni"),
                            rs.getString("name_band"));
                }
            }
        }
        return null;
    }
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    String dni = rs.getString("dni");
                    String band = rs.getString("name_band");
                    User user = new User(id, username, password, role, dni, band);
                    users.add(user);
                }
            }
        }
        return users;
    }

    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

}
