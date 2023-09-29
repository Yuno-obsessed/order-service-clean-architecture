package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.boundary.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.command.CreateProductCommandDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductCommandDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductRateDTO;
import sanity.nil.order.application.product.dto.command.UpdateProductWishListDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.service.ProductCommandService;
import sanity.nil.order.application.product.service.ProductQueryService;

import java.util.List;
import java.util.UUID;

import static sanity.nil.order.application.common.dto.BaseFilters.Order.valueOf;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @GetMapping("/search/{id}")
    public ResponseEntity<ProductQueryDTO> getProductById(@PathVariable UUID id){
        return ResponseEntity
                .status(200)
                .body(productQueryService.getProductByIdQuery.handle(id));
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
                .body(productQueryService.getAllProductsQuery.handle(filters));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductQueryDTO> getProductByName(@PathVariable String name){
        return ResponseEntity
                .status(200)
                .body(productQueryService.getProductsByNameQuery.handle(name));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductCommandDTO createDTO) {
        return ResponseEntity
                .status(201)
                .body(productCommandService.createProductCommand.handle(createDTO));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductCommandDTO updateDTO) {
        return ResponseEntity
                .status(201)
                .body(productCommandService.updateProductCommand.handle(updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProductById(@PathVariable UUID id) {
       return ResponseEntity
               .status(404)
               .body(productCommandService.deleteProductCommand.handle(id));
    }

    @PutMapping("/statistics/addrate")
    public ResponseEntity<ProductStatisticsDTO> addRating(@RequestBody UpdateProductRateDTO productStatisticsDTO) {
        return ResponseEntity
                .status(201)
                .body(productCommandService.updateProductStatisticsCommand.handle(productStatisticsDTO));
    }

    @PutMapping("/statistics/addtowishlist")
    public ResponseEntity<ProductStatisticsDTO> addToWishList(@RequestBody UpdateProductWishListDTO productStatisticsDTO) {
       return ResponseEntity
               .status(201)
               .body(productCommandService.updateProductStatisticsCommand.handle(productStatisticsDTO));
    }

}
