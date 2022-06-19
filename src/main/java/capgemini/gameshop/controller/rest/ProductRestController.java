package capgemini.gameshop.controller.rest;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.service.ProductSerive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductSerive productSerive;

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        return productSerive.getAllProducts();
    }
}
