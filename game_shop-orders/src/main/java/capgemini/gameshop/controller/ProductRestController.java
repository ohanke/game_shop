package capgemini.gameshop.controller;

import capgemini.gameshop.orders.dto.ProductDto;
import capgemini.gameshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final String CIRCUIT_SERVICE = "productService";
    private final ProductService productService;
    private final CircuitBreakerFactory factory;

    /**
     * @return - list of all ProductDTO's
     */
    @GetMapping()
    public List<ProductDto> getProducts(){
        return factory.create(CIRCUIT_SERVICE).run(productService::findAll);
//        return productService.findAll();
    }

    /**
     * @param id - Long type id of ProductDTO object
     * @return - ProductDTO with requested id
     */
    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return factory.create(CIRCUIT_SERVICE).run(() -> productService.findById(id));
    }

    /**
     * @param productDto - body for new ProductDTO object
     * @return - created ProductDTO
     */
    @PostMapping()
    public ProductDto create(@Valid @RequestBody ProductDto productDto){
        return factory.create(CIRCUIT_SERVICE).run(() -> productService.save(productDto));
    }

    /**
     * @param id - Long type id of ProductDTO to be updated
     * @param productDto - Body for ProductDTO update
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody ProductDto productDto){
//        factory.create("productService").run(() -> productService.update(id, productDto));
        productService.update(id, productDto);
    }

    /**
     * @param id - Long type id of ProductDTO to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
//        factory.create("productService").run(() -> productService.delete(id));
        productService.delete(id);
    }
}
