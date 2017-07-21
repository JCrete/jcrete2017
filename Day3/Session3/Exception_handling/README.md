# Errors and exception handling
Convened by [Diomidis](https://www.spinellis.gr).

## Proposed session topics
* Recovery methods
* Exception classes
* Testing
* Language approaches
* Compiler help
* Static analysis tools

## What can you do when you encounter an exception
* Fail fast; give up and let some other part handle the problem at a higher
  level
* Retry.  This can be dangerous, unless the underlying calls are idempotent
* Prompt the user for a correct reply
* Return a cached result

Note: The UX part is difficult to get right, but must be designed from the
beginning to account for all possible failure modes.

## Approaches for graceful exception handling
* Utilize the Java 8 [CompletionStage](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html) API
* Design your architecture so that clients can be able to recover from failed
  service calls
* Pass the error at a higher level where it can be handled in a more inteligent
  manner.  For example, you can rethrow I/O exceptions as runtime exceptions.
  However, this looses the exception's type.
* A better approach involves the use of Lonbok's
  [sneaky throwing](https://projectlombok.org/features/SneakyThrows)
  annotation, which allows you to declare that a method's exceptions
  are handled at a higher level, and thus avoid the boilerplate code.
  An [exception handling policy](http://java-monitor.com/forum/showthread.php?t=4169)
  can formalize how exceptions are handled using this mechanism.
* At lower levels, handle all exceptions as unchecked. This avoids errors that
  developers commit when they encounter an exception and they don't know how
  to handle it (e.g. they return `null` or `-1`).

Note: Asynchronous code complicates the picture.

## Approaches to generate exceptions for testing
* Use the [WireMock](http://wiremock.org/) library to simulate HTTP-level failures
* Use [Linux tc](https://en.wikipedia.org/wiki/Tc_(Linux)) (traffic control)
  to simulate delays and increased latency
* In a microservice environment inject failures through a container sidecar
* For unit tests, you can pass around a custom `FileSystem` object
  (to simulate file system failures) or a `Clock` object to simulate the
  advancement of time.

Note: In unit tests, avoid looking at the exception's error message.

## Links

* [Exception handling policy](http://java-monitor.com/forum/showthread.php?t=4169)
* [Sneaky Throwing](https://projectlombok.org/features/SneakyThrows)
