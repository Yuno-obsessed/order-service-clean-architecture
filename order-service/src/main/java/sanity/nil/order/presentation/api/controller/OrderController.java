package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.order.dto.boundary.OrderDTO;
import sanity.nil.order.application.order.dto.command.CreateOrderCommandDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryFilters;
import sanity.nil.order.application.order.service.OrderCommandService;
import sanity.nil.order.application.order.service.OrderQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "http://localhost:63343", maxAge = 3600)
@RequiredArgsConstructor
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody CreateOrderCommandDTO createOrderCommandDTO) {
        return ResponseEntity
                .status(201)
                .body(orderCommandService.createOrderCommand.handle(createOrderCommandDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderQueryDTO>> getAllOrders(@RequestParam(required = false) Integer limit,
                                                            @RequestParam(required = false) Integer offset,
                                                            @RequestParam(required = false) String order,
                                                            @RequestParam(required = false) String withStatus) {
        OrderQueryFilters filters = new OrderQueryFilters(limit, offset, order, withStatus);
        return ResponseEntity
                .status(200)
                .body(orderQueryService.getAllOrdersQuery.handle(filters));
    }
}
