# Database @ Precious 

I've already created a Database class for you, with some code that
connects to a real database.

The work left there is 

## 1. Define Database tables
Look for the below code, and then learn how to write `SQL statements` that translate
what we will discuss in a later meeeting to be our table format
```java

static String MainHospitalDbFormat = "CREATE TABLE IF NOT EXISTS Hospital";

static String PatientsDbFormat = "CREATE TABLE IF NOT EXISTS Patients ()";
```


## 2. Define readers and writers to the database(or getters and setters)
We will need to be able to read data from the database and for that we need you to again use
SQL statements that can allow us to get data from the database.

The syntax of the SQL statement will be 
```roomsql
SELECT * FROM (A TABLE) WHERE (SOME CONDITION);
```

The java interface will look like

```java
Connection conn;

conn.createStatement().executeQuery();
```

## 3. Map Data from SQL statements to a higher order instance
You should also map the returned data from the SQL statement to a higher order
data representation , e.g a `class` in the spirit of OOP.

You should also take this higher order instance and be able to convert it
into SQL statements.

## 4. Provide a way for error handling
Create a way to handle database errors e.g an error when data is incomplete.

I've set up a rudimentary one where errors are `logged` and rethrown.






I think that's all With Love 🧡🧡 @ CAE.