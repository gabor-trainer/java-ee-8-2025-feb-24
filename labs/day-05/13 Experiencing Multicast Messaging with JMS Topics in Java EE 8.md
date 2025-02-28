### **Hands-on Lab: Experiencing Multicast Messaging with JMS Topics in Java EE 8**

### **Time Estimate**

**Total Time**: **30 minutes**

---

### **Prerequisites**

To complete the exercises in this lab, ensure the following prerequisites are met:

- **Connect** to your lab machine using RDP, with the username and password provided by the instructor or lab manager.
- **IntelliJ IDEA** is installed and configured. GlassFish support is installed in IntelliJ IDEA.
- **GlassFish 5** application server is installed and configured.
- **JDK 8** is installed.
- **Maven** is installed and properly configured.
- **The repository Java-EE-8-Application-Development** is cloned on your lab machine.

**Special requirements for this lab:**

- JMS resources for topics must be correctly set up in GlassFish. If you haven't done this yet, refer to the lab [Setting Up JMS Resources in GlassFish](https://chatgpt.com/c/67402bf6-03f4-8001-9583-14b270c81079../day-05/11%20Setting%20Up%20JMS%20Resources%20in%20GlassFish.md) for guidance.

---

### **Analyze and Fully Understand the `jmspubsubproducer` Project**

Open the `jmspubsubproducer` project from the provided source and configure it to use with GlassFish.

#### Review Points: Producer (`TopicPublisher.java`)

1.  **Core Responsibilities**:

    - Publishes messages to a topic named `jms/JavaEE8BookTopic`.

2.  **Injection Points**:

    - `ConnectionFactory` is injected to establish a connection for message production.
    - `Topic` is injected using `@Resource(mappedName = "jms/JavaEE8BookTopic")`.

3.  **JMS Workflow**:

    - Creates a `JMSContext` using the injected `ConnectionFactory`.
    - Utilizes a `JMSProducer` to send messages to the specified topic.

4.  **Logging**:

    - Logs each message before publishing, aiding in debugging and tracing.

5.  **Best Practices**:

    - The `JMSContext` is created within the method to prevent resource leakage.
    - Topic names and configurations are resolved dynamically through the server.

---

### **Analyze and Fully Understand the `jmspubsubconsumer` Project**

Open the `jmspubsubconsumer` project from the provided source and configure it to use with GlassFish.

#### Review Points: Subscriber (`TopicSubscriber.java`)

1.  **Core Responsibilities**:

    - Subscribes to the `jms/JavaEE8BookTopic` and receives messages published to the topic.

2.  **Injection Points**:

    - `ConnectionFactory` is injected to establish a connection for message consumption.
    - `Topic` is injected using `@Resource(mappedName = "jms/JavaEE8BookTopic")`.

3.  **JMS Workflow**:

    - Creates a subscription using a unique client ID and subscription name.
    - Receives messages continuously and processes them.

4.  **Logging**:

    - Logs received messages for debugging and tracing.

5.  **Best Practices**:

    - Uses durable subscriptions to ensure no messages are lost even when the subscriber is offline.
    - Logs the receipt of messages for better understanding and debugging.

---

### **How They Work Together**

1.  **Producer and Subscribers Relationship**:

    - The producer publishes messages to the topic.
    - Multiple subscribers receive these messages simultaneously.

2.  **Execution Flow**:

    - Run the producer to publish messages to the topic.
    - Start multiple subscriber instances to observe how each receives the same messages.

3.  **Experience**:

    - The producer publishes three messages in sequence.
    - All subscribers receive these messages independently.

4.  **Observations**:

    - Demonstrates a publish/subscribe model where multiple consumers can subscribe to the same topic and receive the same messages.
    - Durable subscriptions ensure messages are not lost if the subscribers are temporarily unavailable.

---

### **Step-by-Step Running Sequence: Experience JMS Topic Multicast Messaging**

---

### **Step 1: Start GlassFish Server**

