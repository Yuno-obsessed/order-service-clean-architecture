package sanity.nil.order.application.product.interfaces.command;

import java.util.UUID;

public interface DeleteProductCommand {

    UUID delete(UUID id);
}
