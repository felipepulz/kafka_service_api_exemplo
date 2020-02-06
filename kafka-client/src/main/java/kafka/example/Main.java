package kafka.example;

public class Main {

    public static void main(String[] args) {
        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
        consumerThread.start();
    }

}
