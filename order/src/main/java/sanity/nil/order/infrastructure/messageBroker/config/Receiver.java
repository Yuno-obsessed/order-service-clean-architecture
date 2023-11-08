package sanity.nil.order.infrastructure.messageBroker.config;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public Receiver() {}

    public void receiveMessage(byte[] message) {
        String messageAsString = new String(message, StandardCharsets.UTF_8);
        log.info("Message received: {}", messageAsString);
        countDownLatch.countDown();
    }
}
