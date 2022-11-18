package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> selectAll() throws Exception {
		return noticeMapper.selectAll();
	}

	@Override
	public NoticeDto select(int no) throws Exception {
		return noticeMapper.select(no);
	}

	@Override
	public int create(NoticeDto noticeDto) throws Exception {
		return noticeMapper.create(noticeDto);
	}

	@Override
	public int update(NoticeDto noticeDto) throws Exception {
		return noticeMapper.update(noticeDto);
	}

	@Override
	public int delete(NoticeDto noticeDto) throws Exception {
		return noticeMapper.delete(noticeDto);
	}
}
