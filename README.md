<h1 align="center">Spring Contact Manager </h1>

<p align="center">
  <em>Desktop based Spring Contact Manager</em>
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/StepJes/Smart-Contact-Manager/main/banner.png" alt="Smart Contact Manager">
</p>


## Project Overview

This is a Smart Contact Manager created using Java and the Spring Boot framework.
* Member 1 : Zeel Mansuri - 2305101020047
* Member 2 : Anchal Patel - 2305101020064
* Member 3 : Hetanshi Modi - 2305101020141
## Features

* Create and Manage Contacts
* Search Contacts by Name or Email
* User Authentication and Authorization
* Modern UI with Bootstrap
* REST API for contact management
* Payment integration with Razor Pay
* Rich text editor with Tiny MCE
* Email verification using SMTP and Gmail

## Prerequisites

Be sure you have the following installed on your development machine:

+ Java Development Kit (JDK) >= 8
+ MySQL
+ Maven
+ Git

## Project Installation

To setup a local development environment:

Clone the GitHub Project,
```bash
git clone git@github.com:StepJes/Smart-Contact-Manager.git

cd Smart-Contact-Manager
```

Install project dependencies,
```bash
mvn install
```

Configure the database:

1. Create a MySQL database named `smart_contact_manager`.
2. Update the `src/main/resources/application.properties` file with your MySQL credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
   spring.datasource.username=<your_mysql_username>
   spring.datasource.password=<your_mysql_password>
   spring.jpa.hibernate.ddl-auto=update
   ```

Run the application,
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Additional Configuration

### SMTP Configuration for Email Notifications

Update the `application.properties` file with your Gmail SMTP settings:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<your_email@gmail.com>
spring.mail.password=<your_email_password>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Razor Pay Configuration

Update the `application.properties` file with your Razor Pay API keys:
```properties
razorpay.key.id=<your_razorpay_key_id>
razorpay.key.secret=<your_razorpay_key_secret>
```

## Features in Detail

### Create and Manage Contacts
- Users can add new contacts, edit existing ones, and delete contacts they no longer need.

### Search Contacts
- Users can search for contacts by name or email, making it easy to find specific contacts quickly.

### Categorize Contacts
- Contacts can be grouped into categories for better organization.

### Import and Export Contacts
- Users can import contacts from CSV files and export their contact list to CSV.

### User Authentication and Authorization
- The application includes user registration, login, and role-based access control.

### REST API
- The application exposes RESTful endpoints for managing contacts programmatically.

### Payment Integration
- Users can make payments using Razor Pay integrated into the application.

### Rich Text Editor
- Tiny MCE is used for rich text editing in the contact notes.

### Email Notifications
- The application can send email notifications using SMTP and Gmail.

## Technologies Used

- **Backend**: Java, Spring Boot, Hibernate, REST API
- **Frontend**: Thymeleaf, JavaScript, AJAX, HTML, CSS, Bootstrap
- **Database**: MySQL
- **Payment Integration**: Razor Pay
- **Rich Text Editor**: Tiny MCE
- **Email**: SMTP, Gmail
- **Build Tool**: Maven

## Conclusion

The Smart Contact Manager is a comprehensive solution for managing contacts with modern features and integrations. By leveraging Java and Spring Boot, it provides a robust backend, while Thymeleaf and Bootstrap ensure a responsive and user-friendly frontend. The addition of REST APIs, payment integration, and email notifications make it a versatile tool for both personal and professional use.
