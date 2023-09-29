package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.command.CreateProductCommandDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductCommandDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductStatisticsDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.dto.query.ProductStatisticsQueryDTO;
import sanity.nil.order.application.product.interfaces.command.CreateProductCommand;
import sanity.nil.order.application.product.interfaces.command.DeleteProductCommand;
import sanity.nil.order.application.product.interfaces.command.UpdateProductCommand;
import sanity.nil.order.application.product.interfaces.command.UpdateProductStatisticsCommand;
import sanity.nil.order.application.product.interfaces.query.GetAllProductsQuery;
import sanity.nil.order.application.product.interfaces.query.GetProductByIdQuery;
import sanity.nil.order.application.product.interfaces.query.GetProductByNameQuery;

import java.util.List;
import java.util.UUID;

import static sanity.nil.order.application.common.dto.BaseFilters.Order.valueOf;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductCommand createProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final DeleteProductCommand deleteProductCommand;
    private final UpdateProductStatisticsCommand updateProductStatisticsCommand;
    private final GetAllProductsQuery getAllProductsQuery;
    private final GetProductByIdQuery getProductByIdQuery;
    private final GetProductByNameQuery getProductByNameQuery;

    @GetMapping("/search/{id}")
    public ResponseEntity<ProductQueryDTO> getProductById(@PathVariable UUID id){
        return ResponseEntity
                .status(200)
                .body(getProductByIdQuery.get(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductQueryDTO>> getAllProducts(
            @RequestParam String productType,
            @RequestParam String productSubtype,
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam int priceAbove,
            @RequestParam int priceBelow,
            @RequestParam String order){
        ProductQueryFilters filters = new ProductQueryFilters(limit, offset, valueOf(order),
                productType, productSubtype, priceBelow, priceAbove);
        return ResponseEntity
                .status(200)
                .body(getAllProductsQuery.getAllProductsWithProductFilters(filters));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductQueryDTO> getProductByName(@PathVariable String name){
        return ResponseEntity
                .status(200)
                .body(getProductByNameQuery.get(name));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductCommandDTO createDTO) {
        return ResponseEntity
                .status(201)
                .body(createProductCommand.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductCommandDTO updateDTO) {
        return ResponseEntity
                .status(201)
                .body(updateProductCommand.update(updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProductById(@PathVariable UUID id) {
       return ResponseEntity
               .status(404)
               .body(deleteProductCommand.delete(id));
    }

    @PutMapping("/statistics/addrate")
    public ResponseEntity<ProductStatisticsQueryDTO> addRating(@RequestBody UpdateProductStatisticsDTO productStatisticsDTO) {
        return ResponseEntity
                .status(201)
                .body(updateProductStatisticsCommand.addRating(productStatisticsDTO));
    }

    @PutMapping("/statistics/addtowishlist")
    public ResponseEntity<ProductStatisticsQueryDTO> addToWishList(@RequestBody UpdateProductStatisticsDTO productStatisticsDTO) {
       return ResponseEntity
               .status(201)
               .body(updateProductStatisticsCommand.addToWishList(productStatisticsDTO));
    }

}
