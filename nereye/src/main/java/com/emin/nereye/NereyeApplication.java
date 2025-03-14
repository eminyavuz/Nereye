package com.emin.nereye;

import com.emin.nereye.domain.advertisement.api.AdService;
import com.emin.nereye.domain.brand.api.BrandService;
import com.emin.nereye.domain.car.api.CarService;
import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class NereyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NereyeApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(CarService carService, BrandService brandService, ColorService colorService,
                                               AdService adService) {
        return runner -> {


        };
    }


}

