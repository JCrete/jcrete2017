# Java - the Bad, the Ugly & JDK9 Deprecation changes
convened by Mario Fusco and Simon Schell

## Summary - the Bad, the Ugly
Things we don't like in Java:
-Checked Exceptions
-Serializable
-Primitive types
-Cloneable
-Vector/Hashtable
-Switch on Strings
-Default qualifier is package private
-Null references
-wait()/notify()
-All objects are monitors
-Implicit default empty constructor

## Summary - JDK9 Deprecation changes
JDK9 adds since() and forRemoval() to @Deprecated.
Where we want forRemoval=true and actually might see it working:
-Vector/Hashtable
-Serializable
-Explicit Constructors in favor of factory methods
-java.sql.date/util.date
-java.util.logging

Feature for the future: Annotate modules as deprecated for Removal?