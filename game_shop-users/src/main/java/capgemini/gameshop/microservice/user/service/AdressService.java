package capgemini.gameshop.microservice.user.service;

import capgemini.gameshop.microservice.user.dto.AdressDto;
import capgemini.gameshop.microservice.user.exception.AdressNotFoundException;
import capgemini.gameshop.microservice.user.model.Adress;
import capgemini.gameshop.microservice.user.repository.AdressRepository;
import lombok.AllArgsConstructor;
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



    private AdressDto convertToDTO(Adress entity) {
        return mapper.map(entity, AdressDto.class);
    }

    private Adress convertToEntity(AdressDto dto){
        return mapper.map(dto, Adress.class);
    }

    //TODO handle possible null return
    public List<AdressDto> findAll(){
        return adressRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdressDto findById(Long id) {
        return adressRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new AdressNotFoundException(id));
    }

    public AdressDto findByZip(String zip) {
        Adress adress = adressRepository.findByZip(zip).orElseThrow(() -> new AdressNotFoundException(zip));
        return mapper.map(adress, AdressDto.class);
    }

    public AdressDto create(AdressDto adressDto) {

            return convertToDTO(adressRepository.save(mapper.map(adressDto, Adress.class)));
        }

    public void update(Long id, AdressDto adressDto) {
        adressRepository.findById(id)
                .map(adress -> updateFields(adressDto, adress))
                .orElseThrow(() -> new AdressNotFoundException(id));
    }

    public void delete(Long id) {
        if (adressRepository.findById(id).isEmpty()) {
            throw new AdressNotFoundException(id);
        } else adressRepository.deleteById(id);
    }

    private AdressDto updateFields(AdressDto adressDto, Adress adress) {
            adress.setCountry(adressDto.getCountry());
            adress.setCity(adressDto.getCity());
            adress.setStreet(adressDto.getStreet());
            adress.setState(adressDto.getState());
            adress.setZip(adressDto.getZip());
            return convertToDTO(adressRepository.save(adress));
        }


    public AdressDto save(AdressDto adressDto) {
        Adress adressToSave = convertToEntity(adressDto);
        Adress savedAdress = adressRepository.save(adressToSave);
        return convertToDTO(savedAdress);
    }



}
