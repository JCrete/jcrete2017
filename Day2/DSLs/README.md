# Domain Specific Languages (DSLs)
Convened by [Diomidis](https://www.spinellis.gr) and Richard.

## Summary
Methods you can use to work with DSLs with Java include the following.

* Use default methods in interfaces to provide to the DSL client an instance
  of the DSLs runtime and environment.
* Use the self type trick
* Generate code at compile time, e.g. through Java annotation processing
* Implement the DSL in
  [Groovy](https://en.wikipedia.org/wiki/Groovy_(programming_language)),
  [Scala](https://en.wikipedia.org/wiki/Scala_(programming_language)), or
  [Kotlin](https://en.wikipedia.org/wiki/Kotlin_(programming_language)).
* Implement a [fluent API](https://en.wikipedia.org/wiki/Fluent_interface).
  * Microsoft apparently uses this approach to auto-generate
  (via another DSL) the *Azure* interfaces for diverse programming languages.
  * [jOOQ](https://www.jooq.org/) does this for database queries.
* Use an IDE's metaprogramming framework based on EMF or
  [use Xtext](https://tomassetti.me/develop-dsls-for-eclipse-and-intellij/).

## Groovy-specific notes
* DSLs can be easily implemented in Goovy and used from Java.
* As an example consider the
  [Goovy DSL](https://github.com/sgoings/jenkins-pipeline-dsl) that is
  [used for constructing Jenkins job pipelines](https://tech.gogoair.com/jenkins-jobs-as-code-with-groovy-dsl-c8143837593a).
* A DSL implemented in Groovy provides Eclipse editing support.
* The marginal cost of including Groovy as a dependency in a Java project
  may be zero, because many Java projects already use
  [Spock](https://en.wikipedia.org/wiki/Spock_(testing_framework)) for
  testing.
* The project's host (the `main` entry point) has to be Groovy, which may
  be a problem if the Java project is based on a framework with its own
  entry point.
* It is better to start with a dynamic implementation of the DSL, and once
  things get stable, implement it in a static manner to get a more usable
  and safe implementation.
* When designing an internal DSL you need to carefully balance how much
  general purpose language functionality you expose to make the DSL
  usable and flexible, versus how much you take away to avoid giving your
  users rope to hang themselves.
