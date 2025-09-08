package com.emin.nereye.domain.advertisement.api;

import com.emin.nereye.domain.car.impl.Car;
import com.emin.nereye.domain.user.impl.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SecondaryRow;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdvertisementDto {
    private int ad_id;
    private int daily_price;
    private String location;
    private int deposit;
    private Car car;
    private User owner_id;
    private User tenet_id;
}
