package com.emin.nereye.domain.advertisement.api.advertisementDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdvertisementUpdateDto {
    private String daily_price;
    private String deposit;
    private String location;

}
