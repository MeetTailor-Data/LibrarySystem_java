import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final String FILE = "data/users.txt";

    // ================= REGISTER USER =================
    public static boolean registerUser(String name, String email, String pass, String enroll) {
        try {
            FileWriter fw = new FileWriter(FILE, true);
            fw.write(name + "|" + email + "|" + pass + "|" + enroll + "\n");
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error while registering user.");
            return false;
        }
    }

    // ================= LOGIN USER =================
    // Returns NAME if login successful, otherwise null
    public static String loginUser(String email, String pass) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length >= 4) {
                    String fileName = data[0];
                    String fileEmail = data[1];
                    String filePass = data[2];

                    if (fileEmail.equals(email) && filePass.equals(pass)) {
                        br.close();
                        return fileName;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error while login.");
        }
        return null;
    }

    // ================= READ ALL USERS =================
    public static List<String> readAllUsers() {
        List<String> users = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;

            while ((line = br.readLine()) != null) {
                users.add(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading users file.");
        }
        return users;
    }

    // ================= GET NAME BY EMAIL =================
    public static String getNameByEmail(String email) {
        for (String line : readAllUsers()) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4 && parts[1].equals(email)) {
                return parts[0];
            }
        }
        return null;
    }

    // ================= GET ENROLLMENT BY EMAIL =================
    public static String getEnrollmentByEmail(String email) {
        for (String line : readAllUsers()) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4 && parts[1].equals(email)) {
                return parts[3];
            }
        }
        return null;
    }

    // ================= GET NAME BY ENROLLMENT =================
    public static String getNameByEnrollment(String enrollment) {
        for (String line : readAllUsers()) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4 && parts[3].equals(enrollment)) {
                return parts[0];
            }
        }
        return null;
    }

    // ================= GET EMAIL BY ENROLLMENT =================
    public static String getEmailByEnrollment(String enrollment) {
        for (String line : readAllUsers()) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4 && parts[3].equals(enrollment)) {
                return parts[1];
            }
        }
        return null;
    }
}
