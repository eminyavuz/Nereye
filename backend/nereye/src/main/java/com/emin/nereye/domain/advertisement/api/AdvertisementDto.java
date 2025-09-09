package com.emin.nereye.domain.advertisement.api;

import com.emin.nereye.domain.car.api.CarDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {
    private int ad_id;
    private int daily_price;
    private String location;
    private int deposit;
    private CarDto car;
    private Integer owner_id;
    private Integer tenet_id;
}
