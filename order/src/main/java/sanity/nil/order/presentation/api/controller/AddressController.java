package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.order.dto.address.AddressDTO;
import sanity.nil.order.application.order.dto.address.CreateAddressDTO;
import sanity.nil.order.application.order.dto.address.UpdateAddressDTO;
import sanity.nil.order.application.order.interfaces.interactors.CreateAddressInteractor;
import sanity.nil.order.application.order.interfaces.interactors.GetAddressInteractor;
import sanity.nil.order.application.order.interfaces.interactors.UpdateAddressInteractor;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final CreateAddressInteractor createAddressInteractor;
    private final UpdateAddressInteractor updateAddressInteractor;
    private final GetAddressInteractor getAddressInteractor;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody CreateAddressDTO createDTO) {
        return ResponseEntity
                .status(201)
                .body(createAddressInteractor.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody UpdateAddressDTO updateDTO) {
        return ResponseEntity
                .status(201)
                .body(updateAddressInteractor.update(updateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable UUID id) {
        return ResponseEntity
                .status(200)
                .body(getAddressInteractor.getAddressDTOById(id));
    }
}