1.  Start your **GlassFish Server**.
2.  Ensure the **JMS topic resource** (`jms/JavaEE8BookTopic`) is configured.

---

### **Step 2: Deploy and Run the Producer Application**

1.  Open the `jmspubsubproducer` project in **IntelliJ IDEA**.
2.  Build and deploy the application to the GlassFish server:
    - Run the producer application by executing `TopicPublisher.java`.
3.  The producer publishes messages (`msg1`, `msg2`, `msg3`, "Goodbye") to the topic.

---

### **Step 3: Deploy and Run the Subscriber Application**

1.  Open the `jmspubsubconsumer` project in **IntelliJ IDEA**.
2.  Build and deploy the application to the GlassFish server:
    - Run multiple instances of the `TopicSubscriber.java` with different **Client IDs**.
    - Each subscriber connects to the topic `jms/JavaEE8BookTopic`.

---

### **Step 4: Observe how the Producer and Subscriber interact**

1.  **Start Subscribers Before the Producer:**

    - Start one or more subscribers and ensure they are online.
    - Start the producer and publish messages.
    - Observe that they receive all messages (`msg1`, `msg2`, `msg3`, and "Goodbye").

2.  **Start Subscribers After the Producer Has Published Messages:**

    - Start new subscriber instances after the producer has already sent all messages.
    - Observe that late subscribers **do not receive previously sent messages**, demonstrating the **online-only** delivery of messages to topic subscribers.

---

### **Step 5: Demonstrate Real-Time Multicast**

1.  Use the producer and publish new messages.
2.  Observe that all currently online subscribers receive the messages simultaneously, showing the multicast behavior.

### **Use Cases for topics**

1.  **Publish/Subscribe Messaging**:

    - Allows multiple subscribers to consume the same messages, enabling broadcast-style communication.

2.  **Decoupling Components**:

    - Producers and subscribers operate independently. Topics ensure reliable delivery to all active subscribers.

3.  **Real-Time Broadcasting**:

    - Useful for scenarios like live feeds, stock price updates, or event broadcasting.

4.  **Durable Subscriptions**:

    - Ensures reliable message delivery even if subscribers are offline when the messages are published.

### **Comparare Queue vs. Topic Behaviors in JMS**

| **Aspect**              | **Queue (Point-to-Point)**                                                                                       | **Topic (Publish/Subscribe)**                                                              |
| ----------------------- | ---------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **Model**               | Point-to-Point                                                                                                   | Publish/Subscribe                                                                          |
| **Message Delivery**    | One message is delivered to **one consumer only** (first come, first serve).                                     | Messages are delivered to **all online subscribers**.                                      |
| **Persistence**         | Messages are stored in the queue until consumed.                                                                 | Messages are not stored unless **durable subscriptions** are used.                         |
| **Offline Consumers**   | Consumers can receive messages sent while they were offline, as long as they connect before the message expires. | Offline subscribers do **not receive messages** unless using a durable subscription.       |
| **Producer Dependency** | Independent of the consumer; the queue ensures message delivery.                                                 | Requires at least one active subscriber to ensure message delivery.                        |
| **Use Case**            | One-to-one communication: Order processing, task distribution.                                                   | One-to-many communication: Stock updates, news broadcasting.                               |
| **JMS Resources**       | Requires a `Queue` resource (e.g., `jms/JavaEE8BookQueue`).                                                      | Requires a `Topic` resource (e.g., `jms/JavaEE8BookTopic`).                                |
| **Concurrency**         | Only one consumer can process each message.                                                                      | Multiple subscribers can receive and process the same message.                             |
| **Flow Control**        | Producers and consumers are decoupled; the queue stores messages until processed.                                | Real-time; messages are lost if no subscribers are online (without durable subscriptions). |
| **Durability Options**  | Not needed; queues inherently store messages.                                                                    | Requires durable subscription to ensure message persistence for offline subscribers.       |
