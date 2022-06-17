package capgemini.gameshop.service;

import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.repository.AdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdressService {
    private final AdressRepository adressRepository;

    public List<Adress> getAllAdresses(){
        return adressRepository.findAll();
    }
}
