# Ecommerce Tests in Selenium Webdriver (Java) - Julio Guerra

## Overview
This project demonstrates the usage of Selenium WebDriver with Java for automated testing. It includes test cases for creating an order on an e-commerce website. 
The test cases cover the following actions: 
- Create an Account
- Login
- Select product along with quantity, size and color
- Fill billing and shipping information
- Payment Checkout
- Success Checkout

## Classes
### Create Order Test
Test Description: This test case automates the complete process of creating an order on the e-commerce website.

### Base Test
Description: The base test class initializes the WebDriver and provides common methods for all test cases.

## Getting Started
### Prerequisites
- Java Development Kit (JDK)
- TestNG
- Selenium WebDriver
- WebDriverManager
- JavaFaker
- Browser Drivers (Chrome, Firefox, Edge)

## Installation
Clone this repository to your local machine:

```
git clone git@github.com:juliojguerra/evershop-selenium-tests.git
```

To install dependencies, run the following command: `mvn clean install`

## Usage
- Configure test data in the CreateOrderTest.java file in the getData method.
- Run the main test case with TestNG. Right click `testng.xml` file and click on Run.
- URL and Browser can be setup in the config.properties file (path: `src/main/java/evershop/resources/config.properties`)

## Test Data
Test data is provided in a data provider method within CreateOrderTest.java. 
Later on, this data will be migrated to an external JSON.


