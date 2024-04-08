# Localized Employee Management Application

## Overview
JavaFx application to add employees to a database.
## Localization Strategy
The application utilizes row localization to support multiple languages. This approach involves specifying a language code for each object in the employee table.

## Localization Details
Each supported language has a corresponding database table with the following schema:


```sql
CREATE TABLE employee (
 languageCode VARHCHAR(50),
 first_name VARCHAR(50),
 last_name VARCHAR(50),
 email VARCHAR(100)
);
```
The localization is implemented via row localization.
