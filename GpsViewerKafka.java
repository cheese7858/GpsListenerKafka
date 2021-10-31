/**
 *******************************************************************************
 * @file GpsListenerKafka/GpsViewerKafka.java
 * @author Daolin Chen - a1838238
 * @date 30102021
 * @brief A viewer that uses kafka consumer to receive messages from running
 * kafka instances on user input
 *******************************************************************************
 */
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class GpsViewerKafka {
    public static void main(String[] args) {
        //properties
        Properties props = new Properties();
        //initiate kafka server 
        props.put("bootstrap.servers", "192.168.244.128:9092");
        props.put("group.id", "test");
        //set strings as key/value pairs
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        props.put("session.timeout.ms", 6000);
        props.put("heartbeat.interval.ms", 2000);
        //initiate consumer
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
        //subscribe topic
        System.out.println("Please submit Tracker Number：");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        consumer.subscribe(Arrays.asList("SimpleTracker"+id));
        System.out.println("Start Tracker"+id);

        //read records from server 
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(500));
            for(ConsumerRecord<String,String> record : records){
                System.out.println("Tracker"+id+"'s output:  " + record.value());
            }
        }
    }
}