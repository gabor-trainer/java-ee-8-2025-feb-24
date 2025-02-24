### Hands-on Lab: Exploring and Modifying the JSF AJAX Project

### **Time Estimate**

**Total Time**: **30-45 minutes**

---

### Prerequisites

To complete this lab, ensure the following prerequisites are met:

- **Connect** to your lab machine using RDP, with the user name and password provided by instructor or lab manager.
- **IntelliJ IDEA** is installed and configured. GlassFish support is installed in IntelliJ IDEA.
- **GlassFish 5** application server is installed and configured.
- **JDK 8** is installed.
- **Maven** is installed and properly configured.
- **The repository Java-EE-8-Application-Development** is cloned on your lab machine.

In case of any of this are not met, please refer to the **Prerequisites**, and **Step 0** in [day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.

---

### Lab Objective

**Note:** This lab provides some source code examples and instructions to guide you through the process of exploring and modifying a Java EE 8 JSF project. Always try to understand the code and experiment with it to get a better grasp of the concepts.
**When the lab asks you to modify the code, always try to do it first on your own. Using, and copy and pasting the solution code provided here is always the last resort. :-)**

- Understand JSF AJAX features.
- Make a meaningful modification to the project.

### **Step 1: Open the Project in IntelliJ IDEA**

### Step 1: Open the Project in IntelliJ IDEA

1.  Open IntelliJ IDEA.
2.  Select **File > Open**.
3.  Navigate to the cloned repository folder:  
    `Chapter02/jsfajax`.
4.  Click **Open** to load the Maven project. IntelliJ will automatically detect and import the Maven configuration.
5.  Configure the project to run with GlassFish 5 as per the instructions in the previous labs. Refer to ([day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.)
6.

### **Step 2: Explore the Project**

- Review and analyze the key source files in the `net.ensode.javaee8book.jsfajax` package.
- Explore the structure of the project and the JSF pages.
- Run and test the application using a browser.
- Press F12 and see the rendered HTML code, compare it with the .xhtml JSF source code.

### **Step 3: Make a Meaningful Modification**

1.  Add a new feature that displays an additional user input field and processes it alongside existing logic: Third Operand
2.  Modify the existing logic to add the third operand to the sum of the first two operands, so it will add three numbers.
3.  Test the modification by running the application and verifying that your modification works as expected.
4.

### Hints and explanations:

### Key Components of `<f:ajax>` in the Code

1.  **`execute` attribute:**

    - Specifies which components' values should be submitted to the server during the Ajax request.
    - In your solution:

      ```xml
      <f:ajax execute="first second third" render="sum"/>
      ```

      The `execute="first second third"` attribute means the input components with IDs `first`, `second`, and `third` will have their values processed on the server during this Ajax request.

    - This is important because only the specified components' values are submitted to the server for processing. Other components on the form are ignored unless explicitly included.

2.  **`render` attribute:**

    - Specifies which components on the page should be updated (re-rendered) after the Ajax request is processed.
    - In our example:

      ```
      render="sum"

      ```

      The `render="sum"` attribute means the `h:outputText` component with the ID `sum` will be updated with the new value calculated on the server.

3.  **How It Works in the Code:**

    - When the `h:commandButton` is clicked, the `calculateTotal` method in the `controller` bean is executed on the server.
    - The values of `first`, `second`, and `third` (provided by the user) are submitted to the server because they are listed in the `execute` attribute.
    - The server processes the request, updates the `controller.total` property, and sends back a response to update the `h:outputText` with ID `sum`.

4.  **`event` Attribute:**

    - Not explicitly used here for the button, but in `<f:ajax>` tags, you can specify the DOM event to trigger the Ajax request.
    - For the button, the default event is `click`.

### Full Lifecycle for `<f:ajax>`:

1.  **Trigger:** Clicking the button triggers the Ajax request.
2.  **Execute:** The values of the specified components (`first`, `second`, `third`) are sent to the server.
3.  **Process:** The server-side method (`calculateTotal`) updates the model (`controller.total`).
4.  **Render:** The response from the server updates the `sum` component with the new total.

This approach enables efficient updates to specific parts of the page without requiring a full page reload, which enhances user experience and performance.
