package capgemini.gameshop.controller;

import capgemini.gameshop.users.dto.AdressDto;
import capgemini.gameshop.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/adresses")
@RequiredArgsConstructor
public class AdressRestController {

    private final String CIRCUIT_SERVICE = "adressService";
    private final AdressService adressService;
    private final CircuitBreakerFactory factory;

    @GetMapping
    public List<AdressDto> getAdresses(){
        return factory.create(CIRCUIT_SERVICE).run(adressService::findAll);
    }

    @GetMapping("{id}")
    public AdressDto getAdress(@PathVariable Long id) {
        return factory.create(CIRCUIT_SERVICE).run(() -> adressService.findById(id));
    }

    @GetMapping(value= "zip/{zip}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AdressDto getAdressByZip(@PathVariable String zip) {
        return factory.create(CIRCUIT_SERVICE).run(() -> adressService.findByZip(zip));
    }


    @PostMapping
    public AdressDto create(@Valid @RequestBody AdressDto adressDto){
        return factory.create(CIRCUIT_SERVICE).run(() -> adressService.create(adressDto));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody AdressDto adressDto){
        adressService.update(id, adressDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        adressService.delete(id);
    }
}
