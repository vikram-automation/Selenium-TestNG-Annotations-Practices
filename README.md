# TestNG Annotations Hierarchy & Practices

This repository covers the practical implementation and execution order of **TestNG Annotations** in Selenium Automation. Understanding the hierarchy of these annotations is crucial for managing pre-requisites and post-execution activities efficiently.

---

## 🧭 TestNG Annotations Execution Order

When running a TestNG suite, the annotations execute in a strict hierarchical order. Here is the flow implemented in this project:


1. **`@BeforeTest`**: Runs before any test section inside the `<test>` tag.
2. **`@BeforeClass`**: Runs before the first test method in the current class.
3. **`=>> @BeforeMethod`**: Runs before **every** `@Test` method.
4. **`   [ @Test ] `**: The actual test case execution.
5. **`=>> @AfterMethod`**: Runs after **every** `@Test` method.
6. **`@AfterClass`**: Runs after all test methods in the current class have executed.

---

## 🛠️ Features Implemented

* **TestNG Annotations**: Structured setup and teardown activities using `@BeforeMethod`, `@AfterMethod`, and `@Test`.
* **Prioritization**: Managing the exact execution sequence of test cases using `priority`.
* **Assertions**: Hard and soft assertions to validate page URLs, titles, and element visibility.
* **Clean Architecture**: Segregated test classes for better readability and maintenance.

---

## 🛠️ Key Concepts Practiced

* **Setup & Teardown**: Managing browser initialization (`@BeforeMethod`) and quitting drivers (`@AfterMethod`).
* **Suite-Level Configurations**: Handling database or report initialization at the suite level.
* **Execution Flow Analysis**: Observing how TestNG controls the test lifecycle without manual ordering.

---

## 🏃 How to Run

1. Open the project in IntelliJ IDEA.
2. Run any annotation test class or execute via terminal:
   ```bash
   mvn test