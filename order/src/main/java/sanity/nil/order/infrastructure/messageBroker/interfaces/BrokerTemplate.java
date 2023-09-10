package sanity.nil.order.infrastructure.messageBroker.interfaces;

public interface BrokerTemplate {

    void publishMessage(byte[] message);
}
