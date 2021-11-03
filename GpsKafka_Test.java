import org.junit.Before;
import org.junit.Test;

public class GpsKafka_Test {

    @Test
    public void test0(){
        // Tracker0 
        String name = "Tracker0";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 1.6262556, 1.1616, 1.7494);
    }

    @Test
    public void test1(){
        // Tracker1
        String name = "Tracker1";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 40.0138816, 116.3438099, 154.2);
    }

    @Test
    public void test10(){
        // Beijing
        String name = "Beijing";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 39.916668, 116.383331, 44);
    }
}

