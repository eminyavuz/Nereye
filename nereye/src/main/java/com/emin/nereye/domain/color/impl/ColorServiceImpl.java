package com.emin.nereye.domain.color.impl;

import com.emin.nereye.domain.color.api.colorDto.ColorCreateDto;
import com.emin.nereye.domain.color.api.colorDto.ColorReadDto;
import com.emin.nereye.domain.color.api.colorDto.ColorUpdateDto;
import com.emin.nereye.mapper.ColorMapper;
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
    public List<Color> findAll() {
        List<Color> colors = colorRepository.findAll();
        return colors;
    }

    @Override
    public Color findById(int theId) {
        Optional<Color> result = colorRepository.findById(theId);

        Color color = null;
        if (result.isPresent()) {
            color = result.get();

        } else throw new RuntimeException("Color did not found");
        return color;

    }

    public ColorReadDto getColor(Color color) {

        return colorMapper.colorToColorReadDto(color);
    }

    @Override
    public List<ColorReadDto> getAll(List<Color> colors) {
        List<ColorReadDto> dtoList = new ArrayList<>();
        ColorReadDto dto = new ColorReadDto();
        for (Color c : colors) {
            dto = colorMapper.colorToColorReadDto(c);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        colorRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public ColorCreateDto save(Color color) {
        colorRepository.save(color);
        return colorMapper.colorToColorCreateDto(color);
    }


    @Override
    public void update(ColorUpdateDto dto, int theId) {
        Color color = colorMapper.colorUpdateDtoToColor(dto);
        colorRepository.save(color);
    }
}
