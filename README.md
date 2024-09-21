`# ATM Machine Project

## Description

This project is a simple **ATM Machine** simulation built using **Java**. It interacts with a **MySQL** database using **JDBC** for performing basic banking operations like:

- **Check Balance**: View the current balance of the account.
- **Cash Withdrawal**: Withdraw cash from the account.
- **Cash Credit**: Deposit money into the account.

The project demonstrates how to build a basic banking system with Java and MySQL, using JDBC for database interactions.

## Prerequisites

Before running this project, make sure you have the following installed on your system:

- **Java JDK 8** or higher.
- **MySQL Server**.
- **MySQL JDBC Driver** (`mysql-connector-java.jar`).

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/atm-machine-java.git
cd atm-machine-java
```

### 2. Set Up MySQL Database

- Start the MySQL server.
- Create the database and tables:

```sql
CREATE DATABASE javaproject;
USE javaproject;

CREATE TABLE accountname (
    ID INT PRIMARY KEY,
    NAME VARCHAR(100)
);

CREATE TABLE accountdetail (
    ID INT PRIMARY KEY,
    FIRST_NAME VARCHAR(100),
    SECOND_NAME VARCHAR(100),
    PIN INT,
    BALANCE DECIMAL(10, 2)
);
```

- Insert sample data into the `accountname` and `accountdetail` tables:

```sql
INSERT INTO accountname (ID, NAME) VALUES
(1, 'John Doe'),
(2, 'Jane Doe');

INSERT INTO accountdetail (ID, FIRST_NAME, SECOND_NAME, PIN, BALANCE) VALUES
(1, 'John', 'Doe', 1234, 1000.00),
(2, 'Jane', 'Doe', 5678, 2000.00);
```

### 3. Update JDBC Credentials

In the `Main.java` file, update the following lines to match your local MySQL setup:

```java
// Database URL
final static String url = "jdbc:mysql://localhost:3306/javaproject";
// Database credentials
final static String username = "root";
final static String password = "Password@mysql#123";
```

### 4. Compile and Run the Application

javac Main.java

java Main

## Usage

### 1. Launch the ATM

Once the application starts, it will prompt the user for the account number and PIN. If the details are valid, the user will be welcomed and shown a menu to:

1. Check the balance.
2. Withdraw cash.
3. Credit money.

### 2. Menu Options

- **Check Balance**: The current balance of the account is displayed.
- **Withdraw Cash**: Enter the amount to withdraw; the balance will be updated accordingly.
- **Credit Money**: Enter the amount to deposit; the balance will be updated accordingly.
- **Exit**: The program will exit after the updates are saved to the database.

### Example Interaction

```
========Welcome========
========Enter your Card Detail========
1
========Welcome John Doe========
========Enter the 4 digit Pin========
1234
========Welcome John Doe========
1.] for Balance :
2.] for Cash Debit :
3.] for Cash Credit :
4.] for Exit :
1
1000.00
```

## Features

- **Database Operations**: The project uses JDBC to connect to MySQL and execute SQL queries for retrieving and updating account information.
- **Menu Driven**: The user can select different options like viewing balance, withdrawing, or depositing money.
- **Validation**: Ensures correct PIN entry and handles errors like insufficient balance.

## Project Structure

- **Main.java**: Contains the main logic to run the ATM operations and interact with the user.
- **SqlOperation.java**: Contains all the database-related operations, including retrieving and updating account information.
- **Menu.java**: Handles the menu interface and operations like balance check, withdrawal, and credit.

## Running Tests

To test the database connection and operations, make sure to:

1. Start the MySQL server.
2. Ensure the database and tables are correctly set up.
3. Run the program from your IDE or command line to verify all operations (balance check, debit, credit) work as expected.

## Built With

- **Java**: The main programming language.
- **MySQL**: Used to store account and transaction details.
- **JDBC**: Java Database Connectivity used for interacting with the database.
- **Maven**: For project build and dependency management (if used).

## Contributing

If you'd like to contribute to this project:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.

## Authors

- **Your Name** - [Apratim Kanth](https://github.com/apratimkanth)

---

### Additional Notes

- Be sure to provide clear instructions for setting up the database, including sample data.
- Test the application thoroughly to ensure it handles incorrect inputs (e.g., wrong PIN) gracefully.
