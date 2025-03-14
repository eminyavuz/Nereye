package com.emin.nereye.dto.AdvertisementDto;

import com.emin.nereye.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementCreateDto {
    private  String  daily_price;
    private  String deposit ;
    private  String  location;
     private Car car;
}
