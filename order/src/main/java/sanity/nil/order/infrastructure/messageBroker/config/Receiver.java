package sanity.nil.order.infrastructure.messageBroker.config;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class Receiver {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public Receiver() {}

    public void receiveMessage(byte[] message) {
        String messageAsString = new String(message, StandardCharsets.UTF_8);
        countDownLatch.countDown();
    }
}
