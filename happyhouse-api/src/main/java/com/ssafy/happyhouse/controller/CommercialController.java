package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.CommercialDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.CommercialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/commercial")
public class CommercialController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private CommercialService commercialService;

    @GetMapping("/{code}")
    @ResponseBody
    public ResponseEntity<List<CommercialDto>> select(@PathVariable String code) throws Exception {
        CommercialDto commercialDto = new CommercialDto();
        commercialDto.setDong_b_code(code);
        logger.debug("notice : {}", commercialService.select(commercialDto));
        return new ResponseEntity<List<CommercialDto>>(commercialService.select(commercialDto), HttpStatus.OK);
    }
}
