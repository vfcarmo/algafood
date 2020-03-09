package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.ProductAssembler;
import br.com.vfc.algafood.api.model.ProductModel;
import br.com.vfc.algafood.api.model.input.ProductInput;
import br.com.vfc.algafood.domain.model.Product;
import br.com.vfc.algafood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private ProductService productService;

    private ProductAssembler productAssembler;

    @Autowired
    public RestaurantProductController(ProductService productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long restaurantId) {

        List<Product> products = productService.findByRestaurant(restaurantId);

        return ResponseEntity.ok(productAssembler.toCollectionModel(products));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> findById(@PathVariable Long restaurantId, @PathVariable Long productId) {

        Product product = productService.findById(restaurantId, productId);

        return ResponseEntity.ok(productAssembler.toModel(product));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@PathVariable Long restaurantId, @RequestBody ProductInput input) {

        Product product = productService.save(restaurantId, productAssembler.toDomain(input));

        ProductModel model = productAssembler.toModel(product);

        return ResponseEntity.created(URI.create(String.format("/restaurants/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long restaurantId,
                              @PathVariable Long productId, @RequestBody ProductInput input) {

        Product product = productService.save(restaurantId, productAssembler.toDomain(productId, input));

        return ResponseEntity.ok(productAssembler.toModel(product));
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable Long restaurantId, @PathVariable Long productId) {

        productService.remove(restaurantId, productId);
    }
}
