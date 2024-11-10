package lk.iit.ticketingsystem.Models;


import jakarta.persistence.*;

@Entity
public class User {
    private @Id @GeneratedValue int id;
    private String fullName;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(String fullName, String username, String password, String email, String role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.valueOf(role);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
