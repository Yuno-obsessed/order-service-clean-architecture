package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.order.dto.boundary.AddressDTO;
import sanity.nil.order.application.order.dto.command.CreateAddressDTO;
import sanity.nil.order.application.order.dto.command.UpdateAddressDTO;
import sanity.nil.order.application.order.dto.query.AddressQueryDTO;
import sanity.nil.order.application.order.service.AddressCommandService;
import sanity.nil.order.application.order.service.AddressQueryService;
import sanity.nil.order.domain.order.entity.Address;
import sanity.nil.order.infrastructure.database.orm.mapper.AddressMapper;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressCommandService addressCommandService;
    private final AddressQueryService addressQueryService;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody CreateAddressDTO createDTO) {
        Address address = addressCommandService.createAddressCommand.handle(createDTO);
        return ResponseEntity
                .status(201)
                .body(AddressMapper.convertEntityToAddressDTO(address));
    }

    @PutMapping
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody UpdateAddressDTO updateDTO) {
        Address address = addressCommandService.updateAddressCommand.handle(updateDTO);
        return ResponseEntity
                .status(201)
                .body(AddressMapper.convertEntityToAddressDTO(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressQueryDTO> getAddress(@PathVariable UUID id) {
        return ResponseEntity
                .status(200)
                .body(addressQueryService.getAddressQuery.handle(id));
    }
}
