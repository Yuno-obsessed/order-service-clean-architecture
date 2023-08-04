package sanity.nil.onlineshop.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.onlineshop.application.product.dto.CreateProductDTO;
import sanity.nil.onlineshop.application.product.dto.GetProductDTO;
import sanity.nil.onlineshop.application.product.dto.ProductDTO;
import sanity.nil.onlineshop.application.product.dto.UpdateProductDTO;
import sanity.nil.onlineshop.application.product.interfaces.interactors.CreateProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.DeleteProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.GetProductInteractor;
import sanity.nil.onlineshop.application.product.interfaces.interactors.UpdateProductInteractor;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductInteractor createProductInteractor;
    private final GetProductInteractor getProductInteractor;
    private final UpdateProductInteractor updateProductInteractor;
    private final DeleteProductInteractor deleteProductInteractor;

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

}
