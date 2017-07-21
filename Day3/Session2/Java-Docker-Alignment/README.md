# Java-Docker-Alignment
Convened by [Gerd Aschemann](https://twitter.com/GerdAschemann).

## Summary

![JVM+Application in Docker Image](jcrete2017-day3-jvm+docker.jpg)

Discussion items:
* Starting point: [Spring Boot Thin Launcher](https://github.com/dsyer/spring-boot-thin-launcher) 
  * provides very thin application loader (loads all dependencies via Maven at runtime)
  * already has Docker mode (run with dryrun option to prefill Maven cache)
  * has space for optimizations, e.g., drop test dependencies and parent POMs etc.
* Possible improvement for Docker: 
  * Add dependencies as Docker layers and only replace top most layer, i.e., _the application_ (jar)
  * Will enable faster shipment of final image updates, once the base layers are downloaded
  * Changes in dependencies currently would lead to updates of some _layers_ (the dependency tree was mapped to a
    sequence, this could be somehow improved in future Docker versions?)
* Similar approach is already possible with Maven Docker plugins (make a libraries layer)
* Could also improve Java EE Micro Profile in Docker
* Then Spring Boot and Micro Profile lower layers (beyond the application) in Docker
  * could effectively become some sort of _Application Server_ (this led to some nice discussions about terminology)
  * could be _preloaded_ during runtime and allow for a _Serverless_ kind of service
* Do we expect JigSaw or JRE modularization to change the game? Apparently there was not much knowledge about that in
  the room?
* Better layering of Java/JVM in Docker could also enable _smaller_ tools, e.g., for CI servers like
  [Concourse](http://concourse.ci/)