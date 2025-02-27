### **1\. CDI (Contexts and Dependency Injection)**

- **Annotations:** `@Inject`, `@Named`, `@Produces`, `@Qualifier`, etc.
- **Purpose:** A modern, flexible, and extensible dependency injection framework.
- **Usage Example:**

  ```Java
  import javax.inject.Inject;
  import javax.inject.Named;

  @Named
  public class MyCDIBean {
      @Inject
      private AnotherBean anotherBean;

      public void performTask() {
          anotherBean.execute();
      }
  }

  ```

- **Key Features:**
  - **Scopes:** CDI offers scope annotations like `@RequestScoped`, `@SessionScoped`, `@ApplicationScoped`, and `@Dependent` for managing bean lifecycles.
  - **Producers:** Allows dynamic creation of beans via `@Produces`.
  - **Qualifiers:** Custom qualifiers using `@Qualifier` for distinguishing between different implementations of the same interface.
  - **Interceptors and Decorators:** Supports `@Interceptor` and `@Decorator` to add cross-cutting concerns.
  - **Named Beans:** Beans can be given names using `@Named`, making them accessible in JSF pages or other managed contexts.

---

### **2\. EJB (Enterprise JavaBeans)**

- **Annotations:** `@EJB`, `@Stateless`, `@Stateful`, `@Singleton`, etc.
- **Purpose:** A traditional framework in Java EE for building enterprise applications, specifically for transaction management, security, and concurrency.
- **Usage Example:**

  ```Java
  import javax.ejb.Stateless;

  @Stateless
  public class MyEJB {
      public void process() {
          // Business logic
      }
  }

  ```

  Injecting an EJB:

  ```Java
  import javax.ejb.EJB;

  public class MyService {
      @EJB
      private MyEJB myEjb;

      public void performTask() {
          myEjb.process();
      }
  }

  ```

- **Key Features:**

  - **State Management:** Supports `@Stateless`, `@Stateful`, and `@Singleton` beans.
  - **Transactions:** Automatically manages transactions with container-managed transaction (CMT) or bean-managed transaction (BMT) models.
  - **Security:** Built-in support for role-based security.
  - **Concurrency:** Offers support for concurrency in `@Singleton` beans.

---

### **Comparison Between CDI and EJB:**

| **Feature**                 | **CDI**                                                              | **EJB**                                            |
| --------------------------- | -------------------------------------------------------------------- | -------------------------------------------------- |
| **Purpose**                 | General-purpose dependency injection                                 | Enterprise application services                    |
| **Injection Annotation**    | `@Inject`                                                            | `@EJB`                                             |
| **Lifecycle Management**    | Fully configurable scopes (e.g., `@RequestScoped`, `@SessionScoped`) | Predefined lifecycles (`@Stateless`, `@Singleton`) |
| **Transaction Support**     | Requires manual integration                                          | Built-in transaction management                    |
| **Interceptors/Decorators** | Yes (`@Interceptor`, `@Decorator`)                                   | Yes (`@Interceptors`)                              |
| **JSF Integration**         | Strong support (`@Named`)                                            | Indirect                                           |
| **Lightweight**             | Yes                                                                  | Heavier, suited for specific use cases             |

---

### **When to Use CDI vs. EJB**

- **Use CDI** when:

  - You need a lightweight, flexible dependency injection framework.
  - You are building web applications or components requiring fine-grained lifecycle control.
  - You need integration with JSF or other modern frameworks.

- **Use EJB** when:

  - You need enterprise features like transaction management, security, or concurrency.
  - You are building business logic for enterprise-grade systems.

While **CDI** is the modern choice for dependency injection and preferred for new applications, **EJB** continues to be used for its robust support for enterprise-specific features.
