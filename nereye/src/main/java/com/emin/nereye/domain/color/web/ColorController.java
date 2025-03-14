package com.emin.nereye.domain.color.web;

import com.emin.nereye.domain.color.api.ColorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {
    private ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

   /* @PostMapping("/save")
    public Color save(@RequestBody Color color) {
        Color tmpColor = colorService.save(color);
        return tmpColor;
    }*/
}
