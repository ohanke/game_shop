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

    private final ProductService productService;
    private final CircuitBreakerFactory factory;

    @GetMapping()
    public List<ProductDto> getProducts(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return factory.create("productService").run(() -> productService.findById(id));
    }

    @PostMapping()
    public ProductDto create(@Valid @RequestBody ProductDto productDto){
        return productService.save(productDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody ProductDto productDto){
        productService.update(id, productDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
