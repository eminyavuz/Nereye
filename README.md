# Nereye – Modern Car Rental Platform

**Nereye** is a full-stack car rental web application built with **Spring Boot**, **React**, and **Docker**.  
It provides a modern solution for browsing, filtering, and reserving vehicles online. The platform is under **active development** and aims to deliver a seamless rental experience with both user and admin functionalities.

---

## ✨ Features

### ✅ Implemented
- 🔐 **Authentication & Authorization** – Secure login and registration with **JWT-based session management**.  
- 🚗 **Car Browsing & Filtering** – Search and filter cars by model, brand, and availability.  
- 📅 **Reservation System** – Book available cars with defined pickup and return dates.  
- 🛠️ **Admin Panel** – Manage cars, users, and reservations through a dedicated dashboard.  
- 📱 **Responsive UI** – Works on both desktop and mobile devices.  

### 🔜 In Progress / Planned
- 💳 **Payment Integration** (Stripe/PayPal or similar).  
- 📊 **Extended Reporting Tools** for admins.  
- 🌍 **Multi-language Support**.  
- 🔔 **Email / Notification System**.  

---

## 🛠️ Tech Stack

**Backend**  
- Spring Boot (Java 17+)  
- Spring Data JPA & Hibernate  
- MySQL  
- JWT Authentication  

**Frontend**  
- React.js  
- React Router  
- Context API / (Redux optional)  
- CSS framework (Bootstrap / Material-UI)  

**Deployment & DevOps**  
- Docker & Docker Compose (3 separate Dockerfiles: backend, frontend, root compose file)  
- Maven & npm for build management  

---

## 🚀 Getting Started

### Prerequisites
- Java 17+ & Maven  
- Node.js (LTS) & npm or yarn  
- MySQL (or run via Docker)  
- Docker & Docker Compose (for containerized deployment)  

---

### Local Development

1. **Clone repository**
   ```bash
   git clone https://github.com/eminyavuz/Nereye.git
   cd Nereye
   
2. **Backend (Spring Boot)**
   ```bash
   cd backend
   mvn clean package
    mvn spring-boot:run

Runs on http://localhost:8080

3. **Frontend (React)**
   ```bash
    cd frontend
    npm install
    npm start
Runs on http://localhost:3000



**Database Setup**
-Update application.properties with your MySQL credentials.
-Run schema migrations automatically handled by Spring JPA (or import provided SQL scripts if any).


**🤝 Contribution**

-This project is still evolving. Contributions are welcome!
-Fork the repo
-Create a new branch
-Commit your changes
-Open a pull request
-For major changes, please open an issue first to discuss what you’d like to change.

**📌 Note**

⚠️ This project is under active development and not yet finalized.
Some features are still in progress and the architecture may evolve as the system matures.
