A Cassandra-Backed RestExpress Server
=====================================
A template RestExpress project with Cassandra persistence for domain objects.
Example Cassandra schema (to run the base server) is in src/main/cql/sample.cql

This project produces two domain objects and two corresponding repositories, illustrating
the two primary ways to persist a domain model to Cassandra

# One domain object is identified by a single UUID
# The other identified by a compound key

To run the project:
	Make sure Cassandra is running (and your keyspace and schema is created)
	mvn clean package exec:java

To create a project deployable assembly (zip file):
	mvn clean package
	mvn assembly:single

To run the project via the assembly (zip file):
	unzip <assembly file created in above step>
	cd <artifact directory>
	java -jar <artifact jar file> [environment name]
