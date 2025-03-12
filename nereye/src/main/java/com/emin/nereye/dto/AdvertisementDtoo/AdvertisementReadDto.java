package com.emin.nereye.dto.AdvertisementDtoo;

import com.emin.nereye.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AdvertisementReadDto {
    private  String  daily_price;
    private  String deposit ;
    private  String  location;
    private Car car;
}
