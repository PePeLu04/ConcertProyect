package login;

public class User {
    private String name;
    private String email;
    private String phone;
    private String Role;
    private String username = "Usuario";

    private String password = "Usuario";

    public User(String name, String email, String phone, String Role, String username, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.Role = Role;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getRole() {
        return Role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
