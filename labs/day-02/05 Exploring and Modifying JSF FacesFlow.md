### Hands-on Lab: Exploring and Modifying JSF FacesFlow

### **Time Estimate**

**Total Time**: **30 minutes**

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

- Understand JSF FacesFlow features.
- Make a meaningful modification to the project.

### **Step 1: Open the Project in IntelliJ IDEA**

### Step 1: Open the Project in IntelliJ IDEA

1.  Open IntelliJ IDEA.
2.  Select **File > Open**.
3.  Navigate to the cloned repository folder:  
    `Chapter02/facesflow`.
4.  Click **Open** to load the Maven project. IntelliJ will automatically detect and import the Maven configuration.
5.  Configure the project to run with GlassFish 5 as per the instructions in the previous labs. Refer to ([day one, lab 01 Working...](../day-01/01%20Working%20with%20a%20Simple%20JSF%20form%20Project.md) for guidance.)
6.

### **Step 2: Explore the Project**

- Review and analyze the key source files in the `net.ensode.javaee8book.facesflow` package.
- Explore the structure of the project and the JSF pages.
- Run and test the application using a browser.

### **Step 3: Make a Meaningful Modification**

1.  Add a new step with one single input field after we are asking for the phone numbers. Ask the user for its possible food allergies. (Use a simple string input)
2.  Test the modification by running the application and verifying that your modification works as expected.
