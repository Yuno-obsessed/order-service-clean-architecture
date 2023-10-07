package sanity.nil.common.application.interfaces.broker;

import sanity.nil.common.application.exceptions.BrokerException;

public interface MessageBroker {

    void publishMessage(String exchangeName, String routingKey, byte[] message) throws BrokerException;
}
