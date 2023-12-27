package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.order.dto.command.*;
import sanity.nil.order.application.order.dto.query.OrderQueryDTO;
import sanity.nil.order.application.order.dto.query.OrderQueryFilters;
import sanity.nil.order.application.order.dto.response.*;
import sanity.nil.order.application.order.service.OrderCommandService;
import sanity.nil.order.application.order.service.OrderQueryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody CreateOrderCommandDTO createOrderCommandDTO) {
        return ResponseEntity
                .status(201)
                .body(orderCommandService.createOrderCommand.handle(createOrderCommandDTO));
    }

    @PostMapping("/product")
    public ResponseEntity<AddedOrderProductDTO> addProduct(@RequestBody AddOrderProductCommandDTO addOrderProductCommandDTO) {
        return ResponseEntity
                .status(201)
                .body(orderCommandService.addOrderProductCommand.handle(addOrderProductCommandDTO));
    }

    @PutMapping("/address")
    public ResponseEntity<UpdatedOrderAddressDTO> updateAddress(@RequestBody UpdateOrderAddressCommandDTO updateOrderAddressCommandDTO) {
        return ResponseEntity
                .status(200)
                .body(orderCommandService.updateOrderAddressCommand.handle(updateOrderAddressCommandDTO));
    }

    @DeleteMapping("/product")
    public ResponseEntity<RemovedOrderProductDTO> removeProduct(@RequestBody RemoveOrderProductCommandDTO removeOrderProductCommandDTO) {
        return ResponseEntity
                .status(204)
                .body(orderCommandService.removeOrderProductCommand.handle(removeOrderProductCommandDTO));
    }

    @PutMapping("/product/quantity")
    public ResponseEntity<UpdatedOrderProductQuantityDTO> updateProductQuantity(@RequestBody UpdateOrderProductQuantityCommandDTO updateOrderProductQuantityCommandDTO) {
        return ResponseEntity
                .status(200)
                .body(orderCommandService.updateProductQuantityCommand.handle(updateOrderProductQuantityCommandDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderQueryDTO> getCart(@PathVariable UUID id) {
        return ResponseEntity
                .status(200)
                .body(orderQueryService.getOrderByIDQuery.handle(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderQueryDTO>> getAllOrders(@RequestParam(required = false) Integer limit,
                                                            @RequestParam(required = false) Integer offset,
                                                            @RequestParam(required = false) String order,
                                                            @RequestParam(required = false) String orderBy,
                                                            @RequestParam(required = false) String withStatus) {
        OrderQueryFilters filters = new OrderQueryFilters(limit, offset, order, orderBy, withStatus);
        return ResponseEntity
                .status(200)
                .body(orderQueryService.getAllOrdersQuery.handle(filters));
    }
}
