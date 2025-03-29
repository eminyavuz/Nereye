package com.emin.nereye.domain.color.impl;
import com.emin.nereye.domain.color.api.ColorDto;
import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorMapper colorMapper;
    private ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository, ColorMapper colorMapper) {
        this.colorRepository = colorRepository;
        this.colorMapper = colorMapper;

    }

    @Override
    public List<ColorDto> findAll() {
        List<Color> colors = colorRepository.findAll();
        List<ColorDto> dto = new ArrayList<>();
        for (Color c : colors)
        {
            dto.add(colorMapper.toColorDto(c));

        }
        return dto;
    }

    @Override
    public ColorDto findById(int theId) {
        Optional<Color> result = colorRepository.findById(theId);

        Color color = null;
        if (result.isPresent()) {
            color = result.get();

        } else throw new RuntimeException("Color did not found");
        return colorMapper.toColorDto(color);

    }

    public ColorDto getColor(Color color) {

        return  colorMapper.toColorDto(color);
    }

   /* @Override
    public List<ColorReadDto> getAll(List<Color> colors) {
        List<ColorReadDto> dtoList = new ArrayList<>();
        ColorReadDto dto = new ColorReadDto();
        for (Color c : colors) {
            dto = colorMapper.colorToColorReadDto(c);
            dtoList.add(dto);
        }
        return dtoList;
    }*/

    @Override
    @Transactional
    public void deleteById(int theId) {
        colorRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public ColorDto save(ColorDto color) {
        colorRepository.save(colorMapper.toColor(color));
        return color;
    }


    @Override
    public void update(ColorDto dto, int theId) {
        Color color = colorMapper.toColor(dto);
        color.setColor_id(theId);
        colorRepository.save(color);
    }

    @Override
    public ColorDto getColor(ColorDto color) {
        return null;
    }
}
