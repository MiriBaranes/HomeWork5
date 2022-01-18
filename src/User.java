public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String toString() {
        return "Full name: " + this.firstName + " " + this.lastName;
    }
}
