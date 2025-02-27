### Hands-on Lab: Using CDI Events and Bean Scopes in Java EE. Understanding decoupled communication and bean lifecycle management.

### **Time Estimate**

**Total Time**: **30 minutes**

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

- Understand and Implement the Observer Design Pattern in Java EE:
  - Learn how CDI enables the observer design pattern using events and observers.
  - Explore how to decouple components through event-driven communication.
  - Use `@Observes` to handle events and process data in an event listener.
- Differentiate Between Bean Scopes in Java EE:\*\*
  - Gain practical knowledge of the lifecycle and usage of `@RequestScoped` and `@ConversationScoped` beans.
  - Implement examples to demonstrate the advantages of each scope.
- Develop Event-Driven Communication:\*\*

  - Fire and observe events using CDI.
  - Use `NavigationInfo` as a data carrier to pass contextual information between components.

- Explore Long-Lived Conversations with `@ConversationScoped`:

  - Implement a multi-step workflow (e.g., customer data entry or a navigation workflow) that spans multiple requests.
  - Use `Conversation.begin()` and `Conversation.end()` to manage conversation lifecycle.

- Debug and Test Components Effectively:

  - Utilize `ReflectionToStringBuilder` for debugging and logging Java objects.
  - Print object states to verify correctness and simplify debugging processes.

- Build Modular and Testable Java EE Applications:

  - Use CDI for dependency injection (`@Inject`) to manage beans and reduce boilerplate code.
  - Demonstrate the benefits of modularity and testability through decoupled components.

---

### Analyze and fully understand the `cdievents` Project

**Important: The origianls source code in the book is in Java EE 8, contains a legacy file `profiles.xml` in the project root. This file cause inconsitent behaviour in IntelliJ and its maven plugin so you may not be able to create a run configuration and a successful deployment. To fix this issue, you can safely delete this `profiles.xml` file from the project root.**

(Actually, all project in chapter 05 has this very same issue)

Open the `cdievents` project in chapter 5 from existing source, and configure it to use with Glassfish.

Run or debug the project and observe the output. Analyze the code using the following points:

### Key Points to Analyze and Understand

1.  **CustomerInfoController.java**

    - Manages customer-related operations.
    - Uses `@RequestScoped` for short-lived scope and `@Named` for making it accessible in JSF views.
    - Uses `@Inject` to access a `Conversation` object and an event for navigation info.

2.  **NavigationInfo.java**

    - Represents an event class carrying navigation details (e.g., `page` and `Customer`).
    - Simple getter/setter methods for encapsulated fields.

3.  **NavigationEventListener.java**

    - A session-scoped class using `@Observes` to listen to navigation events.
    - Outputs navigation details using `System.out.println`.

4.  **Customer.java**

    - A model class marked as `@ConversationScoped` and `@Named`, enabling usage in JSF and persistence over a conversation lifecycle.
    - Contains fields like `firstName`, `lastName`, and address details.
    - Implements a `ReflectionToStringBuilder` for debugging or logging.

---

### Questions

- In general what is observer design pattern? What metaphors can we use to enlight the concept?
- How does `@RequestScoped` differ from `@ConversationScoped`?
- What is the role of `@Observes` in the event listener?
- How does the event system facilitate decoupled communication between components?
- Explain the lifecycle of a `ConversationScoped` bean and give an example of when it might be useful.
- Why would you use `ReflectionToStringBuilder` in the `Customer` class, and what advantage does it provide?
- How does the `NavigationInfo` class work as a data carrier in the event system?

### Answers

**Observer Design Pattern**

The observer design pattern is a behavioral design pattern where one object (the subject) maintains a list of dependents (observers) that it notifies of any changes in its state.

**Metaphors**:

1.  **News Subscription**: A news agency (subject) sends updates to subscribers (observers). When news changes, all subscribers are notified automatically.
2.  **Classroom Announcements**: A teacher (subject) announces updates to students (observers). Students only react when there’s an announcement.

---

**How does `@RequestScoped` differ from `@ConversationScoped`?**

- **@RequestScoped**:

  - The bean exists only during a single HTTP request.
  - It is short-lived and destroyed as soon as the request is completed.
  - Useful for transient operations where data does not need to persist beyond a request.

- **@ConversationScoped**:

  - The bean spans multiple HTTP requests within a single conversation.
  - It ends when explicitly terminated or when the conversation times out.
  - Useful for workflows or wizards where data persists across several user actions.

---

**Role of `@Observes` in the Event Listener**

