package com.emin.nereye.controller;

import com.emin.nereye.Service.ColorService;
import com.emin.nereye.entity.Color;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {
    private ColorService colorService;
    public ColorController(ColorService colorService){
        this.colorService=colorService;
    }

    @PostMapping("/save")
    public Color save(@RequestBody Color color){
        Color tmpColor=colorService.save(color);
        return tmpColor ;
    }
}
