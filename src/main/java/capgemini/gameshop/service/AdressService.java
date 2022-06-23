package capgemini.gameshop.service;

import capgemini.gameshop.dto.AdressDto;
import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.repository.AdressRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AdressService {
    private final AdressRepository adressRepository;
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //TODO handle possible null return
    public List<AdressDto> getAllAdresses(){
        return adressRepository.findAll().stream().map(adress -> mapper.map(adress, AdressDto.class)).collect(Collectors.toList());
    }
}
