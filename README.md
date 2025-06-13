# Website Manager

Website Manager is a Spring Boot application designed to manage website-related configurations, including greetings, websites, and website templates. It provides RESTful APIs to perform CRUD operations on these entities, backed by a MongoDB database. The application is built with Spring Boot 3.3.5, uses MongoDB for data persistence, and supports pagination with HATEOAS.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Website Greetings](#website-greetings)
  - [Websites](#websites)
  - [Website Templates](#website-templates)
- [Testing with Postman](#testing-with-postman)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Features
- Manage website greetings with target URLs and timestamps.
- Configure websites with domains, chatbox settings, and notification preferences.
- Create and manage website templates for different business categories.
- RESTful APIs with pagination support using Spring HATEOAS.
- Data validation using Jakarta Validation annotations.
- MongoDB integration for persistent storage.
- Embedded MongoDB for testing via `de.flapdoodle.embed.mongo`.

## Technologies
- **Java**: 21 (recommended due to compatibility with Spring Boot 3.3.5)
- **Spring Boot**: 3.3.5
- **MongoDB**: 4.x or later
- **Spring Data MongoDB**: For repository operations
- **Jakarta Validation**: For input validation
- **Spring HATEOAS**: For paginated API responses
- **Maven**: Build tool
- **SLF4J**: Logging framework
- **JUnit 5**: For unit testing
- **Embedded MongoDB**: For test environment
- **Postman**: For API testing

## Prerequisites
- **Java 21**:
  - Install JDK 21 (e.g., OpenJDK or Oracle JDK).
  - Set `JAVA_HOME` environment variable:
    ```bash
    export JAVA_HOME=/path/to/jdk-21
    ```
    Example for Windows:
    ```powershell
    $env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
    ```
- **Maven**:
  - Install Maven 3.8.6 or later.
  - Verify:
    ```bash
    mvn -version
    ```
- **MongoDB**:
  - Install MongoDB 4.x or later locally, or use a cloud instance.
  - Ensure MongoDB is running on `localhost:27017` (default).
  - Alternatively, the application uses embedded MongoDB for tests.
- **Postman**:
  - Install Postman for API testing.
- **IDE** (optional):
  - IntelliJ IDEA, Eclipse, or VS Code with Java extensions.
  - For VS Code, configure `settings.json`:
    ```json
    {
      "java.configuration.updateBuildConfiguration": "automatic",
      "java.home": "C:\\Program Files\\Java\\jdk-21",
      "java.jdt.ls.java.home": "C:\\Program Files\\Java\\jdk-21"
    }
    ```

## Setup and Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/website-manager.git
   cd website-manager


Configure MongoDB:

Ensure MongoDB is running:mongod


The application uses mongodb://localhost:27017/website_database (configured in application.properties).
For tests, embedded MongoDB is provided via de.flapdoodle.embed.mongo.


Update Java Version (if using Java 24):

Previous build issues with Java 24 (Unsupported class file major version 68) require Java 21.
Update pom.xml:<properties>
  <java.version>21</java.version>
  <maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>
</properties>


Remove --enable-preview flags from pom.xml (if present).
Verify Java version:java -version




Build the Project:
mvn clean install


This compiles the code, runs tests, and packages the application.
Ensure tests pass (uses embedded MongoDB).



Running the Application

Start the Application:
mvn spring-boot:run


The application runs on http://localhost:8000.
APIs are accessible under /website-manager.


Verify:

Open http://localhost:8000/website-manager/greetings in a browser (returns 405 for GET, use Postman for proper testing).
Check logs in the console for startup details.



API Endpoints
The application exposes REST APIs under /website-manager. All endpoints return JSON responses and use HATEOAS for paginated GET requests.
Website Greetings
Manage greetings with target URLs and timestamps.

GET /website-manager/greetings/{greetingId}

Retrieves a greeting by ID.
Example: http://localhost:8000/website-manager/greetings/60c72b2f9b1e8a1f4c8b4567
Response: 200 OK, WebsiteGreetingDto


GET /website-manager/greetings?page={page}&size={size}

Lists greetings with pagination.
Example: http://localhost:8000/website-manager/greetings?page=0&size=10
Response: 200 OK, PagedModel<WebsiteGreetingDto>


POST /website-manager/greetings

Creates a greeting.
Example Request:{
  "targetUrl": "https://example.com/welcome",
  "time": "2025-06-13T07:25:00Z"
}


Response: 201 Created, WebsiteGreetingDto


PUT /website-manager/greetings

Updates a greeting.
Example Request:{
  "greetingId": "60c72b2f9b1e8a1f4c8b4567",
  "targetUrl": "https://example.com/updated-welcome",
  "time": "2025-06-13T07:55:00Z"
}


Response: 200 OK, WebsiteGreetingDto


DELETE /website-manager/greetings/{greetingId}

Deletes a greeting.
Example: http://localhost:8000/website-manager/greetings/60c72b2f9b1e8a1f4c8b4567
Response: 200 OK, "Website Greeting deleted successfully: {greetingId}"



Websites
Manage website configurations, including domains and chatbox settings.

GET /website-manager/websites/{id}

Retrieves a website by ID.
Example: http://localhost:8000/website-manager/websites/60c72b2f9b1e8a1f4c8b4570
Response: 200 OK, WebsiteDto


GET /website-manager/websites?page={page}&size={size}

Lists websites with pagination.
Example: http://localhost:8000/website-manager/websites?page=0&size=10
Response: 200 OK, PagedModel<WebsiteDto>


POST /website-manager/websites

Creates a website.
Example Request:{
  "companyId": "60c72b2f9b1e8a1f4c8b4567",
  "domain": "example.com",
  "apiToken": "abc123xyz789",
  "apiTopic": "website-events",
  "showChatboxOnMobile": true,
  "showChatboxOnDesktop": true,
  "avatarType": "BOT",
  "zapierApiKey": "zap456def012",
  "queueMessageId": "60c72b2f9b1e8a1f4c8b4568",
  "businessCategoryTemplateId": "60c72b2f9b1e8a1f4c8b4569",
  "websiteTemplateId": "60c72b2f9b1e8a1f4c8b456a",
  "eyeCatcherId": "60c72b2f9b1e8a1f4c8b456b",
  "chatWidgetId": "60c72b2f9b1e8a1f4c8b456c",
  "postAgentResponseMessageEnabled": true,
  "useGlobalNotificationSettings": false,
  "chatboxActive": true,
  "emailNotifications": true,
  "pushNotifications": false,
  "chatBotId": "60c72b2f9b1e8a1f4c8b456d",
  "notificationSettings": {
    "allEmails": "all@example.com",
    "leadEmails": "leads@example.com",
    "serviceChatEmails": "chat@example.com"
  },
  "useTheSameEmail": true,
  "leadGeneratedNotification": true,
  "serviceChatGeneratedNotification": false,
  "tags": ["60c72b2f9b1e8a1f4c8b456e", "60c72b2f9b1e8a1f4c8b456f"]
}


Response: 201 Created, WebsiteDto


PUT /website-manager/websites

Updates a website.
Example Request:{
  "id": "60c72b2f9b1e8a1f4c8b4570",
  "companyId": "60c72b2f9b1e8a1f4c8b4567",
  "domain": "updated-example.com",
  "apiToken": "new456xyz789",
  "apiTopic": "updated-events",
  "showChatboxOnMobile": false,
  "showChatboxOnDesktop": true,
  "avatarType": "HUMAN",
  "zapierApiKey": "new789def012",
  "queueMessageId": "60c72b2f9b1e8a1f4c8b4568",
  "businessCategoryTemplateId": "60c72b2f9b1e8a1f4c8b4569",
  "websiteTemplateId": "60c72b2f9b1e8a1f4c8b456a",
  "eyeCatcherId": "60c72b2f9b1e8a1f4c8b456b",
  "chatWidgetId": "60c72b2f9b1e8a1f4c8b456c",
  "postAgentResponseMessageEnabled": false,
  "useGlobalNotificationSettings": true,
  "chatboxActive": false,
  "emailNotifications": false,
  "pushNotifications": true,
  "chatBotId": "60c72b2f9b1e8a1f4c8b456d",
  "notificationSettings": {
    "allEmails": "new-all@example.com",
    "leadEmails": "new-leads@example.com",
    "serviceChatEmails": "new-chat@example.com"
  },
  "useTheSameEmail": false,
  "leadGeneratedNotification": false,
  "serviceChatGeneratedNotification": true,
  "tags": ["60c72b2f9b1e8a1f4c8b4571"]
}


Response: 200 OK, WebsiteDto


DELETE /website-manager/websites/{id}

Deletes a website.
Example: http://localhost:8000/website-manager/websites/60c72b2f9b1e8a1f4c8b4570
Response: 200 OK, "Website deleted successfully: {id}"



Website Templates
Manage website templates for business categories.

GET /website-manager/templates/{id}

Retrieves a template by ID.
Example: http://localhost:8000/website-manager/templates/60c72b2f9b1e8a1f4c8b4576
Response: 200 OK, WebsiteTemplateDto


GET /website-manager/templates?page={page}&size={size}

Lists templates with pagination.
Example: http://localhost:8000/website-manager/templates?page=0&size=10
Response: 200 OK, PagedModel<WebsiteTemplateDto>


POST /website-manager/templates

Creates a template.
Example Request:{
  "createdById": "60c72b2f9b1e8a1f4c8b4572",
  "companyId": "60c72b2f9b1e8a1f4c8b4567",
  "createdTimestamp": "2025-06-13T07:25:00Z",
  "businessCategory": "E-commerce",
  "businessSubcategory": "Retail",
  "showChatboxOnMobile": true,
  "showChatboxOnDesktop": true,
  "avatarType": "BOT",
  "postAgentResponseMessageEnabled": true,
  "useGlobalNotificationSettings": false,
  "queueMessageId": "60c72b2f9b1e8a1f4c8b4568",
  "eyeCatcherId": "60c72b2f9b1e8a1f4c8b456b",
  "chatWidgetId": "60c72b2f9b1e8a1f4c8b456c",
  "assistantId": "60c72b2f9b1e8a1f4c8b4573",
  "emailNotifications": true,
  "pushNotifications": false,
  "notificationSettings": {
    "allEmails": "template-all@example.com",
    "leadEmails": "template-leads@example.com",
    "serviceChatEmails": "template-chat@example.com"
  },
  "useTheSameEmail": true,
  "leadGeneratedNotification": true,
  "serviceChatGeneratedNotification": false,
  "tags": ["60c72b2f9b1e8a1f4c8b456e", "60c72b2f9b1e8a1f4c8b456f"],
  "smartResponses": ["60c72b2f9b1e8a1f4c8b4574", "60c72b2f9b1e8a1f4c8b4575"]
}


Response: 201 Created, WebsiteTemplateDto


PUT /website-manager/templates

Updates a template.
Example Request:{
  "id": "60c72b2f9b1e8a1f4c8b4576",
  "createdById": "60c72b2f9b1e8a1f4c8b4572",
  "companyId": "60c72b2f9b1e8a1f4c8b4567",
  "createdTimestamp": "2025-06-13T07:25:00Z",
  "businessCategory": "E-commerce",
  "businessSubcategory": "Fashion",
  "showChatboxOnMobile": false,
  "showChatboxOnDesktop": true,
  "avatarType": "HUMAN",
  "postAgentResponseMessageEnabled": false,
  "useGlobalNotificationSettings": true,
  "queueMessageId": "60c72b2f9b1e8a1f4c8b4568",
  "eyeCatcherId": "60c72b2f9b1e8a1f4c8b456b",
  "chatWidgetId": "60c72b2f9b1e8a1f4c8b456c",
  "assistantId": "60c72b2f9b1e8a1f4c8b4573",
  "emailNotifications": false,
  "pushNotifications": true,
  "notificationSettings": {
    "allEmails": "updated-template-all@example.com",
    "leadEmails": "updated-template-leads@example.com",
    "serviceChatEmails": "updated-template-chat@example.com"
  },
  "useTheSameEmail": false,
  "leadGeneratedNotification": false,
  "serviceChatGeneratedNotification": true,
  "tags": ["60c72b2f9b1e8a1f4c8b4571"],
  "smartResponses": ["60c72b2f9b1e8a1f4c8b4577"]
}


Response: 200 OK, WebsiteTemplateDto


DELETE /website-manager/templates/{id}

Deletes a template.
Example: http://localhost:8000/website-manager/templates/60c72b2f9b1e8a1f4c8b4576
Response: 200 OK, "Website Template deleted successfully: {id}"



Testing with Postman

Install Postman:

Download from Postman.


Create a Collection:

Create a new collection named WebsiteManagerAPI.


Add Requests:

For each endpoint, create a request:
Set the method (GET, POST, PUT, DELETE).
Enter the URL (e.g., http://localhost:8000/website-manager/greetings).
For POST/PUT, set Content-Type: application/json in headers and paste the sample JSON payload.
For GET/DELETE, use the appropriate URL with path or query parameters.




Test Workflow:

Create: Send POST requests to create resources (greetings, websites, templates).
Retrieve: Use GET requests to verify created resources.
Update: Send PUT requests with IDs from POST responses.
List: Use paginated GET requests to list resources.
Delete: Send DELETE requests to remove resources.


Sample Postman Requests:

Import the following collection (save as WebsiteManagerAPI.postman_collection.json):{
  "info": {
    "name": "WebsiteManagerAPI",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Website Greeting",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"targetUrl\": \"https://example.com/welcome\", \"time\": \"2025-06-13T07:25:00Z\"}"
        },
        "url": {
          "raw": "http://localhost:8000/website-manager/greetings",
          "host": ["localhost"],
          "port": "8000",
          "path": ["website-manager", "greetings"]
        }
      }
    },
    {
      "name": "Create Website",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"companyId\": \"60c72b2f9b1e8a1f4c8b4567\", \"domain\": \"example.com\", \"apiToken\": \"abc123xyz789\", \"apiTopic\": \"website-events\", \"showChatboxOnMobile\": true, \"showChatboxOnDesktop\": true, \"avatarType\": \"BOT\", \"zapierApiKey\": \"zap456def012\", \"queueMessageId\": \"60c72b2f9b1e8a1f4c8b4568\", \"businessCategoryTemplateId\": \"60c72b2f9b1e8a1f4c8b4569\", \"websiteTemplateId\": \"60c72b2f9b1e8a1f4c8b456a\", \"eyeCatcherId\": \"60c72b2f9b1e8a1f4c8b456b\", \"chatWidgetId\": \"60c72b2f9b1e8a1f4c8b456c\", \"postAgentResponseMessageEnabled\": true, \"useGlobalNotificationSettings\": false, \"chatboxActive\": true, \"emailNotifications\": true, \"pushNotifications\": false, \"chatBotId\": \"60c72b2f9b1e8a1f4c8b456d\", \"notificationSettings\": {\"allEmails\": \"all@example.com\", \"leadEmails\": \"leads@example.com\", \"serviceChatEmails\": \"chat@example.com\"}, \"useTheSameEmail\": true, \"leadGeneratedNotification\": true, \"serviceChatGeneratedNotification\": false, \"tags\": [\"60c72b2f9b1e8a1f4c8b456e\", \"60c72b2f9b1e8a1f4c8b456f\"]}"
        },
        "url": {
          "raw": "http://localhost:8000/website-manager/websites",
          "host": ["localhost"],
          "port": "8000",
          "path": ["website-manager", "websites"]
        }
      }
    },
    {
      "name": "Create Website Template",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"createdById\": \"60c72b2f9b1e8a1f4c8b4572\", \"companyId\": \"60c72b2f9b1e8a1f4c8b4567\", \"createdTimestamp\": \"2025-06-13T07:25:00Z\", \"businessCategory\": \"E-commerce\", \"businessSubcategory\": \"Retail\", \"showChatboxOnMobile\": true, \"showChatboxOnDesktop\": true, \"avatarType\": \"BOT\", \"postAgentResponseMessageEnabled\": true, \"useGlobalNotificationSettings\": false, \"queueMessageId\": \"60c72b2f9b1e8a1f4c8b4568\", \"eyeCatcherId\": \"60c72b2f9b1e8a1f4c8b456b\", \"chatWidgetId\": \"60c72b2f9b1e8a1f4c8b456c\", \"assistantId\": \"60c72b2f9b1e8a1f4c8b4573\", \"emailNotifications\": true, \"pushNotifications\": false, \"notificationSettings\": {\"allEmails\": \"template-all@example.com\", \"leadEmails\": \"template-leads@example.com\", \"serviceChatEmails\": \"template-chat@example.com\"}, \"useTheSameEmail\": true, \"leadGeneratedNotification\": true, \"serviceChatGeneratedNotification\": false, \"tags\": [\"60c72b2f9b1e8a1f4c8b456e\", \"60c72b2f9b1e8a1f4c8b456f\"], \"smartResponses\": [\"60c72b2f9b1e8a1f4c8b4574\", \"60c72b2f9b1e8a1f4c8b4575\"]}"
        },
        "url": {
          "raw": "http://localhost:8000/website-manager/templates",
          "host": ["localhost"],
          "port": "8000",
          "path": ["website-manager", "templates"]
        }
      }
    }
  ]
}


