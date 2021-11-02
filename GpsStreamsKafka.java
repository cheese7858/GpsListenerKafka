/**
 *******************************************************************************
 * @file GpsListenerKafka/GpsStreamKafka.java
 * @author Daolin Chen - a1838238
 * @date 30102021
 * @brief Use kafka stream to perform transformations on the events from the 
 * producer instance
 *******************************************************************************
 */
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.TopicNameExtractor;
import org.apache.kafka.streams.processor.internals.StaticTopicNameExtractor;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.kafka.common.TopicCollection;
import org.apache.kafka.streams.processor.internals.InternalTopologyBuilder;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class GpsStreamsKafka {

    public static void main(String[] args) {

        // output topic
        String output = "Beijing";  
        List<String> input = Arrays.asList("Tracker0", "Tracker1", "Tracker2", "Tracker3", "Tracker4", "Tracker5",
                "Tracker6", "Tracker7", "Tracker8", "Tracker9");
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"logProcessor");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.244.128:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()); 
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());  
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,2000); 

        // use Serde for converting an object into a stream of bytes and vice versa
        Serde<String> stringSerde = Serdes.String();
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> simpleFirstStream = builder.stream(input, Consumed.with(stringSerde, stringSerde));

        // use KStream to split the stream into single string
        KStream<String, String> upperCasedStream = simpleFirstStream.filter(
                (k, line) -> Double.parseDouble(line.split(",")[0]) >= 39.5 && Double.parseDouble(line.split(",")[0]) <= 40.5
                &&Double.parseDouble(line.split(",")[1])>=115.5 && Double.parseDouble(line.split(",")[1])<=117.0);

        // output serialization/deserialization to results
        upperCasedStream.to(output, Produced.with(stringSerde, stringSerde));

        // init and start KStream
        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), properties);
        kafkaStreams.start();

        for (int i = 0; i < 10; i++) {
            // output topic
            String simpleTrackerOutput = "SimpleTracker"+i;  
            List<String> simpleTrackerInput = Arrays.asList("Tracker"+i);
            Properties pro = new Properties();
            pro.put(StreamsConfig.APPLICATION_ID_CONFIG,"logProcessor"+i);
            pro.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.244.128:9092");
            pro.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()); 
            pro.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());  
            pro.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,2000);  

            // use Serde for converting an object into a stream of bytes and vice versa
            Serde<String> serde = Serdes.String();
            StreamsBuilder builder1 = new StreamsBuilder();
            KStream<String, String> stream = builder1.stream(simpleTrackerInput, Consumed.with(serde, serde));

            // use KStream to split the stream into single string
            KStream<String, String> stream1 = stream.mapValues(
                    line -> line.split(",")[0]+","+line.split(",")[1]);

            // output serialization/deserialization to results
            stream1.to(simpleTrackerOutput, Produced.with(serde, serde));
            // init and start KStream
            KafkaStreams streams = new KafkaStreams(builder1.build(),pro);
            streams.start();
        }

    }

}

