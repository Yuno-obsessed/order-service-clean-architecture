package sanity.nil.order.application.service;

import lombok.RequiredArgsConstructor;
import sanity.nil.order.application.command.CreateAddressCommand;
import sanity.nil.order.application.command.UpdateAddressCommand;

@RequiredArgsConstructor
public class AddressCommandService {

    public final CreateAddressCommand createAddressCommand;
    public final UpdateAddressCommand updateAddressCommand;
}
