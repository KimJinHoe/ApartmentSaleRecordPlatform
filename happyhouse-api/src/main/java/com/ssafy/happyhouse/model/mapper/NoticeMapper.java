package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> selectAll() throws SQLException;
	NoticeDto select(int no) throws SQLException;
	int create(NoticeDto noticeDto) throws SQLException;
	int update(NoticeDto noticeDto) throws SQLException;
	int delete(NoticeDto noticeDto) throws SQLException;
}
