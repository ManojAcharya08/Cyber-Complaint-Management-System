# Cyber-Complaint-Management-System

Cyber-Complaint-Management-System is a web application for securely submitting, tracking, and managing cyber-related complaints.  
It enables users to report incidents and administrators to efficiently process and resolve cases through a modern interface.

---

## 🚀 Features

- Submit cyber complaints online
- Track the status of submitted complaints
- Admin dashboard for managing and resolving cases
- Secure login for users and administrators
- Responsive, modern UI

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf
- **Frontend:** HTML5, CSS3, JavaScript (ES6)
- **Database:** MySQL (or H2 for development/testing)
- **Build Tool:** Maven or Gradle

---

## 📦 Getting Started

### 1. **Clone the repository**


### 2. **Configure the Database**

Edit `src/main/resources/application.properties`:


> 💡 For quick testing, you can use H2 in-memory database.

### 3. **Build and Run the Application**


**Solution:**

- Ensure `index.html` is in `src/main/resources/templates/`
- Your controller should return `"index"` (not `"index.html"`)
- Restart your server after changes

### Static Files Not Loading

- Make sure your CSS, JS, and images are in `src/main/resources/static/`
- Reference them in HTML with a leading slash, e.g. `/style.css`, `/Picture.jpg`

---

## 🤝 Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

## 🙋 FAQ

**Q: I see a 404 for `/Picture.jpg`?**  
A: Make sure the image is in `src/main/resources/static/` and restart your app.

**Q: How do I change the accent color or theme?**  
A: Edit the color codes in `style.css`.

---

**Clone, customize, and use this system to streamline cyber complaint management!**
git clone https://github.com/ManojAcharya08/Cyber-Complaint-Management-System.git
cd Cyber-Complaint-Management-System

