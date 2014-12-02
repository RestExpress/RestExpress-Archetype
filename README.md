RestExpress Archetypes
======================

* minimal - a minimal RestExpress server
* mongodb - a MongoDB-backed service suite.
* cassandra - a Cassandra-backed service suite.

__NOTE:__ This project is generated from [RestExpress-Scaffold](https://github.com/RestExpress/RestExpress-Scaffold).  Please fork and edit the sources there as pull requests to this project are not accepted.

Usage
=====
To create a new RestExpress project using one of these archetypes, use the Maven archetype plugin with the 'generate' goal.  To create a MongoDB-based project interactively:
```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-mongodb -DarchetypeVersion=1.13
```

For a Cassandra skeleton, use the following:
```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-cassandra -DarchetypeVersion=1.13
```

For a minimal skeleton with no backing database, use the following:
```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-minimal -DarchetypeVersion=1.13
```

Scripts
=======
Alternatively, there are corresponding scripts with each of these archetypes to simplify their usage.  These are found in the scripts/ directory:

* RestExpress-minimal.sh (https://raw.github.com/RestExpress/RestExpress-Archetype/master/scripts/RestExpress-minimal.sh)
* RestExpress-mongodb.sh (https://raw.github.com/RestExpress/RestExpress-Archetype/master/scripts/RestExpress-mongodb.sh)
* RestExpress-cassandra.sh (https://raw.github.com/RestExpress/RestExpress-Archetype/master/scripts/RestExpress-cassandra.sh)

Running the scripts has the same effect as copying the above maven commands and pasting them into your console window.  But is, arguably, easier to use if you happen to create a number of new projects.

Release
=======
To release a new version of these archetypes to the central Maven repository, perform the following:
```
mvn release:clean
mvn release:prepare
mvn release:perform
```
