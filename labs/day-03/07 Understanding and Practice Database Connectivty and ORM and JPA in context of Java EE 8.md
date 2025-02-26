### Hands-on Lab: 07 Understanding and Practice Database Connectivty and ORM and JPA in context of Java EE 8

### **Time Estimate**

**Total Time**: **15-30 minutes / per exercise**

**Note: this lab is divided into multiple exercises, which you can accomplish in any partiuclar order or your preference.**

---

### Prerequisites for all exercises

To complete any of the following exercised in this lab, ensure the following prerequisites are met:

- **Connect** to your lab machine using RDP, with the user name and password provided by instructor or lab manager.
- **IntelliJ IDEA** is installed and configured. GlassFish support is installed in IntelliJ IDEA.
- **GlassFish 5** application server is installed and configured.
- **JDK 8** is installed.
- **Maven** is installed and properly configured.
- **The repository Java-EE-8-Application-Development** is cloned on your lab machine.

In case of any of this are not met, please refer to the **Prerequisites**, and **Step 0** in [day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.

Special requirements for each exercise relating database access:

- **The CustomerDb database** is created and configured in GlassFish as per the instructions in the previous labs. Refer to ([day three, lab 06 Practicing with the Customer Database](../day-03/06%20Practicing%20with%20the%20Customer%20Database.md)) for guidance.

### Lab Objective

- Explore and practice database connectivity, ORM, and JPA in the context of Java EE 8.
- Learn manual transaction management in Java EE 8 (jpa_intro project).
- Understand relationship mapping in JPA (entityrelationship project).
- Work with composite primary keys in JPA (compositeprimarykeys project).
- Get familiar with JPQL (jpql project).
- Understand the concepts and usage of the Criteria API in JPA, including criteria updates and deletions (criteriaapi, criteriaupdate, criteriadelete projects).
- Learn entity validation techniques (beanvalidation project).

### **Exercise 1: Database Connectivity and ORM**

#### **Based on what the presentation discussed on this topic, try to answer the following questions:**

- What is an ER model, and why is it important in database design?
- Can you explain what a Primary Key (PK) is and why it's crucial in a database?
- What exactly is a Foreign Key (FK), and how does it relate to other tables?
- What is a connection pool, and how does it help improve application performance?
- What is a datasource, and how does it simplify working with databases?
- Why do we need `persistence.xml`, and what role does it play in JPA?

---

### **Exercise 2: Manual Transaction Management**

Open the `jpa_intro` project in chapter 3 from existing source, and configure it to use with Glassfish. Configure the project to demonstrate manual transaction management in Java EE 8, focusing on controlling transactions programmatically.

Run or debug the project and observe the output. Analyze the code in the `JpaDemoBean` class to understand how transactions are managed manually in a Java EE application. Reiew the changed data in the database using IntellJ database tools, to see the effects of the transactions.

#### **Think about the learned concepts and try to answer the following questions:**

- What is the purpose of the `JpaDemoBean` class in the project?
- What is the role of the `@Named` annotation in the `JpaDemoBean` class?
- Why is the `@RequestScoped` annotation used in the `JpaDemoBean` class?
- How does the `@PersistenceContext` annotation interact with the `EntityManager`?
- What is the function of the `UserTransaction` resource in this class?
- How does the `updateDatabase` method manage database transactions?
- What entities are manipulated in the `updateDatabase` method?
- What exception handling mechanism is implemented in the `updateDatabase` method?
- Why is the `entityManager.remove(customer)` call made, and what is its purpose?
- How does this class demonstrate the use of JPA for database persistence and retrieval?

---

### **Exercise 3: Relationship Mapping in JPA**

Open the `entityrelationship` project in chapter 3 from existing source, and configure it to use with Glassfish. Analyze and configure various relationship mappings in JPA, such as one-to-one, one-to-many, and many-to-many associations.

Run or debug the project and observe the output. Review the code in the `Customer`, `LoginInfo`, `Order`, and `Item` entities to understand how relationships are defined and managed in JPA.

#### **Think about the learned concepts and try to answer the following questions:**

- How is the `@OneToOne` relationship between `Customer` and `LoginInfo` defined in the `Customer` entity?
- What does the `@ManyToMany` annotation in the `Order` and `Item` entities signify, and how is the relationship configured?
- How is the `@OneToMany` relationship between `Customer` and `Order` entities modeled, and what does the `mappedBy` attribute indicate?
- What role does the `@JoinTable` annotation play in the `Order` entity for the `items` field?
- How does the `@ManyToOne` relationship between `Order` and `Customer` entities work, and how is the foreign key defined?

---

### **Exercise 4: Composite Primary Keys in JPA**

Open the `compositeprimarykeys` project in chapter 3 from existing source, and configure it to use with Glassfish. Implement and configure composite primary keys using annotations in JPA, and observe their impact on entity behavior.

Run or debug the project and observe the output. Review the code in the `OrderItem` and `OrderItemPK` classes to understand how composite primary keys are defined and used in JPA entities.

#### **Think about the learned concepts and try to answer the following questions:**

- How does the `@IdClass` annotation in the `OrderItem` class define a composite primary key?
- What is the purpose of the `OrderItemPK` class, and how does it support the `OrderItem` entity?
- How does the `EntityManager.find()` method handle composite primary keys in the `CompositePrimaryKeyDemoBean` class?
- What role do the `equals()` and `hashCode()` methods play in the `OrderItemPK` class?
- Why is the `@PersistenceContext` annotation used in the `CompositePrimaryKeyDemoBean` class?

---

### **Exercise 5: JPQL (Java Persistence Query Language)**

Open the `jpql` project in chapter 3 from existing source, and configure it to use with Glassfish. Practice creating and executing JPQL queries to retrieve and manipulate data stored in the database through JPA entities.

Run or debug the project and observe the output. Review the code in the `UsState` entity and `SelectQueryDemoBean` class to understand how JPQL queries are constructed and executed in Java EE applications.

#### **Think about the learned concepts and try to answer the following questions:**

- In the `UsState` class, how is the `@Column` annotation used to map Java fields to database columns? What happens if the `name` attribute is omitted?
- In the `SelectQueryDemoBean` class, how does the JPQL query `SELECT s FROM UsState s WHERE s.usStateNm LIKE :name` work, and what is the significance of the `:name` parameter?
- How does the `@PersistenceContext` annotation ensure that the `EntityManager` is properly injected and managed in the `SelectQueryDemoBean` class?
- The `findStates` method catches exceptions but only prints the stack trace. How would you improve error handling in this method for better user feedback and system reliability?
- The method `findStates` uses `Stream<UsState>` and `Collectors.toList()`. What are the benefits of using streams for JPQL query results? Would using `getResultList()` directly instead of `getResultStream()` be better in this case?
- What are the advantages and disadvantages of dynamic queries compared to named queries defined using `@NamedQuery`?

---

### **Exercise 6: Criteria API in JPA**

Open the `criteriaapi`, `criteriaupdate`, and `criteriadelete` project folders. Explore and configure projects to demonstrate how to use the Criteria API for dynamic query construction, as well as update and delete operations.

Run or debug the projects and observe the output. Review the code in the `UsState` entity and `CriteriaApiDemoBean` class to understand how the Criteria API is used to build queries and perform database operations in Java EE applications.

#### **Think about the learned concepts and try to answer the following questions:**

- How does the `@Table` annotation in the `UsState` class define the relationship between the entity and the database table, and what would happen if it were omitted?
- In the `CriteriaApiDemoBean` class, how does the `CriteriaBuilder` object assist in creating a dynamic query for fetching states based on their names?
- What is the role of the `Metamodel` and `SingularAttribute` API in the `findStates` method, and how does it enhance type safety and query structure?
- In the `findStates` method, why is the `getResultStream()` used for query results, and how does it differ from using `getResultList()`?
- What are the potential benefits of using the Criteria API over a JPQL query in the context of the `findStates` method, especially for dynamic query construction?

---

### **Exercise 7: Entity Validation**

Open the `beanvalidation` project in chapter 3 from existing source, and configure it to use with Glassfish. Set up and implement validation rules using JPA and Bean Validation annotations to enforce constraints on entity fields.

Run or debug the project and observe the output. Review the code in the `Customer` entity and `JpaBeanValidationDemoBean` class to understand how entity validation is performed in Java EE applications.

#### **Think about the learned concepts and try to answer the following questions:**

- How does the `@NotNull` and `@Size` annotations in the `Customer` entity ensure validation, and what happens if a value violates these constraints during persistence?
- In the `JpaBeanValidationDemoBean` class, how does the `beanValidationDemo` method handle validation failures when persisting the `Customer` entity with invalid data?
- What is the purpose of using `UserTransaction` in the `JpaBeanValidationDemoBean` class, and how does it manage the transaction lifecycle during database operations?
- Why does the code attempt to persist a `Customer` entity with a null `firstName`, and what specific exception is thrown due to this violation?
- What role does `ConstraintViolationException` play in handling validation errors in the `JpaBeanValidationDemoBean` class, and how could the error handling be improved for better user feedback?

## **Answers**

### **Answers for Exercise 1: Database Connectivity and ORM**

- An ER (Entity-Relationship) model is a conceptual representation of data that defines the entities, their attributes, and the relationships between them in a database. It is important because it provides a clear and structured blueprint for designing and understanding a database's schema.
- A Primary Key (PK) is a unique identifier for a record in a database table. It is crucial because it ensures each record is distinct and can be efficiently retrieved, updated, or referenced by other tables.
- A Foreign Key (FK) is a field in one table that establishes a relationship with the Primary Key in another table. It enforces referential integrity, ensuring that the relationship between the tables is consistent and valid.
- A connection pool is a cache of database connections maintained so that they can be reused for future requests. It improves application performance by reducing the overhead of creating and closing database connections repeatedly.
- A datasource is an abstraction that provides a standardized way to connect to a database. It simplifies working with databases by managing connection details and pooling behind the scenes, making it easier for applications to access and interact with the database.
- The `persistence.xml` file is a configuration file required by JPA (Java Persistence API). It defines the persistence unit, which includes settings such as the database connection details, entity classes, and other JPA-related properties, enabling the ORM (Object-Relational Mapping) framework to manage entities and transactions.

---

### **Answers for Exercise 2: Manual Transaction Management**

- What is the purpose of the `JpaDemoBean` class in the project?  
  The `JpaDemoBean` class is a managed bean used to demonstrate JPA functionalities, such as persisting, retrieving, updating, and deleting `Customer` entities in the database.
- What is the role of the `@Named` annotation in the `JpaDemoBean` class?  
  The `@Named` annotation makes the `JpaDemoBean` accessible in EL (Expression Language) expressions, allowing it to be used in JSF (JavaServer Faces) pages.
- Why is the `@RequestScoped` annotation used in the `JpaDemoBean` class?  
  The `@RequestScoped` annotation ensures that the bean is created and destroyed with each HTTP request, making it suitable for short-lived operations like database updates triggered by user actions.
- How does the `@PersistenceContext` annotation interact with the `EntityManager`?  
  The `@PersistenceContext` annotation injects an `EntityManager` instance, which is used to interact with the persistence context for performing CRUD operations on entities.
- What is the function of the `UserTransaction` resource in this class?  
  The `UserTransaction` resource manages transactions explicitly. It allows the bean to define transaction boundaries (e.g., begin and commit transactions) instead of relying on container-managed transactions.
- How does the `updateDatabase` method manage database transactions?  
  The `updateDatabase` method begins a transaction using `userTransaction.begin()`, performs multiple database operations (persist, find, update, and remove), and then commits the transaction using `userTransaction.commit()`.
- What entities are manipulated in the `updateDatabase` method?  
  The `updateDatabase` method manipulates three `Customer` entities:

  - `customer`: Created and persisted, but then removed later in the transaction.
  - `customer2`: Created and persisted.
  - `customer3`: Retrieved using `entityManager.find()` and then updated.

- What exception handling mechanism is implemented in the `updateDatabase` method?  
  The method handles various exceptions (`HeuristicMixedException`, `HeuristicRollbackException`, `IllegalStateException`, `NotSupportedException`, `RollbackException`, `SecurityException`, and `SystemException`) to catch transaction-related issues and ensure stability. If an exception occurs, the method logs the error and returns an error state ("error").
- Why is the `entityManager.remove(customer)` call made, and what is its purpose?  
  The `entityManager.remove(customer)` call is used to delete the `customer` entity from the database as part of demonstrating the `remove` operation within a transaction.
- How does this class demonstrate the use of JPA for database persistence and retrieval?  
  This class shows how to use JPA to:

  - Persist entities using `entityManager.persist()`.
  - Retrieve an entity by its primary key using `entityManager.find()`.
  - Update an entity by modifying its fields within a transaction.
  - Remove an entity using `entityManager.remove()`.
  - Handle database operations within explicit transaction boundaries using `UserTransaction`.

---

### **Answers for Exercise 3: Relationship Mapping in JPA**

- How is the `@OneToOne` relationship between `Customer` and `LoginInfo` defined in the `Customer` entity?  
  The `@OneToOne` relationship is defined in the `Customer` entity using the `@OneToOne(mappedBy = "customer")` annotation, where `mappedBy` indicates that the `customer` field in the `LoginInfo` entity owns the relationship. This means the `LoginInfo` entity contains the foreign key column for the relationship.
- What does the `@ManyToMany` annotation in the `Order` and `Item` entities signify, and how is the relationship configured?  
  The `@ManyToMany` annotation signifies a many-to-many relationship between `Order` and `Item`. In the `Order` entity, the relationship is configured using the `@JoinTable` annotation, which defines the join table `ORDER_ITEMS` and specifies `ORDER_ID` and `ITEM_ID` as the foreign key columns. The `mappedBy` attribute in the `Item` entity indicates that the relationship is owned by the `Order` entity.
- How is the `@OneToMany` relationship between `Customer` and `Order` entities modeled, and what does the `mappedBy` attribute indicate?  
  The `@OneToMany` relationship is modeled in the `Customer` entity with the annotation `@OneToMany(mappedBy = "customer")`. The `mappedBy` attribute specifies that the `customer` field in the `Order` entity owns the relationship, meaning the `Order` entity contains the foreign key column `CUSTOMER_ID`.
- What role does the `@JoinTable` annotation play in the `Order` entity for the `items` field?  
  The `@JoinTable` annotation in the `Order` entity defines the join table `ORDER_ITEMS` that facilitates the many-to-many relationship between `Order` and `Item`. It specifies how the foreign key columns (`ORDER_ID` and `ITEM_ID`) map the relationship between the two entities.
- How does the `@ManyToOne` relationship between `Order` and `Customer` entities work, and how is the foreign key defined?  
  The `@ManyToOne` relationship in the `Order` entity is defined using the `@ManyToOne` annotation and the `@JoinColumn(name = "CUSTOMER_ID")` annotation. This indicates that each `Order` is associated with one `Customer`, and the foreign key `CUSTOMER_ID` in the `ORDERS` table references the primary key of the `CUSTOMERS` table.

---

### **Answers for Exercise 4: Composite Primary Keys in JPA**

- How does the `@IdClass` annotation in the `OrderItem` class define a composite primary key?  
  The `@IdClass` annotation in the `OrderItem` class specifies that the primary key consists of multiple fields (`orderId` and `itemId`). The annotation links the `OrderItem` class to the `OrderItemPK` class, which provides a composite key definition.
- What is the purpose of the `OrderItemPK` class, and how does it support the `OrderItem` entity?  
  The `OrderItemPK` class serves as the composite primary key for the `OrderItem` entity. It implements the `Serializable` interface and defines the fields `orderId` and `itemId`. It includes `equals()` and `hashCode()` methods to ensure the correct behavior of the composite key during persistence operations.
- How does the `EntityManager.find()` method handle composite primary keys in the `CompositePrimaryKeyDemoBean` class?  
  The `EntityManager.find()` method is used to retrieve an `OrderItem` entity based on its composite primary key. The key is represented by an instance of `OrderItemPK`, which is passed as the second argument to the `find()` method.
- What role do the `equals()` and `hashCode()` methods play in the `OrderItemPK` class?  
  The `equals()` and `hashCode()` methods ensure that instances of `OrderItemPK` are compared correctly based on their field values (`orderId` and `itemId`). These methods are crucial for JPA to identify entities with composite primary keys in collections or when performing operations like `find()` or `merge()`.
- Why is the `@PersistenceContext` annotation used in the `CompositePrimaryKeyDemoBean` class?  
  The `@PersistenceContext` annotation is used to inject an `EntityManager` instance into the `CompositePrimaryKeyDemoBean` class. This allows the bean to interact with the persistence context for performing database operations, such as retrieving an entity using a composite primary key.

---

### **Answers for Exercise 5: JPQL (Java Persistence Query Language)**

- The `@Column` annotation in the `UsState` class maps Java fields to database columns. If the `name` attribute is omitted, JPA defaults to using the field name (e.g., `usStateId`) as the column name, which may lead to mismatches if the database schema uses different names.
- The JPQL query `SELECT s FROM UsState s WHERE s.usStateNm LIKE :name` retrieves all `UsState` entities where the `usStateNm` field matches the pattern specified by the `:name` parameter. The `:name` parameter acts as a placeholder replaced at runtime with a specific value (e.g., `"New%"`), enabling dynamic filtering.
- The `@PersistenceContext` annotation ensures the `EntityManager` is injected by the container, automatically configuring it with the correct persistence context. This avoids manual management of the `EntityManager`, ensuring lifecycle and transactional consistency.
- The `findStates` method's error handling can be improved by using a logging framework to log the error instead of just printing the stack trace. Additionally, returning a more descriptive error message or wrapping the exception in a custom exception class can provide better feedback to the user.
- Using `Stream<UsState>` allows lazy processing of query results, which can be more memory-efficient for large datasets. However, since the results are eventually converted to a list, using `getResultList()` directly might be simpler and more efficient unless additional stream operations are required.
- Dynamic queries offer flexibility since they can be constructed at runtime, but they are more error-prone and harder to maintain compared to named queries. Named queries, defined with `@NamedQuery`, are pre-compiled, making them safer and easier to manage but less flexible.

---

### **Answers for Exercise 6: Criteria API in JPA**

- The `@Table` annotation in the `UsState` class specifies the database table (`US_STATES`) that the entity maps to. If the annotation is omitted, JPA assumes the table name is the same as the class name (`UsState`), which could lead to errors if the actual table name in the database is different.
- The `CriteriaBuilder` object in the `CriteriaApiDemoBean` class provides a programmatic way to construct queries dynamically. It helps build a type-safe query by specifying entity types, conditions (`WHERE` clause), and query structure without writing raw JPQL or SQL.
- The `Metamodel` and `SingularAttribute` API provide a strongly-typed way to reference entity attributes in queries. In the `findStates` method, the `SingularAttribute` ensures that `usStateNm` is correctly mapped to its type (`String`). This approach improves type safety, reduces runtime errors, and ensures the query is valid.
- The `getResultStream()` method lazily fetches query results as a stream, which is useful for large datasets or further processing (e.g., filtering, mapping). In contrast, `getResultList()` retrieves all results eagerly as a list. Using `getResultStream()` can be more memory-efficient but might not be necessary if the results are converted to a list anyway.
- The Criteria API provides several benefits over JPQL for dynamic query construction:

  - Type safety: Errors are caught at compile time instead of runtime.
  - Flexibility: Queries can be built dynamically at runtime based on conditions.
  - Maintainability: Reduces string-based query errors and allows queries to adapt to changes in entity structure automatically.

---

### **Answers for Exercise 7: Entity Validation**

- The `@NotNull` annotation ensures that a field cannot be null, and the `@Size` annotation enforces a minimum and maximum length for string fields. During persistence, if these constraints are violated, a `ConstraintViolationException` is thrown, preventing the entity from being saved to the database.
- In the `beanValidationDemo` method, validation failures are caught by the `ConstraintViolationException` block. If a `Customer` entity with invalid data is persisted, the exception is thrown, the transaction fails, and the code prints the stack trace while returning "error" as the outcome.
- The `UserTransaction` is used to explicitly manage the transaction lifecycle. It starts the transaction with `userTransaction.begin()` and commits it with `userTransaction.commit()`. If an exception occurs, the transaction can be rolled back to ensure database consistency.
- The code attempts to persist a `Customer` entity with a null `firstName` to demonstrate bean validation. The violation of the `@NotNull` constraint triggers a `ConstraintViolationException`, indicating that a required field is missing.
- The `ConstraintViolationException` identifies and handles validation errors during persistence. However, the current error handling could be improved by:

  - Logging the errors using a logging framework instead of printing the stack trace.
  - Providing a detailed and user-friendly error message that specifies which constraints were violated.
  - Optionally rolling back the transaction explicitly when validation fails.
