package capgemini.gameshop.users.clients;

import capgemini.gameshop.users.dto.AdressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "users", contextId ="adresses", path = "/api/adresses")
public interface AdressClient {

    @GetMapping
    List<AdressDto> getAdresses();

    @GetMapping("{id}")
    AdressDto getAdress(@PathVariable Long id);

    @GetMapping(value= "zip/{zip}", produces = {MediaType.APPLICATION_JSON_VALUE})
    AdressDto getAdressByZip(@PathVariable String zip);

    @PostMapping
    AdressDto create(@Valid @RequestBody AdressDto adressDto);

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable Long id,@Valid @RequestBody AdressDto adressDto);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
