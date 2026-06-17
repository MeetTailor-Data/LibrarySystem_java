public class User {

    private String name;
    private String email;
    private String enrollment;
    private String password;

    public User(String name, String email, String enrollment, String password) {
        this.name = name;
        this.email = email;
        this.enrollment = enrollment;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getEnrollment() { return enrollment; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return name + "," + email + "," + enrollment + "," + password;
    }
}
