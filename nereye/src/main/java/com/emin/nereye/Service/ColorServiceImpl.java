package com.emin.nereye.Service;

import com.emin.nereye.dto.ColorDto;
import com.emin.nereye.entity.Color;
import com.emin.nereye.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    private ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
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

    public ColorDto getColor(Color color) {
        ColorDto dto = new ColorDto();
        dto.colorToColorDto(color);
        return dto;
    }

    @Override
    public List<ColorDto> getAll(List<Color> colors) {
       List<ColorDto> dtoList= new ArrayList<>();
       ColorDto dto= new ColorDto();
       for(Color c : colors){
           dto.colorToColorDto(c);
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
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void update(ColorDto dto, int theId) {
     Color color= dto.dtoToColor(dto,findById(theId));
    colorRepository.save(color);
    }
}
