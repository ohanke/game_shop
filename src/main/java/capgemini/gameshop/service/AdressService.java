package capgemini.gameshop.service;

import capgemini.gameshop.dto.AdressDto;
import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.entity.Order;
import capgemini.gameshop.repository.AdressRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service for AdressRepository.
 *
 *  Calls UserRepository to get required Entities of Adress.
 *  Calls the AdressToAdressDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Product
 */
@Service
@AllArgsConstructor
public class AdressService {
    private AdressRepository adressRepository;
    private ModelMapper mapper;

    public AdressDto convertToAdressDTO (Adress adress) {
        AdressDto adressDto = new AdressDto();
        adressDto = mapper.map(adress, AdressDto.class);
        return adressDto;
    }

    //TODO handle possible null return
    public List<AdressDto> findAll(){
        return adressRepository.findAll().stream().map(this::convertToAdressDTO).collect(Collectors.toList());
    }
}
