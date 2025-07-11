# Salesforce Functional Automation Testing Framework

This repository contains a robust Selenium-based automation framework to validate Salesforce CRM modules like Accounts, Contacts, Opportunities, Leads, Cases, Tasks, Reports, and Dashboards.

---

## 🔧 Tech Stack

- **Language:** Java
- **Framework:** Selenium WebDriver + TestNG
- **Build Tool:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Reporting:** TestNG Reports
- **CI/CD Ready:** Jenkins-compatible
- **Tools Used:** Postman (for API), Git, ChromeDriver

---

## 🧪 Features Automated

| Module        | Description                        | Test Cases Implemented                    |
|---------------|------------------------------------|-------------------------------------------|
| **Login/Logout**   | Auth flows for Salesforce CRM      | LoginTest, LogoutTest                     |
| **Accounts**       | Manage customer/company records    | Create, Edit, Delete, Search, Add Contact |
| **Contacts**       | Manage individuals linked to accounts | Create, Edit, Delete, Search           |
| **Opportunities**  | Track deals and revenue potential  | Create opportunity, Validate header       |
| **Leads**          | Manage potential customers         | Create Lead, Convert Lead                 |
| **Cases**          | Handle support tickets             | Create, Set priority and status           |
| **Tasks/Activities** | User task management             | Create task, Mark complete                |
| **Reports**        | Data reporting engine              | Create, Save, Run reports                 |
| **Dashboards**     | Visual representation of reports   | Validate widgets, View dashboards         |

---

## 🚀 How to Run the Tests

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/salesforce-automation.git
cd salesforce-automation
2. Configure Properties
Update the config.properties file with:

properties
Copy
Edit
url=https://login.salesforce.com
username=your-username
password=your-password3. Run TestNG Suite
bash
Copy
Edit
mvn clean test -DsuiteXmlFile=testng-suite.xml
Or via Eclipse/IntelliJ:
Right-click testng-suite.xml → Run as TestNG Suite.

📁 Project Structure
graphql
Copy
Edit
projectself/
├── base/                  # Base classes like WebDriver setup
├── pages/                 # Page Object Model (POM) classes
├── tests/                 # All TestNG test classes
├── resources/
│   └── config.properties  # URL, credentials
├── testng-suite.xml       # Master suite for all tests
└── pom.xml                # Maven dependencies
📸 Screenshots & Logs
Available in /screenshots and /logs (if implemented with Log4j/TestNG listeners)

✍️ Author
Saloni Jain
Senior Systems Engineer | Salesforce QA | Automation with Selenium & Java
🔗 LinkedIn | 🌐 Portfolio

📄 License
This project is licensed under the MIT License.

yaml
Copy
Edit

---

Let me know if you also want:

- Jenkins pipeline `Jenkinsfile`
- GitHub Actions `.yml`
- A badge section (build, test, license)

I can add them in seconds.








