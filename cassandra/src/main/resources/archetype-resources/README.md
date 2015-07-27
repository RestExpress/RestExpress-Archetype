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

To create a 'fat' runnable jar file:

	mvn clean package

To run the jar file created via package

	java -jar target/{project-name}.jar [environment]


Configuration
-------------

By default, the 'mvn package' goal will create a fat jar file including the configuration files in src/main/resources.
These are loaded from the classpath at runtime. However, to override the values embedded in the jar file, simply create
a new configuration file on the classpath for the desired environment. For example, './config/dev/environment.properties'
and any settings in that file will get added to, or override settings embedded in the jar file.