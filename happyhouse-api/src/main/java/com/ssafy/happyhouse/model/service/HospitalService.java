package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.HospitalDto;

import java.util.List;

public interface HospitalService {
    List<HospitalDto> select(HospitalDto hospitalDto) throws Exception;
}
