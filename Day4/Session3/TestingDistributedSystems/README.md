# Testing distributed systems
Convenor: [Tomasz Nurkiewicz](https://github.com/nurkiewicz)

* End-to-end vs. [consumer driven contracts](https://martinfowler.com/articles/consumerDrivenContracts.html) (see: [Spring Cloud Contract](http://cloud.spring.io/spring-cloud-contract/spring-cloud-contract.html) and [PACT](https://docs.pact.io/))

* Fault testing (see: [Netflix SimianArmy](https://github.com/Netflix/SimianArmy))

* Monitoring vs. testing:
  * Metrics:
     * [Prometheus](https://prometheus.io/) 
     * [Graphite](http://graphite.readthedocs.io/en/latest/)
     * [InfluxDB](https://www.influxdata.com/)
     * [Kibana](https://www.elastic.co/products/kibana)
  * Logs:
     * [Elasticsearch Logstash Kibana](https://www.elastic.co/products)
  * Incident management:
     * [OpsGenie](https://www.opsgenie.com/)
     * [PagerDuty](https://www.pagerduty.com/)
  * Application Performance Monitoring
     * [NewRelic](https://newrelic.com)
     * [DynaTrace](https://www.dynatrace.com/)
     * [AppDynamics](https://www.appdynamics.com/)
     * [Illuminate](https://www.jclarity.com/illuminate/)
     * [To sample or not to sample](https://www.dynatrace.com/blog/apm-myth-busters-sampling-is-as-good-as-having-all-transactions/)
* Testing event-driven systems
