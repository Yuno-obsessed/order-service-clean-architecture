package sanity.nil.order.presentation.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sanity.nil.order.application.order.exceptions.ImagesLimitException;
import sanity.nil.order.application.product.dto.boundary.ProductDTO;
import sanity.nil.order.application.product.dto.boundary.ProductImageInfo;
import sanity.nil.order.application.product.dto.boundary.ProductStatisticsDTO;
import sanity.nil.order.application.product.dto.command.*;
import sanity.nil.order.application.product.dto.query.ProductQueryDTO;
import sanity.nil.order.application.product.dto.query.ProductQueryFilters;
import sanity.nil.order.application.product.service.ProductCommandService;
import sanity.nil.order.application.product.service.ProductQueryService;
import sanity.nil.order.domain.product.entity.Product;
import sanity.nil.order.infrastructure.database.orm.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductQueryDTO> getProductById(@PathVariable UUID id){
        return ResponseEntity
                .status(200)
                .body(productQueryService.getProductByIdQuery.handle(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductQueryDTO>> getAllProducts(
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String productSubtype,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer priceAbove,
            @RequestParam(required = false) Integer priceBelow,
            @RequestParam(required = false) String order) {
        ProductQueryFilters filters = new ProductQueryFilters(limit, offset, order,
                productType, productSubtype, priceBelow, priceAbove);
        return ResponseEntity
                .status(200)
                .body(productQueryService.getAllProductsQuery.handle(filters));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductQueryDTO> getProductByName(@PathVariable String name){
        return ResponseEntity
                .status(200)
                .body(productQueryService.getProductsByNameQuery.handle(name));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductCommandDTO createDTO) {
        Product product = productCommandService.createProductCommand.handle(createDTO);
        return ResponseEntity
                .status(201)
                .body(ProductMapper.convertEntityToBoundary(product));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductCommandDTO updateDTO) {
        Product product = productCommandService.updateProductCommand.handle(updateDTO);
        return ResponseEntity
                .status(201)
                .body(ProductMapper.convertEntityToBoundary(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProduct(@PathVariable UUID id) {
       return ResponseEntity
               .status(404)
               .body(productCommandService.deleteProductCommand.handle(id));
    }

    @PutMapping("/statistics/addrate")
    public ResponseEntity<ProductStatisticsDTO> addRating(@RequestBody UpdateProductRateDTO productStatisticsDTO) {
        Product product = productCommandService.updateProductStatisticsCommand.handle(productStatisticsDTO);
        return ResponseEntity
                .status(201)
                .body(ProductMapper.convertEntityToProductStatisticsDTO(product));
    }

    @PutMapping("/statistics/addtowishlist")
    public ResponseEntity<ProductStatisticsDTO> addToWishList(@RequestBody UpdateProductWishListDTO productStatisticsDTO) {
        Product product = productCommandService.updateProductStatisticsCommand.handle(productStatisticsDTO);
        return ResponseEntity
               .status(201)
               .body(ProductMapper.convertEntityToProductStatisticsDTO(product));
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> uploadProductPhotos(@RequestParam("files") MultipartFile[] images,
                                                          @RequestParam("product_id") String productID) {
        UploadProductImages uploadProductImages = new UploadProductImages(UUID.fromString(productID), List.of(images));
        if (images.length > 8) {
            throw new ImagesLimitException();
        }
        Product product = productCommandService.addImagesCommand
                .handle(ProductMapper.convertUploadImagesToFileData(uploadProductImages));
        return ResponseEntity
                .status(200)
                .body(ProductMapper.convertEntityToBoundary(product));
    }

    @GetMapping(value = "/images/{id}")
    public ResponseEntity<List<ProductImageInfo>> getAllImages(@PathVariable("id") UUID productID,
                                                               @RequestParam(required = false, name = "onlyMain") Boolean onlyMain) {
        return ResponseEntity
                .status(200)
                .body(productQueryService.getAllImagesQuery.handle(productID, onlyMain));
    }
}
