package com.emin.nereye;

import com.emin.nereye.Service.*;
import com.emin.nereye.dto.AdvertisementDto;
import com.emin.nereye.dto.CarDto;
import com.emin.nereye.entity.Advertisement;
import com.emin.nereye.entity.Brand;
import com.emin.nereye.entity.Car;
import com.emin.nereye.entity.Color;
import com.emin.nereye.enumeration.fuel_type;
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
											   AdService adService){
		return runner->{
			brandService.deleteById(2);
		};
		}


}

