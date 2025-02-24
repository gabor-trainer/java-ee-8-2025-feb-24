# Java EE Platform SDK 8u1 vs Java EE Web Profile SDK 8u1

Java EE 8 (Java Platform, Enterprise Edition) provides two different variants for development: the Full Platform SDK and the Web Profile SDK. Both of these SDKs serve different purposes, depending on the size and type of the application you are developing.

**Java EE Platform SDK 8u1** vs **Java EE Web Profile SDK 8u1**:

### 1\. **Definition and Use Case:**

- **Java EE Platform SDK 8u1:**

  - Also known as the Full Profile or Full Platform SDK.
  - Designed for developing and running large-scale, enterprise-level applications.
  - Contains the complete set of APIs available in Java EE 8, including advanced features like Enterprise JavaBeans (EJB), Java Message Service (JMS), and batch processing.
  - Suitable for applications that require complex business logic, high levels of scalability, and advanced resource management.

- **Java EE Web Profile SDK 8u1:**

  - A subset of the Full Profile SDK.
  - Targeted for developing and running lightweight web applications.
  - Focuses on web technologies like Servlets, JavaServer Faces (JSF), JavaServer Pages (JSP), Contexts and Dependency Injection (CDI), and more.
  - Ideal for developers creating standard web applications without the need for advanced enterprise features.

### 2\. **API and Feature Comparison:**

The Web Profile SDK contains a subset of the Full Platform SDK's APIs. Below is a general breakdown:

- **Java EE Platform SDK 8u1:**

  - All the APIs included in the Web Profile SDK.
  - **Additional APIs:**
    - Enterprise JavaBeans (EJB)
    - Java Message Service (JMS)
    - Java EE Concurrency Utilities
    - JavaMail
    - Batch Processing (JSR-352)
    - Java API for WebSocket (full implementation)

- **Java EE Web Profile SDK 8u1:**

  - Core APIs:
    - Servlet (JSR-369)
    - JavaServer Faces (JSF)
    - Contexts and Dependency Injection (CDI)
    - Java Persistence API (JPA)
    - Bean Validation (JSR-380)
    - JSON Processing (JSR-374)
    - RESTful Web Services (JAX-RS)
    - Common Annotations (JSR-250)
    - Java API for WebSocket (subset implementation)

### 3\. **File Size and Complexity:**

- **Java EE Platform SDK 8u1:**

  - Larger file size due to inclusion of more libraries and tools.
  - Heavier runtime footprint and longer deployment times, as it supports more enterprise features.
  - Requires more memory and system resources.

- **Java EE Web Profile SDK 8u1:**

  - Smaller file size.
  - Lighter runtime footprint, optimized for web-based applications.
  - Faster deployment and execution for web applications, since it omits non-essential features.

### 4\. **Deployment Targets:**

- **Java EE Platform SDK 8u1:**

  - Suitable for deploying on application servers that support the full Java EE specification, such as GlassFish or WildFly with full profile support.

- **Java EE Web Profile SDK 8u1:**

  - Compatible with web servers or application servers that support only the Web Profile, such as Apache TomEE or WildFly configured for the Web Profile.

### 5\. **Suitability:**

- **Java EE Platform SDK 8u1:**

  - Best for developers building applications with complex business logic, messaging systems, and batch processing.

- **Java EE Web Profile SDK 8u1:**

  - Best for developers focused on building lightweight, fast-to-deploy, and efficient web applications.

### 6\. **Use Case Scenarios:**

- **Java EE Platform SDK 8u1:**

  - Enterprise-grade applications with complex business logic.
  - Applications that need JMS, EJB, and batch processing.

- **Java EE Web Profile SDK 8u1:**

  - Standard web applications (e.g., e-commerce websites, content management systems).
  - Web services and RESTful applications that don’t need complex enterprise services.

### 7\. **Typical Application Server Support:**

- **Java EE Platform SDK 8u1:**

  - GlassFish Full Profile
  - WildFly Full Profile
  - WebLogic Full Profile

- **Java EE Web Profile SDK 8u1:**

  - GlassFish Web Profile
  - WildFly (configured for Web Profile)
  - Apache TomEE
