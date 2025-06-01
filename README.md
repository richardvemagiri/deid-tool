# DeID Tool

DeID Tool is a Java-based application with a web UI that allows users to de-identify C-CDA XML documents by replacing personally identifiable information (PII) with static text. This helps anonymize healthcare data in compliance with HIPAA.

## Key Features
* **C-CDA XML De-identification**: Replaces PII in clinical document architecture (C-CDA) XML files based on XPaths defined in the database.
* **Multiple Input Options**: Users can either upload a C-CDA XML file or enter XML text manually.
* **PII Masking**: Sensitive data is replaced with static placeholders, ensuring full anonymization. These static placeholders are defined in the database based on what's allowed as per the [HL7 Implementation Guide](https://www.hl7.org/implement/standards/product_brief.cfm?product_id=492), and can be changed as needed.
* **De-identified Output**: The de-identified C-CDA XML can be downloaded once the process is complete.
* **Database Integration**: Uses a relational database to store configurations.

# Project Setup
Clone the repository and install dependencies:

```
git clone https://github.com/richardvemagiri/deid-tool.git
cd deid-tool
mvn clean install
```

# Project Config

## Profiles
The properties are picked from the appropriate `.properties file` based on the profile mentioned in `application.properties` for the property `spring.profiles.active`,i.e., if `application.properties` has `spring.profiles.active=dev`, the application will look into `application-dev.properties` for the necessary properties.

## Azure Config
This application supports Entra ID (Azure AD). To enable this, `spring.cloud.azure.active-directory.enabled` should be set to `true`.
Also, tenant-id, client-id and client-secret are needed. These details can be obtained from your app registered on Entra ID (Azure AD) portal.
```
spring.cloud.azure.active-directory.enabled=true

spring.cloud.azure.active-directory.profile.tenant-id=${AzureAD.TenantID}
spring.cloud.azure.active-directory.credential.client-id=${AzureAD.ClientID}
spring.cloud.azure.active-directory.credential.client-secret=${AzureAD.ClientSecret}
```

If AD login is not needed, the following properties must be set as shown below.
```
spring.cloud.azure.active-directory.enabled=false
spring.autoconfigure.exclude[0]=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

## Database Configuration
You must specify your database connection details in the `application-*.properties` file. The `spring.datasource` properties should be configured with the appropriate database details. Here's an example for a MySQL database.

```
spring.datasource.url=jdbc:mysql://localhost:3306/deid_tool?createDatabaseIfNotExist=true
spring.datasource.username=db_user
spring.datasource.password=db_password
```

### Database Initialization
This project includes .sql files located in the resources/sql folder. These files contain necessary SQL statements to create and populate the database tables with the necessary configuration data. 
If you want the DB schema and data to be loaded everytime the server starts, you must specify the database initialization details in the `application-*.properties`. The `spring.sql.init` properties should be configured as shown below for the database initialization to happen automatically once the application starts.
```
spring.jpa.hibernate.ddl-auto=none
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:sql/deidconfig_schema.sql, classpath:sql/deidconfig_data.sql
```
If loading DB schema and data is not needed everytime the app starts, the `.sql` files can be run separately as a one-time setup.

## Logging Config

The following properties are used to control logging behavior. These properties must be available in `application-*.properties` and can be changed to suit the environment and logging requirements.
```
logging.level.root=INFO
logging.level.com.ecw.deidtool=INFO
logging.file.name=logs/deid-tool.log
logging.file.max-size=50MB
logging.file.max-history=10
```
If DB queries need to be logged, the following property is needed in `application-*.properties`.
```
spring.jpa.show-sql=true
```
# Building the Application
To build the application, use the below command. This will generate a `war` file to be deployed in an external server like Tomcat, etc. If a `jar` is needed instead, set `<packaging>jar</packaging>` in `pom.xml`.
```
mvn clean package
```

# Running the Application
To run the application locally, use the below command.
```
mvn spring-boot:run
```
Once the application starts, you can access the user interface by opening your web browser and navigating to:
```
http://localhost:2020/deid-tool
```

To deploy and run the application on an external server like Tomcat, place the war file in the appropriate server directory and start the server.
Eg: In the case of a Tomcat server, place the `deid-tool.war` file in `webapps` folder of Tomcat, and run the Tomcat server. Tomcat will automatically pick the war file and the application should be accessible via the Tomcat port.

# Usage
## UI Workflow
1. **Upload or Enter C-CDA XML**:
   * **Upload a file**: Select a C-CDA XML file from your computer.
   * **Enter XML text**: Paste the C-CDA XML directly into the provided text area. 
2. **De-identification**:
   After submitting the XML file of text, the application processes the data, replacing all PII (e.g., names, addresses, phone numbers, etc.) with static placeholders. 
3. **Download**:
   Once the de-identification process is complete, the de-identified (anonymized) C-CDA XML can be accessed directly from the UI. In case of XML upload/download flow, the uploaded (original) XML is not stored on the server. The downloadable (de-identified) XML is temporarily stored on the server.

## Example Workflow
* Input XML:
```
<given>Smith</given>
```
* After de-identification:
```
<given>REDACTED</given>
```

# Maintenance
* **Logs**: The max size for log files is 50MB by default, and the max file size is 10 by default. These properties are available in `application-*.properties` and can be changed to suit the environment and logging requirements.
* **De-Identified files**: The de-identified XML files are stored either in a temp folder (non Azure flow) or in the principal name folder (Azure flow). These folders are deleted everytime the application/server is restarted.

## Learn More

📝 **Blog Article**: 
[Anonymizing Healthcare Data: Building a DeID Tool for C-CDA XML Documents](https://richardvemagiri.hashnode.dev/ccda-deid-tool)

This comprehensive article covers:
- The motivation behind building this tool
- Technical implementation details
- Usage examples