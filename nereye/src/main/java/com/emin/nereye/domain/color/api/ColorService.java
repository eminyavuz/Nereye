package com.emin.nereye.domain.color.api;

import com.emin.nereye.domain.color.api.colorDto.ColorCreateDto;
import com.emin.nereye.domain.color.api.colorDto.ColorReadDto;
import com.emin.nereye.domain.color.api.colorDto.ColorUpdateDto;
import com.emin.nereye.domain.color.impl.Color;

import java.util.List;

public interface ColorService {
    List<Color> findAll();

    Color findById(int theId);

    void deleteById(int theId);

    ColorCreateDto save(Color color);

    void update(ColorUpdateDto color, int theId);

    ColorReadDto getColor(Color color);

    List<ColorReadDto> getAll(List<Color> colors);


}
