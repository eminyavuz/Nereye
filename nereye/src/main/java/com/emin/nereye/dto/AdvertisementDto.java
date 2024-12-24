package com.emin.nereye.dto;

import com.emin.nereye.entity.Advertisement;
import com.emin.nereye.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {
    private int daily_price;
    private String location;
    private int deposit;
    private Car car;

    public AdvertisementDto adtoAdDto(Advertisement ad){
        AdvertisementDto dto= new AdvertisementDto();
        dto.setCar(ad.getCar());
        dto.setDeposit(ad.getDeposit());
        dto.setLocation(ad.getLocation());
        dto.setDaily_price(ad.getDaily_price());
        return dto;
    }

    public Advertisement dtoToAd(Advertisement ad, AdvertisementDto dto) {

        ad.setCar(dto.getCar());
        ad.setDeposit(dto.getDeposit());
        ad.setLocation(dto.getLocation());
        ad.setDaily_price(dto.getDaily_price());
        return ad;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                ", daily_price=" + daily_price +
                ", location='" + location + '\'' +
                ", deposit=" + deposit +
                ", car=" + car.getBrand().getBrand_name()+ " "+ car.getModel()+
                '}';
    }
}

