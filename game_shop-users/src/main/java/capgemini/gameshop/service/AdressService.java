package capgemini.gameshop.service;

import capgemini.gameshop.users.dto.AdressDto;
import capgemini.gameshop.users.event.AdressCreatedEvent;
import capgemini.gameshop.users.event.AdressDeletedEvent;
import capgemini.gameshop.users.event.IntegrationEvent;
import capgemini.gameshop.exception.AdressNotFoundException;
import capgemini.gameshop.model.Adress;
import capgemini.gameshop.repository.AdressRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final KafkaTemplate<Long, IntegrationEvent> kafkaTemplate;

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
        Adress adress = mapper.map(adressDto, Adress.class);
        AdressDto savedAdress = convertToDTO(adressRepository.save(adress));
        kafkaTemplate.send("adresses-create", savedAdress.getId(),
                new AdressCreatedEvent(savedAdress.getId(), savedAdress.getUserId()));
        return savedAdress;
    }

    public void update(Long id, AdressDto adressDto) {
        adressRepository.findById(id)
                .map(adress -> updateFields(adressDto, adress))
                .orElseThrow(() -> new AdressNotFoundException(id));
    }

    public void delete(Long id) {
        AdressDto adress = mapper.map(adressRepository.findById(id).orElseThrow(() -> new AdressNotFoundException(id)), AdressDto.class);
        kafkaTemplate.send("adresses-delete", adress.getId(),
                new AdressDeletedEvent(adress.getId(), adress.getUserId()));
        adressRepository.deleteById(id);

    }

    private AdressDto updateFields(AdressDto adressDto, Adress adress) {
            adress.setCountry(adressDto.getCountry());
            adress.setCity(adressDto.getCity());
            adress.setStreet(adressDto.getStreet());
            adress.setState(adressDto.getState());
            adress.setZip(adressDto.getZip());
            return convertToDTO(adressRepository.save(adress));
        }


}