Import in Postman: File > Import > Upload the JSON file.


Run Tests:

Send requests and verify responses (e.g., 201 Created, 200 OK).
Use IDs from POST responses for PUT, GET, and DELETE requests.



Troubleshooting

Build Failure (Unsupported class file major version 68):
Cause: Java 24 is not fully supported by Spring Boot 3.3.5.
Solution: Switch to Java 21 (update pom.xml, JAVA_HOME, IDE settings).


JSON Deserialization Error:
Cause: Mismatch between JSON payload and DTO fields (e.g., Instant vs. int for time).
Solution: Ensure payloads match DTO types (use ISO 8601 for Instant).
Example Fix: For WebsiteGreetingDto, use "time": "2025-06-13T07:25:00Z".


400 Bad Request:
Cause: Missing or invalid fields (check @NotBlank, @NotNull).
Solution: Validate JSON payloads against DTOs.


404 Not Found:
Cause: Invalid ID or resource not found.
Solution: Verify IDs in MongoDB or use IDs from POST responses.


MongoDB Connection Error:
Cause: MongoDB not running or incorrect URI.
Solution: Start MongoDB (mongod) or check spring.data.mongodb.uri in application.properties.


Compilation Error in WebsiteGreeting.java:
Cause: Non-canonical constructor missing this(...) call.
Solution: Applied in WebsiteGreeting.java:public WebsiteGreeting(ObjectId greetingId2, String targetUrl2, Instant time2) {
    this(greetingId2, targetUrl2, (int) time2.getEpochSecond());
}





Contributing

Fork the repository.
Create a feature branch:git checkout -b feature/your-feature


Commit changes:git commit -m "Add your feature"


Push to the branch:git push origin feature/your-feature


Open a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.```

