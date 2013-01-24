RestExpress Archetypes
======================

minimal - a minimal RestExpress server
mongodb - a mongodb-backed service suite.

Usage
=====
To create a new RestExpress project using one of these archetypes, use the Maven archetype plugin with the 'generate' goal.  To create a mongodb-based project interactively:

```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-scaffold-mongodb -DarchetypeVersion=1.1
```

For a minimal skeleton interactively, use the following:
```
mvn archetype:generate -DarchetypeGroupId=com.strategicgains.archetype -DarchetypeArtifactId=restexpress-scaffold-minimal -DarchetypeVersion=1.1
```

Release
=======
To release a new version of these archetypes to the central Maven repository, perform the following:
```
mvn release:clean
mvn release:prepare
mvn release:perform
```
