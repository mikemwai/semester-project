## Interface @ Michael

Let's get it started.

## 1. Creation of interfaces and classes
You are responsible for how data moves in the whole system.

Venoliah will give you data(or you may give her a class to populate with data) which you will then
forward to Precious

An example is a doctors class.
```java
import java.util.Date;

class Doctors {
    // fields from which we will be reading our data
    public  String name;
    public  Date dob;
    
    Doctors(String name, Date dob){
        this.name = name;
        this.dob = dob;
    }
    
    // e.g a method Precious may require to write to a Database
    // may look like
    public String formatForDb(){
        // 
        return "INSERT INTO DoctorsTable VALUES (" + this.name +"," + this.dob + ")";
        // you will discuss with Precious how these data interpolates with the interface
    }
}
```

## 2. Translation between interfaces.

You and Venoliah will discuss how you will get data from the GUI and give it to Precious Db and 

You and Precious will discuss how to get data from the Db and pass it to Venoliah



Good luck Master.