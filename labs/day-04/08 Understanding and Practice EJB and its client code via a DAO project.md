### Hands-on Lab: Understanding and Practice EJB and its client code via a DAO project

### **Time Estimate**

**Total Time**: **60 minutes**

---

### Prerequisites

To complete any of the following exercised in this lab, ensure the following prerequisites are met:

- **Connect** to your lab machine using RDP, with the user name and password provided by instructor or lab manager.
- **IntelliJ IDEA** is installed and configured. GlassFish support is installed in IntelliJ IDEA.
- **GlassFish 5** application server is installed and configured.
- **JDK 8** is installed.
- **Maven** is installed and properly configured.
- **The repository Java-EE-8-Application-Development** is cloned on your lab machine.

In case of any of this are not met, please refer to the **Prerequisites**, and **Step 0** in [day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.

Special requirements for relating database access:

- **The CustomerDb database** is created and configured in GlassFish as per the instructions in the previous labs. Refer to ([day three, lab 06 Practicing with the Customer Database](../day-03/06%20Practicing%20with%20the%20Customer%20Database.md)) for guidance.

### Lab Objective

The objective of this lab is to understand and practice the Enterprise JavaBeans (EJB) framework in Java EE 8. You will work with a sample project that demonstrates the use of EJBs for managing transactions in a Java EE application. You will analyze the code, understand the role of EJBs in transaction management, and practice adding explicit transaction management to the EJB session bean.

---

### Exercise 1: Analyze and fully understand the `daosessionbeanwebclient` Project

Open the `daosessionbeanwebclient` project in chapter 4 from existing source, and configure it to use with Glassfish.

Run or debug the project and observe the output. Analyze the code using the following points:

1.  **Entity Design:**

    - Understand the `Customer` entity class:
      - How JPA annotations like `@Entity`, `@Table`, and `@Id` define the entity's relationship with the database.
      - The use of `@GeneratedValue(strategy = GenerationType.AUTO)` for automatic ID generation.
      - The purpose of the `toString()` method in providing a string representation of the entity.

2.  **DAO Implementation:**

    - Review the `CustomerDaoBean` class:
      - How `@Stateful` and `@LocalBean` annotations define the EJB session bean.
      - How `@PersistenceContext` is used to inject an `EntityManager`.
      - Methods like `saveCustomer`, `getCustomer`, and `deleteCustomer`:
        - The difference between `persist()` (for new entities) and `merge()` (for updates).
        - The use of `find()` for retrieving entities and `remove()` for deletion.

3.  **Controller Logic:**

    - Analyze the `CustomerController` class:
      - The role of the `@Named` and `@RequestScoped` annotations in integrating with JSF.
      - The use of `@EJB` to inject the `CustomerDaoBean` EJB into the controller.
      - How the `saveCustomer()` method populates the `Customer` object with user input and interacts with the DAO to persist data.

4.  **JSF and Bean Integration:**

    - Understand how the `CustomerController` class serves as a backing bean for JSF pages:
      - How it handles user input fields (e.g., `firstName`, `lastName`, `email`).
      - The method flow: populating the `Customer` object, calling DAO methods, and returning navigation outcomes.

5.  **Transaction Handling:**

    - Understand how database operations in the `CustomerDaoBean` are managed within the persistence context provided by JPA.
    - The absence of explicit transaction boundaries due to container-managed transactions in this example.

### Exercise 2: Add Explicit Transaction Management to the `CustomerDaoBean` Class

Currently, the EJB relies on the default transaction management behavior. To make the transaction handling more explicit and optimized, you can use the `@TransactionAttribute` annotation to specify transaction scopes for each method in the `CustomerDaoBean` class. For example:

- Use `@TransactionAttribute(TransactionAttributeType.REQUIRED)` for operations like `saveCustomer` and `deleteCustomer` to ensure they execute within a transactional context.
- Use `@TransactionAttribute(TransactionAttributeType.SUPPORTS)` for read-only methods like `getCustomer`, where transactions are not mandatory but supported if one exists.

Update the `CustomerDaoBean` class to include these annotations and ensure that the transaction management is optimized for each operation type.

Hint: You can use the following code snippets as a reference for updating the `CustomerDaoBean` class, but please do not copy an paste it mechanically, instead ensure you understand the purpose and usage of the annotations before making the changes. (not talking about that this code may contain errors... it's just a hint)

### Updated `CustomerDaoBean` Class:

```java
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
@LocalBean
public class CustomerDaoBean {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            saveNewCustomer(customer);
        } else {
            updateCustomer(customer);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void saveNewCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Customer getCustomer(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(Customer customer) {
        entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
    }
}

```

### Review of the Changes above:

1.  **Save and Delete Operations (`REQUIRED`):**

    - `TransactionAttributeType.REQUIRED` ensures these methods always execute within a transaction. If a transaction exists, it will use it; otherwise, a new one will be created.

2.  **Read-Only Operations (`SUPPORTS`):**

    - `TransactionAttributeType.SUPPORTS` allows methods like `getCustomer` to run without requiring a transaction, improving performance for read-only operations.

3.  **Merge Before Remove:**

    - The `deleteCustomer` method ensures the entity is attached to the persistence context before calling `remove()`.

### Benefits of the Modification:

1.  **Optimized Transaction Management:**

    - Explicitly defining transaction attributes improves clarity and performance by tailoring transaction scopes to the operation type.

2.  **Improved Database Integrity:**

    - By enforcing transactional boundaries explicitly, this modification ensures that all operations are completed successfully or rolled back in case of failure.

3.  **Performance Optimization:**

    - Using `SUPPORTS` for read-only methods avoids unnecessary transaction creation, improving system performance for read operations.
