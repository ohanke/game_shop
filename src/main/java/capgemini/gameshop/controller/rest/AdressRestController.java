package capgemini.gameshop.controller.rest;

import capgemini.gameshop.dto.AdressDto;
import capgemini.gameshop.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdressRestController {

    private final AdressService adressService;

    @GetMapping("/adresses")
    public List<AdressDto> getAdresses(){
        return adressService.findAll();
    }
}
