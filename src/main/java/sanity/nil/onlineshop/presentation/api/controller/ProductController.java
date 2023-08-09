package sanity.nil.onlineshop.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.onlineshop.application.product.dto.CreateProductDTO;
import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.UpdateProductDTO;
import sanity.nil.onlineshop.application.product.dto.statistics.ProductStatisticsDTO;
import sanity.nil.onlineshop.application.product.dto.statistics.UpdateProductStatisticsDTO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductInteractor createProductInteractor;
    private final GetProductInteractor getProductInteractor;
    private final UpdateProductInteractor updateProductInteractor;
    private final DeleteProductInteractor deleteProductInteractor;
    private final UpdateProductStatisticsInteractor updateProductStatisticsInteractor;

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDTO> getProductById(@PathVariable UUID id){
        return ResponseEntity
                .status(201)
                .body(getProductInteractor.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO createDTO) {
        return ResponseEntity
                .status(200)
                .body(createProductInteractor.create(createDTO));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductDTO updateDTO) {
        return ResponseEntity
                .status(200)
                .body(updateProductInteractor.update(updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProductById(@PathVariable UUID id) {
       return ResponseEntity
               .status(404)
               .body(deleteProductInteractor.delete(id));
    }

    @PutMapping("/statistics/addrate")
    public ResponseEntity<ProductStatisticsDTO> addRating(@RequestBody UpdateProductStatisticsDTO productStatisticsDTO) {
        return ResponseEntity
                .status(200)
                .body(updateProductStatisticsInteractor.addRating(productStatisticsDTO));
    }

    @PutMapping("/statistics/addtowishlist")
    public ResponseEntity<ProductStatisticsDTO> addToWishList(@RequestBody UpdateProductStatisticsDTO productStatisticsDTO) {
       return ResponseEntity
               .status(200)
               .body(updateProductStatisticsInteractor.addToWishList(productStatisticsDTO));
    }

}
