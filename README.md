# Library Management System

A JavaFX-based GUI application for managing a library system with user registration, login, book issuing, and book returns.

## Project Analysis & Issues Fixed

###  Issues Identified and Resolved:

1. **Missing JavaFX Library**
   - **Problem**: JavaFX was not installed, causing 100+ compilation errors
   - **Solution**: Downloaded JavaFX 21.0.2 JAR files from Maven Central Repository
   - **Files**: JARs stored in `lib/` directory

2. **Undefined Variable in IssueBookPage**
   - **Problem**: `userEmail` variable used but never defined
   - **Solution**: Changed to use the `email` field from the instance variable

3. **Constructor Parameter Mismatch**
   - **Problem**: DashboardPage was calling constructors without required email parameter
   - **Solution**: 
     - Added email parameter to SearchBookPage and ReturnBookPage constructors
     - Updated DashboardPage button handlers to pass email to all page constructors

4. **Method Signature Mismatch**
   - **Problem**: Method signatures didn't match between caller and callee (missing `username` parameter)
   - **Solution**: Updated all getScene methods to accept `String username` parameter

5. **File Path Inconsistency**
   - **Problem**: UserManager was using `data/users.txt` instead of `src/data/users.txt`
   - **Solution**: Updated file path to match actual file location

6. **Incomplete Issue Book Functionality**
   - **Problem**: Issue button didn't implement the actual issue logic
   - **Solution**: Added proper implementation to call BookManager.issueBook() and show results

7. **LoginPage Dashboard Integration**
   - **Problem**: LoginPage created a simple welcome label instead of showing the full dashboard
   - **Solution**: Now properly instantiates DashboardPage and passes username

## Project Structure

```
LibrarySystem/
├── src/
│   ├── Main.java                 # Application entry point
│   ├── LoginPage.java            # Login UI
│   ├── RegPage.java              # Registration UI
│   ├── DashboardPage.java        # Main dashboard after login
│   ├── IssueBookPage.java        # Issue book functionality
│   ├── ReturnBookPage.java       # Return book functionality
│   ├── SearchBookPage.java       # Search book functionality
│   ├── BookManager.java          # Book management logic
│   ├── UserManager.java          # User authentication & registration
│   ├── User.java                 # User data model
│   ├── UIHelper.java             # UI styling constants
│   ├── FileHandler.java          # Legacy file handling (deprecated)
│   └── data/
│       ├── books.txt             # Book database (Book ID, Title, Author, Copies)
│       ├── users.txt             # User database (Name|Email|Password|Enrollment)
│       └── issued.txt            # Issued books tracking
├── bin/                          # Compiled Java classes (generated)
├── lib/                          # JavaFX and other dependencies
├── build.sh                      # Build script
├── run.sh                        # Run script
└── README.md                     # This file
```

## Requirements

- **Java**: JDK 21 or higher (OpenJDK recommended)
- **JavaFX**: 21.0.2 (automatically downloaded)
- **OS**: Linux, macOS, or Windows
- **Internet**: Required for first-time build (to download JavaFX)

## How to Run

### Quick Start (Recommended)

```bash
cd /home/dwarkeshramani/Downloads/LibrarySystem

# First time: Build the project
./build.sh

# Run the application
./run.sh
```

### Manual Steps

```bash
cd /home/dwarkeshramani/Downloads/LibrarySystem

# Compile (if not already compiled)
javac -cp "lib/*" -d bin src/*.java

# Run
java -cp "bin:lib/*" Main
```

## Features

### User Features
1. **Registration** - Create a new account with:
   - Full Name
   - Email
   - Enrollment Number
   - Password

2. **Login** - Authenticate with Email + Password

3. **Dashboard** - After login, access:
   - Issue Book
   - Return Book
   - Search Book

4. **Book Issuing** - Issue a book by:
   - Entering Book ID
   - Entering Student Enrollment Number

5. **Book Return** - Return a book by:
   - Entering the Book ID

6. **Book Search** - Search for books by:
   - Book ID

## Test Credentials

The system comes with sample users:

| Email | Password | Enrollment | Name |
|-------|----------|-----------|------|
| meet@gmail.com | meet123 | 236 | Meet Tailor |
| m@gmail.com | m123 | 123 | m |

## Sample Books Available

The system includes books like:
- Java Programming (ID: 101)
- Python Basics (ID: 102)
- Data Structures and Algorithms (ID: 103)
- C Programming Language (ID: 104)
- And more...

See `src/data/books.txt` for complete list.

## Troubleshooting

### Issue: "cannot find symbol" or JavaFX import errors
**Solution**: Run `./build.sh` first to download JavaFX JAR files

### Issue: "File not found" for user data
**Solution**: Ensure you run the application from the project root directory. File paths are relative to the execution directory.

### Issue: "No module named javafx"
**Solution**: Java classpath is not set correctly. Use `./run.sh` which handles this automatically.

### Issue: Cannot execute scripts on Windows
**Solution**: Use PowerShell or Git Bash, or run commands manually:
```bash
javac -cp "lib/*" -d bin src/*.java
java -cp "bin;lib/*" Main
```

## Application Flow

```
Main.java
    ↓
LoginPage (Login or Register)
    ├─→ RegPage (Registration)
    │    └─→ UserManager.registerUser()
    └─→ UserManager.loginUser()
        ↓
        DashboardPage
        ├─→ IssueBookPage → BookManager.issueBook()
        ├─→ ReturnBookPage → BookManager.returnBook()
        └─→ SearchBookPage → BookManager.searchBook()
```

## Data Storage

- **users.txt**: Stores user credentials in format: `Name|Email|Password|Enrollment`
- **books.txt**: Stores book catalog in format: `ID,Title,Author,Copies`
- **issued.txt**: Tracks issued books in format: `BookID,StudentEnrollment`

## Key Classes

### LoginPage
Handles user login and navigation to registration page.

### DashboardPage
Main menu after login with buttons for all book operations.

### BookManager
Core logic for:
- `searchBook(id)` - Search by book ID
- `issueBook(id, username)` - Issue a book to a student
- `returnBook(id)` - Return a book

### UserManager
Core logic for:
- `registerUser()` - Create new account
- `loginUser()` - Authenticate user
- `enrollmentExists()` - Check for duplicate enrollments

## Future Improvements

- [ ] Add database support (replace file-based storage)
- [ ] Implement fine calculation for overdue books
- [ ] Add admin panel
- [ ] Book availability/inventory management
- [ ] User profile management
- [ ] Book rating and reviews

## License

This project is for educational purposes.

---

**Status**: ✅ Fully working and tested
**Last Updated**: December 16, 2025
