package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseinfo")
@CrossOrigin(origins = "http://localhost:8080")
public class HouseInfoController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private HouseMapService houseMapService;

    @GetMapping("/{dong}")
    @ResponseBody
    public ResponseEntity<List<HouseInfoDto>> selectWithDong(@PathVariable("dong") String dong) throws Exception {
        List<HouseInfoDto> houseInfoList = houseMapService.getAptInDong(dong);
        logger.debug("notice : {}", houseInfoList);
        return new ResponseEntity<List<HouseInfoDto>>(houseInfoList, HttpStatus.OK);
    }

}
