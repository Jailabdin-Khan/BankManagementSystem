# BankManagementSystem


A simple Java-based console application simulating core banking operations like account creation, deposit, withdrawal, balance inquiry, and user login/logout. Designed using Object-Oriented Programming (OOP) principles and integrated with MySQL for persistent data storage.

---

## 💻 Technologies Used

- Java  
- MySQL  
- JDBC  
- BCrypt (for password hashing)  
- OOP Concepts  
- Exception Handling  

---

## 🧠 Features

### 🔐 Pre-Login:
- User Registration  
- User Login  
- Exit  

### 🏦 Post-Login:
- Create Account  
- Deposit Money  
- Withdraw Money  
- View Balance  
- Logout  

---

## 🗂️ Project Structure

- `User.java` – Handles user attributes and operations  
- `Account.java` – Models bank account details  
- `DatabaseManager.java` – Manages JDBC connections and queries  
- `Main.java` – Application entry point  
- `Utils.java` – Helper methods (like input validation)  

---

## 🔒 Security

- Passwords are securely hashed using **BCrypt**
- Sensitive input and database operations are handled with proper **exception handling**  

---

## 🧪 How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/Jailabdin-Khan/Bank-Management-System.git

2. Import the project into your IDE (Eclipse/VS Code)


3. Make sure MySQL is installed and running


4. Set up your database:

Create a new DB

Update DB credentials in the code

Run the SQL script. 

5. Run Main.java
