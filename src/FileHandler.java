import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "users.txt";

    // Save a new user
    public static void saveUser(User user) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(user.toString());
            bw.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load all users
    public static List<User> loadUsers() {
        List<User> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 4) {
                    User user = new User(data[0], data[1], data[2], data[3]);
                    list.add(user);
                }
            }

        } catch (Exception e) {
            // Ignore file not found (first time)
        }

        return list;
    }

    // Check login
    public static boolean validateLogin(String enroll, String password) {
        List<User> users = loadUsers();

        for (User u : users) {
            if (u.getEnrollment().equals(enroll) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Check if enrollment already exists
    public static boolean enrollmentExists(String enroll) {
        List<User> users = loadUsers();

        for (User u : users) {
            if (u.getEnrollment().equals(enroll)) {
                return true;
            }
        }
        return false;
    }
}
