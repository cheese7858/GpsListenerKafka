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
    public void test2(){
        // Tracker2
        String name = "Tracker2";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 35.652832, 139.839478, 40);
    }

    @Test
    public void test3(){
        // Tracker3
        String name = "Tracker3";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 34.052235, -118.243683, 87);
    }

    @Test
    public void test4(){
        // Tracker4
        String name = "Tracker4";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 33.8688, -151.2093, 19);
    }

    @Test
    public void test5(){
        // Tracker5
        String name = "Tracker5";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 37.8136, -144.9631, 31);
    }

    @Test
    public void test6(){
        // Tracker6
        String name = "Tracker6";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 34.9285, 138.6007, 50);
    }

    @Test
    public void test7(){
        // Tracker7
        String name = "Tracker7";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 48.8566, 2.3522, 35);
    }

    @Test
    public void test8(){
        // Tracker8
        String name = "Tracker8";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 51.5072, 0.1276, 11);
    }

    @Test
    public void test9(){
        // Tracker9
        String name = "Tracker9";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 40.7128, 74.0060, 10);
    }

    @Test
    public void test10(){
        // Beijing
        String name = "Beijing";
        GpsListenerKafka gpsListenerKafka = new GpsListenerKafka();
        gpsListenerKafka.update(name, 39.916668, 116.383331, 44);
    }
}

