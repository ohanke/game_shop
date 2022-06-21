package capgemini.gameshop.service;

import capgemini.gameshop.dto.AdressDto;
import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.repository.AdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //TODO handle possible null return
    public List<AdressDto> getAllAdresses(){
        return AdressToAdressDtoMapper.map(adressRepository.findAll());
    }
}
