A MongoDB-Backed RestExpress Server
===================================
A template RestExpress project with MongoDB persistence for domain objects.

To run the project:
	Make sure MongoDB is running
	mvn clean package exec:java

To create a project deployable assembly (zip file):
	mvn clean package
	mvn assembly:single
