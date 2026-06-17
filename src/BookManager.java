import java.util.*;

public class BookManager {

    // ✅ MUST be static
    private static final Set<String> allBooks = new HashSet<>(Arrays.asList(
            "B101", "B102", "B103", "B104", "B105"
    ));

    // enrollment → issued books
    private static final Map<String, Set<String>> issued = new HashMap<>();

    // ISSUE BOOK
    public static boolean issueBook(String enrollment, String bookId) {
        if (!allBooks.contains(bookId)) return false;

        issued.putIfAbsent(enrollment, new HashSet<>());

        if (issued.get(enrollment).contains(bookId)) return false;

        issued.get(enrollment).add(bookId);
        return true;
    }

    // RETURN BOOK
    public static boolean returnBook(String enrollment, String bookId) {
        if (!issued.containsKey(enrollment)) return false;
        return issued.get(enrollment).remove(bookId);
    }

    // LIST ISSUED BOOKS
    public static List<String> listIssuedFor(String enrollment) {
        return issued.containsKey(enrollment)
                ? new ArrayList<>(issued.get(enrollment))
                : new ArrayList<>();
    }

    // SEARCH BOOK
    public static String searchBook(String keyword) {
        StringBuilder sb = new StringBuilder();

        for (String id : allBooks) {
            if (id.toLowerCase().contains(keyword.toLowerCase())) {
                sb.append("Found: ").append(id).append("\n");
            }
        }
        return sb.length() == 0 ? "No Book Found" : sb.toString();
    }

    // ✅ LIST ALL BOOKS (FIXED METHOD)
    public static String listAllBooks() {
        return String.join(", ", allBooks);
    }
}
