package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.CommercialDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommercialMapper {
    List<CommercialDto> select(CommercialDto commercialDto) throws SQLException;
}