The `@Observes` annotation in CDI (Contexts and Dependency Injection) marks a method as an event observer. When an event is fired, CDI automatically calls the method annotated with `@Observes`, passing the event payload.

Example in the context:

- `handleNavigationEvent(@Observes NavigationInfo navigationInfo)` listens for navigation-related events and reacts by processing the `NavigationInfo` object.

---

**How does the event system facilitate decoupled communication between components?**

The event system in CDI allows components to communicate without being directly dependent on one another:

- **Publishers** fire events without knowing who will handle them.
- **Listeners** observe events and react when notified but don’t need to know the source.
- This decoupling makes components more modular, testable, and easier to maintain.

---

**Lifecycle of a `ConversationScoped` Bean and Example of Usage**

- **Lifecycle**:

  - Begins when a conversation is explicitly started using `Conversation.begin()`.
  - Spans multiple requests until explicitly ended using `Conversation.end()` or times out.
  - Ideal for long-running tasks where intermediate states need to be preserved.

- **Example**:

  - A multi-step checkout process: User fills a cart, provides shipping information, and confirms the purchase. The `ConversationScoped` bean keeps all this data intact across the steps.

---

**Why use `ReflectionToStringBuilder` in the `Customer` class?**

- **Purpose**: Simplifies the implementation of the `toString()` method by automatically generating a string representation of all fields in the object.
- **Advantages**:
  - Reduces boilerplate code.
  - Dynamically adapts if fields in the class are modified, avoiding manual updates to the `toString()` method.

---

**How does the `NavigationInfo` class work as a data carrier in the event system?**

- The `NavigationInfo` class encapsulates navigation-related data, such as the current page and the associated `Customer`.
- When an event is fired, an instance of `NavigationInfo` is passed to the observer method.
- Observers use the encapsulated data to take appropriate actions, such as logging, redirecting, or updating the UI, without needing direct interaction with the source of the event.

### Appendix A: Comparison Between CDI and EJB

In JavaServer Faces (JSF) applications, managing the lifecycle and scope of beans is crucial for maintaining state across user interactions. Two annotations that facilitate this are `@ConversationScoped` and `@FlowScoped`. Here's a comparison of their characteristics and use cases:

**`@ConversationScoped`:**

- **Definition:** This scope is part of Contexts and Dependency Injection (CDI) and is used to manage beans that need to maintain state across multiple requests within a defined conversation.
- **Lifecycle Control:** Developers have explicit control over the conversation's lifecycle. A conversation starts by invoking `conversation.begin()` and ends with `conversation.end()`. This manual control allows for flexible management of the bean's state duration.
- **Use Cases:** Ideal for scenarios where a series of interactions (e.g., a multi-step form) need to share state, but the scope should not extend beyond this specific sequence. It's particularly useful when the conversation's boundaries are not tied to specific page flows.
- **Implementation Example:**

  ```java
  @Named
  @ConversationScoped
  public class ConversationBean implements Serializable {
      @Inject
      private Conversation conversation;

      public void startConversation() {
          if (conversation.isTransient()) {
              conversation.begin();
          }
      }

      public void endConversation() {
          if (!conversation.isTransient()) {
              conversation.end();
          }
      }
  }

  ```

**`@FlowScoped`:**

- **Definition:** Introduced in JSF 2.2, `@FlowScoped` is used to define a scope that spans a specific flow, which is a set of pages representing a unit of work or a task.
- **Lifecycle Control:** The lifecycle is managed by JSF's flow mechanism. A flow begins when a user enters it and ends when they exit, either by completing the task or navigating out of the flow. This automatic management simplifies state handling within the flow.
- **Use Cases:** Suitable for well-defined, page-centric workflows where the navigation and state are confined to a specific sequence of pages, such as a checkout process or a wizard.
- **Implementation Example:**

  ```java
  @Named
  @FlowScoped("myFlow")
  public class FlowBean implements Serializable {
      // Flow-specific methods and properties
  }

  ```

**Key Differences:**

- **Scope Duration:** `@ConversationScoped` beans persist as long as the conversation is active, which is manually controlled. In contrast, `@FlowScoped` beans persist for the duration of the flow, managed automatically by JSF.
- **Control Mechanism:** `@ConversationScoped` offers fine-grained control over the bean's lifecycle, making it flexible for various scenarios. `@FlowScoped` is more declarative, aligning with JSF's navigation model for defined flows.
- **Use Case Suitability:** Use `@ConversationScoped` when you need explicit control over the conversation's boundaries, especially in non-linear interactions. Opt for `@FlowScoped` when dealing with linear, page-defined workflows where JSF's flow management can handle the state transitions.
