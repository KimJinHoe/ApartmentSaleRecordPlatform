package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void userRegister(MemberDto memberDto) throws Exception;
	public void userDelete(String userid) throws Exception;
	public void userUpdate(MemberDto memberDto) throws Exception;
}
