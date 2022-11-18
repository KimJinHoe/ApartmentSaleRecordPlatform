package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.CommercialDto;
import com.ssafy.happyhouse.model.mapper.CommercialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialServiceImpl implements CommercialService {

    @Autowired
    private CommercialMapper commercialMapper;

    @Override
    public List<CommercialDto> select(CommercialDto commercialDto) throws Exception {
        return commercialMapper.select(commercialDto);
    }
}
