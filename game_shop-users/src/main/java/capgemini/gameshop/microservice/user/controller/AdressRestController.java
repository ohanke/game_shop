package capgemini.gameshop.microservice.user.controller;

import capgemini.gameshop.microservice.user.dto.AdressDto;
import capgemini.gameshop.microservice.user.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/adresses")
@RequiredArgsConstructor
public class AdressRestController {

    private final AdressService adressService;

    @GetMapping("/")
    public List<AdressDto> getAdresses(){
        return adressService.findAll();
    }

    @GetMapping("/{id}")
    public AdressDto getAdress(@PathVariable Long id) {
        return adressService.findById(id);
    }

    @GetMapping(value= "/zip/{zip}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AdressDto getAdressByZip(@PathVariable String zip) {
        return adressService.findByZip(zip);
    }


    @PostMapping(value = "/")
    public AdressDto create(@Valid @RequestBody AdressDto adressDto){
        return adressService.save(adressDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@Valid @RequestBody AdressDto adressDto){
        adressService.update(id, adressDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        adressService.delete(id);
    }
}
