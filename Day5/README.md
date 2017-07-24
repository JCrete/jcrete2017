# HackDay by Stephen Chin

## Arduino / NodeMCU workshop

Java client that interacts with NodeMCU via MQTT server topic and WiFi:

	import org.fusesource.mqtt.client.BlockingConnection;
	import org.fusesource.mqtt.client.MQTT;
	import org.fusesource.mqtt.client.QoS;

	public class MqttClient {

	    public static void main(String[] args) throws Exception {
	        MQTT mqtt = new MQTT();
	        mqtt.setHost("tcp://127.0.0.1:1883");
	        mqtt.setUserName("user");
	        mqtt.setPassword("pass");
	        BlockingConnection conn = mqtt.blockingConnection();
	        conn.connect();
	        conn.publish("RX-music", "1".getBytes(), QoS.AT_MOST_ONCE, false);
	    }

	}

You need this dependency in `build.gradle`:

	compile 'org.fusesource.mqtt-client:mqtt-client:1.12'
