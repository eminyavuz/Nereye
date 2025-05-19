package com.emin.nereye.domain.color.api;
import java.util.List;

public interface ColorService {
    List<ColorDto> findAll();

    ColorDto findById(int theId);

    void deleteById(int theId);

    ColorDto save(ColorDto color);

    void update(ColorDto color, int theId);

    ColorDto getColor(ColorDto color);

   // List<Color> getAll(List<Color> colors);


}
