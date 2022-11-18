package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.CommercialDto;

import java.util.List;

public interface CommercialService {
    List<CommercialDto> select(CommercialDto commercialDto) throws Exception;
}
