# Kafka Auth Note

## Authentication Methods
- SASL using JAAS
- SASL/GSSAPI (Kerberos)
- SASL/OAUTHBEARER
- SASL/PLAIN
- SASL/SCRAM
- Delegation Tokens (SASL/SSL)
- LDAP
- Ref
    - https://docs.confluent.io/platform/current/kafka/overview-authentication-methods.html

### 1) SASL using JAAS
- Kafka uses the Java Authentication and Authorization Service (JAAS) for SASL configuration. You must provide JAAS configurations for all SASL authentication mechanisms. There are two ways to configure Kafka clients to provide the necessary information for JAAS:
    - Specify the JAAS configuration using the sasl.jaas.config configuration property (recommended)
    - Pass a static JAAS configuration file into the JVM using the java.security.auth.login.config property at runtime
- Ref
    - https://docs.confluent.io/platform/current/kafka/authentication_sasl/index.html

### 2) SASL/GSSAPI (Kerberos)
- Use the `Kerberos` mechanism. Good to use if already use kerberos
- SASL/GSSAPI is for organizations using Kerberos (for example, by using Active Directory). You don’t need to install a new server just for Apache Kafka®. Ask your Kerberos administrator for a principal for each Kafka broker in your cluster and for every operating system user that will access Kafka with Kerberos authentication (via clients and tools).
- Note:
    - Please `configure a KafkaServer section with a unique principal and keytab` in each broker’s JAAS file :
    ```
    // kafka_server_jaas.conf

    // Specifies a unique keytab and principal name for each broker
    KafkaServer {
        com.sun.security.auth.module.Krb5LoginModule required
        useKeyTab=true
        storeKey=true
        keyTab="/etc/security/keytabs/kafka_server.keytab"
        principal="kafka/kafka1.hostname.com@EXAMPLE.COM";
    };
    
    ```
    - Please `Enable GSSAPI mechanism in the server.properties file of every broker`:
    ```
    # server.properties

    # List of enabled mechanisms, can be more than one
    sasl.enabled.mechanisms=GSSAPI

    # Specify one of of the SASL mechanisms
    sasl.mechanism.inter.broker.protocol=GSSAPI
    
    ```

- Ref
    - https://docs.confluent.io/platform/current/kafka/authentication_sasl/authentication_sasl_gssapi.html#kafka-sasl-auth-gssapi


### 3) SASL/OAUTHBEARER
### 4) SASL/PLAIN
### 5) SASL/SCRAM
### 6) Delegation Tokens (SASL/SSL)
### 7) LDAP

## Terms
-  `JAAS`
    - Java Authentication and Authorization Service (JAAS)
    - Kafka uses the Java Authentication and Authorization Service (JAAS) for SASL configuration.
    - https://docs.oracle.com/javase/8/docs/technotes/guides/security/jaas/JAASRefGuide.html
- `kafka_server_jaas.conf` : 
    the conf for kafka auth (SASL/GSSAPI (Kerberos))
- `KDC` : 
    - A Kerberos Server authenticate kafka client (via Kerberos)
    - https://docs.microsoft.com/en-us/openspecs/windows_protocols/ms-kile/b4af186e-b2ff-43f9-b18e-eedb366abf13
- SASL
    - `Simple Authentication and Security Layer (SASL)`, is a framework for authentication and data security in Internet protocols. It decouples authentication mechanisms from application protocols, in theory allowing any authentication mechanism supported by SASL to be used in any application protocol that uses SASL. Authentication mechanisms can also support proxy authorization, a facility allowing one user to assume the identity of another. They can also provide a data security layer offering data integrity and data confidentiality services. DIGEST-MD5 provides an example of mechanisms which can provide a data-security layer. Application protocols that support SASL typically also support Transport Layer Security (TLS) to complement the services offered by SASL.
    - https://en.wikipedia.org/wiki/Simple_Authentication_and_Security_Layer
- SSL
    - `Secure Sockets Layer`. and, in short, it's the standard technology for keeping an internet connection secure and safeguarding any sensitive data that is being sent between two systems, preventing criminals from reading and modifying any information transferred, including potential personal details. The two systems can be a server and a client (for example, a shopping website and browser) or server to server (for example, an application with personal identifiable information or with payroll information).
    - https://www.websecurity.digicert.com/security-topics/what-is-ssl-tls-https
- SASL_SSL: 
    - if SSL encryption is enabled (SSL encryption should always be used if SASL mechanism is PLAIN)

## Ref
- SASL VS SSL: 
    - https://stackoverflow.com/questions/11347304/security-authentication-ssl-vs-sasl