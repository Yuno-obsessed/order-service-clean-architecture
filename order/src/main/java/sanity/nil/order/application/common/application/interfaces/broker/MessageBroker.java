package sanity.nil.order.application.common.application.interfaces.broker;

import sanity.nil.order.application.common.application.exceptions.BrokerException;

public interface MessageBroker {

    void publishMessage(String exchangeName, String routingKey, byte[] message) throws BrokerException;
}
