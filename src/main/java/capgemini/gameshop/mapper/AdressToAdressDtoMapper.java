package capgemini.gameshop.mapper;

import capgemini.gameshop.dto.AdressDto;
import capgemini.gameshop.entity.Adress;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Maps entities of Adress to DTO type
 *
 *  First method takes in one entity and returns one DTO
 *
 *  Second method takes in a List of entities and returns a List of DTO's
 */
public class AdressToAdressDtoMapper {
    public static AdressDto map(Adress entity){
        return new AdressDto(
                entity.getId(),
                entity.getCountry(),
                entity.getAddress(),
                entity.getState(),
                entity.getCity(),
                entity.getZip()
        );
    }

    public static List<AdressDto> map(List<Adress> entities){
        return entities
                .stream()
                .map(AdressToAdressDtoMapper::map)
                .collect(Collectors.toList());
    }
}
