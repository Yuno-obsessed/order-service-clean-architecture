package sanity.nil.order.application.order.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.order.command.CreateAddressCommand;
import sanity.nil.order.application.order.command.UpdateAddressCommand;

@RequiredArgsConstructor
public class AddressCommandService {

    public final CreateAddressCommand createAddressCommand;
    public final UpdateAddressCommand updateAddressCommand;
}
