package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeService {
	public List<NoticeDto> selectAll() throws Exception;
	public NoticeDto select(int no) throws Exception;
	public int create(NoticeDto noticeDto) throws Exception;
	public int update(NoticeDto noticeDto) throws Exception;
	public int delete(NoticeDto noticeDto) throws Exception;
}
