package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public void userRegister(MemberDto memberDto) throws Exception {
		memberMapper.userRegister(memberDto);
	}

	@Override
	public void userDelete(String userid) throws Exception {
		memberMapper.userDelete(userid);
	}

	@Override
	public void userUpdate(MemberDto memberDto) throws Exception {
		memberMapper.userUpdate(memberDto);
	}

}
