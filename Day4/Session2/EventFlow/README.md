# Complex flows in distributed systems
Convenor: Bernd


* SAGA pattern: long running "transaction" will undo actions if it fails
* Process manager 
* BPM in each microservice
* Event bus 
* Kafka Event Bus
* Hermes keeps track until which point the messages have been consumed and pushes them to the service
* Otherwise zookeeper
* Direct communication between the services to the next service means you couple it tightly and you have to reverse from the next to previous
* BPM could help with it to orchestrate
* Event bus is more flexible in changing the order in being able to insert a new service at some point
* Send avro messages which have a Schema 
* Have another service listening to all the events to have a state
