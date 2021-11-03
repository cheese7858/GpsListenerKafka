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

    @Test
    public void test1(){
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update("Tracker1", 1.6262556, 1.1616, 1.7494);
    }

}

