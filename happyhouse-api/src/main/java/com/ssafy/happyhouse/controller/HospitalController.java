package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.service.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("")
    @ResponseBody
    public List<HospitalDto> select(@RequestParam String sido, @RequestParam String gugun) throws Exception {
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setSido(sido);
        hospitalDto.setGugun(gugun);

        logger.debug("hospitals: {}", hospitalService.select(hospitalDto));
        return hospitalService.select(hospitalDto);
    }
}
