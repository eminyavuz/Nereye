package com.emin.nereye.domain.advertisement.web;

import com.emin.nereye.domain.advertisement.api.AdService;
import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.car.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdService adService;
    private final CarService carService;
    @Autowired
    public AdvertisementController (AdService adService,
                                    CarService carService){
        this.carService = carService;
        this.adService = adService;
    }

    @PostMapping("/save")
    public AdvertisementDto save(@RequestBody AdvertisementDto ad , Authentication authentication){
        System.out.println("AdvertisementController - Authentication: " + authentication);
        System.out.println("AdvertisementController - Authentication Details: " + authentication.getDetails());
        System.out.println("AdvertisementController - Authentication Principal: " + authentication.getPrincipal());
        Integer userId= (Integer) authentication.getDetails();
        System.out.println("AdvertisementController - Extracted UserId: " + userId);
         return  adService.save(ad,userId);
    }

    @DeleteMapping("/delete/{id}")
    public void  delete(@PathVariable int id)
    {
        adService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    public AdvertisementDto getAd(@PathVariable int id)
    {
        return adService.getAd(id);
    }
    @PutMapping("/update")
    public AdvertisementDto update( @RequestBody AdvertisementDto dto){
        return adService.update(dto);
    }

    @GetMapping("/getAll")
    public List<AdvertisementDto> getAll() {
        return adService.getAll(adService.findAll());
    }
}
