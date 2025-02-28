### Setting Up JMS Resources in GlassFish

Before we can run any sample code in our bookusing the JMS API, we need to set up some JMS resources in our GlassFish. This includes

- a JMS connection factory,
- a message queue,
- and a message topic.
- we will also cover how to create durable subscribers (later)

---

#### **About JMS Connection Factory Setup**

Java EE 8 compliant application servers (like GlassFish) already have a default JMS connection factory. So technically, you might not need to create one. But often, custom setups are required, so as a very basic required skill, let’s see how it’s done.

#### **How to Set Up a JMS Connection Factory**

1.  **Start Your GlassFish Server:** Run the following command in your terminal:

    ```bash
    asadmin start-domain domain1
    ```

    Then open your browser and go to [http://localhost:4848](http://localhost:4848).

2.  **Create the Factory in the Web Console:**

    - Expand **Resources** > **JMS Resources** > **Connection Factories** in the left-hand menu.
    - Click **New...** in the main section.

3.  **Fill Out the Details:**

    - Enter a name starting with `jms/`, e.g., `jms/JavaEE8BookConnectionFactory`, to make it easy to identify.
    - Pick a resource type:
      - `javax.jms.TopicConnectionFactory`: For pub/sub messaging.
      - `javax.jms.QueueConnectionFactory`: For point-to-point (PTP) messaging.
      - `javax.jms.ConnectionFactory`: A versatile option that works with both.
    - We’ll use `javax.jms.ConnectionFactory` in this example.

4.  **Save Your Settings:** Click **OK**, and your connection factory will appear in the list.

---

#### **Setting Up a JMS Message Queue**

1.  **Navigate in the Web Console:**

    - Expand **Resources** > **JMS Resources** > **Destination Resources**.
    - Click **New...**.

2.  **Add the Details:**

    - JNDI Name: `jms/JavaEE8BookQueue`.
    - Resource Type: `javax.jms.Queue`.
    - Physical Destination Name: `JavaEE8BookQueue`.

3.  **Save Your Queue:** Click **OK**, and you’ll see your queue in the list.

---

#### **Setting Up a JMS Message Topic**

The process is almost identical to creating a queue:

1.  Go to **Resources** > **JMS Resources** > **Destination Resources**, and click **New...**.
2.  Enter the following:

    - JNDI Name: `jms/JavaEE8BookTopic`.
    - Resource Type: `javax.jms.Topic`.
    - Physical Destination Name: `JavaEE8BookTopic`.

3.  Hit **OK** to save your topic.

---

### Wrapping Up

Now you’ve set up a JMS connection factory, a message queue, and a message topic in GlassFish. You’re ready to start coding with the JMS API!
