# Gradle Issue with Worker Leaking Classes

The Gradle worker is leaking classes that it shouldn't be (such as Guava).

This project reproduces the issue.

To validate this:

1. Clone this repo.
1. Run `./gradlew guava`.

It will output that Guava was found on the classpath.
