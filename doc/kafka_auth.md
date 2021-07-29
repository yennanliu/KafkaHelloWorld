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
- Note:
	- Please `Enable GSSAPI mechanism in the server.properties file of every broker` as below
	```
	# server.properties

	# List of enabled mechanisms, can be more than one
	sasl.enabled.mechanisms=GSSAPI

	# Specify one of of the SASL mechanisms
	sasl.mechanism.inter.broker.protocol=GSSAPI
	
	```

- https://docs.confluent.io/platform/current/kafka/authentication_sasl/authentication_sasl_gssapi.html#kafka-sasl-auth-gssapi



## Terms
- `kafka_server_jaas.conf` : the conf for kafka auth (SASL/GSSAPI (Kerberos))
- `KDC` : A Kerberos Server authenticate kafka client (via Kerberos)
	- https://docs.microsoft.com/en-us/openspecs/windows_protocols/ms-kile/b4af186e-b2ff-43f9-b18e-eedb366abf13