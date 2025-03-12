package com.emin.nereye.Service;

import com.emin.nereye.dto.ColorDto;
import com.emin.nereye.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> findAll();

    Color findById(int theId);

    void deleteById(int theId);

   Color save(Color color);

    void update(ColorDto color, int theId);
    ColorDto getColor(Color color);

    List<ColorDto> getAll(List<Color> colors);
}
