package com.emin.nereye.Service;

import com.emin.nereye.dto.ColorDto.ColorCreateDto;
import com.emin.nereye.dto.ColorDto.ColorReadDto;
import com.emin.nereye.dto.ColorDto.ColorUpdateDto;
import com.emin.nereye.entity.Color;
import com.emin.nereye.mapper.ColorMapper;
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
private  final ColorMapper colorMapper;
    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository,ColorMapper colorMapper) {
        this.colorRepository = colorRepository;
        this.colorMapper=colorMapper;

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
       List<ColorReadDto> dtoList= new ArrayList<>();
       ColorReadDto dto= new ColorReadDto();
       for(Color c : colors){
           dto=colorMapper.colorToColorReadDto(c);
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
     Color color=colorMapper.colorUpdateDtoToColor(dto) ;
    colorRepository.save(color);
    }
}
