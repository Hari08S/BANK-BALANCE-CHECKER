Bank Balance Checker & Fund Transfer System – Java Application

**Project Overview**

The Bank Balance Checker & Fund Transfer System is a Java-based console application that simulates core banking operations such as checking account balance and transferring funds between accounts.
The application follows a layered architecture and integrates with an Oracle Database using JDBC for real-time data operations.
This project demonstrates:
JDBC connectivity with Oracle
Layered architecture (Main → Service → DAO → Bean → Util)
Exception handling
Real-time transaction updates
Business rule validation (Insufficient Funds check)

**Features**
**Check Account Balance**

Fetches account balance from Oracle database
Displays current balance before transaction

💸 **Fund Transfer**

Transfers amount from one account to another
Updates sender and receiver balances
Records transaction details
Automatically checks for sufficient balance

 **Exception Handling**

Throws InsufficientFundsException if balance is low
Handles SQL and connection errors
Prevents invalid transactions

**Technologies Used**

Java (JDK 8+)
Eclipse IDE
JDBC
Oracle Database (XE)
OOP Principles
Custom Exception Handling

**How It Works**

Application checks account balance.
Creates a TransferBean object with:
From Account
To Account
Amount
Date
Calls BankService.transfer()
Service validates balance.
DAO updates balances in database.
Transaction is recorded.
Success or exception message displayed.

**How to Run**

Configure Oracle XE database.
Update DB credentials in DBUtil.java.
Add Oracle JDBC driver to project.
Run BankMain.java.
View results in console.


**OUTPUT**

<img width="1133" height="765" alt="image" src="https://github.com/user-attachments/assets/9f536021-3e41-4328-81ac-acbea423f966" />

<img width="1105" height="445" alt="image" src="https://github.com/user-attachments/assets/c978fdc8-636d-45d4-9bf8-a2bf3b92b4d2" />
