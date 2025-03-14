package com.emin.nereye.domain.advertisement.api.advertisementDto;

import com.emin.nereye.domain.car.impl.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementCreateDto {
    private String daily_price;
    private String deposit;
    private String location;
    private Car car;
}
