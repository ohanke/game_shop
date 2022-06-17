package capgemini.gameshop.mapper;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Maps entities of Product to DTO type
 *
 *  First method takes in one entity and returns one DTO
 *
 *  Second method takes in a List of entities and returns a List of DTO's
 */
public class ProductToProductDtoMapper {
    public static ProductDto map(Product entity){
        return new ProductDto(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getPriceNett(),
                entity.getPriceGross()
        );
    }

    public static List<ProductDto> map(List<Product> entities){
        return entities
                .stream()
                .map(ProductToProductDtoMapper::map)
                .collect(Collectors.toList());
    }
}
