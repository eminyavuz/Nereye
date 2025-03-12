package com.emin.nereye.dto.AdvertisementDtoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AdvertisementUpdateDto {
    private String daily_price;
    private String deposit;
    private  String  location;

}
