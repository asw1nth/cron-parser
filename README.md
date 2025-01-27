# Cron Expression Parser

## Overview

This project is a Java-based Cron Expression Parser, which parses and validates cron expressions according to standard cron syntax. The parser is capable of interpreting cron fields, including ranges, steps, wildcards, and lists, and provides detailed outputs of the parsed cron schedule.

## Dependencies

This project uses the following dependencies:

```xml
 <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### Installation

To get started with the `cron-parser` project, follow these steps:

1. **Clone** the repository:

    ```bash
    git clone https://github.com/asw1nth/cron-parser.git
    ```
    ```bash
    cd cron-parser
    ```

2. **Build** the project using Maven:

    ```bash
    mvn clean install
    ```
   
This will create a jar file in the target directory.



##Usage:

The cron-parser application allows you to parse cron expressions. Provide a valid cron expression as a string argument when running the program. The cron expression must consist of five fields representing:

- Minutes (0 - 59)
- Hours (0 - 23)
- Day of the Month (1 - 31)
- Month (1 - 12)
- Day of the Week (1 - 7)
- Command to execute

The program will output each field's parsed values, formatted for easy reading.

## Running Tests
To run the unit tests for this project, use the following Maven command:

```bash
mvn test
```
This will execute all the tests and provide a summary of the test results.

## IDE

Run Main.java method with following input:

#### Input:
```bash
"*/15 0 1-7/2 * 1 /usr/bin/find"
```

#### Output:
```bash
minute        0 15 30 45 
hour          0 
day of month  1 3 5 7 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 
command       /usr/bin/find
```

More details of different functional tests are shared in TestCases.md file
