### Hands-on Lab: Experiencing Point to Point messaging JMS with Java EE 8

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

Special requirements for this lab:
JMS resources must be correctly set up in GlassFish. If you haven't done this yet, refer to the lab [Setting Up JMS Resources in GlassFish](../day-05/11%20Setting%20Up%20JMS%20Resources%20in%20GlassFish.md) for guidance.

### Analyze and fully understand the `jmsptpproducer` Project

Open the `jmsptpproducer` project in chapter 8 from existing source, and configure it to use with Glassfish.

### Review Points: Producer (`MessageSender.java`)

1.  **Core Responsibilities**:

    - Produces and sends three messages (`msg1`, `msg2`, and `msg3`) to a queue named `jms/JavaEE8BookQueue`.

2.  **Injection Points**:

    - `ConnectionFactory` is injected to establish a connection for message production.
    - `Queue` is injected using `@Resource(mappedName = "jms/JavaEE8BookQueue")`.

3.  **JMS Workflow**:

    - Creates a `JMSContext` using the injected `ConnectionFactory`.
    - Utilizes a `JMSProducer` to send messages to the specified queue.

4.  **Logging**:

    - Logs each message before it is sent, aiding in debugging and tracing the flow of messages.

5.  **Best Practices**:

    - The `JMSContext` is created within the method, which ensures no resource leakage.
    - The code avoids hard-coding resource names in multiple locations, relying on the server configuration.

---

### Analyze and fully understand the `jmsptpconsumer` Project

Open the `jmsptpconsumer` project in chapter 8 from existing source, and configure it to use with Glassfish.

### Review Points: Consumer (`MessageReceiver.java`)

1.  **Core Responsibilities**:

    - Receives and processes messages from the `jms/JavaEE8BookQueue` queue.

2.  **Injection Points**:

    - `ConnectionFactory` is injected to establish a connection for message consumption.
    - `Queue` is injected using `@Resource(mappedName = "jms/JavaEE8BookQueue")`.

3.  **JMS Workflow**:

    - Creates a `JMSContext` and uses a `JMSConsumer` to consume messages from the queue.
    - Continuously receives messages until the "Good bye!" message is encountered.

4.  **Logging**:

    - Logs messages as they are received for debugging and tracing.

5.  **Best Practices**:

    - Uses a loop to wait for messages, demonstrating a blocking receive mechanism.
    - Logs the receipt of messages to understand message flow.

---

### How They Work Together

1.  **Producer and Consumer Relationship**:

    - The producer sends messages to the queue.
    - The consumer waits for and processes those messages.

2.  **Execution Flow**:

    - Run the producer to populate the queue with messages.
    - Start the consumer to process messages from the queue.

3.  **Experience**:

    - The producer sends three messages in sequence.
    - The consumer retrieves these messages, logs them, and stops when it encounters "Good bye!".

4.  **Observations**:

    - They work asynchronously, with the queue acting as the intermediary.
    - Both use `@Resource` annotations to resolve the queue, requiring proper server configuration.

---

### Use Cases

1.  **Point-to-Point Messaging**:

    - Ensures a single consumer processes messages, as demonstrated in the consumer logic.

2.  **Decoupling Components**:

    - Producer and consumer can run independently. The queue ensures reliable delivery even if the consumer is not immediately available.

3.  **Transactional Messaging**:

    - Can be extended to ensure transactional guarantees, where messages are processed only after successful acknowledgment.

4.  **Real-Time Data Flow**:

    - Useful for real-time systems where one component generates data, and another processes it asynchronously.
