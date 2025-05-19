package com.emin.nereye.domain.advertisement.web;

import com.emin.nereye.domain.advertisement.api.AdService;
import com.emin.nereye.domain.advertisement.api.AdvertisementDto;
import com.emin.nereye.domain.car.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
    private final AdService adService;
    private final CarService carService;
    @Autowired
    public AdvertisementController (AdService adService,
                                    CarService carService){
        this.carService=carService;
        this.adService=adService;

    }

    @PostMapping("/save")
    public AdvertisementDto save(@RequestBody AdvertisementDto ad){
        return adService.save(ad);
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
    @PutMapping("/update/{id}")
    public AdvertisementDto update(@PathVariable int id, @RequestBody AdvertisementDto dto){
        return adService.update(id,dto);
    }
}
