import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


public class GpsKafka_Test {

    @Before
    public void test1(){
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
        //subscribe to topic
        System.out.println("Please submit the Tracker Number：");
        Scanner scanner = new Scanner(System.in);
        String name = "Tracker1";
        consumer.subscribe(Arrays.asList(name));
        System.out.println("Start subscribe"+name);
        
        //read records from server
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String,String> record : records){
                System.out.println(name+"'s output:  " + record.value());
            }
        }
    }

    @Test
    public void test2(){
        GpsService service = new GpsService();
        service.run();
    }

}
