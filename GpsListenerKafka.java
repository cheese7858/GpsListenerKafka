/**
 *******************************************************************************
 * @file GpsListenerKafka/GpsListenerKafka.java
 * @author Daolin Chen - a1838238
 * @date 30102021
 * @brief A class use kafka producer to send messages to a running kafka 
 * instance
 *******************************************************************************
 */
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class GpsListenerKafka implements GpsListener{

    private static KafkaProducer<String, String> producer;

    static {
        Properties props = new Properties();
        //initiate kafka server 
        props.put("bootstrap.servers", "192.168.244.128:9092");
        //set strings as key/value pairs
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        producer = new KafkaProducer<>(props);
    }

    @Override
    public void update(String name, double latitude, double longitude, double altitude) {
        String out = name + " | Latitude:" + latitude + ", Longitude:" + longitude + ", Altitude:" + altitude;
        System.out.println(out);
        String contents = latitude + "," + longitude + "," + altitude;
        //send records
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(name,
                "coordinates", contents);
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
