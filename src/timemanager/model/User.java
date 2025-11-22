package timemanager.model;

/**
 * Simple User model
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;   // ADD THIS
    private String role;

    public User() { }

    // Constructor used during login
    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Constructor used during registration
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }    // ADD
    public void setPassword(String password) {           // ADD
        this.password = password;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
