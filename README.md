# SauceDemo BDD Testing Project

This project implements Behavior-Driven Development (BDD) testing for the SauceDemo e-commerce website using Cucumber, Selenium WebDriver, and TestNG.

## Features Tested

### 1. Authentication
- User login with valid credentials
- Login failure with invalid credentials

### 2. Product Sorting
- Sort products by price (low to high)
- Sort products by price (high to low)
- Negative test for invalid sort options

### 3. Shopping Cart Management
- Add single product to cart
- Add multiple products to cart
- Negative test for adding non-existent product
- Reset app state before logout (clears cart state)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/kurniadi/cucumber/
│   │       ├── drivers/
│   │       │   ├── DriverSingleton.java (Singleton pattern for WebDriver)
│   │       │   └── strategies/
│   │       │       ├── Chrome.java
│   │       │       ├── Firefox.java
│   │       │       ├── DriverStrategy.java (Interface)
│   │       │       └── DriverStrategyImplementer.java
│   │       ├── pages/
│   │       │   ├── InventoryPage.java (Inventory & Cart operations)
│   │       │   └── LoginPage.java (Login operations)
│   │       └── utils/
│   │           └── Constants.java (URLs and credentials)
│   └── resources/
│       └── features/
│           ├── AddToCart.feature (Add to cart scenarios)
│           ├── Sorting.feature (Product sorting scenarios)
│           └── Login.feature (Authentication scenarios)
└── test/
    └── java/
        └── com/juaracoding/
            ├── AddToCartTest.java (Add to cart step definitions)
            ├── SortingTest.java (Product sorting step definitions)
            ├── LoginTest.java (Login and common step definitions)
            └── TestRunner.java (Cucumber test runner)
```

## Test Scenarios

### Login Feature (Login.feature)
- **Positive Test**: Successful login with valid credentials (standard_user / secret_sauce)
- **Negative Test**: Login failure with invalid credentials

### Product Sorting Feature (Sorting.feature)
- **Positive Test 1**: Sort products by price low to high
- **Positive Test 2**: Sort products by price high to low
- **Negative Test**: Invalid sort option handling
- All scenarios include reset app state before logout

### Add to Cart Feature (AddToCart.feature)
- **Positive Test 1**: Add single product to cart (Sauce Labs Backpack)
- **Positive Test 2**: Add all products to cart (6 products)
- **Negative Test**: Add non-existent product (cart remains unchanged)
- Positive tests include reset app state before logout

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Chrome or Firefox browser
- WebDriver for selected browser (ChromeDriver or GeckoDriver)

## Setup

1. Clone or download the project
2. Navigate to the project directory
3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=TestRunner
```

### Run tests with detailed output
```bash
mvn test -X
```

## Test Execution Flow

Each test scenario follows this pattern:
1. **Given**: Navigate to login page
2. **When**: Enter credentials and click login
3. **When**: Perform action (sort products / add to cart)
4. **Then**: Verify expected result
5. **And**: Reset app state (clears changes from inventory)
6. **And**: Logout (returns to login page)

The reset app state step ensures clean state between tests by clearing the cart and any applied filters.

## Reports

Test reports are generated in:
- `target/cucumber-report.html` - Cucumber HTML report
- `target/surefire-reports/` - TestNG reports with details
- `target/cucumber.json` - Cucumber JSON report for CI/CD integration

## Documentation & Traceability

Detailed test cases and execution results can be found in the System Integration Testing (SIT) document:
- 📑 **[SIT Test Cases & Execution Report](https://docs.google.com/spreadsheets/d/18dvOcYLqLHMY9V1MUqPc7gGYaX9_6Fr4/edit?usp=drive_link&ouid=102556497230646308513&rtpof=true&sd=true)**

## Key Technologies

| Technology | Version | Purpose |
|---|---|---|
| Selenium WebDriver | 4.43.0 | Browser automation |
| Cucumber | 7.34.3 | BDD framework |
| TestNG | 7.12.0 | Test execution |
| ExtentReports | 5.1.2 | Advanced reporting |
| Java | 17 | Programming language |

## Design Patterns Used

1. **Page Object Model (POM)**: Separates test logic from page interactions
2. **Singleton Pattern**: Single instance of WebDriver across tests
3. **Strategy Pattern**: Different browser implementations (Chrome/Firefox)

## Test Data

- **Valid Credentials**: standard_user / secret_sauce
- **Base URL**: https://www.saucedemo.com/
- **Browser**: Firefox (configurable)</content>