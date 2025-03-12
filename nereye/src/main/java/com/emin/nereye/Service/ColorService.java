package com.emin.nereye.Service;

import com.emin.nereye.dto.ColorDto.ColorCreateDto;
import com.emin.nereye.dto.ColorDto.ColorReadDto;
import com.emin.nereye.dto.ColorDto.ColorUpdateDto;
import com.emin.nereye.entity.Color;

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
