package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanity.nil.order.application.order.dto.boundary.OrderDTO;
import sanity.nil.order.application.order.dto.command.CreateOrderDTO;
import sanity.nil.order.application.order.service.OrderCommandService;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody CreateOrderDTO createOrderDTO) {
        return ResponseEntity
                .status(201)
                .body(orderCommandService.createOrderCommand.handle(createOrderDTO));
    }
}
