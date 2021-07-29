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

### SASL/GSSAPI (Kerberos)
- Use the `Kerberos` mechanism. Good to use if already use kerberos
- SASL/GSSAPI is for organizations using Kerberos (for example, by using Active Directory). You don’t need to install a new server just for Apache Kafka®. Ask your Kerberos administrator for a principal for each Kafka broker in your cluster and for every operating system user that will access Kafka with Kerberos authentication (via clients and tools).
- https://docs.confluent.io/platform/current/kafka/authentication_sasl/authentication_sasl_gssapi.html#kafka-sasl-auth-gssapi