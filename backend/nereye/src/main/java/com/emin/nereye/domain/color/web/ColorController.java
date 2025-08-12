package com.emin.nereye.domain.color.web;

import com.emin.nereye.domain.color.api.ColorDto;
import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {
    private ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/save")
    public ColorDto save(@RequestBody ColorDto dto) {
        return colorService.save(dto);
    }

    @GetMapping("/getAll")
    public List<ColorDto> getAll() {
        return colorService.findAll();
    }

    @GetMapping("/{id}")
    public ColorDto get(@PathVariable int id) {
        return colorService.findById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody ColorDto dto) {
        colorService.update(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        colorService.deleteById(id);
    }
}
