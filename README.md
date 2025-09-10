# 🚀 API Automation Using HTTPClient

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=java)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue?logo=apachemaven)
![Tests](https://img.shields.io/badge/Tests-Automated-green)
![Status](https://img.shields.io/badge/Project-Active-brightgreen)

---

## 📌 Overview
This project demonstrates **REST API Automation** using **Java’s `HttpClient` (Java 11+)**.  
It provides reusable utilities to send HTTP requests (GET, POST, PUT, DELETE), parse JSON responses, and validate API results.  
The project is built with **Maven** for dependency management and **TestNG** for test execution.

---

## 📂 Project Structure

APIAutomationUsingHTTPClient/
├── src/ # Java source code (tests & utilities)
│ └── test/java # Test classes
├── test-output/ # Test execution reports
├── pom.xml # Maven dependencies & plugins
└── README.md # Project documentation

---

## ⚙️ Tech Stack
- **Language:** Java 11+
- **Build Tool:** Maven
- **Testing Framework:** TestNG
- **HTTP Client:** Java 11+ `HttpClient`

---

## 🚀 Getting Started

### ✅ Prerequisites
- Install **Java JDK 11 or above**
- Install **Maven 3.6+**
- Clone the repository

### 📥 Clone Repository
```bash
git clone https://github.com/abdullahned/APIAutomationUsingHTTPClient.git
cd APIAutomationUsingHTTPClient

▶️ Run Tests
mvn clean test

🧪 Sample Test Code
@Test
public void testGetPostById() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    System.out.println("Response: " + response.body());
}
📊 Reports
test-output/index.html

🤝 Contributing

Fork the repo

Create a feature branch (git checkout -b feature-name)

Commit changes (git commit -m "Add new feature")

Push to your branch (git push origin feature-name)

Open a Pull Request 🚀
