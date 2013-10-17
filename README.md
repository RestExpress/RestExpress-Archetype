RestExpress Archetypes
======================

* minimal - a minimal RestExpress server
* mongodb - a mongodb-backed service suite.

__NOTE:__ This project is generated from [RestExpress-Scaffold](https://github.com/RestExpress/RestExpress-Scaffold).  Please fork and edit the sources there as pull requests to this project are not accepted.

Usage
=====
To create a new RestExpress project using one of these archetypes, use the Maven archetype plugin with the 'generate' goal.  To create a mongodb-based project interactively:

```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-scaffold-mongodb -DarchetypeVersion=1.7
```

For a minimal skeleton interactively, use the following:
```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-scaffold-minimal -DarchetypeVersion=1.7
```

Scripts
=======
Alternatively, there are corresponding scripts with each of these archetypes to simplify their usage.  These are found in the scripts/ directory:

* RestExpress-minimal.sh (https://raw.github.com/RestExpress/RestExpress-Archetype/master/scripts/RestExpress-minimal.sh)
* RestExpress-mongodb.sh (https://raw.github.com/RestExpress/RestExpress-Archetype/master/scripts/RestExpress-mongodb.sh)

Running the scripts has the same effect as copying the above maven commands and pasting them into your console window.  But is, arguably, easier to use if you happen to create a number of new projects.

Release
=======
To release a new version of these archetypes to the central Maven repository, perform the following:
```
mvn release:clean
mvn release:prepare
mvn release:perform
```
