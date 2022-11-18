package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public List<HospitalDto> select(HospitalDto hospitalDto) throws Exception {
        return hospitalMapper.select(hospitalDto);
    }
}
