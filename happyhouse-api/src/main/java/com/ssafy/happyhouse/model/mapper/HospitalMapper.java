package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.HospitalDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HospitalMapper {
    List<HospitalDto> select(HospitalDto hospitalDto) throws SQLException;
}
