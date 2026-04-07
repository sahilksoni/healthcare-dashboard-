# 🏥 Healthcare Patient Monitoring Dashboard

A **JavaFX-based Healthcare Management & Patient Monitoring System** integrated with **MySQL database**.
This application enables doctors to manage patient records, generate OPD slips, and visualize patient data with charts in a clean and interactive UI.

---

## 🚀 Features

### 🔐 Authentication System

* Login & Register functionality
* Role-based access:

  * 👨‍⚕️ Doctor
  * 🧑 Patient

---

### 👨‍⚕️ Doctor Dashboard

* Add Patient (Name, Age, Disease, Medicine)
* Delete Patient records
* Search Patient by name
* Auto-fill form when selecting a table row
* View patient data in table format
* Bar Chart visualization (Patient Age Analysis)
* Generate OPD Slip automatically after adding patient

---

### 🧾 OPD Slip Generation

* Displays:

  * Patient Name, Age, Disease
  * Prescribed Medicine
  * Doctor Name
  * Cabin Number
* Auto popup after patient entry

---

### 👨‍🦱 Patient Dashboard

* View all patient records
* Simple and clean UI

---

## 🛠️ Tech Stack

| Technology   | Purpose               |
| ------------ | --------------------- |
| Java         | Core programming      |
| JavaFX       | UI development        |
| MySQL        | Database              |
| JDBC         | Database connectivity |
| Git & GitHub | Version control       |

---

## 📂 Project Structure

```
src/
│── Main.java
│── LoginPage.java
│── RegisterPage.java
│── DoctorDashboard.java
│── PatientDashboard.java
│── OPDSlip.java
│── DBConnection.java
│── User.java
│── UserDAO.java
│── Patient.java
│── PatientDAO.java
```

---

## ⚙️ Setup Instructions

### 1️⃣ Install Requirements

* Java JDK (17 or above)
* JavaFX SDK
* MySQL Server
* IntelliJ IDEA (recommended)

---

### 2️⃣ Database Setup

Run the following SQL commands:

```sql
CREATE DATABASE testdb;

USE testdb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(20)
);

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    disease VARCHAR(100),
    medicine VARCHAR(200)
);
```

---

### 3️⃣ Configure Database Connection

Update credentials in `DBConnection.java`:

```java
return DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb",
    "root",
    "YOUR_PASSWORD"
);
```

---

### 4️⃣ Add Required Libraries

#### ✅ JavaFX

* Add JavaFX SDK → `lib` folder
* Add VM options:

```
--module-path "PATH_TO_JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml
```

#### ✅ MySQL Connector

* Add `mysql-connector-j-9.6.0.jar` in Project Structure → Libraries

---

### 5️⃣ Run the Project

Run the main class:

```
Main.java
```

---

## 📊 Application Flow

1. Launch Application
2. Login / Register
3. Select Role (Doctor / Patient)
4. Doctor:

   * Add Patient
   * Generate OPD Slip
   * View Charts
5. Patient:

   * View records

---

## 📈 Future Enhancements

* 🔒 Password encryption (security improvement)
* 📄 Export OPD Slip as PDF
* 📡 Real-time patient monitoring (IoT integration)
* 📊 Advanced analytics dashboard
* 🏥 Multi-doctor system with departments

---

## 👨‍💻 Author

**Sahil Soni**
B.Tech Computer Science
VIT Bhopal University

---

## ⭐ GitHub

If you find this project useful, please give it a ⭐ on GitHub!
